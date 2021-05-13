package com.challenge.inditex.controllers;

import com.challenge.inditex.model.Price;
import com.challenge.inditex.responses.ApiError;
import com.challenge.inditex.responses.PriceResponse;
import com.challenge.inditex.services.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
public class PriceController {

    @Autowired
    @Qualifier("priceService")
    private PriceService priceService;

    @Operation(summary = "Find service by id", description = "The search for service is performed filtering by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = PriceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    @GetMapping(value = "/product/{productId}/brand/{brandId}/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceResponse> findByProductAndBrandAndDate(@PathVariable Long productId, @PathVariable Long brandId, @RequestParam(value = "date", required = true) String dateString) throws Exception {
        try {
            Price price;
            LocalDateTime date;
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
            date = LocalDateTime.parse(dateString, format);

            price = priceService.findAllByDateAndProductAndBrand(date, productId, brandId);
            if (price != null) {
                return ResponseEntity.status(HttpStatus.OK).body(new PriceResponse(price));
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No result found",null);
            }
        } catch (DateTimeParseException dte) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"The date is not in a valid format",null);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

}

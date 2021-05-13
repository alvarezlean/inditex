package com.challenge.inditex.controllers;

import com.challenge.inditex.model.Price;
import com.challenge.inditex.responses.PriceResponse;
import com.challenge.inditex.services.PriceService;
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

    @GetMapping(value = "/product/{productId}/brand/{brandId}/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceResponse> findByProductAndBrandAndDate(@PathVariable Long productId, @PathVariable Long brandId, @RequestParam(value = "date", required = true) String dateString) throws Exception {// @DateTimeFormat(fallbackPatterns = "yyyyy-MM-dd-hh.mm.ss") LocalDateTime date) throws Exception{
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

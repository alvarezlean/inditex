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
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    @Autowired
    @Qualifier("priceService")
    private PriceService priceService;

    @GetMapping(value = "/product/{productId}/brand/{brandId}/date/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceResponse> findByProductAndBrandAndDate(@PathVariable Long productId, @PathVariable Long brandId, @DateTimeFormat(pattern="yyyyy-MM-dd-HH.mm.ss") LocalDateTime date) throws Exception{
        Price price;
            price = priceService.findAllByDateAndProductAndBrand(date, productId, brandId);
            if(price!=null){
                return ResponseEntity.status(HttpStatus.OK).body(new PriceResponse(price));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
    }

}

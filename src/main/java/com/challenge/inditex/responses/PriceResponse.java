package com.challenge.inditex.responses;

import com.challenge.inditex.model.Price;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PriceResponse implements Serializable {

    private Long productId, brandId, priceId;
    private String curr;
    private BigDecimal price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate, endDate;


    public PriceResponse(Price priceEntity) {
        this.productId = priceEntity.getProduct().getId();
        this.brandId = priceEntity.getBrand().getId();
        this.priceId = priceEntity.getId();
        this.startDate = priceEntity.getStartDate();
        this.endDate = priceEntity.getEndDate();
        this.curr = priceEntity.getCurr();
        this.price = priceEntity.getPrice();

    }
}

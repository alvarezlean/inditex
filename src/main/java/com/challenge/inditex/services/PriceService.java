package com.challenge.inditex.services;

import com.challenge.inditex.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {
    public List<Price> findAllByDateAndProductAndBrand(LocalDateTime date, Long productId, Long brandId) throws Exception;
}

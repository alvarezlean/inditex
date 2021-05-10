package com.challenge.inditex.services.impl;

import com.challenge.inditex.model.Price;
import com.challenge.inditex.repository.PriceRepository;
import com.challenge.inditex.services.PriceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public class PriceServiceImpl implements PriceService {
    @Qualifier("priceRepository")
    private PriceRepository priceRepository;

    @Transactional(readOnly = true)
    public List<Price> findAllByDateAndProductAndBrand(LocalDateTime date, Long productId, Long brandId) throws Exception {
        return priceRepository.findAllByProductIdAndBrandIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(productId, brandId, date, date);
    }
}

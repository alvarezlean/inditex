package com.challenge.inditex.services.impl;

import com.challenge.inditex.model.Price;
import com.challenge.inditex.repository.PriceRepository;
import com.challenge.inditex.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service("priceService")
public class PriceServiceImpl implements PriceService {

    private PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(@Qualifier("priceRepository") PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Transactional(readOnly = true)
    public Price findAllByDateAndProductAndBrand(LocalDateTime date, Long productId, Long brandId) throws Exception {
        Price price = null;
        List<Price> priceList = priceRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId, date, date);
        if (priceList != null && priceList.size() > 0)
            price = priceList.stream().max(Comparator.comparing(Price::getPriority)).orElse(null);
        return price;
    }
}

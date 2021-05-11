package com.challenge.inditex.services.impl;

import com.challenge.inditex.model.Price;
import com.challenge.inditex.repository.PriceRepository;
import com.challenge.inditex.services.PriceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service("priceService")
public class PriceServiceImpl implements PriceService {
    @Qualifier("priceRepository")
    private PriceRepository priceRepository;

    @Transactional(readOnly = true)
    public Price findAllByDateAndProductAndBrand(LocalDateTime date, Long productId, Long brandId) throws Exception {
        Price price = null;
        List<Price> priceList = priceRepository.findAllByProductIdAndBrandIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(productId, brandId, date, date);
        if(priceList != null && priceList.size()>0)
            price = priceList.stream().max(Comparator.comparing(Price::getPriority)).orElse(null);
        return price;
    }
}

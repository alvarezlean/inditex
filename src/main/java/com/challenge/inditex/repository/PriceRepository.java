package com.challenge.inditex.repository;

import com.challenge.inditex.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Repository("priceRepository")
public interface PriceRepository extends JpaRepository<Price, Serializable> {
    List<Price> findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long productId, Long brandId, LocalDateTime date, LocalDateTime dateBis);
}

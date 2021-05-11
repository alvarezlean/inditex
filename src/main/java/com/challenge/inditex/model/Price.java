package com.challenge.inditex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Price {
    @Id
    Long id;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer priority;
    BigDecimal price;
    String curr;
    @ManyToOne(cascade = {CascadeType.ALL})
    Brand brand;
    @ManyToOne(cascade = {CascadeType.ALL})
    Product product;
}
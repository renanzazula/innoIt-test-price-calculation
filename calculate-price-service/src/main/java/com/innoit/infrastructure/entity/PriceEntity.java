package com.innoit.infrastructure.entity;

import com.innoit.domain.model.Amount;
import com.innoit.domain.model.Currency;
import com.innoit.domain.model.Price;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Data
@Entity
@NoArgsConstructor
@Table(name = "PRICES")
public class PriceEntity implements Serializable {

    @Id
    @Column(name = "PRICE_LIST", length = 10)
    private String id;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PRIORITY")
    private Long priority;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "CURR", length = 3)
    private String currency;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private BrandEntity brandEntity;

    public Price toDomainModel() {
        return Price.builder()
            .productId(this.getProductEntity().getId())
            .brandId(this.getBrandEntity().getId())
            .startDate(OffsetDateTime.of(this.getStartDate(), ZoneOffset.UTC))
            .endDate(OffsetDateTime.of(this.getEndDate(), ZoneOffset.UTC))
            .amount(Amount.builder()
            .price(this.getPrice())
            .currency(Currency.fromValue(this.getCurrency()))
            .build()).build();
    }
}

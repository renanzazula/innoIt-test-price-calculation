package com.innoit.infrastructure.entity;

import com.innoit.domain.model.common.Amount;
import com.innoit.domain.model.price.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
                .startDate(this.getStartDate())
                .endDate(this.getEndDate())
                .amount(Amount.builder()
                .price(this.getPrice())
                .currency(Currency.getInstance(this.getCurrency()))
                .build()).build();
    }
}

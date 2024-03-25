package com.innoit.domain.model.common;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Amount implements Serializable,Comparable<Amount> {
    
    private BigDecimal price;
    private Currency currency;

    @Override
    public int compareTo(Amount o) {
        return this.price.compareTo(o.getPrice());
    }
}

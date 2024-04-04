package com.innoit.domain.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern=Constants.PATTERN_NUMBER_FORMAT)
    private BigDecimal price;
    private Currency currency;

    @Override
    public int compareTo(Amount o) {
        return this.price.compareTo(o.getPrice());
    }
}

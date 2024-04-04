package com.innoit.domain.model.price;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.innoit.domain.model.common.Amount;
import com.innoit.domain.model.common.Constants;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Price implements Serializable {

    private String brandId;
    private String productId;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = Constants.PATTERN_DATE_AND_TIME)
    private LocalDateTime startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = Constants.PATTERN_DATE_AND_TIME)
    private LocalDateTime endDate;
    private Amount amount;

}

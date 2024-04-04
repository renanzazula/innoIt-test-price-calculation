package com.innoit.infrastructure.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CalculatePriceException extends RuntimeException {

    private HttpStatus errorCode;
    private String errorMessage;

}

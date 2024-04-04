package com.innoit.infrastructure.advice;

import com.innoit.infrastructure.exception.CalculatePriceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BasicAdvice  {

    @ExceptionHandler(CalculatePriceException.class)
    public ResponseEntity<String> handleEmptyInput(CalculatePriceException exception){
        return new ResponseEntity<>(exception.getErrorMessage(), exception.getErrorCode());
    }

}

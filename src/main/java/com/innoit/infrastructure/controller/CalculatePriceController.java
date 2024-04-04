package com.innoit.infrastructure.controller;

import com.innoit.domain.model.price.CalculatePrice;
import com.innoit.domain.model.price.Price;
import com.innoit.domain.ports.in.CalculatePriceUseCaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping(CalculatePriceController.CALCULATE_PRICE_BASE_URL)
public class CalculatePriceController {

    public static final String CALCULATE_PRICE_BASE_URL = "/api/calculate/prices";

    private final CalculatePriceUseCaseService calculatePriceUseCaseService;

    @PostMapping()
    public ResponseEntity<Price> calculatePrice(@RequestBody CalculatePrice calculatePrice) {
        return calculatePriceUseCaseService.calculatePrice(calculatePrice)
                .map(priceResponse -> new ResponseEntity<>(priceResponse, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }


}

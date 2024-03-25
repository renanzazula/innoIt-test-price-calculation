package com.innoit.infrastructure.controller;

import com.innoit.application.services.CalculatePriceService;
import com.innoit.domain.model.price.CalculatePrice;
import com.innoit.domain.model.price.Price;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping(CalculatePriceController.CALCULATE_PRICE_BASE_URL)
public class CalculatePriceController {

    public static final String CALCULATE_PRICE_BASE_URL = "/api/calculate/prices";

    private final CalculatePriceService calculatePriceService;

    @GetMapping()
    public ResponseEntity<Price> calculatePrice(@RequestBody CalculatePrice calculatePrice) {
        return calculatePriceService.calculatePrice(calculatePrice)
                .map(priceResponse -> new ResponseEntity<>(priceResponse, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }


}

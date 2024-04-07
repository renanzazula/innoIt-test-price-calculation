package com.innoit.infrastructure.controller;

import com.innoit.domain.model.CalculatePrice;
import com.innoit.domain.model.Price;
import com.innoit.domain.ports.in.CalculatePriceUseCaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping(CalculatePriceController.CALCULATE_PRICE_BASE_URL)
public class CalculatePriceController implements CalculateApi {

    public static final String CALCULATE_PRICE_BASE_URL = "/api/calculate/prices";

    private final CalculatePriceUseCaseService calculatePriceUseCaseService;

    @Override
    @PostMapping()
    public ResponseEntity<Price> calculatePrice(@RequestBody @Valid CalculatePrice calculatePrice) {
        return calculatePriceUseCaseService.calculatePrice(calculatePrice)
                .map(priceResponse -> new ResponseEntity<>(priceResponse, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }


}

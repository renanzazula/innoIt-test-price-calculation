package com.innoit.application.services;

import com.innoit.domain.model.price.CalculatePrice;
import com.innoit.domain.model.price.Price;
import com.innoit.domain.ports.in.CalculatePriceUseCaseService;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CalculatePriceService implements CalculatePriceUseCaseService {

    private final CalculatePriceUseCaseService calculatePriceUseCaseService;

    @Override
    public Optional<Price> calculatePrice(CalculatePrice calculatePrice) {
        return calculatePriceUseCaseService.calculatePrice(calculatePrice);
    }
}

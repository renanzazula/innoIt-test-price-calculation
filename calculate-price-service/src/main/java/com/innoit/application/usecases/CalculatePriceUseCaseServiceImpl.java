package com.innoit.application.usecases;

import com.innoit.domain.model.CalculatePrice;
import com.innoit.domain.model.Price;
import com.innoit.domain.ports.in.CalculatePriceUseCaseService;
import com.innoit.domain.ports.out.CalculatePriceRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CalculatePriceUseCaseServiceImpl implements CalculatePriceUseCaseService {

    private final CalculatePriceRepositoryPort calculatePriceRepositoryPort;

    @Override
    public Optional<Price> calculatePrice(CalculatePrice calculatePrice) {
        return calculatePriceRepositoryPort.calculatePriceByApplicationDateAndProductIdAndBrandId(calculatePrice);
    }
}

package com.innoit.domain.ports.in;

import com.innoit.domain.model.CalculatePrice;
import com.innoit.domain.model.Price;

import java.util.Optional;

public interface CalculatePriceUseCaseService {

    Optional<Price> calculatePrice(CalculatePrice calculatePrice);
}

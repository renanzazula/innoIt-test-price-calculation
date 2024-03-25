package com.innoit.domain.ports.in;

import com.innoit.domain.model.price.CalculatePrice;
import com.innoit.domain.model.price.Price;

import java.util.Optional;

public interface CalculatePriceUseCaseService {

    Optional<Price> calculatePrice(CalculatePrice calculatePrice);
}

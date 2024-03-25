package com.innoit.domain.ports.out;

import com.innoit.domain.model.price.CalculatePrice;
import com.innoit.domain.model.price.Price;

import java.util.Optional;

public interface CalculatePriceRepositoryPort {

    Optional<Price> calculatePriceByApplicationDateAndProductIdAndBrandId(CalculatePrice calculatePrice);





}

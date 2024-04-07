package com.innoit.domain.ports.out;

import com.innoit.domain.model.CalculatePrice;
import com.innoit.domain.model.Price;

import java.util.Optional;

public interface CalculatePriceRepositoryPort {

    Optional<Price> calculatePriceByApplicationDateAndProductIdAndBrandId(CalculatePrice calculatePrice);





}

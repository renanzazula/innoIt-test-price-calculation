package com.innoit.infrastructure.repository;


import com.innoit.domain.model.CalculatePrice;
import com.innoit.domain.model.Price;
import com.innoit.domain.ports.out.CalculatePriceRepositoryPort;
import com.innoit.infrastructure.entity.PriceEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;


@Component
@AllArgsConstructor
public class JpaCalculatePriceRepositoryAdapter implements CalculatePriceRepositoryPort {

    private final JpaPriceRepository jpaPriceRepository;
    @Override
    public Optional<Price> calculatePriceByApplicationDateAndProductIdAndBrandId(CalculatePrice calculatePrice) {
        return jpaPriceRepository.findAllByPricesByApplicationDateAndProductIdAndBrandId(calculatePrice.getApplicationDate().toLocalDateTime(), calculatePrice.getProductId(), calculatePrice.getBrandId())
                .stream().max(Comparator.comparing(PriceEntity::getPriority))
                .stream()
                .findAny()
                .map(PriceEntity::toDomainModel);
    }
}

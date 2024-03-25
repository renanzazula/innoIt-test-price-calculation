package com.innoit.infrastructure.repository;

import com.innoit.domain.model.price.CalculatePrice;
import com.innoit.domain.model.price.Price;
import com.innoit.domain.ports.out.CalculatePriceRepositoryPort;
import com.innoit.infrastructure.entity.PriceEntity;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

@Component
public class JpaCalculatePriceRepositoryAdapter implements CalculatePriceRepositoryPort {

    private final JpaPriceRepository jpaPriceRepository;

    public JpaCalculatePriceRepositoryAdapter(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Override
    public Optional<Price> calculatePriceByApplicationDateAndProductIdAndBrandId(CalculatePrice calculatePrice) {
        return jpaPriceRepository.findAllByPricesByApplicationDateAndProductIdAndBrandId(calculatePrice.getApplicationDate(), calculatePrice.getProductId(), calculatePrice.getBrandId())
                .stream().max(Comparator.comparing(PriceEntity::getPriority))
                .stream()
                .findAny()
                .map(PriceEntity::toDomainModel);
    }
}

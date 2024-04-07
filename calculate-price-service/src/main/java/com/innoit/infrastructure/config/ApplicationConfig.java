package com.innoit.infrastructure.config;

import com.innoit.application.services.CalculatePriceService;
import com.innoit.application.usecases.CalculatePriceUseCaseServiceImpl;
import com.innoit.domain.ports.out.CalculatePriceRepositoryPort;
import com.innoit.infrastructure.repository.JpaPriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CalculatePriceService calculatePriceService(CalculatePriceRepositoryPort calculatePriceRepositoryPort) {
        return new CalculatePriceService(new CalculatePriceUseCaseServiceImpl(calculatePriceRepositoryPort));
    }

    @Bean
    public JpaPriceRepository priceRepositoryPort(JpaPriceRepository jpaPriceRepository) {
        return jpaPriceRepository;
    }




}

package com.innoit.application.usecases;

import com.innoit.domain.model.Amount;
import com.innoit.domain.model.CalculatePrice;
import com.innoit.domain.model.Currency;
import com.innoit.domain.model.Price;
import com.innoit.domain.ports.out.CalculatePriceRepositoryPort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatePriceUseCaseServiceImplTest {

    @Mock
    private CalculatePriceRepositoryPort calculatePriceRepositoryPort;

    @InjectMocks
    private CalculatePriceUseCaseServiceImpl calculatePriceUseCaseService;

    @ParameterizedTest
    @MethodSource("com.innoit.infrastructure.BaseTest#testScenariosCalculatePriceWithExpectedValues")
    void testScenarios_For_CalculatePrice(OffsetDateTime applicationDate, String productId, String brandId,
                                          String expectedProductId, String expectedBrandId, OffsetDateTime expectedStartDate,
                                          OffsetDateTime expectedEndDate, BigDecimal expectedPrice, String expectedCurrency) {

        CalculatePrice calculatePrice = CalculatePrice.builder().applicationDate(applicationDate)
                .productId(productId)
                .brandId(brandId)
                .build();

        Price priceExpected = Price.builder()
                .productId(expectedProductId)
                .brandId(expectedBrandId)
                .startDate(expectedStartDate)
                .endDate(expectedEndDate)
                .amount(Amount.builder()
                        .price(expectedPrice)
                        .currency(Currency.valueOf(expectedCurrency)).build()).build();

        when(calculatePriceRepositoryPort.calculatePriceByApplicationDateAndProductIdAndBrandId(calculatePrice))
                .thenReturn(Optional.of(priceExpected));


        Optional<Price> result = calculatePriceUseCaseService.calculatePrice(calculatePrice);

        assertEquals(Optional.of(priceExpected), result);
    }
}

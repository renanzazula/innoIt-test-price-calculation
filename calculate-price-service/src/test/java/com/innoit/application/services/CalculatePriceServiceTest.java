package com.innoit.application.services;

import com.innoit.domain.model.Amount;
import com.innoit.domain.model.CalculatePrice;
import com.innoit.domain.model.Currency;
import com.innoit.domain.model.Price;
import com.innoit.domain.ports.in.CalculatePriceUseCaseService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatePriceServiceTest {

    @Mock
    private CalculatePriceUseCaseService calculatePriceUseCaseService;


    @ParameterizedTest
    @MethodSource("com.innoit.infrastructure.BaseTest#testScenariosCalculatePriceWithExpectedValues")
    void testScenarios_For_CalculatePrice(OffsetDateTime applicationDate, String productId, String brandId,
                                          String expectedProductId, String expectedBrandId, OffsetDateTime expectedStartDate,
                                          OffsetDateTime expectedEndDate, BigDecimal expectedPrice, String expectedCurrency)  {

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
                        .price(expectedPrice).currency(Currency.fromValue(expectedCurrency)).build()).build();

        when(calculatePriceUseCaseService.calculatePrice(calculatePrice)).thenReturn(Optional.of(priceExpected));
        Optional<Price> result = calculatePriceUseCaseService.calculatePrice(calculatePrice);
        assertEquals(Optional.of(priceExpected), result);
    }
}

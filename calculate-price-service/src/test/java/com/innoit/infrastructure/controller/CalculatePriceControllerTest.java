package com.innoit.infrastructure.controller;


import com.innoit.application.services.CalculatePriceService;
import com.innoit.domain.model.Amount;
import com.innoit.domain.model.CalculatePrice;
import com.innoit.domain.model.Currency;
import com.innoit.domain.model.Price;
import com.innoit.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CalculatePriceControllerTest extends BaseTest {

    @Mock
    private CalculatePriceService calculatePriceService;

    @InjectMocks
    private CalculatePriceController calculatePriceController;

    public CalculatePriceControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculatePrice_ReturnsPrice_WhenServiceReturnsResult() {
        CalculatePrice calculatePrice = new CalculatePrice();
        Price expectedPrice = new Price();
        when(calculatePriceService.calculatePrice(calculatePrice)).thenReturn(Optional.of(expectedPrice));
        ResponseEntity<Price> response = calculatePriceController.calculatePrice(calculatePrice);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice, response.getBody());
    }

    @Test
    void calculatePrice_ReturnsNoContent_WhenServiceReturnsEmptyResult() {
        CalculatePrice calculatePrice = new CalculatePrice(); // Create your input CalculatePrice object
        when(calculatePriceService.calculatePrice(calculatePrice)).thenReturn(Optional.empty());
        ResponseEntity<Price> response = calculatePriceController.calculatePrice(calculatePrice);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @ParameterizedTest
    @MethodSource("com.innoit.infrastructure.BaseTest#testScenariosCalculatePriceWithExpectedValues")
    void testScenarios_For_CalculatePrice(OffsetDateTime applicationDate, String productId, String brandId,
                                          String expectedProductId, String expectedBrandId, OffsetDateTime expectedStartDate,
                                          OffsetDateTime expectedEndDate, BigDecimal expectedPrice, String expectedCurrency)  {

        CalculatePrice calculatePrice = CalculatePrice.builder().applicationDate(applicationDate)
                .productId(productId)
                .brandId(brandId)
                .build();

        when(calculatePriceService
                .calculatePrice(calculatePrice))
                    .thenReturn(Optional.of(Price.builder()
                        .productId(productId)
                        .brandId(brandId)
                        .startDate(expectedStartDate)
                        .endDate(expectedEndDate)
                        .amount(Amount.builder().price(expectedPrice)
                        .currency(Currency.valueOf(expectedCurrency)).build()).build()));

        ResponseEntity<Price> response = calculatePriceController.calculatePrice(calculatePrice);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals(expectedProductId, response.getBody().getProductId());
        assertEquals(expectedBrandId, response.getBody().getBrandId());
        assertEquals(expectedStartDate, response.getBody().getStartDate());
        assertEquals(expectedEndDate, response.getBody().getEndDate());

        assertNotNull(response.getBody().getAmount());
        assertEquals(expectedPrice, response.getBody().getAmount().getPrice());

        assertNotNull(response.getBody().getAmount().getCurrency());
        assertEquals(expectedCurrency, response.getBody().getAmount().getCurrency().toString());

    }



}

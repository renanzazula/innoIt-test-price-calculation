package com.innoit.infrastructure.controller;


import com.innoit.domain.exceptions.BadRequestException;
import com.innoit.domain.model.Amount;
import com.innoit.domain.model.CalculatePrice;
import com.innoit.domain.model.Currency;
import com.innoit.domain.model.Price;
import com.innoit.domain.ports.in.CalculatePriceUseCaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {CalculatePriceController.class})
class CalculatePriceControllerMVCTest extends AbstractRestControllerTest {

    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CalculatePriceUseCaseService calculatePriceUseCaseService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @ParameterizedTest
    @MethodSource("com.innoit.infrastructure.BaseTest#testScenariosCalculatePriceWithExpectedValues")
    void testScenarios_For_CalculatePrice(OffsetDateTime applicationDate, String productId, String brandId,
                                          String expectedProductId, String expectedBrandId, OffsetDateTime expectedStartDate,
                                          OffsetDateTime expectedEndDate, BigDecimal expectedPrice, String expectedCurrency) throws Exception {

        CalculatePrice calculatePriceRequest = CalculatePrice.builder()
                .applicationDate(applicationDate)
                .productId(productId)
                .brandId(brandId)
                .build();

        Price priceResponse = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .startDate(expectedStartDate)
                .endDate(expectedEndDate)
                .amount(Amount.builder().price(expectedPrice)
                        .currency(Currency.valueOf(expectedCurrency)).build())
                .build();

        when(calculatePriceUseCaseService
                .calculatePrice(calculatePriceRequest))
                .thenReturn(Optional.of(priceResponse));

        mockMvc.perform(post(CalculatePriceController.CALCULATE_PRICE_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(calculatePriceRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(expectedProductId))
                .andExpect(jsonPath("$.brandId").value(expectedBrandId))
                .andExpect(jsonPath("$.startDate").value(expectedStartDate.format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(jsonPath("$.endDate").value(expectedEndDate.format(DateTimeFormatter.ISO_DATE_TIME)))
                .andExpect(jsonPath("$.amount.price").value(expectedPrice.doubleValue()))
                .andExpect(jsonPath("$.amount.currency").value(expectedCurrency));
    }

    @Test
    void testCalculatePriceFailWhenTheRequestNotGiven() throws Exception {
        CalculatePrice calculatePriceRequest = CalculatePrice.builder()
                .applicationDate(OffsetDateTime.now())
                .productId(PRODUCT_ID)
                .brandId(BRAND_ID)
                .build();

        Price priceResponse = Price.builder()
                .productId(PRODUCT_ID)
                .brandId(BRAND_ID)
                .startDate(OffsetDateTime.now())
                .endDate(OffsetDateTime.now())
                .amount(Amount.builder().price(AMOUNT_ONE)
                        .currency(Currency.valueOf(CURRENCY_EUR)).build())
                .build();

        when(calculatePriceUseCaseService
                .calculatePrice(calculatePriceRequest))
                .thenReturn(Optional.of(priceResponse));

        mockMvc.perform(post(CalculatePriceController.CALCULATE_PRICE_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testCalculatePriceFailWhenTheApplicationDateNotGiven() throws Exception {
        CalculatePrice calculatePriceRequest = CalculatePrice.builder()
                .productId(PRODUCT_ID)
                .brandId(BRAND_ID)
                .build();

        Price priceResponse = Price.builder()
                .productId(PRODUCT_ID)
                .brandId(BRAND_ID)
                .startDate(OffsetDateTime.now())
                .endDate(OffsetDateTime.now())
                .amount(Amount.builder().price(AMOUNT_ONE)
                        .currency(Currency.valueOf(CURRENCY_EUR)).build())
                .build();

        when(calculatePriceUseCaseService
                .calculatePrice(calculatePriceRequest))
                .thenReturn(Optional.of(priceResponse));

        mockMvc.perform(post(CalculatePriceController.CALCULATE_PRICE_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(calculatePriceRequest)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void testCalculatePriceFailWhenTheProductIdNotGiven() throws Exception {
        CalculatePrice calculatePriceRequest = CalculatePrice.builder()
                .applicationDate(OffsetDateTime.now())
                .brandId(BRAND_ID)
                .build();

        Price priceResponse = Price.builder()
                .productId(PRODUCT_ID)
                .brandId(BRAND_ID)
                .startDate(OffsetDateTime.now())
                .endDate(OffsetDateTime.now())
                .amount(Amount.builder().price(AMOUNT_ONE)
                        .currency(Currency.valueOf(CURRENCY_EUR)).build())
                .build();

        when(calculatePriceUseCaseService
                .calculatePrice(calculatePriceRequest))
                .thenReturn(Optional.of(priceResponse));

        mockMvc.perform(post(CalculatePriceController.CALCULATE_PRICE_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(calculatePriceRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCalculatePriceFailWhenTheBrandIdNotGiven() throws Exception {
        CalculatePrice calculatePriceRequest = CalculatePrice.builder()
                .applicationDate(OffsetDateTime.now())
                .productId(PRODUCT_ID)
                .build();

        Price priceResponse = Price.builder()
                .productId(PRODUCT_ID)
                .brandId(BRAND_ID)
                .startDate(OffsetDateTime.now())
                .endDate(OffsetDateTime.now())
                .amount(Amount.builder().price(AMOUNT_ONE)
                        .currency(Currency.valueOf(CURRENCY_EUR)).build())
                .build();

        when(calculatePriceUseCaseService
                .calculatePrice(calculatePriceRequest))
                .thenReturn(Optional.of(priceResponse));

        mockMvc.perform(post(CalculatePriceController.CALCULATE_PRICE_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(calculatePriceRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCalculatePriceWhenIsFoundAnyMatch() throws Exception {
        CalculatePrice calculatePriceRequest = CalculatePrice.builder()
                .applicationDate(OffsetDateTime.now())
                .productId(PRODUCT_ID)
                .brandId(BRAND_ID)
                .build();

        when(calculatePriceUseCaseService
                .calculatePrice(calculatePriceRequest))
                .thenReturn(Optional.empty());

        mockMvc.perform(post(CalculatePriceController.CALCULATE_PRICE_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(calculatePriceRequest)))
                .andExpect(status().isNoContent());
    }

    @Test
    void testCalculatePriceErrorResponse() throws Exception {
        CalculatePrice calculatePriceRequest = CalculatePrice.builder()
                .applicationDate(OffsetDateTime.now())
                .productId(PRODUCT_ID)
                .brandId(BRAND_ID)
                .build();

        when(calculatePriceUseCaseService.calculatePrice(any())).thenThrow(new RuntimeException());

        mockMvc.perform(post(CalculatePriceController.CALCULATE_PRICE_BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(calculatePriceRequest)))
                .andExpect(status().isInternalServerError());
    }


}

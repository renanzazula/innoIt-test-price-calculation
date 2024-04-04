package com.innoit.infrastructure.controller;

import com.innoit.domain.model.common.Amount;
import com.innoit.domain.model.price.CalculatePrice;
import com.innoit.domain.model.price.Price;
import com.innoit.domain.ports.in.CalculatePriceUseCaseService;
import org.junit.jupiter.api.BeforeEach;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {CalculatePriceController.class})
class CalculatePriceControllerMVCTest  extends AbstractRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CalculatePriceUseCaseService calculatePriceUseCaseService;

    @Autowired
    protected WebApplicationContext wac;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                    .webAppContextSetup(wac)
                    .build();
    }
    @ParameterizedTest
    @MethodSource("com.innoit.infrastructure.BaseTest#testScenariosCalculatePriceWithExpectedValues")
    void testScenarios_For_CalculatePrice(LocalDateTime applicationDate, String productId, String brandId,
                                          String expectedProductId, String expectedBrandId, LocalDateTime expectedStartDate,
                                          LocalDateTime expectedEndDate, BigDecimal expectedPrice, String expectedCurrency) throws Exception {

        CalculatePrice calculatePriceRequest = CalculatePrice.builder().applicationDate(applicationDate)
                .productId(productId)
                .brandId(brandId)
                .build();

        Price priceResponse = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .startDate(expectedStartDate)
                .endDate(expectedEndDate)
                .amount(Amount.builder().price(expectedPrice)
                                        .currency(Currency.getInstance(expectedCurrency)).build())
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
                            .andExpect(jsonPath("$.startDate").value(expectedStartDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                            .andExpect(jsonPath("$.endDate").value(expectedEndDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                            .andExpect(jsonPath("$.amount.price").value(expectedPrice.doubleValue()))
                            .andExpect(jsonPath("$.amount.currency").value(expectedCurrency));
    }

}

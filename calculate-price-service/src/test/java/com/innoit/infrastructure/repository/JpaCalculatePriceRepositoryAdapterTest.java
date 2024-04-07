package com.innoit.infrastructure.repository;


import com.innoit.domain.model.CalculatePrice;
import com.innoit.domain.model.Price;
import com.innoit.infrastructure.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql("/scripts/dataset.sql")
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
class JpaCalculatePriceRepositoryAdapterTest extends BaseTest {

    @Autowired
    private JpaPriceRepository jpaPriceRepository;
    private JpaCalculatePriceRepositoryAdapter jpaCalculatePriceRepositoryAdapter;

    @BeforeEach
    void setUp() {
        jpaCalculatePriceRepositoryAdapter = new JpaCalculatePriceRepositoryAdapter(jpaPriceRepository);
    }

    @ParameterizedTest
    @MethodSource("com.innoit.infrastructure.BaseTest#testScenariosCalculatePriceWithExpectedValues")
    void testCalculateGivenCorrectPrice(OffsetDateTime applicationDate, String productId, String brandId,
                                        String expectedProductId, String expectedBrandId, OffsetDateTime expectedStartDate,
                                        OffsetDateTime expectedEndDate, BigDecimal expectedPrice, String expectedCurrency) {


        Optional<Price> price = jpaCalculatePriceRepositoryAdapter
                .calculatePriceByApplicationDateAndProductIdAndBrandId(CalculatePrice.builder()
                        .applicationDate(applicationDate)
                        .productId(productId)
                        .brandId(brandId).build());

        assertTrue(price.isPresent());
        assertEquals(expectedPrice, price.get().getAmount().getPrice());

        assertEquals(expectedProductId, price.get().getProductId());
        assertEquals(expectedBrandId, price.get().getBrandId());
        assertEquals(expectedStartDate, price.get().getStartDate());
        assertEquals(expectedEndDate, price.get().getEndDate());

        assertNotNull(price.get().getAmount());
        assertEquals(expectedPrice, price.get().getAmount().getPrice());

        assertNotNull(price.get().getAmount().getCurrency());
        assertEquals(expectedCurrency, price.get().getAmount().getCurrency().toString());

    }

    @Test
    void testIfNotExistTestDateForThisUseCase() {
        Optional<Price> price = jpaCalculatePriceRepositoryAdapter
                .calculatePriceByApplicationDateAndProductIdAndBrandId(CalculatePrice.builder()
                        .applicationDate(REQUEST_NOW).productId(PRODUCT_ID).brandId(BRAND_ID).build());
        assertTrue(price.isEmpty());
    }

}

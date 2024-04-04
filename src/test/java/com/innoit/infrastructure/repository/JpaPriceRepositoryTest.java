package com.innoit.infrastructure.repository;

import com.innoit.infrastructure.BaseTest;
import com.innoit.infrastructure.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Sql("/scripts/dataset.sql")
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
class JpaPriceRepositoryTest extends BaseTest {

    @Autowired
    private JpaPriceRepository jpaPriceRepository;
    @ParameterizedTest
    @MethodSource("com.innoit.infrastructure.BaseTest#testScenariosCalculatePriceRepository")
    void testIfExistTestDateForThisUseCase(LocalDateTime applicationDate, String productId, String brandId) {
        List<PriceEntity> priceList = jpaPriceRepository.findAllByPricesByApplicationDateAndProductIdAndBrandId(applicationDate, productId, brandId);
        assertFalse(priceList.isEmpty());
    }

    @Test
    void testIfNotExistTestDateForThisUseCase()  {
        List<PriceEntity> priceList = jpaPriceRepository.findAllByPricesByApplicationDateAndProductIdAndBrandId(REQUEST_NOW, PRODUCT_ID, BRAND_ID);
        assertTrue(priceList.isEmpty());
    }

}

package com.innoit.infrastructure.repository;

import com.innoit.infrastructure.BaseTest;
import com.innoit.infrastructure.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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

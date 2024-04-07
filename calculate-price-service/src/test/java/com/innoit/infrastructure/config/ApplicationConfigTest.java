package com.innoit.infrastructure.config;

import com.innoit.application.services.CalculatePriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void testCalculatePriceServiceBean() {
        CalculatePriceService calculatePriceService = context.getBean(CalculatePriceService.class);
        assertNotNull(calculatePriceService);
    }

}

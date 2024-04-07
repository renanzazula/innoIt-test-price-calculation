package com.innoit.infrastructure;

import org.junit.jupiter.params.provider.Arguments;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.stream.Stream;

public class BaseTest {

    public static final String CURRENCY_EUR = "EUR";

    public static final String PRODUCT_ID = String.valueOf(35455);
    public static final String BRAND_ID = String.valueOf(1);

    public static final OffsetDateTime REQUEST_NOW = OffsetDateTime.now();



    public static final OffsetDateTime REQUEST_ONE =  OffsetDateTime.of(LocalDateTime.of(2020, 6, 14, 10, 0, 0), ZoneOffset.UTC);
    public static final OffsetDateTime REQUEST_TWO =  OffsetDateTime.of(LocalDateTime.of(2020, 6, 14, 16, 0, 0), ZoneOffset.UTC);
    public static final OffsetDateTime REQUEST_THREE =  OffsetDateTime.of(LocalDateTime.of(2020, 6, 14, 21, 0, 0), ZoneOffset.UTC);
    public static final OffsetDateTime REQUEST_FOUR =  OffsetDateTime.of(LocalDateTime.of(2020, 6, 15, 10, 0, 0), ZoneOffset.UTC);
    public static final OffsetDateTime REQUEST_FIVE =  OffsetDateTime.of(LocalDateTime.of(2020, 6, 16, 21, 0, 0), ZoneOffset.UTC);

    public static final LocalDateTime EXPECTED_START_DATE_ONE   =  LocalDateTime.of(2020, 6, 14, 0, 0, 0);
    public static final LocalDateTime EXPECTED_END_DATE_ONE     =  LocalDateTime.of(2020, 12, 31, 23, 59, 59);
    public static final LocalDateTime EXPECTED_START_DATE_TWO   =  LocalDateTime.of(2020, 6, 14, 15, 0, 0);
    public static final LocalDateTime EXPECTED_END_DATE_TWO     =  LocalDateTime.of(2020, 6, 14, 18, 30, 0);
    public static final LocalDateTime EXPECTED_START_DATE_THREE = EXPECTED_START_DATE_ONE;
    public static final LocalDateTime EXPECTED_END_DATE_THREE   = EXPECTED_END_DATE_ONE;
    public static final LocalDateTime EXPECTED_START_DATE_FOUR  =  LocalDateTime.of(2020, 6, 15, 0, 0, 0);
    public static final LocalDateTime EXPECTED_END_DATE_FOUR    =  LocalDateTime.of(2020, 6, 15, 11, 0, 0);
    public static final LocalDateTime EXPECTED_START_DATE_FIVE  =  LocalDateTime.of(2020, 6, 15, 16, 0, 0);
    public static final LocalDateTime EXPECTED_END_DATE_FIVE  =  LocalDateTime.of(2020, 12, 31, 23, 59, 59);

    public static final BigDecimal AMOUNT_ONE = new BigDecimal("35.50");
    public static final BigDecimal AMOUNT_TWO = new BigDecimal("25.45");
    public static final BigDecimal AMOUNT_FOUR = new BigDecimal("30.50");
    public static final BigDecimal AMOUNT_FIVE = new BigDecimal("38.95");

    static Stream<Arguments> testScenariosCalculatePriceRepository() {
        return Stream.of(
            Arguments.of(REQUEST_ONE.toLocalDateTime(), PRODUCT_ID, BRAND_ID, AMOUNT_ONE),
            Arguments.of(REQUEST_TWO.toLocalDateTime(), PRODUCT_ID, BRAND_ID, AMOUNT_TWO),
            Arguments.of(REQUEST_THREE.toLocalDateTime(), PRODUCT_ID, BRAND_ID, AMOUNT_ONE),
            Arguments.of(REQUEST_FOUR.toLocalDateTime(), PRODUCT_ID, BRAND_ID, AMOUNT_FOUR),
            Arguments.of(REQUEST_FIVE.toLocalDateTime(), PRODUCT_ID, BRAND_ID, AMOUNT_FIVE)
        );
    }

   

    static Stream<Arguments> testScenariosCalculatePriceWithExpectedValues() {
        // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
        // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
        return Stream.of(
            Arguments.of(REQUEST_ONE,   PRODUCT_ID, BRAND_ID, PRODUCT_ID, BRAND_ID, OffsetDateTime.of(EXPECTED_START_DATE_ONE, ZoneOffset.UTC),   OffsetDateTime.of(EXPECTED_END_DATE_ONE,ZoneOffset.UTC), AMOUNT_ONE, CURRENCY_EUR),
            Arguments.of(REQUEST_TWO,   PRODUCT_ID, BRAND_ID, PRODUCT_ID, BRAND_ID, OffsetDateTime.of(EXPECTED_START_DATE_TWO, ZoneOffset.UTC),   OffsetDateTime.of(EXPECTED_END_DATE_TWO,ZoneOffset.UTC), AMOUNT_TWO, CURRENCY_EUR),
            Arguments.of(REQUEST_THREE, PRODUCT_ID, BRAND_ID, PRODUCT_ID, BRAND_ID, OffsetDateTime.of(EXPECTED_START_DATE_THREE, ZoneOffset.UTC), OffsetDateTime.of(EXPECTED_END_DATE_THREE,ZoneOffset.UTC), AMOUNT_ONE, CURRENCY_EUR),
            Arguments.of(REQUEST_FOUR,  PRODUCT_ID, BRAND_ID, PRODUCT_ID, BRAND_ID, OffsetDateTime.of(EXPECTED_START_DATE_FOUR, ZoneOffset.UTC),  OffsetDateTime.of(EXPECTED_END_DATE_FOUR,ZoneOffset.UTC), AMOUNT_FOUR, CURRENCY_EUR),
            Arguments.of(REQUEST_FIVE,  PRODUCT_ID, BRAND_ID, PRODUCT_ID, BRAND_ID, OffsetDateTime.of(EXPECTED_START_DATE_FIVE, ZoneOffset.UTC),  OffsetDateTime.of(EXPECTED_END_DATE_FIVE,ZoneOffset.UTC), AMOUNT_FIVE, CURRENCY_EUR)
        );
    }
}



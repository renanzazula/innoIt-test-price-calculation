CREATE TABLE PRICES
(
    price_list VARCHAR(10) NOT NULL,
    start_date TIMESTAMP,
    end_date   TIMESTAMP,
    priority   BIGINT,
    price      DECIMAL,
    curr       VARCHAR(3),
    product_id VARCHAR(10),
    brand_id   VARCHAR(10),
    CONSTRAINT pk_prices PRIMARY KEY (price_list)
);

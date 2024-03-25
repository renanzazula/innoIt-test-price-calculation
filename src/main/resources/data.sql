create table brand (brand_id varchar(10) not null,
                    brand_name varchar(300),
                    primary key (brand_id));

create table product (product_id varchar(10) not null,
                      product_name varchar(300),
                      primary key (product_id)
);

create table prices (price_list varchar(10) not null,
                     curr varchar(3),
                     end_date timestamp,
                     price decimal(19,2),
                     priority bigint,
                     start_date timestamp,
                     brand_id varchar(10),
                     product_id varchar(10),
                     primary key (price_list));

INSERT INTO BRAND(brand_id, brand_name) VALUES ('1','ZARA');
INSERT INTO PRODUCT(product_id, product_name) VALUES ('35455','PRODUCT 1');

INSERT INTO PRICES (price_list,start_date, end_date, price, curr, priority,brand_id,product_id) VALUES ( '1', '2020-06-14 00.00.00','2020-12-31 23.59.59', 35.50, 'EUR', 0, '1','35455');
INSERT INTO PRICES (price_list,start_date, end_date, price, curr, priority,brand_id,product_id) VALUES ( '2', '2020-06-14 15.00.00','2020-06-14 18.30.00', 25.45, 'EUR', 1, '1','35455');
INSERT INTO PRICES (price_list,start_date, end_date, price, curr, priority,brand_id,product_id) VALUES ( '3', '2020-06-15 00.00.00','2020-06-15 11.00.00', 30.50, 'EUR', 1, '1','35455');
INSERT INTO PRICES (price_list,start_date, end_date, price, curr, priority,brand_id,product_id) VALUES ( '4', '2020-06-15 16.00.00','2020-12-31 23.59.59', 38.95, 'EUR', 1, '1','35455');

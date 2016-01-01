﻿DROP TABLE IF EXISTS CUSTOMER CASCADE;
CREATE TABLE CUSTOMER (
	ID SERIAL PRIMARY KEY,
	USERNAME TEXT NOT NULL UNIQUE,
	PASSWORD TEXT NOT NULL,
	EMAIL TEXT NOT NULL UNIQUE,
	ROLE_ID INT REFERENCES USER_ROLE(ID),
	ENABLED BOOLEAN NOT NULL
);

INSERT INTO CUSTOMER (USERNAME, EMAIL, PASSWORD, ROLE_ID, ENABLED) VALUES ('admin', 'admin@gmail.com', 'admin', 1, TRUE);
INSERT INTO CUSTOMER (USERNAME, EMAIL, PASSWORD, ROLE_ID, ENABLED) VALUES ('A', 'A@gmail.com', 'Apass', 2, TRUE);
INSERT INTO CUSTOMER (USERNAME, EMAIL, PASSWORD, ROLE_ID, ENABLED) VALUES ('B', 'B@gmail.com', 'Bpass', 2, TRUE);

DROP TABLE IF EXISTS ADDRESS CASCADE;
CREATE TABLE ADDRESS (
	ID SERIAL PRIMARY KEY,
	STREET TEXT,
	CITY TEXT
);

INSERT INTO ADDRESS (STREET, CITY) VALUES ('111/8 street', 'Sai Gon');
INSERT INTO ADDRESS (STREET, CITY) VALUES ('123/4 Phan Huy Ich', 'Da Nang');

DROP TABLE IF EXISTS CUSTOMER_DETAIL CASCADE;
CREATE TABLE CUSTOMER_DETAIL (
	CUSTOMER_ID INT PRIMARY KEY,
	ADDRESS_ID INT REFERENCES ADDRESS(ID)
);

INSERT INTO CUSTOMER_DETAIL VALUES (2, 1);
INSERT INTO CUSTOMER_DETAIL VALUES (3, 2);



DROP TABLE IF EXISTS USER_ROLE CASCADE;
CREATE TABLE USER_ROLE (
	ID SERIAL PRIMARY KEY,
	ROLE_NAME TEXT NOT NULL UNIQUE
);

INSERT INTO USER_ROLE (ROLE_NAME) VALUES ('role_admin');
INSERT INTO USER_ROLE (ROLE_NAME) VALUES ('role_user');



DROP TABLE IF EXISTS PURCHASE CASCADE;
CREATE TABLE PURCHASE (
	ID SERIAL PRIMARY KEY,
	CUSTOMER_ID INT REFERENCES CUSTOMER(ID),
	STATUS_ID INT REFERENCES PURCHASE_STATUS(ID)
);

INSERT INTO PURCHASE (CUSTOMER_ID, STATUS_ID) VALUES (2, 1);
INSERT INTO PURCHASE (CUSTOMER_ID, STATUS_ID) VALUES (3, 2);


DROP TABLE IF EXISTS PURCHASE_STATUS CASCADE;
CREATE TABLE PURCHASE_STATUS (
	ID SERIAL PRIMARY KEY,
	STATUS_NAME TEXT NOT NULL UNIQUE
);

INSERT INTO PURCHASE_STATUS (STATUS_NAME) VALUES ('ORDERING');
INSERT INTO PURCHASE_STATUS (STATUS_NAME) VALUES ('CLOSED');
	
DROP TABLE IF EXISTS PURCHASE_ORDER CASCADE;
CREATE TABLE PURCHASE_ORDER (
	PURCHASE_ID INT PRIMARY KEY,
	SHIPPING_ADDRESS_COUNTRY TEXT,
	SHIPPING_ADDRESS_STREET TEXT,
	SHIPPING_ADDRESS_REGION TEXT,
	SHIPPING_ADDRESS_CITY TEXT,
	TOTAL NUMERIC,
	ORDER_STATUS TEXT NOT NULL,
	ORDER_DATE DATE NOT NULL
);

INSERT INTO PURCHASE_ORDER (PURCHASE_ID, SHIPPING_ADDRESS_COUNTRY, SHIPPING_ADDRESS_STREET, SHIPPING_ADDRESS_REGION, SHIPPING_ADDRESS_CITY ,TOTAL, ORDER_STATUS, ORDER_DATE)
		VALUES (2, 'Vietnam', 'Phan Huy Ich', 'Tphcm', 'Go Vap', 12.5, 'NEW', '2015-06-25');


DROP TABLE IF EXISTS OPERATING_SYSTEM CASCADE;
CREATE TABLE OPERATING_SYSTEM (
	ID SERIAL PRIMARY KEY,
	NAME TEXT UNIQUE NOT NULL
);

INSERT INTO OPERATING_SYSTEM (NAME) VALUES ('android');
INSERT INTO OPERATING_SYSTEM (NAME) VALUES ('ios');
INSERT INTO OPERATING_SYSTEM (NAME) VALUES ('window phone');

DROP TABLE IF EXISTS BRAND CASCADE;
CREATE TABLE BRAND (
	ID SERIAL PRIMARY KEY,
	NAME TEXT UNIQUE NOT NULL
);

INSERT INTO BRAND (NAME) VALUES ('Apple');
INSERT INTO BRAND (NAME) VALUES ('LG');
INSERT INTO BRAND (NAME) VALUES ('Sony');
INSERT INTO BRAND (NAME) VALUES ('Nexus');
INSERT INTO BRAND (NAME) VALUES ('Samsung');

DROP TABLE IF EXISTS PRODUCT CASCADE;
CREATE TABLE PRODUCT (
	ID SERIAL PRIMARY KEY,
	NAME TEXT,
	PRICE NUMERIC CHECK (PRICE > 0),
	OPERATING_SYSTEM_ID INT REFERENCES OPERATING_SYSTEM(ID),
	BRAND_ID INT REFERENCES BRAND(ID),
	COLOR TEXT,
	IMAGE_PATH TEXT
);

INSERT INTO PRODUCT (NAME, PRICE, OPERATING_SYSTEM_ID, BRAND_ID, COLOR, IMAGE_PATH) 
		VALUES ('Samsung - Galaxy S6 4G with 32GB ', 449, 1, 5, 'black', 'http://pisces.bbystatic.com/image2/BestBuy_US/images/products/4667/4667100_sa.jpg;canvasHeight=250;canvasWidth=200');
INSERT INTO PRODUCT (NAME, PRICE, OPERATING_SYSTEM_ID, BRAND_ID, COLOR, IMAGE_PATH) 
		VALUES ('Samsung - Galaxy S6 with 32GB', 149, 1, 5, 'black', 'http://pisces.bbystatic.com/image2/BestBuy_US/images/products/4372/4372036_sa.jpg;canvasHeight=250;canvasWidth=200');
INSERT INTO PRODUCT (NAME, PRICE, OPERATING_SYSTEM_ID, BRAND_ID, COLOR, IMAGE_PATH) 
		VALUES ('Samsung - Galaxy S6 4G LTE', 199, 1, 5, 'white', 'http://pisces.bbystatic.com/image2/BestBuy_US/images/products/4412/4412012_sa.jpg;canvasHeight=250;canvasWidth=200');

INSERT INTO PRODUCT (NAME, PRICE, OPERATING_SYSTEM_ID, BRAND_ID, COLOR, IMAGE_PATH) 
		VALUES ('Apple - iPhone 6 Plus 16GB', 199, 2, 1, 'black', 'http://pisces.bbystatic.com/image2/BestBuy_US/images/products/7642/7642051_sa.jpg;canvasHeight=250;canvasWidth=200');
INSERT INTO PRODUCT (NAME, PRICE, OPERATING_SYSTEM_ID, BRAND_ID, COLOR, IMAGE_PATH) 
		VALUES ('Apple - iPhone 6s 128GB', 399, 2, 1, 'white', 'http://pisces.bbystatic.com/image2/BestBuy_US/images/products/7640/7640099_sa.jpg;canvasHeight=250;canvasWidth=200');
INSERT INTO PRODUCT (NAME, PRICE, OPERATING_SYSTEM_ID, BRAND_ID, COLOR, IMAGE_PATH) 
		VALUES ('Apple - iPhone 6 Plus 16GB - Gold ', 21.67, 2, 1, 'white', 'http://pisces.bbystatic.com/image2/BestBuy_US/images/products/7640/7640099_sa.jpg;canvasHeight=250;canvasWidth=200');

INSERT INTO PRODUCT (NAME, PRICE, OPERATING_SYSTEM_ID, BRAND_ID, COLOR, IMAGE_PATH) 
		VALUES ('LG - G4 Cell Phone - Deep Blue', 198.99, 1, 2, 'black', 'http://pisces.bbystatic.com/image2/BestBuy_US/images/products/7873/7873212_sa.jpg;canvasHeight=250;canvasWidth=200');
INSERT INTO PRODUCT (NAME, PRICE, OPERATING_SYSTEM_ID, BRAND_ID, COLOR, IMAGE_PATH) 
		VALUES ('LG - G4 4G with 32GB', 198.99, 1, 2, 'black', 'http://pisces.bbystatic.com/image2/BestBuy_US/images/products/7873/7873212_sa.jpg;canvasHeight=250;canvasWidth=200');
INSERT INTO PRODUCT (NAME, PRICE, OPERATING_SYSTEM_ID, BRAND_ID, COLOR, IMAGE_PATH) 
		VALUES ('LG - V10 4G with 64GB', 149, 1, 2, 'black', 'http://pisces.bbystatic.com/image2/BestBuy_US/images/products/4634/4634500_sa.jpg;canvasHeight=250;canvasWidth=200');


DROP TABLE IF EXISTS PURCHASE_PRODUCT;
CREATE TABLE PURCHASE_PRODUCT (
	PURCHASE_ID INT REFERENCES PURCHASE(ID),
	PRODUCT_ID INT REFERENCES PRODUCT(ID),
	QUANTITY INT,

	PRIMARY KEY(PURCHASE_ID, PRODUCT_ID)
);

INSERT INTO PURCHASE_PRODUCT (PURCHASE_ID, PRODUCT_ID, QUANTITY)
VALUES (1, 1, 1);
INSERT INTO PURCHASE_PRODUCT (PURCHASE_ID, PRODUCT_ID, QUANTITY)
VALUES (2, 2, 3);

DROP TABLE IF EXISTS PRODUCT_IMAGE;
CREATE TABLE PRODUCT_IMAGE (
	ID SERIAL PRIMARY KEY,
	FILE_PATH TEXT
);

DROP TABLE IF EXISTS VERIFICATION_TOKEN;
CREATE TABLE VERIFICATION_TOKEN (
	ID SERIAL PRIMARY KEY,
	TOKEN TEXT UNIQUE NOT NULL,
	EXPIRY_DATE DATE,
	CUSTOMER_ID INT REFERENCES CUSTOMER(ID)
);

DROP TABLE IF EXISTS RESET_PASSWORD_TOKEN;
CREATE TABLE RESET_PASSWORD_TOKEN (
	ID SERIAL PRIMARY KEY,
	TOKEN TEXT UNIQUE NOT NULL,
	EXPIRY_DATE DATE,
	CUSTOMER_ID INT REFERENCES CUSTOMER(ID)
);

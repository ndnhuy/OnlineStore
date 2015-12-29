﻿DROP TABLE IF EXISTS CUSTOMER CASCADE;
CREATE TABLE CUSTOMER (
	ID SERIAL PRIMARY KEY,
	USERNAME TEXT NOT NULL UNIQUE,
	PASSWORD TEXT NOT NULL,
	EMAIL TEXT NOT NULL UNIQUE,
	ROLE_ID INT REFERENCES USER_ROLE(ID)
);

INSERT INTO CUSTOMER (USERNAME, EMAIL, PASSWORD, ROLE_ID) VALUES ('admin', 'admin@gmail.com', 'admin', 1);
INSERT INTO CUSTOMER (USERNAME, EMAIL, PASSWORD, ROLE_ID) VALUES ('A', 'A@gmail.com', 'Apass', 2);
INSERT INTO CUSTOMER (USERNAME, EMAIL, PASSWORD, ROLE_ID) VALUES ('B', 'B@gmail.com', 'Bpass', 2);

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

INSERT INTO PURCHASE (CUSTOMER_ID, STATUS_ID) VALUES (1, 1);
INSERT INTO PURCHASE (CUSTOMER_ID, STATUS_ID) VALUES (2, 1);


DROP TABLE IF EXISTS PURCHASE_STATUS CASCADE;
CREATE TABLE PURCHASE_STATUS (
	ID SERIAL PRIMARY KEY,
	STATUS_NAME TEXT NOT NULL UNIQUE
);

INSERT INTO PURCHASE_STATUS (STATUS_NAME) VALUES ('ORDERING');


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

INSERT INTO BRAND (NAME) VALUES ('Iphone');
INSERT INTO BRAND (NAME) VALUES ('LG');
INSERT INTO BRAND (NAME) VALUES ('Sony');
INSERT INTO BRAND (NAME) VALUES ('Nexus');

DROP TABLE IF EXISTS PRODUCT CASCADE;
CREATE TABLE PRODUCT (
	ID SERIAL PRIMARY KEY,
	NAME TEXT,
	PRICE NUMERIC CHECK (PRICE > 0),
	OPERATING_SYSTEM_ID INT REFERENCES OPERATING_SYSTEM(ID),
	BRAND_ID INT REFERENCES BRAND(ID),
	COLOR TEXT
);

INSERT INTO PRODUCT (NAME, PRICE, OPERATING_SYSTEM_ID, BRAND_ID, COLOR) VALUES ('Iphone 5s', 5.5, 2, 1, 'black');
INSERT INTO PRODUCT (NAME, PRICE, OPERATING_SYSTEM_ID, BRAND_ID, COLOR) VALUES ('Nexus 7', 15.5, 1, 4, 'white');


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
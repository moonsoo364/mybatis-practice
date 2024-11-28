DROP DATABASE STOCK_INFOMATION;
CREATE DATABASE STOCK_INFOMATION;

USE STOCK_INFOMATION;

SHOW TABLES;

CREATE TABLE MEMBER (
	MEMBER_ID VARCHAR(100),
	MEMBER_PASSWORD VARCHAR(100) NOT NULL,
	MEMBER_NAME VARCHAR(30) NOT NULL,
	CREATED_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT PK_MEMBER PRIMARY KEY (MEMBER_ID)
);

CREATE TABLE STOCK(
	STOCK_ID VARCHAR(30),
	STOCK_NAME VARCHAR(100) NOT NULL,
	STOCK_EXCHANGE VARCHAR(10) NOT NULL,
	CURRENT_PRICE DECIMAL(10,2) NOT NULL,
	CREATED_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT PK_STOCK PRIMARY KEY (STOCK_ID)
);

DESC STOCK;

CREATE TABLE MEMBER_TRANSACTION(
	TRANSACTION_ID INT AUTO_INCREMENT,
	MEMBER_ID VARCHAR(100) NOT NULL,
	STOCK_ID VARCHAR(30) NOT NULL,
	STOCK_QUANTITY INT NOT NULL,
	STOCK_PRICE DECIMAL(10,2) NOT NULL,
	TRANSACTION_TYPE ENUM('BUY','SELL') NOT NULL DEFAULT 'BUY',
	CURRENCY_CODE VARCHAR(3) NOT NULL DEFAULT 'USD',
	TRANSACTION_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT PK_MEMBER_TRANSACTION PRIMARY KEY(TRANSACTION_ID)
);


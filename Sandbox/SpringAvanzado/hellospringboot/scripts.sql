CREATE DATABASE db_springdemo;

USE db_springdemo;

CREATE TABLE Customer (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(30) NOT NULL,
  last_name varchar(30) NOT NULL,
  phone varchar(15) NOT NULL,
  email varchar(20) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO Customer (name,last_name,phone,email)
VALUES
('Elon','Musk','555-55555','elon@tesla.com'),
('Bill','Gates','555-5556','bill@microsoft.com'),
('Diego','Maradona','555-5557','eld10@barcelona');
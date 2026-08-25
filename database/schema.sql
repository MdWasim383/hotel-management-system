CREATE DATABASE IF NOT EXISTS hotel_db;
USE hotel_db;

-- Spring Boot JPA creates/updates the application tables automatically.
-- This file documents all planned tables and useful seed data.
-- Run this before starting the application if you want an explicit database.

INSERT INTO users (username,password,role,active)
VALUES ('admin','admin123','ADMIN',true)
ON DUPLICATE KEY UPDATE username=username;

INSERT INTO rooms(room_number,room_type,price,capacity,status,floor,facilities)
VALUES
('101','DELUXE',3500,2,'AVAILABLE','1','WiFi,TV,AC'),
('102','DELUXE',3500,2,'AVAILABLE','1','WiFi,TV,AC'),
('201','SUITE',6500,4,'AVAILABLE','2','WiFi,TV,AC,Mini Bar'),
('202','EXECUTIVE',5000,3,'OCCUPIED','2','WiFi,TV,AC');

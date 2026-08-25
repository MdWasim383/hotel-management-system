# Hotel Management System

## Technology
- Java 17
- Spring Boot 3.5
- Spring Web
- Spring Data JPA / Hibernate
- MySQL
- HTML5
- CSS3
- JavaScript

## Modules
Dashboard, Rooms, Guests, Reservations, Check-In, Check-Out, Food Orders, Hotel Services, Housekeeping, Payments, Employees, Feedback, Maintenance, Notifications and Chatbot.

## Project structure

hotel-management-system/
├── pom.xml
├── database/
│   └── schema.sql
└── src/main/
    ├── java/com/hotel/
    │   ├── HotelManagementApplication.java
    │   ├── controller/
    │   ├── dto/
    │   ├── entity/
    │   ├── repository/
    │   └── service/
    └── resources/
        ├── application.properties
        └── static/
            ├── index.html
            ├── css/style.css
            └── js/app.js

## Setup

1. Install JDK 17, Maven and MySQL.
2. Create the database:
   CREATE DATABASE hotel_db;
3. Open application.properties and change:
   spring.datasource.username=root
   spring.datasource.password=root
4. Run:
   mvn clean spring-boot:run
5. Open:
   http://localhost:8081/
6. API dashboard:
   http://localhost:8081/api/dashboard

JPA creates/updates tables automatically.

## Important
This is a complete learning/project foundation with CRUD APIs for the main hotel modules and a rule-based chatbot. For production, add Spring Security/JWT, password hashing, role-based authorization, DTO validation, audit logging, database migrations, payment gateway integration and a real AI/chat service.

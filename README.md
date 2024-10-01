# Library Project

This is a demo project for job interview built using Spring Boot. The project demonstrates a library management system with features such as security, data validation, and API documentation. The application is structured with Maven as the build tool and includes various Spring Boot starters for different functionalities.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Dependencies](#dependencies)

## Introduction
This project is designed to be a Spring Boot-based library management system. It leverages multiple dependencies like Spring Data JPA, Spring Security, and validation tools, along with Oracle database support via Testcontainers for testing purposes.

## Features
- REST API for library management
- JWT authentication and security using Spring Security
- Database management with Spring Data JPA
- Data validation using Spring Boot validation tools
- Integration with Oracle database
- OpenAPI documentation with Springdoc
- Migrations with flywaydb

## Installation

### Prerequisites
- Java 17 or higher
- Maven 3.8.1 or higher
- Docker (for running the Oracle database in containers)

### Steps
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd library

2. Build the project using Maven skipping the tests:
   ```bash 
   ./mvnw clean package -DskipTests
   
3. To run with Docker, use the following command:
   ```bash
   docker-compose up
   
4. Build the project using Maven:
   ```bash
   ./mvnw spring-boot:run
   
5. If the User table in the database is empty, please run the Flyway migration manually to populate it:
   ```bash
   ./mvnw flyway:clean && ./mvnw flyway:migrate


## Usage
After the application starts, you can access the API at http://localhost:8080. 
Use a tool like Postman or Swagger UI (at http://localhost:8080/swagger-ui.html ) to interact with the API.

API Endpoints:
To access the secured endpoints, JWT authentication is required. You can obtain a token via the authentication endpoint.

OpenAPI Documentation
OpenAPI documentation is available at http://localhost:8080/swagger-ui.html.

#### In the DB should already be an Admin user waiting for you so you can login and take a token 
(at login API http://localhost:8080/swagger-ui/index.html?continue#/authentication-controller/login):

###### email: guest@example.com 

###### password: @Test@

## Dependencies
The project uses the following dependencies:

1. Spring Boot Starters  
 - spring-boot-starter
 - spring-boot-starter-data-jpa
 - spring-boot-starter-security
 - spring-boot-starter-validation
 - spring-boot-starter-web

2. Oracle Database
 - ojdbc11 (runtime)

3. JWT

 - jjwt-impl
 - jjwt-jackson

4. Validation

 - jakarta.validation-api

5. MapStruct (for DTO mapping)

- mapstruct
- mapstruct-processor

6. Testing Dependencies

- spring-boot-starter-test
- spring-boot-testcontainers
- testcontainers (Oracle DB)

7. API Documentation

- springdoc-openapi-starter-webmvc-ui

8. Migrations

- flywaydb


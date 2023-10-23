# Healthcare Appointments API

A Spring Boot REST CRUD API for managing healthcare appointments with Spring Data JPA and MySQL.


## Description

The Healthcare Appointments API is a Spring Boot application designed for managing healthcare appointments. It provides a RESTful interface for creating, updating, and retrieving appointments, doctors, and patients. The data is stored in a MySQL database using Spring Data JPA.

## Prerequisites

Before you begin, ensure you have the following requirements:

- Java 17
- MySQL database
- Maven

## Getting Started

To get a copy of this project up and running on your local machine, follow these steps:

1. Clone this repository:
   ```bash
   git clone https://github.com/sh4on/healthcare-appointments-api.git

Configure your MySQL database by editing the application.properties file located in src/main/resources:

Properties:

    spring.datasource.url=jdbc:mysql://localhost:3306/db_practice
    spring.datasource.username=root
    spring.datasource.password=root


Project Structure

The project has the following structure:

    src/main/java/com/example/healthcare/appointments/api contains the main application classes.
    src/main/java/com/example/healthcare/appointments/api/controller contains the REST controllers.
    src/main/java/com/example/healthcare/appointments/api/model contains the data model classes.
    src/main/java/com/example/healthcare/appointments/api/repository contains the JPA repositories.
    src/main/java/com/example/healthcare/appointments/api/service contains the service classes.
    src/main/java/com/example/healthcare/appointments/api/exception contains the exception classes.
    src/main/resources contains application configuration and properties files.

API Endpoints

The following API endpoints are available:

    /api/appointments for managing appointments.
    /api/doctors for managing doctors.
    /api/patients for managing patients.

You can find more information on how to use these endpoints in the respective controller classes.
# Employee Management System

This is a Java-based Employee Management System designed to manage employee records efficiently. The system allows for the addition, viewing, updating, and deletion of employee details and includes features for managing departments and roles within an organization.

## Features

- Add, update, view, and delete employees
- Manage departments and roles
- Uses MySQL for database management
- Follows a layered architecture with DAO, Service, and Model classes
- Console-based interface

## Prerequisites

Before running this project, make sure you have the following installed on your system:

- **Java Development Kit (JDK)**: Version 8 or above
- **Maven**: For dependency management and build
- **MySQL**: For database management
- **IDE**: (Optional) Recommended to use Eclipse or IntelliJ IDEA

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/employee-management-system.git
cd employee-management-system

```
### Database setup

#### Install and configure MySQL.
#### Create a new database named EmployeeDB and run the following SQL script to create the required tables:

### CREATE DATABASE EmployeeDB;

USE EmployeeDB;

CREATE TABLE Department (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    salary DOUBLE NOT NULL,
    department_id INT,
    role_id INT,
    FOREIGN KEY (department_id) REFERENCES Department(id),
    FOREIGN KEY (role_id) REFERENCES Role(id)
);


 3. Configure the Database Connection
#### In the DBConnection.java file located in src/main/java/employee/utils/DBConnection.java, update the following constants with your MySQL credentials:


### 4. Build the Project
#### Navigate to the project directory and build the project using Maven:

```
mvn clean install
```
This will download all the dependencies, compile the project, and package it into a JAR file.


### 5. Run the Project
#### You can run the project directly from your IDE (Eclipse or IntelliJ IDEA) by running the main() method in EmployeeManagementSystem.java.


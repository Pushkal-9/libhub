
# LibHub - Library Management System

## Overview
LibHub is a **Spring Boot** application designed to manage library operations such as book inventory, user transactions, and role-based access control. It provides a RESTful API for handling library-related functionalities, including user authentication, book management, and transaction tracking.

## Features
- **User Authentication**: Secure login and registration using Spring Security.
- **Role-based Access Control**: Users can have different roles (e.g., Admin, User) with varying permissions.
- **Book Management**: Add, update, and manage the library's book inventory.
- **Transaction Management**: Borrow and return books, with tracking of transactions.
- **RESTful API**: All functionalities are accessible through an API for easy integration with frontends or other systems.

## Prerequisites
- **Java 17** or higher
- **Maven 3.6.0** or higher

## Setup Instructions
1. Clone the repository.
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory.
   ```bash
   cd libhub-main
   ```
3. Build the project using Maven.
   ```bash
   mvn clean install
   ```
4. Run the application.
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints
This application provides RESTful endpoints for managing books, items, transactions, and user authentication. Documentation for the API endpoints is available in the source code comments.

## Technologies Used
- **Spring Boot 3.2.2**: Core framework for building the application.
- **Spring Data JPA**: For database interactions.
- **Spring Security**: For user authentication and role-based access control.
- **H2 Database**: An in-memory database used for development and testing purposes.

## License
This project is licensed under the MIT License.

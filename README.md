# Banking Application

A RESTful banking application built using Spring Boot that allows users to create bank accounts, retrieve account information, and perform common banking operations such as deposits, withdrawals, and money transfers.

## Features

- Create new bank accounts
- Retrieve account details by ID
- Deposit funds into an account
- Withdraw funds from an account
- Transfer money between accounts
- RESTful API architecture
- MySQL database integration
- Data persistence using Spring Data JPA

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- Maven
- MySQL
- MySQL Workbench
- Postman
- IntelliJ IDEA

## Project Structure

```
src
├── controller
├── dto
├── entity
├── mapper
├── repository
├── service
│   └── impl
└── resources
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/accounts` | Create a new account |
| GET | `/api/accounts/{id}` | Get account by ID |
| PUT | `/api/accounts/{id}/deposit` | Deposit money |
| PUT | `/api/accounts/{id}/withdraw` | Withdraw money |
| PUT | `/api/accounts/transfer` | Transfer funds between accounts |

## Running the Project

1. Clone the repository

```bash
git clone https://github.com/Paavan-Ghuman/banking-app.git
```

2. Create a MySQL database

```sql
CREATE DATABASE banking_app;
```

3. Update `application.properties` with your MySQL username and password.

4. Run the Spring Boot application.

5. Test the API using Postman.

## Future Improvements

- JWT Authentication
- Transaction History
- Account Statements
- Input Validation
- Unit Testing with JUnit
- API Documentation using Swagger
- Docker Support

## Author

**Paavan Ghuman**

GitHub: https://github.com/Paavan-Ghuman

# Simplified Payment API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

This project is an API built using **Java, Java Spring, H2 as the database.**
Unit tests for Java Spring apps using **JUnit, Mockito and AssertJ.**


## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database](#database)


## Installation

1. Clone the repository:

```bash
git clone https://github.com/mv-vieira/desafio-picpay-backend
```

2. Install dependencies with Maven

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080


## API Endpoints
The API provides the following endpoints:

**GET USERS**
```markdown
GET /users - Retrieve a list of all users.
```
```json
[
    {
        "id": 1,
        "firstName": "João",
        "lastName": "Pedro",
        "document": "123456789",
        "email": "joao@example.com",
        "password": "senha",
        "balance": 50.00,
        "userType": "MERCHANT"
    },
    {
        "id": 2,
        "firstName": "Maria",
        "lastName": "Júlia",
        "document": "987654321",
        "email": "maria@example.com",
        "password": "senha",
        "balance": 0.00,
        "userType": "COMMON"
    },
      {
        "id": 3,
        "firstName": "Matheus",
        "lastName": "Vicente",
        "document": "987654640",
        "email": "matheus@example.com",
        "password": "senha",
        "balance": 100.00,
        "userType": "COMMON"
    }
]
```

**POST USERS**
```markdown
POST /users - Register a new user into the App
```
```json
{
        "id": 1,
        "firstName": "João",
        "lastName": "Pedro",
        "document": "123456789",
        "email": "joao@example.com",
        "password": "senha",
        "balance": 50.00,
        "userType": "MERCHANT"
    }
```

**POST TRANSACTIONS**
```markdown
POST /transactions - Register a new Transaction between users (COMMON to COMMON or COMMON to MERCHANT)
```

```json

{
  "senderId": 3,
  "receiverId": 2,
  "value": 50
}
```

## Database
The project utilizes [H2 Database](https://www.h2database.com/html/tutorial.html) as the database. 






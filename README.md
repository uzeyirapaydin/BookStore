# CaseStudy - ReadingIsGood
ReadingIsGood is an online books retail firm which operates only on the Internet.
Main target of ReadingIsGood is to deliver books from its one centralized warehouse
to their customers within the same day.

## Technology Stack

- Java 17
- Spring Boot
- MongoDB
- Maven
- Docker

## Installation

To start project the following commands needs to be run on project path.
```shell
mvn clean install or  mvn clean install -DskipTests

docker-compose up -d
```

All done!

The backend service runs on http://localhost:8088.

After deployment, to show and test API endpoints the followings can be used:

- **Swagger Documentation:** [Open API Endpoint](http://localhost:8088/swagger-ui/index.html)
- **Postman Collection:** [Download](https://raw.githubusercontent.com/uzeyirapaydin/BookStore/main/CaseStudy.postman_collection.json)

## Usage

### Login

There is an endpoint(`POST /customer/login`) to login and be authorized customer.

#### Customer's Registration:
There is an endpoint(`POST /customer/signup`) to register new customer.

### Authentication & Authorization

> **Authentication:** Bearer <your_token>

Any tokens expire **720 mins** after creation.


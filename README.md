# Library API – Spring Boot

A simple backend REST API for managing books and borrowers.

---

## Tech Stack
- Java 17
- Spring Boot
- PostgreSQL 16
- Docker & Docker Compose
- Spring Data JPA
- Swagger / OpenAPI

---

## Project Structure
- `application.yml` → base config
- `application-dev.yml` → H2 (local development)
- `application-prod.yml` → PostgreSQL (Docker / production)
- `docker-compose.yml` → App + Database setup

---

## Prerequisites
Make sure you have:
- Docker
- Docker Compose

---

## Environment Variables
Create a `.env` file in the project root:

```env
DB_HOST=postgres
DB_PORT=5432
DB_NAME=librarydb
DB_USERNAME=libraryuser
DB_PASSWORD=librarypass
```

## How to Run (Docker)
Run the following command:
```bash
docker-compose up --build
```

## Application Access
API Base URL: http://localhost:8080
Swagger UI: http://localhost:8080/swagger-ui.html

## Spring Profiles
dev → H2 in-memory database<br>
prod → PostgreSQL (Docker)<br>
Active profile is set using:<br>
```bash
SPRING_PROFILES_ACTIVE=prod
```

## Sample API Endpoints
### Books
- GET `/books`
- POST `/books`

### Borrowers
- GET `/borrowers`
- POST `/borrowers`

## Why PostgreSQL?

PostgreSQL is chosen as the database for this project because it is:<br>
- Open-source and production-ready – widely used in real-world enterprise systems<br>
- Strong ACID compliance – ensures data integrity and reliability<br>
- Excellent support for relational data – suitable for structured entities like books and borrowers<br>
- Advanced features – such as constraints, indexing, and JSON support<br>
- Docker-friendly – easy to run consistently across development and production environments<br>
- PostgreSQL provides a good balance between performance, reliability, and scalability, making it a solid choice for backend REST APIs.<br>

## Notes
Database data is persisted using Docker volume
PostgreSQL healthcheck ensures the app starts only after DB is ready


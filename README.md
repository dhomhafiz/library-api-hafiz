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

## How to Run (Docker)
Run the following command:
docker-compose up --build

## Application Access
API Base URL: http://localhost:8080
Swagger UI: http://localhost:8080/swagger-ui.html

## Spring Profiles
dev → H2 in-memory database
prod → PostgreSQL (Docker)
Active profile is set using:
SPRING_PROFILES_ACTIVE=prod

## Sample API Endpoints
Books
GET /books
POST /books

Borrowers
GET /borrowers
POST /borrowers

## Notes
Database data is persisted using Docker volume
PostgreSQL healthcheck ensures the app starts only after DB is ready


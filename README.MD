# Book Collection Manager

A simple full-stack web application to manage a personal book collection.  
This project is built as part of a **Senior Full-Stack Developer Take-Home Assignment**.

The focus of this implementation is on clean code, correctness, and practical DevOps practices without over-engineering.

---

## Tech Stack

### Backend
- **Java 21**
- **Spring Boot 3**
- Spring Web (REST API)
- Spring Data JPA
- Jakarta Validation
- **H2 In-Memory Database**
- Maven

### Frontend
- **Angular (Standalone Components)**
- Reactive Forms
- Angular Router
- HttpClient

### DevOps
- Docker (backend containerization)
- GitHub Actions (CI/CD pipeline concept)

---

## Features

### Backend
- CRUD operations for books
- Search books by **title** or **author**
- Input validation for required fields
- Duplicate ISBN prevention
- In-memory database for simplicity
- Unit test for core business rules

### Frontend
- Book List View with search
- Book Form View for add/edit (single reusable component)
- Reactive Forms with validation
- Intuitive navigation between views

---

## Project Structure

```
book-collection/
├── .github/
│   └── workflows/
│       └── ci.yml
├── book-collection-backend/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/example/books
│       │   └── resources/application.yml
│       └── test/
│           └── java/com/example/books/service
├── book-collection-frontend/
│   └── src/app/...
└── README.md
```

---

## How to Run Locally

### Backend

```bash
cd book-collection-backend
mvn spring-boot:run
```

Backend will run at:
```
http://localhost:8080
```

H2 Console:
```
http://localhost:8080/h2-console
```

---

### Frontend

```bash
cd book-collection-frontend
ng serve
```

Frontend will run at:
```
http://localhost:4200
```

---

## 🔗 API Endpoints

| Method | Endpoint | Description |
|------|---------|-------------|
| GET | `/api/books` | List all books (optional `?search=`) |
| GET | `/api/books/{id}` | Get book by ID |
| POST | `/api/books` | Create a new book |
| PUT | `/api/books/{id}` | Update an existing book |
| DELETE | `/api/books/{id}` | Delete a book |

---

## Docker (Backend)

### Build Docker Image

```bash
cd book-collection-backend
mvn clean package
docker build -t book-collection-api .
```

### Run Container

```bash
docker run -p 8080:8080 book-collection-api
```

---

## CI/CD Pipeline (Concept)

A simple CI/CD pipeline concept is provided using **GitHub Actions**.

**Location**
```
.github/workflows/ci.yml
```

### Pipeline Steps
1. Checkout source code
2. Set up Java 21
3. Run unit tests
4. Build application JAR
5. Build Docker image

---

## Architecture Overview

```
User
 |
Browser (Angular SPA)
 |
Spring Boot REST API (Docker Container)
 |
Database
(H2 for demo, replaceable with PostgreSQL in production)
```

---

## Testing

```bash
cd book-collection-backend
mvn test
```

---

## Submission Checklist

- [x] Public Git repository
- [x] Dockerfile included
- [x] CI/CD pipeline concept included
- [x] Architecture description provided
- [x] README documentation completed

---


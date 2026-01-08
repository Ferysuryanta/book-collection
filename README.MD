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

## Assumptions and Design Decisions

- A monolithic architecture was chosen to keep the solution simple and easy to understand.
- An H2 in-memory database is used to avoid additional setup complexity.
- Authentication and authorization are not implemented, as they were not part of the requirements.
- Angular standalone components are used to reduce boilerplate and keep the frontend modular.
- UI styling is intentionally minimal to focus on functionality.

---

## Trade-offs and Future Improvements

Trade-offs:
- Data is not persisted between restarts due to the in-memory database.
- Pagination and sorting are not implemented.
- UI design is basic and not based on a design system.

Future Improvements:
- Replace H2 with PostgreSQL or MySQL.
- Add pagination and sorting.
- Add authentication and authorization.
- Improve UI using a component library.
- Deploy to a cloud environment (AWS or Azure).

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

## AI Tools Usage Disclosure

AI tools were used for syntax assistance, debugging support, and initial scaffolding ideas.
All architectural decisions, trade-offs, and final implementations were reviewed and completed manually by the author.

---
## Commit History

The repository follows an incremental commit history demonstrating development progress:
- Project initialization
- Backend CRUD implementation
- Unit testing
- Frontend views and forms
- UI responsiveness improvements
- DevOps configuration
- Documentation
---


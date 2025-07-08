# User Shield API – Spring Boot Secure REST API

This project is a secure RESTful API built with Java 17 and Spring Boot. It provides user authentication using JWT, profile management, and proper security handling for protected endpoints.

## Features

- User registration and login endpoints
- Secure JWT-based authentication
- Authenticated user profile update using `/me` endpoint
- Password encryption using BCrypt
- Centralized error handling for missing or invalid tokens (401) and forbidden access (403)

## Technologies Used

- Java 17
- Spring Boot
- Spring Security
- Spring Web
- Spring Data JPA
- H2 / MySQL (depending on config)
- Maven

## Endpoints Overview

| Method | Endpoint              | Description                       | Authentication Required |
|--------|-----------------------|-----------------------------------|--------------------------|
| POST   | /api/auth/register    | Register a new user               | No                       |
| POST   | /api/auth/login       | Authenticate user and get token   | No                       |
| GET    | /me                   | het logged-in user’s profile data | Yes (JWT)                |
| PUT    | /me                   | Update logged-in user’s profile   | Yes (JWT)                |
## Security Handling

- All endpoints except `/api/auth/**` require a valid JWT token in the `Authorization` header.
- If the token is missing or invalid, the API returns:
    - HTTP 401 with a message: `Token is required or invalid`
- If access is forbidden (user is authenticated but not authorized), the API returns:
    - HTTP 403 with a message: `You do not have permission to access this resource`

## File Structure Overview
src/

└── main/

├── java/com/shifal/userapi/

│ ├── config/ # Spring Security configuration

│ ├── controller/ # API endpoints (e.g., AuthController)

│ ├── dto/ # Request and response DTOs

│ ├── exception/ # Custom exception handlers

│ ├── model/ # Entity classes (e.g., User)

│ ├── repository/ # Spring Data JPA repositories

│ ├── security/ # JWT filters and utility

│ └── service/ # Business logic

└── resources/

└── application.yml # Configuration file

## How to Run

1. Clone the repository
2. Configure database and secret key in `application.yml`
3. Build and run the project:

```bash
mvn spring-boot:run


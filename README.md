# 67-Bank API

A secure, production-grade digital banking REST API built with **Java 21** and **Spring Boot 4**. The system provides end-to-end banking operations including user authentication, multi-account management, financial transactions (deposits, withdrawals, transfers), role-based access control, an auditor dashboard, and asynchronous email notifications.

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Features](#features)
- [API Reference](#api-reference)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Environment Variables](#environment-variables)
- [Roadmap](#roadmap)
- [Acknowledgments](#acknowledgments)

---

## Tech Stack

| Layer              | Technology                                          |
| ------------------ | --------------------------------------------------- |
| **Language**       | Java 21                                             |
| **Framework**      | Spring Boot 4.0.1 (Web MVC, Data JPA, Security, Mail) |
| **Security**       | Spring Security 6 + JWT (JJWT 0.13.0)              |
| **Database**       | MySQL with Hibernate ORM                            |
| **Email**          | Spring Mail (SMTP) + Thymeleaf HTML templates       |
| **Mapping**        | ModelMapper 3.2.6                                   |
| **Cloud (planned)**| AWS S3 SDK 2.41.4 (dependency present, not yet active) |
| **Build**          | Maven Wrapper (Apache Maven 3.9.12)                 |

---

## Architecture

```
Client (REST)
    │
    ▼
┌──────────────────────────────────────────────────┐
│                Security Layer                     │
│  CORS Filter → JWT AuthFilter → SecurityChain     │
│  (stateless sessions, BCrypt password encoding)   │
└──────────────┬───────────────────────────────────┘
               ▼
┌──────────────────────────────────────────────────┐
│              Controller Layer                     │
│  AuthController · UserController                  │
│  AccountController · TransactionController        │
│  RoleController · AuditorController               │
└──────────────┬───────────────────────────────────┘
               ▼
┌──────────────────────────────────────────────────┐
│               Service Layer                       │
│  Business logic, validation, DTO mapping          │
│  Async email dispatch (NotificationService)       │
└──────────────┬───────────────────────────────────┘
               ▼
┌──────────────────────────────────────────────────┐
│            Repository Layer (Spring Data JPA)     │
│  UserRepo · AccountRepo · TransactionRepo         │
│  RoleRepo · NotificationRepo · PasswordResetRepo  │
└──────────────┬───────────────────────────────────┘
               ▼
           [ MySQL ]
```

All API responses use a unified `Response<T>` wrapper with `statusCode`, `message`, `data`, and optional `meta` fields.

---

## Features

### Authentication & Authorization
- JWT-based stateless authentication with configurable secret and expiration
- Role-Based Access Control (RBAC) with **ADMIN**, **CUSTOMER**, and **AUDITOR** roles
- Secure registration and login with BCrypt password hashing
- Forgot-password and reset-password flow using time-sensitive verification codes (5-hour expiry)
- Custom 401/403 JSON error responses via `AuthenticationEntryPoint` and `AccessDeniedHandler`

### User Management
- User profile retrieval and paginated user listing (admin only)
- Password update with old-password verification
- Profile picture upload (multipart file)

### Account Management
- Automatic Savings account creation on user registration (USD, unique 10-digit account number)
- View all accounts for the authenticated user
- Close an account (requires zero balance, sets status to `CLOSED`)

### Transaction Engine
- **Deposits** — credit any owned account
- **Withdrawals** — debit with insufficient-balance validation
- **Transfers** — debit source, credit destination, with email alerts to both parties
- Paginated transaction history sorted by date (includes incoming transfers)

### Auditor Dashboard
- System-wide totals: user count, account count, transaction count
- Look up any user by email or any account by account number
- Trace transactions by account number or transaction ID
- Restricted to ADMIN and AUDITOR roles

### Notification System
- Asynchronous email delivery via `@Async` and `JavaMailSender`
- Thymeleaf HTML templates: welcome, account created, password reset, password update confirmation, credit alert, debit alert
- All sent notifications are persisted in the database

### Error Handling
- Global `@ControllerAdvice` exception handler
- Custom exceptions: `NotFoundException` (404), `BadRequestException` (400), `InsufficientBalanceException` (400), `InvalidTransactionException` (400)
- Unhandled exceptions return 500 with a generic error message

---

## API Reference

> All endpoints (except `/api/auth/**`) require a valid JWT in the `Authorization: Bearer <token>` header.

### Authentication — `/api/auth`

| Method | Endpoint                    | Description                          | Access  |
| ------ | --------------------------- | ------------------------------------ | ------- |
| POST   | `/api/auth/register`        | Register a new user                  | Public  |
| POST   | `/api/auth/login`           | Authenticate and receive a JWT       | Public  |
| POST   | `/api/auth/forgot-password` | Request a password reset code        | Public  |
| POST   | `/api/auth/reset-password`  | Reset password using verification code | Public |

### Users — `/api/users`

| Method | Endpoint                       | Description                        | Access    |
| ------ | ------------------------------ | ---------------------------------- | --------- |
| GET    | `/api/users`                   | List all users (paginated)         | ADMIN     |
| GET    | `/api/users/me`                | Get authenticated user profile     | Any       |
| PUT    | `/api/users/update-password`   | Change password                    | Any       |
| PUT    | `/api/users/profile-picture`   | Upload profile picture (multipart) | Any       |

### Accounts — `/api/accounts`

| Method | Endpoint                              | Description                  | Access |
| ------ | ------------------------------------- | ---------------------------- | ------ |
| GET    | `/api/accounts/me`                    | List authenticated user's accounts | Any |
| DELETE | `/api/accounts/close/{accountNumber}` | Close an account             | Owner  |

### Transactions — `/api/transactions`

| Method | Endpoint                              | Description                              | Access |
| ------ | ------------------------------------- | ---------------------------------------- | ------ |
| POST   | `/api/transactions`                   | Create a transaction (deposit/withdraw/transfer) | Any |
| GET    | `/api/transactions/{accountNumber}`   | Get transaction history (paginated)      | Any    |

### Roles — `/api/roles`

| Method | Endpoint            | Description       | Access |
| ------ | ------------------- | ----------------- | ------ |
| POST   | `/api/roles`        | Create a role     | ADMIN  |
| PUT    | `/api/roles`        | Update a role     | ADMIN  |
| GET    | `/api/roles`        | List all roles    | ADMIN  |
| DELETE | `/api/roles/{id}`   | Delete a role     | ADMIN  |

### Audit — `/api/audit`

| Method | Endpoint                                      | Description                        | Access        |
| ------ | --------------------------------------------- | ---------------------------------- | ------------- |
| GET    | `/api/audit/totals`                           | System metrics (counts)            | ADMIN/AUDITOR |
| GET    | `/api/audit/users?email=`                     | Look up user by email              | ADMIN/AUDITOR |
| GET    | `/api/audit/accounts?accountNumber=`          | Look up account by number          | Authenticated |
| GET    | `/api/audit/transactions/by-account?accountNumber=` | Transactions for an account  | ADMIN/AUDITOR |
| GET    | `/api/audit/transactions/by-id?id=`           | Look up transaction by ID          | ADMIN/AUDITOR |

---

## Project Structure

```
67-bank/
├── pom.xml
├── mvnw / mvnw.cmd
├── .env                          # Local environment config (gitignored)
├── src/
│   ├── main/
│   │   ├── java/com/sixseven/sixsevenBank/
│   │   │   ├── SixsevenBankApplication.java      # Entry point (@EnableAsync)
│   │   │   ├── config/                            # AppConfig (Thymeleaf, ModelMapper)
│   │   │   ├── security/                          # JWT filter, SecurityFilterChain, CORS
│   │   │   ├── auth_users/                        # Auth + User controllers, services, DTOs, entities
│   │   │   ├── account/                           # Account controller, service, DTOs, entity
│   │   │   ├── transaction/                       # Transaction controller, service, DTOs, entity
│   │   │   ├── role/                              # Role CRUD controller, service, entity
│   │   │   ├── audit_dashboard/                   # Auditor controller and service
│   │   │   ├── notification/                      # Async email service, DTOs, entity
│   │   │   ├── enums/                             # AccountStatus, AccountType, Currency, etc.
│   │   │   ├── exceptions/                        # GlobalExceptionHandler + custom exceptions
│   │   │   └── res/                               # Unified Response<T> wrapper
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/                         # Thymeleaf email templates (6 HTML files)
│   └── test/
│       └── java/.../SixsevenBankApplicationTests.java
└── README.md
```

---

## Getting Started

### Prerequisites

- **Java 21** (JDK)
- **MySQL 8+** running locally or remotely
- **Gmail App Password** (or another SMTP provider) for email notifications

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/67-bank.git
cd 67-bank
```

### 2. Create the Database

```sql
CREATE DATABASE sixseven_bank;
```

Hibernate will auto-generate tables on first run (`ddl-auto=update`).

### 3. Configure Environment Variables

Create a `.env` file in the project root:

```env
# Server
PORT=8080

# Database
LOCAL_DB_URL=jdbc:mysql://localhost:3306/sixseven_bank
LOCAL_DB_USERNAME=root
LOCAL_DB_PASSWORD=your_password

# JWT
JWT_SECRET=your_base64_encoded_secret_key
JWT_EXPIRATION_TIME=3600000

# Email (Gmail SMTP)
MAIL_USER=your_email@gmail.com
MAIL_PASS=your_app_password

# AWS S3 (optional — not yet active)
AWS_ACCESS_KEY=your_access_key
AWS_SECRETE_KEY=your_secret_key
AWS_BUCKET_NAME=your_bucket_name
```

### 4. Run the Application

**Linux / macOS:**

```bash
./mvnw spring-boot:run
```

**Windows:**

```cmd
mvnw.cmd spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## Environment Variables

| Variable             | Required | Description                              |
| -------------------- | -------- | ---------------------------------------- |
| `PORT`               | Yes      | Server port (default `8080`)             |
| `LOCAL_DB_URL`       | Yes      | JDBC connection string for MySQL         |
| `LOCAL_DB_USERNAME`  | Yes      | Database username                        |
| `LOCAL_DB_PASSWORD`  | Yes      | Database password                        |
| `JWT_SECRET`         | Yes      | HMAC-SHA256 signing key for JWTs         |
| `JWT_EXPIRATION_TIME`| Yes      | Token lifetime in milliseconds           |
| `MAIL_USER`          | Yes      | SMTP username (e.g. Gmail address)       |
| `MAIL_PASS`          | Yes      | SMTP password or app-specific password   |
| `AWS_ACCESS_KEY`     | No       | AWS access key (for future S3 integration) |
| `AWS_SECRETE_KEY`    | No       | AWS secret key                           |
| `AWS_BUCKET_NAME`    | No       | S3 bucket name                           |

---

## Roadmap

- [x] JWT authentication and role-based authorization
- [x] User registration, login, and password reset flow
- [x] Account management (create, view, close)
- [x] Transaction engine (deposit, withdrawal, transfer)
- [x] Async email notifications with Thymeleaf templates
- [x] Auditor dashboard with system metrics and lookup
- [x] Global exception handling and unified API responses
- [ ] AWS S3 integration for profile picture storage
- [ ] Swagger / OpenAPI documentation
- [ ] Dockerized deployment with Docker Compose
- [ ] CI/CD pipeline (GitHub Actions to AWS EC2)
- [ ] Comprehensive unit and integration tests
- [ ] SMS and push notification channels

---

## Acknowledgments

This project's architecture is inspired by the [Phegon Bank](https://github.com/phegondev/phegon-bank) repository. Building 67-Bank serves as a hands-on implementation to deeply understand enterprise Spring Boot development, API security, and financial system design.

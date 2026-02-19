# 67-Bank API

## Overview

67-Bank is a secure, backend digital banking REST API built with Java and Spring Boot. This project focuses on implementing core banking operations, secure user authentication, and transaction management using modern backend engineering practices.

This project is actively being developed as a structured learning progression, inspired by the architecture and workflows of the [Phegon Bank](https://github.com/phegondev/phegon-bank) reference application.

---

## Tech Stack

| Layer | Technology |
|---|---|
| **Language** | Java 21 |
| **Framework** | Spring Boot 3.x (Web MVC, Data JPA, Security, Mail) |
| **Security** | Spring Security with JWT (JSON Web Tokens) |
| **Database** | MySQL (Hibernate / Spring Data JPA) |
| **Templating** | Thymeleaf (for dynamic HTML email templates) |
| **Build Tool** | Maven Wrapper |

---

## Current Progress & Implemented Features

The application is currently in development. The foundational security, user management, and notification systems have been successfully completed.

### Security & Authentication
- JWT-based stateless authentication with custom security filters
- Role-Based Access Control (RBAC) supporting `ADMIN`, `CUSTOMER`, and `AUDITOR` roles
- Secure user registration, login, and token generation
- Password reset workflow via secure, time-sensitive email verification codes

### User Management
- End-to-end user profile creation and management
- Profile picture upload capabilities
- Relational entity mapping for users, roles, and accounts

### Notification System
- Asynchronous email notifications using `JavaMailSender`
- Customized HTML email templates powered by Thymeleaf (e.g., Welcome Emails, Password Reset Links)

### Robust API Architecture
- Global centralized exception handling (`@ControllerAdvice`) for consistent API error responses
- DTO (Data Transfer Object) pattern implementation using ModelMapper to prevent data over-fetching and ensure security

---

## Roadmap & Upcoming Features

Following the reference architecture, the next phases of development will focus on the core banking engine and cloud infrastructure.

- [ ] **Account Management** — Services and controllers for opening, viewing, and closing Savings/Current accounts
- [ ] **Transaction Engine** — Implementing ACID-compliant logic for deposits, withdrawals, and inter-account transfers with strict balance validation
- [ ] **Auditor Dashboard** — Specialized endpoints for administrators to view system metrics (total users, accounts, transactions) and trace financial logs
- [ ] **AWS S3 Integration** — Migrating local file storage (profile pictures) to Amazon S3
- [ ] **DevOps & Deployment** — Containerizing the application using Docker and setting up GitHub Actions for CI/CD to AWS EC2

---

## Local Setup & Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/67-bank.git
cd 67-bank
```

### 2. Configure Environment Variables

Create a `.env` file in the root directory and configure your local database, JWT secrets, and SMTP settings:

```env
PORT=8080
LOCAL_DB_URL=jdbc:mysql://localhost:3306/sixseven_bank
LOCAL_DB_USERNAME=root
LOCAL_DB_PASSWORD=your_password

JWT_SECRET=your_super_secret_jwt_key_here
JWT_EXPIRATION_TIME=3600000

MAIL_USER=your_email@gmail.com
MAIL_PASS=your_app_password

AWS_ACCESS_KEY=your_access_key
AWS_SECRETE_KEY=your_secret_key
AWS_BUCKET_NAME=your_bucket_name
```

### 3. Run the Application

Start the application using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## Acknowledgments

This project's architecture and learning path are heavily inspired by the [Phegon Bank](https://github.com/phegondev/phegon-bank) repository. Building `67-Bank` serves as a hands-on implementation to deeply understand enterprise Spring Boot development, API security, and system integration.

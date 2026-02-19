# 67-Bank (In Progress)

A backend-focused digital banking application I’m building to practice production-style Java/Spring development.

> **Project status:** 🚧 In progress (active side project)

---

## Why I’m Building This

I’m building **67-Bank** to strengthen my skills in:
- Secure authentication/authorization flows
- Backend architecture with Spring Boot
- Database modeling and persistence with JPA/MySQL
- Real-world integrations (email notifications, JWT, AWS S3)

This project is inspired by the architecture and workflow of the public `phegon-bank` project, while I implement and customize my own version and learning path.

---

## Current Progress

### ✅ What is already set up
- Spring Boot project scaffold and app entry point
- Core dependencies added for:
  - Spring Web MVC
  - Spring Security
  - Spring Data JPA
  - Thymeleaf
  - Validation
  - Mail
  - JWT (`jjwt`)
  - AWS S3 SDK
  - ModelMapper
- Base configuration structure for:
  - Server port
  - MySQL connection
  - JWT secret/expiration
  - Mail SMTP settings
  - AWS S3 credentials and bucket
- HTML email template files for onboarding and password flows

### 🧩 Partially implemented / in progress
- User/auth and notification module wiring
- Business logic and API endpoint completion
- End-to-end integration between controllers, services, repositories, and templates

### ⏭️ Next tasks
- Complete authentication and user account flows
- Build account/transaction APIs
- Add role-based access controls and security hardening
- Improve test coverage (service + controller + integration tests)
- Add deployment setup and public demo endpoint

---

## Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot
- **Security:** Spring Security + JWT
- **Database:** MySQL + Spring Data JPA
- **View/Templates:** Thymeleaf
- **Email:** Spring Mail
- **Cloud Storage:** AWS S3 (SDK v2)
- **Build Tool:** Maven Wrapper

---

## Local Setup (Current)

> This section reflects the current in-progress setup and may evolve.

1. Clone the repository.
2. Create a `.env` file in the root.
3. Provide required environment variables (examples):

```env
PORT=8080
LOCAL_DB_URL=jdbc:mysql://localhost:3306/sixseven_bank
LOCAL_DB_USERNAME=root
LOCAL_DB_PASSWORD=your_password

JWT_SECRET=your_jwt_secret
JWT_EXPIRATION_TIME=3600000

MAIL_USER=your_email@gmail.com
MAIL_PASS=your_app_password

AWS_ACCESS_KEY=your_access_key
AWS_SECRETE_KEY=your_secret_key
AWS_BUCKET_NAME=your_bucket_name
```

4. Start the app:

```bash
bash mvnw spring-boot:run
```

---

## Resume-Friendly Project Summary

If you are a recruiter/developer viewing this repository:

This is an **active in-progress backend banking application** where I am intentionally building features iteratively, with focus on secure backend design, integration patterns, and production-ready engineering practices.

I keep this repository public to demonstrate:
- how I structure a non-trivial Spring project,
- how I integrate multiple services (DB, email, JWT, cloud storage),
- and how I communicate progress transparently while the system is still being developed.

---

## Planned Milestones

- [ ] Milestone 1: Auth + user profile flows complete
- [ ] Milestone 2: Account and transaction operations complete
- [ ] Milestone 3: Improved validation, exception handling, and tests
- [ ] Milestone 4: Deployment + demo + API docs

---

## Notes

Because this is still in development, some classes, endpoints, and flows may be incomplete.

That is intentional: this repo is part of my public learning/build journey.

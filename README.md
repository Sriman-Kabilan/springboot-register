# 🔐 springboot-register  
### Secure Spring Boot 3.x JWT Authentication Backend

A production-ready **Spring Boot 3.x** backend that provides **JWT-based authentication** (Register & Login) with **MySQL**, designed to work seamlessly with the **BlueConnect Flutter BLE Scanner** app.

This backend is deployed on **Railway** and exposes secure REST APIs for user management and token-based authentication.

***

## 🚀 Key Features

- 🔐 **JWT Authentication**  
  - Secure user registration & login
  - JWT token generation and validation
  - Token expiry and refresh-ready design

- 🛡️ **Security**
  - Spring Security with BCrypt password hashing
  - CORS configured for Flutter apps
  - HTTPS enforced in production

- 🗄️ **Database**
  - MySQL 8.x integration
  - JPA / Hibernate ORM
  - Clean entity and repository design

- 🌐 **API & Deployment**
  - RESTful API design (JSON)
  - Deployed on Railway with auto-scaling
  - Environment variables for secrets

***

## 🏗️ System Architecture

```
Flutter App (BlueConnect)
       ↓ HTTPS + JSON
Spring Boot REST API (Railway)
       ↓ JDBC
   MySQL Database (Railway)
```

***

## 🛠️ Tech Stack

| Layer | Technology |
|------|------------|
| **Framework** | Spring Boot 3.x |
| **Language** | Java 17+ |
| **Security** | Spring Security, JWT, BCrypt |
| **Database** | MySQL 8.x |
| **ORM** | JPA / Hibernate |
| **Build Tool** | Maven |
| **Deployment** | Railway |
| **API Docs** | Postman / Swagger (optional) |

***

## 🔗 API Endpoints

### Base URL
```
[https://springboot-register.onrender.com/api]
```

### 1. Register User
```http
POST /api/register
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "password": "securePassword123!"
}
```

**Response (200 OK):**
```json
{
  "message": "User registered successfully"
}
```

### 2. Login User
```http
POST /api/login
Content-Type: application/json

{
  "username": "john@example.com",
  "password": "securePassword123!"
}
```

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "expiresIn": 86400
}
```

***

## 🚀 Quick Start (Local)

### Prerequisites
- Java 17+
- Maven
- MySQL 8.x
- Git

### 1. Clone the Repository
```bash
git clone https://github.com/Sriman-Kabilan/springboot-register.git
cd springboot-register
```

### 2. Configure Database
Update `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_db_name?useSSL=false&serverTimezone=UTC
    username: your_mysql_user
    password: your_mysql_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL8Dialect
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The server will start at `http://localhost:8080`.

***

## 🚀 Deployment on Railway

### 1. Deploy to Railway
- Create a new Railway project.
- Link this GitHub repo.
- Add environment variables:
  - `SPRING_DATASOURCE_URL` – MySQL connection URL
  - `SPRING_DATASOURCE_USERNAME` – DB username
  - `SPRING_DATASOURCE_PASSWORD` – DB password
  - `JWT_SECRET` – Strong secret key for JWT
  - `JWT_EXPIRATION_MS` – e.g., `86400000` (24 hours)

### 2. Production Base URL
```
https://springbootregister-production.up.railway.app/api
```

***

## 📂 Project Structure

```
springboot-register/
├── src/main/java/com/example/springbootregister/
│   ├── config/          # Security & JWT config
│   ├── controller/      # REST controllers
│   ├── model/           # JPA entities
│   ├── repository/      # JPA repositories
│   ├── service/         # Business logic
│   └── SpringbootRegisterApplication.java
├── src/main/resources/
│   ├── application.yml  # DB & app config
│   └── application.properties
└── pom.xml              # Maven dependencies
```

***

## 🔐 Security Best Practices

- ✅ Passwords are hashed using BCrypt.
- ✅ JWT tokens are signed and have a limited expiry.
- ✅ CORS is configured only for trusted origins (Flutter app).
- ✅ Secrets are stored in environment variables, not in code.
- ✅ HTTPS is enforced in production.

***

## 📂 Repository Links

- 🔗 **Backend Repo**: [https://github.com/Sriman-Kabilan/springboot-register](https://github.com/Sriman-Kabilan/springboot-register)
- 🔗 **Flutter App**: [https://github.com/Sriman-Kabilan/Flutter_BLE_Scanner](https://github.com/Sriman-Kabilan/Flutter_BLE_Scanner)

***

## 🌟 Why This Backend Stands Out

- ✅ **Production-Ready**: JWT auth, MySQL, Railway deployment.
- ✅ **Secure**: BCrypt, JWT, environment secrets.
- ✅ **Clean Architecture**: Separated layers (Controller, Service, Repository).
- ✅ **Flutter-Ready**: Designed for mobile apps with proper CORS and JSON APIs.
- ✅ **Scalable**: Easy to extend with roles, refresh tokens, and more.

***

## 🚀 Future Enhancements

- 🔁 Refresh token support
- 👤 Role-based access control (Admin/User)
- 📧 Email verification
- 📊 Spring Boot Actuator for monitoring
- 🧪 Unit & integration tests (JUnit, Mockito)
- 📈 CI/CD pipeline (GitHub Actions)

***

## 📜 License

This project is licensed under the **MIT License** – see the [LICENSE](LICENSE) file for details.

***

## 👨‍💻 Author
**Sriman Kabilan**  

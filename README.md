# JWT Authentication Demo Project

This project demonstrates the **basic working of JWT (JSON Web Token)** for authentication and authorization in a Spring Boot application.  
It covers user registration, login, and secure access to protected resources using JWT.

---

## 🚀 Features
- **User Registration** → Register with username, email, and password (password securely stored).
- **User Login** → Authenticate with stored credentials and generate a JWT token.
- **Profile Access** → Access profile details only with a valid JWT token.
- **Courses Access** → Access user-specific courses with a valid JWT token.
- **Authentication & Authorization** → Demonstrates both identity verification and controlled resource access.

---

## 🔑 JWT Flow
1. User registers or logs in with credentials.  
2. Server generates a JWT token on successful authentication.  
3. Client sends the JWT token in the request header (`Authorization: Bearer <token>`).  
4. Server validates the token and authorizes access to protected endpoints.  

---

## 🛠️ Tech Stack
- Java  
- Spring Boot  
- Spring Security  
- JWT (JSON Web Token)  
- MySQL (or any relational database)  

---

## 📂 Project Structure
- **Controller Layer** → Handles API requests (register, login, profile, courses).  
- **Service Layer** → Business logic for authentication and token generation.  
- **Repository Layer** → Database access using Spring Data JPA.  
- **Security Layer** → JWT filter, authentication manager, and token validation.  

---

## ▶️ How to Run
1. Clone the repository:  
   ```bash
   git clone https://github.com/your-username/your-repo.git

# 🚀 Spring Boot REST API with Swagger Documentation

This project is a **Spring Boot-based REST API** integrated with **Swagger UI** for interactive API documentation and testing.  
It demonstrates how to build, document, and deploy production-ready REST APIs using modern Spring Boot practices.

---

## 🌐 Live API Documentation
Access the live Swagger UI here:  
🔗 [Deployed on Render](https://finalrepofrosprinboot-2.onrender.com/swagger-ui/index.html#/)

---

## 🧠 Overview
The project provides a structured and scalable REST API using Spring Boot.  
It is integrated with **Swagger** to allow developers to explore and test endpoints directly through a web interface.  

The API is suitable for:
- CRUD operations  
- Backend integration for web/mobile apps  
- Demonstrating secure and documented RESTful design  

---

## ⚙️ Key Features
✅ Built using **Spring Boot 3+**  
✅ **Swagger UI** for API visualization and testing  
✅ Layered architecture (Controller → Service → Repository)  
✅ Integrated with **Spring Data JPA** and **Hibernate**  
✅ Exception handling and validation support  
✅ Deployed on **Render Cloud**

---

## 🛠️ Tech Stack
| Component | Technology Used |
|------------|-----------------|
| **Backend Framework** | Spring Boot |
| **Language** | Java |
| **Database** | H2 / MySQL (configurable) |
| **API Documentation** | Swagger UI / OpenAPI 3 |
| **Build Tool** | Maven |
| **Deployment** | Render Cloud Platform |

---

## 📦 Project Setup

### 🧩 Prerequisites
- Java 8 or later  
- Maven 3.8+  
- IDE (IntelliJ IDEA / Eclipse / VS Code)  

---

### 🏃 Steps to Run Locally

```bash
# 1️⃣ Clone the repository
git clone https://github.com/ruby10111/finalrepofrosprinboot.git
cd finalrepofrosprinboot 

# 2️⃣ Build the project
mvn clean install



## 📁 Project Structure

src/
 ├── main/
 │   ├── java/
 │   │   └── com/example/project/
 │   │        ├── controller/     # REST Controllers
 │   │        ├── service/        # Business Logic
 │   │        ├── model/          # Entity / DTO Classes
 │   │        └── repository/     # JPA Repositories
 │   └── resources/
 │       ├── application.properties
 │       └── static/ templates/
 └── test/                        # Unit Tests

# 3️⃣ Run the application
mvn spring-boot:run

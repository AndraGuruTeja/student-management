# Student Management - Spring Boot Application

A simple Spring Boot REST API for managing student records with full CRUD operations.

## Features

- **Create** new students
- **Read** student details (by ID, email, roll number, course, semester, or search by name)
- **Update** student information
- **Delete** students
- In-memory H2 database for quick testing
- RESTful API endpoints
- CORS enabled for frontend integration

## Project Structure

```
StudentManagement/
├── pom.xml                              # Maven dependencies
├── Dockerfile                           # Docker containerization
├── .dockerignore                        # Docker build exclusions
├── README.md                            # This file
└── src/
    ├── main/
    │   ├── java/com/student/
    │   │   ├── StudentManagementApplication.java   # Main Spring Boot class
    │   │   ├── controller/
    │   │   │   └── StudentController.java          # REST endpoints
    │   │   ├── service/
    │   │   │   └── StudentService.java             # Business logic
    │   │   ├── repository/
    │   │   │   └── StudentRepository.java          # Data access
    │   │   └── entity/
    │   │       └── Student.java                    # Entity model
    │   └── resources/
    │       └── application.properties               # Configuration
    └── test/
        └── java/                                   # Test files
```

## Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **Docker** (optional, for containerization)

## Build & Run Locally

### 1. Build the project

```powershell
cd C:\Users\andra\Desktop\EmployeeManagement
mvn clean package
```

### 2. Run the application

```powershell
mvn spring-boot:run
```

Or run the JAR directly:

```powershell
java -jar target/student-management-1.0.0.jar
```

The application will start on `http://localhost:8080`

### 3. Access H2 Console (for debugging)

Open `http://localhost:8080/h2-console` and use:
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave empty)

## Build & Run with Docker

### 1. Build the Docker image

```powershell
cd C:\Users\andra\Desktop\EmployeeManagement
mvn clean package
docker build -t student-management:latest .
```

### 2. Run the Docker container

```powershell
docker run -d -p 8080:8080 --name student-app student-management:latest
```

### 3. Verify the container is running

```powershell
docker ps --filter name=student-app
docker logs student-app --tail 50
```

### 4. Test the API

```powershell
# Create a student
$body = @{
    firstName = "John"
    lastName = "Doe"
    email = "john.doe@example.com"
    rollNumber = "ST001"
    course = "Computer Science"
    semester = 1
    phoneNumber = "123-456-7890"
} | ConvertTo-Json

Invoke-RestMethod -UseBasicParsing -Uri http://localhost:8080/api/students `
    -Method POST `
    -ContentType "application/json" `
    -Body $body
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/students` | Create a new student |
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| GET | `/api/students/email/{email}` | Get student by email |
| GET | `/api/students/roll/{rollNumber}` | Get student by roll number |
| GET | `/api/students/course/{course}` | Get students by course |
| GET | `/api/students/semester/{semester}` | Get students by semester |
| GET | `/api/students/search/{firstName}` | Search students by first name |
| PUT | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Delete student |

## Example API Requests

### Create Student
```bash
POST /api/students
Content-Type: application/json

{
  "firstName": "Alice",
  "lastName": "Smith",
  "email": "alice.smith@example.com",
  "rollNumber": "ST002",
  "course": "Electronics",
  "semester": 2,
  "phoneNumber": "987-654-3210"
}
```

### Get All Students
```bash
GET /api/students
```

### Get Student by ID
```bash
GET /api/students/1
```

### Update Student
```bash
PUT /api/students/1
Content-Type: application/json

{
  "firstName": "Alice",
  "lastName": "Johnson",
  "email": "alice.johnson@example.com",
  "rollNumber": "ST002",
  "course": "Mechanical Engineering",
  "semester": 3,
  "phoneNumber": "555-123-4567"
}
```

### Delete Student
```bash
DELETE /api/students/1
```

## Stop & Remove Docker Container

```powershell
docker stop student-app
docker rm student-app
```

## View Docker Image

```powershell
docker images | grep student-management
```

## Troubleshooting

- **Port 8080 already in use?** Run on a different port: `docker run -d -p 8081:8080 --name student-app student-management:latest`
- **Build fails?** Ensure Maven and Java 17+ are installed: `java -version` and `mvn -v`
- **Container won't start?** Check logs: `docker logs student-app`

## Technology Stack

- **Spring Boot 3.1.5**
- **Spring Web** (REST APIs)
- **Spring Data JPA** (Database)
- **H2 Database** (In-memory)
- **Lombok** (Reduces boilerplate)
- **Maven** (Build tool)
- **Docker** (Containerization)
- **Java 17**

## License

MIT License

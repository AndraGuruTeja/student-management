# Employee Management - Spring Boot Application

A simple Spring Boot REST API for managing employee records with full CRUD operations.

## Features

- **Create** new employees
- **Read** employee details (by ID, email, department, or search by name)
- **Update** employee information
- **Delete** employees
- In-memory H2 database for quick testing
- RESTful API endpoints
- CORS enabled for frontend integration

## Project Structure

```
EmployeeManagement/
├── pom.xml                              # Maven dependencies
├── Dockerfile                           # Docker containerization
├── .dockerignore                        # Docker build exclusions
├── README.md                            # This file
└── src/
    ├── main/
    │   ├── java/com/employee/
    │   │   ├── EmployeeManagementApplication.java  # Main Spring Boot class
    │   │   ├── controller/
    │   │   │   └── EmployeeController.java         # REST endpoints
    │   │   ├── service/
    │   │   │   └── EmployeeService.java            # Business logic
    │   │   ├── repository/
    │   │   │   └── EmployeeRepository.java         # Data access
    │   │   └── entity/
    │   │       └── Employee.java                   # Entity model
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
java -jar target/employee-management-1.0.0.jar
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
docker build -t employee-management:latest .
```

### 2. Run the Docker container

```powershell
docker run -d -p 8080:8080 --name employee-app employee-management:latest
```

### 3. Verify the container is running

```powershell
docker ps --filter name=employee-app
docker logs employee-app --tail 50
```

### 4. Test the API

```powershell
# Create an employee
$body = @{
    firstName = "John"
    lastName = "Doe"
    email = "john.doe@example.com"
    department = "IT"
    salary = 50000
    phoneNumber = "123-456-7890"
} | ConvertTo-Json

Invoke-RestMethod -UseBasicParsing -Uri http://localhost:8080/api/employees `
    -Method POST `
    -ContentType "application/json" `
    -Body $body
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/employees` | Create a new employee |
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| GET | `/api/employees/email/{email}` | Get employee by email |
| GET | `/api/employees/department/{department}` | Get employees by department |
| GET | `/api/employees/search/{firstName}` | Search employees by first name |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |

## Example API Requests

### Create Employee
```bash
POST /api/employees
Content-Type: application/json

{
  "firstName": "Alice",
  "lastName": "Smith",
  "email": "alice.smith@example.com",
  "department": "HR",
  "salary": 60000,
  "phoneNumber": "987-654-3210"
}
```

### Get All Employees
```bash
GET /api/employees
```

### Get Employee by ID
```bash
GET /api/employees/1
```

### Update Employee
```bash
PUT /api/employees/1
Content-Type: application/json

{
  "firstName": "Alice",
  "lastName": "Johnson",
  "email": "alice.johnson@example.com",
  "department": "Engineering",
  "salary": 75000,
  "phoneNumber": "555-123-4567"
}
```

### Delete Employee
```bash
DELETE /api/employees/1
```

## Stop & Remove Docker Container

```powershell
docker stop employee-app
docker rm employee-app
```

## View Docker Image

```powershell
docker images | grep employee-management
```

## Troubleshooting

- **Port 8080 already in use?** Run on a different port: `docker run -d -p 8081:8080 --name employee-app employee-management:latest`
- **Build fails?** Ensure Maven and Java 17+ are installed: `java -version` and `mvn -v`
- **Container won't start?** Check logs: `docker logs employee-app`

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

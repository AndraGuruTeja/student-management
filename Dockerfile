# Stage 1: Build the application with Maven
FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /build

# Copy the entire project
COPY . .

# Build the application
RUN apk add --no-cache maven && \
    mvn clean package -DskipTests

# Stage 2: Runtime image
FROM eclipse-temurin:17-jre-alpine

# Set working directory inside container
WORKDIR /app

# Copy the JAR file from builder stage
COPY --from=builder /build/target/student-management-1.0.0.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

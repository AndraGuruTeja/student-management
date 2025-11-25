# Use official Java 17 runtime as a parent image
FROM eclipse-temurin:17-jre-alpine

# Set working directory inside container
WORKDIR /app

# Copy the JAR file from target folder (built by Maven)
COPY target/student-management-1.0.0.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

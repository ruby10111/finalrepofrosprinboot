# Use lightweight Java image
FROM eclipse-temurin:17-jdk-alpine

# Create working directory
WORKDIR /app

# Copy Maven wrapper & pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (this step caches Maven deps)
RUN ./mvnw dependency:go-offline

# Copy the rest of the project
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (Render dynamically sets PORT)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/journalApp-0.0.1-SNAPSHOT.jar"]

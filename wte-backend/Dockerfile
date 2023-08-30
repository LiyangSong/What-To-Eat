# Use maven/Java 17 image to create a build artifact.
FROM maven:3.8.5-openjdk-17-slim AS build

# Set working directory
WORKDIR /usr/src/app

# Copy the pom.xml file and download the dependencies.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the project files and build the project.
COPY . .
RUN mvn clean package -DskipTests

# Use OpenJDK to run the JAR
FROM openjdk:17-jdk-slim

# Copy the JAR from the build stage
COPY --from=build /usr/src/app/target/wte-backend-*.jar /app/wte-backend.jar

# Set the command to run your application
ENTRYPOINT ["java", "-jar", "/app/wte-backend.jar"]

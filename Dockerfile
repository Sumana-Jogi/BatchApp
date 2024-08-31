# Use an official Maven image to build the project
FROM maven:3.8.5-openjdk-11 AS builder

# Set the dir for executing future commands
WORKDIR /app

# Copy the pom.xml file and the source code into the container
COPY pom.xml /app
COPY src /app/src
#COPY src/main/resources /app/src/main/resources

# Package the application as a shaded JAR
RUN mvn clean package

#use the open jdk 11 image as the base image
FROM openjdk:11

# Copy the JAR file from the builder stage to the final image
COPY --from=builder /app/target/SparkPro-1.0-SNAPSHOT.jar /app/SparkPro.jar

# Set the entry point for the container to run your JAR file
ENTRYPOINT ["java", "-jar", "/app/SparkPro.jar"]


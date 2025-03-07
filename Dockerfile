# Etapa de construcción con Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

# Etapa final con Java 17
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app /app
EXPOSE 8080
CMD ["mvn", "tomcat7:run"]

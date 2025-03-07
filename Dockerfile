# Etapa de construcción con Maven y Java 21
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

# Etapa final con Java 21
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app /app
EXPOSE 8080
CMD ["mvn", "tomcat7:run"]

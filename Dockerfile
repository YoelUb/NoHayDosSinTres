# Etapa de construcción
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

# Etapa final con Jetty
FROM jetty:11
WORKDIR /var/lib/jetty/webapps
COPY --from=build /app/target/*.war /var/lib/jetty/webapps/ROOT.war

EXPOSE 8080
CMD ["java", "-jar", "/usr/local/jetty/start.jar"]

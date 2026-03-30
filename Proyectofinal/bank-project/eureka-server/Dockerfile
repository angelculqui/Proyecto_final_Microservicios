FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia el JAR generado por Maven
COPY target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

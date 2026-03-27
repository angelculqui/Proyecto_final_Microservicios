# Build stage
FROM eclipse-temurin:17-jre
WORKDIR /app
# cambiado: usar el nombre exacto del JAR generado
COPY target/account-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
# Use shell form to expand environment variable SPRING_DATA_MONGODB_URI into a JVM property
ENTRYPOINT ["sh","-c","java -Dspring.data.mongodb.host=mongo -Dspring.data.mongodb.port=27017 -Dspring.data.mongodb.uri=${SPRING_DATA_MONGODB_URI} -jar /app/app.jar"]

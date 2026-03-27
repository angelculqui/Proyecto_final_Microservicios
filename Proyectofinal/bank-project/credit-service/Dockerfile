# Build stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/credit-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["sh","-c","java -Dspring.data.mongodb.host=mongo -Dspring.data.mongodb.port=27017 -Dspring.data.mongodb.uri=${SPRING_DATA_MONGODB_URI} -jar /app/app.jar"]


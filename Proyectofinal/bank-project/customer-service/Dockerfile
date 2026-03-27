FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/customer-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

# Pass important Spring properties as system properties so the container
# will use the docker-compose environment values (SPRING_DATA_MONGODB_URI, REDIS_HOST, etc.)
# This ensures the app doesn't fall back to localhost when resolving dependencies
# inside the docker-compose network.
ENTRYPOINT ["sh", "-c", "java -Dspring.data.mongodb.uri=${SPRING_DATA_MONGODB_URI} -Dspring.data.mongodb.host=${SPRING_DATA_MONGODB_HOST:-mongo-bank} -Dspring.data.mongodb.port=${SPRING_DATA_MONGODB_PORT:-27017} -Dspring.redis.host=${REDIS_HOST} -Dspring.redis.port=${REDIS_PORT} -Dspring.kafka.bootstrap-servers=${SPRING_KAFKA_BOOTSTRAP_SERVERS} -jar /app/app.jar"]

package com.bank.customer_service.config;

import com.mongodb.ConnectionString;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Explicit reactive Mongo configuration.
 * Reason: force the application to use the SPRING_DATA_MONGODB_URI provided
 * by docker-compose and avoid falling back to localhost. This bean ensures
 * the Reactive MongoClient is created from the URI we expect.
 */
@Configuration
public class MongoConfig {

    @Bean
    public MongoClient reactiveMongoClient(Environment env) {
        // Read the configured URI from environment or application properties
        String uri = env.getProperty("spring.data.mongodb.uri", "mongodb://mongo-bank:27017/customer_db");
        ConnectionString connectionString = new ConnectionString(uri);
        return MongoClients.create(connectionString);
    }

    /**
     * Provide a synchronous MongoClient bean so Spring Boot does not create a
     * default one (which in some environments fell back to localhost). This
     * ensures sync components (health indicators or templates) use the
     * same configured URI.
     */
    @Bean
    @Primary
    public com.mongodb.client.MongoClient syncMongoClient(Environment env) {
        String uri = env.getProperty("spring.data.mongodb.uri", "mongodb://mongo-bank:27017/customer_db");
        return com.mongodb.client.MongoClients.create(uri);
    }

}


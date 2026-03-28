package com.bank.transaction_service.config;

import com.mongodb.ConnectionString;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Explicit reactive Mongo configuration for transaction-service.
 */
@Configuration
public class MongoConfig {

    @Bean
    public MongoClient reactiveMongoClient(Environment env) {
        String uri = env.getProperty("spring.data.mongodb.uri", "mongodb://mongo-bank:27017/transaction_db");
        ConnectionString connectionString = new ConnectionString(uri);
        return MongoClients.create(connectionString);
    }

    @Bean
    @Primary
    public com.mongodb.client.MongoClient syncMongoClient(Environment env) {
        String uri = env.getProperty("spring.data.mongodb.uri", "mongodb://mongo-bank:27017/transaction_db");
        return com.mongodb.client.MongoClients.create(uri);
    }
}


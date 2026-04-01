package com.bank.customer_service.config;

import com.mongodb.ConnectionString;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

/**
 * Explicit reactive Mongo configuration.
 * // 🔧 CAMBIO: Javadoc ya estaba OK
 */
@Configuration
public class MongoConfig {

    /**
     * Creates a reactive MongoClient using the configured URI.
     * // 🔧 CAMBIO: agregado Javadoc requerido
     */
    @Bean
    public MongoClient reactiveMongoClient(Environment env) {
        String uri = env.getProperty("spring.data.mongodb.uri",
                "mongodb://mongo-bank:27017/customer_db"); // 🔧 CAMBIO: línea dividida
        ConnectionString connectionString = new ConnectionString(uri);
        return MongoClients.create(connectionString);
    }

    /**
     * Creates a synchronous MongoClient to avoid fallback to localhost.
     * // 🔧 CAMBIO: agregado Javadoc requerido
     */
    @Bean
    @Primary
    public com.mongodb.client.MongoClient syncMongoClient(Environment env) {
        String uri = env.getProperty("spring.data.mongodb.uri",
                "mongodb://mongo-bank:27017/customer_db"); // 🔧 CAMBIO: línea dividida
        return com.mongodb.client.MongoClients.create(uri);
    }
}



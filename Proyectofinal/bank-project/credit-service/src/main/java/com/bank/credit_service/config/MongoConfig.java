package com.bank.credit_service.config;

import com.mongodb.ConnectionString;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient reactiveMongoClient(Environment env) {
        String uri = env.getProperty("spring.data.mongodb.uri", "mongodb://mongo-bank:27017/credit_db");
        return MongoClients.create(new ConnectionString(uri));
    }
}



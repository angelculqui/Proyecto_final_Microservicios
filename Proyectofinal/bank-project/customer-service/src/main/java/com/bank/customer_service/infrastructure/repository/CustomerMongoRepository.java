package com.bank.customer_service.infrastructure.repository;

import com.bank.customer_service.domain.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerMongoRepository extends MongoRepository<Customer, String> {

    // 🔥 Query automática por Spring Data
    Optional<Customer> findByDocumentNumber(String documentNumber);
}
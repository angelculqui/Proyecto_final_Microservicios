package com.bank.customer_service.infrastructure.repository;

import com.bank.customer_service.domain.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerMongoRepository extends ReactiveMongoRepository<Customer, String> {

    // Reactive query by document number
    Mono<Customer> findByDocumentNumber(String documentNumber);
}
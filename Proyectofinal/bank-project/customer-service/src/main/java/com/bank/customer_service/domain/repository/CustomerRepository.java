package com.bank.customer_service.domain.repository;

import com.bank.customer_service.domain.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository {

    Flux<Customer> findAll();

    Mono<Customer> findById(String id);

    Mono<Customer> findByDocumentNumber(String documentNumber);

    Mono<Customer> save(Customer customer);

    Mono<Void> delete(Customer customer);
}

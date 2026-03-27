package com.bank.customer_service.infrastructure.repository;

import com.bank.customer_service.domain.model.Customer;
import com.bank.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerMongoRepository mongoRepository;

    @Override
    public Flux<Customer> findAll() {
        return mongoRepository.findAll();
    }

    @Override
    public Mono<Customer> findById(String id) {
        return mongoRepository.findById(id);
    }

    @Override
    public Mono<Customer> findByDocumentNumber(String documentNumber) {
        return mongoRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public Mono<Customer> save(Customer customer) {
        return mongoRepository.save(customer);
    }

    @Override
    public Mono<Void> delete(Customer customer) {
        return mongoRepository.delete(customer);
    }
}

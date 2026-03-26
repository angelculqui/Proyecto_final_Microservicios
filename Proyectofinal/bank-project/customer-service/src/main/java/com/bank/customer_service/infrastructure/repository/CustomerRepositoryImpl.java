package com.bank.customer_service.infrastructure.repository;

import com.bank.customer_service.domain.model.Customer;
import com.bank.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerMongoRepository mongoRepository;

    @Override
    public List<Customer> findAll() {
        return mongoRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(String id) {
        return mongoRepository.findById(id);
    }

    @Override
    public Optional<Customer> findByDocumentNumber(String documentNumber) {
        return mongoRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public Customer save(Customer customer) {
        return mongoRepository.save(customer);
    }

    @Override
    public void delete(String id) {
        mongoRepository.deleteById(id);
    }
}
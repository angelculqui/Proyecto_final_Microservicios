package com.bank.customer_service.application.service;

import com.bank.customer_service.domain.model.Customer;
import com.bank.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Mono<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public Mono<Customer> createCustomer(Customer customer) {
        return customerRepository.findByDocumentNumber(customer.getDocumentNumber())
                .flatMap(existing -> Mono.<Customer>error(new RuntimeException("Customer with this document already exists")))
                .switchIfEmpty(customerRepository.save(customer));
    }

    public Mono<Customer> updateCustomer(String id, Customer customer) {
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Customer not found")))
                .flatMap(existing -> {
                    existing.setName(customer.getName());
                    existing.setDocumentNumber(customer.getDocumentNumber());
                    existing.setType(customer.getType());
                    return customerRepository.save(existing);
                });
    }

    public Mono<Void> deleteCustomer(String id) {
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Customer not found")))
                .flatMap(customerRepository::delete);
    }
}

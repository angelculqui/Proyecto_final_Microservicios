package com.bank.customer_service.infrastructure.controller;

import com.bank.customer_service.application.service.CustomerService;
import com.bank.customer_service.domain.model.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for Customer operations.
 * // 🔧 CAMBIO: agregado Javadoc requerido
 */
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Get all customers.
     * // 🔧 CAMBIO: agregado Javadoc
     */
    @GetMapping
    public Flux<Customer> getAll() {
        return customerService.getAllCustomers();
    }

    /**
     * Get customer by ID.
     * // 🔧 CAMBIO: agregado Javadoc
     */
    @GetMapping("/{id}")
    public Mono<Customer> getById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    /**
     * Create a new customer.
     * // 🔧 CAMBIO: agregado Javadoc
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> create(@Valid @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    /**
     * Update a customer.
     * // 🔧 CAMBIO: agregado Javadoc
     */
    @PutMapping("/{id}")
    public Mono<Customer> update(@PathVariable String id,
                                 @Valid @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    /**
     * Delete a customer.
     * // 🔧 CAMBIO: agregado Javadoc
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return customerService.deleteCustomer(id);
    }
}

package com.bank.customer_service.infrastructure.controller;

import com.bank.customer_service.application.service.CustomerService;
import com.bank.customer_service.domain.model.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // Obtener todos
    @GetMapping
    public Flux<Customer> getAll() {
        return customerService.getAllCustomers();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Mono<Customer> getById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    // Crear
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> create(@Valid @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    // Actualizar
    @PutMapping("/{id}")
    public Mono<Customer> update(@PathVariable String id,
                                           @Valid @RequestBody Customer customer) {

        return customerService.updateCustomer(id, customer);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return customerService.deleteCustomer(id);
    }
}
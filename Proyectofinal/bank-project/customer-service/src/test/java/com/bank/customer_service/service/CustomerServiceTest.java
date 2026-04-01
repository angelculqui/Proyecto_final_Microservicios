package com.bank.customer_service.service;

import com.bank.customer_service.application.service.CustomerService;
import com.bank.customer_service.domain.model.Customer;
import com.bank.customer_service.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    @Test
    void testGetAllCustomers() {
        Customer c = new Customer("1", "Juan", "DNI-1", "PERSONAL");

        when(repository.findAll()).thenReturn(Flux.just(c));

        StepVerifier.create(service.getAllCustomers())
                .expectNext(c)
                .verifyComplete();
    }

    @Test
    void testGetCustomerById() {
        Customer c = new Customer("1", "Juan", "DNI-1", "PERSONAL");

        when(repository.findById("1")).thenReturn(Mono.just(c));

        StepVerifier.create(service.getCustomerById("1"))
                .expectNext(c)
                .verifyComplete();
    }

    @Test
    void testCreateCustomerAlreadyExists() {
        Customer c = new Customer("1", "Juan", "DNI-1", "PERSONAL");

        when(repository.findByDocumentNumber(anyString()))
                .thenReturn(Mono.just(c));

        StepVerifier.create(service.createCustomer(c))
                .expectErrorMatches(ex -> ex.getMessage().contains("already exists"))
                .verify();
    }

    @Test
    void testCreateCustomerSuccess() {
        Customer c = new Customer("1", "Juan", "DNI-1", "PERSONAL");

        when(repository.findByDocumentNumber(anyString())).thenReturn(Mono.empty());
        when(repository.save(c)).thenReturn(Mono.just(c));

        StepVerifier.create(service.createCustomer(c))
                .expectNext(c)
                .verifyComplete();
    }

    @Test
    void testUpdateCustomer() {
        Customer existing = new Customer("1", "Juan", "DNI-1", "PERSONAL");
        Customer update = new Customer(null, "Nuevo", "DNI-2", "PERSONAL");

        when(repository.findById("1")).thenReturn(Mono.just(existing));
        when(repository.save(existing)).thenReturn(Mono.just(existing));

        StepVerifier.create(service.updateCustomer("1", update))
                .expectNext(existing)
                .verifyComplete();
    }

    @Test
    void testDeleteCustomer() {
        Customer existing = new Customer("1", "Juan", "DNI-1", "PERSONAL");

        when(repository.findById("1")).thenReturn(Mono.just(existing));
        when(repository.delete(existing)).thenReturn(Mono.empty());

        StepVerifier.create(service.deleteCustomer("1"))
                .verifyComplete();
    }
}

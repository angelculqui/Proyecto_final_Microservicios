package com.bank.customer_service.repository;

import com.bank.customer_service.domain.model.Customer;
import com.bank.customer_service.infrastructure.repository.CustomerMongoRepository;
import com.bank.customer_service.infrastructure.repository.CustomerRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryImplTest {

    @Mock
    private CustomerMongoRepository mongo;

    @InjectMocks
    private CustomerRepositoryImpl repo;

    @Test
    void testFindAll() {
        Customer c = new Customer("1", "Juan Pérez", "DNI-2001", "PERSONAL");

        when(mongo.findAll()).thenReturn(Flux.just(c));

        StepVerifier.create(repo.findAll())
                .expectNext(c)
                .verifyComplete();
    }

    @Test
    void testFindById() {
        Customer c = new Customer("1", "Juan Pérez", "DNI-2001", "PERSONAL");

        when(mongo.findById("1")).thenReturn(Mono.just(c));

        StepVerifier.create(repo.findById("1"))
                .expectNext(c)
                .verifyComplete();
    }

    @Test
    void testFindByDocumentNumber() {
        Customer c = new Customer("1", "Juan Pérez", "DNI-2001", "PERSONAL");

        when(mongo.findByDocumentNumber("DNI-2001")).thenReturn(Mono.just(c));

        StepVerifier.create(repo.findByDocumentNumber("DNI-2001"))
                .expectNext(c)
                .verifyComplete();
    }

    @Test
    void testSave() {
        Customer c = new Customer("1", "Juan Pérez", "DNI-2001", "PERSONAL");

        when(mongo.save(c)).thenReturn(Mono.just(c));

        StepVerifier.create(repo.save(c))
                .expectNext(c)
                .verifyComplete();
    }

    @Test
    void testDelete() {
        Customer c = new Customer("1", "Juan Pérez", "DNI-2001", "PERSONAL");

        when(mongo.delete(c)).thenReturn(Mono.empty());

        StepVerifier.create(repo.delete(c))
                .verifyComplete();
    }
}

package com.bank.customer_service.controller;

import com.bank.customer_service.application.service.CustomerService;
import com.bank.customer_service.domain.model.Customer;
import com.bank.customer_service.infrastructure.controller.CustomerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService service;

    private WebTestClient client;

    @BeforeEach
    void setup() {
        client = WebTestClient.bindToController(new CustomerController(service)).build();
    }

    @Test
    void testGetAll() {
        Customer c1 = new Customer("1", "Juan Pérez", "DNI-2001", "PERSONAL");
        Customer c2 = new Customer("2", "TechCorp SAC", "RUC-2005", "BUSINESS");

        when(service.getAllCustomers()).thenReturn(Flux.just(c1, c2));

        client.get().uri("/customers")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Juan Pérez")
                .jsonPath("$[1].name").isEqualTo("TechCorp SAC");
    }

    @Test
    void testGetById() {
        Customer c = new Customer("1", "Juan Pérez", "DNI-2001", "PERSONAL");

        when(service.getCustomerById("1")).thenReturn(Mono.just(c));

        client.get().uri("/customers/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.documentNumber").isEqualTo("DNI-2001");
    }

    @Test
    void testCreate() {
        Customer c = new Customer("1", "Juan Pérez", "DNI-2001", "PERSONAL");

        when(service.createCustomer(c)).thenReturn(Mono.just(c));

        client.post().uri("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(c)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1");
    }

    @Test
    void testUpdate() {
        Customer updated = new Customer("1", "Juan Updated", "DNI-9999", "PERSONAL");

        when(service.updateCustomer("1", updated)).thenReturn(Mono.just(updated));

        client.put().uri("/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updated)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Juan Updated");
    }

    @Test
    void testDelete() {
        when(service.deleteCustomer("1")).thenReturn(Mono.empty());

        client.delete().uri("/customers/1")
                .exchange()
                .expectStatus().isNoContent();
    }
}

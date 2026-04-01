package com.bank.credit_service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerClient {

    private final WebClient webClient;

    private static final String BASE_URL = "http://customer-service:8081/customers";

    public Mono<CustomerDTO> getCustomerById(String id) {
        return webClient.get()
                .uri(BASE_URL + "/{id}", id)
                .retrieve()
                .bodyToMono(CustomerDTO.class)
                .switchIfEmpty(Mono.error(new RuntimeException("Customer not found")));
    }
}


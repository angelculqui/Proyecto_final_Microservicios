package com.bank.transaction_service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class AccountClient {

    private final WebClient webClient;

    private final String BASE_URL = "http://account-service:8082/api/accounts";

    public String getAccountById(String accountId) {

        return webClient.get()
                .uri(BASE_URL + "/" + accountId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
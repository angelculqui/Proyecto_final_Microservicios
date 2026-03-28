package com.bank.transaction_service.client;

import com.bank.transaction_service.dto.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AccountClient {

    private final WebClient webClient;

    private static final String BASE_URL = "http://account-service:8082/api/accounts";

    public Mono<AccountDTO> getAccountById(String accountId) {
        return webClient.get()
                .uri(BASE_URL + "/" + accountId)
                .retrieve()
                .bodyToMono(AccountDTO.class)
                .switchIfEmpty(Mono.error(new RuntimeException("Account not found")));
    }

    public Mono<AccountDTO> updateAccount(AccountDTO account) {
        return webClient.put()
                .uri(BASE_URL)
                .bodyValue(account)
                .retrieve()
                .bodyToMono(AccountDTO.class);
    }
}

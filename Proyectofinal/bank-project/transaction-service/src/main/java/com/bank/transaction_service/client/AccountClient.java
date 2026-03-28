package com.bank.transaction_service.client;

import com.bank.transaction_service.dto.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AccountClient {

    private final WebClient webClient;
    private final ReactiveCircuitBreakerFactory<?, ?> circuitBreakerFactory;

    private static final String BASE_URL = "http://account-service:8082/api/accounts";

    public Mono<AccountDTO> getAccountById(String accountId) {

        ReactiveCircuitBreaker cb =
                circuitBreakerFactory.create("accountServiceCircuitBreaker");

        Mono<AccountDTO> call = webClient.get()
                .uri(BASE_URL + "/" + accountId)
                .retrieve()
                .bodyToMono(AccountDTO.class)
                .switchIfEmpty(Mono.error(new RuntimeException("Account not found")));

        return cb.run(
                call,
                throwable -> Mono.error(new RuntimeException("Account service unavailable", throwable))
        );
    }

    public Mono<AccountDTO> updateAccount(AccountDTO account) {

        ReactiveCircuitBreaker cb =
                circuitBreakerFactory.create("accountServiceCircuitBreaker");

        Mono<AccountDTO> call = webClient.put()
                .uri(BASE_URL)
                .bodyValue(account)
                .retrieve()
                .bodyToMono(AccountDTO.class);

        return cb.run(
                call,
                throwable -> Mono.error(new RuntimeException("Account service unavailable", throwable))
        );
    }
}

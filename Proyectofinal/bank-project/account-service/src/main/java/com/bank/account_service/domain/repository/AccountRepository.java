package com.bank.account_service.domain.repository;

import com.bank.account_service.domain.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository {

    Mono<Account> save(Account account);

    Mono<Account> findById(String id);

    Flux<Account> findAll();

    Mono<Account> update(Account account);

    Mono<Void> deleteById(String id);
}

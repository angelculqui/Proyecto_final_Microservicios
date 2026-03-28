package com.bank.account_service.infrastructure.repository;

import com.bank.account_service.domain.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AccountMongoRepository extends ReactiveMongoRepository<Account, String> {

    Flux<Account> findByCustomerId(String customerId);

    // 🔥 NUEVO
    Flux<Account> findByHoldersContaining(String clientId);

    // 🔥 NUEVO
    Flux<Account> findBySignersContaining(String clientId);
}


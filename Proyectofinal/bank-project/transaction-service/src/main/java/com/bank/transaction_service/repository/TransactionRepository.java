package com.bank.transaction_service.repository;

import com.bank.transaction_service.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

    // Busca todas las transacciones de una cuenta
    Flux<Transaction> findByAccountId(String accountId);
}
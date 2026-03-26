package com.bank.transaction_service.service;

import com.bank.transaction_service.client.AccountClient; // 🔥 cliente para llamar a account-service
import com.bank.transaction_service.model.Transaction;
import com.bank.transaction_service.repository.TransactionRepository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final AccountClient accountClient; // 🔥 inyectamos el cliente

    // 🔥 Constructor con ambas dependencias
    public TransactionService(TransactionRepository repository,
                              AccountClient accountClient) {
        this.repository = repository;
        this.accountClient = accountClient;
    }

    // ============================================
    // ✅ CREAR TRANSACCIÓN CON VALIDACIÓN REAL
    // ============================================
    public Mono<Transaction> createTransaction(Transaction transaction) {

        // 🔥 llamamos al account-service para validar que la cuenta existe
        String accountResponse = accountClient.getAccountById(transaction.getAccountId());

        // ⚠️ validación simple (luego lo mejoramos con DTO)
        if (accountResponse == null || accountResponse.isEmpty()) {
            return Mono.error(new RuntimeException("Account not found"));
        }

        // 🔥 asignamos fecha actual
        transaction.setTimestamp(LocalDateTime.now());

        // 🔥 guardamos en Mongo
        return repository.save(transaction);
    }

    // ============================================
    // 📄 OBTENER TODAS LAS TRANSACCIONES
    // ============================================
    public Flux<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    // ============================================
    // 📄 OBTENER TRANSACCIONES POR CUENTA
    // ============================================
    public Flux<Transaction> getTransactionsByAccount(String accountId) {
        return repository.findByAccountId(accountId);
    }
}
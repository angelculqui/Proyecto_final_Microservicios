package com.bank.transaction_service.service;

import com.bank.transaction_service.client.AccountClient;
import com.bank.transaction_service.dto.AccountDTO;
import com.bank.transaction_service.model.Transaction;
import com.bank.transaction_service.repository.TransactionRepository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final AccountClient accountClient;

    public TransactionService(TransactionRepository repository,
                              AccountClient accountClient) {
        this.repository = repository;
        this.accountClient = accountClient;
    }

    public Mono<Transaction> createTransaction(Transaction transaction) {

        return accountClient.getAccountById(transaction.getAccountId())
                .flatMap(account -> validateAndApplyTransaction(account, transaction))
                .flatMap(updatedAccount -> accountClient.updateAccount(updatedAccount)
                        .then(repository.save(transaction)));
    }

    private Mono<AccountDTO> validateAndApplyTransaction(AccountDTO account, Transaction tx) {

        if (tx.getAmount() <= 0) {
            return Mono.error(new RuntimeException("Amount must be positive"));
        }

        switch (tx.getType()) {

            case "DEPOSIT":
                account.setBalance(account.getBalance() + tx.getAmount());
                break;

            case "WITHDRAW":
                if (account.getBalance() < tx.getAmount()) {
                    return Mono.error(new RuntimeException("Insufficient balance"));
                }
                account.setBalance(account.getBalance() - tx.getAmount());
                break;

            default:
                return Mono.error(new RuntimeException("Invalid transaction type"));
        }

        tx.setTimestamp(LocalDateTime.now());
        return Mono.just(account);
    }

    public Flux<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public Flux<Transaction> getTransactionsByAccount(String accountId) {
        return repository.findByAccountId(accountId);
    }
}

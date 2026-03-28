package com.bank.transaction_service.controller;

import com.bank.transaction_service.model.Transaction;
import com.bank.transaction_service.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) {
        return service.createTransaction(transaction);
    }

    @GetMapping
    public Flux<Transaction> getAllTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping("/account/{accountId}")
    public Flux<Transaction> getTransactionsByAccount(@PathVariable String accountId) {
        return service.getTransactionsByAccount(accountId);
    }
}

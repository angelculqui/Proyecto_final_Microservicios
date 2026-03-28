package com.bank.account_service.infrastructure.controller;

import com.bank.account_service.application.service.AccountService;
import com.bank.account_service.domain.model.Account;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // CREATE
    @PostMapping
    public Mono<Account> createAccount(@Valid @RequestBody Account account) {
        return accountService.createAccount(account);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Mono<Account> getAccountById(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    // GET ALL
    @GetMapping
    public Flux<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // 🔥 GET ACCOUNTS BY CLIENT ID (ESTE ES EL ENDPOINT QUE FALTABA)
    @GetMapping("/client/{clientId}")
    public Flux<Account> getAccountsByClient(@PathVariable String clientId) {
        return accountService.getAccountsByClient(clientId);
    }

    // UPDATE
    @PutMapping
    public Mono<Account> updateAccount(@Valid @RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public Mono<Void> deleteAccount(@PathVariable String id) {
        return accountService.deleteAccount(id);
    }
}


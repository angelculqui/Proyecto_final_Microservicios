package com.bank.account_service.infrastructure.controller;

import com.bank.account_service.application.service.AccountService;
import com.bank.account_service.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    @PostMapping
    public Mono<Account> createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public Mono<Account> getAccountById(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    @GetMapping
    public Flux<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping
    public Mono<Account> updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAccount(@PathVariable String id) {
        return accountService.deleteAccount(id);
    }
}
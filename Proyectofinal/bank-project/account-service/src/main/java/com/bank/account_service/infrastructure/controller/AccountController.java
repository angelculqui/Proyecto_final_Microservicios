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

    @PostMapping
    public Mono<Account> createAccount(@Valid @RequestBody Account account) {
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

    @GetMapping("/client/{clientId}")
    public Flux<Account> getAccountsByClient(@PathVariable String clientId) {
        return accountService.getAccountsByClient(clientId);
    }

    // 🔥 NUEVO: obtener saldo
    @GetMapping("/{id}/balance")
    public Mono<Double> getBalance(@PathVariable String id) {
        return accountService.getBalance(id);
    }

    // 🔥 NUEVO: obtener cuentas donde el cliente es titular o firmante
    @GetMapping("/related/{clientId}")
    public Flux<Account> getAccountsRelated(@PathVariable String clientId) {
        return accountService.getAccountsRelatedToClient(clientId);
    }

    @PutMapping
    public Mono<Account> updateAccount(@Valid @RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAccount(@PathVariable String id) {
        return accountService.deleteAccount(id);
    }
}



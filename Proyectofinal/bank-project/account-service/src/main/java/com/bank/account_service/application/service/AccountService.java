package com.bank.account_service.application.service;

import com.bank.account_service.domain.model.Account;
import com.bank.account_service.infrastructure.repository.AccountMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountMongoRepository accountRepository;

    public Mono<Account> createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Mono<Account> getAccountById(String id) {
        return accountRepository.findById(id);
    }

    public Flux<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Mono<Account> updateAccount(Account account) {
        return accountRepository.findById(account.getId())
                .flatMap(existing -> {
                    existing.setAccountNumber(account.getAccountNumber());
                    existing.setBalance(account.getBalance());
                    existing.setAccountType(account.getAccountType());
                    return accountRepository.save(existing);
                });
    }

    public Mono<Void> deleteAccount(String id) {
        return accountRepository.deleteById(id);
    }
}
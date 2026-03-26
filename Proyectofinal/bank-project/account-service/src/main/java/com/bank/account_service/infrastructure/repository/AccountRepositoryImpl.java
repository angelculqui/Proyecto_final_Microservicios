package com.bank.account_service.infrastructure.repository;

import com.bank.account_service.domain.model.Account;
import com.bank.account_service.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountMongoRepository mongoRepo;

    @Override
    public Mono<Account> save(Account account) {
        return mongoRepo.save(account);
    }

    @Override
    public Mono<Account> findById(String id) {
        return mongoRepo.findById(id);
    }

    @Override
    public Flux<Account> findAll() {
        return mongoRepo.findAll();
    }

    @Override
    public Mono<Account> update(Account account) {
        return mongoRepo.save(account);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return mongoRepo.deleteById(id);
    }
}
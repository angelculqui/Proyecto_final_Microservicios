package com.bank.account_service.infrastructure.repository;

import com.bank.account_service.domain.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMongoRepository extends ReactiveMongoRepository<Account, String> {
}
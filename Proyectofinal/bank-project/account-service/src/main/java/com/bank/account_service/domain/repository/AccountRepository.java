package com.bank.account_service.domain.repository;

import com.bank.account_service.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);

    Optional<Account> findById(String id);

    List<Account> findAll();

    Account update(Account account);

    void deleteById(String id);
}
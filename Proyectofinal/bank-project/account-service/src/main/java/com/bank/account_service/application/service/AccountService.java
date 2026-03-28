package com.bank.account_service.application.service;

import com.bank.account_service.domain.model.Account;
import com.bank.account_service.infrastructure.repository.AccountMongoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountMongoRepository accountRepository;
    private final WebClient webClient;

    // ============================
    // CREATE ACCOUNT
    // ============================
    public Mono<Account> createAccount(Account account) {

        return getCustomerFromService(account.getCustomerId())
                .flatMap(customer -> validateBusinessRules(account, customer.getType()))
                .then(validateDuplicateAccountNumber(account))
                .then(accountRepository.save(account));
    }

    // ============================
    // GET BY ID
    // ============================
    public Mono<Account> getAccountById(String id) {
        return accountRepository.findById(id);
    }

    // ============================
    // GET ALL
    // ============================
    public Flux<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // ============================
    // UPDATE ACCOUNT
    // ============================
    public Mono<Account> updateAccount(Account account) {
        return accountRepository.findById(account.getId())
                .switchIfEmpty(Mono.error(new RuntimeException("Account not found")))
                .flatMap(existing -> {

                    existing.setAccountNumber(account.getAccountNumber());
                    existing.setBalance(account.getBalance());
                    existing.setAccountType(account.getAccountType());
                    existing.setMaxTransactionsWithoutFee(account.getMaxTransactionsWithoutFee());
                    existing.setTransactionsThisMonth(account.getTransactionsThisMonth());

                    return accountRepository.save(existing);
                });
    }

    // ============================
    // DELETE ACCOUNT
    // ============================
    public Mono<Void> deleteAccount(String id) {
        return accountRepository.deleteById(id);
    }

    // ============================================================
    // VALIDACIONES DE NEGOCIO (PARTE 1)
    // ============================================================

    // 1. Obtener cliente desde customer-service
    private Mono<CustomerResponse> getCustomerFromService(String customerId) {
        return webClient.get()
                .uri("http://customer-service:8081/api/customers/" + customerId)
                .retrieve()
                .bodyToMono(CustomerResponse.class)
                .switchIfEmpty(Mono.error(new RuntimeException("Customer does not exist")));
    }

    // 2. Validar duplicidad de accountNumber
    private Mono<Void> validateDuplicateAccountNumber(Account account) {
        return accountRepository.findAll()
                .filter(a -> a.getAccountNumber().equals(account.getAccountNumber()))
                .hasElements()
                .flatMap(exists -> exists
                        ? Mono.error(new RuntimeException("Account number already exists"))
                        : Mono.empty());
    }

    // 3. Validar reglas de negocio según tipo de cliente
    private Mono<Void> validateBusinessRules(Account newAccount, String customerType) {

        return accountRepository.findAll()
                .filter(acc -> acc.getCustomerId().equals(newAccount.getCustomerId()))
                .collectList()
                .flatMap(existingAccounts -> {

                    if (customerType.equals("PERSONAL")) {
                        return validatePersonalCustomer(existingAccounts, newAccount);
                    }

                    if (customerType.equals("BUSINESS")) {
                        return validateBusinessCustomer(existingAccounts, newAccount);
                    }

                    return Mono.error(new RuntimeException("Invalid customer type"));
                });
    }

    // Reglas para clientes PERSONALES
    private Mono<Void> validatePersonalCustomer(List<Account> existing, Account newAcc) {

        boolean hasSavings = existing.stream().anyMatch(a -> a.getAccountType().equals("SAVINGS"));
        boolean hasChecking = existing.stream().anyMatch(a -> a.getAccountType().equals("CHECKING"));

        switch (newAcc.getAccountType()) {

            case "SAVINGS":
                if (hasSavings)
                    return Mono.error(new RuntimeException("Personal customer already has a savings account"));
                break;

            case "CHECKING":
                if (hasChecking)
                    return Mono.error(new RuntimeException("Personal customer already has a checking account"));
                break;

            case "FIXED":
                return Mono.empty(); // permitido
        }

        return Mono.empty();
    }

    // Reglas para clientes EMPRESARIALES
    private Mono<Void> validateBusinessCustomer(List<Account> existing, Account newAcc) {

        switch (newAcc.getAccountType()) {

            case "SAVINGS":
                return Mono.error(new RuntimeException("Business customers cannot have savings accounts"));

            case "FIXED":
                return Mono.error(new RuntimeException("Business customers cannot have fixed-term accounts"));

            case "CHECKING":
                return Mono.empty(); // permitido
        }

        return Mono.empty();
    }

    // Clase para mapear respuesta del customer-service
    @Data
    public static class CustomerResponse {
        private String id;
        private String name;
        private String documentNumber;
        private String type;
    }
}


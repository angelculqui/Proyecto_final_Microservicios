package com.bank.account_service.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "accounts")
public abstract class Account {
    @Id
    private String id;
    private String customerId; // ID del cliente propietario
    private String accountNumber; // account identifier
    private String accountType; // SAVINGS, CHECKING, FIXED
    private Double balance;
    private Integer maxTransactionsWithoutFee;
    private Integer transactionsThisMonth;

    // Explicit getters/setters for static analysis tools (Lombok still generates at compile time)
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
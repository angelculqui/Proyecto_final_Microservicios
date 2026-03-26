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
    private Double balance;
    private Integer maxTransactionsWithoutFee;
    private Integer transactionsThisMonth;
}
package com.bank.transaction_service.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private String id;
    private String customerId;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private Integer maxTransactionsWithoutFee;
    private Integer transactionsThisMonth;
}

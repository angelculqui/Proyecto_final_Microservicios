package com.bank.transaction_service.dto;

import lombok.Data;

@Data
public class TransactionDTO {

    // 🔥 ID de la cuenta asociada
    private String accountId;

    // 🔥 monto de la transacción
    private double amount;

    // 🔥 tipo: DEPOSIT o WITHDRAW
    private String type;
}
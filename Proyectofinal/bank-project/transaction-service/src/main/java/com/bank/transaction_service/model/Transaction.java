package com.bank.transaction_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;                  // ID único generado por MongoDB

    @NotNull
    private String accountId;           // ID de la cuenta asociada a la transacción

    @Positive
    private double amount;              // Monto de la transacción

    @NotNull
    private String type;                // Tipo de transacción: DEBIT / CREDIT

    private LocalDateTime timestamp;    // Fecha y hora de la transacción
}
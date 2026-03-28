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
    private String id;

    @NotNull
    private String accountId;

    @Positive
    private double amount;

    @NotNull
    private String type; // DEPOSIT / WITHDRAW

    private LocalDateTime timestamp;
}

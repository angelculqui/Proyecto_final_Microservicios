package com.bank.account_service.domain.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "fixed_term_accounts")
public class FixedTermAccount extends Account {
    private LocalDate allowedTransactionDay;
}
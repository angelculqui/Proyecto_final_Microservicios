package com.bank.account_service.domain.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "savings_accounts")
public class SavingsAccount extends Account {
    private static final double MAINTENANCE_FEE = 0.0;
}
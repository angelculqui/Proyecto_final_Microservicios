package com.bank.account_service.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "savings_accounts")
public class SavingsAccount extends Account {
    private static final double MAINTENANCE_FEE = 0.0;
}
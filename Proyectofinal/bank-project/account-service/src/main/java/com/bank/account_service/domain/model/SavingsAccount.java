package com.bank.account_service.domain.model;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "savings_accounts")
public class SavingsAccount extends Account {

    @Min(value = 0, message = "Maintenance fee cannot be negative")
    private Double maintenanceFee = 0.0;

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }
}

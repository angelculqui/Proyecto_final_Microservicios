package com.bank.account_service.domain.model;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "checking_accounts")
public class CheckingAccount extends Account {

    @Min(value = 0, message = "Maintenance fee cannot be negative")
    private Double maintenanceFee;

    @Override
    public String getAccountType() {
        return "CHECKING";
    }
}

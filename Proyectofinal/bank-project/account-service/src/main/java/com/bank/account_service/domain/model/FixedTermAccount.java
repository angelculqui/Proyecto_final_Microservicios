package com.bank.account_service.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "fixed_term_accounts")
public class FixedTermAccount extends Account {

    @NotNull(message = "Allowed transaction day is required")
    private LocalDate allowedTransactionDay;

    @Override
    public String getAccountType() {
        return "FIXED";
    }
}

package com.bank.account_service.domain.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "checking_accounts")
public class CheckingAccount extends Account {
    private Double maintenanceFee;
}
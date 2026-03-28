package com.bank.credit_service.domain.dto;

import lombok.Data;

@Data
public class PayCreditRequest {
    private String creditId;
    private double amount;
}

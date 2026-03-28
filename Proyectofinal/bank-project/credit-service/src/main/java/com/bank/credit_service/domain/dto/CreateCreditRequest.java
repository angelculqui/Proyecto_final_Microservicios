package com.bank.credit_service.domain.dto;

import lombok.Data;

@Data
public class CreateCreditRequest {
    private String clientId;
    private String creditType; // PERSONAL o BUSINESS
    private double amount;
    private double interestRate;
}

package com.bank.credit_service.domain.dto;

import lombok.Data;

@Data
public class CreateCreditCardRequest {
    private String clientId;
    private String cardType; // PERSONAL o BUSINESS
    private String cardNumber;
    private double creditLimit;
    private String expirationDate; // formato YYYY-MM-DD
}

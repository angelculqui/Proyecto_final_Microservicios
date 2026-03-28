package com.bank.credit_service.domain.dto;

import lombok.Data;

@Data
public class ConsumeCreditCardRequest {
    private String cardId;
    private double amount;
}

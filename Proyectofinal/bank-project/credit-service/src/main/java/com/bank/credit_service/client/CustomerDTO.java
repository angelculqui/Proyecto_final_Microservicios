package com.bank.credit_service.client;

import lombok.Data;

@Data
public class CustomerDTO {
    private String id;
    private String name;
    private String documentNumber;
    private String type; // PERSONAL / BUSINESS
}

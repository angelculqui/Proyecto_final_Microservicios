package com.bank.credit_service.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad que representa una tarjeta de crédito.
 * Puede ser personal o empresarial.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "credit_cards") // Colección en MongoDB
public class CreditCard {

    @Id
    private String id; // Identificador único de la tarjeta

    private String clientId; // Id del cliente que posee la tarjeta

    private String cardType; // Tipo de tarjeta: PERSONAL o EMPRESARIAL

    private String cardNumber; // Número de tarjeta (formato seguro)

    private BigDecimal creditLimit; // Límite máximo de crédito

    private BigDecimal currentBalance; // Balance actual consumido

    private LocalDate expirationDate; // Fecha de expiración

    private boolean active; // Indica si la tarjeta está activa
}
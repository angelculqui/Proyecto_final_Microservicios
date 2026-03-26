package com.bank.credit_service.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad que representa un crédito otorgado por el banco.
 * Puede ser un crédito personal o empresarial.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "credits") // Colección en MongoDB
public class Credit {

    @Id
    private String id; // Identificador único del crédito

    private String clientId; // Id del cliente que solicitó el crédito

    private String creditType; // Tipo de crédito: PERSONAL o EMPRESARIAL

    private BigDecimal amount; // Monto del crédito

    private BigDecimal balance; // Saldo pendiente del crédito

    private double interestRate; // Tasa de interés del crédito

    private LocalDate startDate; // Fecha de inicio del crédito

    private LocalDate dueDate; // Fecha de vencimiento del crédito

    private boolean paidOff; // Indica si el crédito está totalmente pagado
}
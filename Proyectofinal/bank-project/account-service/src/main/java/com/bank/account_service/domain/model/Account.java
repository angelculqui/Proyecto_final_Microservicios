package com.bank.account_service.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "accounts")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "accountType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SavingsAccount.class, name = "SAVINGS"),
        @JsonSubTypes.Type(value = CheckingAccount.class, name = "CHECKING"),
        @JsonSubTypes.Type(value = FixedTermAccount.class, name = "FIXED")
})
public abstract class Account {

    @Id
    private String id;

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    // Este campo ya NO se serializa dos veces
    @JsonIgnore
    private String accountType;

    @Min(value = 0, message = "Balance cannot be negative")
    private Double balance;

    @Min(value = 0, message = "Max transactions without fee must be >= 0")
    private Integer maxTransactionsWithoutFee;

    @Min(value = 0, message = "Transactions this month must be >= 0")
    private Integer transactionsThisMonth;

    // Getter virtual para que Jackson siga usando el tipo correcto
    public abstract String getAccountType();
}

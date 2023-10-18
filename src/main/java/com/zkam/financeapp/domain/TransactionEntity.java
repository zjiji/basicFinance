package com.zkam.financeapp.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class TransactionEntity extends GeneratedIdEntity {
    @NotNull
    private Long customerAccountId;
    @NotNull
    private BigDecimal amount;
    private String comment;

    public TransactionEntity(Long customerAccountId, BigDecimal amount) {
        this.customerAccountId = customerAccountId;
        this.amount = amount;
    }

    public TransactionEntity() {

    }
}

package com.zkam.financeapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Data
public class TransactionEntity {
    @NotNull
    private Long customerAccountId;
    @NotNull
    private BigDecimal amount;
    private String comment;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public TransactionEntity(Long customerAccountId, BigDecimal amount) {
        this.customerAccountId = customerAccountId;
        this.amount = amount;
    }

    public TransactionEntity() {

    }

}

package com.zkam.financeapp.domain;

import lombok.Data;
import lombok.Getter;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Data
public class CustomerAccountEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @OneToOne
    private TransactionEntity transactionEntity;


    public CustomerAccountEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        if (transactionEntity == null) return BigDecimal.ZERO;
        return transactionEntity.getAmount();
    }
}

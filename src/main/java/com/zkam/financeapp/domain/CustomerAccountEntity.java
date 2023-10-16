package com.zkam.financeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import jakarta.persistence.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Getter
@Entity
@Data
public class CustomerAccountEntity extends GeneratedIdEntity {

    @OneToOne
    private TransactionEntity transactionEntity;

    public CustomerAccountEntity() {
    }
    public BigDecimal getAmount() {
        if (transactionEntity == null) return BigDecimal.ZERO;
        return transactionEntity.getAmount();
    }
}

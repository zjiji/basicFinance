package com.zkam.financeapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Entity
@Data
public class UserEntity extends GeneratedIdEntity {

    @NotBlank
    private String name;
    @NotBlank
    private String surName;

    @OneToMany
    List<CustomerAccountEntity> customerAccounts = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public BigDecimal getBalance() {
        return customerAccounts.stream()
                .map(CustomerAccountEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

package com.zkam.financeapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Data
public class UserEntity {

    @NotBlank
    private String name;
    @NotBlank
    private String surName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    List<CustomerAccountEntity> customerAccounts = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return customerAccounts.stream()
                .map(CustomerAccountEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

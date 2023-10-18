package com.zkam.financeapp.api;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Tag(name = "User Resource", description = "Resource to hold user (customer) information.")
public class User {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "User Name")
    private String name;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "User Surname")
    private String surname;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "User Id")
    private Long userId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "User accounts")
    private List<CustomerAccount> accounts = new ArrayList<>();
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Balance of the accounts")
    private BigDecimal balance;

    public User() {
    }

    public User(String name, String surName) {
        this.name = name;
        this.surname = surName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setAccounts(List<CustomerAccount> accounts) {
        this.accounts = accounts;
    }
}

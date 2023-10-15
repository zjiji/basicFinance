package com.zkam.financeapp.api;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Tag(name = "Customer Account Resource", description = "Resource to holds customer (a.k.a user) account information.")
public class CustomerAccount {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Customer Account Id")
    private Long accountId;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Transaction amount of the account.")
    private BigDecimal transactionAmount;
    public CustomerAccount() {
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

}

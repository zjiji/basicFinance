package com.zkam.financeapp.mapping;

import com.zkam.financeapp.api.CustomerAccount;
import com.zkam.financeapp.domain.CustomerAccountEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomerAccountMapper {
    public CustomerAccount mapToApiObject(CustomerAccountEntity customerAccountEntity) {
        if (customerAccountEntity == null) return null;
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setAccountId(customerAccountEntity.getId());
        customerAccount.setTransactionAmount(customerAccountEntity.getTransactionEntity() != null ? customerAccountEntity.getTransactionEntity().getAmount() : BigDecimal.ZERO);
        return customerAccount;
    }
}

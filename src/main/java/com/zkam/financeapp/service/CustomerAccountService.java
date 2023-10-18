package com.zkam.financeapp.service;

import com.zkam.financeapp.domain.CustomerAccountEntity;
import com.zkam.financeapp.domain.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class CustomerAccountService implements EntityService<CustomerAccountEntity> {
    @Autowired
    CustomerAccountRepository customerAccountRepository;
    @Autowired
    TransactionService transactionService;

    public CustomerAccountEntity createAccount(BigDecimal initialCredit) {
        CustomerAccountEntity customerAccount = new CustomerAccountEntity();

        if (!Objects.equals(initialCredit, BigDecimal.ZERO)) {
            customerAccount.setTransactionEntity(transactionService.create(new TransactionEntity(customerAccount.getId(), initialCredit)));
        }
        return create(customerAccount);
    }

    @Override
    public CustomerAccountEntity create(CustomerAccountEntity entity) {
        entity = customerAccountRepository.save(entity);
        return entity;
    }

    @Override
    public CustomerAccountEntity delete(CustomerAccountEntity entity) {
        customerAccountRepository.delete(entity);
        return entity;
    }
}

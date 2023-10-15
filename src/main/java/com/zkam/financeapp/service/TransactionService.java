package com.zkam.financeapp.service;

import com.zkam.financeapp.domain.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public TransactionEntity create(TransactionEntity transactionEntity) {
        return transactionRepository.save(transactionEntity);
    }
}

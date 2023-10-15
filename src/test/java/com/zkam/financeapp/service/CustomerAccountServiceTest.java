package com.zkam.financeapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class CustomerAccountServiceTest {
    @InjectMocks
    CustomerAccountService customerAccountService;
    @Mock
    CustomerAccountRepository customerAccountRepository;
    @Mock
    TransactionService transactionService;

    @Test
    void testCreateAccountWithNoTransaction() {

        customerAccountService.createAccount(BigDecimal.ZERO);

        verify(customerAccountRepository).save(any());
        verifyNoInteractions(transactionService);

    }

    @Test
    void testCreateAccount() {
        customerAccountService.createAccount(BigDecimal.valueOf(100));

        verify(transactionService).create(any());
        verify(customerAccountRepository).save(any());

    }
}

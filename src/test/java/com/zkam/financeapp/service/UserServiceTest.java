package com.zkam.financeapp.service;

import com.zkam.financeapp.domain.CustomerAccountEntity;
import com.zkam.financeapp.domain.TransactionEntity;
import com.zkam.financeapp.domain.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    @Mock
    CustomerAccountService accountService;
    UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity("arya", "stark");
        userEntity.setId(1L);
        CustomerAccountEntity customerAccountEntity = new CustomerAccountEntity();
        customerAccountEntity.setId(2L);
        userEntity.getCustomerAccounts().add(customerAccountEntity);
    }

    @Test
    void testCreate() {
        UserEntity userEntity = new UserEntity("sansa", "stark");

        when(userRepository.save(userEntity)).thenReturn(userEntity);
        UserEntity result = userService.create(userEntity);

        verify(userRepository).save(userEntity);
        assertThat(result.getName()).isEqualTo(userEntity.getName());
    }

    @Test
    void testCreateAccount() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        assertThat(userEntity.getBalance()).isEqualTo(BigDecimal.valueOf(0));
        assertThat(userEntity.getCustomerAccounts()).size().isEqualTo(1);

        CustomerAccountEntity newAccount = new CustomerAccountEntity();
        newAccount.setId(100L);
        newAccount.setTransactionEntity(new TransactionEntity(newAccount.getId(), BigDecimal.valueOf(100)));
        when(accountService.createAccount(BigDecimal.valueOf(100))).thenReturn(newAccount);
        UserEntity result = userService.createAccount(userEntity.getId(), BigDecimal.valueOf(100));

        verify(userRepository).save(userEntity);
        verify(accountService).createAccount(BigDecimal.valueOf(100));
        assertThat(result.getName()).isEqualTo(userEntity.getName());
        assertThat(result.getCustomerAccounts()).size().isEqualTo(2);
        assertThat(result.getBalance()).isEqualTo(BigDecimal.valueOf(100));

    }

    @Test
    void testCreateAccountNonExistingUser() {
        assertThatThrownBy(() -> userService.createAccount(2L, BigDecimal.ONE))
                .hasMessage("Could not find user with id " + 2L);
    }
}

package com.zkam.financeapp.service;

import com.zkam.financeapp.domain.CustomerAccountEntity;
import com.zkam.financeapp.domain.UserEntity;
import com.zkam.financeapp.validation.UserNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService implements EntityService<UserEntity> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerAccountService accountService;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public UserEntity create(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity delete(UserEntity entity) {
        userRepository.delete(entity);
        return entity;
    }

    public UserEntity createAccount(Long userId, BigDecimal initialCredit) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotfoundException(userId));
        CustomerAccountEntity account = accountService.createAccount(initialCredit);

        userEntity.getCustomerAccounts().add(account);
        userRepository.save(userEntity);
        return userEntity;
    }

}

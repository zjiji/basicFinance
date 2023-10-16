package com.zkam.financeapp.mapping;

import com.zkam.financeapp.api.User;
import com.zkam.financeapp.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    CustomerAccountMapper customerAccountMapper;

    public User mapToApiObject(UserEntity userEntity) {
        if (userEntity == null) return null;
        User user = new User();
        user.setName(userEntity.getName());
        user.setSurname(userEntity.getSurName());
        user.setUserId(userEntity.getId());
        return user;
    }

    public User mapToDetailedApiObject(UserEntity userEntity) {
        if (userEntity == null) return null;
        User user = new User();
        user.setName(userEntity.getName());
        user.setSurname(userEntity.getSurName());
        user.setUserId(userEntity.getId());
        user.setBalance(userEntity.getBalance());
        user.setAccounts(userEntity.getCustomerAccounts().stream()
                .map(ac -> customerAccountMapper.mapToApiObject(ac))
                .collect(Collectors.toList()));
        return user;
    }

    public UserEntity mapToEntity(User user) {
        if (user == null) return null;
        return new UserEntity(user.getName(), user.getSurname());
    }


}

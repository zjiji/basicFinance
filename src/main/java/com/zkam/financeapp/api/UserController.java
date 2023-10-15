package com.zkam.financeapp.api;

import com.zkam.financeapp.mapping.CustomerAccountMapper;
import com.zkam.financeapp.mapping.UserMapper;
import com.zkam.financeapp.service.CustomerAccountService;
import com.zkam.financeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CustomerAccountService customerAccountService;
    @Autowired
    CustomerAccountMapper customerAccountMapper;

    @GetMapping("/users")
    List<User> listAllUsers() {
        return userService.findAll()
                .stream()
                .map(entity -> userMapper.mapToApiObject(entity))
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    User getById(@PathVariable Long userId) {
        return userMapper.mapToApiObject(userService.findByUserId(userId));
    }

    @PostMapping("/user/{name},{surName}")
    User create(@PathVariable String name, @PathVariable String surName) {
        return userMapper.mapToApiObject(userService.create(userMapper.mapToEntity(new User(name, surName))));
    }

    @PostMapping("/create-account/{userId},{initialCredit}")
    User createAccount(@PathVariable Long userId, Optional<BigDecimal> initialCredit) {
        return userMapper.mapToDetailedApiObject(userService.createAccount(userId, initialCredit.orElse(BigDecimal.ZERO)));
    }

    @GetMapping("/user-accounts")
    List<User> listAllUserInfo() {
        return userService.findAll()
                .stream()
                .map(entity -> userMapper.mapToDetailedApiObject(entity))
                .collect(Collectors.toList());
    }

}

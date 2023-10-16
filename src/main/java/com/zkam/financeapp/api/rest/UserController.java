package com.zkam.financeapp.api.rest;

import com.zkam.financeapp.api.User;
import com.zkam.financeapp.domain.UserEntity;
import com.zkam.financeapp.mapping.CustomerAccountMapper;
import com.zkam.financeapp.mapping.UserMapper;
import com.zkam.financeapp.service.CustomerAccountService;
import com.zkam.financeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<User> listAllUsers() {
        return userService.findAll()
                .stream()
                .map(entity -> userMapper.mapToApiObject(entity))
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public User getById(@PathVariable Long userId) {
        return userMapper.mapToDetailedApiObject(userService.findByUserId(userId));
    }

    @PostMapping("/user/{name},{surname}")
    public User create(@PathVariable String name, @PathVariable String surname) {
        return userMapper.mapToApiObject(userService.create(userMapper.mapToEntity(new User(name, surname))));
    }

    @PostMapping("/user/{user}")
    public User create(@PathVariable User user) {
        return userMapper.mapToApiObject(userService.create(userMapper.mapToEntity(user)));
    }

    @DeleteMapping("/user/{id}")
    public User delete(@PathVariable Long id) {
        UserEntity byUserId = userService.findByUserId(id);
        return userMapper.mapToApiObject(userService.delete(byUserId));
    }

    @PostMapping("/create-account/{userId},{initialCredit}")
    public User createAccount(@PathVariable Long userId, Optional<BigDecimal> initialCredit) {
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

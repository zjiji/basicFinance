package com.zkam.financeapp.api.rest;

import com.zkam.financeapp.api.User;
import com.zkam.financeapp.domain.UserEntity;
import com.zkam.financeapp.mapping.CustomerAccountMapper;
import com.zkam.financeapp.mapping.UserMapper;
import com.zkam.financeapp.service.CustomerAccountService;
import com.zkam.financeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Rest controller for User/Customer Account endpoints
 */
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

    /**
     * Returns all users with basic information
     *
     * @return List of users
     */
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> listAllUsers() {
        return userService.findAll()
                .stream()
                .map(entity -> userMapper.mapToApiObject(entity))
                .collect(Collectors.toList());
    }

    /**
     * Returns detailed information of the user with userId
     *
     * @param userId id of the User
     * @return User
     */
    @GetMapping("/user/{userId}")
    public User getById(@PathVariable Long userId) {
        return userMapper.mapToDetailedApiObject(userService.findByUserId(userId));
    }

    /**
     * Creates a new user
     *
     * @param name    name of the user
     * @param surname surname of the user
     * @return new User
     */
    @PostMapping("/user/{name},{surname}")
    public User create(@PathVariable String name, @PathVariable String surname) {
        return userMapper.mapToApiObject(userService.create(userMapper.mapToEntity(new User(name, surname))));
    }

    /**
     * Creates a new user with User DTO
     *
     * @param user new User to be created
     * @return new user
     */
    @PostMapping(value = "/user/{user}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@PathVariable User user) {
        return userMapper.mapToApiObject(userService.create(userMapper.mapToEntity(user)));
    }

    /**
     * Deletes a user with given identifier
     *
     * @param id user Id
     * @return deleted user
     */
    @DeleteMapping("/user/{id}")
    public User delete(@PathVariable Long id) {
        UserEntity byUserId = userService.findByUserId(id);
        return userMapper.mapToApiObject(userService.delete(byUserId));
    }

    /**
     * Creates a new user account
     *
     * @param userId        identifier of the user
     * @param initialCredit initial transaction amount
     * @return User information with accounts and balances
     */
    @PostMapping("/create-account/{userId},{initialCredit}")
    public User createAccount(@PathVariable Long userId, Optional<BigDecimal> initialCredit) {
        return userMapper.mapToDetailedApiObject(userService.createAccount(userId, initialCredit.orElse(BigDecimal.ZERO)));
    }

    /**
     * Lists all user info together with accounts and balance
     *
     * @return List of users
     */
    @GetMapping("/user-accounts")
    List<User> listAllUserInfo() {
        return userService.findAll()
                .stream()
                .map(entity -> userMapper.mapToDetailedApiObject(entity))
                .collect(Collectors.toList());
    }

}

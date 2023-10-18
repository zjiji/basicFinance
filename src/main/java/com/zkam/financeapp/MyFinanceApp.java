package com.zkam.financeapp;

import com.zkam.financeapp.domain.UserEntity;
import com.zkam.financeapp.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyFinanceApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyFinanceApp.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) {
        userRepository.save(new UserEntity("Jane", "Doe"));
        userRepository.save(new UserEntity("John", "Doe"));
        userRepository.save(new UserEntity("John", "Wick"));
        userRepository.save(new UserEntity("Jane", "Austen"));
        userRepository.save(new UserEntity("Jack", "Bauer"));
        userRepository.save(new UserEntity("Tony", "Almeida"));
    }

}

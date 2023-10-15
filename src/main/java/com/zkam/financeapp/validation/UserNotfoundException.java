package com.zkam.financeapp.validation;

import jakarta.persistence.EntityNotFoundException;

public class UserNotfoundException extends EntityNotFoundException {
    public UserNotfoundException(Long id) {
        super("Could not find user with id " + id);
    }
}




package com.kapilsony.libraryservice.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String msg) {
        super(msg);
    }

}
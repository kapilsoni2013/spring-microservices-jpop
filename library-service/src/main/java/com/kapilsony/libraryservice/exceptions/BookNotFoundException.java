package com.kapilsony.libraryservice.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String msg) {
        super(msg);
    }

}
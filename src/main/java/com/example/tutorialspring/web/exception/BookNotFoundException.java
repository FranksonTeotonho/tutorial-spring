package com.example.tutorialspring.web.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}

package com.app.main.exception.custom;


public class APIExceptions extends RuntimeException {
    public APIExceptions() {}
    public APIExceptions(String message) {
        super(message);
    }
}

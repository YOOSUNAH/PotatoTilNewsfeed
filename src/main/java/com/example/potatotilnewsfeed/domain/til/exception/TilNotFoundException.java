package com.example.potatotilnewsfeed.domain.til.exception;

public class TilNotFoundException extends RuntimeException {

    public TilNotFoundException(String message) {
        super(message);
    }
}

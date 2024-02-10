package com.example.potatotilnewsfeed.domain.user.exception;

public class UserNotFoundException extends RuntimeException{
    public  UserNotFoundException(String message){
        super(message);
    }
}

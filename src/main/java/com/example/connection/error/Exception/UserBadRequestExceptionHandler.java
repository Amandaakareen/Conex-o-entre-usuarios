package com.example.connection.error.Exception;

public class UserBadRequestExceptionHandler extends RuntimeException {
    
    public UserBadRequestExceptionHandler(String massage){
        super(massage);
    }
}

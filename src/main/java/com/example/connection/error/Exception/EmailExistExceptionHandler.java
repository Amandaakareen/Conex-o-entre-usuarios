package com.example.connection.error.Exception;

public class EmailExistExceptionHandler extends RuntimeException {
    
    public EmailExistExceptionHandler(String massage){
        super(massage);
    }
}
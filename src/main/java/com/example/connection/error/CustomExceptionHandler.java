package com.example.connection.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.connection.error.Exception.ConnectionNotFoudExceptionHandler;
import com.example.connection.error.Exception.EmailExistExceptionHandler;
import com.example.connection.error.Exception.UserBadRequestExceptionHandler;
import com.example.connection.error.Exception.UserNotFoudExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(UserBadRequestExceptionHandler.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorMessage> userBadRequestException(UserBadRequestExceptionHandler exception ,  WebRequest request){
        log.error("BAD_REQUEST", exception);
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(exception.getMessage());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorMessage);
    }
  

    @ExceptionHandler(EmailExistExceptionHandler.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorMessage> emailExistException(EmailExistExceptionHandler exception ,  WebRequest request){
        log.error("NOT_FOUND", exception);
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(exception.getMessage());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorMessage);
    }
    
    @ExceptionHandler(UserNotFoudExceptionHandler.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorMessage> userNotFoud(UserNotFoudExceptionHandler exception ,  WebRequest request){
        log.error("NOT_FOUND", exception);
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(exception.getMessage());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorMessage);
    }

    @ExceptionHandler(ConnectionNotFoudExceptionHandler.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorMessage> ConnectionNotFoud(ConnectionNotFoudExceptionHandler exception ,  WebRequest request){
        log.error("NOT_FOUND", exception);
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(exception.getMessage());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorMessage);
    }

}

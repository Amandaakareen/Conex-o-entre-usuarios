package com.example.connection.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.connection.UserEntity;
import com.example.connection.dto.LoginUserDTO;
import com.example.connection.dto.RegisterUserDTO;
import com.example.connection.error.Exception.EmailExistExceptionHandler;
import com.example.connection.error.Exception.UserNotFoudExceptionHandler;
import com.example.connection.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    
    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody @Valid RegisterUserDTO registerUserDTO){
        try{
            userService.saveUser(UserEntity.mapTo(registerUserDTO));
            return  ResponseEntity.status(HttpStatus.CREATED).build();
            
        }catch(EmailExistExceptionHandler e){
            throw new EmailExistExceptionHandler(e.getMessage());
        } 
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid LoginUserDTO loginUserDTO ){
        try{
            String token = userService.verifyUser(loginUserDTO.getEmail(), loginUserDTO.getPassword());
            return ResponseEntity.ok(token);

        }catch(UserNotFoudExceptionHandler e){
            throw new UserNotFoudExceptionHandler(e.getMessage());
        }
    }

   
}

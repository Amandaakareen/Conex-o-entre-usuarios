package com.example.connection.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.connection.UserEntity;
import com.example.connection.dto.RegisterUserDTO;
import com.example.connection.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    
    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterUserDTO registerUserDTO){

        try{
            String user =userService.saveUser(UserEntity.mapTo(registerUserDTO));
            return ResponseEntity.ok(user);
            
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
        
    }
}

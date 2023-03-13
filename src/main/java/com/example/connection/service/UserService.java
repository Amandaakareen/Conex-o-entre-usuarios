package com.example.connection.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.connection.UserEntity;
import com.example.connection.md5.Cryptography;
import com.example.connection.repository.ConnectionRepository;
import com.example.connection.repository.UserRepository;

@Service
public class UserService {
    
    UserRepository userRepository;
    ConnectionRepository connectionRepository;

    public UserService(ConnectionRepository connectionRepository, UserRepository userRepository){
        this.connectionRepository = connectionRepository;
        this.userRepository = userRepository;
    }

    public String saveUser(UserEntity user){
        List<UserEntity> verifyUser = userRepository.findbyEmail(user.getEmail());
        if(verifyUser.isEmpty()){
            String passwordCryptography = Cryptography.cryptography(user.getPassword());
            user.setPassword(passwordCryptography);
            userRepository.save(user);
            return "cadastrado com sucesso";
        }
        return "essa email já existe";
        
    }
}

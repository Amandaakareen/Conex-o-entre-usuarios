package com.example.connection.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.connection.UserEntity;
import com.example.connection.error.Exception.EmailExistExceptionHandler;
import com.example.connection.error.Exception.UserNotFoudExceptionHandler;
import com.example.connection.md5.Cryptography;
import com.example.connection.repository.ConnectionRepository;
import com.example.connection.repository.UserRepository;

@Service
public class UserService {
    
    UserRepository userRepository;
    ConnectionRepository connectionRepository;
    jwtService jwtService;

    public UserService(ConnectionRepository connectionRepository, 
    UserRepository userRepository,
    jwtService jwtService){
        this.connectionRepository = connectionRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public void saveUser(UserEntity user){
        Optional<UserEntity> verifyUser = userRepository.findbyEmail(user.getEmail());
        if(verifyUser.isEmpty()){
            String passwordCryptography = Cryptography.cryptography(user.getPassword());
            user.setPassword(passwordCryptography);
            userRepository.save(user);
            
        }
        throw new EmailExistExceptionHandler("essa email já existe");
        
    }

    public String verifyUser(String email, String password){

        String passwordCryptography = Cryptography.cryptography(password);

        Optional<UserEntity> verifyUser = userRepository.findbyEmailbySenha(email,passwordCryptography);
        
        if(verifyUser.isEmpty()){
            throw new UserNotFoudExceptionHandler("Usuário não encontrado");
        }
        UserEntity userEntity = verifyUser.get();
        return jwtService.generateToken(userEntity.getId().toString()); 
        
    }

    public Optional<UserEntity> verifyUserById(Long idUserConnection) {
        Optional<UserEntity> user = userRepository.findById(idUserConnection);
        return user;
    }
}

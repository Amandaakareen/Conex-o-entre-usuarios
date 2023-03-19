package com.example.connection.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.connection.ConnectionEntity;
import com.example.connection.UserEntity;
import com.example.connection.dto.IdUserConnectionDTO;
import com.example.connection.error.Exception.ConnectionNotFoudExceptionHandler;
import com.example.connection.error.Exception.UserBadRequestExceptionHandler;
import com.example.connection.error.Exception.UserNotFoudExceptionHandler;
import com.example.connection.repository.ConnectionRepository;

@Service
public class ConnectionService {
    
    ConnectionRepository connectionRepository;
    UserService userService;

    public ConnectionService(UserService userService, ConnectionRepository connectionRepository){
        this.userService = userService;
        this.connectionRepository = connectionRepository;
    }

    public void connectUsers(Long id, Long idUserConnection){
        Optional<UserEntity> getUser = userService.verifyUserById(id);

        if(getUser.isEmpty()){
            throw new UserNotFoudExceptionHandler("Usuário principal não existe");
        }

        UserEntity user = getUser.get();
        if(user.getId() == idUserConnection ){
            throw new UserBadRequestExceptionHandler( "Conexão não permitida com mesmo usuario" );
        }

        Optional<UserEntity> verifyUserById = userService.verifyUserById(idUserConnection);
        
        if(verifyUserById.isEmpty()){
            throw new UserNotFoudExceptionHandler("Usuário não existe");
        }

        Optional<ConnectionEntity> verifyConnection = connectionRepository.validatesConnection(id, idUserConnection);
        
        if(verifyConnection.isPresent()){
            throw new UserBadRequestExceptionHandler( "essa conexão já existe" );
        }
         
        ConnectionEntity connectionUserEntity = new ConnectionEntity();
        UserEntity userConnectionEntity = verifyUserById.get();
        UserEntity userEntity = getUser.get();
        
        connectionUserEntity.setUserEntity(userEntity);
        connectionUserEntity.setUserConnection(userConnectionEntity);
        connectionUserEntity.setApproved(false);

        
        connectionRepository.save(connectionUserEntity);
    }

    public void ApproveConnectionService(Long id, Long idUser){
        Optional<ConnectionEntity> verifyConnection = connectionRepository.findById(id);
        if(verifyConnection.isEmpty()){
            throw new ConnectionNotFoudExceptionHandler("Conexão não encontrada");
        }
        ConnectionEntity connection = verifyConnection.get();

        if(connection.getUserEntity().getId() == idUser || connection.getUserConnection().getId() != idUser){
            throw new UserBadRequestExceptionHandler(idUser.toString() + " não pode aceitar essa solictação");
        }
        
        connection.setApproved(true);
        connectionRepository.save(connection);
    }

    public Boolean verifyConnectionUser(Long id, Long idUserConnection){
        Optional<UserEntity> verifyUserById = userService.verifyUserById(idUserConnection);
        
        if(verifyUserById.isEmpty()){
            throw new UserNotFoudExceptionHandler("Usuário não existe");
        }

        Optional<ConnectionEntity> verifyConnection = connectionRepository.verifyConnection(id, idUserConnection);
        if(verifyConnection.isPresent()){
            return true;
        }
        return false;
    }

    public List<ConnectionEntity> getConnection(Long id, Boolean approved) {
        List<ConnectionEntity> listConnection = new ArrayList<>();

        if(approved == null){
            List<ConnectionEntity> connectionUser = connectionRepository.connectionUser(id);
            listConnection.addAll(connectionUser);
            return listConnection;
        }

        
        List<ConnectionEntity> connectionUser = connectionRepository.connectionUserApproved(id, approved);
        listConnection.addAll(connectionUser);
        
        return listConnection;
    }

    public void removeConnectionUser(Long id){
        Optional<ConnectionEntity> verifyConnection = connectionRepository.findById(id);
        if(verifyConnection.isEmpty()){
            throw new ConnectionNotFoudExceptionHandler("Conexão não encontrada");
        }
        connectionRepository.deleteById(id);
    }

    
}

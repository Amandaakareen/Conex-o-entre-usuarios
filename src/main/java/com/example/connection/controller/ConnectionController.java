package com.example.connection.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.connection.ConnectionEntity;
import com.example.connection.Response.VerificationResponse;
import com.example.connection.dto.ConnectionResponse;
import com.example.connection.dto.IdUserConnectionDTO;
import com.example.connection.error.Exception.ConnectionNotFoudExceptionHandler;
import com.example.connection.error.Exception.UserBadRequestExceptionHandler;
import com.example.connection.error.Exception.UserNotFoudExceptionHandler;
import com.example.connection.service.ConnectionService;

@RequestMapping("/connections")
@RestController
public class ConnectionController {
    
    ConnectionService connectionService;
    

    public ConnectionController(ConnectionService connectionService ){
        this.connectionService = connectionService;
    }

    @PostMapping("/request/user/{id}")
    public ResponseEntity RequestUserConnection(@PathVariable("id") Long id, @RequestBody IdUserConnectionDTO  userConnection){
        try{
            connectionService.connectUsers(id, userConnection.getIdUserConnection());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(UserNotFoudExceptionHandler e){
            throw new UserNotFoudExceptionHandler(e.getMessage());
        }
        
    }
    @PutMapping("{id}/approve/{idUser}")
    public ResponseEntity ApproveConnection(@PathVariable("id") Long id, @PathVariable("idUser") Long idUser){
        try{
            connectionService.ApproveConnectionService( id, idUser);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        }catch(ConnectionNotFoudExceptionHandler e){
            throw new ConnectionNotFoudExceptionHandler(e.getMessage());
        }catch(UserBadRequestExceptionHandler e){
            throw new UserBadRequestExceptionHandler(e.getMessage());
        }
    }

    @GetMapping("/users/{idUserConnection}/exists/{idUser}")
    public ResponseEntity<VerificationResponse> validateConnectionUsers(@PathVariable Long idUser, @PathVariable Long idUserConnection ){
        try{
            Boolean verifyConnection = connectionService.verifyConnectionUser(idUser ,idUserConnection );
            VerificationResponse verificationResponse = new VerificationResponse();
            verificationResponse.setVerification(verifyConnection);
            return ResponseEntity.ok(verificationResponse);
        }catch(UserNotFoudExceptionHandler e){
            throw new UserNotFoudExceptionHandler(e.getMessage());
        }

    }

    
    @GetMapping("/connections/{id}")
    public ResponseEntity<List<ConnectionResponse>> getConnectionUser(@PathVariable("id") Long id, @RequestParam(required = false) Boolean approved){
        try{
            List<ConnectionEntity> getConnection = connectionService.getConnection(id, approved);
            List<ConnectionResponse> dtoConncetion = getConnection.stream().map(connection -> ConnectionEntity.mapTo(connection)).toList();
            return ResponseEntity.ok(dtoConncetion);
           
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @DeleteMapping("connections/users/remove/{id}")
    public ResponseEntity removeConnection(@PathVariable("id") Long id){
        try{
            connectionService.removeConnectionUser(id);
            return ResponseEntity.ok().build();
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
}

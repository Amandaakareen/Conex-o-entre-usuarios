package com.example.connection.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.connection.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    

    @Query(value = "Select u from UserEntity u where u.email = :email")
    Optional<UserEntity> findbyEmail(String email);


    @Query(value = "Select u from UserEntity u where u.email = :email " +
    " and u.password = :password")
    Optional<UserEntity> findbyEmailbySenha(String email, String password);
}

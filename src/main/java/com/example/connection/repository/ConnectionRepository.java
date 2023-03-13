package com.example.connection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.connection.ConnectionEntity;

@Repository
public interface ConnectionRepository extends JpaRepository<ConnectionEntity, Long> {
    
}

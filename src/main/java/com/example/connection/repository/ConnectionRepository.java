package com.example.connection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.connection.ConnectionEntity;

@Repository
public interface ConnectionRepository extends JpaRepository<ConnectionEntity, Long> {
   

    @Query(value = "select c from ConnectionEntity c where c.userEntity.id = :idUser " +
    " and c.userConnection.id = :idUserConnection ")
    Optional<ConnectionEntity> validatesConnection(Long idUser, Long idUserConnection);

    @Query(value = "select c from ConnectionEntity c where (c.userEntity.id = :idUser " +
    " or c.userConnection.id = :idUser) and (c.userEntity.id = :idUserConnection " +
    " or c.userConnection.id = :idUserConnection) ")
    Optional<ConnectionEntity> verifyConnection(Long idUser, Long idUserConnection);

    @Query(value = "select c from ConnectionEntity c where c.approved = :approved and " +
    " (c.userEntity.id = :id or c.userConnection.id = :id) ")
    List<ConnectionEntity> connectionUserApproved(Long id, Boolean approved);

    @Query(value = "select c from ConnectionEntity c where  c.userEntity.id = :id " +
    " or c.userConnection.id = :id ")
    List<ConnectionEntity> connectionUser(Long id);
    
}

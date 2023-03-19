package com.example.connection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.connection.dto.ConnectionResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "CONNECTION")
public class ConnectionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "idUserConnection")
    private UserEntity userConnection;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity userEntity;

    @Column(name = "APPROVED")
    private boolean approved;

    public static ConnectionResponse mapTo(ConnectionEntity connectionEntity){
        ConnectionResponse dto = new ConnectionResponse();
        dto.setApproved(connectionEntity.isApproved());
        dto.setUserConnection(UserEntity.mapToUserDTO(connectionEntity.getUserConnection()));
        return dto;
    }

   
}

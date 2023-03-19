package com.example.connection;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.connection.dto.RegisterUserDTO;
import com.example.connection.dto.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "USERS")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long Id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;
    

    public static UserEntity mapTo(RegisterUserDTO registerUserDTO){
        UserEntity user = new UserEntity();
        user.setName(registerUserDTO.getName());
        user.setEmail(registerUserDTO.getEmail());
        user.setPassword(registerUserDTO.getPassword());
        return user;
    }

    public static UserResponse mapToUserDTO(UserEntity entity){
        UserResponse  user = new UserResponse ();
        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        return user;
    }

}

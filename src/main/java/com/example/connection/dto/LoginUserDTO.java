package com.example.connection.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginUserDTO {
    
    @NotEmpty @NotNull @Email
    private String email;
    @NotEmpty @NotNull
    private String password;
}

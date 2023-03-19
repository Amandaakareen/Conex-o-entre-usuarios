package com.example.connection.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class IdUserConnectionDTO {
    @NotBlank 
    private Long idUserConnection;
}

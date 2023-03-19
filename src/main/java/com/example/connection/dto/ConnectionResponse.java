package com.example.connection.dto;

import lombok.Data;

@Data
public class ConnectionResponse {
    
    private boolean approved;
   
    private UserResponse userConnection;  
   
}

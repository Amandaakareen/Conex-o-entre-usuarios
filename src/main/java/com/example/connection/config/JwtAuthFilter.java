package com.example.connection.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.example.connection.UserEntity;
import com.example.connection.service.UserService;
import com.example.connection.service.jwtService;

public class JwtAuthFilter /*extends OncePerRequestFilter*/ {
    
    private jwtService jwtService;
    private UserService userService;

    public JwtAuthFilter(jwtService jwtService, UserService userService){
        this.userService =  userService;
        this.jwtService = jwtService;
    }

    /*@Override
    protected void doFilterInternal(HttpServletRequest request, 
    HttpServletResponse response, 
    FilterChain filterChain){
        String authorization = request.getHeader("Authorization");

        if(authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split("")[1];
            boolean isValid = jwtService.validateToken(token);
            if(isValid){
                String getUserId = jwtService.getTokenUserId(token);
                UserName
            }
        }
        
    }*/
}

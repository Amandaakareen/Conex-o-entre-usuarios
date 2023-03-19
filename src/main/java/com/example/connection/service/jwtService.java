package com.example.connection.service;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class jwtService {
    @Value("${security.jwt.expiration}")
    private String expiration;
    @Value("${security.jwt.signature-key}")
    private String signatureKey;


    public String generateToken(String id){
        Long expirationLong = Long.valueOf(expiration);
        LocalDateTime localDateTime = LocalDateTime.now().minusHours(expirationLong);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return Jwts
                    .builder()
                    .setSubject(id)
                    .setExpiration(date)
                    .signWith(Keys.hmacShaKeyFor(signatureKey.getBytes()))
                    .compact();
    }

    public Claims getClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(signatureKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token){
        try{
            Claims claims = getClaims(token);
            Date data = claims.getExpiration();
            LocalDateTime localDateTime = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);   
        }catch(Exception e){
            return false;
        }
    }
    public String getTokenUserId(String token) throws ExpiredJwtException{
        return getClaims(token).getSubject();
    }
}

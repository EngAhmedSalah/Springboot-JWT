package com.example.springjwtdemo.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTutil
{
    public String generateToken(UserDetails userDetails)
    {
        Map<String , Object> claims = new HashMap<>();
        return createToken(claims , userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject)
    {
        String SECRET_KEY = "secretsecretsecretsecretsecretsecretsecretsecretsecretsecret";
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))      //when does the token created
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))      //after 10 hours
                .signWith(SignatureAlgorithm.HS256 , SECRET_KEY).compact();
    }

}

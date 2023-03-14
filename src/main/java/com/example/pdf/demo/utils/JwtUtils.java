package com.example.pdf.demo.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtils {

    private long JWT_ACCESS_TOKEN_VALIDITY = 14 * 24 * 60 * 60 * 1000;

    private String jwtSecret;

    private Algorithm algorithm;

    @Autowired
    public JwtUtils(@Value("${jwt.secret}") String jwtSecret){
        this.jwtSecret = jwtSecret;
        this.algorithm = Algorithm.HMAC256(this.jwtSecret);
    }

    public String generateToken(String uuid){
        String token = JWT.create()
        .withSubject(uuid)
        .withExpiresAt(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY))
        .sign(algorithm);
        return token;
    }
    public DecodedJWT decodeToken(String token){
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }
}

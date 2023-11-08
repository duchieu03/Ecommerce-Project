package com.ecommerceproject.ecommerceproject.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ecommerceproject.ecommerceproject.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expired}")
    private int expired;

    public String generateToken(User user){
        return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis()+expired))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withClaim("EMAIL",user.getEmail())
                .sign(Algorithm.HMAC256(algorithmKey));
    }

    public String generateVerifyToken(User user){
        return JWT.create()
                .withClaim("EMAIL_VERIFY_KEY",user.getEmail())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis()+(1000*60*30)))
                .withIssuer(issuer)
                .sign(Algorithm.HMAC256(algorithmKey));
    }

    public String generateResetPasswordToken(User user){
        return JWT.create()
                .withClaim("REST_EMAIL_KEY",user.getEmail())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis()+(1000*60*30)))
                .withIssuer(issuer)
                .sign(Algorithm.HMAC256(algorithmKey));
    }


    public String getEmail(String token){
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(algorithmKey)).withIssuer(issuer).build().verify(token);
        return JWT.decode(token).getClaim("EMAIL").asString();
    }
}

package com.exhio_api.exhio_api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.exhio_api.exhio_api.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    private final String issuer = "EXHIO WIKI API";

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().
                    withIssuer(issuer)
                    .withSubject(user.getEmail())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        }
        catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating JWT Token", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }
        catch (JWTVerificationException exception) {
            throw new RuntimeException("Token is invalid or expired");
        }
    }

    public Instant expirationDate() {
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }

}

package com.Versetti.AuthChallenge.infraestructure.utilities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtils {

    private final Key secretToken = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final Integer expirationTime = 1000 * 30 * 60;

    public String generateAuthToken (String username) {
        return Jwts.builder()
                .setIssuer("auth-api")
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretToken)
                .compact();
    }

}

package com.Versetti.AuthChallenge.infraestructure.utilities;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;


@Component
public class JwtUtils {

    private final Key secretToken = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final Long expirationTime = 1000 * 30 * 60;


}

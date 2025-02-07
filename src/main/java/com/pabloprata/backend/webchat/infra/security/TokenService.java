package com.pabloprata.backend.webchat.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pabloprata.backend.webchat.domain.Psychologist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Psychologist psychologist) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String userId = psychologist.getUser().getId().toString();

            return JWT.create()
                    .withIssuer("webchat")
                    .withSubject(userId)
                    .withClaim("email", psychologist.getUsername())
                    //.withClaim("roles", roles)
                    //.withExpiresAt(generateExpirationDate())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(generateExpirationDate())
                    .withClaim("name", psychologist.getUser().getName())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            logger.error("Erro ao gerar token JWT", exception);
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("webchat")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            logger.error("TokenJWT inválido ou expirado!", exception);
            throw new RuntimeException("TokenJWT inválido ou expirado!", exception);
        }

    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.UTC);
    }
}

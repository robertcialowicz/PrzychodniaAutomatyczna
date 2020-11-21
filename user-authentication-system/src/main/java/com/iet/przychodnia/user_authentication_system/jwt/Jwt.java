package com.iet.przychodnia.user_authentication_system.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
@Builder
@Slf4j
public class Jwt {

    public static final long DEFAULT_VALIDITY = 3 * 60 * 60; // 3 hours

    private static final ObjectMapper objectMapper = new ObjectMapper();

    String subject;
    Instant issuedAt;
    Instant expiresAt;

    @Singular
    List<String> roles;

    public static Jwt fromUserDetails(UserDetails user) {
        return Jwt.builder()
                .subject(user.getUsername())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(DEFAULT_VALIDITY))
                .roles(user.getAuthorities().stream().map(Object::toString).collect(Collectors.toList()))
                .build();
    }

    public static boolean verify(String encodedJwt, PublicKey publicKey) {
        return verify(encodedJwt, Algorithm.RSA512((RSAPublicKey) publicKey, null));
    }

    public static boolean verify(String encodedJwt, Algorithm algorithm) {
        try {
            val decodedJwt = JWT.decode(encodedJwt);

            algorithm.verify(decodedJwt);

            return true;
        } catch (SignatureVerificationException e) {
            log.error("An error occurred when decoding jwt: ", e);

            return false;
        }
    }

    public static Optional<Jwt> decode(String encodedJwt, PublicKey publicKey) {
        return decode(encodedJwt, Algorithm.RSA512((RSAPublicKey) publicKey, null));
    }

    public static Optional<Jwt> decode(String encodedJwt, Algorithm algorithm) {
        if (!verify(encodedJwt, algorithm)) {
            return Optional.empty();
        }

        try {
            val payload = JWT.decode(encodedJwt).getPayload();
            val jsonPayload = Base64.getDecoder().decode(payload);
            val node = objectMapper.readValue(jsonPayload, JwtPayload.class);

            return Optional.of(new Jwt(node.getSub(),
                                       Instant.ofEpochSecond(node.getIat()),
                                       Instant.ofEpochSecond(node.getExp()),
                                       node.getRoles()));
        } catch (Exception e) {
            log.error("An error occurred when decoding jwt: ", e);

            return Optional.empty();
        }
    }

    public String sign(PrivateKey privateKey) {
        return sign(Algorithm.RSA512(null, (RSAPrivateKey) privateKey));
    }

    public String sign(Algorithm algorithm) {
        return JWT.create()
                  .withClaim("sub", subject)
                  .withClaim("iat", Date.from(issuedAt))
                  .withClaim("exp", Date.from(expiresAt))
                  .withArrayClaim("roles", roles.toArray(String[]::new))
                  .sign(algorithm);
    }

    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    public boolean isExpired(Instant timestamp) {
        return timestamp.isBefore(issuedAt) || timestamp.isAfter(expiresAt);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class JwtPayload {

        private String sub;
        private long iat;
        private long exp;
        private List<String> roles;
    }
}
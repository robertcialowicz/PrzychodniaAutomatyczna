package com.iet.przychodnia.user_authentication_system.jwt;

import com.iet.przychodnia.user_authentication_system.authorization.AuthorizationRole;
import com.iet.przychodnia.user_authentication_system.exceptions.ForbiddenException;
import com.iet.przychodnia.user_authentication_system.exceptions.JwtExpiredException;
import com.iet.przychodnia.user_authentication_system.exceptions.JwtInvalidSignatureException;
import com.iet.przychodnia.user_authentication_system.exceptions.UnauthorizedException;
import lombok.val;

import java.time.Instant;
import java.util.List;

public class JwtValidator {
    public static final String BEARER_PREFIX = "Bearer ";

    public static Jwt validate(String authorizationHeader) {
        System.out.println(">>> " + authorizationHeader);

        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            throw new UnauthorizedException("Authorization header is not specified");
        }

        if (!authorizationHeader.startsWith(BEARER_PREFIX)) {
            throw new UnauthorizedException("Invalid Authorization header format");
        }

        val token = authorizationHeader.replace(BEARER_PREFIX, "");
        val decodedJwt = Jwt.decode(token, RSA.PUBLIC_KEY);

        if (decodedJwt.isEmpty()) {
            throw new JwtInvalidSignatureException(token);
        }

        val jwt = decodedJwt.get();

        if (jwt.isExpired(Instant.now())) {
            throw new JwtExpiredException(jwt.getIssuedAt(), jwt.getExpiresAt());
        }

        return jwt;
    }

    public static Jwt validateWithRoles(String authorizationHeader, List<AuthorizationRole> allowedRoles) {
        val jwt = validate(authorizationHeader);

        if (allowedRoles.stream().noneMatch(jwt::hasRole)) {
            throw new ForbiddenException("JWT Token has no permissions of allowed roles: " + allowedRoles);
        }

        return jwt;
    }
}

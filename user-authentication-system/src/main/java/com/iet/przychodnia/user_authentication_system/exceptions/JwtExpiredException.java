package com.iet.przychodnia.user_authentication_system.exceptions;

import java.time.Instant;

public class JwtExpiredException extends RuntimeException {
    public JwtExpiredException() {
    }

    public JwtExpiredException(Instant issuedAt, Instant expiresAt) {
        this("issuedAt: " + issuedAt + ", expiresAt: " + expiresAt);
    }

    public JwtExpiredException(String message) {
        super(message);
    }

    public JwtExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtExpiredException(Throwable cause) {
        super(cause);
    }

    public JwtExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

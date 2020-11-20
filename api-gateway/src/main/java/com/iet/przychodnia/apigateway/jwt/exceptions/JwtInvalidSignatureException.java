package com.iet.przychodnia.apigateway.jwt.exceptions;

public class JwtInvalidSignatureException extends RuntimeException {
    public JwtInvalidSignatureException() {
    }

    public JwtInvalidSignatureException(String message) {
        super(message);
    }

    public JwtInvalidSignatureException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtInvalidSignatureException(Throwable cause) {
        super(cause);
    }

    public JwtInvalidSignatureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

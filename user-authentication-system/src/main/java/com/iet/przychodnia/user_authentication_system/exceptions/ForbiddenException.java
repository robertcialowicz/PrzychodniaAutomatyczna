package com.iet.przychodnia.user_authentication_system.exceptions;

public class ForbiddenException extends RuntimeException {
	public ForbiddenException() {
    }

	public ForbiddenException(String message) {
        super(message);
    }

	public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

	public ForbiddenException(Throwable cause) {
        super(cause);
    }

	public ForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
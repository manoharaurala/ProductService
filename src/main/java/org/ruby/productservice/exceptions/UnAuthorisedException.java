package org.ruby.productservice.exceptions;

public class UnAuthorisedException extends RuntimeException {
    public UnAuthorisedException(String message) {
        super(message);
    }

    public UnAuthorisedException(String message, Throwable cause) {
        super(message, cause);
    }
}

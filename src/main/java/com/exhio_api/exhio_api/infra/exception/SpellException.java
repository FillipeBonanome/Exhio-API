package com.exhio_api.exhio_api.infra.exception;

public class SpellException extends RuntimeException {
    public SpellException(String message) {
        super(message);
    }
    public SpellException(String message, Throwable cause) { super(message, cause); }
}

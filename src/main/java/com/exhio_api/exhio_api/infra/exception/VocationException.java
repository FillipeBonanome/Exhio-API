package com.exhio_api.exhio_api.infra.exception;

public class VocationException extends RuntimeException {
    public VocationException(String message) {
        super(message);
    }
    public VocationException(String message, Throwable cause) { super(message, cause); }
}

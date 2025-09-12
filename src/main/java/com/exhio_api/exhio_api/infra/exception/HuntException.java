package com.exhio_api.exhio_api.infra.exception;

public class HuntException extends RuntimeException {
    public HuntException(String message) {
        super(message);
    }
    public HuntException(String message, Throwable cause) { super(message, cause); }
}

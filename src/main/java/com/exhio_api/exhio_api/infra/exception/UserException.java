package com.exhio_api.exhio_api.infra.exception;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
    public UserException(String messge, Throwable cause) { super(messge, cause); }
}

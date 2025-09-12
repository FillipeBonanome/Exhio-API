package com.exhio_api.exhio_api.infra.exception;

public class MonsterException extends RuntimeException {
    public MonsterException(String message) {
        super(message);
    }
    public MonsterException(String message, Throwable cause) { super(message, cause); }
}

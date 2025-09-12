package com.exhio_api.exhio_api.infra.exception;

public class DungeonException extends RuntimeException {
    public DungeonException(String message) {
        super(message);
    }
    public DungeonException(String message, Throwable cause) { super(message, cause); }
}

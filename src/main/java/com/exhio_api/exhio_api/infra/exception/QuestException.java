package com.exhio_api.exhio_api.infra.exception;

public class QuestException extends RuntimeException {
    public QuestException(String message) {
        super(message);
    }
    public QuestException(String message, Throwable cause) { super(message, cause); }
}

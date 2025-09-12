package com.exhio_api.exhio_api.dto.validation;

import org.springframework.validation.FieldError;

public record ValidationErrorDTO(String field, String message) {
    public ValidationErrorDTO(FieldError e) {
        this(
                e.getField(),
                e.getDefaultMessage()
        );
    }
}

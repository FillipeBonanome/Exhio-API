package com.exhio_api.exhio_api.infra.handler;

import com.exhio_api.exhio_api.dto.validation.FieldValidationDTO;
import com.exhio_api.exhio_api.dto.validation.ValidationErrorDTO;
import com.exhio_api.exhio_api.infra.exception.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorDTO>> handleError400(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(exception.getFieldErrors().stream().map(ValidationErrorDTO::new).toList());
    }

    @ExceptionHandler(DungeonException.class)
    public ResponseEntity<FieldValidationDTO> handleDungeonExceptions(DungeonException exception) {
        return ResponseEntity.badRequest().body(new FieldValidationDTO(exception.getMessage()));
    }

    @ExceptionHandler(HuntException.class)
    public ResponseEntity<FieldValidationDTO> handleHuntException(HuntException exception) {
        return ResponseEntity.badRequest().body(new FieldValidationDTO(exception.getMessage()));
    }

    @ExceptionHandler(QuestException.class)
    public ResponseEntity<FieldValidationDTO> handleQuestException(QuestException exception) {
        return ResponseEntity.badRequest().body(new FieldValidationDTO(exception.getMessage()));
    }

    @ExceptionHandler(SpellException.class)
    public ResponseEntity<FieldValidationDTO> handleSpellException(SpellException exception) {
        return ResponseEntity.badRequest().body(new FieldValidationDTO(exception.getMessage()));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<FieldValidationDTO> handleUserException(UserException exception) {
        return ResponseEntity.badRequest().body(new FieldValidationDTO(exception.getMessage()));
    }

}

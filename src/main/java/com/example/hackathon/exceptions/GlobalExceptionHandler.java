package com.example.hackathon.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex){
        StringBuilder response = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(e -> {
            response.append("[");
            response.append(e.getDefaultMessage());
            response.append("]");
        });
        log.warn(response.toString());
        return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<String> handleMethodValidationException(HandlerMethodValidationException ex){
        StringBuilder response = new StringBuilder();
        ex.getAllErrors().forEach(e -> {
            response.append("[");
            response.append(e.getDefaultMessage());
            response.append("]");
        });
        log.warn(response.toString());
        return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonParseException(HttpMessageNotReadableException ex){
        String response = "Request body contains invalid or unknown fields";
        log.warn(response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handleIdNotPresentException(IdNotFoundException ex){
        log.warn(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

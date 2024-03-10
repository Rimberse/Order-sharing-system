package fr.efrei.ordersharingsystem.controller;

import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException exception) {
        System.out.println("[ItemNotFoundException]: " + exception.getMessage());
        return ResponseEntity
                .notFound()
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        System.out.println("[IllegalArgumentException]: " + exception.getMessage());
        return ResponseEntity
                .badRequest()
                .build();
    }
}

package com.programmers.kwonjoosung.BootCampRatingNet.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Void> DataNotFoundExceptionExceptionHandler(DataNotFoundException e) {
        log.error("DataNotFoundException -> tableName: {}, columnName: {}, value: {}", e.getParams());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DataParseException.class)
    public ResponseEntity<Void> DataParseExceptionExceptionHandler(DataParseException e) {
        log.error("DataParseException -> tableName: {}, columnName: {}, value: {}", e.getParams());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<Void> ServiceRuntimeExceptionHandler(DataAlreadyExistException e) {
        log.error("DataParseException -> tableName: {}, columnName: {}, value: {}", e.getParams());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> ServiceRuntimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException -> {}", e.getMessage());
        return ResponseEntity.badRequest().build();
    }

}

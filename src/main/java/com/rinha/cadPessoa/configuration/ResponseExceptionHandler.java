package com.rinha.cadPessoa.configuration;

import com.rinha.cadPessoa.exception.NotNullPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("Exception error: {}", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage())
                );
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ExceptionDTO> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.error("Exception error: {}", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage())
                );
    }

    @ExceptionHandler({NotNullPropertyException.class, DataIntegrityViolationException.class})
    public ResponseEntity<ExceptionDTO> methodArgumentNotValidExceptionHandler(RuntimeException e) {
        log.error("Exception error: {}", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ExceptionDTO(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage())
                );
    }

}

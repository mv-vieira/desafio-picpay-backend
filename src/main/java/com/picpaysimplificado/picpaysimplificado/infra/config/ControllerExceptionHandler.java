package com.picpaysimplificado.picpaysimplificado.infra.config;

import com.picpaysimplificado.picpaysimplificado.domain.web.dto.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity dataDuplicateEntry(DataIntegrityViolationException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já está cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound(EntityNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity generalException(Exception e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}

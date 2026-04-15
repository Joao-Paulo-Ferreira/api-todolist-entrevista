package com.entrevista.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nomeDoCampo = ((FieldError) error).getField();
            String mensagemDeErro = error.getDefaultMessage();
            erros.put(nomeDoCampo, mensagemDeErro);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroPadrao> handleRuntimeException(RuntimeException ex) {
        ErroPadrao erro = new ErroPadrao(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroPadrao> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErroPadrao erro = new ErroPadrao(
                LocalDateTime.now(), 
                HttpStatus.CONFLICT.value(), 
                "Erro de integridade de dados: a operação violou uma restrição do banco de dados."
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErroPadrao> handleIllegalStateException(IllegalStateException ex) {
        ErroPadrao erro = new ErroPadrao(LocalDateTime.now(), HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    public static class ErroPadrao {
        public LocalDateTime timestamp;
        public int status;
        public String mensagem;

        public ErroPadrao(LocalDateTime timestamp, int status, String mensagem) {
            this.timestamp = timestamp;
            this.status = status;
            this.mensagem = mensagem;
        }
    }
}
package com.automaster.ms_testdrive.expection;


import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }

        log.warn("Error de validación en la petición: {}", errores);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        log.error("Error de negocio: {}", ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("error", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, Object>> handleFeignException(FeignException ex) {
        log.error("Error de comunicación con otro microservicio: {}", ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("error", "No se pudo establecer comunicación con el servicio externo o el recurso no existe.");
        response.put("detalle", ex.status() == 404 ? "Recurso no encontrado en el microservicio destino" : "Error interno del microservicio destino");
        response.put("timestamp", LocalDateTime.now());

        HttpStatus status = ex.status() == 404 ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status).body(response);
    }
}

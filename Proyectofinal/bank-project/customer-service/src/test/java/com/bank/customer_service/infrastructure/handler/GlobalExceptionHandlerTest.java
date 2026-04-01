package com.bank.customer_service.infrastructure.handler;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testHandleValidation() {
        // Mock del BindingResult
        BindingResult bindingResult = mock(BindingResult.class);

        // Simulamos un error de validación
        FieldError fieldError = new FieldError("object", "name", "must not be empty");

        when(bindingResult.getFieldErrors()).thenReturn(java.util.List.of(fieldError));

        // Creamos la excepción simulada
        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(null, bindingResult);

        // Ejecutamos el handler
        ResponseEntity<Map<String, Object>> response = handler.handleValidation(ex);

        // Validaciones
        assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
        assert response.getBody() != null;
        assert response.getBody().get("status").equals(400);

        Map<String, String> errors = (Map<String, String>) response.getBody().get("errors");
        assert errors.get("name").equals("must not be empty");
    }

    @Test
    void testHandleAny() {
        Exception ex = new Exception("unexpected error");

        ResponseEntity<Map<String, Object>> response = handler.handleAny(ex);

        assert response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR;
        assert response.getBody() != null;
        assert response.getBody().get("status").equals(500);
        assert response.getBody().get("error").equals("unexpected error");
    }
}

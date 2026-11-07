package co.com.crediya.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 1. Maneja los errores de validación del DTO (Ej: salario negativo, correo sin arroba)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("Error de validación en los datos de entrada");
        Map<String, String> errores = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(nombreCampo, mensaje);
        });
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

    // 2. Maneja el error de lógica de negocio (Ej: El correo ya existe)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleBusinessExceptions(IllegalArgumentException ex) {
        log.error("Error de negocio: {}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // 3. Maneja cualquier otra excepción no controlada (Mensaje inesperado)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralExceptions(Exception ex) {
        log.error("Error interno del servidor: {}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("error", "Ocurrió un error inesperado. Por favor, intente más tarde.");
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
package br.com.teste.diegomarques.agendador_transferencia_financeira.resources.exceptions;

import br.com.teste.diegomarques.agendador_transferencia_financeira.services.exceptions.TaxaNaoAplicavelException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(TaxaNaoAplicavelException.class)
    public ResponseEntity<StandardError> handleTaxaNaoAplicavel(TaxaNaoAplicavelException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Taxa não aplicável",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handleObjectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        return handleExceptionInternal(e, HttpStatus.NOT_FOUND, "Não Encontrado", request.getRequestURI());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> handleDataIntegrity(DataIntegrityViolationException e, HttpServletRequest request) {
        return handleExceptionInternal(e, HttpStatus.BAD_REQUEST, "Integridade de Dados", request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ValidationError err = new ValidationError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Erro na validação dos campos",
                request.getRequestURI()
        );

        ex.getBindingResult().getFieldErrors().forEach(f -> {
            err.addError(f.getField(), f.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    private ResponseEntity<StandardError> handleExceptionInternal(RuntimeException ex, HttpStatus status, String error, String path) {
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, ex.getMessage(), path);
        return ResponseEntity.status(status).body(standardError);
    }
}

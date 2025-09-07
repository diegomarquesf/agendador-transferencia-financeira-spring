package br.com.teste.diegomarques.agendador_transferencia_financeira.resources.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidationError {
    private StandardError standardError;
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {}

    public ValidationError(StandardError standardError) {
        this.standardError = standardError;
    }

    public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
        this(new StandardError(timestamp, status, error, message, path));
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }

    public StandardError getStandardError() { return standardError; }
    public List<FieldMessage> getErrors() { return errors; }
}
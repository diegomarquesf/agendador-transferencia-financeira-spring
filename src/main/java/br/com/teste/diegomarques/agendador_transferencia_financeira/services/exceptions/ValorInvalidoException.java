package br.com.teste.diegomarques.agendador_transferencia_financeira.services.exceptions;

public class ValorInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ValorInvalidoException(String message) {
        super(message);
    }
}
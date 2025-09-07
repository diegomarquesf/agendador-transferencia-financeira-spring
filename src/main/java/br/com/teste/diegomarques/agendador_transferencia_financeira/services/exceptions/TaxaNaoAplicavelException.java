package br.com.teste.diegomarques.agendador_transferencia_financeira.services.exceptions;

public class TaxaNaoAplicavelException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TaxaNaoAplicavelException(String message) {
        super(message);
    }
}

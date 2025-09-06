package br.com.teste.diegomarques.agendador_transferencia_financeira.entities.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferenciaRequestDTO implements Serializable {
    private String contaOrigem ;
    private String contaDestino;
    private BigDecimal valor;
    private LocalDate dtTransferencia;

    public TransferenciaRequestDTO() {

    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDtTransferencia() {
        return dtTransferencia;
    }

    public void setDtTransferencia(LocalDate dtTransferencia) {
        this.dtTransferencia = dtTransferencia;
    }
}
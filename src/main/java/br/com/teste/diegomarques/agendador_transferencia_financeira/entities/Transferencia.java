package br.com.teste.diegomarques.agendador_transferencia_financeira.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transferencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contaOrigem;
    private String contaDestino;

    private BigDecimal valor;
    private BigDecimal taxa;

    private LocalDate dtTransferencia;
    private LocalDate dtAgendamento;

    public Transferencia() {

    }

    public Transferencia(Long id, String contaOrigem, String contaDestino, BigDecimal valor, BigDecimal taxa, LocalDate dtTransferencia, LocalDate dtAgendamento) {
        this.id = id;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.taxa = taxa;
        this.dtTransferencia = dtTransferencia;
        this.dtAgendamento = dtAgendamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public LocalDate getDtTransferencia() {
        return dtTransferencia;
    }

    public void setDtTransferencia(LocalDate dtTransferencia) {
        this.dtTransferencia = dtTransferencia;
    }

    public LocalDate getDtAgendamento() {
        return dtAgendamento;
    }

    public void setDtAgendamento(LocalDate dtAgendamento) {
        this.dtAgendamento = dtAgendamento;
    }
}
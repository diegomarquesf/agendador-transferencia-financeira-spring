package br.com.teste.diegomarques.agendador_transferencia_financeira.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_transferencia_01")
public class Transferencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "contaOrigem")
    private String contaOrigem;

    @NotBlank
    @Column(name = "contaDestino")
    private String contaDestino;

    @NotNull
    @DecimalMin("0.01")
    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "taxa")
    private BigDecimal taxa;

    @NotNull
    @Column(name = "dtTransferencia")
    private LocalDate dtTransferencia;

    @Column(name = "dtAgendamento")
    private LocalDate dtAgendamento = LocalDate.now();

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
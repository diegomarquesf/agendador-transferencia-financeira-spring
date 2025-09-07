package br.com.teste.diegomarques.agendador_transferencia_financeira.entities.dtos;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferenciaRequestDTO implements Serializable {

    @NotBlank(message = "O campo 'Conta de origem' é obrigatório!")
    @Pattern(regexp = "^[X0-9]{10}$", message = "O campo 'Conta de origem' deve ter 10 dígitos!")
    private String contaOrigem ;

    @NotBlank(message = "O campo 'Conta de destino' é obrigatório!")
    @Pattern(regexp = "^[X0-9]{10}$", message = "O campo 'Conta de destino' deve ter 10 dígitos!")
    private String contaDestino;

    @NotNull(message = "O campo 'Valor' é obrigatório!")
    @DecimalMin(value = "0.01", message = "o campo 'Valor' deve ser maior que 0,01!")
    private BigDecimal valor;

    @NotNull(message = "O campo 'Data de transferência' é obrigatória!")
    @FutureOrPresent(message = "O campo 'Data de transferência' deve ser hoje ou futura!")
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
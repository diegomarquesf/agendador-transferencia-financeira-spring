package br.com.teste.diegomarques.agendador_transferencia_financeira.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_taxa_02")
public class Taxa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dia_de")
    private Long minDias;
    @Column(name = "dia_ate")
    private Long maxDias;

    @Column(name = "valor_taxa_fixa", precision = 15, scale = 2)
    private BigDecimal taxaFixa;

    @Column(name = "porcentual_taxa", precision = 5, scale = 4)
    private BigDecimal percentual;

    public boolean seAplica(long dias) {
        return dias >= minDias && dias <= maxDias;
    }

    public BigDecimal calcular(BigDecimal valor) {
        BigDecimal taxa = BigDecimal.ZERO;
        if (taxaFixa != null) {
            taxa = taxa.add(taxaFixa);
        }
        if (percentual != null) {
            taxa = taxa.add(valor.multiply(percentual));
        }
        return taxa;
    }

    public Taxa() {

    }

    public Taxa(Long minDias, Long maxDias, BigDecimal taxaFixa, BigDecimal percentual) {
        this.minDias = minDias;
        this.maxDias = maxDias;
        this.taxaFixa = taxaFixa;
        this.percentual = percentual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMinDias() {
        return minDias;
    }

    public void setMinDias(Long minDias) {
        this.minDias = minDias;
    }

    public Long getMaxDias() {
        return maxDias;
    }

    public void setMaxDias(Long maxDias) {
        this.maxDias = maxDias;
    }

    public BigDecimal getTaxaFixa() {
        return taxaFixa;
    }

    public void setTaxaFixa(BigDecimal taxaFixa) {
        this.taxaFixa = taxaFixa;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }
}

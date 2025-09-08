package br.com.teste.diegomarques.agendador_transferencia_financeira.services;

import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.Taxa;
import br.com.teste.diegomarques.agendador_transferencia_financeira.repositories.TaxaRepository;
import br.com.teste.diegomarques.agendador_transferencia_financeira.services.exceptions.TaxaNaoAplicavelException;
import br.com.teste.diegomarques.agendador_transferencia_financeira.services.exceptions.ValorInvalidoException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CalculadoraTaxaService {

    private final TaxaRepository taxaRepository;

    public CalculadoraTaxaService(TaxaRepository regraTaxaRepository) {
        this.taxaRepository = regraTaxaRepository;
    }

    public BigDecimal calcular(long dias, BigDecimal valor) {
        if (dias < 0) {
            throw new ValorInvalidoException("O número de dias não pode ser negativo.");
        }
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("O valor deve ser maior que zero.");
        }

        Optional<Taxa> taxaOpt = taxaRepository.findByDias(dias);
        if (taxaOpt.isEmpty()) {
            throw new TaxaNaoAplicavelException("Nenhuma taxa aplicável para " + dias + " dias.");
        }

        return taxaOpt.get().calcular(valor);
    }
}

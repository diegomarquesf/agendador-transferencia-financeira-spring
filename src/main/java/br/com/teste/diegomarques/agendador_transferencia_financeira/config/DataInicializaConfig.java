package br.com.teste.diegomarques.agendador_transferencia_financeira.config;

import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.Taxa;
import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.Transferencia;
import br.com.teste.diegomarques.agendador_transferencia_financeira.repositories.TaxaRepository;
import br.com.teste.diegomarques.agendador_transferencia_financeira.repositories.TransferenciaRepository;
import br.com.teste.diegomarques.agendador_transferencia_financeira.services.CalculadoraTaxaService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Configuration
public class DataInicializaConfig {

    @Bean
    ApplicationRunner initRegraTaxa(TaxaRepository taxaRepository) {
        return args -> {
            if (taxaRepository.count() == 0) {
                taxaRepository.saveAll(List.of(
                        criarRegra(0L, 0L, new BigDecimal("3.00"), new BigDecimal("0.025")),
                        criarRegra(1L, 10L, new BigDecimal("12.00"), null),
                        criarRegra(11L, 20L, null, new BigDecimal("0.082")),
                        criarRegra(21L, 30L, null, new BigDecimal("0.069")),
                        criarRegra(31L, 40L, null, new BigDecimal("0.047")),
                        criarRegra(41L, 50L, null, new BigDecimal("0.017"))
                ));
            }
        };
    }

    @Bean
    ApplicationRunner initAgendamentos(TransferenciaRepository transferenciaRepository, CalculadoraTaxaService calculadoraTaxaService) {
        return args -> {
            if (transferenciaRepository.count() == 0) {
                List<Transferencia> agendamentos = List.of(
                        new Transferencia(null, "0000000001", "0000000010", new BigDecimal("100.00"), null, LocalDate.now().plusDays(9), LocalDate.now()),
                        new Transferencia(null, "0000000009", "0000000002", new BigDecimal("200.00"), null, LocalDate.now().plusDays(3), LocalDate.now()),
                        new Transferencia(null, "0000000003", "0000000008", new BigDecimal("30.00"), null, LocalDate.now().plusDays(5), LocalDate.now()),
                        new Transferencia(null, "0000000007", "0000000004", new BigDecimal("400.00"), null, LocalDate.now().plusDays(10), LocalDate.now()),
                        new Transferencia(null, "0000000005", "0000000006", new BigDecimal("550.00"), null, LocalDate.now().plusDays(24), LocalDate.now()),
                        new Transferencia(null, "0000000002", "0000000001", new BigDecimal("660.00"), null, LocalDate.now().plusDays(12), LocalDate.now()),
                        new Transferencia(null, "0000000005", "0000000009", new BigDecimal("700.00"), null, LocalDate.now().plusDays(6), LocalDate.now()),
                        new Transferencia(null, "0000000004", "0000000003", new BigDecimal("800.00"), null, LocalDate.now().plusDays(40), LocalDate.now()),
                        new Transferencia(null, "0000000006", "0000000001", new BigDecimal("9000.00"), null, LocalDate.now().plusDays(33), LocalDate.now()),
                        new Transferencia(null, "0000000008", "0000000005", new BigDecimal("1500.00"), null, LocalDate.now().plusDays(29), LocalDate.now())
                );

                for (Transferencia transferencia : agendamentos) {
                    long dias = ChronoUnit.DAYS.between(transferencia.getDtAgendamento(), transferencia.getDtTransferencia());
                    if (dias < 0) {
                        throw new IllegalArgumentException("A data de transferência deve ser posterior à data de agendamento para a transferência de " + transferencia.getValor());
                    }
                    transferencia.setTaxa(calculadoraTaxaService.calcular(dias, transferencia.getValor()));
                    transferenciaRepository.save(transferencia);
                }
            }
        };
    }

    private Taxa criarRegra(Long min, Long max, BigDecimal fixo, BigDecimal porcentual) {
        return new Taxa(min, max, fixo, porcentual);
    }
}

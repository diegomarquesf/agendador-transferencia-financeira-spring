package br.com.teste.diegomarques.agendador_transferencia_financeira.services;

import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.Transferencia;
import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.dtos.TransferenciaRequestDTO;
import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.mappers.TransferenciaMapper;
import br.com.teste.diegomarques.agendador_transferencia_financeira.repositories.TransferenciaRepository;
import br.com.teste.diegomarques.agendador_transferencia_financeira.services.exceptions.TaxaNaoAplicavelException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final TransferenciaMapper transferenciaMapper;
    private final CalculadoraTaxaService calculadoraTaxa;

    public TransferenciaService(TransferenciaRepository transferenciaRepository, TransferenciaMapper transferenciaMapper, CalculadoraTaxaService calculadoraTaxa) {
        this.transferenciaRepository = transferenciaRepository;
        this.transferenciaMapper = transferenciaMapper;
        this.calculadoraTaxa = calculadoraTaxa;
    }

    public Transferencia agendarTransferencia(TransferenciaRequestDTO dto) {
        Transferencia transferencia = transferenciaMapper.toEntity(dto);
        long dias = calcularDias(transferencia.getDtAgendamento(), dto.getDtTransferencia());

        BigDecimal taxa = calcularTaxa(dto, dias);
        transferencia.setTaxa(taxa);
        return transferenciaRepository.save(transferencia);
    }

    public List<Transferencia> findExtrato() {
        return transferenciaRepository.findAll();
    }

    private long calcularDias(LocalDate dataAgendamento, LocalDate dataTransferencia) {
        return ChronoUnit.DAYS.between(dataAgendamento, dataTransferencia);
    }

    private BigDecimal calcularTaxa(TransferenciaRequestDTO dto, long dias) {
        BigDecimal taxa;
        try {
            taxa = calculadoraTaxa.calcular(dias, dto.getValor());
        } catch (TaxaNaoAplicavelException e) {
            String dataFormatada = dto.getDtTransferencia().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            throw new TaxaNaoAplicavelException("Não é possível aplicar uma taxa para a transferência agendada em " + dataFormatada);
        }
        return taxa;
    }
}
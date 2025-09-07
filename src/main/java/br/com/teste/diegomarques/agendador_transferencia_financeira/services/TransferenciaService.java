package br.com.teste.diegomarques.agendador_transferencia_financeira.services;

import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.Transferencia;
import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.dtos.TransferenciaRequestDTO;
import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.mappers.TransferenciaMapper;
import br.com.teste.diegomarques.agendador_transferencia_financeira.repositories.TransferenciaRepository;
import br.com.teste.diegomarques.agendador_transferencia_financeira.services.exceptions.TaxaNaoAplicavelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    @Autowired
    private TransferenciaMapper transferenciaMapper;

    public Transferencia agendarTransferencia(TransferenciaRequestDTO dto) {
        Transferencia transferencia = transferenciaMapper.toEntity(dto);

        long dias = calcularDias(transferencia.getDtAgendamento(), dto.getDtTransferencia());
        BigDecimal taxa = calcularTaxa(dias, dto.getValor());

        if (taxa == null) {
            throw new TaxaNaoAplicavelException("Não é possível aplicar uma taxa para a transferência agendada em " + dto.getDtTransferencia());
        }
        transferencia.setTaxa(taxa);
        return transferenciaRepository.save(transferencia);
    }

    public List<Transferencia> findExtrato() {
        return transferenciaRepository.findAll();
    }

    private long calcularDias(LocalDate dataAgendamento, LocalDate dataTransferencia) {
        return ChronoUnit.DAYS.between(dataAgendamento, dataTransferencia);
    }

    private BigDecimal calcularTaxa(long dias, BigDecimal valor) {
        if (dias == 0) {
            return new BigDecimal("3.00").add(valor.multiply(new BigDecimal("0.025")));
        } else if (dias >= 1 && dias <= 10) {
            return new BigDecimal("12.00");
        } else if (dias >= 11 && dias <= 20) {
            return valor.multiply(new BigDecimal("0.082"));
        } else if (dias >= 21 && dias <= 30) {
            return valor.multiply(new BigDecimal("0.069"));
        } else if (dias >= 31 && dias <= 40) {
            return valor.multiply(new BigDecimal("0.047"));
        } else if (dias >= 41 && dias <= 50) {
            return valor.multiply(new BigDecimal("0.017"));
        }
        return null;
    }
}

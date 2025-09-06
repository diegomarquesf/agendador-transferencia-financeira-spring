package br.com.teste.diegomarques.agendador_transferencia_financeira.entities.mappers;

import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.Transferencia;
import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.dtos.TransferenciaRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper extends GenericMapper<Transferencia, TransferenciaRequestDTO> {

}

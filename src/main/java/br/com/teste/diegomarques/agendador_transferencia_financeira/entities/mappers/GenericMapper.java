package br.com.teste.diegomarques.agendador_transferencia_financeira.entities.mappers;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface GenericMapper<E, D> {

    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDtoList(List<E> entityList);

    List<E> toEntityList(List<D> dtoList);
}


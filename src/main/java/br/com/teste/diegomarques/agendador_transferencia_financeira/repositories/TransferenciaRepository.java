package br.com.teste.diegomarques.agendador_transferencia_financeira.repositories;

import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

}

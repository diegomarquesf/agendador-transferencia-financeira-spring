package br.com.teste.diegomarques.agendador_transferencia_financeira.repositories;

import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.Taxa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TaxaRepository extends JpaRepository<Taxa, Long> {

    @Query("SELECT t FROM Taxa t WHERE :dias >= t.minDias AND :dias <= t.maxDias")
    Optional<Taxa> findByDias(@Param("dias") long dias);
}

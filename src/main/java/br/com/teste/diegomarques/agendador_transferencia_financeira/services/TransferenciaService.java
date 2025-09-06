package br.com.teste.diegomarques.agendador_transferencia_financeira.services;

import br.com.teste.diegomarques.agendador_transferencia_financeira.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;
}

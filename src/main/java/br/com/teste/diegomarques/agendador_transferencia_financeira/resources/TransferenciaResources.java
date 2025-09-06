package br.com.teste.diegomarques.agendador_transferencia_financeira.resources;

import br.com.teste.diegomarques.agendador_transferencia_financeira.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/tranferencia")
public class TransferenciaResources {

    @Autowired
    private TransferenciaService transferenciaService;

}

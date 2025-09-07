package br.com.teste.diegomarques.agendador_transferencia_financeira.resources;

import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.Transferencia;
import br.com.teste.diegomarques.agendador_transferencia_financeira.entities.dtos.TransferenciaRequestDTO;
import br.com.teste.diegomarques.agendador_transferencia_financeira.services.TransferenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/test/tranferencia")
public class TransferenciaResources {

    private final TransferenciaService transferenciaService;

    public TransferenciaResources(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping("/createAgendamento")
    public ResponseEntity<Transferencia> createAgendamento(@Valid @RequestBody TransferenciaRequestDTO dto) {
        Transferencia transferencia = transferenciaService.agendarTransferencia(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transferencia.getId())
                .toUri();

        return ResponseEntity.created(uri).body(transferencia);
    }

    @GetMapping("agendamentos")
    public ResponseEntity<List<Transferencia>> findExtrato() {
        List<Transferencia> transferencias = transferenciaService.findExtrato();
        return ResponseEntity.ok(transferencias);
    }

}

package com.proposta.app.adapters.inbounds;

import com.proposta.app.adapters.inbounds.mapper.PropostaRequestToPropostaMapper;
import com.proposta.app.adapters.inbounds.mapper.PropostaToPropostaResponseMapper;
import com.proposta.app.adapters.inbounds.models.PropostaRequest;
import com.proposta.app.adapters.inbounds.models.PropostaResponse;
import com.proposta.app.application.ports.inbounds.PropostaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private static final Logger log = LoggerFactory.getLogger(PropostaController.class);
    private static final String IDENTIFICADOR_OPERACAO_PROPOSTA_CONTROLLER = "[PropostaController]";

    private final PropostaService propostaService;
    private final PropostaRequestToPropostaMapper propostaRequestToPropostaMapper;
    private final PropostaToPropostaResponseMapper propostaToPropostaResponseMapper;

    @PostMapping()
    public ResponseEntity<PropostaResponse> criarProposta(@RequestBody PropostaRequest request) {

        log.info("{} Chamando serviço para criar proposta", IDENTIFICADOR_OPERACAO_PROPOSTA_CONTROLLER);
        PropostaResponse response = this.propostaToPropostaResponseMapper.mapper(
            this.propostaService.criarProposta(this.propostaRequestToPropostaMapper.mapper(request))
        );
        return ResponseEntity
                .created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(response.id())
                    .toUri()
                ).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<PropostaResponse>> obterPropostas() {
        log.info("{} Chamando serviço para buscar todas propostas",
                IDENTIFICADOR_OPERACAO_PROPOSTA_CONTROLLER);
        List<PropostaResponse> response = this.propostaService.obterPropostas().stream()
            .map(this.propostaToPropostaResponseMapper::mapper)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}

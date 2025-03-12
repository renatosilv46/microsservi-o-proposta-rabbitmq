package com.proposta.app.adapters.inbounds;

import com.proposta.app.adapters.inbounds.mapper.PropostaRequestToPropostaMapper;
import com.proposta.app.adapters.inbounds.mapper.PropostaToPropostaResponseMapper;
import com.proposta.app.adapters.inbounds.models.PropostaRequest;
import com.proposta.app.adapters.inbounds.models.PropostaResponse;
import com.proposta.app.application.ports.inbounds.PropostaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private final PropostaService propostaService;
    private final PropostaRequestToPropostaMapper propostaRequestToPropostaMapper;
    private final PropostaToPropostaResponseMapper propostaToPropostaResponseMapper;

    public PropostaController(
            final PropostaService propostaService,
            final PropostaRequestToPropostaMapper propostaRequestToPropostaMapper,
            final PropostaToPropostaResponseMapper propostaToPropostaResponseMapper
    ){
        this.propostaService = propostaService;
        this.propostaRequestToPropostaMapper = propostaRequestToPropostaMapper;
        this.propostaToPropostaResponseMapper = propostaToPropostaResponseMapper;
    }

    @PostMapping()
    public ResponseEntity<PropostaResponse> criarProposta(@RequestBody PropostaRequest request) {

        PropostaResponse response = propostaToPropostaResponseMapper.mapper(
            propostaService.criarProposta(propostaRequestToPropostaMapper.mapper(request))
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
        List<PropostaResponse> response = propostaService.obterPropostas().stream()
            .map(propostaToPropostaResponseMapper::mapper)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}

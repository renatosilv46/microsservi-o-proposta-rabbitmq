package com.proposta.app.adapters.outbounds.client;

import com.proposta.app.adapters.outbounds.mapper.PropostaEntityToPropostaMapper;
import com.proposta.app.adapters.outbounds.mapper.PropostaToPropostaEntityMapper;
import com.proposta.app.adapters.outbounds.models.PropostaEntity;
import com.proposta.app.application.core.domain.Proposta;
import com.proposta.app.application.ports.outbounds.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class PropostaRepositoryImpl implements PropostaRepository {

    private static final Logger log = LoggerFactory.getLogger(PropostaRepositoryImpl.class);
    private static final String IDENTIFICADOR_OPERACAO_PROPOSTA_REPOSITORY = "[PropostaRepository]";

    private final PropostaRepositoryJpa propostaRepositoryJpa;
    private final PropostaToPropostaEntityMapper propostaToPropostaEntityMapper;
    private final PropostaEntityToPropostaMapper propostaEntityToPropostaMapper;

    @Override
    public Proposta criarProposta(Proposta proposta) {
        log.info("{} Salvando proposta na base de dados",
                IDENTIFICADOR_OPERACAO_PROPOSTA_REPOSITORY);
        PropostaEntity propostaCriada = this.propostaRepositoryJpa
                .save(propostaToPropostaEntityMapper.mapper(proposta));
        return this.propostaEntityToPropostaMapper.mapper(propostaCriada);
    }

    @Override
    public List<Proposta> obterPropostas() {
        log.info("{} Buscando propostas na base de dados",
                IDENTIFICADOR_OPERACAO_PROPOSTA_REPOSITORY);
        return propostaRepositoryJpa.findAll()
                .stream()
                .map(propostaEntityToPropostaMapper::mapper)
                .collect(Collectors.toList()
        );
    }

    @Override
    public List<Proposta> obterPropostasNaoIntegradas() {
        log.info("{} Buscando propostas não integradas na base de dados",
                IDENTIFICADOR_OPERACAO_PROPOSTA_REPOSITORY);
        return propostaRepositoryJpa.findAllByIntegradaIsFalse()
                .stream()
                .map(propostaEntityToPropostaMapper:: mapper)
                .collect(Collectors.toList());
    }
}

package com.proposta.app.adapters.outbounds.client;


import com.proposta.app.adapters.outbounds.mapper.PropostaEntityToPropostaMapper;
import com.proposta.app.adapters.outbounds.mapper.PropostaToPropostaEntityMapper;
import com.proposta.app.adapters.outbounds.models.PropostaEntity;
import com.proposta.app.application.core.domain.Proposta;
import com.proposta.app.application.ports.outbounds.PropostaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class PropostaRepositoryImpl implements PropostaRepository {

    private final PropostaRepositoryJpa propostaRepositoryJpa;
    private final PropostaToPropostaEntityMapper propostaToPropostaEntityMapper;
    private final PropostaEntityToPropostaMapper propostaEntityToPropostaMapper;

    public PropostaRepositoryImpl(
            final PropostaRepositoryJpa propostaRepositoryJpa,
            final PropostaToPropostaEntityMapper propostaToPropostaEntityMapper,
            final PropostaEntityToPropostaMapper propostaEntityToPropostaMapper
    ) {
        this.propostaRepositoryJpa = propostaRepositoryJpa;
        this.propostaToPropostaEntityMapper = propostaToPropostaEntityMapper;
        this.propostaEntityToPropostaMapper = propostaEntityToPropostaMapper;
    }

    @Override
    public Proposta criarProposta(Proposta proposta) {
        PropostaEntity propostaCriada = propostaRepositoryJpa
                .save(propostaToPropostaEntityMapper.mapper(proposta));
        return propostaEntityToPropostaMapper.mapper(propostaCriada);
    }

    @Override
    public List<Proposta> obterPropostas() {
        return propostaRepositoryJpa.findAll()
                .stream()
                .map(propostaEntityToPropostaMapper::mapper)
                .collect(Collectors.toList()
        );
    }

    @Override
    public List<Proposta> obterPropostasNaoIntegradas() {
        return propostaRepositoryJpa.findAllByIntegradaIsFalse()
                .stream()
                .map(propostaEntityToPropostaMapper:: mapper)
                .collect(Collectors.toList());
    }
}

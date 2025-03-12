package com.proposta.app.application.ports.inbounds;

import com.proposta.app.application.core.domain.Proposta;

import java.util.List;

public interface PropostaService {
    Proposta criarProposta(Proposta proposta);
    List<Proposta> obterPropostas();
}

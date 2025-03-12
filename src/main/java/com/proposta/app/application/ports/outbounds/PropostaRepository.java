package com.proposta.app.application.ports.outbounds;

import com.proposta.app.application.core.domain.Proposta;
import java.util.List;

public interface PropostaRepository {

    Proposta criarProposta(Proposta proposta);

    List<Proposta> obterPropostas();

    List<Proposta> obterPropostasNaoIntegradas();
}

package com.proposta.app.application.ports.inbounds;

import com.proposta.app.application.core.domain.Proposta;

public interface PropostaConcluidaListener {
    void recebePropostaConcluida(Proposta proposta);
}

package com.proposta.app.application.ports.outbounds;

import com.proposta.app.application.core.domain.Proposta;

public interface NotificacaoRabbitService {
    void notificar(Proposta propostaResponse, String exchange);
}

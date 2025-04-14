package com.proposta.app.application.ports.outbounds;

import com.proposta.app.adapters.inbounds.models.PropostaResponse;

public interface WebSocketService {
  void notificar(PropostaResponse propostaResponse);
}

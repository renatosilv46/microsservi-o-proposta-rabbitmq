package com.proposta.app.adapters.outbounds.websocket;

import com.proposta.app.adapters.inbounds.models.PropostaResponse;
import com.proposta.app.application.ports.outbounds.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class WebSocketServiceImpl implements WebSocketService {
    private static final String IDENTIFICADOR_OPERACAO_WEB_SOCKET_SERVICE = "[WebSocketService]";
    private final SimpMessagingTemplate template;

    @Override
    public void notificar(PropostaResponse propostaResponse) {
        try{
            template.convertAndSend("/propostas", propostaResponse);
        } catch(Exception ex) {
            log.error("{} Erro ao tentar notificar o  web socket: {}",
                    IDENTIFICADOR_OPERACAO_WEB_SOCKET_SERVICE, ex.getMessage(), ex);
        }
    }
}

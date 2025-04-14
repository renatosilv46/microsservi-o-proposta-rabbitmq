package com.proposta.app.adapters.inbounds;

import com.proposta.app.adapters.inbounds.mapper.PropostaToPropostaResponseMapper;
import com.proposta.app.application.core.domain.Proposta;
import com.proposta.app.application.ports.inbounds.PropostaConcluidaListener;
import com.proposta.app.application.ports.outbounds.PropostaRepository;
import com.proposta.app.application.ports.outbounds.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PropostaConcluidaListenerImpl implements PropostaConcluidaListener {

    private static final String IDENTIFICADOR_OPERACAO_PROPOSTA_CONCLUIDA_LISTENER = "[PropostaConcluidaListener]";

    private final PropostaRepository propostaRepository;
    private final WebSocketService webSocketService;
    private final PropostaToPropostaResponseMapper propostaToPropostaResponseMapper;

    @Override
    @RabbitListener(queues = "${rabbitMQ-queue-proposta-concluida-from-analise-credito}")
    public void recebePropostaConcluida(Proposta proposta) {
        log.info("{} Recebendo proposta concluida | Aprovando proposta",
                IDENTIFICADOR_OPERACAO_PROPOSTA_CONCLUIDA_LISTENER);
        this.propostaRepository.criarProposta(proposta);
        this.webSocketService.notificar(
                propostaToPropostaResponseMapper.mapper(proposta));
    }
}

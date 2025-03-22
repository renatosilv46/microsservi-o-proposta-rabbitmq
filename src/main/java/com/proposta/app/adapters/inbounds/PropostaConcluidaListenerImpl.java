package com.proposta.app.adapters.inbounds;

import com.proposta.app.application.core.domain.Proposta;
import com.proposta.app.application.ports.inbounds.PropostaConcluidaListener;
import com.proposta.app.application.ports.outbounds.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PropostaConcluidaListenerImpl implements PropostaConcluidaListener {

    private static final Logger log = LoggerFactory.getLogger(PropostaConcluidaListener.class);
    private static final String IDENTIFICADOR_OPERACAO_PROPOSTA_CONCLUIDA_LISTENER = "[PropostaConcluidaListener]";

    private final PropostaRepository propostaRepository;

    @Override
    @RabbitListener(queues = "${rabbitMQ-queue-proposta-concluida-from-analise-credito}")
    public void recebePropostaConcluida(Proposta proposta) {
        log.info("{} Recebendo proposta concluida | Aprovando proposta",
                IDENTIFICADOR_OPERACAO_PROPOSTA_CONCLUIDA_LISTENER);
        this.propostaRepository.criarProposta(proposta);
    }
}

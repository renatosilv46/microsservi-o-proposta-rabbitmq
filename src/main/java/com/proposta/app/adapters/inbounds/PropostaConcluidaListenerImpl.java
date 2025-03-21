package com.proposta.app.adapters.inbounds;

import com.proposta.app.application.core.domain.Proposta;
import com.proposta.app.application.ports.inbounds.PropostaConcluidaListener;
import com.proposta.app.application.ports.outbounds.PropostaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListenerImpl implements PropostaConcluidaListener {

    private final PropostaRepository propostaRepository;

    public PropostaConcluidaListenerImpl(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @Override
    @RabbitListener(queues = "${rabbitMQ-queue-proposta-concluida-from-analise-credito}")
    public void recebePropostaConcluida(Proposta proposta) {
        this.propostaRepository.criarProposta(proposta);
    }
}

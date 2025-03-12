package com.proposta.app.adapters.outbounds.message;

import com.proposta.app.application.ports.outbounds.NotificacaoRabbitService;
import com.proposta.app.application.ports.outbounds.PropostaRepository;
import com.proposta.app.application.ports.outbounds.PropostasNaoIntegradas;
import org.springframework.beans.factory.annotation.Value;

public class PropostasNaoIntegradasImpl implements PropostasNaoIntegradas {

    private final PropostaRepository propostaRepository;
    private final NotificacaoRabbitService notificacaoRabbitService;
    private final String exchange;

    public PropostasNaoIntegradasImpl(
            PropostaRepository propostaRepository,
            NotificacaoRabbitService notificacaoRabbitService,
            @Value("${rabbitMQ-proposta-pendente-exchange}")
            String exchange)
    {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchange = exchange;
    }

    @Override
    public void buscarPropostaSemIntegracao() {
        propostaRepository.obterPropostasNaoIntegradas().forEach(proposta -> {
            try {
                notificacaoRabbitService.notificar(proposta, exchange);
                proposta.setIntegrada(true);
                propostaRepository.criarProposta(proposta);
            } catch(RuntimeException ex) {
            }
        });
    }
}

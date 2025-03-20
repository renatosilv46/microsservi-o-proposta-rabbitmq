package com.proposta.app.adapters.outbounds.message;

import com.proposta.app.application.core.domain.Proposta;
import com.proposta.app.application.ports.outbounds.NotificacaoRabbitService;
import com.proposta.app.application.ports.outbounds.PropostaRepository;
import com.proposta.app.application.ports.outbounds.PropostasNaoIntegradas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PropostasNaoIntegradasImpl implements PropostasNaoIntegradas {

    private static Logger log = LoggerFactory.getLogger(PropostasNaoIntegradasImpl.class);
    private static final String IDENTIFICADOR_SERVICO_PROPOSTAS_NAO_INTEGRADAS =
            "[PropostasNaoIntegradasImpl]";

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

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
    @Override
    public void buscarPropostaSemIntegracao() {
        log.info("{}: Buscando propostas não integradas",
                IDENTIFICADOR_SERVICO_PROPOSTAS_NAO_INTEGRADAS);
        propostaRepository.obterPropostasNaoIntegradas().forEach(proposta -> {
            try {
                this.notificacaoRabbitService.notificar(proposta, exchange);
                this.atualizarPropostaComStatusIntegrada(proposta);
                log.info("{}: Proposta integrada com sucesso | Proposta: {}",
                        IDENTIFICADOR_SERVICO_PROPOSTAS_NAO_INTEGRADAS, proposta);
            } catch(RuntimeException ex) {
                log.info("{}: Não foi possível integrar a proposta",
                        IDENTIFICADOR_SERVICO_PROPOSTAS_NAO_INTEGRADAS);
            }
        });
    }

    public void atualizarPropostaComStatusIntegrada(Proposta proposta) {
        log.info("{}: Atualizando proposta com status integrada | Proposta: {}",
                IDENTIFICADOR_SERVICO_PROPOSTAS_NAO_INTEGRADAS, proposta);
        proposta.setIntegrada(true);
        this.propostaRepository.criarProposta(proposta);
    }
}

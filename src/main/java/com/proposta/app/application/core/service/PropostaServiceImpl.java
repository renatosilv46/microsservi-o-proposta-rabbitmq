package com.proposta.app.application.core.service;

import com.proposta.app.application.core.domain.Proposta;
import com.proposta.app.application.ports.inbounds.PropostaService;
import com.proposta.app.application.ports.outbounds.NotificacaoRabbitService;
import com.proposta.app.application.ports.outbounds.PropostaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Slf4j
public class PropostaServiceImpl implements PropostaService {

    private static final String IDENTIFICADOR_OPERACAO_PROPOSTA_SERVICE = "[PropostaServiceImpl]";

    private final PropostaRepository propostaRepository;
    private final NotificacaoRabbitService notificacaoRabbitService;
    private final String exchangePendente;

    public PropostaServiceImpl(final PropostaRepository propostaRepository,
                               final NotificacaoRabbitService notificacaoRabbitService,
                               @Value("${rabbitMQ-proposta-pendente-exchange}")
                               final String exchangePendente)
    {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchangePendente = exchangePendente;
    }

    @Override
    public Proposta criarProposta(Proposta proposta) {
        log.info("{} Processando proposta recebida",
                IDENTIFICADOR_OPERACAO_PROPOSTA_SERVICE);
        Proposta response = propostaRepository.criarProposta(proposta);
        this.notificarRabbitMQ(response);
        log.info("{} Proposta processada e criada com sucesso",
                IDENTIFICADOR_OPERACAO_PROPOSTA_SERVICE);
        return response;
    }

    @Override
    public List<Proposta> obterPropostas() {
        log.info("{} Obtendo propostas", IDENTIFICADOR_OPERACAO_PROPOSTA_SERVICE);
        return propostaRepository.obterPropostas();
    }

    public void notificarRabbitMQ(Proposta proposta) {
        try {
            log.info("{} Notificando propostas pendentes para as queues do rabbit",
                    IDENTIFICADOR_OPERACAO_PROPOSTA_SERVICE);
            this.notificacaoRabbitService.notificar(proposta, exchangePendente);
        } catch(RuntimeException ex) {
            log.info("{} Não foi possível notificar propostas pendentes para as queues",
                    IDENTIFICADOR_OPERACAO_PROPOSTA_SERVICE);
            this.criarPropostaNaoIntegrada(proposta);
        }
    }

    public void criarPropostaNaoIntegrada(Proposta proposta) {
        log.info("{} Criando proposta não integrada",
                IDENTIFICADOR_OPERACAO_PROPOSTA_SERVICE);
        proposta.setIntegrada(false);
        this.propostaRepository.criarProposta(proposta);
    }

}

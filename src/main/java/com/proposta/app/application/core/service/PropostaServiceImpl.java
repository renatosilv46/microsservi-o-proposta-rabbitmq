package com.proposta.app.application.core.service;

import com.proposta.app.application.core.domain.Proposta;
import com.proposta.app.application.ports.inbounds.PropostaService;
import com.proposta.app.application.ports.outbounds.NotificacaoRabbitService;
import com.proposta.app.application.ports.outbounds.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaServiceImpl implements PropostaService {

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
        Proposta response = propostaRepository.criarProposta(proposta);
        this.notificarRabbitMQ(proposta);
        return response;
    }

    @Override
    public List<Proposta> obterPropostas() {
        return propostaRepository.obterPropostas();
    }

    public void notificarRabbitMQ(Proposta proposta) {
        try {
            this.notificacaoRabbitService.notificar(proposta, exchangePendente);
        } catch(RuntimeException ex) {
            this.criarPropostaNaoIntegrada(proposta);
        }
    }

    public void criarPropostaNaoIntegrada(Proposta proposta) {
        proposta.setIntegrada(false);
        this.propostaRepository.criarProposta(proposta);
    }

}

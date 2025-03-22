package com.proposta.app.adapters.outbounds.message;

import com.proposta.app.application.core.domain.Proposta;
import com.proposta.app.application.ports.outbounds.NotificacaoRabbitService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificacaoRabbitServiceImpl implements NotificacaoRabbitService {

    private static final Logger log = LoggerFactory.getLogger(NotificacaoRabbitService.class);
    private static final String IDENTIFICADOR_OPERACAO_PROPOSTA_REPOSITORY = "[NotificacaoRabbitService]";

    private RabbitTemplate rabbitTemplate;

    @Override
    public void notificar(Proposta proposta, String exchange) {
        this.rabbitTemplate.convertAndSend(exchange, "", proposta);
        log.info("{} Queue notificada com sucesso | exchange: {}",
                IDENTIFICADOR_OPERACAO_PROPOSTA_REPOSITORY, exchange);
    }
}

package com.proposta.app.adapters.outbounds.message;

import com.proposta.app.application.core.domain.Proposta;
import com.proposta.app.application.ports.outbounds.NotificacaoRabbitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificacaoRabbitServiceImpl implements NotificacaoRabbitService {

    private static final String IDENTIFICADOR_OPERACAO_PROPOSTA_REPOSITORY = "[NotificacaoRabbitService]";

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void notificar(Proposta proposta, String exchange) {
        this.rabbitTemplate.convertAndSend(exchange, "", proposta);
        log.info("{} Queue notificada com sucesso | exchange: {}",
                IDENTIFICADOR_OPERACAO_PROPOSTA_REPOSITORY, exchange);
    }
}

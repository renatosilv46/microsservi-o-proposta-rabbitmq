package com.proposta.app.adapters.outbounds.message;

import com.proposta.app.application.core.domain.Proposta;
import com.proposta.app.application.ports.outbounds.NotificacaoRabbitService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoRabbitServiceImpl implements NotificacaoRabbitService {

    private RabbitTemplate rabbitTemplate;

    public NotificacaoRabbitServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;

    }

    @Override
    public void notificar(Proposta proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}

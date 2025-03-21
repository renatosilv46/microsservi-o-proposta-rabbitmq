package com.proposta.app.config;

import com.proposta.app.application.core.service.PropostaServiceImpl;
import com.proposta.app.application.ports.inbounds.PropostaService;
import com.proposta.app.application.ports.outbounds.NotificacaoRabbitService;
import com.proposta.app.application.ports.outbounds.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public PropostaService propostaService(
            PropostaRepository propostaRepository,
            NotificacaoRabbitService notificacaoRabbitService,
            @Value("${rabbitMQ-proposta-pendente-exchange}") String exchangePendente
    ) {
        return new PropostaServiceImpl(propostaRepository, notificacaoRabbitService, exchangePendente);
    }
}

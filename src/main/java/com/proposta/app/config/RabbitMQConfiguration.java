package com.proposta.app.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitMQ-proposta-pendente-exchange}")
    private String exchangePendente;
    @Value("${rabbitMQ-proposta-concluida-exchange}")
    private String exchangeConcluida;

    // Criação das filas
    @Bean
    public Queue criarFilaPropostaPendenteToAnaliseCredito() {
        return QueueBuilder.durable("proposta-pendente.to.analise-credito").build();
    }

    @Bean
    public Queue criarFilaPropostaPendenteToNotificacao() {
        return QueueBuilder.durable("proposta-pendente.to.notificacao").build();
    }

    @Bean
    public Queue criarFilaPropostaConcluidaToProposta() {
        return QueueBuilder.durable("proposta-concluida.to.proposta").build();
    }

    @Bean
    public Queue criarFilaPropostaConcluidaToNotificacao() {
        return QueueBuilder.durable("proposta-concluida.to.notificacao").build();
    }

    // Criação do RabbitAdmin
    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    // Inicializar RabbitAdmin
    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializarAdmin(RabbitAdmin admin) {
        return event -> admin.initialize();
    }

    // Criação da exchange, responsável por enviar as mensagens para as filas
    @Bean
    public FanoutExchange criarFanoutExchangePropostaPendente() {
        return ExchangeBuilder.fanoutExchange(exchangePendente).build();
    }

    @Bean
    public FanoutExchange criarFanoutExchangePropostaConcluida() {
        return ExchangeBuilder.fanoutExchange(exchangeConcluida).build();
    }

    // Criação do binding responsável por ligar a exchange a uma determinada fila
    @Bean
    public Binding criarBindingPropostaPendenteToAnaliseCredito() {
        return BindingBuilder.bind(criarFilaPropostaPendenteToAnaliseCredito())
                .to(criarFanoutExchangePropostaPendente());
    }

    @Bean
    public Binding criarBindingPropostaPendenteToNotificacao() {
        return BindingBuilder.bind(criarFilaPropostaPendenteToNotificacao())
                .to(criarFanoutExchangePropostaPendente());
    }

    @Bean
    public Binding criarBindingPropostaConcluidaToProposta() {
        return BindingBuilder.bind(criarFilaPropostaConcluidaToProposta())
                .to(criarFanoutExchangePropostaConcluida());
    }

    @Bean
    public Binding criarBindingPropostaConcluidaToNotificacao() {
        return BindingBuilder.bind(criarFilaPropostaConcluidaToNotificacao())
                .to(criarFanoutExchangePropostaConcluida());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}

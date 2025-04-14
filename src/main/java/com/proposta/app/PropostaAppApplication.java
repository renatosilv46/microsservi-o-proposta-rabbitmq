package com.proposta.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@EnableScheduling
@EnableWebSocketMessageBroker
@SpringBootApplication
public class PropostaAppApplication {

	private static final Logger log = LoggerFactory.getLogger(PropostaAppApplication.class);
	private static final String IDENTIFICADOR_OPERACAO_PROPOSTA_APPLICATION = "[PropostaApplication]";

	public static void main(String[] args) {
		SpringApplication.run(PropostaAppApplication.class, args);
		log.info("{} Aplicação inicializada com sucesso na porta: 8080",
				IDENTIFICADOR_OPERACAO_PROPOSTA_APPLICATION);
	}

}

package com.proposta.app;

import com.proposta.app.adapters.outbounds.client.PropostaRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
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

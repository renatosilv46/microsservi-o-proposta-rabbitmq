package com.proposta.app.adapters.inbounds.models;

import io.swagger.v3.oas.annotations.media.Schema;

public record PropostaRequest(
        @Schema(description = "Primeiro Nome do cliente solicitante", example = "Fulano") String nome,
        @Schema(description = "Sobrenome do cliente solicitante", example = "de Beltrano") String sobrenome,
        @Schema(description = "Telefone do cliente solicitante", example = "5511912345678") String telefone,
        @Schema(description = "CPF do cliente solicitante", example = "12345678901") String cpf,
        @Schema(description = "Renda do cliente solicitante", example = "1000.00") Double renda,
        @Schema(description = "Valor solicitado para empréstimo", example = "1000.00") Double valorSolicitado,
        @Schema(description = "Quantidade de meses para pagamento", example = "12") int prazoPagamento
) {
}

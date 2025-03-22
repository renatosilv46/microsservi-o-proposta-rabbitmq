package com.proposta.app.adapters.inbounds.models;

import io.swagger.v3.oas.annotations.media.Schema;

public record PropostaResponse(
        @Schema(description = "ID da proposta criada", example = "1")long id,
        @Schema(description = "Primeiro Nome do cliente solicitante", example = "Fulano") String nome,
        @Schema(description = "Sobrenome do cliente solicitante", example = "de Beltrano") String sobrenome,
        @Schema(description = "Telefone do cliente solicitante", example = "5511912345678") String telefone,
        @Schema(description = "CPF do cliente solicitante", example = "12345678901") String cpf,
        @Schema(description = "Renda do cliente solicitante", example = "1000") Double renda,
        @Schema(description = "Valor solicitado para empréstimo", example = "R$ 1000,00")String valorSolicitadoFmt,
        @Schema(description = "Prazo de pagamento do empréstimo em meses", example = "10") int prazoPagamento,
        @Schema(description = "Status da proposta", example = "true") Boolean aprovada,
        @Schema(description = "Motivo recusa da proposta", example = "Proposta recusada por...") String observacao
) {
}

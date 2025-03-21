package com.proposta.app.adapters.inbounds.models;

public record PropostaResponse(
        long id,
        String nome,
        String sobrenome,
        String telefone,
        String cpf,
        Double renda,
        String valorSolicitadoFmt,
        int prazoPagamento,
        Boolean aprovada,
        String observacao
) {
}

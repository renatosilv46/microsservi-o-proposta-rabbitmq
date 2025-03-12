package com.proposta.app.adapters.inbounds.models;

public record PropostaRequest(
        String nome,
        String sobrenome,
        String telefone,
        String cpf,
        Double renda,
        Double valorSolicitado,
        int prazoPagamento
) {
}

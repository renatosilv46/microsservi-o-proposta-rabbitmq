package com.proposta.app.adapters.inbounds.mapper;

import com.proposta.app.adapters.inbounds.models.PropostaResponse;
import com.proposta.app.application.core.domain.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.NumberFormat;

@Mapper(componentModel = "spring")
public interface PropostaToPropostaResponseMapper {

    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "sobrenome", source = "usuario.sobrenome")
    @Mapping(target = "telefone", source = "usuario.telefone")
    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "renda", source = "usuario.renda")
    @Mapping(target = "valorSolicitadoFmt", expression = "java(setValorSolicitadoFmt(proposta))")
    PropostaResponse mapper(Proposta proposta);

    default String setValorSolicitadoFmt(Proposta proposta) {
        return NumberFormat.getCurrencyInstance().format(proposta.getValorSolicitado());
    }

}

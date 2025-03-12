package com.proposta.app.adapters.inbounds.mapper;

import com.proposta.app.adapters.inbounds.models.PropostaRequest;
import com.proposta.app.application.core.domain.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PropostaRequestToPropostaMapper {

    @Mappings({
            @Mapping(target = "usuario.nome", source = "nome"),
            @Mapping(target = "usuario.sobrenome", source = "sobrenome"),
            @Mapping(target = "usuario.telefone", source = "telefone"),
            @Mapping(target = "usuario.cpf", source = "cpf"),
            @Mapping(target = "usuario.renda", source = "renda"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "aprovado", ignore = true),
            @Mapping(target = "integrada", constant = "true"),
            @Mapping(target = "observacao", ignore = true),
            @Mapping(target = "usuario", ignore = true)
    })
    Proposta mapper(PropostaRequest propostaRequest);
}

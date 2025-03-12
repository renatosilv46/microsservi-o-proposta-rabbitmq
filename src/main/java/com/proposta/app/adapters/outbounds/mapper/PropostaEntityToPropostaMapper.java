package com.proposta.app.adapters.outbounds.mapper;

import com.proposta.app.adapters.outbounds.models.PropostaEntity;
import com.proposta.app.application.core.domain.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropostaEntityToPropostaMapper {
    @Mapping(target = "usuario.proposta", ignore = true)
    Proposta mapper(PropostaEntity entity);
}

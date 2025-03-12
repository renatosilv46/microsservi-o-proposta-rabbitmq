package com.proposta.app.adapters.outbounds.mapper;

import com.proposta.app.adapters.outbounds.models.PropostaEntity;
import com.proposta.app.application.core.domain.Proposta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropostaToPropostaEntityMapper {
    PropostaEntity mapper(Proposta proposta);
}

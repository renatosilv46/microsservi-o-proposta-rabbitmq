package com.proposta.app.adapters.outbounds.client;

import com.proposta.app.adapters.outbounds.models.PropostaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepositoryJpa extends JpaRepository<PropostaEntity, Long> {

    // Query derivada
    List<PropostaEntity> findAllByIntegradaIsFalse();
}

package com.proposta.app.adapters.outbounds.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PropostaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorSolicitado;
    private int prazoPagamento;
    private Boolean aprovado;
    private boolean integrada;
    private String observacao;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(Double valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }

    public int getPrazoPagamento() {
        return prazoPagamento;
    }

    public void setPrazoPagamento(int prazoPagamento) {
        this.prazoPagamento = prazoPagamento;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }

    public boolean isIntegrada() {
        return integrada;
    }

    public void setIntegrada(boolean integrada) {
        this.integrada = integrada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}

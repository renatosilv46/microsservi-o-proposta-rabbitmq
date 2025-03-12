package com.proposta.app.application.core.domain;

public class Proposta {
    private Long id;
    private Double valorSolicitado;
    private int prazoPagamento;
    private Boolean aprovado;
    private boolean integrada;
    private String observacao;
    private Usuario usuario;

    public Proposta() {};

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Proposta(
        Long id,
        Double valorSolicitado,
        int prazoPagamento,
        Boolean aprovado,
        boolean integrada,
        String observacao
    ){
        this.id = id;
        this.valorSolicitado = valorSolicitado;
        this.prazoPagamento = prazoPagamento;
        this.aprovado = aprovado;
        this.integrada = integrada;
        this.observacao = observacao;
    }

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
}

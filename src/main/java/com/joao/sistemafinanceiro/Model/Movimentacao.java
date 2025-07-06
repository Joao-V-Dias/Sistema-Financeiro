package com.joao.sistemafinanceiro.Model;

import java.util.Date;

public class Movimentacao {
    private int id;
    private Banco banco;
    private TituloCobranca titulo;
    private String tipo;
    private Date dataOperacao;
    private String observacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public TituloCobranca getTituloCobranca() {
        return titulo;
    }

    public void setTituloCobranca(TituloCobranca titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}

package com.joao.sistemafinanceiro.Model;

import java.util.Date;

public class DocumentoFiscal {
    private int id;
    private String tipo;
    private String numero;
    private Date dataEmissao;
    private double valorTotal;
    private Parceiro emitente;
    private Parceiro remetente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Parceiro getEmitente() {
        return emitente;
    }

    public void setEmitente(Parceiro emitente) {
        this.emitente = emitente;
    }

    public Parceiro getRemetente() {
        return remetente;
    }

    public void setRemetente(Parceiro remetente) {
        this.remetente = remetente;
    }
}

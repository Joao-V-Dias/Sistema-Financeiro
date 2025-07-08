package com.joao.sistemafinanceiro.Model;

import java.util.Date;
import java.util.List;

public class DocumentoFiscal {
    private String tipo;
    private String numero;
    private Date dataEmissao;
    private double valorTotal;
    private Parceiro cliente;
    private Parceiro fornecedor;
    private List<TituloCobranca> titulos;

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

    public Parceiro getCliente() {
        return cliente;
    }

    public void setCliente(Parceiro cliente) {
        this.cliente = cliente;
    }

    public Parceiro getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Parceiro fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<TituloCobranca> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<TituloCobranca> titulos) {
        this.titulos = titulos;
    }
}

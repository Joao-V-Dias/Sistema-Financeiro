package com.joao.sistemafinanceiro.Model;

import java.util.Date;

public class TituloCobranca {
    private int id;
    private DocumentoFiscal documento;
    private Date dataVencimento;
    private double valor;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DocumentoFiscal getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoFiscal documento) {
        this.documento = documento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

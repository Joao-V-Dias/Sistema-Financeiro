package com.joao.sistemafinanceiro.Service;

import com.joao.sistemafinanceiro.DAO.DocumentoFiscalDAO;
import com.joao.sistemafinanceiro.DAO.TituloCobrancaDAO;
import com.joao.sistemafinanceiro.Model.DocumentoFiscal;
import com.joao.sistemafinanceiro.Model.TituloCobranca;

import java.util.List;

public class DocumentoFiscalService {
    DocumentoFiscalDAO dDAO;
    TituloCobrancaDAO tDAO;

    public DocumentoFiscalService() {
        this.dDAO = new DocumentoFiscalDAO();
        this.tDAO = new TituloCobrancaDAO();
    }

    public void salvar(DocumentoFiscal d) {
        dDAO.salvar(d);
        for(TituloCobranca t : d.getTitulos()){
            tDAO.salvar(t);
        }
    }

    public void consultarDocumentos() {
        List<DocumentoFiscal> lstD = dDAO.consultarTodos();
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-20s | %-18s | %-20s | %-18s | %-6s | %-12s | %-12s | %-13s |\n", "Cliente", "Doc Cliente", "Fornecedor", "Doc Fornecedor", "Tipo", "Número", "Emissão", "Valor");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
        for (DocumentoFiscal d : lstD) {
            consultar(d);
        }
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+\n\n");
    }

    public void consultar(DocumentoFiscal d) {
        System.out.printf("| %-20s | %-18s | %-20s | %-18s | %-6s | %-12s | %-12s | R$ %-10.2f |\n", d.getCliente().getNome(), d.getCliente().getDocumento(), d.getFornecedor().getNome(), d.getFornecedor().getDocumento(), d.getTipo(), d.getNumero(), d.getDataEmissao().toString(), d.getValorTotal());

    }

    public void atualizar(DocumentoFiscal d){
        dDAO.atualizar(d);
    }

    public void excluir(String numero){
        dDAO.excluir(numero);
    }
}

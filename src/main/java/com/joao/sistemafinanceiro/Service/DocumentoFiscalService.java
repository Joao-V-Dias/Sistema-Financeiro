package com.joao.sistemafinanceiro.Service;

import com.joao.sistemafinanceiro.DAO.DocumentoFiscalDAO;
import com.joao.sistemafinanceiro.Model.DocumentoFiscal;

import java.util.List;

public class DocumentoFiscalService {
    DocumentoFiscalDAO dDAO;

    public DocumentoFiscalService() {
        this.dDAO = new DocumentoFiscalDAO();
    }

    public void salvar(DocumentoFiscal d) {
        dDAO.salvar(d);
    }

    public void consultarDocumentos() {
        List<DocumentoFiscal> lstD = dDAO.consultarTodos();
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-20s | %-18s | %-20s | %-18s | %-6s | %-12s | %-12s | %-13s |\n", "Emitente", "Doc Emitente", "Remetente", "Doc Remetente", "Tipo", "Número", "Emissão", "Valor");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
        for (DocumentoFiscal d : lstD) {
            consultar(d);
        }
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+\n\n");
    }

    public void consultar(DocumentoFiscal d) {
        System.out.printf("| %-20s | %-18s | %-20s | %-18s | %-6s | %-12s | %-12s | R$ %-10.2f |\n", d.getEmitente().getNome(), d.getEmitente().getDocumento(), d.getRemetente().getNome(), d.getRemetente().getDocumento(), d.getTipo(), d.getNumero(), d.getDataEmissao().toString(), d.getValorTotal());

    }

    public void atualizar(){}

    public void excluir(String numero){
        dDAO.excluir(numero);
    }
}

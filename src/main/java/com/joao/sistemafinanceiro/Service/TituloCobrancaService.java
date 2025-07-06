package com.joao.sistemafinanceiro.Service;

import com.joao.sistemafinanceiro.DAO.TituloCobrancaDAO;
import com.joao.sistemafinanceiro.Model.TituloCobranca;

import java.util.List;

public class TituloCobrancaService {
    TituloCobrancaDAO tDAO;

    public TituloCobrancaService() {
        this.tDAO = new TituloCobrancaDAO();
    }

    public void salvar(TituloCobranca t) {
        tDAO.salvar(t);
    }

    public void consultarTodos() {
        List<TituloCobranca> lstT = tDAO.consultarTodos();
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.printf("| %-3s | %-12s | %-12s | %-12s | %-10s |\n", "ID", "Documento ID", "Vencimento", "Valor", "Status");
        System.out.println("+----------------------------------------------------------------------------+");
        for (TituloCobranca t : lstT) {
            consultar(t);
        }
        System.out.println("+----------------------------------------------------------------------------+\n\n");
    }

    public void consultar(TituloCobranca t) {
        System.out.printf("| %-3d | %-12d | %-12s | R$ %-12.2f | %-10s |\n", t.getId(), t.getDocumento(), t.getDataVencimento().toString(), t.getValor(), t.getStatus());
    }
}

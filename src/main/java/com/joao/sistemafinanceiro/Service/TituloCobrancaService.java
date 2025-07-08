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
        System.out.printf("| %-3s | %-25s | %-12s | %-12s | %-10s |\n", "ID", "Referente ao Documento", "Vencimento", "Valor", "Status");
        System.out.println("+----------------------------------------------------------------------------+");
        for (TituloCobranca t : lstT) {
            consultar(t);
        }
        System.out.println("+----------------------------------------------------------------------------+\n\n");
    }

    public void consultarTodos(String numero) {
        List<TituloCobranca> lstT = tDAO.consultarTodos(numero);
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.printf("| %-3s | %-25s | %-12s | %-12s | %-10s |\n", "ID", "Referente ao Documento", "Vencimento", "Valor", "Status");
        System.out.println("+----------------------------------------------------------------------------+");
        for (TituloCobranca t : lstT) {
            consultar(t);
        }
        System.out.println("+----------------------------------------------------------------------------+\n\n");
    }

    public void consultar(TituloCobranca t) {
        System.out.printf("| %-3d | %-25s | %-12s | R$ %-9.2f | %-10s |\n", t.getId(), t.getDocumento().getNumero(), t.getDataVencimento().toString(), t.getValor(), t.getStatus());
    }

    public void atualizar(TituloCobranca t){
        tDAO.atualizar(t);
    }

    public void excluir(int id){
        tDAO.excluir(id);
    }
}

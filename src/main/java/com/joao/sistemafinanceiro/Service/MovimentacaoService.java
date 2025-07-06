package com.joao.sistemafinanceiro.Service;

import com.joao.sistemafinanceiro.DAO.MovimentacaoDAO;
import com.joao.sistemafinanceiro.Model.Movimentacao;

import java.util.List;

public class MovimentacaoService {
    MovimentacaoDAO mDAO;

    public MovimentacaoService() {
        this.mDAO = new MovimentacaoDAO();
    }

    public void salvar(Movimentacao m) {
        mDAO.salvar(m);
    }

    public void consultarTodos() {
        List<Movimentacao> lstM = mDAO.consultarTodos();
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-4s | %-19s | %-8s | %-20s | %-16s | %-12s | %-20s | %-20s |\n", "ID", "Data Operação", "Tipo", "Banco", "Documento", "Emissão", "Emitente", "Remetente");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
        for (Movimentacao m : lstM) {
            consultar(m);
        }
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+\n\n");
    }

    public void consultar(Movimentacao m) {
        System.out.printf("| %-4d | %-19s | %-8s | %-20s | %-16s | %-12s | %-20s | %-20s |\n", m.getId(), m.getDataOperacao().toString(), m.getTipo(), m.getBanco().getNome(), m.getDocumento().getNumero(), m.getDocumento().getDataEmissao().toString(), m.getDocumento().getEmitente().getNome(), m.getDocumento().getRemetente().getNome());
    }

    public void excluir(int id){
        mDAO.excluir(id);
    }
}

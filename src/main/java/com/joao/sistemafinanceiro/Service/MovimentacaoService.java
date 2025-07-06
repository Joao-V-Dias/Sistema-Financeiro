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
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-4s | %-24s | %-8s | %-20s | %-16s | %-20s | %-20s |\n", "ID", "Data Operação", "Tipo", "Banco", "Documento", "Fornecedor", "Cliente", "Fornecedor");
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------+");
        for (Movimentacao m : lstM) {
            consultar(m);
        }
        System.out.println(
                "+------------------------------------------------------------------------------------------------------------------------------------+\n\n");
    }

    public void consultarPorId(int id) {
        Movimentacao m = mDAO.consultarPorId(id);
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-4s | %-24s | %-8s | %-20s | %-16s | %-20s | %-20s |\n", "ID", "Data Operação", "Tipo", "Banco", "Documento", "Emissão", "Fornecedor", "Cliente");
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------+");
        consultar(m);
        System.out.println(
                "+------------------------------------------------------------------------------------------------------------------------------------+\n\n");
    }

    public void consultar(Movimentacao m) {
        System.out.printf("| %-4d | %-24s | %-8s | %-20s | %-16s | %-20s | %-20s |\n", m.getId(), m.getDataOperacao().toString(), m.getTipo(), m.getBanco().getNome(), m.getTituloCobranca().getDocumento().getNumero(), m.getTituloCobranca().getDocumento().getCliente().getNome(), m.getTituloCobranca().getDocumento().getFornecedor().getNome());
    }

    public void atualizar(Movimentacao m) {
        mDAO.atualizar(m);
    }

    public void excluir(int id) {
        mDAO.excluir(id);
    }
}

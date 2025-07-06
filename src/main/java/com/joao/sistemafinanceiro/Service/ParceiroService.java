package com.joao.sistemafinanceiro.Service;

import com.joao.sistemafinanceiro.DAO.ParceiroDAO;
import com.joao.sistemafinanceiro.Model.Parceiro;

import java.util.List;

/**
 * @author Joao
 * @version 1.0
 * @since Jul 5, 2025
 */
public class ParceiroService {

    ParceiroDAO pDAO;

    public ParceiroService() {
        this.pDAO = new ParceiroDAO();
    }

    public void salvar(Parceiro p) {
        pDAO.salvar(p);
    }

    public void consultarTodos() {
        List<Parceiro> lstP = pDAO.consultarTodos();
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-20s | %-18s | %-11s | %-25s | %-15s |\n", "Nome", "Documento", "Tipo", "Email", "Telefone");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
        for (Parceiro p : lstP) {
            consultar(p);
        }
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------+\n\n");
    }

    public void consultar(Parceiro p) {
        System.out.printf("| %-20s | %-18s | %-11s | %-25s | %-15s |\n", p.getNome(), p.getDocumento(), p.getTipo(), p.getEmail(), p.getTelefone());
    }

    public void atualizar(Parceiro p){

    }

    public void excluir(String documento){
        pDAO.excluir(documento);
    }
}

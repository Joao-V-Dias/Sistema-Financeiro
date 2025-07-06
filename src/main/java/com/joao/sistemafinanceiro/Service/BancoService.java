package com.joao.sistemafinanceiro.Service;

import com.joao.sistemafinanceiro.DAO.BancoDAO;
import com.joao.sistemafinanceiro.Model.Banco;

import java.util.List;

/**
 * @author Joao
 * @version 1.1
 * @since Jul 5, 2025
 */
public class BancoService {

    BancoDAO bDAO;

    public BancoService() {
        this.bDAO = new BancoDAO();
    }

    public void salvar(Banco b) {
        bDAO.salvar(b);
    }

    public void consultarTodos() {
        List<Banco> lstB = bDAO.consultarTodos();
        System.out.println("+--------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-40s | %-12s | %-20s | %-14s | %-16s |\n", "Nome", "Agencia", "Conta", "Tipo", "Saldo");
        System.out.println("+--------------------------------------------------------------------------------------------------------------------+");
        for (Banco b : lstB) {
            consultar(b);
        }
        System.out.println("+--------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-95s | R$ %-13.2f |\n", "TOTAL", saldoTotal());
        System.out.println("+--------------------------------------------------------------------------------------------------------------------+\n\n");
    }

    public double saldoTotal() {
        return bDAO.consultarSaldoTotal();
    }

    public void consultar(Banco b) {
        System.out.printf("| %-40s | %-12s | %-20s | %-14s | R$ %-13.2f |\n", b.getNome(), b.getAgencia(), b.getConta(), b.getTipo(), b.getSaldo());
    }

    public void atualizar(Banco b) {
        bDAO.atualizar(b);
    }

    public void excluir(String agencia, String conta) {
        bDAO.excluir(agencia, conta);
    }
}

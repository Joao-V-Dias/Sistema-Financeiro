package com.joao.sistemafinanceiro.Service;

import com.joao.sistemafinanceiro.DAO.BancoDAO;
import com.joao.sistemafinanceiro.Model.Banco;
import java.util.List;

/**
 * @author Joao
 * @version 1.0
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
        List<Banco> lstB = bDAO.consultarBancos();
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-3s | %-40s | %-12s | %-20s | %-14s | %-16s |\n",
                "ID", "Nome", "Agencia", "Conta", "Tipo", "Saldo");
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------+");
        for (Banco b : lstB) {
            consultar(b);
        }
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-101s | %-16.2f |\n", "TOTAL", saldoTotal());
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------+\n\n");
    }
    
    public double saldoTotal(){
        return bDAO.consultarSaldoTotal();
    }

    public void consultar(Banco b) {
        System.out.printf("| %-3d | %-40s | %-12s | %-20s | %-14s | R$ %-13.2f |\n",
                b.getId(), b.getNome(), b.getAgencia(),
                b.getConta(), b.getTipo(), b.getSaldo());
    }

    public void editarBanco(Banco b) {

    }

    public void excluirBanco(int id) {
        
    }
}

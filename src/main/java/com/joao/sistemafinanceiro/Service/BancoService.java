package com.joao.sistemafinanceiro.Service;

import com.joao.sistemafinanceiro.DAO.BancoDAO;
import com.joao.sistemafinanceiro.Model.Banco;
import com.joao.sistemafinanceiro.Service.Generic.GenericService;

import java.util.List;

public class BancoService implements GenericService<Banco>{

	BancoDAO bDAO;

	public BancoService(){
		this.bDAO = new BancoDAO();
	}

	/**
	 * Salvar tupla no banco de dados
	 *
	 * @param b que será salvo
	 */
	@Override
	public void salvar(Banco b){
		bDAO.salvar(b);
	}

	/**
	 * Buscar todas as tuplas de uma tabela no banco de dados
	 *
	 * @return
	 */
	@Override
	public void buscarTodos(){
		List<Banco> lstB = bDAO.buscarTodos();
		System.out.println("+--------------------------------------------------------------------------------------------------------------------+");
		System.out.printf("| %-40s | %-12s | %-20s | %-14s | %-16s |\n", "Nome", "Agencia", "Conta", "Tipo", "Saldo");
		System.out.println("+--------------------------------------------------------------------------------------------------------------------+");
		for(Banco b : lstB){
			buscar(b);
		}
		System.out.println("+--------------------------------------------------------------------------------------------------------------------+");
		System.out.printf("| %-95s | R$ %-13.2f |\n", "TOTAL", saldoTotal());
		System.out.println("+--------------------------------------------------------------------------------------------------------------------+\n\n");
	}

	/**
	 * Transforma em String os dados de uma tupla
	 */
	@Override
	public void buscar(Banco b){
		System.out.printf("| %-40s | %-12s | %-20s | %-14s | R$ %-13.2f |\n", b.getNome(), b.getAgencia(), b.getConta(), b.getTipo(), b.getSaldo());
	}

	/**
	 * Editar tupla e salvar no banco de dados
	 *
	 * @param b Será o objeto utilizado para sobrescrever o que ja esta no banco de dados
	 */
	@Override
	public void editar(Banco b){
		bDAO.editar(b);
	}

	public void excluir(String agencia, String conta){
		bDAO.deletarPorId(agencia, conta);
	}

	public double saldoTotal(){
		return bDAO.consultarSaldoTotal();
	}
}

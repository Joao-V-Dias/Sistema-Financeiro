package com.joao.sistemafinanceiro.Service;

import com.joao.sistemafinanceiro.DAO.ParceiroDAO;
import com.joao.sistemafinanceiro.Model.Parceiro;
import com.joao.sistemafinanceiro.Service.Generic.GenericService;

import java.util.List;

public class ParceiroService implements GenericService<Parceiro>{

	ParceiroDAO pDAO;

	public ParceiroService(){
		this.pDAO = new ParceiroDAO();
	}

	/**
	 * Salvar tupla no banco de dados
	 *
	 * @param p que será salvo
	 */
	@Override
	public void salvar(Parceiro p){
		pDAO.salvar(p);
	}

	/**
	 * Buscar todas as tuplas de uma tabela no banco de dados
	 */
	@Override
	public void buscarTodos(){
		List<Parceiro> lstP = pDAO.buscarTodos();
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.printf("| %-20s | %-18s | %-11s | %-25s | %-15s |\n", "Nome", "Documento", "Tipo", "Email", "Telefone");
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		for(Parceiro p : lstP){
			buscar(p);
		}
		System.out.println("+-------------------------------------------------------------------------------------------------------+\n\n");
	}

	public void buscarTodos(String tipo){
		List<Parceiro> lstP = pDAO.buscarTodos(tipo);
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.printf("| %-20s | %-18s | %-11s | %-25s | %-15s |\n", "Nome", "Documento", "Tipo", "Email", "Telefone");
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		for(Parceiro p : lstP){
			buscar(p);
		}
		System.out.println("+-------------------------------------------------------------------------------------------------------+\n\n");
	}

	/**
	 * Transforma em String os dados de uma tupla
	 *
	 * @param p
	 */
	@Override
	public void buscar(Parceiro p){
		System.out.printf("| %-20s | %-18s | %-11s | %-25s | %-15s |\n", p.getNome(), p.getDocumento(), p.getTipo(), p.getEmail(), p.getTelefone());
	}

	/**
	 * Editar tupla e salvar no banco de dados
	 *
	 * @param p Será o objeto utilizado para sobrescrever o que ja esta no banco de dados
	 */
	@Override
	public void editar(Parceiro p){
		pDAO.editar(p);
	}

	public void excluir(String documento){
		pDAO.excluir(documento);
	}
}

package com.joao.sistemafinanceiro.Service;

import com.joao.sistemafinanceiro.DAO.TituloCobrancaDAO;
import com.joao.sistemafinanceiro.Model.TituloCobranca;
import com.joao.sistemafinanceiro.Service.Generic.GenericService;

import java.util.List;

public class TituloCobrancaService implements GenericService<TituloCobranca>{
	TituloCobrancaDAO tDAO;

	public TituloCobrancaService(){
		this.tDAO = new TituloCobrancaDAO();
	}

	/**
	 * Salvar tupla no banco de dados
	 *
	 * @param t que será salvo
	 */
	@Override
	public void salvar(TituloCobranca t){
		tDAO.salvar(t);
	}

	/**
	 * Buscar todas as tuplas de uma tabela no banco de dados
	 */
	@Override
	public void buscarTodos(){
		List<TituloCobranca> lstT = tDAO.buscarTodos();
		System.out.println("+----------------------------------------------------------------------------+");
		System.out.printf("| %-3s | %-25s | %-12s | %-12s | %-10s |\n", "ID", "Referente ao Documento", "Vencimento", "Valor", "Status");
		System.out.println("+----------------------------------------------------------------------------+");
		for(TituloCobranca t : lstT){
			buscar(t);
		}
		System.out.println("+----------------------------------------------------------------------------+\n\n");
	}

	public void consultarTodos(String numero){
		List<TituloCobranca> lstT = tDAO.buscarTodos(numero);
		System.out.println("+----------------------------------------------------------------------------+");
		System.out.printf("| %-3s | %-25s | %-12s | %-12s | %-10s |\n", "ID", "Referente ao Documento", "Vencimento", "Valor", "Status");
		System.out.println("+----------------------------------------------------------------------------+");
		for(TituloCobranca t : lstT){
			buscar(t);
		}
		System.out.println("+----------------------------------------------------------------------------+\n\n");
	}

	/**
	 * Transforma em String os dados de uma tupla
	 *
	 * @param t
	 */
	@Override
	public void buscar(TituloCobranca t){
		System.out.printf("| %-3d | %-25s | %-12s | R$ %-9.2f | %-10s |\n", t.getId(), t.getDocumento().getNumero(), t.getDataVencimento().toString(), t.getValor(), t.getStatus());
	}

	/**
	 * Editar tupla e salvar no banco de dados
	 *
	 * @param t Será o objeto utilizado para sobrescrever o que ja esta no banco de dados
	 */
	@Override
	public void editar(TituloCobranca t){
		tDAO.editar(t);
	}

	public void excluir(int id){
		tDAO.excluir(id);
	}
}

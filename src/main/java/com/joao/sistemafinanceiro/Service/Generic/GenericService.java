package com.joao.sistemafinanceiro.Service.Generic;

public interface GenericService<T>{
	/**
	 * Salvar tupla no banco de dados
	 *
	 * @param objeto que será salvo
	 */
	void salvar(T objeto);

	/**
	 * Buscar todas as tuplas de uma tabela no banco de dados
	 */
	void buscarTodos();

	/**
	 * Transforma em String os dados de uma tupla
	 */
	void buscar(T objeto);

	/**
	 * Editar tupla e salvar no banco de dados
	 *
	 * @param objeto Será o objeto utilizado para sobrescrever o que ja esta no banco de dados
	 */
	void editar(T objeto);
}

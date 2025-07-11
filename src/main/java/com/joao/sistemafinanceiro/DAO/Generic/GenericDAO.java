package com.joao.sistemafinanceiro.DAO.Generic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T>{
	/**
	 * Salvar tupla no banco de dados
	 *
	 * @param objeto que será salvo
	 */
	void salvar(T objeto);

	/**
	 * Buscar todas as tuplas de uma tabela no banco de dados
	 *
	 * @return
	 */
	List<T> buscarTodos();

	/**
	 * Constrói um objeto a partir da tupla atual do {@code ResultSet}.
	 *
	 * @param rs Resultado da consulta SQL já posicionado em uma linha válida.
	 * @return Objeto populado com os dados da linha atual do {@code ResultSet}.
	 * @throws SQLException Caso ocorra erro ao acessar os dados do {@code ResultSet}.
	 */
	T consultarResult(ResultSet rs) throws SQLException;

	/**
	 * Editar tupla e salvar no banco de dados
	 *
	 * @param objeto Será o objeto utilizado para sobrescrever o que ja esta no banco de dados
	 */
	void editar(T objeto);
}

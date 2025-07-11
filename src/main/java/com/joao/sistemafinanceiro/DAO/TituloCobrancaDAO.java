package com.joao.sistemafinanceiro.DAO;

import com.joao.sistemafinanceiro.DAO.Generic.GenericDAO;
import com.joao.sistemafinanceiro.Model.DocumentoFiscal;
import com.joao.sistemafinanceiro.Model.TituloCobranca;
import com.joao.sistemafinanceiro.Util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TituloCobrancaDAO implements GenericDAO<TituloCobranca>{
	Connection conn;

	public TituloCobrancaDAO(){
		this.conn = new Conexao().conectar();
	}

	@Override
	public void salvar(TituloCobranca t){
		try{
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO titulo_cobranca (numero_documento, data_vencimento, valor) VALUES (?, ?, ?)");
			stmt.setString(1, t.getDocumento().getNumero());
			stmt.setDate(2, (Date) t.getDataVencimento());
			stmt.setDouble(3, t.getValor());
			int verifica = stmt.executeUpdate();
			if(verifica > 0) System.out.println("Titulo cadastrado com sucesso!");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<TituloCobranca> buscarTodos(){
		List<TituloCobranca> lstT = new ArrayList<>();
		ResultSet rs;
		try{
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM titulo_cobranca");
			rs = stmt.executeQuery();
			while(rs.next()){
				lstT.add(consultarResult(rs));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return lstT;
	}

	public List<TituloCobranca> buscarTodos(String numero){
		List<TituloCobranca> lstT = new ArrayList<>();
		ResultSet rs;
		try{
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM titulo_cobranca WHERE numero_documento = ?");
			stmt.setString(1, numero);
			rs = stmt.executeQuery();
			while(rs.next()){
				lstT.add(consultarResult(rs));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return lstT;
	}

	@Override
	public TituloCobranca consultarResult(ResultSet rs) throws SQLException{
		TituloCobranca t = new TituloCobranca();
		DocumentoFiscal d = new DocumentoFiscal();

		t.setId(rs.getInt("id"));
		d.setNumero(rs.getString("numero_documento"));
		t.setDocumento(d);
		t.setDataVencimento(rs.getDate("data_vencimento"));
		t.setValor(rs.getDouble("valor"));
		t.setStatus(rs.getString("status"));

		return t;
	}

	@Override
	public void editar(TituloCobranca t){
		try{
			PreparedStatement stmt = conn.prepareStatement("UPDATE titulo_cobranca SET numero_documento = ?, data_vencimento = ?, valor = ? WHERE id = ?");
			stmt.setString(1, t.getDocumento().getNumero());
			stmt.setDate(2, (Date) t.getDataVencimento());
			stmt.setDouble(3, t.getValor());
			stmt.setInt(4, t.getId());
			int verifica = stmt.executeUpdate();
			if(verifica > 0){
				System.out.println("Título de cobrança atualizado com sucesso!");
			}else{
				System.out.println("Nenhum título foi atualizado. Verifique se o ID esta correto.");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Deletar tupla do banco de dados com base no ID
	 *
	 * @param id Usado para identificar a tupla no banco de dados
	 */
	public void excluir(int id){
		try{
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM titulo_cobranca WHERE id = ?");
			stmt.setInt(1, id);
			int verifica = stmt.executeUpdate();
			if(verifica > 0){
				System.out.println("Item de ID: " + id + " excluido com sucesso!");
				return;
			}
			System.out.println("Ocorreu um erro ao tentar excluir, verifique o dados novamente");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}

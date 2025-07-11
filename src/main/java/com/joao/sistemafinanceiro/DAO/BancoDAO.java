package com.joao.sistemafinanceiro.DAO;

import com.joao.sistemafinanceiro.DAO.Generic.GenericDAO;
import com.joao.sistemafinanceiro.Model.Banco;
import com.joao.sistemafinanceiro.Util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BancoDAO implements GenericDAO<Banco>{

	Connection conn;

	public BancoDAO(){
		this.conn = new Conexao().conectar();
	}

	@Override
	public void salvar(Banco b){
		try{
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO banco (nome, agencia, conta, tipo, saldo) VALUES (?, ?, ?, ?, ?)");
			stmt.setString(1, b.getNome());
			stmt.setString(2, b.getAgencia());
			stmt.setString(3, b.getConta());
			stmt.setString(4, b.getTipo());
			stmt.setDouble(5, b.getSaldo());
			int verifica = stmt.executeUpdate();
			if(verifica > 0){
				System.out.println("Cadastro realizado!");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Banco> buscarTodos(){
		List<Banco> lstB = new ArrayList<>();
		ResultSet rs;
		try{
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM banco");
			rs = stmt.executeQuery();
			while(rs.next()){
				lstB.add(consultarResult(rs));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return lstB;
	}

	@Override
	public Banco consultarResult(ResultSet rs) throws SQLException{
		Banco b = new Banco();

		b.setNome(rs.getString("nome"));
		b.setAgencia(rs.getString("agencia"));
		b.setConta(rs.getString("conta"));
		b.setTipo(rs.getString("tipo"));
		b.setSaldo(rs.getDouble("saldo"));

		return b;
	}

	@Override
	public void editar(Banco b){
		try{
			PreparedStatement stmt = conn.prepareStatement("UPDATE banco SET nome = ?, tipo = ?, saldo = ? WHERE agencia = ? AND conta = ?");
			stmt.setString(1, b.getNome());
			stmt.setString(2, b.getTipo());
			stmt.setDouble(3, b.getSaldo());
			stmt.setString(4, b.getAgencia());
			stmt.setString(5, b.getConta());
			int verifica = stmt.executeUpdate();
			if(verifica > 0){
				System.out.println("Banco atualizado com sucesso!");
			}else{
				System.out.println("Nenhum banco foi atualizado. Verifique se a agencia e conta estao corretas.");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Deletar tupla do banco de dados com base no ID
	 *
	 * @param agencia Usado para identificar a tupla no banco de dados
	 * @param conta   Usado para identificar a tupla no banco de dados
	 */
	public void deletarPorId(String agencia, String conta){
		try{
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM banco WHERE agencia = ? AND conta = ?");
			stmt.setString(1, agencia);
			stmt.setString(2, conta);
			int verifica = stmt.executeUpdate();
			if(verifica > 0){
				System.out.println("Banco deletado com sucesso!");
				return;
			}
			System.out.println("Ocorreu um erro ao tentar excluir, verifique o dados novamente");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public double consultarSaldoTotal(){
		double saldo = 0.0;
		ResultSet rs;
		try{
			PreparedStatement stmt = conn.prepareStatement("SELECT SUM(saldo) FROM banco");
			rs = stmt.executeQuery();
			if(rs.next()){
				saldo = rs.getDouble(1);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return saldo;
	}
}

package com.joao.sistemafinanceiro.DAO;

import com.joao.sistemafinanceiro.Model.Banco;
import com.joao.sistemafinanceiro.Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BancoDAO {

    Connection conn;

    public BancoDAO() {
        this.conn = new Conexao().conectar();
    }

    public void salvar(Banco b) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO banco "
                    + "(nome, agencia, conta, tipo, saldo) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, b.getNome());
            stmt.setString(2, b.getAgencia());
            stmt.setString(3, b.getConta());
            stmt.setString(4, b.getTipo());
            stmt.setDouble(5, b.getSaldo());
            int verifica = stmt.executeUpdate();
            if (verifica > 0) {
                System.out.println("Cadastro realizado!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Banco> consultarTodos() {
        List<Banco> lstB = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM banco");
            rs = stmt.executeQuery();
            while (rs.next()) {
                lstB.add(consultar(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstB;
    }

    public Banco consultar(ResultSet rs) throws SQLException {
        Banco b = new Banco();

        b.setNome(rs.getString("nome"));
        b.setAgencia(rs.getString("agencia"));
        b.setConta(rs.getString("conta"));
        b.setTipo(rs.getString("tipo"));
        b.setSaldo(rs.getDouble("saldo"));

        return b;
    }

    public void atualizar(Banco b) {

    }

    public void excluir(String agencia, String conta) {
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM banco WHERE agencia = ? AND conta = ?");
            stmt.setString(1, agencia);
            stmt.setString(2, conta);
            int verifica = stmt.executeUpdate();
            if(verifica > 0) {
                System.out.println("Banco deletado com sucesso!");
                return;
            }
            System.out.println("Ocorreu um erro ao tentar excluir, verifique o dados novamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double consultarSaldoTotal() {
        double saldo = 0.0;
        ResultSet rs;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT SUM(saldo) FROM banco");
            rs = stmt.executeQuery();
            if (rs.next()) {
                saldo = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return saldo;
    }
}

package com.joao.sistemafinanceiro.DAO;

import com.joao.sistemafinanceiro.Model.Parceiro;
import com.joao.sistemafinanceiro.Util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParceiroDAO {

    Connection conn;

    public ParceiroDAO() {
        conn = new Conexao().conectar();
    }

    public void salvar(Parceiro p) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO parceiro " + "(nome, documento, tipo, email, telefone) VALUES" + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDocumento());
            stmt.setString(3, p.getTipo());
            stmt.setString(4, p.getEmail());
            stmt.setString(5, p.getTelefone());
            int verifica = stmt.executeUpdate();
            if (verifica > 0) {
                System.out.println("Cadastro realizado!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Parceiro> consultarTodos() {
        List<Parceiro> lstP = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM parceiro");
            rs = stmt.executeQuery();
            while (rs.next()) {
                lstP.add(consultar(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstP;
    }

    public List<Parceiro> consultarTodos(String tipo) {
        List<Parceiro> lstP = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM parceiro WHERE tipo = ? OR tipo = 'AMBOS'");
            stmt.setString(1, tipo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                lstP.add(consultar(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstP;
    }

    public Parceiro consultar(ResultSet rs) throws SQLException {
        Parceiro p = new Parceiro();
        p.setNome(rs.getString("nome"));
        p.setDocumento(rs.getString("documento"));
        p.setTipo(rs.getString("tipo"));
        p.setEmail(rs.getString("email"));
        p.setTelefone(rs.getString("telefone"));
        return p;
    }

    public void atualizar(Parceiro p) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE parceiro SET nome = ?, tipo = ?, email = ?, telefone = ? WHERE documento = ?");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTipo());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getTelefone());
            stmt.setString(5, p.getDocumento());

            int verifica = stmt.executeUpdate();
            if (verifica > 0) {
                System.out.println("Parceiro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum parceiro foi atualizado. Verifique se o documento está correto.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void excluir(String documento) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM parceiro WHERE documento = ?");
            stmt.setString(1, documento);
            int verifica = stmt.executeUpdate();
            if (verifica > 0) {
                System.out.println("Parceiro " + documento + " excluido com sucesso!");
                return;
            }
            System.out.println("Ocorreu um erro ao tentar excluir, verifique o dados novamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

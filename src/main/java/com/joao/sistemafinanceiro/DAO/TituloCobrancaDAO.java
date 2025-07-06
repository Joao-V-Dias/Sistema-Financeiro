package com.joao.sistemafinanceiro.DAO;

import com.joao.sistemafinanceiro.Model.DocumentoFiscal;
import com.joao.sistemafinanceiro.Model.TituloCobranca;
import com.joao.sistemafinanceiro.Util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TituloCobrancaDAO {
    Connection conn;

    public TituloCobrancaDAO() {
        this.conn = new Conexao().conectar();
    }

    public void salvar(TituloCobranca t) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO titulo_cobranca (numero_documento, data_vencimento, valor)" + "VALUES (?, ?, ?)");
            stmt.setString(1, t.getDocumento().getNumero());
            stmt.setDate(2, (Date) t.getDataVencimento());
            stmt.setDouble(3, t.getValor());
            int verifica = stmt.executeUpdate();
            if (verifica > 0) System.out.println("Titulo cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TituloCobranca> consultarTodos() {
        List<TituloCobranca> lstT = new ArrayList<>();
        ResultSet rs;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM titulo_cobranca");
            rs = stmt.executeQuery();
            while (rs.next()) {
                lstT.add(consultar(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lstT;
    }

    public TituloCobranca consultar(ResultSet rs) throws SQLException {
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

    public void atualizar(TituloCobranca t) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE titulo_cobranca SET numero_documento = ?, data_vencimento = ?, valor = ?, status = ? WHERE id = ?");
            stmt.setString(1, t.getDocumento().getNumero());
            stmt.setDate(2, (Date) t.getDataVencimento());
            stmt.setDouble(3, t.getValor());
            stmt.setString(4, t.getStatus());
            stmt.setInt(5, t.getId());

            int verifica = stmt.executeUpdate();
            if (verifica > 0) {
                System.out.println("Título de cobrança atualizado com sucesso!");
            } else {
                System.out.println("Nenhum título foi atualizado. Verifique se o ID está correto.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void excluir(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM titulo_cobranca WHERE id = ?");
            stmt.setInt(1, id);
            int verifica = stmt.executeUpdate();
            if (verifica > 0) {
                System.out.println("Item de ID: " + id + " excluido com sucesso!");
                return;
            }
            System.out.println("Ocorreu um erro ao tentar excluir, verifique o dados novamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.joao.sistemafinanceiro.DAO;

import com.joao.sistemafinanceiro.Model.*;
import com.joao.sistemafinanceiro.Util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDAO {
    Connection conn;

    public MovimentacaoDAO() {
        this.conn = new Conexao().conectar();
    }

    public void salvar(Movimentacao m) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO movimentacao (agencia, conta, titulo_cobranca, tipo, observacao) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, m.getBanco().getAgencia());
            stmt.setString(2, m.getBanco().getConta());
            stmt.setInt(3, m.getTituloCobranca().getId());
            stmt.setString(4, m.getTipo());
            stmt.setString(5, m.getObservacao());
            int verifica = stmt.executeUpdate();
            if (verifica > 0) {
                System.out.println("Cadastro realizado!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movimentacao> consultarTodos() {
        List<Movimentacao> lstM = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM vw_movimentacao");
            rs = stmt.executeQuery();
            while (rs.next()) {
                lstM.add(consultar(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstM;
    }

    public Movimentacao consultar(ResultSet rs) throws SQLException {
        Movimentacao m = new Movimentacao();
        Banco b = new Banco();
        TituloCobranca t = new TituloCobranca();
        DocumentoFiscal d = new DocumentoFiscal();
        Parceiro cliente = new Parceiro();
        Parceiro fornecedor = new Parceiro();

        m.setId(rs.getInt("id_movimentacao"));
        m.setDataOperacao(rs.getTimestamp("data_operacao"));
        m.setTipo(rs.getString("tipo_movimentacao"));

        b.setNome(rs.getString("banco"));
        m.setBanco(b);

        t.setId(rs.getInt("id_titulo"));
        t.setDataVencimento(rs.getDate("data_vencimento"));
        t.setStatus(rs.getString("status_titulo"));
        m.setTituloCobranca(t);

        d.setNumero(rs.getString("numero_documento"));
        d.setTipo(rs.getString("tipo_documento"));
        d.setValorTotal(rs.getDouble("valor_total"));

        cliente.setNome(rs.getString("nome_cliente"));
        fornecedor.setNome(rs.getString("nome_fornecedor"));
        d.setCliente(cliente);
        d.setFornecedor(fornecedor);
        t.setDocumento(d);

        return m;
    }

    public Movimentacao consultarPorId(int id) {
        Movimentacao m = null;
        ResultSet rs;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM vw_movimentacao WHERE id_movimentacao = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                m = consultar(rs);
            } else {
                System.out.println("Movimentação com ID " + id + " não encontrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    public void atualizar(Movimentacao m) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE movimentacao SET agencia = ?, conta = ?, titulo_cobranca = ?, tipo = ?, valor = ?, observacao = ? WHERE id = ?");
            stmt.setString(1, m.getBanco().getAgencia());
            stmt.setString(2, m.getBanco().getConta());
            stmt.setInt(3, m.getTituloCobranca().getId());
            stmt.setString(4, m.getTipo());
            stmt.setString(5, m.getObservacao());
            stmt.setInt(6, m.getId());

            int verifica = stmt.executeUpdate();
            if (verifica > 0) {
                System.out.println("Movimentação atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma movimentação foi atualizada. Verifique se o ID está correto.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void excluir(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM movimentacao WHERE id = ?");
            stmt.setInt(1, id);
            int verifica = stmt.executeUpdate();
            if (verifica > 0) {
                System.out.println("Movimento ID: " + id + " excluido com sucesso!");
                return;
            }
            System.out.println("Ocorreu um erro ao tentar excluir, verifique o dados novamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

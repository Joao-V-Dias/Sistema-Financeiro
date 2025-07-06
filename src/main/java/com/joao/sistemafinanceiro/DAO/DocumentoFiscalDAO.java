package com.joao.sistemafinanceiro.DAO;

import com.joao.sistemafinanceiro.Model.DocumentoFiscal;
import com.joao.sistemafinanceiro.Model.Parceiro;
import com.joao.sistemafinanceiro.Util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentoFiscalDAO {
    Connection conn;

    public DocumentoFiscalDAO() {
        conn = new Conexao().conectar();
    }

    public void salvar(DocumentoFiscal d) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO documento_fiscal (tipo, numero, data_emissao, emitente, remetente, valor_total) VALUES" + "(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, d.getTipo());
            stmt.setString(2, d.getNumero());
            stmt.setDate(3, (Date) d.getDataEmissao());
            stmt.setString(4, d.getEmitente().getDocumento());
            stmt.setString(5, d.getRemetente().getDocumento());
            stmt.setDouble(6, d.getValorTotal());
            int verifica = stmt.executeUpdate();
            if (verifica > 0) {
                System.out.println("Cadastro realizado!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DocumentoFiscal> consultarTodos() {
        List<DocumentoFiscal> lstD = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_documentos_com_pessoas");
            rs = stmt.executeQuery();
            while (rs.next()) {
                lstD.add(consultar(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lstD;
    }

    public DocumentoFiscal consultar(ResultSet rs) throws SQLException {
        DocumentoFiscal d = new DocumentoFiscal();
        Parceiro emitente = new Parceiro();
        Parceiro remetente = new Parceiro();

        d.setTipo(rs.getString("tipo"));
        d.setNumero(rs.getString("numero"));
        emitente.setNome(rs.getString("nome_emitente"));
        emitente.setDocumento(rs.getString("emitente"));
        d.setEmitente(emitente);
        remetente.setNome(rs.getString("nome_remetente"));
        remetente.setDocumento(rs.getString("remetente"));
        d.setRemetente(remetente);
        d.setDataEmissao(rs.getDate("data_emissao"));
        d.setValorTotal(rs.getDouble("valor_total"));

        return d;
    }

    public void atualizar(DocumentoFiscal d) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE documento_fiscal SET tipo = ?, data_emissao = ?, emitente = ?, remetente = ?, valor_total = ? WHERE numero = ?");
            stmt.setString(1, d.getTipo());
            stmt.setDate(2, new java.sql.Date(d.getDataEmissao().getTime()));
            stmt.setString(3, d.getEmitente().getDocumento());
            stmt.setString(4, d.getRemetente().getDocumento());
            stmt.setDouble(5, d.getValorTotal());
            stmt.setString(6, d.getNumero());

            int verifica = stmt.executeUpdate();
            if (verifica > 0) {
                System.out.println("Documento fiscal atualizado com sucesso!");
            } else {
                System.out.println("Nenhum documento foi atualizado. Verifique se o número está correto.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void excluir(String numero) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM documento_fiscal WHERE numero = ?");
            stmt.setString(1, numero);
            int verifica = stmt.executeUpdate();
            if (verifica > 0) {
                System.out.println(numero + " excluido com sucesso!");
                return;
            }
            System.out.println("Ocorreu um erro ao tentar excluir, verifique o dados novamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

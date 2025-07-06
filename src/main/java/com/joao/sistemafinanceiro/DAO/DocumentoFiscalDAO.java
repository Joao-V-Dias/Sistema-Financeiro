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

    public void salvar(DocumentoFiscal d){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO documento_fiscal (tipo, numero, data_emissao, emitente, remetente, valor_total) VALUES" +
                    "(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
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
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<DocumentoFiscal> consultarTodos(){
        List<DocumentoFiscal> lstD = new ArrayList<>();
        ResultSet rs;
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_documentos_com_pessoas");
            rs = stmt.executeQuery();
            while(rs.next()){
                lstD.add(getDocumento(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return lstD;
    }

    public DocumentoFiscal getDocumento(ResultSet rs) throws SQLException {
        DocumentoFiscal d = new DocumentoFiscal();
        Parceiro emitente = new Parceiro();
        Parceiro remetente = new Parceiro();

        d.setId(rs.getInt("id"));
        d.setTipo(rs.getString("tipo"));
        d.setNumero(rs.getString("numero"));
        emitente.setNome(rs.getString("nome_emitente"));
        emitente.setDocumento(rs.getString("doc_emitente"));
        d.setEmitente(emitente);
        remetente.setNome(rs.getString("nome_remetente"));
        remetente.setDocumento(rs.getString("doc_remetente"));
        d.setRemetente(remetente);
        d.setDataEmissao(rs.getDate("data_emissao"));
        d.setValorTotal(rs.getDouble("valor_total"));

        return d;
    }
}

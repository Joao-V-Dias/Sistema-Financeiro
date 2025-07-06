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
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO titulo_cobranca (documento_id, data_vencimento, valor)" +
                    "VALUES (?, ?, ?)");
            stmt.setInt(1, t.getDocumento().getId());
            stmt.setDate(2, (Date) t.getDataVencimento());
            stmt.setDouble(3, t.getValor());
            int verifica = stmt.executeUpdate();
            if(verifica > 0) System.out.println("Titulo cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TituloCobranca> consultarTodos(){
        List<TituloCobranca> lstT = new ArrayList<>();
        ResultSet rs;

        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM titulo_cobranca");
            rs = stmt.executeQuery();
            while(rs.next()){
                lstT.add(consultar(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return lstT;
    }

    public TituloCobranca consultar(ResultSet rs) throws SQLException{
        TituloCobranca t = new TituloCobranca();
        DocumentoFiscal d = new DocumentoFiscal();

        t.setId(rs.getInt("id"));
        d.setId(rs.getInt("documento_id"));
        t.setDocumento(d);
        t.setDataVencimento(rs.getDate("data_vencimento"));
        t.setValor(rs.getDouble("valor"));
        t.setStatus(rs.getString("status"));

        return t;
    }
}

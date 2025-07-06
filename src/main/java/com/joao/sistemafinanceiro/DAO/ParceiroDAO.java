package com.joao.sistemafinanceiro.DAO;

import com.joao.sistemafinanceiro.Model.Parceiro;
import com.joao.sistemafinanceiro.Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joao
 * @version 1.0
 * @since Jul 5, 2025
 */
public class ParceiroDAO {

    Connection conn;

    public ParceiroDAO() {
        conn = new Conexao().conectar();
    }

    public void salvar(Parceiro p) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO pessoa "
                    + "(nome, documento, tipo, email, telefone) VALUES"
                    + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDocumento());
            stmt.setString(3, p.getTipo());
            stmt.setString(4, p.getEmail());
            stmt.setString(5, p.getTelefone());
            int verifica = stmt.executeUpdate();
            if(verifica > 0){
                System.out.println("Cadastro realizado!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Parceiro> consultarParceiros(){
        List<Parceiro> lstP = new ArrayList<>();
        ResultSet rs;
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pessoa");
            rs = stmt.executeQuery();
            while(rs.next()){
                lstP.add(consultarParceiro(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return lstP;
    }
    
    public Parceiro consultarParceiro(ResultSet rs) throws SQLException {
        Parceiro p = new Parceiro();
        p.setNome(rs.getString("nome"));
        p.setDocumento(rs.getString("documento"));
        p.setTipo(rs.getString("tipo"));
        p.setEmail(rs.getString("email"));
        p.setTelefone(rs.getString("telefone"));
        return p;
    }
}

package com.joao.sistemafinanceiro.DAO;

import com.joao.sistemafinanceiro.Model.Banco;
import com.joao.sistemafinanceiro.Model.DocumentoFiscal;
import com.joao.sistemafinanceiro.Model.Movimentacao;
import com.joao.sistemafinanceiro.Model.Parceiro;
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
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO movimentacao (agencia, conta, " +
                    "numero_documento, tipo, valor, observacao) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, m.getBanco().getAgencia());
            stmt.setString(2, m.getBanco().getConta());
            stmt.setString(3, m.getDocumento().getNumero());
            stmt.setString(4, m.getTipo());
            stmt.setDouble(5, m.getValor());
            stmt.setString(6, m.getObservacao());
            int verifica = stmt.executeUpdate();
            if(verifica > 0){
                System.out.println("Cadastro realizado!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movimentacao> consultarTodos(){
        List<Movimentacao> lstM = new ArrayList<>();
        ResultSet rs;
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM vw_movimentacao");
            rs = stmt.executeQuery();
            while(rs.next()){
                lstM.add(consultar(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lstM;
    }

    public Movimentacao consultar(ResultSet rs) throws SQLException{
        Movimentacao m = new Movimentacao();
        Banco b = new Banco();
        DocumentoFiscal d = new DocumentoFiscal();
        Parceiro emitente = new Parceiro();
        Parceiro remetente = new Parceiro();

        m.setId(rs.getInt("id_movimentacao"));
        m.setDataOperacao(rs.getDate("data_operacao"));
        m.setTipo(rs.getString("tipo_movimentacao"));
        b.setNome(rs.getString("banco"));
        m.setBanco(b);
        d.setNumero(rs.getString("documento"));
        d.setDataEmissao(rs.getDate("data_emissao"));
        m.setDocumento(d);
        emitente.setNome(rs.getString("emitente"));
        remetente.setNome(rs.getString("remetente"));
        d.setEmitente(emitente);
        d.setRemetente(remetente);

        return m;
    }

    public void excluir(int id){
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM movimentacao WHERE id = ?");
            stmt.setInt(1, id);
            int verifica = stmt.executeUpdate();
            if(verifica > 0){
                System.out.println("Movimento ID: " + id + " excluido com sucesso!");
                return;
            }
            System.out.println("Ocorreu um erro ao tentar excluir, verifique o dados novamente");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

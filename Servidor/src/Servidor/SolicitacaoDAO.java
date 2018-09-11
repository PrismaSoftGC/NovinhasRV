package Servidor;

import Model.SolicitacaoBEAN  ;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SolicitacaoDAO {
    
    private static SolicitacaoDAO instance;

    private SolicitacaoDAO() {
        MySQLDAO.getConnection();
    }

    public static SolicitacaoDAO getInstance() {
        if (instance == null) {
            instance = new SolicitacaoDAO();
        }
        return instance;
    }

    public int create(SolicitacaoBEAN solicitacao) {
        String query = "INSERT INTO solicitacao (codigoCliente1,codigoCliente2,aceitar) VALUES (?,?,?)";
        int id = (int) MySQLDAO.executeQuery(query,solicitacao.getCodigoCliente1(),solicitacao.getCodigoCliente2(),solicitacao.getAceitar());
       solicitacao.setCodigoSolicitacao(id);
        return id;
    }

    public int update(SolicitacaoBEAN solicitacao) {
        String query = "UPDATE solicitacao SET codigoCliente1, codigoCliente2 WHERE codigoSolicitacao = ?";
        return (int) MySQLDAO.executeQuery(query,solicitacao.getCodigoCliente1(),solicitacao.getCodigoCliente2(),solicitacao.getAceitar());
    }

    public ArrayList<SolicitacaoBEAN  > findAllSolicitacao() {
        return listaSolicitacao("SELECT * FROM solicitacao ORDER BY codigoSolicitacao");
    }

    public ArrayList<SolicitacaoBEAN > listaSolicitacao(String query) {
        ArrayList<SolicitacaoBEAN > lista = new ArrayList<SolicitacaoBEAN  >();
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet(query);
        try {
            while (rs.next()) {
                lista.add(new SolicitacaoBEAN (rs.getInt("codigoSolicitacao"), rs.getInt("codigoCliente1"),rs.getInt("codigoCLiente2"),rs.getString("aceitar")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public SolicitacaoBEAN  findSolicitacao(int codigoSolicitacao) {
        SolicitacaoBEAN    result = null;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM solicitacao WHERE codigoSolicitacao=?", codigoSolicitacao);
        try {
            if (rs.next()) {
                result = new SolicitacaoBEAN (rs.getInt("codigoSolicitacao"), rs.getInt("codigoCliente1"),rs.getInt("codigoCLiente2"),rs.getString("aceitar"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int findId(SolicitacaoBEAN  solicitacao) {
        int result = 0;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM solicitacao WHERE codigoCliente1=? and codigoCliente2=? ",solicitacao.getCodigoCliente1(),solicitacao.getCodigoCliente2());
        try {
            if (rs.next()) {
                result = rs.getInt("codigoSolicitacao");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
}

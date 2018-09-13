
package Servidor;

import Model.Aux_conversaBEAN;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Aux_conversaDAO {
    
    private static Aux_conversaDAO instance;

    private Aux_conversaDAO() {
        MySQLDAO.getConnection();
    }

    public static Aux_conversaDAO getInstance() {
        if (instance == null) {
            instance = new Aux_conversaDAO();
        }
        return instance;
    }

    public int create(Aux_conversaBEAN aux) {
        String query = "INSERT INTO aux_conversa (codigoConversa, mensagem) VALUES (?,?)";
        int id = (int) MySQLDAO.executeQuery(query, aux.getCodigoConversa(),aux.getMensage());
        aux.setCodigoConversa(id);
        return id;
    }

    public int update(Aux_conversaBEAN aux) {
        String query = "UPDATE AUX_CONVERSA SET mensagem=? WHERE codigoConversa = ?";
        return (int) MySQLDAO.executeQuery(query, aux.getMensage(),aux.getCodigoConversa());
    }

    public ArrayList<Aux_conversaBEAN> findAllAux() {
        return listaAuxs("SELECT * FROM AUX_CONVERSA ORDER BY codigoConversa");
    }


    public ArrayList<Aux_conversaBEAN> listaAuxs(String query) {
        ArrayList<Aux_conversaBEAN> lista = new ArrayList<Aux_conversaBEAN>();
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet(query);
        try {
            while (rs.next()) {
                lista.add(new Aux_conversaBEAN(rs.getInt("codigoConversa"), rs.getString("mensagens")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Aux_conversaBEAN findAux(int idAux) {
        Aux_conversaBEAN result = null;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM USUARIO WHERE codigoConeversa=?", idAux);
        try {
            if (rs.next()) {
                result = new Aux_conversaBEAN(rs.getInt("codigoConversa"), rs.getString("mensagens"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
   
 
    public ArrayList<Aux_conversaBEAN> findConversas(int codigoConversa) {
        ArrayList<Aux_conversaBEAN> lista = new ArrayList<Aux_conversaBEAN>();
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM AUX_CONVERSA WHERE codigoConversa=? ", codigoConversa);
        try {
            while (rs.next()) {
                lista.add(new Aux_conversaBEAN(rs.getInt("codigoConversa"), rs.getString("mensagem")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}

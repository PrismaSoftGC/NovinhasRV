package Servidor;

import Model.ConversaBEAN ;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConversaDAO {
    
    private static ConversaDAO instance;

    private ConversaDAO() {
        MySQLDAO.getConnection();
    }

    public static ConversaDAO getInstance() {
        if (instance == null) {
            instance = new ConversaDAO();
        }
        return instance;
    }

    public int create(ConversaBEAN conversa) {
        String query = "INSERT INTO conversa (codigoCliente1,codigoCliente2) VALUES (?,?)";
        int id = (int) MySQLDAO.executeQuery(query, conversa.getCodigoCliente1(),conversa.getCodigoCliente2());
        conversa.setCodigoConversa(id);
        return id;
    }

    public int update(ConversaBEAN conversa) {
        String query = "UPDATE conversa SET codigoCliente1, codigoCliente2 WHERE codigoConversa = ?";
        return (int) MySQLDAO.executeQuery(query, conversa.getCodigoCliente1(),conversa.getCodigoCliente2(),conversa.getCodigoConversa());
    }

    public ArrayList<ConversaBEAN > findAllConversa() {
        return listaConversas("SELECT * FROM conversa ORDER BY codigoConversa");
    }

    public ArrayList<ConversaBEAN > listaConversas(String query) {
        ArrayList<ConversaBEAN > lista = new ArrayList<ConversaBEAN >();
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet(query);
        try {
            while (rs.next()) {
                lista.add(new ConversaBEAN (rs.getInt("codigoConversa"), rs.getInt("codigoCliente1"),rs.getInt("codigoCLiente2")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ConversaBEAN  findConversa(int codigoConversa) {
        ConversaBEAN   result = null;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM conversa WHERE codigoConversa=?", codigoConversa);
        try {
            if (rs.next()) {
                result = new ConversaBEAN (rs.getInt("codigoConversa"), rs.getInt("codigoCliente1"),rs.getInt("codigoCLiente2"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int findId(ConversaBEAN   conversa) {
        int result = 0;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM conversa WHERE codigoCliente1=? and codigoCliente2=? ", conversa.getCodigoCliente1(), conversa.getCodigoCliente2());
        try {
            if (rs.next()) {
                result = rs.getInt("codigoConversa");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
}

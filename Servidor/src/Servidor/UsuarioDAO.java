
package Servidor;

import Model.UsuarioBEAN;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
    private static UsuarioDAO instance;

    private UsuarioDAO() {
        MySQLDAO.getConnection();
    }

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }

    public int create(UsuarioBEAN usuario) {
        String query = "INSERT INTO USUARIO (nome, email, senha, idade, descricao, prefSexo, prefEsporte, prefReligioso, prefMusica, prefGames, prefIdade) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
        int id = (int) MySQLDAO.executeQuery(query, usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getIdade(), usuario.getDescricao(), usuario.getPrefSexo()
                                            , usuario.getPrefEsporte(), usuario.getPrefReligioso(), usuario.getPrefMusica(), usuario.getPrefGames(), usuario.getPrefIdade());
        usuario.setId(id);
        return id;
    }

    public int update(UsuarioBEAN usuario) {
        String query = "UPDATE USUARIO SET nome=?, email=?, senha=?, idade=?, descricao=?, prefSexo=?, prefEsporte=?, prefReligioso=?, prefMusica=?, prefGames=?, prefIdade=? WHERE idusuario = ?";
        JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso!");
        return (int) MySQLDAO.executeQuery(query, usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getIdade(), usuario.getDescricao(), usuario.getPrefSexo()
                                            , usuario.getPrefEsporte(), usuario.getPrefReligioso(), usuario.getPrefMusica(), usuario.getPrefGames(), usuario.getPrefIdade(), usuario.getId());
    }

    public ArrayList<UsuarioBEAN> findAllUsuario() {
        return listaUsuarios("SELECT * FROM USUARIO ORDER BY idusuario");
    }

    public ArrayList<UsuarioBEAN> findAllUsuario(String nome) {
        return listaUsuarios("SELECT * FROM USUARIO where nome like '%" + nome + "%'");
    }

    public ArrayList<UsuarioBEAN> listaUsuarios(String query) {
        ArrayList<UsuarioBEAN> lista = new ArrayList<UsuarioBEAN>();
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet(query);
        try {
            while (rs.next()) {
                lista.add(new UsuarioBEAN(rs.getInt("idusuario"), rs.getString("login"),rs.getString("nome"), rs.getString("email"), rs.getString("senha"), rs.getInt("idade"), rs.getString("descricao"),
                          rs.getByte("prefSexo"), rs.getByte("prefEsporte"), rs.getByte("prefReligioso"), rs.getByte("prefMusica"), rs.getByte("prefGames"), rs.getByte("prefIdade")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public UsuarioBEAN findUsuario(int idusuario) {
        UsuarioBEAN result = null;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM USUARIO WHERE idusuario=?", idusuario);
        try {
            if (rs.next()) {
                result = new UsuarioBEAN(rs.getInt("idusuario"),rs.getString("login"),rs.getString("nome"), rs.getString("email"), rs.getString("senha"), rs.getInt("idade"), rs.getString("descricao"),
                          rs.getByte("prefSexo"), rs.getByte("prefEsporte"), rs.getByte("prefReligioso"), rs.getByte("prefMusica"), rs.getByte("prefGames"), rs.getByte("prefIdade"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int findId(UsuarioBEAN usuario) {
        int result = 0;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM USUARIO WHERE nome=? and email=? and senha=? and idade=? and descricao=? and prefSexo=? and prefEsporte=? and prefReligioso=? and prefMusica=? and prefGames=? and prefIdade=?", usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getIdade(), usuario.getDescricao(), usuario.getPrefSexo()
                                            , usuario.getPrefEsporte(), usuario.getPrefReligioso(), usuario.getPrefMusica(), usuario.getPrefGames(), usuario.getPrefIdade());
        try {
            if (rs.next()) {
                result = rs.getInt("idusuario");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
}


package Servidor;

import Model.UsuarioBEAN;
import java.util.ArrayList;

public class Controller {

    public Controller() {
    }

    public int addUsuario(UsuarioBEAN usuario) {
        return UsuarioDAO.getInstance().create(usuario);
    }

    public int updateUsuario(UsuarioBEAN usuario) {
        return UsuarioDAO.getInstance().update(usuario);
    }

    public UsuarioBEAN findUsuario(int idusuario) {
        return UsuarioDAO.getInstance().findUsuario(idusuario);
    }

    public int findIdUsuario(UsuarioBEAN usuario) {
        return UsuarioDAO.getInstance().findId(usuario);
    }

    public ArrayList<UsuarioBEAN> listaUsuarios() {
        return UsuarioDAO.getInstance().findAllUsuario();
    }

    public ArrayList<UsuarioBEAN> listaUsuario(String nome) {
        return UsuarioDAO.getInstance().findAllUsuario(nome);
    }
}

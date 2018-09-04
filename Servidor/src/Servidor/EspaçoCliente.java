package Servidor;

import Model.UsuarioBEAN;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.*;
import java.math.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class EspaçoCliente extends Thread{
    
    private Socket cliente=null;
    private static ObjectInputStream entrada=null;
    private static ObjectOutputStream saida=null;
    private String comando;
    private Controller controle;
    
    public EspaçoCliente(Socket cliente) {
        this.controle = new Controller();
        this.cliente=cliente;
        try {
            saida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());    
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void run(){
        acao();    
    }
    
    public synchronized void acao(){
        while(true){
            try {
                comando = entrada.readUTF();
                
                if (comando.contains("CRIAR")) {
                    UsuarioBEAN aux = (UsuarioBEAN) entrada.readObject();
                    if (notNull(aux)) {
                        boolean retorno = gravarUsuario(aux);
                        saida.writeBoolean(retorno);
                    }else{ // Se o usuario é nulo
                        saida.writeBoolean(false);
                    }
                }else 
                if (comando.contains("ATUALIZAR")) {
                    UsuarioBEAN aux1 = buscarUsuario(entrada.readInt());
                    if (notNull(aux1)) {
                        saida.writeObject(aux1); // Envia informaçoes do usuario
                        UsuarioBEAN aux = (UsuarioBEAN) entrada.readObject();
                        if (notNull(aux)) {
                           boolean retorno = atualizarUsuario(aux);
                           saida.writeBoolean(retorno); 
                        }else{
                           saida.writeBoolean(false);
                        }
                    }else{ // Se o usuario é nulo
                        saida.writeBoolean(false);
                    }
                }else 
                if (comando.contains("BUSCAR USUARIO")) {
                    String login = entrada.readUTF();
                    String senha = senhaToMd5(entrada.readUTF());
                    UsuarioBEAN aux = buscarUsuario(login, senha);
                    if (notNull(aux)) {
                        saida.writeObject(aux);
                    }else{
                        saida.writeObject(null);
                    }
                }else
                if (comando.contains("ENCONTRAR")) {
                    UsuarioBEAN aux = (UsuarioBEAN) entrada.readObject();
                    if (notNull(aux)) {
                        saida.writeObject(encontrarCompanhia(aux));
                    }
                }
                               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }    
        }
    }
    
    
    
    // #########################################################################
    private void exibeBean(UsuarioBEAN usuario){
        if (usuario != null) {
            System.out.println("Nome: "+usuario.getNome());
            System.out.println("Senha: "+usuario.getSenha());
        }else{
            System.out.println("VAZIO!!!");
        }
    }
    
    // #########################################################################
    private boolean notNull(UsuarioBEAN usuario){
        return usuario == null ? false : true;
    }
    
    // #########################################################################
    private boolean gravarUsuario(UsuarioBEAN usuario){
        controle.addUsuario(usuario);
        if (controle.findIdUsuario(usuario) >= 0) {
            return true;
        }
        return false;
    }
    
    // #########################################################################
    private boolean atualizarUsuario(UsuarioBEAN usuario){
        UsuarioBEAN usuario_antigo = controle.findUsuario(usuario.getId());
        controle.updateUsuario(usuario);
        if (controle.findUsuario(usuario.getId()).equals(usuario_antigo)) {
            return false;
        }
        return true;
    }
    
    // #########################################################################
    private UsuarioBEAN buscarUsuario(String login, String senha){
        ArrayList<UsuarioBEAN> usuarios = controle.listaUsuarios();
        for(UsuarioBEAN usuario : usuarios){
            if (usuario.getEmail().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
    
    // #########################################################################
    private UsuarioBEAN buscarUsuario(int id_usuario){
        return controle.findUsuario(id_usuario);
    }
    
    // #########################################################################
    private UsuarioBEAN encontrarCompanhia(UsuarioBEAN usuario){
        ArrayList<UsuarioBEAN> usuarios = controle.listaUsuarios();
        for(UsuarioBEAN aux : usuarios){
            if (aux.getPrefEsporte() == usuario.getPrefEsporte() ||
                aux.getPrefGames() == usuario.getPrefGames() ||
                aux.getPrefIdade() == usuario.getPrefIdade() ||
                aux.getPrefMusica() == usuario.getPrefMusica() ||
                aux.getPrefReligioso() == usuario.getPrefReligioso() ||
                aux.getPrefSexo() == usuario.getPrefSexo()) {
                return aux;
            }
        }
        return null;
    }
    
    // #########################################################################
    private String senhaToMd5(String senha){
        try {
            MessageDigest algoritmo=MessageDigest.getInstance("MD5");
            byte messageDigest[] = algoritmo.digest(senha.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            return hexString.toString();
        } catch (Exception e) {
            return null;
        }
    }
    
    // ### FIM ###
}

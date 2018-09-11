package Servidor;

import Model.UsuarioBEAN;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.*;
import java.math.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class EspacoCliente extends Thread {

    private Socket cliente = null;
    private static DataInputStream entrada = null;
    private static DataOutputStream saida = null;
    private static ObjectOutputStream saidaObjeto = null;
    private static ObjectInputStream entradaObjeto = null;
    private String comando;
    private Controller controle;
    private int idusuario;

    public EspacoCliente(Socket cliente) {
        this.controle = new Controller();
        this.cliente = cliente;

    }

    @Override
    public void run() {
        try {
            saida = new DataOutputStream(cliente.getOutputStream());
            entrada = new DataInputStream(cliente.getInputStream());
            saidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
            entradaObjeto = new ObjectInputStream(cliente.getInputStream());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        acao();
    }

    public synchronized void acao() {
        while (true) {
            try {
                comando = entrada.readUTF();
                if (comando.contains("CRIAR")) {
                    UsuarioBEAN aux = (UsuarioBEAN) entradaObjeto.readObject();
                    //byte[] foto = (byte[]) entradaObjeto.readObject();
                    if (!aux.getCaminhoImagem().contains("FotoUsuario")) {
                        aux.setCaminhoImagem("C:\\Users\\Erick\\Desktop\\NovinhasRV\\NovinhasRV\\Fotos Clientes\\" + aux.getNome() + ".jpg");
                    }
                    FileOutputStream file = new FileOutputStream("C:\\Users\\Erick\\Desktop\\NovinhasRV\\NovinhasRV\\Fotos Clientes\\" + aux.getNome() + ".jpg");
                    byte[] buf = new byte[4096];
                    while (true) {
                        int len = entrada.read(buf);
                        file.write(buf, 0, len);
                        if (len < 4096) {
                            break;
                        }
                    }
                    if (notNull(aux)) {
                        aux.setSenha(senhaToMd5(aux.getSenha()));
                        boolean retorno = gravarUsuario(aux);
                        saida.writeBoolean(retorno);
                        //insereImagem(foto);
                        saida.flush();
                    } else { // Se o usuario é nulo
                        saida.writeBoolean(false);
                        saida.flush();
                    }
                } else if (comando.contains("ATUALIZAR")) {
                    UsuarioBEAN usuarioAntigo = buscarUsuario(entrada.readInt());
                    if (notNull(usuarioAntigo)) {
                        saidaObjeto.writeObject(usuarioAntigo); // Envia informaçoes do usuario
                        saida.flush();
                        FileInputStream file2 = new FileInputStream(usuarioAntigo.getCaminhoImagem());
                        BufferedImage image = ImageIO.read(file2);
                        saidaObjeto.writeObject(retornaImagem(image));
                        saidaObjeto.flush();
                        UsuarioBEAN aux = (UsuarioBEAN) entradaObjeto.readObject();
                        if (notNull(aux)) {
                            if (entrada.readInt() == 1) {
                                aux.setCaminhoImagem("C:\\Users\\adeja\\Desktop\\NovinhasRV\\Fotos Clientes\\" + aux.getNome() + ".jpg");
                                FileOutputStream file = new FileOutputStream("C:\\Users\\adeja\\Desktop\\NovinhasRV\\Fotos Clientes\\" + aux.getNome() + ".jpg");
                                byte[] buf = new byte[4096];
                                while (true) {
                                    int len = entrada.read(buf);
                                    System.out.println(len);
                                    file.write(buf, 0, len);
                                    if (len < 4096) {
                                        break;
                                    }
                                }
                            }
                            boolean retorno = atualizarUsuario(aux);
                            saida.writeBoolean(retorno);
                            saida.flush();
                        } else {
                            saida.writeBoolean(false);
                            saida.flush();
                        }
                    } else { // Se o usuario é nulo
                        saida.writeBoolean(false);
                        saida.flush();
                    }
                } else if (comando.contains("BUSCAR USUARIO")) {
                    String login = entrada.readUTF();
                    String senha = senhaToMd5(entrada.readUTF());
                    //String senha = entrada.readUTF();
                    UsuarioBEAN aux = buscarUsuario(login, senha);
                    if (notNull(aux)) {
                        saidaObjeto.writeObject(aux);
                        saidaObjeto.flush();
                         aux = (UsuarioBEAN)entradaObjeto.readObject();
                    } else {
                        saidaObjeto.writeObject(null);
                        saidaObjeto.flush();
                    }
                } else if (comando.contains("ENCONTRAR")) {
                    UsuarioBEAN aux = (UsuarioBEAN) entradaObjeto.readObject();
                    if (notNull(aux)) {
                        saidaObjeto.writeObject(encontrarCompanhia(aux));
                        FileInputStream file2 = new FileInputStream(encontrarCompanhia(aux).getCaminhoImagem());
                        BufferedImage image = ImageIO.read(file2);
                        saidaObjeto.writeObject(retornaImagem(image));
                        saidaObjeto.flush();

                    } else {
                        saidaObjeto.writeObject(null);
                        saidaObjeto.flush();
                    }
                }else if(comando.contains("CONVERSA")){
                   
                }
            } catch (Exception ex) {

            }
        }
    }

    // #########################################################################
    private void exibeBean(UsuarioBEAN usuario) {
        if (usuario != null) {
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Senha: " + usuario.getSenha());
        } else {
            System.out.println("VAZIO!!!");
        }
    }

    // #########################################################################
    private boolean notNull(UsuarioBEAN usuario) {
        return usuario == null ? false : true;
    }

    // #########################################################################
    public static byte[] retornaImagem(BufferedImage originalImage) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // #########################################################################
    private boolean gravarUsuario(UsuarioBEAN usuario) {
        idusuario = controle.addUsuario(usuario);
        if (controle.findIdUsuario(usuario) >= 0) {
            return true;
        }
        return false;
    }

    // #########################################################################
    private boolean atualizarUsuario(UsuarioBEAN usuario) {
        UsuarioBEAN usuario_antigo = controle.findUsuario(usuario.getId());
        controle.updateUsuario(usuario);
        if (controle.findUsuario(usuario.getId()).equals(usuario_antigo)) {
            return false;
        }
        return true;
    }

    // #########################################################################
    private UsuarioBEAN buscarUsuario(String login, String senha) {
        ArrayList<UsuarioBEAN> usuarios = controle.listaUsuarios();
        for (UsuarioBEAN usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    // #########################################################################
    private UsuarioBEAN buscarUsuario(int id_usuario) {
        return controle.findUsuario(id_usuario);
    }

    // #########################################################################
    private UsuarioBEAN encontrarCompanhia(UsuarioBEAN usuario) {
        ArrayList<UsuarioBEAN> usuarios = controle.listaUsuarios();
        for (UsuarioBEAN aux : usuarios) {
            if (aux.getLogin() != usuario.getLogin() && aux.getPrefEsporte() == usuario.getPrefEsporte()
                    || aux.getPrefGames() == usuario.getPrefGames()
                    || aux.getPrefIdade() == usuario.getPrefIdade()
                    || aux.getPrefMusica() == usuario.getPrefMusica()
                    || aux.getPrefReligioso() == usuario.getPrefReligioso()
                    || aux.getPrefSexo() == usuario.getPrefSexo()) {
                return aux;
            }
        }
        return null;
    }

    // #########################################################################
    private String senhaToMd5(String senha) {
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("MD5");
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

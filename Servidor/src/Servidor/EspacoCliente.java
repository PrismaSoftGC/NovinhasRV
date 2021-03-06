package Servidor;

import Model.Aux_conversaBEAN;
import Model.ConversaBEAN;
import Model.SolicitacaoBEAN;
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
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class EspacoCliente extends Thread {

    private Socket cliente = null;
    private DataInputStream entrada = null;
    private DataOutputStream saida = null;
    private ObjectOutputStream saidaObjeto = null;
    private ObjectInputStream entradaObjeto = null;
    private String comando;
    private Controller controle;
    private int idusuario;

    public EspacoCliente(Socket cliente) {
        this.controle = new Controller();
        this.cliente = cliente;

    }

    @Override
    public synchronized void run() {
        try {
            saida = new DataOutputStream(cliente.getOutputStream());
            entrada = new DataInputStream(cliente.getInputStream());
            saidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
            entradaObjeto = new ObjectInputStream(cliente.getInputStream());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        while (true) {
            acao();
        }
    }

    public synchronized void acao() {
        try {
            comando = entrada.readUTF();
            if (comando.contains("CRIAR")) {
                UsuarioBEAN aux = (UsuarioBEAN) entradaObjeto.readObject();
                if (!aux.getCaminhoImagem().contains("FotoUsuario")) {
                    aux.setCaminhoImagem("C:\\Users\\adeja\\Desktop\\NovinhasRV\\Fotos Clientes\\" + aux.getNome() + ".jpg");
                }
                FileOutputStream file = new FileOutputStream("C:\\Users\\adeja\\Desktop\\NovinhasRV\\Fotos Clientes\\" + aux.getNome() + ".jpg");
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
                UsuarioBEAN aux = buscarUsuario(login, senha);
                if (notNull(aux)) {
                    saidaObjeto.writeObject(aux);
                    saidaObjeto.flush();
                } else {
                    saidaObjeto.writeObject(null);
                    saidaObjeto.flush();
                }
            } else if (comando.contains("ENCONTRAR")) {
                UsuarioBEAN aux = (UsuarioBEAN) entradaObjeto.readObject();
                if (notNull(aux)) {
                    UsuarioBEAN usuarioCompativel =encontrarCompanhia(aux);
                    saidaObjeto.writeObject(usuarioCompativel);
                    FileInputStream file2 = new FileInputStream(usuarioCompativel.getCaminhoImagem());
                    BufferedImage image = ImageIO.read(file2);
                    saidaObjeto.writeObject(retornaImagem(image));
                    saidaObjeto.flush();
                } else {
                    saidaObjeto.writeObject(null);
                    saidaObjeto.flush();
                }
            } else if (comando.contains("CONVERSA")) {

            } else if (comando.contains("VERIFICAR SOLICITACAO")) {
                int codigo2 = entrada.readInt();
                UsuarioBEAN usuario = verificarSolicitacao(codigo2);
                saidaObjeto.writeObject(usuario);
                saidaObjeto.flush();
            } else if (comando.contains("ENVIAR SOLICITACAO")) {
                UsuarioBEAN solicitante = (UsuarioBEAN) entradaObjeto.readObject();
                UsuarioBEAN solicitado = (UsuarioBEAN) entradaObjeto.readObject();
                salvarSolicitacao(solicitante, solicitado);
                boolean controle = verificarSolicitacao(solicitante.getId(), solicitado.getId());
//                while (controle == false) {
//                    controle = verificarSolicitacao(solicitante.getId(), solicitado.getId());
//                }
                saida.writeBoolean(controle);
                saida.flush();
            } else if (comando.contains("ENVIAR FOTO")) {
                UsuarioBEAN aux = (UsuarioBEAN) entradaObjeto.readObject();
                FileInputStream file2 = new FileInputStream(encontrarFoto(aux).getCaminhoImagem());
                BufferedImage image = ImageIO.read(file2);
                saidaObjeto.writeObject(retornaImagem(image));
                saidaObjeto.flush();
            } else if (comando.contains("ENVIAR MENSAGEM")) {
                String mensagem = entrada.readUTF();
                UsuarioBEAN usuario1 = (UsuarioBEAN) entradaObjeto.readObject();
                UsuarioBEAN usuario2 = (UsuarioBEAN) entradaObjeto.readObject();
                enviarMensagem(usuario1, usuario2, mensagem);

            } else if (comando.contains("RECEBER MENSAGEM")) {
                UsuarioBEAN usuario1 = (UsuarioBEAN) entradaObjeto.readObject();
                UsuarioBEAN usuario2 = (UsuarioBEAN) entradaObjeto.readObject();
                String mensagem = receberMensagem(usuario1, usuario2);
                saida.writeUTF(mensagem);
                saida.flush();
//                System.out.println(cliente.toString());
//                System.out.println(mensagem);
            }
        } catch (Exception ex) {

        }
    }

    // #########################################################################
    private synchronized void enviarMensagem(UsuarioBEAN usuario1, UsuarioBEAN usuario2, String mensagem) {
        ConversaBEAN conversa = new ConversaBEAN(0, usuario1.getId(), usuario2.getId());
        int codigo = controle.findIdConversa(conversa);
        // Esse IF serve para envitar criar 2 conversas diferentes
        if (codigo == 0) {
            conversa = new ConversaBEAN(0, usuario2.getId(), usuario1.getId());
            codigo = controle.findIdConversa(conversa);
        }
        if (codigo == 0) {
            controle.addConversa(conversa);
            Aux_conversaBEAN aux_conversa = new Aux_conversaBEAN(conversa.getCodigoConversa(), mensagem);
            controle.addAux_Conversa(aux_conversa);

        } else {
            Aux_conversaBEAN aux_conversa = new Aux_conversaBEAN(codigo, mensagem);
            controle.addAux_Conversa(aux_conversa);
        }
    }

    // #########################################################################
    private synchronized String receberMensagem(UsuarioBEAN usuario1, UsuarioBEAN usuario2) {
        String retorno = "";
        ConversaBEAN conversa = new ConversaBEAN(0, usuario1.getId(), usuario2.getId());
        int codigo = controle.findIdConversa(conversa);
        // Esse IF serve para envitar criar 2 conversas diferentes
        if (codigo == 0) {
            conversa = new ConversaBEAN(0, usuario2.getId(), usuario1.getId());
            codigo = controle.findIdConversa(conversa);
        }
        ArrayList<Aux_conversaBEAN> lista = controle.findConversas(codigo);
        for (Aux_conversaBEAN umaConversa : lista) {
            retorno += umaConversa.getMensage();
            retorno += "\n";
        }
        return retorno;
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
    private synchronized void salvarSolicitacao(UsuarioBEAN usuario1, UsuarioBEAN usuario2) {
        SolicitacaoBEAN solicitacao = new SolicitacaoBEAN(0, usuario1.getId(), usuario2.getId(), "ESPERANDO");
        //Verificar se já existe uma solicitação, para não criar outra
        int codigoSolicitacao = controle.findIdSolicitacao(solicitacao);
        if(controle.findSolicitacao(codigoSolicitacao) == null){
            controle.addSolicitacao(solicitacao);
        }
    }

    // #########################################################################
    private synchronized UsuarioBEAN verificarSolicitacao(int codigoCliente2) {
        ArrayList<SolicitacaoBEAN> lista = controle.findSolicitacaoPorCliente(codigoCliente2);
        for (SolicitacaoBEAN umaSolicitacao : lista) {
            if (umaSolicitacao.getAceitar().equals("ESPERANDO")) {
                // VAI ENVIAR O CLIENTE QUEM PEDIU PARA CONVERSAR
                UsuarioBEAN usuarioMandouSolicitacao = controle.findUsuario(umaSolicitacao.getCodigoCliente1());
                umaSolicitacao.setAceitar("ACEITO");
                controle.updateSolicitacao(umaSolicitacao);
                return usuarioMandouSolicitacao;
            }
        }
        return null;
    }

    private synchronized boolean verificarSolicitacao(int codigoCliente1, int codigoCliente2) {
        ArrayList<SolicitacaoBEAN> lista = controle.findSolicitacaoPorCliente(codigoCliente2);
        for (SolicitacaoBEAN umaSolicitacao : lista) {
            if (umaSolicitacao.getAceitar().equals("ACEITO") && umaSolicitacao.getCodigoCliente1() == codigoCliente1) {
                umaSolicitacao.setAceitar("CONVERSANDO");
                controle.updateSolicitacao(umaSolicitacao);
                return true;
            }
        }
        return false;
    }

    // #########################################################################
    private synchronized boolean notNull(UsuarioBEAN usuario) {
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
    private synchronized boolean gravarUsuario(UsuarioBEAN usuario) {
        idusuario = controle.addUsuario(usuario);
        if (controle.findIdUsuario(usuario) >= 0) {
            return true;
        }
        return false;
    }

    // #########################################################################
    private synchronized boolean atualizarUsuario(UsuarioBEAN usuario) {
        UsuarioBEAN usuario_antigo = controle.findUsuario(usuario.getId());
        controle.updateUsuario(usuario);
        if (controle.findUsuario(usuario.getId()).equals(usuario_antigo)) {
            return false;
        }
        return true;
    }

    // #########################################################################
    private synchronized UsuarioBEAN buscarUsuario(String login, String senha) {
        ArrayList<UsuarioBEAN> usuarios = controle.listaUsuarios();
        for (UsuarioBEAN usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    // #########################################################################
    private synchronized UsuarioBEAN buscarUsuario(int id_usuario) {
        return controle.findUsuario(id_usuario);
    }

    // #########################################################################
    private synchronized UsuarioBEAN encontrarCompanhia(UsuarioBEAN usuario) {
        int contador=0;
        Random rand = new Random();
        ArrayList<UsuarioBEAN> usuarios = controle.listaUsuarios();
        ArrayList<UsuarioBEAN> auxUsuarios = new ArrayList();
        for (UsuarioBEAN aux : usuarios) {
            if (!aux.getLogin().equals(usuario.getLogin())) {
                if (aux.getPrefEsporte() == usuario.getPrefEsporte()
                        || aux.getPrefGames() == usuario.getPrefGames()
                        || aux.getPrefIdade() == usuario.getPrefIdade()
                        || aux.getPrefMusica() == usuario.getPrefMusica()
                        || aux.getPrefReligioso() == usuario.getPrefReligioso()
                        || aux.getPrefSexo() == usuario.getPrefSexo()) {
                    //return aux;
                    contador++;
                    auxUsuarios.add(aux);
                }
            }

        }
        if(contador == 0){
        return null;
        }else{
            return auxUsuarios.get(rand.nextInt(contador));
        }
    }

    // #########################################################################
    private synchronized UsuarioBEAN encontrarFoto(UsuarioBEAN usuario) {
        UsuarioBEAN usuarioAux = controle.findUsuario(usuario.getId());
        return usuarioAux;
    }

    // #########################################################################
    private synchronized String senhaToMd5(String senha) {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.UsuarioBEAN;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author adeja
 */
public class Inicio extends javax.swing.JFrame {

    private Conexao conexao = Login.getConexaoServidor();
    private static UsuarioBEAN usuario;
    private static UsuarioBEAN usuarioCompativel;

    public Inicio(UsuarioBEAN usuario) {
        try {
            initComponents();
            setLocationRelativeTo(null);
            desativarBotoes();
            this.usuario = usuario;
            this.setTitle(usuario.getNome());
            procurarUsuario();

            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    //caixa de dialogo retorna um inteiro
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja finalizar essa operação?", "Finalizar", JOptionPane.YES_NO_OPTION);

                    //sim = 0, nao = 1
                    if (resposta == 0) {
                        try {
                            conexao.getSaida().writeUTF("SAIR");
                        } catch (IOException ex) {
                            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.exit(0);
                    }

                }
            });
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        new receberSolicitacao().start();
    }

    public void procurarUsuario() throws IOException, ClassNotFoundException {
        conexao.getSaida().writeUTF("ENCONTRAR");
        conexao.getSaida().flush();
        conexao.getSaidaObjeto().writeObject(this.usuario);
        conexao.getSaidaObjeto().flush();
        usuarioCompativel = (UsuarioBEAN) conexao.getEntradaObjeto().readObject();

        byte imagem[] = (byte[]) conexao.getEntradaObjeto().readObject();

        ImageIcon im = new ImageIcon(imagem);
        exibeimagem.setIcon(new ImageIcon(im.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));

        if (usuarioCompativel == null) {
            JOptionPane.showMessageDialog(null, "NENHUM USUARIO COMPATIVEL");
        } else {
            textNome.setText(usuarioCompativel.getNome());
            textIdade.setText(Integer.toString(usuarioCompativel.getIdade()));
            textDescricao.setText(usuarioCompativel.getDescricao());
            reativarBotoes();
        }
    }

    public void setUsuario(UsuarioBEAN usuario) {
        this.usuario = usuario;
    }

    private void desativarBotoes() {
        botaoConversar.setEnabled(false);
        botaoRecusar.setEnabled(false);
    }

    private void reativarBotoes() {
        botaoConversar.setEnabled(true);
        botaoRecusar.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botaoConversar = new javax.swing.JButton();
        botaoRecusar = new javax.swing.JButton();
        txtNome = new javax.swing.JLabel();
        txtIdade = new javax.swing.JLabel();
        txtDescrição = new javax.swing.JLabel();
        textNome = new javax.swing.JTextField();
        textIdade = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textDescricao = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        exibeimagem = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setMaximumSize(new java.awt.Dimension(200, 200));
        jPanel1.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        botaoConversar.setBackground(new java.awt.Color(51, 255, 51));
        botaoConversar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        botaoConversar.setForeground(new java.awt.Color(255, 255, 255));
        botaoConversar.setText("Conversar");
        botaoConversar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConversarActionPerformed(evt);
            }
        });

        botaoRecusar.setBackground(new java.awt.Color(255, 0, 0));
        botaoRecusar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        botaoRecusar.setForeground(new java.awt.Color(255, 255, 255));
        botaoRecusar.setText("Recusar");
        botaoRecusar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRecusarActionPerformed(evt);
            }
        });

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNome.setText("Nome:");

        txtIdade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIdade.setText("Idade:");

        txtDescrição.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescrição.setText("Descrição:");

        textNome.setEnabled(false);

        textIdade.setEnabled(false);

        textDescricao.setColumns(20);
        textDescricao.setRows(5);
        textDescricao.setEnabled(false);
        jScrollPane1.setViewportView(textDescricao);

        exibeimagem.setText("FOTO");
        exibeimagem.setMaximumSize(new java.awt.Dimension(200, 200));
        exibeimagem.setMinimumSize(new java.awt.Dimension(200, 200));
        exibeimagem.setPreferredSize(new java.awt.Dimension(200, 200));

        jMenu1.setText("Usuário");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Perfil");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Configurações");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Verificar Solicitações");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Sobre");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(exibeimagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDescrição)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNome)
                                .addComponent(txtIdade)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textNome)
                            .addComponent(textIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(botaoRecusar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoConversar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exibeimagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome)
                    .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdade)
                    .addComponent(textIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescrição)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoConversar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoRecusar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Perfil perfil = new Perfil(this.usuario, this);
        perfil.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Configuracoes configuracao = new Configuracoes(usuario, this);
        configuracao.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    private void botaoConversarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConversarActionPerformed
//        Chat chat;
//        try {
//            conexao.getSaida().writeUTF("ENVIAR SOLICITACAO");
//            conexao.getSaidaObjeto().writeObject(usuario);
//            conexao.getSaidaObjeto().flush();
//            conexao.getSaidaObjeto().writeObject(usuarioCompativel);
//            conexao.getSaidaObjeto().flush();
//            if (conexao.getEntrada().readBoolean() == true) {
//                JOptionPane.showMessageDialog(null, "POVE CONVERSAR");
//            }
//            chat = new Chat(usuario, usuarioCompativel);
//            chat.setVisible(true);
//        } catch (IOException ex) {
//            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
//        }

        JOptionPane.showMessageDialog(null, "SOLICITAÇÃO DE CONVERSA ENVIADA");
        new enviarSolicitacao().start();
    }//GEN-LAST:event_botaoConversarActionPerformed

    class enviarSolicitacao extends Thread {

        public enviarSolicitacao() {
        }

        public void run() {
            while (true) {
                enviaMsg("ENVIAR SOLICITACAO");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }
        }
    }

    public synchronized void enviaMsg(String msg) {
        try {
            conexao.getSaida().writeUTF(msg);
            conexao.getSaida().flush();
            conexao.getSaidaObjeto().writeObject(usuario);
            conexao.getSaidaObjeto().flush();
            conexao.getSaidaObjeto().writeObject(usuarioCompativel);
            conexao.getSaidaObjeto().flush();
            if (conexao.getEntrada().readBoolean() == true) {
                Chat chat = new Chat(usuario, usuarioCompativel);
                chat.setVisible(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
//        try {
//            conexao.getSaida().writeUTF("VERIFICAR SOLICITACAO");
//            conexao.getSaida().flush();
//            conexao.getSaida().writeInt(usuario.getId());
//            UsuarioBEAN usuarioCompativel = (UsuarioBEAN) conexao.getEntradaObjeto().readObject();
//            if (usuarioCompativel == null) {
//                JOptionPane.showMessageDialog(null, "AINDA NENHUMA SOLICITAÇÃO");
//            } else {
//                Chat chat = new Chat(usuario, usuarioCompativel);
//                chat.setVisible(true);
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jMenu2MouseClicked

    class receberSolicitacao extends Thread {

        public receberSolicitacao() {
        }

        public void run() {
            while (true) {
                enviaComando("VERIFICAR SOLICITACAO");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }
        }
    }

    public synchronized void enviaComando(String msg) {
        try {
            conexao.getSaida().writeUTF(msg);
            conexao.getSaida().flush();
            conexao.getSaida().writeInt(usuario.getId());
            UsuarioBEAN usuarioCompativel2 = (UsuarioBEAN) conexao.getEntradaObjeto().readObject();
            if (usuarioCompativel2 != null) {
                JOptionPane.showMessageDialog(null, "ALGUÉM ESTA QUERENDO CONVERSAR");
                Chat chat = new Chat(usuario, usuarioCompativel2);
                chat.setVisible(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void botaoRecusarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRecusarActionPerformed
        try {
            procurarUsuario();
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoRecusarActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        Sobre sobre = new Sobre(this, true);
        sobre.setVisible(true);
    }//GEN-LAST:event_jMenu3MouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Inicio().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoConversar;
    private javax.swing.JButton botaoRecusar;
    private javax.swing.JLabel exibeimagem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea textDescricao;
    private javax.swing.JTextField textIdade;
    private javax.swing.JTextField textNome;
    private javax.swing.JLabel txtDescrição;
    private javax.swing.JLabel txtIdade;
    private javax.swing.JLabel txtNome;
    // End of variables declaration//GEN-END:variables
}

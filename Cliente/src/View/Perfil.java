/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.UsuarioBEAN;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author adeja
 */
public class Perfil extends javax.swing.JFrame {

    private Conexao conexao = Login.getConexaoServidor();
    private UsuarioBEAN usuario;
    private Inicio instance;

    public Perfil(UsuarioBEAN usuario,Inicio instance) {
        try {
            initComponents();
            this.instance = instance;
            this.usuario = usuario;
            conexao.getSaida().writeUTF("ATUALIZAR");
            conexao.getSaida().flush();
            conexao.getSaida().writeInt(usuario.getId());
            conexao.getSaida().flush();
            usuario = (UsuarioBEAN) conexao.getEntradaObjeto().readObject();
            System.out.println(usuario.getNome());
        } catch (IOException ex) {
            Logger.getLogger(Perfil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Perfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        preencherCampos();
    }

    private void preencherCampos() {
        textNome.setText(usuario.getNome());
        textIdade.setText(Integer.toString(usuario.getIdade()));
        textDescricao.setText(usuario.getDescricao());
        if (usuario.getPrefSexo() == 0) {
            comboSexo.setSelectedIndex(0);
        }else{
            comboSexo.setSelectedIndex(1);
        }
        if (usuario.getPrefEsporte() == 0) {
            comboEsporte.setSelectedIndex(1);
        }else{
            comboEsporte.setSelectedIndex(0);
        }
        if (usuario.getPrefMusica() == 0) {
            comboMusica.setSelectedIndex(1);
        }else{
            comboMusica.setSelectedIndex(0);
        }
        if (usuario.getPrefGames() == 0) {
            comboGames.setSelectedIndex(1);
        }else{
            comboGames.setSelectedIndex(0);
        }
        if (usuario.getPrefIdade() == 0) {
            comboIdade.setSelectedIndex(1);
        }else{
            comboIdade.setSelectedIndex(0);
        }
        if (usuario.getPrefReligioso() == 0) {
            comboReligiao.setSelectedIndex(1);
        }else{
            comboReligiao.setSelectedIndex(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelAutor = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        comboSexo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        comboMusica = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comboEsporte = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        comboGames = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        comboReligiao = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        labelLogin1 = new javax.swing.JLabel();
        comboIdade = new javax.swing.JComboBox<>();
        botaoAtualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        textNome = new javax.swing.JTextField();
        labelLogin = new javax.swing.JLabel();
        textIdade = new javax.swing.JTextField();
        labelLogin6 = new javax.swing.JLabel();
        textDescricao = new javax.swing.JTextField();
        labelNome2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelAutor.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        labelAutor.setText("Perfil");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Preferencias"));

        comboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino" }));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("SEXO:");

        comboMusica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIM", "NÃO" }));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("MÚSICA:");

        comboEsporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIM", "NÃO" }));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("ESPORTE:");

        comboGames.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIM", "NÃO" }));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("GAMES:");

        comboReligiao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIM", "NÃO" }));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("RELIGIOSO:");

        labelLogin1.setBackground(new java.awt.Color(0, 0, 0));
        labelLogin1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelLogin1.setText("IDADE:");

        comboIdade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIM", "NÃO" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboEsporte, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelLogin1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboReligiao, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132)))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGames, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(comboMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboEsporte, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(comboGames, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(comboReligiao, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLogin1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(comboIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoAtualizar.setText("ATUALIZA");
        botaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("FOTO");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Cadastrais"));

        labelLogin.setBackground(new java.awt.Color(0, 0, 0));
        labelLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelLogin.setText("NOME:");

        labelLogin6.setBackground(new java.awt.Color(0, 0, 0));
        labelLogin6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelLogin6.setText("IDADE:");

        labelNome2.setBackground(new java.awt.Color(0, 0, 0));
        labelNome2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelNome2.setText("DESCRIÇÃO:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogin)
                .addGap(47, 47, 47)
                .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labelNome2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(293, 293, 293)
                            .addComponent(labelLogin6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(77, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLogin))
                .addContainerGap(144, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelLogin6)
                        .addComponent(textIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labelNome2)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(textDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                    .addContainerGap()))
        );

        jButton2.setText("ALTERAR FOTO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(labelAutor))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(botaoAtualizar)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelAutor)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(botaoAtualizar)
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EscolherFoto escolher = new EscolherFoto(this, true);
        escolher.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        try {
            UsuarioBEAN retorno = new UsuarioBEAN();
            byte prefSexo = 1;
            byte prefEsporte = 1;
            byte prefReligioso = 1;
            byte prefMusica = 1;
            byte prefGames = 1;
            byte prefIdade = 1;
            if (comboSexo.getSelectedItem() == "Masculino") {
                prefSexo = 0;
            }
            if (comboEsporte.getSelectedItem() == "NÃO") {
                prefEsporte = 0;
            }
            if (comboMusica.getSelectedItem() == "NÃO") {
                prefMusica = 0;
            }
            if (comboGames.getSelectedItem() == "NÃO") {
                prefGames = 0;
            }
            if (comboIdade.getSelectedItem() == "NÃO") {
                prefIdade = 0;
            }
            if (comboReligiao.getSelectedItem() == "NÃO") {
                prefReligioso = 0;
            }
            retorno.setNome(textNome.getText());
            retorno.setIdade(Integer.parseInt(textIdade.getText()));
            retorno.setDescricao(textDescricao.getText());
            retorno.setPrefEsporte(prefEsporte);
            retorno.setPrefSexo(prefSexo);
            retorno.setPrefMusica(prefMusica);
            retorno.setPrefReligioso(prefReligioso);
            retorno.setPrefGames(prefGames);
            retorno.setPrefIdade(prefIdade);
            retorno.setEmail(usuario.getEmail());
            retorno.setLogin(usuario.getLogin());
            retorno.setId(usuario.getId());
            retorno.setSenha(usuario.getSenha());
            conexao.getSaidaObjeto().writeObject(retorno);
            conexao.getSaidaObjeto().flush();
            if(conexao.getEntrada().readBoolean() == false){
                JOptionPane.showMessageDialog(null, "ERROR!");
            }else{
                JOptionPane.showMessageDialog(null, "DADOS SALVOS");
                instance.setUsuario(retorno);
                this.setVisible(false);
            }
        } catch (IOException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoAtualizarActionPerformed

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
//            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Perfil().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JComboBox<String> comboEsporte;
    private javax.swing.JComboBox<String> comboGames;
    private javax.swing.JComboBox<String> comboIdade;
    private javax.swing.JComboBox<String> comboMusica;
    private javax.swing.JComboBox<String> comboReligiao;
    private javax.swing.JComboBox<String> comboSexo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelAutor;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelLogin1;
    private javax.swing.JLabel labelLogin6;
    private javax.swing.JLabel labelNome2;
    private javax.swing.JTextField textDescricao;
    private javax.swing.JTextField textIdade;
    private javax.swing.JTextField textNome;
    // End of variables declaration//GEN-END:variables
}

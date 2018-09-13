/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.UsuarioBEAN;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author adeja
 */
public class Chat extends javax.swing.JFrame {

    private UsuarioBEAN usuario1;
    private UsuarioBEAN usuario2;
    private Conexao conexao = Login.getConexaoServidor();

    public Chat(UsuarioBEAN usuario1, UsuarioBEAN usuario2) throws IOException {
        initComponents();
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.setTitle(usuario1.getNome()+" - > "+ usuario2.getNome());
        try {
            conexao.getSaida().writeUTF("ENVIAR FOTO");
            conexao.getSaida().flush();
            conexao.getSaidaObjeto().writeObject(this.usuario1);
            conexao.getSaidaObjeto().flush();

            byte imagem[];

            imagem = (byte[]) conexao.getEntradaObjeto().readObject();
            ImageIcon im = new ImageIcon(imagem);
            imagemUsuario1.setIcon(new ImageIcon(im.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            
            conexao.getSaida().writeUTF("ENVIAR FOTO");
            conexao.getSaida().flush();
            conexao.getSaidaObjeto().writeObject(this.usuario2);
            conexao.getSaidaObjeto().flush();


            imagem = (byte[]) conexao.getEntradaObjeto().readObject();
            im = new ImageIcon(imagem);
            imagemUsuario2.setIcon(new ImageIcon(im.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
             new atualizaChat().start();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        setLocationRelativeTo(null);
        
    }

    class atualizaChat extends Thread {
       
        public atualizaChat(){

        }
        public void run(){
            while(true){
                enviaMsg("RECEBER MENSAGEM MENSASSEM");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }
        }    
    }
   
    public synchronized void enviaMsg(String msg){
        try {
            conexao.getSaida().writeUTF(msg);
            conexao.getSaida().flush();
            conexao.getSaidaObjeto().writeObject(usuario1);
            conexao.getSaidaObjeto().flush();
            conexao.getSaidaObjeto().writeObject(usuario2);
            conexao.getSaidaObjeto().flush();
            textao.setText(conexao.getEntrada().readUTF());
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textao = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        textEnviar = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        imagemUsuario2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        imagemUsuario1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        textao.setEditable(false);
        textao.setColumns(20);
        textao.setRows(5);
        jScrollPane1.setViewportView(textao);

        textEnviar.setColumns(20);
        textEnviar.setRows(5);
        jScrollPane2.setViewportView(textEnviar);

        imagemUsuario2.setText("jLabel1");
        imagemUsuario2.setMaximumSize(new java.awt.Dimension(200, 200));
        imagemUsuario2.setMinimumSize(new java.awt.Dimension(200, 200));
        imagemUsuario2.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(imagemUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imagemUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        imagemUsuario1.setText("jLabel2");
        imagemUsuario1.setMaximumSize(new java.awt.Dimension(200, 200));
        imagemUsuario1.setMinimumSize(new java.awt.Dimension(200, 200));
        imagemUsuario1.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(imagemUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(imagemUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(20, 20, 20)))))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton1))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            conexao.getSaida().writeUTF("ENVIAR MENSAGEM");
            conexao.getSaida().flush();
            conexao.getSaida().writeUTF(this.getTitle()+" : "+textEnviar.getText());
            conexao.getSaida().flush();
            conexao.getSaidaObjeto().writeObject(usuario1);
            conexao.getSaidaObjeto().flush();
            conexao.getSaidaObjeto().writeObject(usuario2);
            conexao.getSaidaObjeto().flush();
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imagemUsuario1;
    private javax.swing.JLabel imagemUsuario2;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textEnviar;
    private javax.swing.JTextArea textao;
    // End of variables declaration//GEN-END:variables
}

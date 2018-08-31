
package webcam;

import View.CadastroUsuario;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TirarFotoWebcam extends javax.swing.JFrame {
    
    private Dimension dimensao;
    private Webcam webcam;
    private int validador = 0;
    private CadastroUsuario c;
    
    public TirarFotoWebcam(CadastroUsuario obj) {
        initComponents();
        c = obj;
        inicializar();
        setLocationRelativeTo(null);
        validador = 0;
    }

    public void inicializar() {
        try {
            dimensao = WebcamResolution.VGA.getSize();
            webcam = Webcam.getDefault();
            webcam.setViewSize(dimensao);   
            
            for (Dimension d : webcam.getViewSizes()) {
                System.out.println("Largura: " + d.getWidth() + " Largura:" + d.getHeight());
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void inicializarVideo() {
        validador = 0;
        new Thread() {
            @Override
            public void run(){
                while (validador == 0) {
                    try {
                        Image imagem = webcam.getImage();
                        ImageIcon icon = new ImageIcon(imagem);
                        icon.setImage(icon.getImage().getScaledInstance(240, labelFoto.getHeight(), 100));
                        labelFoto.setIcon(icon);
                        Thread.sleep(50);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }.start();
    }
    
    public static void main(String[] args) {
        CadastroUsuario obj = null;
        new TirarFotoWebcam(obj).setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        botaoCamera = new javax.swing.JToggleButton();
        botaoTirarFoto = new javax.swing.JToggleButton();
        botaoCancelar = new javax.swing.JToggleButton();
        labelFoto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        botaoCamera.setBackground(new java.awt.Color(51, 51, 255));
        botaoCamera.setText("Câmera");
        botaoCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCameraActionPerformed(evt);
            }
        });

        botaoTirarFoto.setBackground(new java.awt.Color(51, 255, 0));
        botaoTirarFoto.setText("Tirar Foto");
        botaoTirarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoTirarFotoActionPerformed(evt);
            }
        });

        botaoCancelar.setBackground(new java.awt.Color(255, 0, 0));
        botaoCancelar.setText("Voltar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        labelFoto.setBackground(new java.awt.Color(0, 0, 0));
        labelFoto.setForeground(new java.awt.Color(51, 255, 51));
        labelFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFoto.setText("                     ");
        labelFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelFoto.setOpaque(true);

        jLayeredPane1.setLayer(botaoCamera, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(botaoTirarFoto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(botaoCancelar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(labelFoto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(botaoCamera)
                        .addGap(34, 34, 34)
                        .addComponent(botaoTirarFoto)
                        .addGap(33, 33, 33)
                        .addComponent(botaoCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCamera)
                    .addComponent(botaoTirarFoto)
                    .addComponent(botaoCancelar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCameraActionPerformed
        
        new Thread () {
            public void run() {
                labelFoto.setText("Carregando...");
                webcam.open();
                labelFoto.setText(" ");
                inicializarVideo();
            }
        }.start();
        
    }//GEN-LAST:event_botaoCameraActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        new Thread () {
            public void run() {
                validador = 1;
                webcam.close();
            }
        }.start();
        this.dispose();
        c.setVisible(true);
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoTirarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoTirarFotoActionPerformed
         try {
             ByteArrayOutputStream buff = new ByteArrayOutputStream();
             ImageIO.write(webcam.getImage(), "jpg", buff);
             byte[] bytes = buff.toByteArray();
             
             ByteArrayInputStream is = new ByteArrayInputStream(bytes);
             BufferedImage imagem = ImageIO.read(is);

             int novaLargura = 500, novaAltura = 500;
             BufferedImage new_img = new BufferedImage(novaLargura, novaAltura, BufferedImage.TYPE_INT_RGB);
             Graphics2D g = new_img.createGraphics();
             g.drawImage(imagem, 0, 0, novaLargura, novaAltura, null);
             ImageIO.write(new_img, "jpg", new File("C:\\FotoUsuario\\FotoUsuario.jpg"));
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
    }//GEN-LAST:event_botaoTirarFotoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton botaoCamera;
    private javax.swing.JToggleButton botaoCancelar;
    private javax.swing.JToggleButton botaoTirarFoto;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel labelFoto;
    // End of variables declaration//GEN-END:variables
}

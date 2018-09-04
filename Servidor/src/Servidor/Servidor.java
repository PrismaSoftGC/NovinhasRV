
package Servidor;

import java.net.ServerSocket;
import javax.swing.JOptionPane;


public class Servidor{
    public static void main(String[] args) {
            
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket server = new ServerSocket(3312);
                    while(true){
                        new EspaçoCliente(server.accept()).start();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
         
    }
}   
    


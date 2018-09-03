
package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(3312);
        new Thread(() -> {
            while (true){
                try {
                    Socket client = server.accept();
                    if (client != null) {
                        new Sala(client);
                    }
                } catch (IOException ex) {   
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}   
    


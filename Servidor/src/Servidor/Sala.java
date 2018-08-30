
package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sala extends Thread{
    
    Socket cliente=null;
    DataInputStream entrada=null;
    DataOutputStream saida=null;
    
    public Sala(Socket cliente) {
        this.cliente=cliente;
        try {
            entrada=new DataInputStream(cliente.getInputStream());
            saida=new DataOutputStream(cliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
    
        while(true){
            
        }
    
    }
}

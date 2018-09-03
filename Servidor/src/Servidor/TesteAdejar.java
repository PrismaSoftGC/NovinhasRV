/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Model.UsuarioBEAN;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe Peretti
 */
public class TesteAdejar {
    public static void main(String[] args) {
        
        Socket aux;
        ObjectOutputStream saida;
        ObjectInputStream entrada;
        
        try {
            ServerSocket server = new ServerSocket(3312);
            
            while(true){
                aux = server.accept();
                if (aux != null) {
                    saida = new ObjectOutputStream(aux.getOutputStream());
                    entrada = new ObjectInputStream(aux.getInputStream());
                    String str;
                    while(true){
                        str = entrada.readUTF();
                    
                     if (str.contains("CRIAR")) {
                        UsuarioBEAN usuario = (UsuarioBEAN) entrada.readObject();
                        //UsuarioBEAN usuario = new UsuarioBEAN(); 
                       
                       
                        if (usuario != null) {
                            System.out.println("Nome> "+usuario.getNome());
                            System.out.println("Senha> "+usuario.getSenha());
                        }
                    }else if (str.contains("SAIR")) {
                            break;
                        }
                    }
                    
                    
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(TesteAdejar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}

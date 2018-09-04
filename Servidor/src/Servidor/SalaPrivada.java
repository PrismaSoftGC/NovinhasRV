/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe Peretti
 */
public class SalaPrivada extends Thread{
    
    private Socket servidor;
    private Socket usuario_01;
    private Socket usuario_02;
    
    private ObjectInputStream in_01;
    private ObjectOutputStream out_01;
    
    private ObjectInputStream in_02;
    private ObjectOutputStream out_02;
    
    private StringBuffer conversa;
    
    public SalaPrivada(Socket servidor, Socket usuario_01, Socket usuario_02) throws IOException{
        this.servidor = servidor;
        this.usuario_01 = usuario_01;
        this.usuario_02 = usuario_02;
        this.conversa = new StringBuffer();
        
        in_01 = new ObjectInputStream(usuario_01.getInputStream());
        out_01 = new ObjectOutputStream(usuario_01.getOutputStream());
        
        in_02 = new ObjectInputStream(usuario_02.getInputStream());
        out_02 = new ObjectOutputStream(usuario_02.getOutputStream());
    }
    
    @Override
    public void run(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        escrever("Usuario_01: "+in_01.readUTF()+"\n");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na Thread usuario 01 entrada", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        escrever("Usuario_02: "+in_02.readUTF()+"\n");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na Thread usuario 02 entrada", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        out_01.writeUTF(ler());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na Thread usuario 01 saida", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        out_02.writeUTF(ler());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na Thread usuario 02 saida", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }).start();
    }
    
    public synchronized void escrever(String msg){
        conversa.append(msg);
    }
    
    public synchronized String ler(){
        return conversa.toString();
    }
}

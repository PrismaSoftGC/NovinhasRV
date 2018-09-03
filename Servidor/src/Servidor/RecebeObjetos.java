
package Servidor;

import Model.UsuarioBEAN;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecebeObjetos extends Thread implements Runnable {

    private InputStream inputCliente;
    private ObjectInputStream input;
    private UsuarioBEAN usuario;

    public RecebeObjetos(InputStream inputCliente) {
        this.inputCliente = inputCliente;
    }

    @Override
    public void run() {
        try {
            input = new ObjectInputStream(inputCliente);
            Object aux = input.readObject();
            if (aux instanceof UsuarioBEAN) {
                usuario = (UsuarioBEAN) aux;
                Controller controle;
                //Chame aqui os métodos
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecebeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

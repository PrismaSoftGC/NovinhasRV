/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author adeja
 */
public class Conexao {
    private ObjectOutputStream saida;
    private ObjectInputStream entrada;

    public Conexao(ObjectOutputStream saida, ObjectInputStream entrada) {
        this.saida = saida;
        this.entrada = entrada;
    }

    public ObjectOutputStream getSaida() {
        return saida;
    }

    public void setSaida(ObjectOutputStream saida) {
        this.saida = saida;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(ObjectInputStream entrada) {
        this.entrada = entrada;
    }
    
    
    
}

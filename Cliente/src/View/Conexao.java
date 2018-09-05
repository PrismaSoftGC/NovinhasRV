/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author adeja
 */
public class Conexao {
    private DataOutputStream saida;
    private DataInputStream entrada;
    private ObjectOutputStream saidaObjeto;
    private ObjectInputStream entradaObjeto;

    public Conexao(DataOutputStream saida, DataInputStream entrada, ObjectOutputStream saidaObjeto, ObjectInputStream entradaObjeto) {
        this.saida = saida;
        this.entrada = entrada;
        this.saidaObjeto = saidaObjeto;
        this.entradaObjeto = entradaObjeto;
    }

    public DataOutputStream getSaida() {
        return saida;
    }

    public void setSaida(DataOutputStream saida) {
        this.saida = saida;
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }

    public ObjectOutputStream getSaidaObjeto() {
        return saidaObjeto;
    }

    public void setSaidaObjeto(ObjectOutputStream saidaObjeto) {
        this.saidaObjeto = saidaObjeto;
    }

    public ObjectInputStream getEntradaObjeto() {
        return entradaObjeto;
    }

    public void setEntradaObjeto(ObjectInputStream entradaObjeto) {
        this.entradaObjeto = entradaObjeto;
    }

  
    
    
    
}

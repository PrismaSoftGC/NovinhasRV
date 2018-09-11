/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author adeja
 */
public class Aux_conversaBEAN {
    private int codigoConversa;
    private String mensage;

    public Aux_conversaBEAN(int codigoConversa, String mensage) {
        this.codigoConversa = codigoConversa;
        this.mensage = mensage;
    }

    public int getCodigoConversa() {
        return codigoConversa;
    }

    public void setCodigoConversa(int codigoConversa) {
        this.codigoConversa = codigoConversa;
    }

    public String getMensage() {
        return mensage;
    }

    public void setMensage(String mensage) {
        this.mensage = mensage;
    }
    
}

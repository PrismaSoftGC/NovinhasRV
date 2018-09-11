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
public class ConversaBEAN {
    private int codigoConversa;
    private int codigoCliente1;
    private int codigoCliente2;

    public ConversaBEAN(int codigoConversa, int codigoCliente1, int codigoCliente2) {
        this.codigoConversa = codigoConversa;
        this.codigoCliente1 = codigoCliente1;
        this.codigoCliente2 = codigoCliente2;
    }

    public int getCodigoConversa() {
        return codigoConversa;
    }

    public void setCodigoConversa(int codigoConversa) {
        this.codigoConversa = codigoConversa;
    }

    public int getCodigoCliente1() {
        return codigoCliente1;
    }

    public void setCodigoCliente1(int codigoCliente1) {
        this.codigoCliente1 = codigoCliente1;
    }

    public int getCodigoCliente2() {
        return codigoCliente2;
    }

    public void setCodigoCliente2(int codigoCliente2) {
        this.codigoCliente2 = codigoCliente2;
    }
    
    
}

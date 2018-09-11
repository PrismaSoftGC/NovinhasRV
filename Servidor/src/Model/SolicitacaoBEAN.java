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
public class SolicitacaoBEAN {
    private int codigoSolicitacao;
    private int codigoCliente1;
    private int codigoCliente2;
    private String aceitar;

    public SolicitacaoBEAN(int codigoCompativel, int codigoCliente1, int codigoCliente2, String aceitar) {
        this.codigoSolicitacao = codigoCompativel;
        this.codigoCliente1 = codigoCliente1;
        this.codigoCliente2 = codigoCliente2;
        this.aceitar = aceitar;
    }

    public int getCodigoSolicitacao() {
        return codigoSolicitacao;
    }

    public void setCodigoSolicitacao(int codigoSolicitacao) {
        this.codigoSolicitacao = codigoSolicitacao;
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

    public String getAceitar() {
        return aceitar;
    }

    public void setAceitar(String aceitar) {
        this.aceitar = aceitar;
    }
    
}

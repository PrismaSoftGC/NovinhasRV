
package Servidor;

import Model.Aux_conversaBEAN;
import Model.ConversaBEAN;
import Model.SolicitacaoBEAN;
import Model.UsuarioBEAN;
import java.util.ArrayList;

public class Controller {

  
    
    public Controller() {
       
    }

    public int addUsuario(UsuarioBEAN usuario) {
        return UsuarioDAO.getInstance().create(usuario);
    }

    public int updateUsuario(UsuarioBEAN usuario) {
        return UsuarioDAO.getInstance().update(usuario);
    }

    public UsuarioBEAN findUsuario(int idusuario) {
        return UsuarioDAO.getInstance().findUsuario(idusuario);
    }

    public int findIdUsuario(UsuarioBEAN usuario) {
        return UsuarioDAO.getInstance().findId(usuario);
    }

    public ArrayList<UsuarioBEAN> listaUsuarios() {
        return UsuarioDAO.getInstance().findAllUsuario();
    }

    public ArrayList<UsuarioBEAN> listaUsuario(String nome) {
        return UsuarioDAO.getInstance().findAllUsuario(nome);
    }
    /////////////////////////////////// AUX //////////////////////////////////////
    
    public int addAux_Conversa(Aux_conversaBEAN aux) {
        return Aux_conversaDAO.getInstance().create(aux);
    }

    public int updateAux(Aux_conversaBEAN aux) {
        return Aux_conversaDAO.getInstance().update(aux);
    }

    public Aux_conversaBEAN findAux(int idAux) {
        return Aux_conversaDAO.getInstance().findAux(idAux);
    }

    public ArrayList<Aux_conversaBEAN> findConversas (int codigoConversa) {
        return Aux_conversaDAO.getInstance().findConversas(codigoConversa);
    }

    public ArrayList<Aux_conversaBEAN> listaAux() {
        return Aux_conversaDAO.getInstance().findAllAux();
    }
    
    ////////////////////////////////////// CONVERSA ////////////////////////////////
    
    public int addConversa(ConversaBEAN conversa) {
        return ConversaDAO.getInstance().create(conversa);
    }

    public int updateConversa(ConversaBEAN conversa) {
        return ConversaDAO.getInstance().update(conversa);
    }

    public ConversaBEAN findConversa(int idConversa) {
        return ConversaDAO.getInstance().findConversa(idConversa);
    }

    public int findIdConversa(ConversaBEAN conversa) {
        return ConversaDAO.getInstance().findId(conversa);
    }

    public ArrayList<ConversaBEAN> listaConversas() {
        return ConversaDAO.getInstance().findAllConversa();
    }
    
     ////////////////////////////////////// SOLICITAÇAO ////////////////////////////////
    
    public int addSolicitacao(SolicitacaoBEAN solicitacao) {
        return SolicitacaoDAO.getInstance().create(solicitacao);
    }

    public int updateSolicitacao(SolicitacaoBEAN solicitacao) {
        return SolicitacaoDAO.getInstance().update(solicitacao);
    }

    public SolicitacaoBEAN  findSolicitacao(int idSolicitacao) {
        return SolicitacaoDAO.getInstance().findSolicitacao(idSolicitacao);
    }

    public int findIdSolicitacao(SolicitacaoBEAN solicitacao) {
        return SolicitacaoDAO.getInstance().findId(solicitacao);
    }

    public ArrayList<SolicitacaoBEAN> listaSolicitacoes() {
        return SolicitacaoDAO.getInstance().findAllSolicitacao();
    }
    
    public  ArrayList<SolicitacaoBEAN> findSolicitacaoPorCliente(int codigoCliente2){
        return SolicitacaoDAO.getInstance().findSolicitacaoPorCliente(codigoCliente2);
    }
}


package Model;

import java.io.Serializable;


public class UsuarioBEAN implements Serializable  {
    
    private int id;
    private String login;
    private String nome;
    private String email;
    private String senha;
    private int idade;
    private String descricao;
    private byte prefSexo;
    private byte prefEsporte;
    private byte prefReligioso;
    private byte prefMusica;
    private byte prefGames;
    private byte prefIdade;

     public UsuarioBEAN(){};
    
    public UsuarioBEAN(int id, String login,String nome, String email, String senha, int idade, String descricao, byte prefSexo, byte prefEsporte, byte prefReligioso, byte prefMusica, byte prefGames, byte prefIdade) {
        this.id = id;
        this.login = login;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.descricao = descricao;
        this.prefSexo = prefSexo;
        this.prefEsporte = prefEsporte;
        this.prefReligioso = prefReligioso;
        this.prefMusica = prefMusica;
        this.prefGames = prefGames;
        this.prefIdade = prefIdade;
    }

    public UsuarioBEAN(String nome, String login,String email, String senha, int idade, String descricao, byte prefSexo, byte prefEsporte, byte prefReligioso, byte prefMusica, byte prefGames, byte prefIdade) {
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.descricao = descricao;
        this.prefSexo = prefSexo;
        this.prefEsporte = prefEsporte;
        this.prefReligioso = prefReligioso;
        this.prefMusica = prefMusica;
        this.prefGames = prefGames;
        this.prefIdade = prefIdade;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte getPrefSexo() {
        return prefSexo;
    }

    public void setPrefSexo(byte prefSexo) {
        this.prefSexo = prefSexo;
    }

    public byte getPrefEsporte() {
        return prefEsporte;
    }

    public void setPrefEsporte(byte prefEsporte) {
        this.prefEsporte = prefEsporte;
    }

    public byte getPrefReligioso() {
        return prefReligioso;
    }

    public void setPrefReligioso(byte prefReligioso) {
        this.prefReligioso = prefReligioso;
    }

    public byte getPrefMusica() {
        return prefMusica;
    }

    public void setPrefMusica(byte prefMusica) {
        this.prefMusica = prefMusica;
    }

    public byte getPrefGames() {
        return prefGames;
    }

    public void setPrefGames(byte prefGames) {
        this.prefGames = prefGames;
    }

    public byte getPrefIdade() {
        return prefIdade;
    }

    public void setPrefIdade(byte prefIdade) {
        this.prefIdade = prefIdade;
    }
    
}

    package bd;

 
 
public class BancoT extends Banco {
	public static String getvarchar(int num){
		
		return " varchar("+num+")";
		
	}
	  public BancoT(boolean exe) {
        super(exe);
    }
 		
  public BancoT() {
        super(true);
    }
 		
	public static String blob="blob";
	public static String varchar="varchar(250)";
	public static String integer="integer";
	public static String numero="float";
	public static String real="float ";
	public static String auto_increment="INTEGER PRIMARY KEY AUTO_INCREMENT";
	public static String date="date";
        public static String datetime="datetime";
	public static String text="text";
	 	
	 
        
	public static String usuario="usuario";
	public static colunaT usuario_nome=new colunaT("cadastro_nome", 
			usuario,varchar,true,"Nome ");
        public static colunaT usuario_senha=new colunaT("usuario_senha", 
			usuario,varchar,true,"Senha ");
        public static colunaT usuario_email=new colunaT("usuario_email", 
			usuario,getvarchar(250),true,"CPF ");
        public static colunaT usuario_sexo=new colunaT("usuario_sexo", 
			usuario,getvarchar(25),true,"Sexo ");
	 public static colunaT usuario_musica=new colunaT("usuario_musica", 
			usuario,getvarchar(10),true,"Musica ");
          public static colunaT usuario_esporte=new colunaT("usuario_esporte", 
			usuario,getvarchar(10),true,"Esporte ");
          public static colunaT usuario_games=new colunaT("usuario_games", 
			usuario,getvarchar(10),true,"Games ");
               public static colunaT usuario_religioso=new colunaT("usuario_religioso", 
			usuario,getvarchar(10),true,"Religioso ");
                public static colunaT usuario_idade=new colunaT("usuario_idade", 
			usuario,getvarchar(10),true,"Idade ");       
  public static colunaT usuario_foto=new colunaT("usuario_foto", 
			usuario,blob,true,"Idade "); 
	
}
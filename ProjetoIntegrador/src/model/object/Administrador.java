package model.object;

public class Administrador{
	private String login;
	private String senha;
	
	//Contrutor
	public Administrador(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	public Administrador() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Propriedades
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}	 
}
 

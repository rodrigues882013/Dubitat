package model.object;

import model.db.UsuarioBD;

public abstract class Usuario {
 
	private String nome;
	private String login;
	private String senha;
	private String matricula;
	private String email;
	 
	
	public Usuario(String nome, String login, String senha, String matricula, String email) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.matricula = matricula;
		this.email = email;
	}

	public Usuario(){
		super();
	}
	
	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



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



	public String getMatricula() {
		return matricula;
	}



	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	/*public Usuario validarLogin(Usuario U){
		return UsuarioBD.validaLogin(U.login, U.senha);
	}*/
	
	public boolean cadastrarUsuario() {
		return false;
	}
	 
}
 

package model.object;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Monitor{
	private String nome;
	private String login;
	private String senha;
	private String matricula;
	private String email;
	private String status;
	private Date dataCadastro;
	private Map<String, Disciplina> disciplina = new HashMap<String, Disciplina>();
	private Map<String,Pergunta> pergunta;

	//Métodos Contrutores
	public Monitor(String nome, String senha, String matricula, String email, Disciplina disciplina) {
		this.nome = nome ;
		this.senha = senha; 
		this.matricula = matricula; 
		this.email = email;
		this.status = "INATIVO";
		this.disciplina.put(disciplina.getCodigo(), disciplina);
		this.pergunta = null;
		this.dataCadastro = new Date();

	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Monitor(){
		super();
	}

	//Demais metodos
	public String getStatus(){
		return this.status;
	}
	public void setStatus(){
		this.status = "ATIVO";
	}
	public void setStatus(String status){
		this.status = status;
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
	public Disciplina getDisciplina(String disciplina){
		return this.disciplina.get(disciplina);
	}
	
	/*public Map<String, Disciplina> getDisciplina() {
		return disciplina;
	}*/
	
	public Set<String> getDisciplina(){
		return this.disciplina.keySet();
		
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina.put(disciplina.getCodigo(), disciplina);
	}
	public void setPergunta(String ticket, Pergunta pergunta) {
		this.pergunta.put(ticket, pergunta);
	}
	public void setPergunta(Pergunta P){
		pergunta.put(P.getTicket(), P);
	}
	public Map<String, Pergunta> getPergunta(){
		return pergunta;
	}
	public void removeDisciplina(Disciplina disciplina){
		this.disciplina.remove(disciplina);
	}
	public void removePergunta(Pergunta pergunta){
		this.disciplina.remove(pergunta.getTicket());
	}

}




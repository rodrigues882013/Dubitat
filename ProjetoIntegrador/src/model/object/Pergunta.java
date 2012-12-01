package model.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Pergunta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<String> aux = new ArrayList<String>();
	private String ticket;
	private String nomeAluno;
	private String matriculaAluno;
	private String emailAluno;
	//private String assunto;
	private String prioridade;
	private String mensagem;
	private Monitor monitor;
	private Disciplina disciplina;
	private Resposta resposta; //Uma pergunta pode ter mais de uma resposta (Réplica)
	private Date dataHora;
	
	public Pergunta(String nomeAluno, String matriculaAluno, String emailAluno, String prioridade, String mensagem, Disciplina disciplina) {
		super();
		this.ticket = this.geraTicket();
		this.nomeAluno = nomeAluno;
		this.matriculaAluno = matriculaAluno;
		this.emailAluno = emailAluno;
		this.prioridade = prioridade;
		this.mensagem = mensagem;
		this.disciplina = disciplina;
		this.dataHora = new Date();
		
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Pergunta(){
		super();
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public String getMatriculaAluno() {
		return matriculaAluno;
	}

	public void setMatriculAluno(String matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}

	public String getEmailAluno() {
		return emailAluno;
	}

	public void setEmailAluno(String emailAluno) {
		this.emailAluno = emailAluno;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
	
	public String geraTicket(){
		boolean b = true;
		String[] carct ={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; 

		String senha=""; 


		while(b){
			for (int x=0; x<7; x++){ 
				int j = (int) (Math.random()*carct.length); 
				senha += carct[j]; 

			} 

			if(!aux.contains(senha)){
				aux.add(senha);
				b = false;
			}
		}
		return senha; 
	} 

	//Libera tickets sempre que perguntas forem eliminadas do Banco
	public boolean liberaTicket(String ticket){
		return aux.remove(ticket);
	}
	
	public static boolean removePergunta(String ticket){
		if(aux.remove(ticket)){
			return true;
		}
		else{
			return false;
		}
	}
	
	
} 
	
	
	
	 


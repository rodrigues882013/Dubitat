package model.object;

import java.util.ArrayList;


public class Disciplina {
 
	private String nome;
	private String codigo;
	private static ArrayList<Pergunta> perguntas;
	
	
	public Disciplina(String codigo, String nome) {
		super();
		this.nome = nome;
		this.codigo = codigo;
		perguntas =  new ArrayList<Pergunta>();
	}
	
	public Disciplina() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public void setPergunta(Pergunta P){
		perguntas.add(P);
	}
	public ArrayList<Pergunta> getPergunta(){
		return perguntas;
	}
	
	public static boolean removePergunta(Pergunta P){
		if(perguntas.remove(P)){
			return true;
		}
		else{
			return false;
		}
	}
	
	 
}
 

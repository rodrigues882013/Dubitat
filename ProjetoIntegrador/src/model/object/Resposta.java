package model.object;

import java.util.Date;

public class Resposta {
 
	private String mensagem;
	private Date data;
	
	
	public Resposta() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Resposta(String mensagem) {
		super();
		this.mensagem = mensagem;
		this.data = new Date();
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	 
	 
}
 

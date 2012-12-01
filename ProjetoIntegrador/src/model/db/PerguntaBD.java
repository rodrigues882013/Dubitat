package model.db;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import model.object.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class PerguntaBD {
	//Cadastra uma nova pergunta
	public static synchronized boolean addPergunta(Pergunta P){
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			//Convertendo para String para posterioremente inserir no banco
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(P.getDataHora());
			//Date d1 = new Date();
			//java.sql.Date d2 = new java.sql.Date(P.getDataHora().getTime());
			
			//Preparando as querys
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("INSERT INTO log_pergunta (ticket, nome_aluno, matricula_aluno, email_aluno, prioridade, mensagem, hora_Pergunta, disciplina, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, P.getTicket());
			ps.setString(2, P.getNomeAluno());
			ps.setString(3, P.getMatriculaAluno());
			ps.setString(4, P.getEmailAluno());
			ps.setString(5, P.getPrioridade());
			ps.setString(6, P.getMensagem());
			ps.setString(7, currentTime);
			ps.setString(8, P.getDisciplina().getCodigo());
			ps.setString(9, "RP");
			ps.executeUpdate();
			
			//Fechando as conexoes
			ps.close();
			c.close();
			conn.fechaBd();


			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	//Retorna todas as perguntas que já foram respondidas em um ArrayList
	public static synchronized ArrayList<Pergunta> historicoPerguntas(){
		ConexaoBD conn;
		ArrayList<Pergunta> H = new ArrayList<Pergunta>();
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			//Prepara a consulta a ser feita
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT id, ticket, mensagem, resposta FROM pergunta WHERE resposta != null");
			ResultSet res =  (ResultSet) ps.executeQuery();
					
			//Adicionando a um arraylist as perguntas respondidas
			while(res.next())
			{
				Pergunta P = new Pergunta();
				P.setTicket(res.getString("ticket"));
				P.setMensagem(res.getString("mensagem"));
				P.setResposta(new Resposta());
				P.getResposta().setMensagem(res.getString("resposta"));
				
				//Adicionando ao arraylist
				H.add(P);
			}
			
			//Fechando as conexoes
			ps.close();
			c.close();
			conn.fechaBd();
			return H;
		}
		catch(Exception e){
			e.printStackTrace();
			H = null;
			return H;
		}
	}
	
	//Retorna uma pergunta em especifico - Deve ser usada para buscar com o ticket
	public static synchronized Pergunta retornaPergunta(String ticket, String matriculaAluno){
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			//Preparando as querys
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT*FROM pergunta WHERE (ticket=? AND matriculaAluno=?)");
			ps.setString(1, ticket);
			ps.setString(2, matriculaAluno);
			
			ResultSet res = ps.executeQuery();
			
			if(res.getString("resposta") != null){
				ps = (PreparedStatement) c.prepareStatement("SELECT matricula FROM monitor WHERE (id=?)");
				ps.setString(1, res.getString("id"));
				ResultSet matriculaMonitor = ps.executeQuery();
				
				ps = (PreparedStatement) c.prepareStatement("SELECT nome FROM usuario WHERE (matricula=?)");
				ps.setString(1, res.getString(matriculaMonitor.getString("matricula")));
				ResultSet  nomeMonitor = ps.executeQuery();
				
				//ps = (PreparedStatement) c.preparedStatement(SELECT nome FROM usuario WHERE matricula = (SELECT matricula FROM monitor WHERE (id=?)))
				//ps.setString(1, res.getString("id"));
				//ResultSet  nomeMonitor = ps.executeQuery();
				
				Pergunta P = new Pergunta();
				P.setTicket(res.getString("ticket"));
				P.setNomeAluno(res.getNString("nomeAluno"));
				P.setMatriculAluno(res.getNString("matriculaAluno"));
				P.setEmailAluno(res.getString("emailAluno"));
				P.setPrioridade(res.getString("prioridade"));
				P.setMensagem(res.getString("mensagem"));
				P.setResposta(new Resposta());
				P.getResposta().setMensagem(res.getString("resposta"));
				P.setMonitor(new Monitor());
				P.getMonitor().setNome(res.getString(nomeMonitor.getString("nome")));

				return P;
			}
			else{
				throw new Exception("Pergunta ainda não respondida");
			}
			//Parei de implementar aqui.........
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static synchronized ArrayList<Pergunta> retornaPerguntaPendenteDeResposta(String disciplina){
		ConexaoBD conn;
		ArrayList<Pergunta> p = new ArrayList<Pergunta>();
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();

			//Preparando as querys
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT*FROM pergunta WHERE (disciplina=? AND resposta is null AND status=?)");
			ps.setString(1, disciplina);
			ps.setString(2, "AP");


			ResultSet res = (ResultSet)ps.executeQuery();

			res.next();
			do{

				Pergunta P = new Pergunta();
				P.setTicket((String)res.getString("ticket"));
				P.setMensagem((String)res.getString("mensagem"));
				P.setPrioridade((String)res.getString("prioridade"));
				P.setDataHora((Date)res.getDate("hora_pergunta"));
				String codigo = (String) res.getString("disciplina");
				Disciplina D = new Disciplina(codigo, DisciplinaBD.listaDisciplinaPorNome(codigo));
				P.setDisciplina(D);
				
				p.add(P);

			}while(res.next());
			return p;


		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//Deleta Uma pergunta do Banco
	public static synchronized boolean deletaPergunta(String ticket){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("DELETE FROM pergunta WHERE ticket=?");
			ps.setString(1, ticket);
			ps.executeUpdate();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public static synchronized ArrayList<Pergunta> historicoPerguntasPendentes(String disciplina){
		ConexaoBD conn;
		ArrayList<Pergunta> H = new ArrayList<Pergunta>();
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			//Prepara a consulta a ser feita
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT codigo WHERE nomeDisciplina=?)");
			ps.setString(1, disciplina);
			ResultSet res =  (ResultSet) ps.executeQuery();
			
			//Prepara a consulta a ser feita
			ps = (PreparedStatement) c.prepareStatement("SELECT ticket, mensagem, prioridade, data FROM pergunta WHERE (resposta = null) and (disciplina=?");
			ps.setString(1, res.getString("codigo"));
			res =  (ResultSet) ps.executeQuery();
			
					
			//Adicionando a um arraylist as perguntas respondidas
			while(res.next())
			{
				Pergunta P = new Pergunta();
				P.setTicket(res.getString("ticket"));
				P.setMensagem(res.getString("mensagem"));
				P.setPrioridade(res.getString("prioridade"));
				P.setDataHora(res.getDate("data"));
				
				//Adicionando ao arraylist
				H.add(P);
			}
			
			//Fechando as conexoes
			ps.close();
			c.close();
			conn.fechaBd();
			return H;
		}
		catch(Exception e){
			e.printStackTrace();
			H = null;
			return H;
		}
	}

	public static synchronized ArrayList<Pergunta> historicoPerguntasPendentes(){
		ConexaoBD conn;
		ArrayList<Pergunta> H = new ArrayList<Pergunta>();
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			
			//Prepara a consulta a ser feita
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM pergunta WHERE (resposta is null)");
			ResultSet res =  (ResultSet) ps.executeQuery();
			
					
			//Adicionando a um arraylist as perguntas respondidas
			while(res.next())
			{
				Pergunta P = new Pergunta();
				P.setTicket((String)res.getString("ticket"));
				P.setMensagem((String)res.getString("mensagem"));
				P.setPrioridade((String)res.getString("prioridade"));
				P.setDataHora((Date)res.getDate("hora_pergunta"));
				String codigo = (String) res.getString("disciplina");
				Disciplina D = new Disciplina(codigo, DisciplinaBD.listaDisciplinaPorNome(codigo));
				P.setDisciplina(D);
				
				//Adicionando ao arraylist
				H.add(P);
			}
			
			//Fechando as conexoes
			ps.close();
			c.close();
			conn.fechaBd();
			res.close();
			
			return H;
		}
		catch(Exception e){
			e.printStackTrace();
			H = null;
			return H;
		}
	}

	public static synchronized boolean updatePergunta(Pergunta P){
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			
			//Preparando as querys
			
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE pergunta SET prioridade=?, mensagem=?, disciplina=? WHERE ticket=?");
			ps.setString(1, P.getPrioridade());
			ps.setString(2, P.getMensagem());
			ps.setString(3, P.getDisciplina().getCodigo());
			ps.setString(4, P.getTicket());
			
			ps.executeUpdate();
			
			//Fechando as conexoes
			ps.close();
			c.close();
			conn.fechaBd();


			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public static synchronized Pergunta retornaPergunta(String ticket){
		ConexaoBD conn;

		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();

			//Preparando as querys
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT*FROM pergunta WHERE ticket=?");
			ps.setString(1, ticket);


			ResultSet res = (ResultSet) ps.executeQuery();
			res.next();

			Pergunta P = new Pergunta();
			P.setTicket(res.getString("ticket"));
			P.setMensagem(res.getString("mensagem"));
			P.setPrioridade(res.getString("prioridade"));
			P.setDataHora(res.getDate("hora_pergunta"));
			
			ps = (PreparedStatement) c.prepareStatement("SELECT*FROM resposta WHERE idresposta=?");
			ps.setInt(1, res.getInt("resposta"));
			
			ResultSet res2 = (ResultSet) ps.executeQuery();
			res2.next();
			Resposta R = new Resposta();
			R.setMensagem(res2.getString("texto"));
			
			P.setResposta(R);

			return P;

			//Parei de implementar aqui.........

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static synchronized boolean responderPergunta(Resposta R, String ticket, String matricula){
		ConexaoBD conn;

		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();


			if(RespostaBD.addResposta(R)){
				//Preparando as querys

				Integer id = RespostaBD.selectById(R.getMensagem());
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE pergunta SET monitor=?, resposta=? WHERE ticket=?");
				ps.setString(1, matricula);
				ps.setInt(2, id);
				ps.setString(3, ticket);
				ps.executeUpdate();

				//Fechando as conexoes
				ps.close();
				c.close();
				conn.fechaBd();


				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	public static synchronized Pergunta retornaPerguntaRespondida(String ticket){
		ConexaoBD conn;

		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();

			//Preparando as querys
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT*FROM pergunta WHERE ticket=?)");
			ps.setString(1, ticket);


			ResultSet res = ps.executeQuery();
			
			Integer id = (int)res.getInt("resposta");
			
			if(id != null){

				Resposta R = RespostaBD.retornaReposta(id);
				Pergunta P = new Pergunta();
				P.setTicket(res.getString("ticket"));
				P.setMensagem(res.getString("mensagem"));
				P.setPrioridade(res.getString("prioridade"));
				P.setDataHora(res.getDate("data"));
				P.setResposta(R);
				String nome = MonitorBD.monitorNome((String)res.getString("monitor"));
				Monitor M = new Monitor();
				M.setNome(nome);
				M.setMatricula((String)res.getString("monitor"));
				P.setMonitor(M);
				
				//Fechando as conexoes
				ps.close();
				c.close();
				conn.fechaBd();

				
				return P;
			}
			else{
				//Fechando as conexoes
				ps.close();
				c.close();
				conn.fechaBd();

				
				return null;
			}

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static synchronized ArrayList<Pergunta> retornaPerguntaPendenteDeAprovacao(String disciplina){
		ConexaoBD conn;
		ArrayList<Pergunta> p = new ArrayList<Pergunta>();
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();

			//Preparando as querys
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT*FROM pergunta WHERE (disciplina=? AND resposta is null AND status=?)");
			ps.setString(1, disciplina);
			ps.setString(2, "RP");


			ResultSet res = (ResultSet)ps.executeQuery();

			res.next();
			do{

				Pergunta P = new Pergunta();
				P.setTicket((String)res.getString("ticket"));
				P.setMatriculAluno(((String)res.getString("matricula_aluno")));
				P.setNomeAluno(((String)res.getString("nome_aluno")));
				P.setMensagem((String)res.getString("mensagem"));
				P.setPrioridade((String)res.getString("prioridade"));
				P.setDataHora((Date)res.getDate("hora_pergunta"));
				String codigo = (String) res.getString("disciplina");
				P.setPrioridade((String)res.getString("prioridade"));
				Disciplina D = new Disciplina(codigo, DisciplinaBD.listaDisciplinaPorNome(codigo));
				P.setDisciplina(D);
				
				p.add(P);

			}while(res.next());
			
			
			ps.close();
			c.close();
			conn.fechaBd();
			
			return p;


		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static synchronized Pergunta listaPergunta(String ticket){
		ConexaoBD conn;
		Pergunta P = null;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();

			//Preparando as querys
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT*FROM pergunta WHERE ticket=?");
			ps.setString(1, ticket);


			ResultSet res = (ResultSet)ps.executeQuery();
			if(res != null){
				res.next();
				P = new Pergunta();
				P.setTicket((String)res.getString("ticket"));
				P.setMatriculAluno(((String)res.getString("matricula_aluno")));
				P.setNomeAluno(((String)res.getString("nome_aluno")));
				P.setMensagem((String)res.getString("mensagem"));
				P.setPrioridade((String)res.getString("prioridade"));
				P.setDataHora((Date)res.getDate("hora_pergunta"));
				String codigo = (String) res.getString("disciplina");
				P.setPrioridade((String)res.getString("prioridade"));
				Disciplina D = new Disciplina(codigo, DisciplinaBD.listaDisciplinaPorNome(codigo));
				P.setDisciplina(D);
			}
			ps.close();
			c.close();
			conn.fechaBd();
			
			return P;
		}
		catch(Exception e){
			e.printStackTrace();
			return P;
		}
	}

	public static synchronized boolean liberarPergunta(String ticket){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement)c.prepareStatement("UPDATE pergunta SET status=? WHERE ticket=?");
			ps.setString(1, "AP");
			ps.setString(2, ticket);
			
			ps.executeUpdate();
			
			ps.close();
			c.close();
			conn.fechaBd();
			
			return true;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public static synchronized ArrayList<Pergunta> historicoPergunta(String cod){
		ConexaoBD conn;
		ArrayList<Pergunta> H = new ArrayList<Pergunta>();
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();

			//Prepara a consulta a ser feita
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM pergunta WHERE disciplina=? AND resposta is not null");
			ps.setString(1, cod);
			
			ResultSet res =  (ResultSet) ps.executeQuery();
			int i = 0;
			//Adicionando a um arraylist as perguntas respondidas
			while((res.next()))
			{
				
					Pergunta P = new Pergunta();
					P.setMensagem((String)res.getString("mensagem"));
					P.setDataHora((Date)res.getDate("hora_pergunta"));	
					
					ps = (PreparedStatement) c.prepareStatement("SELECT*FROM resposta WHERE idresposta=?");
					ps.setInt(1, res.getInt("resposta"));
					
					ResultSet res2 = (ResultSet) ps.executeQuery();
					res2.next();
					Resposta R = new Resposta();
					R.setMensagem(res2.getString("texto"));
					P.setResposta(R);

					//Adicionando ao arraylist
					H.add(P);
					i++;
				
			}

			//Fechando as conexoes
			ps.close();
			c.close();
			conn.fechaBd();
			return H;
		}
		catch(Exception e){
			e.printStackTrace();
			H = null;
			return H;
		}
	}
	
	public static synchronized ArrayList<Pergunta> historicoPerguntaPorPalavraChave(String key){
		ConexaoBD conn;
		ArrayList<Pergunta> H = new ArrayList<Pergunta>();
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();

			//Prepara a consulta a ser feita
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT id, ticket, mensagem, resposta FROM pergunta WHERE mensagem like ? AND resposta is not null");
			key = "'%" + key + "%'";
			ps.setString(1, key);
			
			ResultSet res =  (ResultSet) ps.executeQuery();

			int i = 0;
			//Adicionando a um arraylist as perguntas respondidas, no máximo 10 perguntas
			while(res.next() || i < 10)
			{
				
					Pergunta P = new Pergunta();
					P.setMensagem((String)res.getString("mensagem"));
					P.setDataHora((Date)res.getDate("hora_pergunta"));	
					
					ps = (PreparedStatement) c.prepareStatement("SELECT*FROM resposta WHERE idresposta=?");
					ps.setInt(1, res.getInt("resposta"));
					
					ResultSet res2 = (ResultSet) ps.executeQuery();
					res2.next();
					Resposta R = new Resposta();
					R.setMensagem(res2.getString("texto"));
					P.setResposta(R);

					//Adicionando ao arraylist
					H.add(P);
					i++;
				
			}

			//Fechando as conexoes
			ps.close();
			c.close();
			conn.fechaBd();
			return H;
		}
		catch(Exception e){
			e.printStackTrace();
			H = null;
			return H;
		}
	}

	public static synchronized ArrayList<Pergunta> retornaPerguntaPendenteDeLiberacao(){
		ConexaoBD conn;
		ArrayList<Pergunta> p = new ArrayList<Pergunta>();
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();

			//Preparando as querys
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT*FROM log_pergunta");
			ResultSet res = (ResultSet)ps.executeQuery();

			res.next();
			do{

				Pergunta P = new Pergunta();
				P.setTicket((String)res.getString("ticket"));
				P.setMatriculAluno(((String)res.getString("matricula_aluno")));
				P.setNomeAluno(((String)res.getString("nome_aluno")));
				P.setMensagem((String)res.getString("mensagem"));
				P.setPrioridade((String)res.getString("prioridade"));
				P.setDataHora((Date)res.getDate("hora_pergunta"));
				String codigo = (String) res.getString("disciplina");
				P.setPrioridade((String)res.getString("prioridade"));
				Disciplina D = new Disciplina(codigo, DisciplinaBD.listaDisciplinaPorNome(codigo));
				P.setDisciplina(D);
				
				p.add(P);

			}while(res.next());
			
			
			ps.close();
			c.close();
			conn.fechaBd();
			
			return p;


		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	
}


package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.object.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class MonitorBD {
	//Cadastra um novo monitor em uma disciplina
	public static synchronized boolean addMonitor(Monitor m, String cod) throws SQLException{
		ConexaoBD conn;
		Connection c = null;
		
		try{
			//Começando as conexões
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			c = conn.getConexao();
			c.setAutoCommit(false); 
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("INSERT INTO monitor (nome_monitor, status, senha, matricula, data_cadastro, email) VALUES " + "(?,?,?,?,?,?)"); 
			
			//Editando os parametros das Strings
			ps.setString(1, m.getNome());
			ps.setString(2, m.getStatus());
			ps.setString(3, m.getSenha());
			ps.setString(4, m.getMatricula());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(m.getDataCadastro());
			ps.setString(5, currentTime);
			ps.setString(6, m.getEmail());
			ps.executeUpdate();
			
			ps = (PreparedStatement) c.prepareStatement("INSERT INTO log_monitora (monitor_matricula, cod_disciplina, data_filiacao) VALUES " + "(?, ?, ?)");
			ps.setString(1, m.getMatricula());
			ps.setString(2, cod);
			ps.setString(3, currentTime);
			ps.executeUpdate();
			
			c.commit();
			ps.close();
			c.close();
			conn.fechaBd();
			
			return true;
		}
		catch (SQLException e) {
			if (c != null) {
				c.rollback();
				System.out.println("Connection rollback...");
			}
			return false;
		}
	}
	
	//Deleta um Monitor
	public static synchronized boolean deleteMonitor(String matricula){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			//Primeiro deleta-se da tabela monitor
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("DELETE FROM monitora WHERE monitor_matricula=?");
			ps.setString(1, matricula);
			ps.executeUpdate();
			
			ps = (PreparedStatement) c.prepareStatement("DELETE FROM log_monitora WHERE monitor_matricula=?");
			ps.setString(1, matricula);
			ps.executeUpdate();
			
			ps = (PreparedStatement) c.prepareStatement("DELETE FROM monitor WHERE matricula=?");
			ps.setString(1, matricula);
			ps.executeUpdate();
			
			//Deletando da tabela usuario
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}
	
	//Atualiza um monitor
	public static synchronized boolean updateMonitor(Monitor M, String cod){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE monitor SET matricula=?, senha=?, nome_monitor=?, email=? WHERE matricula=?"); 
			ps.setString(1, M.getMatricula());
			ps.setString(2, M.getSenha());
			ps.setString(3, M.getNome());
			ps.setString(4, M.getEmail());
			ps.setString(5, cod);
			ps.executeUpdate();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	//Valida o login de um monitor 
	public static synchronized Monitor validarLogin(String login, String senha) {
		Monitor M;
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection con = conn.getConexao();
			
			M = new Monitor();
			//Prepara o Banco para Receber a Query
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM monitor WHERE matricula =? AND senha =? AND status=?"); 
			//Adiciona os parametros às interrogaçoes na query, prefira assim pois é mais seguro
			ps.setString(1,login);
			ps.setString(2,senha);
			ps.setString(3, "ATIVO");
			
			//Executa a query
			ResultSet res = (ResultSet) ps.executeQuery();
			res.next();

			
			String loginBd = res.getString("matricula");
			String senhaBd = res.getString("senha");
			
			if((loginBd.compareTo(login) == 0) && (senhaBd.compareTo(senha) == 0)){
				M.setNome(res.getString("nome_monitor"));
				M.setMatricula(res.getString("matricula"));
				M.setSenha(res.getString("senha"));
				M.setEmail(res.getString("email"));
				
				ps = (PreparedStatement) con.prepareStatement("SELECT cod_disciplina FROM monitora WHERE monitor_matricula=?");
				ps.setString(1, res.getString("matricula"));
				
				ResultSet res2 = (ResultSet)ps.executeQuery();
				
				res2.next();
				do{
					Disciplina D = new Disciplina();
					D.setCodigo(res2.getString("cod_disciplina"));
					M.setDisciplina(D);
					System.out.println(M.getDisciplina(D.getCodigo()).getCodigo());
				}while(res2.next());
				
				
				
				//Fecha as conexoes
				ps.close();
				con.close();
				conn.fechaBd();	
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			M = null;
		}
		
		return M;
	}

	//Retorna um ArrayList com todos os monitores ativos no sistema
	public static synchronized ArrayList<Monitor> listaMonitorAtivo(){
		ArrayList<Monitor> M = new ArrayList<Monitor>();
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection con = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT*FROM monitor WHERE status =?");
			ps.setString(1, "ATIVO");
			
			ResultSet res = (ResultSet) ps.executeQuery();
			while(res.next()){
				Monitor m = new Monitor();
				m.setLogin(res.getString("login"));
				m.setEmail(res.getString("email"));
				ps = (PreparedStatement) con.prepareStatement("SELECT nomeDisciplina, nomeMonitor FROM monitora, disciplina, monitor WHERE (monitora.disciplina = disciplina.codigo) AND (monitora.monitor = monitor.ID)");
				ResultSet res2 = (ResultSet) ps.executeQuery();
				Disciplina d = new Disciplina();
				d.setNome(res2.getString("nomeDisciplina"));
				m.setDisciplina(d);
				m.setMatricula(res.getString("matricula"));
				M.add(m);
			}
			
			ps.close();
			con.close();
			conn.fechaBd();	
			return M;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//Retorna o nome do Monitor dado sua matricula
	public static synchronized String monitorNome(String matricula){
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection con = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT*FROM monitor WHERE matricula =?");
			ps.setString(1, matricula);
			
			ResultSet res = (ResultSet) ps.executeQuery();
			
			res.next();
			
			String nome = (String) res.getString("nome_monitor");
			
			ps.close();
			con.close();
			conn.fechaBd();	
			
			return nome;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	//Retorna um ArrayList de monitores inativos
	public static synchronized ArrayList<Monitor> listaMonitorInativos(){
		ArrayList<Monitor> M = new ArrayList<Monitor>();
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection con = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT*FROM monitor WHERE status =?");
			ps.setString(1, "INATIVO");
			
			ResultSet res = (ResultSet) ps.executeQuery();
			while(res.next()){
				Monitor m = new Monitor();
				m.setLogin(res.getString("login"));
				m.setEmail(res.getString("email"));
				ps = (PreparedStatement) con.prepareStatement("SELECT nomeDisciplina, nomeMonitor FROM monitora, disciplina, monitor WHERE (monitora.disciplina = disciplina.codigo) AND (monitora.monitor = monitor.ID)");
				ResultSet res2 = (ResultSet) ps.executeQuery();
				Disciplina d = new Disciplina();
				d.setNome(res2.getString("nomeDisciplina"));
				m.setDisciplina(d);
				m.setMatricula(res.getString("matricula"));
				M.add(m);
			}
			
			ps.close();
			con.close();
			conn.fechaBd();	
			return M;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	//Ativa ou Inativa um monitor
	public static synchronized boolean alterarStatus(String status, String matricula){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE monitor SET status=? WHERE matricula=?"); 
			ps.setString(1, status);
			ps.setString(2, matricula);
			
			ps.executeUpdate();
			
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	//Grava na tabela monitora o efetivo cadastro do monitor
	public static synchronized boolean ativarMonitoria(String matricula, String cod){
		ConexaoBD conn;
		
		try{
			//Começando as conexões
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("INSERT INTO monitora (monitor_matricula, cod_disciplina) VALUES " + "(?, ?)");
			ps.setString(1, matricula);
			ps.setString(2, cod);
			ps.executeUpdate();
			
			ps = (PreparedStatement) c.prepareStatement("DELETE FROM log_monitora WHERE monitor_matricula=? AND cod_disciplina=?");
			ps.setString(1, matricula);
			ps.setString(2, cod);
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
	
	//Metodo adiciona uma disciplina a um monitor já cadastrado
	public static synchronized boolean addDisciplina(String matricula, String disciplina){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("INSERT INTO log_monitora (monitor_matricula, cod_disciplina) VALUES " + "(?, ?)");
			ps.setString(1, matricula);
			ps.setString(2, disciplina);
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

	
	public static synchronized ArrayList<Monitor> listaMonitor(){
		ArrayList<Monitor> M = new ArrayList<Monitor>();
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection con = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT*FROM monitor");
			
			ResultSet res = (ResultSet) ps.executeQuery();
			while(res.next()){
				Monitor m = new Monitor();
				m.setNome(res.getString("nome_monitor"));
				m.setEmail(res.getString("email"));
				m.setMatricula(res.getString("matricula"));
				m.setStatus(res.getString("status"));
				m.setDataCadastro(res.getDate("data_cadastro"));
				M.add(m);
			}
			
			ps.close();
			con.close();
			conn.fechaBd();	
			return M;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//Checa a matricula de um monitor é valida
	public static synchronized String validarMatricula(String matricula){
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection con = conn.getConexao();
			
			
			//Prepara o Banco para Receber a Query
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM monitor WHERE matricula =?"); 
			//Adiciona os parametros às interrogaçoes na query, prefira assim pois é mais seguro
			ps.setString(1, matricula);
			
			
			//Executa a query
			ResultSet res = (ResultSet) ps.executeQuery();
			res.next();

			
			String loginBd = res.getString("matricula");
			
			
			if(loginBd.compareTo(matricula) == 0){
				
				//Fecha as conexoes
				ps.close();
				con.close();
				conn.fechaBd();	
				
				
				return loginBd;
			}
			else{
				
				//Fecha as conexoes
				ps.close();
				con.close();
				conn.fechaBd();	
				
				return null;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	//Retorna um monitor dado sua matricula
	public static synchronized Monitor listaMonitorPorMatricula(String matricula){
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection con = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT*FROM monitor WHERE matricula=?");
			ps.setString(1, matricula);
			
			
			ResultSet res = (ResultSet) ps.executeQuery();
			res.next();
			
			Monitor m = new Monitor();
			m.setNome(res.getString("nome_monitor"));
			m.setEmail(res.getString("email"));
			m.setMatricula(res.getString("matricula"));
			m.setStatus(res.getString("status"));
			if(m.getStatus().compareTo("ATIVO") == 0 && !m.getDisciplina().isEmpty()){
				ps = (PreparedStatement) con.prepareStatement("SELECT nomeDisciplina, nomeMonitor FROM monitora, disciplina, monitor WHERE (monitora.disciplina = disciplina.codigo) AND (monitora.monitor = monitor.ID)");
			
				ResultSet res2 = (ResultSet) ps.executeQuery();
				while(res2.next()){
					Disciplina d = new Disciplina();
					d.setNome(res2.getString("nomeDisciplina"));
					m.setDisciplina(d);

				}
			}
			m.setStatus(res.getString("senha"));
			m.setDataCadastro(res.getDate("data_cadastro"));
				
			ps.close();
			con.close();
			conn.fechaBd();	
			return m;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static synchronized ArrayList<Monitor> listaMonitoresMonitora(String disciplina){
		ArrayList<Monitor> M = new ArrayList<Monitor>();
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection con = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT monitor.nome_monitor, disciplina.Nome_Disciplina, log_monitora.monitor_matricula, log_monitora.cod_disciplina FROM log_monitora, disciplina, monitor WHERE log_monitora.cod_disciplina = disciplina.Codigo AND log_monitora.monitor_matricula = monitor.matricula AND log_monitora.cod_disciplina =? AND monitor.status != 'INATIVO'");
			ps.setString(1, disciplina);
			
			ResultSet res = (ResultSet) ps.executeQuery();
			while(res.next()){
				Monitor m = new Monitor();
				m.setNome(res.getString("nome_monitor"));
				m.setMatricula(res.getString("monitor_matricula"));
				Disciplina d = DisciplinaBD.listaDisciplina(res.getString("cod_disciplina"));
				m.setDisciplina(d);
				M.add(m);
			}
			
			ps.close();
			con.close();
			conn.fechaBd();	
			return M;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
	}
}


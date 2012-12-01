package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import model.object.Disciplina;
import model.object.Monitor;
import model.object.Tutor;

public class TutorBD {
	
	//Insere um novo tutor no banco
	public static synchronized boolean addTutor(Tutor m, String cod) throws SQLException{
		ConexaoBD conn;
		Connection c = null;
		
		try{
			//Começando as conexões
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			c = conn.getConexao();
			c.setAutoCommit(false); 
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("INSERT INTO tutor (nome_tutor, status, senha, matricula, data_cadastro, email) VALUES " + "(?,?,?,?,?,?)"); 
			
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
			
			ps = (PreparedStatement) c.prepareStatement("INSERT INTO log_supervisiona (matricula_tutor, cod_disciplina) VALUES " + "(?, ?)");
			ps.setString(1, m.getMatricula());
			ps.setString(2, cod);
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

	//Deleta um tutor do banco
	public static synchronized boolean deleteTutor(String matricula){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			//Primeiro deleta-se da tabela monitor
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("DELETE FROM supervisiona WHERE matricula_tutor=?");
			ps.setString(1, matricula);
			ps.executeUpdate();
			
			ps = (PreparedStatement) c.prepareStatement("DELETE FROM log_supervisiona WHERE matricula_tutor=?");
			ps.setString(1, matricula);
			ps.executeUpdate();
			
			ps = (PreparedStatement) c.prepareStatement("DELETE FROM tutor WHERE matricula=?");
			ps.setString(1, matricula);
			ps.executeUpdate();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}
	
	//Atualiza um tutor
	public static synchronized boolean updateTutor(Tutor T, String cod){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();

			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE tutor SET matricula=?, senha=?, nome_tutor=?, email=? WHERE matricula=?"); 
			ps.setString(1, T.getMatricula());
			ps.setString(2, T.getSenha());
			ps.setString(3, T.getNome());
			ps.setString(4, T.getEmail());
			ps.setString(5, cod);
			ps.executeUpdate();
	

			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	//Consulta os tutores ativos no sistema
	public static synchronized ArrayList<Tutor> consultaTutor(){
		ArrayList<Tutor> T = new ArrayList<Tutor>();
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM tutor WHERE status =? ");
			ps.setString(1, "ATIVO");
			
			ResultSet res = (ResultSet) ps.executeQuery();
			while(res.next()){
				Tutor t = new Tutor();
				t.setLogin(res.getString("login"));
				t.setEmail(res.getString("email"));
				ps = (PreparedStatement) c.prepareStatement("SELECT nomeDisciplina, nomeTutor FROM supervisiona, disciplina, tutor WHERE (supervisiona.disciplina = disciplina.codigo) AND (supervisiona.tutor = tutor.ID)");
				ResultSet res2 = (ResultSet) ps.executeQuery();
				Disciplina d = new Disciplina();
				d.setNome(res2.getString("nomeDisciplina"));
				t.setDisciplina(d);
				t.setMatricula(res.getString("matricula"));
				T.add(t);
			}
			
			return T;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//Valida Login de um Tutor
	public static synchronized Tutor validarLogin(String login, String senha){
		Tutor T = null;
		ConexaoBD conn;

		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection con = conn.getConexao();

			//Verifica se o login e senha constam na tabela usuario
			T = new Tutor();
			//Prepara o Banco para Receber a Query
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM tutor WHERE matricula =? and senha =? and status=?"); 
			//Adiciona os parametros às interrogaçoes na query, prefira assim pois é mais seguro
			ps.setString(1,login);
			ps.setString(2,senha);
			ps.setString(3, "ATIVO");

			//Executa a query
			ResultSet res = (ResultSet) ps.executeQuery();
			//Move o ponteiro pelo banco
			res.next();

			String loginBd = res.getString("matricula");
			String senhaBd = res.getString("senha");

			if((loginBd.compareTo(login) == 0) && (senhaBd.compareTo(senha) == 0)){
				T.setNome(res.getString("nome_tutor"));
				T.setMatricula(res.getString("matricula"));
				T.setSenha(res.getString("senha"));
				T.setEmail(res.getString("email"));

				ps = (PreparedStatement) con.prepareStatement("SELECT cod_disciplina FROM supervisiona WHERE matricula_tutor=?");
				ps.setString(1, res.getString("matricula"));

				ResultSet res2 = (ResultSet)ps.executeQuery();

				res2.next();
				do{
					Disciplina D = new Disciplina();
					D.setCodigo(res2.getString("cod_disciplina"));
					T.setDisciplina(D);
					System.out.println(T.getDisciplina(D.getCodigo()).getCodigo());
				}while(res2.next());



				//Fecha as conexoes
				ps.close();
				con.close();
				conn.fechaBd();	

			}
		}
		catch(Exception e){
			e.printStackTrace();
			T = null;
		}
		
		return T;

	}
	
	public static synchronized ArrayList<Tutor> listaTutor(){
		ArrayList<Tutor> T = new ArrayList<Tutor>();
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection con = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT*FROM tutor");
			
			ResultSet res = (ResultSet) ps.executeQuery();
			while(res.next()){
				Tutor m = new Tutor();
				m.setNome(res.getString("nome_tutor"));
				m.setEmail(res.getString("email"));
				m.setMatricula(res.getString("matricula"));
				m.setStatus(res.getString("status"));
				m.setDataCadastro(res.getDate("data_cadastro"));
				T.add(m);
			}
			
			ps.close();
			con.close();
			conn.fechaBd();	
			return T;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

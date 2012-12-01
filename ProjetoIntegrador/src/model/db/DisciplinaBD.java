package model.db;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.object.Disciplina;

public class DisciplinaBD {
	
	public static synchronized boolean addDisciplina(Disciplina D){
		ConexaoBD conn;
		
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("INSERT INTO disciplina (Nome_Disciplina, codigo) VALUES (?,?)");
			ps.setString(1, D.getNome());
			ps.setString(2, D.getCodigo());
			
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
	
	public static synchronized boolean deleteDisciplina(String cod){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			//Primeiro deleta-se da tabela monitor
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("DELETE FROM disciplina WHERE codigo=?");
			ps.setString(1, cod);
			ps.executeUpdate();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static synchronized boolean updateDisciplina (Disciplina d, String cod){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE disciplina SET (nomeDisciplina=?, codigo=?) WHERE cod=?"); 
			ps.setString(1, d.getNome());
			ps.setString(2, d.getCodigo());
			ps.setString(3, cod);
			ps.executeUpdate();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static synchronized ArrayList<String> listaDisciplina(){
		ConexaoBD conn;
		ArrayList<String> L = new ArrayList<String>();
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * from disciplina;");
			ResultSet res = (ResultSet) ps.executeQuery();
			//res.next();
			
			while(res.next()){
				L.add(res.getString("Nome_Disciplina"));
			}
			ps.close();
			c.close();
			conn.fechaBd();	
			res.close();
			
			return L;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static synchronized String listaDisciplinaPorCodigo(String disciplina){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT Codigo FROM disciplina WHERE Nome_Disciplina=?");
			ps.setString(1, disciplina);
			ResultSet res = (ResultSet) ps.executeQuery();
			String codigo = null;
			while(res.next()){
				codigo = (String) res.getString("Codigo");
			}
			
			ps.close();
			c.close();
			conn.fechaBd();	
			
			
			res.close();
			
			return codigo;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static synchronized String listaDisciplinaPorNome(String codigo){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT Nome_Disciplina FROM disciplina WHERE Codigo=?");
			ps.setString(1, codigo);
			ResultSet res = (ResultSet) ps.executeQuery();
			String nome = null;
			while(res.next()){
				nome = (String) res.getString("Nome_Disciplina");
			}
			
			ps.close();
			c.close();
			conn.fechaBd();	
			
			
			res.close();
			
			return nome;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static synchronized Disciplina listaDisciplina(String codigo){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT Nome_Disciplina FROM disciplina WHERE Codigo=?");
			ps.setString(1, codigo);
			ResultSet res = (ResultSet) ps.executeQuery();
			res.next();
			String nome = (String) res.getString("Nome_Disciplina");
			
			Disciplina D = new Disciplina(codigo, nome);
			
			ps.close();
			c.close();
			conn.fechaBd();	
			
			
			res.close();
			
			return D;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static synchronized Integer numeroDeRegistro(){
		
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT COUNT(*) AS num FROM disciplina");
			ResultSet res = (ResultSet) ps.executeQuery();
			res.next();
			Integer num = (Integer) res.getInt("num");
			
			return num;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static synchronized ArrayList<Disciplina> listaDisciplina2(){
		ConexaoBD conn;
		ArrayList<Disciplina> L = new ArrayList<Disciplina>();
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * from disciplina;");
			ResultSet res = (ResultSet) ps.executeQuery();
			//res.next();
			
			while(res.next()){
				Disciplina d = new Disciplina(res.getString("Codigo"), res.getString("Nome_Disciplina"));
				L.add(d);
			}
			ps.close();
			c.close();
			conn.fechaBd();	
			res.close();
			
			return L;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static synchronized ArrayList<String> listaDisciplinaDiferente(Set<String> codigos){
		ConexaoBD conn;
		ArrayList<String> L = new ArrayList<String>();
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			PreparedStatement ps;
			ResultSet res;
			
			Iterator<String> it = codigos.iterator();
			while(it.hasNext()){
				String cod = (String)it.next();
				ps = (PreparedStatement) c.prepareStatement("SELECT * from disciplina WHERE Codigo !=?");
				ps.setString(1, cod);
				res = (ResultSet) ps.executeQuery();
				//res.next();
			
				if(!codigos.contains(cod)){
				//while(res.next()){
					L.add(res.getString("Nome_Disciplina"));
					
				}
				ps.close();
				res.close();
			}
			for(int i=0; i < L.size(); i++){
				System.out.println(L.get(i));
				
				
			}
			System.out.println("\n");
			c.close();
			conn.fechaBd();	

			
			return L;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}

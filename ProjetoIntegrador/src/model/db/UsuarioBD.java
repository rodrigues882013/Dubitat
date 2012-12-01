package model.db;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.object.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UsuarioBD {
	//Esse método retorna um ResultSet contendo as informações de um usuario 
	public static synchronized ResultSet validaLogin(String login, String senha) {
		ResultSet res = null;
		
		try{
			ConexaoBD conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection con = conn.getConexao();
			//Prepara o Banco para Receber a Query
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * from usuario2 where login =? and senha =?"); 
			//Adiciona os parametros às interrogaçoes na query, prefira assim pois é mais seguro
			ps.setString(1,login);
			ps.setString(2,senha);
			
		
			//Executa a query
			res = (ResultSet) ps.executeQuery();
			//Move o ponteiro pelo banco
			res.next();
			
			//Fecha o PreparedStatement liberando recursos do Banco
			ps.close();
			//Fecha as Conexões para liberar recursos do Banco
			con.close();
			conn.fechaBd();	
			
			return res;
		}
		catch(Exception e){
			e.printStackTrace();
			return res;
		}
	}

	//Cadastrar um usuario no BANCO
	public static synchronized boolean addUsuario(Usuario u){
		ConexaoBD conn;
		
		try{
			//Começando as conexões
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("INSERT INTO usuario (login, senha, matricula, nome) VALUES " + "(?,?,?,?)"); 
			
			//Editando os parametros das Strings
			ps.setString(1, u.getLogin());
			ps.setString(2, u.getSenha());
			ps.setString(3, u.getMatricula());
			ps.setString(4, u.getNome());
			
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

	//Deleta um usuario
	public static synchronized boolean deleteUsuario (String matricula){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("DELETE FROM usuario WHERE matricula=?");
			ps.setString(1, matricula);
			ps.executeUpdate();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	//Alteração de Dados de Usuario
	public static synchronized boolean updateUsuario(Usuario U){
		ConexaoBD conn;
		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE usuario SET (login=?, senha=?, nome=?) WHERE matricula=?"); 
			ps.setString(1, U.getLogin());
			ps.setString(2, U.getSenha());
			ps.setString(3, U.getNome());
			ps.setString(4, U.getMatricula());
			ps.executeUpdate();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	//Retorna todos os usuarios do banco
	public static synchronized ArrayList<Usuario> consultaUsuario(){
		return new ArrayList();
	}
}

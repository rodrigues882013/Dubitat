package model.db;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import model.object.*;


public class AdministradorBD {
	public static synchronized Administrador validarLogin(String login, String senha){
		Administrador S = null;
		ConexaoBD conn;

		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection con = conn.getConexao();

			//Prepara o Banco para Receber a Query
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * from superusuario where login =? and senha =?"); 
			//Adiciona os parametros às interrogaçoes na query, prefira assim pois é mais seguro
			ps.setString(1,login);
			ps.setString(2,senha);

			//Executa a query
			ResultSet res = (ResultSet) ps.executeQuery();
			//Move o ponteiro pelo banco
			res.next();

			String loginBd = res.getString("login");
			String senhaBd = res.getString("senha");

			if((loginBd.compareTo(login) == 0) && (senhaBd.compareTo(senha) == 0)){
				S = new Administrador();
				S.setLogin(res.getString("login"));
				S.setSenha(res.getString("senha"));
			}
			
			//Fecha as conexoes
			ps.close();
			con.close();
			conn.fechaBd();	

			return S;
		}
		catch(Exception e){
			e.printStackTrace();
			return S;
		}
	}

}


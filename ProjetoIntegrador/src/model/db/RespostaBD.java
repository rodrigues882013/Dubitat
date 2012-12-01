package model.db;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import model.object.Resposta;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class RespostaBD {
	public static synchronized boolean addResposta(Resposta R){
		ConexaoBD conn;

		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();

			//Convertendo para String para posterioremente inserir no banco
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(R.getData());
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement ("INSERT INTO resposta (texto, data_resposta) VALUES (?,?)");
			ps.setString(1, R.getMensagem());
			ps.setString(2, currentTime);
			
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

	public static synchronized Integer selectById(String resposta){
		ConexaoBD conn;

		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM resposta WHERE texto=?");
			ps.setString(1, resposta);
			
			ResultSet res = (ResultSet) ps.executeQuery();
			res.next();
			int id = Integer.parseInt((String)res.getString("idresposta"));
			
			//Fechando as conexoes
			ps.close();
			c.close();
			conn.fechaBd();
			
			return id;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static synchronized Resposta retornaReposta(int id){
		ConexaoBD conn;

		try{
			conn = ConexaoBD.getInstancia();
			conn.iniciaBD();
			Connection c = conn.getConexao();
			
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM resposta WHERE id=?");
			ps.setInt(1, id);
			
			ResultSet res = (ResultSet) ps.executeQuery();
			res.next();
			
			Resposta R = new Resposta();
			R.setMensagem((String)res.getString("texto"));
						
			//Fechando as conexoes
			ps.close();
			c.close();
			conn.fechaBd();
			
			return R;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

package model.db;


//Imports
import java.sql.DriverManager;
import com.mysql.jdbc.Connection;

public class ConexaoBD {
	private Connection conn;
	private static ConexaoBD Instancia;
	private String database;
	private String usuario;
	private String senha;
	
	//Contrutor privado
	private ConexaoBD(){
		database = "jdbc:mysql://127.0.0.1:3306/Dubitat";
		usuario = "root";
		senha = "";
	}
	
	//Retorna a Instancia, aqui estou usando padrão Singleton, veja que sempre so existira uma instancia do objeto ConexeaoBD
	public static ConexaoBD getInstancia(){
		if(Instancia == null){
			Instancia = new ConexaoBD();
		}
		return Instancia;
	}
	
	public void iniciaBD(){
		//String database = "jdbc:mysql://127.0.0.1:3306/java";
		//String usuario = "root";
		//String senha = "";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection( database, usuario, senha );
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void fechaBd()
	{	
		try{	
			conn.close();
		}
		catch ( Exception e ) {
         e.printStackTrace();
		}
	}	

	public Connection getConexao()
	{	
		return conn;		
	}
}


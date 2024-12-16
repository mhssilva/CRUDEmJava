package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	//nome do usuario mysql
	private static final String USERNAME = "root";
	
	//senha do banco
	private static final String PASSWORD = "Matheuzxd123";
	
	//caminho do banco
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda?serverTimezone=UTC";
	
	//conexão com o banco de dados
	public static Connection createConnectionToMySQL() throws Exception{
		
		//faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		//recuperar a conexão com banco de dados se tiver
		Connection con = createConnectionToMySQL();
		
		if(con != null) {
			System.out.println("Conexão obetida com sucesso!");
			con.close();
		}
			
			
	}
}

package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static final String USERNAME = "root"; //Nome de user do BD
	private static final String PASSWORD = ""; //Senha do BD
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda"; //Caminho do BD / porta, nome do BD
	
	public static Connection createConnectionToMySQL() throws Exception {
		Class.forName("com.mysql.jdbc.Driver"); //Faz com que a classe seja carregada pela JVM
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); //Cria a conexão com o banco de dados
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		//Recuperar uma conexão com o BD
		Connection conn = createConnectionToMySQL();
		
		if (conn!=null) {
			System.out.println("Conexão obtida com sucesso!"); //Testar se a conexão é nula
			conn.close();
		}
	}
}

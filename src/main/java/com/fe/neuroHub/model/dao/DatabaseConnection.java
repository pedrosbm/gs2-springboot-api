package com.fe.neuroHub.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.datasource.impl.OracleDataSource;

public class DatabaseConnection {
	private static final String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
	public static Connection conn;

	private DatabaseConnection() {
		
	}
	
	/**
	 * Método que instância uma conexão de banco de dados baseado em singleton. Retornará sempre
	 * uma conexão única e com o mesmo endereço de memória.
	 * @return Connection
	 */
	public static Connection getConnection(){
		if(conn == null) {
			try {
			OracleDataSource ods = new OracleDataSource();
			
			ods.setURL(url);
			ods.setUser(Credentials.user);
			ods.setPassword(Credentials.pwd);
			conn = ods.getConnection();
			
			} catch (Exception e) {
				System.err.println("Conexão não estabelecida");
				e.printStackTrace();
			}
		} else {
			System.out.println("Instância já existente / estabelecida");
		}
		
		return conn;
	}
	
	/**
	 * Método que fecha a conexão de banco de dados em singleton da classe
	 * DatabaseConnection.
	 * @return Confirmação da execução do método - String
	 */
	public static String closeConnection() {
		try {
			conn.close();;
		} catch (SQLException e) {
			System.err.println("Conexão já foi fechada");
			e.printStackTrace();
		}
		
		return "Conexão fechada";
	}

}

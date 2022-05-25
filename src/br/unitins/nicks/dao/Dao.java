package br.unitins.nicks.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface Dao {

	public static Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/nicksdb", "topicos2", "123456");
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("O Driver n�o foi encontrado.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Falha na conexao com o banco de dados.");
			e.printStackTrace();
		}
		return null;
	}
}

package br.com.prova.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String STR_DRIVER = "com.mysql.jdbc.Driver";
	// identificacao url do driver do banco de dados

	private static final String USER = "javatech_prova"; // usuario do database
	private static final String PASSWORD = "123iop"; // senha do database

	private static final String IP = "javatechbrasil.com.br";

	private static final String DATABASE = "javatech_prova";

	private static final String STR_CON = "jdbc:mysql://" + IP + ":3306/"
			+ DATABASE+"?noAccessToProcedureBodies=true";

	public static Connection getConexao() throws SQLException {
		Connection conn = null;
		try {
			Class.forName(STR_DRIVER);
			conn = DriverManager.getConnection(STR_CON, USER, PASSWORD);
			System.out.println("Obtendo conexao...");
			return conn;
			// acima obtenho uma conexao passando os parametros do database,
			// usuario e senha

		} catch (ClassNotFoundException e) {
			String errorMsg = "Driver nao encontrado";
			throw new SQLException(errorMsg, e);
			// acima faco uma excessao para o driver do MySQL nao encontrado
		} catch (SQLException e) {
			String errorMsg = "Erro ao obter a conexao";
			throw new SQLException(errorMsg, e);
			// acima tenho uma excessao caso dê outro problema de conexao da
			// aplicacao com o banco como problemas de rede, servico mysql
			// parado, etc
		}
	}

	public static void closeAll(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
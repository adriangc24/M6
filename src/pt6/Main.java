package pt6;

import java.sql.*;

public class Main {
	static Statement stmt;
	static Connection conn;

	public static void main(String[] args) throws SQLException {
		String url = "jdbc:sqlite:forHonor.db";
		String sql = "INSERT INTO forHonor (faccion_id,nombre_faccion,lore) values (1,'pepe','hola')";
		conexion(url);
		//createNewTable("forHonor");
		//insertData(sql);
		sql = "SELECT faccion_id,nombre_faccion,lore FROM forHonor";
		selectAll(sql);
		finalizarConexion();
	}

	public static void insertData(String sql) throws SQLException {
		stmt.executeUpdate(sql);
	}

	public static void createNewTable(String tableName) throws SQLException {

		// SQL statement for creating a new table
		String sql = "CREATE TABLE " + tableName +
				"(faccion_id INT PRIMARY KEY NOT NULL," +
				"nombre_faccion TEXT ," +
				"lore TEXT);";
		stmt.execute(sql);

	}

	public static void conexion(String url) {
		try {
			// create a connection to the database
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();

			System.out.println("Connection to SQLite has been established.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error: statement");
		}
	}

	public static void selectAll(String sql) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);

		// loop through the result set
		while (rs.next()) {
			System.out.println(rs.getInt("faccion_id") + "\n" + rs.getString("nombre_faccion") + "\n" + rs.getString("lore"));
		}

	}

	public static void finalizarConexion() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

}

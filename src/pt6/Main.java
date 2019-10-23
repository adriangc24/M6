package pt6;

import java.sql.*;

public class Main {
	static Statement stmt;
	static Connection conn;
	static String sql;
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:sqlite:forHonor.db";
		conexion(url);

		// EJECUTAR LINEAS COMENTADAS LA PRIMERA VEZ

		//createNewTable("faccion");

		//insertData(new String("INSERT INTO faccion (faccion_id,nombre_faccion,lore) values (1,'caballeros','Los caballeros de Ashfeld son paradigmas del poder. La Legión de Hierro los envió para llevar la paz a esas tierras. Desde entonces disfrutan de la libertad y han hecho de Ashfeld su hogar.')"));
		//insertData(new String("INSERT INTO faccion (faccion_id,nombre_faccion,lore) values (2,'vikingos','Los vikingos desaparecieron hace siglos, tras escapar de sus derruidas tierras natales en busca de costas desconocidas. Los caballeros conquistaron a aquellos que se quedaron atrás y los incorporaron a su cultura.')"));
		//insertData(new String("INSERT INTO faccion (faccion_id,nombre_faccion,lore) values (3,'samurais','La historia no ha sido amable con los samuráis. Originarios de una tierra muy lejana, allende los mares, cuentan la historia de un emperador y una patria que desaparecieron entre el mar y el fuego.')"));
		
		//createNewTable("personaje");
	
		//insertData(new String("INSERT INTO personaje (personaje_id,nombre_personaje,ataque,defensa,faccion_id) values (1,'conquistador',3444,4769,1)"));
		//insertData(new String("INSERT INTO personaje (personaje_id,nombre_personaje,ataque,defensa,faccion_id) values (2,'guardian',2135,6754,1)"));
		//insertData(new String("INSERT INTO personaje (personaje_id,nombre_personaje,ataque,defensa,faccion_id) values (3,'gladiador',9087,4678,1)"));
		//insertData(new String("INSERT INTO personaje (personaje_id,nombre_personaje,ataque,defensa,faccion_id) values (4,'hulda',3456,4598,2)"));
		//insertData(new String("INSERT INTO personaje (personaje_id,nombre_personaje,ataque,defensa,faccion_id) values (5,'celta',4741,6985,2)"));
		//insertData(new String("INSERT INTO personaje (personaje_id,nombre_personaje,ataque,defensa,faccion_id) values (6,'valquiria',2839,9965,2)"));
		//insertData(new String("INSERT INTO personaje (personaje_id,nombre_personaje,ataque,defensa,faccion_id) values (7,'kensei',1278,5633,3)"));
		//insertData(new String("INSERT INTO personaje (personaje_id,nombre_personaje,ataque,defensa,faccion_id) values (8,'shinobi',5463,4455,3)"));
		//insertData(new String("INSERT INTO personaje (personaje_id,nombre_personaje,ataque,defensa,faccion_id) values (9,'orochi',7845,9896,3)"));
		
		sql = "SELECT * from personaje";
		selectAll(sql);
		
		System.out.println("\n-- CABALLEROS");
		mostrarCaballeros();
		
		System.out.println("\n-- SAMURAI CON MAS ATAQUE");
		mostrarSamuraiAtaque();
		
		finalizarConexion();
	}

	public static void mostrarSamuraiAtaque() throws SQLException {
		sql = "select * from personaje where ataque=(select max(ataque) from personaje where faccion_id=(select faccion_id from faccion where nombre_faccion='samurais'))";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(
					rs.getInt("personaje_id")+" "+ rs.getString("nombre_personaje") + " " +rs.getInt("ataque") + " "+rs.getInt("defensa") + " "+rs.getInt("faccion_id") + " ");
		}
	}

	public static void mostrarCaballeros() throws SQLException {
		sql = "SELECT * from personaje where faccion_id=(select faccion_id from faccion where nombre_faccion='caballeros')";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(
					rs.getInt("personaje_id")+" "+ rs.getString("nombre_personaje") + " " +rs.getInt("ataque") + " "+rs.getInt("defensa") + " "+rs.getInt("faccion_id") + " ");
		}
	}

	public static void insertData(String sql) throws SQLException {
		stmt.executeUpdate(sql);
	}

	public static void createNewTable(String tableName) throws SQLException {
		// Crear tabla faccion
		sql = "CREATE TABLE " + tableName + "(faccion_id INT PRIMARY KEY NOT NULL,nombre_faccion TEXT ,lore TEXT)";
		
		// Crear tabla personaje
		/*sql = "CREATE TABLE "+tableName+" (personaje_id INT PRIMARY KEY,nombre_personaje TEXT,ataque INT,defensa INT,faccion_id INT, FOREIGN KEY(faccion_id) REFERENCES faccion(faccion_id))";*/
		
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
			/*System.out.println(
					rs.getInt("faccion_id") + "\n" + rs.getString("nombre_faccion") + "\n" + rs.getString("lore"));*/
			System.out.println(
					rs.getInt("personaje_id")+" "+ rs.getString("nombre_personaje") + " " +rs.getInt("ataque") + " "+rs.getInt("defensa") + " "+rs.getInt("faccion_id") + " ");
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

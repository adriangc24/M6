package pt7;

import java.util.Scanner;

import com.mysql.cj.result.LocalDateTimeValueFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
	static Scanner sc;
	static int telefono;
	static int cp;
	static int edad;
	static int cuota;
	static Connection conn;
	static Statement st;
	static PreparedStatement ps;
	static int id;
	static String cod_soc;
	static int cod;
	static boolean encontrado = false;
	static String sino;

	public static void main(String[] args) throws SQLException {

		try {
			String url = "jdbc:mysql://localhost/videoclub?useTimezone=true&serverTimezone=GMT%2B8";
			String user = "root";
			String password = "";

			conn = DriverManager.getConnection(url, user, password);
			st = conn.createStatement();

			menu();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void imprimirSocios() throws SQLException {
		ResultSet rs = st.executeQuery("select * from socio");
		while (rs.next()) {
			Object o = rs.getObject("cod_soc")+" | "+rs.getObject("nombre")+" | "+rs.getObject("apellidos")+" | "+rs.getObject("cuota");
			System.out.println(o.toString());

		}
	}

	public static void menu() throws SQLException {
		int opcion = 0;
		sc = new Scanner(System.in);
		do {
			System.out.println(
					"BIENVENIDO AL VIDEOCLUB DE ADRIAN GONZALEZ\n1- Dar de alta un socio\n2- Cambiar cuota\n3- Eliminar socio\n4- Salir");
			if (sc.hasNextInt()) {
				opcion = sc.nextInt();
				if (opcion >= 1 && opcion <= 4) {
					switch (opcion) {
					case 1:
						sc = new Scanner(System.in);
						System.out.println("Introduce el nombre");
						String nombre = sc.nextLine();
						System.out.println("Introduce los apellidos");
						String apellidos = sc.nextLine();
						System.out.println("Introduce direccion");
						String direccion = sc.nextLine();
						while (true) {
							System.out.println("Introduce el telefono");
							if (sc.hasNextInt()) {
								telefono = sc.nextInt();
								break;
							} else {
								System.out.println("Numero incorrecto, formato : 123456789");
							}
							sc.nextLine();
						}
						sc = new Scanner(System.in);
						System.out.println("Introduce la poblacion");
						String poblacion = sc.nextLine();
						while (true) {
							System.out.println("Introduce el codigo postal");
							if (sc.hasNextInt()) {
								cp = sc.nextInt();
								break;
							} else {
								System.out.println("Codigo postal incorrecto, formato : 08970");
							}
							sc.nextLine();
						}
						sc = new Scanner(System.in);
						System.out.println("Introduce provincia");
						String provincia = sc.nextLine();
						System.out.println("Introduce pais");
						String pais = sc.nextLine();
						while (true) {
							System.out.println("Introduce la edad");
							if (sc.hasNextInt()) {
								edad = sc.nextInt();
								break;
							} else {
								System.out.println("Edad incorrecta, formato : 54");
							}
							sc.nextLine();

						}
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDateTime now = LocalDateTime.now();
						String fechaActual = dtf.format(now);
						System.out.println(fechaActual);
						sc = new Scanner(System.in);
						while (true) {
							System.out.println("Introduce la cuota");
							if (sc.hasNextInt()) {
								cuota = sc.nextInt();
								break;
							} else {
								System.out.println("Cuota incorrecta, formato : 1234");
							}
							sc.nextLine();

						}
						// Creamos objeto socio
						Socio socio = new Socio(nombre, apellidos, direccion, telefono, poblacion, cp, provincia, pais,
								edad, fechaActual, cuota);
						// Consultamos ultimo id de la BBDD para aumentarlo en 1 (autoIncrement)
						ResultSet rs = st.executeQuery("select * from socio ORDER BY cod_soc DESC LIMIT 1");
						while (rs.next()) {
							Object o = rs.getObject("cod_soc");
							id = Integer.valueOf(o.toString());
							id++;

						}
						// Insercion del socio
						st.executeUpdate(
								"insert into socio (cod_soc,nombre,apellidos,direccion,telefono,poblacion,cp,provincia,pais,edad,fechaalta,cuota) VALUES "
										+ "(" + id + ",'" + socio.getNombre() + "','" + socio.getApellidos() + "','"
										+ socio.getDireccion() + "','" + socio.getTelefono() + "','"
										+ socio.getPoblacion() + "','" + socio.getCp() + "','" + socio.getProvincia()
										+ "','" + socio.getPais() + "'," + socio.getEdad() + "," + "'"
										+ socio.getFechaAlta() + "'" + "," + socio.getCuota() + ")");
						break;
					case 2:
						imprimirSocios();
						encontrado = false;
						while (true) {
							sc = new Scanner(System.in);
							System.out.println("Introduce el codigo de socio a modificar cuota");
							if (sc.hasNextInt()) {
								cod = sc.nextInt();
								rs = st.executeQuery("select * from socio");
								while (rs.next()) {
									Object o = rs.getObject("cod_soc");
									id = Integer.valueOf(o.toString());
									if (id == cod) {
										encontrado = true;
										break;
									}
								}
								if (encontrado) {
									while (true) {
										sc = new Scanner(System.in);
										System.out.println("Introduce la cuota");
										if (sc.hasNextInt()) {
											cuota = sc.nextInt();
											st.executeUpdate(
													"update socio set cuota= " + cuota + " where cod_soc= " + cod);
											break;
										} else {
											System.out.println("Cuota incorrecta, formato : 1234");
										}
									}
									break;
								} else {
									System.out.println("Este codigo de socio no existe");
								}

							} else {
								System.out.println("Introduce numero de socio ex : 9");
							}

						}
						break;
					case 3:
						imprimirSocios();
						encontrado = false;
						while (true) {
							sc = new Scanner(System.in);
							System.out.println("Introduce el numero de socio a eliminar");
							if (sc.hasNextInt()) {
								cod = sc.nextInt();
								rs = st.executeQuery("select * from socio");
								while (rs.next()) {
									Object o = rs.getObject("cod_soc");
									id = Integer.valueOf(o.toString());
									if (id == cod) {
										encontrado = true;
										break;
									}
								}
								if (encontrado) {
									while (true) {
										sc = new Scanner(System.in);
										System.out.println("Seguro que quieres eliminar a: "+rs.getObject("nombre").toString()+" "+rs.getObject("apellidos").toString()+"\nINTRODUCE UNA OPCION: ( S/N )");
										if(sc.hasNextLine()) {
											sino=sc.nextLine();
											if(sino.equalsIgnoreCase("s")) {
												st.executeUpdate("delete from socio where cod_soc="+rs.getObject("cod_soc").toString());
												System.out.println("Socio Borrado");
											}
											else if(sino.equalsIgnoreCase("n")) {
												System.out.println("Socio no eliminado");
											}
										}
										
										break;

									}
									break;
								} else {
									System.out.println("Este codigo de socio no existe");
								}

							} else {
								System.out.println("Introduce numero de socio ex : 9");
							}

						}
					case 4:
						System.out.println("Hasta luego");
						break;
					}
				}
			} else {
				System.out.println("ERROR: Introduce un numero entre 1 y 4");
			}
			if (opcion == 4) {
				break;
			}
		} while (true);
	}

}

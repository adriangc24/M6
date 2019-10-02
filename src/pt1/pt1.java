package pt1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class pt1 {

	static Scanner sc = new Scanner(System.in);
	static String nombre;
	static float precio;
	static TreeMap<String, Float> jocs = new TreeMap<String, Float>();

	public static void main(String[] args) {

		int opcio = 0;
		imprimirMenu();

		// Bucle para menú
		
		if (sc.hasNextInt()) {
		while (true) {
				opcio = sc.nextInt();
				if (opcio >= 1 && opcio <= 5) {
					if (opcio == 5) {
						System.out.println("Hasta luego !");
						break;
					}
					switch (opcio) {
					case 1:
						introducirJuego();
						jocs.put(nombre, precio);
						imprimirMenu();
						opcio = 0;
						break;
					case 2:
						if (!jocs.isEmpty()) {
							modificarJuego();
						} else {
							System.out.println("ERROR: no hay ningun juego");
						}
						opcio = 0;
						break;
					case 3:
						if (!jocs.isEmpty()) {
							borrarJuego();
						} else {
							System.out.println("ERROR: no hay ningun juego para mostrar");
						}
						opcio = 0;
						imprimirMenu();

						break;
					case 4:
						if (!jocs.isEmpty()) {
							mostrarJuegos();
						} else {
							System.out.println("ERROR: no hay ningun juego");
							imprimirMenu();
						}
						opcio = 0;
						imprimirMenu();
						break;
					case 5:
						opcio = 5;
						break;
					}

				} else {
					imprimirMenu();
				}
				if (opcio == 5) {
					break;
				}
			} 
		}
		else {
			imprimirMenu();
			sc.nextLine();
		}

	}

	public static void imprimirMenu() {
		System.out.println(
				"1- Insertar\n2- Modificar\n3- Borrar\n4- Mostrar\n5- Salir\n----------------------\nIntrodueix una opció");
	}

	private static void borrarJuego() {
		boolean joc = false;
		System.out.println("Que juego quieres borrar? introduce el nombre");
		String sino="";
		nombre = sc.next();
		for (Iterator i = jocs.keySet().iterator(); i.hasNext();) {
			String k = (String) i.next();
			if (k.toLowerCase().equals(nombre)) {
				System.out.println("Estas seguro de que lo deseas borrar? introduce S / N");
				sino=sc.next();
				if (sino.toLowerCase().equals("s")) {
					jocs.remove(nombre);
					sc.nextLine();
					System.out.println("Juego borrado");
				} else {
					System.out.println("Juego no borrado");
				}
			}
			else {
				System.out.println("El joc no existeix");
			}
		}
	}

	public static void modificarJuego() {
		boolean joc = false;
		System.out.println("Que juego quieres modificar? introduce el nombre");
		nombre = sc.nextLine();
		for (Iterator i = jocs.keySet().iterator(); i.hasNext();) {
			String k = (String) i.next();
			if (k.toLowerCase().equals(nombre)) {
				joc = true;
			}

		}
		if (joc == false) {
			System.out.println("El joc no existeix");
		} else {
			System.out.println("Introdueix el nou preu");
			precio = sc.nextFloat();
			jocs.put(nombre, precio);
		}

	}

	public static void mostrarJuegos() {
		String key = "";
		Iterator it = jocs.keySet().iterator();

		while (it.hasNext()) {
			key = (String) it.next();
			System.out.println(key + " : " + jocs.get(key));
		}
	}

	public static void introducirJuego() {
		System.out.println("Introduce el nombre del videjuego");
		nombre = sc.next();
		System.out.println("Introduce el precio del videjuego (decimales con ,)");
		while (true) {
			if (!sc.hasNextFloat()) {
				sc.next();
				System.out.println("Introdueix decimals amb ,");
			} else {
				precio = sc.nextFloat();
				jocs.put(nombre, precio);
				break;
			}
		}
	}

}

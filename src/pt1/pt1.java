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
		System.out.println(
				"1- Insertar\n2- Modificar\n3- Borrar\n4- Mostrar\n5- Salir\n----------------------\nIntrodueix una opció ");

		// Bucle para menú
		while (true) {
			if (sc.hasNextInt()) {
				opcio = sc.nextInt();
				while (opcio >= 1 && opcio <= 5) {
					if(opcio==5) {
						System.out.println("Hasta luego !");
						break;
					}
					switch (opcio) {
					case 1:
						introducirJuego();
						jocs.put(nombre, precio);
						System.out.println(
								"1- Insertar\n2- Modificar\n3- Borrar\n4- Mostrar\n5- Salir\n----------------------\nIntrodueix una opció");
						opcio=0;
						break;
					case 2:
						if(!jocs.isEmpty()) {
							modificarJuego();
						}
						else {
							System.out.println("ERROR: no hay ningun juego");
						}
						opcio=0;
						break;
					case 3:
						if(!jocs.isEmpty()) {
							borrarJuego();
						}
						else {
							System.out.println("ERROR: no hay ningun juego para mostrar");
						}
						opcio=0;
						break;
					case 4:
						if(!jocs.isEmpty()) {
							mostrarJuegos();
						}
						else {
							System.out.println("ERROR: no hay ningun juego");
						}
						opcio=0;
						break;
					case 5:
						opcio=5;
						break;
						
					}
					
					
				} 
				if(opcio==5) {
					break;
				}
			} 	
		}

	}

	private static void borrarJuego() {
		boolean joc=false;
		System.out.println("Que juego quieres modificar? introduce el nombre");
		nombre=sc.nextLine();
		for(Iterator i=jocs.keySet().iterator(); i.hasNext();){ 
			String k=(String)i.next(); 
			if(k.toLowerCase().equals(nombre)) {
				joc=true;
			}
			
			}
		if(joc==false) {
			System.out.println("El joc no existeix");
		}
		else {
			System.out.println("Estas seguro de que lo deseas borrar? introduce S / N");
			if(sc.nextLine().toLowerCase().equals("s")) {
				jocs.remove(nombre);
			}
			else {
				
			}
		}
	}

	public static void modificarJuego() {
		boolean joc=false;
		System.out.println("Que juego quieres modificar? introduce el nombre");
		nombre=sc.nextLine();
		for(Iterator i=jocs.keySet().iterator(); i.hasNext();){ 
			String k=(String)i.next(); 
			if(k.toLowerCase().equals(nombre)) {
				joc=true;
			}
			
			}
		if(joc==false) {
			System.out.println("El joc no existeix");
		}
		else {
			System.out.println("Introdueix el nou preu");
			precio = sc.nextFloat();
			jocs.put(nombre, precio);
		}

	}
	public static void mostrarJuegos() {
		String k="";
		Float v=0f;
		
		for(Iterator i=jocs.keySet().iterator(); i.hasNext();){ 
			 k=(String)i.next(); 
			
			}
		for(Iterator i=jocs.values().iterator(); i.hasNext();){ 
			 v=(Float)i.next(); 
				System.out.println(k+" "+v);
			}
	}

	public static void introducirJuego() {
		
			System.out.println("Introduce el nombre del videjuego");
			sc.next();
			nombre = sc.nextLine();
			System.out.println("Introduce el precio del videjuego (decimales con ,)");
			while (true) {
			if (!sc.hasNextFloat()) {
				sc.next();
				System.out.println("Introdueix decimals amb ,");
			} else {
				precio = sc.nextFloat();
				break;
			}
		}
	}

}

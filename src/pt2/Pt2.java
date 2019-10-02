package pt2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Pt2 {
	static File f3 = new File("myFiles");
	static File f= new File("myFiles\\fichero1.txt");
	static File f2 = new File("myFiles\\fichero2.txt");
	static File frasesMatrix = new File("myFiles\\matrix.txt");
	static File f4 = new File("myFiles\\escribeLineas.txt");
	static File fMaps = new File("mapita.txt");

	
	public static void main(String[] args) throws IOException {
		createFiles();
		myFiles();
		cat();
		escriure();
		escribeLineas();
		//cp();
		mapsInAFile();
		muestraMaps();
	}

	public static void muestraMaps() throws FileNotFoundException {
		TreeMap<Integer, String>mapitaOrden=new TreeMap();
		Scanner sc = new Scanner(fMaps);
		int i=1;
		while(sc.hasNextLine()) {
			mapitaOrden.put(i, sc.nextLine());
			System.out.println(mapitaOrden.toString());
			i++;
		}
	}

	public static void mapsInAFile() throws IOException {
		PrintStream ps = new PrintStream(fMaps);
		if(!fMaps.exists()) {
			fMaps.createNewFile();

		}
		HashMap<Integer, String>mapita=new HashMap();
		mapita.put(1, "Hola");
		mapita.put(2, "Hello");
		mapita.put(3, "Ni Hao");
		mapita.put(4, "Halo");
		mapita.put(5, "Aloha");
		
		System.out.println(mapita.toString());
		for(int i=1;i<mapita.size();i++) {
			ps.println(mapita.get(i));
		}
	}

	public static void cp() throws IOException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Introdueix la ruta del fitxer origen");
		File origen = new File(sc.nextLine());
		System.out.println("Introdueix la ruta del destí");
		File destino = new File(sc.nextLine());
		Files.copy(origen.toPath(), destino.toPath(),StandardCopyOption.REPLACE_EXISTING);
	}

	public static void escribeLineas() throws FileNotFoundException {
		int i=0;
		PrintStream ps = new PrintStream(f4);

		while(i<=9) {
			ps.println("Linea "+i+"\n");
			i++;
		}
	}

	public static void escriure() throws FileNotFoundException {
		PrintStream ps = new PrintStream(frasesMatrix);
		Scanner sc = new Scanner(frasesMatrix);
		
		// Si ja té una línea no sobreescriurem
		if(sc.hasNextLine()) {
			System.out.println("No sobreescribirem");
		}
		else {
			ps.println("Yo sólo puedo mostrarte la puerta, tú eres quien la tiene que atravesar.");
		}
		
	}

	public static void cat() throws FileNotFoundException {
		if(f2.isFile()) {
			Scanner sc = new Scanner(f2);
			while(sc.hasNextLine()) {
				if(sc.hasNextLine()) {
					System.out.println(sc.nextLine());
				}
			}
		}
		else {
			System.out.println("'ruta' no es un fichero, es un directorio");
		}
		
	}

	private static void createFiles() throws IOException {
		
	
		
		if(!f.exists()) {
			f.createNewFile();
		}
		if(!f2.exists()) {
			f2.createNewFile();
		}
		
		Scanner sc = new Scanner(f);
		
		// Lectura de fichero por palabras
				while(sc.hasNext()) {
					System.out.println(sc.next());
				}
		
		// reiniciamos scanner para que el puntero vaya al inicio
		sc=new Scanner(f);
				
		// Lectura de fichero por lineas
		System.out.println("---------------Lectura por lineas--------------");
		while(sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
	}

	private static void myFiles() throws IOException {

		if(f3.exists()&&f3.isDirectory()) {
			System.out.println("Nice thats a directory !");
		}
		else {
			f3.mkdir();
			
		}
		f2.renameTo(new File("myFiles\\ficheroRenombrado.txt"));
				
			
		// Listamos contenido metiendo el contenido de f3 en array de Files
		File[]arrayFiles=f3.listFiles();
		System.out.println(Arrays.toString(arrayFiles));
		
		f.delete();
		
		arrayFiles=f3.listFiles();
		System.out.println(Arrays.toString(arrayFiles));
		
	}

}

package pt3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Pt3 {
	static 	File f = new File("binari.dat");
	
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream(f);

		if(!f.exists()) {
			f.createNewFile();
		}
		ArrayList<Persona>array=new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		String nom = null, genero = null, residencia = null;
		int edad = 0;
		float ingressos = 0;
		String opcion;
		boolean salida = false;
		
		while (true) {
			System.out.println("-------- BIENVENIDO A LA SOLICITUD DE BECA ------------ "
					+ "\nPARA CONTINUAR INTRODUZCA si"
					+ "\nPARA SALIR INTRODUZCA -1");
			opcion=sc.nextLine();
			if(opcion.equals("-1")) {
				salida=true;
				break;
			}
			else if(opcion.toLowerCase().equals("si")) {
				
			
			System.out.println("Inserta Nom i cognom");
			nom = sc.nextLine();
			while (true) {
				System.out.println("Inserta Sexe [H / M]");
				genero = sc.nextLine();
				if (genero.toLowerCase().equals("h") || genero.toLowerCase().equals("m")) {

					break;
				}
			}
			while (true) {
				System.out.println("Inserta Edat [20 / 60]");
				if (sc.hasNextInt()) {
					edad = sc.nextInt();
					if (edad >= 20 && edad <= 60) {

						break;
					} else {
						sc.nextLine();
					}
				} else {
					System.out.println("Introduce un numero entero");
					sc.nextLine();
				}
			}
			sc = new Scanner(System.in);
			while (true) {
				System.out.println("Residencia Familiar [SI / NO]");
				residencia = sc.nextLine();
				if (residencia.toLowerCase().equals("si") || residencia.toLowerCase().equals("no")) {

					break;
				}
			}
			while (true) {
				System.out.println("Inserta Ingressos anuals familiars");
				if (sc.hasNextFloat()) {
					ingressos = sc.nextFloat();
					break;
				} else {
					System.out.println("Introduce numeros (decimals amb ,)");
					sc.nextLine();
				}
			}
			if(salida==true) {
				break;
			}

		}
			sc.nextLine();
			array.add(new Persona(nom,genero,residencia,opcion,edad,ingressos));
		}
		byte arrayByte[]=array.toString().getBytes();
		fos.write(arrayByte);
		backupFichero();
		leerFichero();
	}

	public static void backupFichero() throws IOException {
		FileInputStream fis = new FileInputStream(f);
		File fBack = new File("binariBack.dat");
		FileOutputStream fos = new FileOutputStream(fBack);

		int valor=fis.read();
        while(valor!=-1){
            fos.write(valor);
            valor=fis.read();
        }
	}

	public static void leerFichero() throws IOException {
		 FileInputStream fis = new FileInputStream(f);
		 String personita = null;
		 int valor=fis.read();
         while(valor!=-1){
             personita+=(char)valor;
             valor=fis.read();
         }
         personita=Arrays.toString(personita.split(","));
         
         int i=0;
         while(i<=personita.length()) {
        	 personita=personita.replace("[", "");
        	 personita=personita.replace("]", "");
        	 personita=personita.replace("null", "");
        	 personita=personita.replace("Persona", "");
        	 i++;
         }

         System.out.println(personita);
	}

}

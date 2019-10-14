package pt5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class Principal{

	public static void main(String[] args) throws IOException {
		
		Persona p = new Persona("Maria","Lopez",36);
		Persona p2 = new Persona("Gustavo","Gomez",1);
		Persona p3 = new Persona("Irene","Salas",36);
		Persona p4 = new Persona("Roberto","Morgade",36);
		Persona p5 = new Persona("Graciela","Iglesias",60);
		
		try {
			FileOutputStream f = new FileOutputStream(new File("myPeople.dat"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			o.writeObject(p);
			o.writeObject(p2);
			o.writeObject(p3);
			o.writeObject(p4);
			o.writeObject(p5);
			
			o.close();
			f.close();
			
			FileInputStream fi = new FileInputStream(new File("myPeople.dat"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			Persona pr = (Persona)oi.readObject();
			Persona pr2 = (Persona)oi.readObject();
			Persona pr3 = (Persona)oi.readObject();
			Persona pr4 = (Persona)oi.readObject();
			Persona pr5 = (Persona)oi.readObject();
			
			System.out.println(pr.toString());
			System.out.println(pr2.toString());
			System.out.println(pr3.toString());
			System.out.println(pr4.toString());
			System.out.println(pr5.toString());
			
			oi.close();
			fi.close();

			
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}

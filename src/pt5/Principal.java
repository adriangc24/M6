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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Principal {

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {

		Persona p = new Persona("Maria", "Lopez", 36);
		Persona p2 = new Persona("Gustavo", "Gomez", 1);
		Persona p3 = new Persona("Irene", "Salas", 36);
		Persona p4 = new Persona("Roberto", "Morgade", 36);
		Persona p5 = new Persona("Graciela", "Iglesias", 60);

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

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement("Personas");
			doc.appendChild(rootElement);
			int i=1;
			
			while (true) {
				try {
					Persona pr = (Persona) oi.readObject();

					Element persona =doc.createElement("Persona");
					rootElement.appendChild(persona);
					Attr id = doc.createAttribute("id");
					id.setValue(String.valueOf(i));
					persona.setAttributeNode(id);
					
					Element nombre = doc.createElement("Nombre");
			        nombre.appendChild(doc.createTextNode(pr.getNombre()));
					persona.appendChild(nombre);
					
					Element apellido = doc.createElement("Apellido");
			        apellido.appendChild(doc.createTextNode(pr.getApellido()));
					persona.appendChild(apellido);
					
					Element edad = doc.createElement("Edad");
			        edad.appendChild(doc.createTextNode(String.valueOf(pr.getEdad())));
					persona.appendChild(edad);
					
					i++;
				} catch (Exception e) {
					break;
				}
			}
			 // write the content into xml file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File("Personas.xml"));
	         transformer.transform(source, result);
	         // Output to console for testing
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);

			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

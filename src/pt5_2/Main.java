package pt5_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int opcion;
	static Document doc;
	// Cogemos el fichero de ejemplo :
	static File file = new File("cursosexemple.xml");
	static DocumentBuilder dBuilder;

	public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException {
		System.out.println(file.getAbsolutePath());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		dBuilder = factory.newDocumentBuilder();
		try {
			doc = dBuilder.parse(file);
			while (true) {
				if (menu() == true) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception");
			while (true) {
				try {
					if (menu() == true) {
						break;
					}
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	public static void imprimirXml() throws TransformerException, IOException {
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformer = factoria.newTransformer();

		Source source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);
		// Output to console for testing
		StreamResult consoleResult = new StreamResult(System.out);
		transformer.transform(source, consoleResult);
	}
	public static void generarXml() throws TransformerException, IOException {
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformer = factoria.newTransformer();

		Source source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);
	}

	public static boolean menu() throws SAXException, IOException, TransformerException {
		System.out.println("\n-- PROGRAMA DE CONTROL DEL ESTEVE TERRADAS\nQUE QUIERES HACER: \n1-INTRODUCIR ALUMNO"
				+ "\n2-ELIMINAR ALUMNO\n3-MOSTRAR CURSOS\n4-SALIR");
		if (sc.hasNextInt()) {
			opcion = sc.nextInt();
			if (opcion == 1) {
				System.out.println("Sobre que curso quieres crear el alumno?");
				String curso = sc.next();
				sc = new Scanner(System.in);

				if (checkCurso(curso.toLowerCase()) == false) {
					System.out.println("Este curso no existe");
				} else {
					System.out.println("Introduce el nombre del alumno");
					String aux2 = sc.nextLine();
					if (!aux2.isEmpty()) {
						crearAlumno(aux2, curso.toLowerCase());
					} else {
						System.out.println("Nombre vacío");
					}
				}
				generarXml();

			} else if (opcion == 2) {
				System.out.println("Sobre que curso quieres eliminar el alumno?");
				String curso = sc.next();
				sc = new Scanner(System.in);

				if (checkCurso(curso.toLowerCase()) == false) {
					System.out.println("Este curso no existe");
				} else {
					System.out.println("Introduce el nombre del alumno");
					String aux2 = sc.nextLine();
					if (!aux2.isEmpty()) {
						eliminarAlumno(aux2, curso.toLowerCase());
					} else {
						System.out.println("Nombre vacío");
					}
				}
				generarXml();

			} else if (opcion == 3) {
				mostrarCursos();
			} else if (opcion == 4) {
				System.out.println("HASTA LUEGO");
				generarXml();
				imprimirXml();
				return true;
			} else {
				System.out.println("Introduce una opcion correcta");
			}
		} else {
			System.out.println("Introduce una opcion correcta");
		}
		return false;
	}

	public static void mostrarCursos() throws SAXException, IOException {
		System.out.println("-- Metodo mostrarCursos");
		// doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("curs");
		System.out.println("numero de cursos: " + nList.getLength());
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("\nCurs id: " + eElement.getAttribute("id"));

			}
		}
	}

	public static boolean checkCurso(String curso) {
		System.out.println("-- Metodo checkCurso");

		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("curs");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element eElement = (Element) nNode;
			if (eElement.getAttribute("id").toLowerCase().equals(curso)) {
				return true;
			}
		}
		return false;
	}

	public static void crearAlumno(String nombreAlumno, String nombreCurso) {
		System.out.println("-- Metodo crearAlumno");

		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("curs");
		for (int i = 0; i < nList.getLength(); i++) {
			Element element = (Element) nList.item(i);
			if (element.getAttribute("id").toLowerCase().equals(nombreCurso)) {
				// Si existeix el curs demanat, guardem a una NodeList tots els nodes alumnes
				NodeList nAlumnes = doc.getElementsByTagName("alumnes");
				// Creem i afegim el alumne nou al node de alumnes del curs que convingui
				Element alumne = doc.createElement("alumne");
				alumne.appendChild(doc.createTextNode(nombreAlumno));
				nAlumnes.item(i).appendChild(alumne);
			}
		}
	}

	public static void eliminarAlumno(String nombreAlumno, String nombreCurso) {
		System.out.println("-- Metodo eliminarAlumno");

		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("curs");

		// Busquem a la llista un curs que sigui el mateix que el demanat
		for (int i = 0; i < nList.getLength(); i++) {
			Element element = (Element) nList.item(i);
			if (element.getAttribute("id").toLowerCase().equals(nombreCurso)) {
				// Guardem els alumnes a una nodelist
				NodeList alumnes = doc.getElementsByTagName("alumne");
				// Busquem a la nodelist de alumnes un alumne que tingui el mateix nom que el
				// demanat
				for (int j = 0; j < alumnes.getLength(); j++) {
					if (alumnes.item(j).getTextContent().toLowerCase().equals(nombreAlumno.toLowerCase())) {
						// Esborra el alumne
						alumnes.item(j).getParentNode().removeChild(alumnes.item(j));
						break;
					}
				}
				break;
			}
		}

	}

}

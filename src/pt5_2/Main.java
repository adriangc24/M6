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
	static File file = new File("cursosexemple.xml");
	static DocumentBuilder dBuilder;

	public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException {

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
			System.out.println("Creando documento");
			doc = dBuilder.newDocument();
			Element cursos = doc.createElement("cursos");
			doc.appendChild(cursos);
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

	public static void generarXml() throws TransformerException, IOException {
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformer = factoria.newTransformer();

		Source source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);
		// Output to console for testing
		StreamResult consoleResult = new StreamResult(System.out);
		transformer.transform(source, consoleResult);
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

				if (checkCurso(curso) == false) {
					System.out.println("Este curso no existe");
				} else {
					System.out.println("Introduce el nombre del alumno");
					String aux2 = sc.nextLine();
					if (!aux2.isEmpty()) {
						crearAlumno(aux2, curso);
					} else {
						System.out.println("Nombre vacío");
					}
				}

			} else if (opcion == 2) {
				System.out.println("Introduce el nombre del alumno a eliminar");
				String aux = sc.next();
				if (!aux.isEmpty()) {
					eliminarAlumno(aux);
				} else {
					System.out.println("Nombre vacío");
				}

			} else if (opcion == 3) {
				mostrarCursos();
			} else if (opcion == 4) {
				System.out.println("HASTA LUEGO");
				generarXml();
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
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("curs");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				System.out.println("\nCurs id: " + eElement.getAttribute("id"));

			}
		}
	}

	public static boolean checkCurso(String curso) {
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("curs");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				if (eElement.getAttribute("id").toLowerCase().equals(curso.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

	public static void crearAlumno(String nombreAlumno, String nombreCurso) {
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("curs");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				if (eElement.getAttribute("id").toLowerCase().equals(nombreCurso.toLowerCase())) {
					Element alumne = doc.createElement("alumne");
					alumne.appendChild(doc.createTextNode(nombreAlumno));
					Element alumnes = doc.getDocumentElement();
					alumnes.appendChild(alumne);
					eElement.appendChild(alumnes);
				}
				
			}
		}
	}

	public static void eliminarAlumno(String nombre) {
		Element alumne = doc.getDocumentElement();
		Node parent = alumne.getParentNode();
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("alumne");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				if (eElement.getTextContent().toLowerCase().equals(nombre.toLowerCase())) {
					eElement.getParentNode().removeChild(eElement);
				}
			}
		}

	}

}

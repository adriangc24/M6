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
	static Curs c = new Curs();
	static Modul m = new Modul();
	static File file = new File("cursos.xml");
	static DocumentBuilder dBuilder;
	//static Element alumnes, alumne, tutor, cursos, curs, moduls, modul, profes, profe, ufs, uf, titol;
	public static void main(String[] args)
			throws ParserConfigurationException, IOException, TransformerException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		dBuilder = factory.newDocumentBuilder();		
		try{ 
			doc = dBuilder.parse(file);
			while (true) {
				if (menu() == true) {
					break;
				} 
			}
		}
		catch(Exception e) {
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
				+ "\n2-ELIMINAR ALUMNO\n3-CREAR CURSO\n4-CREAR TUTOR\n5-SALIR");
		if (sc.hasNextInt()) {
			opcion = sc.nextInt();
			if (opcion == 1) {
				System.out.println("Sobre que curso quieres crear el alumno?");
				mostrarCursos();
				String curso=sc.next();
				try {
					checkCurso(curso);
					System.out.println("Introduce el nombre del alumno");
					String aux=sc.next();
					if(!aux.isEmpty()) {
						crearAlumno(aux,curso);
					}
					else {
						System.out.println("Nombre vacío");
					}
				}
				catch(Exception e){
					System.out.println("Este curso no existe");
				}
				
			} else if (opcion == 2) {
				System.out.println("Sobre que curso quieres eliminar el alumno?");
				mostrarCursos();
				String aux=sc.next();
				if(!aux.isEmpty()) {
					System.out.println("Introduce el nombre del alumno a eliminar");
					String aux2=sc.next();
					if(!aux2.isEmpty()) {
						eliminarAlumno(aux2, aux);
					}
					else {
						System.out.println("Nombre vacío");
					}
				}
				else {
					System.out.println("Nombre vacío");
				}
				

			} else if (opcion == 3) {
				System.out.println("Introduce el nombre del curso a crear");
				String aux=sc.next();
				if(!aux.isEmpty()) {
					crearCurso(aux);
				}
				else {
					System.out.println("Nombre vacío");
				}
			} else if (opcion == 4) {
				System.out.println("Sobre que curso quieres crear el tutor?");
				mostrarCursos();
				System.out.println("Introduce el nombre del tutor");
				String aux=sc.next();
				if(!aux.isEmpty()) {
					crearCurso(aux);
				}
				else {
					System.out.println("Nombre vacío");
				}

			} else if (opcion == 5) {
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
				if(eElement.getAttribute("id").equals(curso)) {
					return true;
				}
			}
		}
		return false;
	}

	

	public static void crearTutor(String nombre,String nombreCurso) {
		Element tutor = doc.createElement("tutor");
		Element curs = doc.getDocumentElement();
		Element cursos = doc.getDocumentElement();
		tutor.appendChild(doc.createTextNode(nombre));
		curs.appendChild(tutor);
	}

	public static void crearCurso(String curso) {
		Element alumnes=doc.createElement("alumnes");
		Element curs=doc.createElement("curs");
		Attr id = doc.createAttribute("id");
		id.setValue(curso);
		curs.setAttributeNode(id);
		Element cursos=doc.getDocumentElement();
		curs.appendChild(alumnes);
		cursos.appendChild(curs);
	}

	public static void crearAlumno(String nombreAlumno,String nombreCurso) {
		Element alumne = doc.createElement("alumne");
		alumne.appendChild(doc.createTextNode(nombreAlumno));
		Element alumnes=doc.getDocumentElement();
		alumnes.appendChild(alumne);
		Element curs = doc.getDocumentElement();
		curs.appendChild(alumnes);
		NodeList nList = doc.getElementsByTagName("curs");

	}

	public static void eliminarAlumno(String nombre,String nombreCurso) {
		Element alumne = doc.getDocumentElement();
	    Node parent = alumne.getParentNode();
	    parent.removeChild(alumne);

	}

}

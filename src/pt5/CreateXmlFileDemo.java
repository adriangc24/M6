package pt5;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class CreateXmlFileDemo {
	
   public static void main(String argv[]) {

      try {
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.newDocument();
         // root element
         Element rootElement = doc.createElement("Esteve_Terradas");
         doc.appendChild(rootElement);
         
         //  M6 element
         Element departamento = doc.createElement("Ciclos");
         rootElement.appendChild(departamento);

         // setting attribute to element
         Attr attr = doc.createAttribute("Nombre");
         attr.setValue("DAM");
         departamento.setAttributeNode(attr);

         // nombre element
         Element nombre = doc.createElement("Persona");
         Attr attrType = doc.createAttribute("rol");
         attrType.setValue("Profesor");
         nombre.setAttributeNode(attrType);
         nombre.appendChild(doc.createTextNode("Rafa Aracil"));
         departamento.appendChild(nombre);

         Element nombre2 = doc.createElement("Persona");
         Attr attrType2 = doc.createAttribute("rol");
         attrType2.setValue("Profesor");
         nombre2.setAttributeNode(attrType2);
         nombre2.appendChild(
         doc.createTextNode("Enric Mieza"));
         departamento.appendChild(nombre2);
         
         
         Element nombre3 = doc.createElement("Persona");
         Attr attrType3 = doc.createAttribute("rol");
         attrType3.setValue("Alumno");
         nombre3.setAttributeNode(attrType3);
         nombre3.appendChild(
         doc.createTextNode("Juas Salas"));
         departamento.appendChild(nombre3);

         // write the content into xml file
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource source = new DOMSource(doc);
         StreamResult result = new StreamResult(new File("Instituto.xml"));
         transformer.transform(source, result);
         // Output to console for testing
         StreamResult consoleResult = new StreamResult(System.out);
         transformer.transform(source, consoleResult);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
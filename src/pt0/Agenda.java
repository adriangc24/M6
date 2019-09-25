package pt0;

import java.util.HashMap;
import java.util.*;
import java.util.*;

public class Agenda {

	private HashMap persones = new HashMap();

	public static void main(String args[]) {

// Crearem una nova agenga
		Agenda a = new Agenda();

// Afegirem una sèrie de parells <key,value> a l’agenda
		a.persones.put("Metge", "(+52)-4000-5000");
		a.persones.put("Casa", "(888)-4500-3400");
		a.persones.put("Germa", "(575)-2042-3233");
		a.persones.put("Oncle", "(421)-1010-0020");
		a.persones.put("Sogres", "(334)-6105-4334");
		a.persones.put("Oficina", "(304)-5205-8454");
		a.persones.put("Advocat", "(756)-1205-3454");
		a.persones.put("Pare", "(55)-9555-3270");
		a.persones.put("Botiga", "(874)-2400-8600");

// Cridem un mètode que mostrarà la llista de contactes de l’agenda
		a.mostrarAgenda();

// Definirem un Array amb determinats contactes de l’agenda 
		String contactes[] = { "Oncle", "Sogres", "Advocat" };

// Eliminar els valors continguts a l’Array
		for (int i = 0; i < contactes.length; i++) {
			//3a.agenda.remove(contactes[i]);
		}

		a.mostrarAgenda();
	}

	public void mostrarAgenda() {
		System.out.println(" Agenda amb " + this.persones.size() + " telefons");
		for (Iterator i = this.persones.keySet().iterator(); i.hasNext();) {
			String k = (String) i.next();
			String v = (String) this.persones.get(k);
			System.out.println(k + " : " + v);
		}
	}
}

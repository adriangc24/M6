package pt5_2;

import java.util.ArrayList;

public class Curs {
	private String codi;
	private String tutor;
	private ArrayList<String> alumnes;
	private ArrayList<String> moduls;
	public String getCodi() {
		return codi;
	}
	public void setCodi(String codi) {
		this.codi = codi;
	}
	public String getTutor() {
		return tutor;
	}
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}
	public ArrayList<String> getAlumnes() {
		return alumnes;
	}
	public void setAlumnes(ArrayList<String> alumnes) {
		this.alumnes = alumnes;
	}
	public ArrayList<String> getModuls() {
		return moduls;
	}
	public void setModuls(ArrayList<String> moduls) {
		this.moduls = moduls;
	}
	public Curs() {
		super();
	}
	public Curs(String codi, String tutor, ArrayList<String> alumnes, ArrayList<String> moduls) {
		super();
		this.codi = codi;
		this.tutor = tutor;
		this.alumnes = alumnes;
		this.moduls = moduls;
	}
	
	
}

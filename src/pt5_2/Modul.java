package pt5_2;

import java.util.ArrayList;

public class Modul {
	private String codi;
	private String titol;
	private ArrayList<String>profesors;
	private ArrayList<String>ufs;
	public String getCodi() {
		return codi;
	}
	public void setCodi(String codi) {
		this.codi = codi;
	}
	public String getTitol() {
		return titol;
	}
	public void setTitol(String titol) {
		this.titol = titol;
	}
	public ArrayList<String> getProfesors() {
		return profesors;
	}
	public void setProfesors(ArrayList<String> profesors) {
		this.profesors = profesors;
	}
	public ArrayList<String> getUfs() {
		return ufs;
	}
	public void setUfs(ArrayList<String> ufs) {
		this.ufs = ufs;
	}
	public Modul() {
		super();
	}
	public Modul(String codi, String titol, ArrayList<String> profesors, ArrayList<String> ufs) {
		super();
		this.codi = codi;
		this.titol = titol;
		this.profesors = profesors;
		this.ufs = ufs;
	}
	
	
}

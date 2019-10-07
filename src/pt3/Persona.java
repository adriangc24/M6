package pt3;

public class Persona {
	String nom, genero, residencia, opcion;
	int edad;
	float ingressos;
	
	public static void main(String[] args) {
		
		
		
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getIngressos() {
		return ingressos;
	}

	public void setIngressos(float ingressos) {
		this.ingressos = ingressos;
	}

	public Persona(String nom, String genero, String residencia, String opcion, int edad, float ingressos) {
		super();
		this.nom = nom;
		this.genero = genero;
		this.residencia = residencia;
		this.opcion = opcion;
		this.edad = edad;
		this.ingressos = ingressos;
	}

	@Override
	public String toString() {
		return "Persona [nom=" + nom + ", genero=" + genero + ", residencia=" + residencia + ", opcion=" + opcion
				+ ", edad=" + edad + ", ingressos=" + ingressos + "]";
	}

	

}

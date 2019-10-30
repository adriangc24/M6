package pt7;

import java.sql.Date;

public class Socio {
	private String nombre;
	private String apellidos;
	private String direccion;
	private int telefono;
	private String poblacion;
	private int  cp;
	private String provincia;
	private String pais;
	private int edad;
	private String fechaAlta;
	private int cuota;
	
	public Socio() {
		super();
	}

	public Socio(String nombre, String apellidos, String direccion, int telefono, String poblacion, int cp,
			String provincia, String pais, int edad, String fechaAlta, int cuota) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.poblacion = poblacion;
		this.cp = cp;
		this.provincia = provincia;
		this.pais = pais;
		this.edad = edad;
		this.fechaAlta = fechaAlta;
		this.cuota = cuota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getCuota() {
		return cuota;
	}

	public void setCuota(int cuota) {
		this.cuota = cuota;
	}
	
	
	
}

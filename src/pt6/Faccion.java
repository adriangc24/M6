package pt6;

public class Faccion {
	private int faccion_id;
	private String nombre_faccion;
	private String lore;
	public int getFaccion_id() {
		return faccion_id;
	}
	public void setFaccion_id(int faccion_id) {
		this.faccion_id = faccion_id;
	}
	public String getNombre_faccion() {
		return nombre_faccion;
	}
	public void setNombre_faccion(String nombre_faccion) {
		this.nombre_faccion = nombre_faccion;
	}
	public String getLore() {
		return lore;
	}
	public void setLore(String lore) {
		this.lore = lore;
	}
	public Faccion() {
		super();
	}
	public Faccion(int faccion_id, String nombre_faccion, String lore) {
		super();
		this.faccion_id = faccion_id;
		this.nombre_faccion = nombre_faccion;
		this.lore = lore;
	}
	
	
}

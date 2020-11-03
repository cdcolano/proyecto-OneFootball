package clases;

public class Jugador {
	private String nombre;
	private String pais;
	private String posicion;
	private int edad;
	private int numGoles;
	private int numAmarillas;
	private int numAsistencias;
	private int numRojas;
	private Equipo equipo;

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getNumGoles() {
		return numGoles;
	}
	public void setNumGoles(int numGoles) {
		this.numGoles = numGoles;
	}
	public int getNumAmarillas() {
		return numAmarillas;
	}
	public void setNumAmarillas(int numAmarillas) {
		this.numAmarillas = numAmarillas;
	}
	public int getNumAsistencias() {
		return numAsistencias;
	}
	public void setNumAsistencias(int numAsistencias) {
		this.numAsistencias = numAsistencias;
	}
	public int getNumRojas() {
		return numRojas;
	}
	public void setNumRojas(int numRojas) {
		this.numRojas = numRojas;
	}
	
	public Jugador(String nombre, String pais, int edad, String posicion){
		this.nombre= nombre;
		this.pais=pais;
		this.edad=edad;
		this.posicion=posicion;
	}
	
}

package clases;

import java.util.ArrayList;

public class Jugador implements ConImagenes{
	private String nombre; // nombre + equipo clave primaria
	private String pais;
	private String posicion;
	private int edad;
	private int numGoles;
	private int dorsal;
	private int numAmarillas;
	private int numAsistencias;
	private int numRojas;
	private Equipo equipo;
	private String imagen;
	private Liga liga;
	
	public Jugador(String nombre) {
		this.nombre=nombre;
	}
	
	
	public Liga getLiga() {
		return liga;
	}
	public void setLiga(Liga liga) {
		this.liga = liga;
	}
	public int getDorsal() {
		return dorsal;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
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
	
	public Jugador(String nombre, String pais, int edad, String posicion, Equipo equipo, int dorsal,Liga liga){
		this.nombre= nombre;
		this.pais=pais;
		this.edad=edad;
		this.dorsal=dorsal;
		this.posicion=posicion;
		this.equipo=equipo;
		this.liga= liga;
		numAmarillas=0;
		numRojas=0;
		numGoles=0;
		numAsistencias=0;

	}
	
	public Jugador(String nombre,Equipo e) {
		this(nombre);
		this.equipo=e;
	}
	
	
	public Jugador() {
	
	}
	
	/**Considera iguales dos jugadores con mismo nombre y mismo equipo
	 * @param j Jugador a comparar
	 * @return true si son iguales,, false si no lo son
	 */
	public boolean equals(Jugador j) {
		if (nombre.contentEquals(j.getNombre()) && equipo.equals(j.getEquipo())) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**Compara en base al nombre y en caso de ser iguales en base al nombre del equipo
	 * @param Jugador a comparar
	 * @return devuelve 1 si el jugador a comparar es mayor y -1 si es menor
	 */
	public int compareTo(Jugador j) {
		if (!nombre.contentEquals(j.getNombre()))	return nombre.compareTo(j.getNombre());
		else {
			return equipo.getNombre().compareTo(j.getEquipo().getNombre());
		}
	}
	
}

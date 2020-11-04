package clases;

import java.util.ArrayList;

public class Usuario {
	String nombre;
	String correoElec;
	String contrasena;
	ArrayList<Equipo>equiposSeguidos;
	ArrayList<Jugador>jugadoresSeguidos;
	ArrayList<Liga> ligasSeguidas;
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreoElec() {
		return correoElec;
	}
	public void setCorreoElec(String correoElec) {
		this.correoElec = correoElec;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public ArrayList<Equipo> getEquiposSeguidos() {
		return equiposSeguidos;
	}
	public void setEquiposSeguidos(ArrayList<Equipo> equiposSeguidos) {
		this.equiposSeguidos = equiposSeguidos;
	}
	public ArrayList<Jugador> getJugadoresSeguidos() {
		return jugadoresSeguidos;
	}
	public void setJugadoresSeguidos(ArrayList<Jugador> jugadoresSeguidos) {
		this.jugadoresSeguidos = jugadoresSeguidos;
	}
	public ArrayList<Liga> getLigasSeguidas() {
		return ligasSeguidas;
	}
	public void setLigasSeguidas(ArrayList<Liga> ligasSeguidas) {
		this.ligasSeguidas = ligasSeguidas;
	}
}

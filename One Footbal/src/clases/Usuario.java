package clases;

import java.util.ArrayList;

public class Usuario {
	String nombre;
	String correoElec;
	String contrasena;
	ArrayList<Equipo>equiposSeguidos;
	ArrayList<Jugador>jugadoresSeguidos;
	ArrayList<Liga> ligasSeguidas;
	
	public Usuario() {
		equiposSeguidos= new ArrayList<Equipo>();
		jugadoresSeguidos= new ArrayList<Jugador>();
		ligasSeguidas= new ArrayList<Liga>();
	}
	
	public Usuario (String nombre, String correoElec, String contrasena) {
		this();
		this.contrasena=contrasena;
		this.correoElec= correoElec;
		this.nombre= nombre;
		
	}
	
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
	
	public void addLigaSeguida(Liga l) {
		ligasSeguidas.add(l);
	}
	public void addEquipoSeguido(Equipo e) {
		equiposSeguidos.add(e);
	}
	
	public void addJugadorSeguido(Jugador j) {
		jugadoresSeguidos.add(j);
	}
	
	
	public String toString() {
		return "Nombre: "+ nombre + "Correo: " + correoElec;
	}
	
	/**Considera iguales dos Usuarios con el mismo correo electronico
	 * @param u Usuario a comparar
	 * @return true si son iguales false si no lo son
	 */
	public boolean equals(Usuario u) {
		if (u.getCorreoElec().contentEquals(correoElec)) {
			return true;
		}else {
			return false;
		}
	}
	
}

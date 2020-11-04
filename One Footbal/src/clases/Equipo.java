package clases;
import java.util.ArrayList;

public class Equipo extends Contenedor implements Comparable{
	private String nombre;
	private ArrayList<Jugador>jugadores;
	private int puntos;
	private int golesAFavor;
	private int golesEnContra;
	private int partidosJugados;
	private String logo;
	private ArrayList<Noticia>noticias;
	
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getGolesAFavor() {
		return golesAFavor;
	}
	public void setGolesAFavor(int golesAFavor) {
		this.golesAFavor = golesAFavor;
	}
	public int getGolesEnContra() {
		return golesEnContra;
	}
	public void setGolesEnContra(int golesEnContra) {
		this.golesEnContra = golesEnContra;
	}
	public int getPartidosJugados() {
		return partidosJugados;
	}
	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados = partidosJugados;
	}
	
	public ArrayList<Noticia> getNoticias() {
		return noticias;
	}
	
	public void setNoticias(ArrayList<Noticia> noticias) {
		this.noticias = noticias;
	}
	
	
	
	public Equipo() {
		jugadores= new ArrayList<Jugador>();
		puntos=0;
		golesAFavor=0;
		golesEnContra=0;
		partidosJugados=0;
	}
	
	public Equipo(String nombre) {
		this.nombre=nombre;
		jugadores= new ArrayList<Jugador>();
		traspasos= new ArrayList<Traspaso>();
		noticias= new ArrayList<Noticia>();
		puntos=0;
		golesAFavor=0;
		golesEnContra=0;
		partidosJugados=0;
	}
	@Override
	public int compareTo(Object o) {
		Equipo eq=(Equipo)o;
		if (puntos<eq.getPuntos()) {
			return -1;
		}else if(puntos==eq.getPuntos()) {
			return 0;
		}else {
			return 1;
		}
		
	}
	
	
	public String toString() {
		return nombre + "PJ: " + partidosJugados + "Pts: " + puntos+ "GF: " + golesAFavor + "GC: " +golesEnContra;
	}
	
	
	/**Considera iguales dos equipos con el mismo nombre
	 * @param Equipo a comparar
	 * @return true si son iguales, false en caso de que no lo sean
	 */
	public boolean equals(Equipo e) {
		if (nombre.contentEquals(e.getNombre())) {
			return true;
		}else {
			return false;
		}
	}
}

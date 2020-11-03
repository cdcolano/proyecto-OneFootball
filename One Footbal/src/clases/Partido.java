package clases;
import java.util.ArrayList;
import java.util.Date;

public class Partido {
	private Date fecha;
	private Equipo local;
	private Equipo visitante;
	private int golesLocal;
	private int golesVisitante;
	private ArrayList<Jugador>goleadores;
	private ArrayList<Jugador>asistentes;
	private ArrayList<Jugador>amarillas;
	private ArrayList<Jugador>rojas;
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Equipo getLocal() {
		return local;
	}
	public void setLocal(Equipo local) {
		this.local = local;
	}
	public Equipo getVisitante() {
		return visitante;
	}
	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}
	public int getGolesLocal() {
		return golesLocal;
	}
	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}
	public int getGolesVisitante() {
		return golesVisitante;
	}
	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}
	public ArrayList<Jugador> getGoleadores() {
		return goleadores;
	}
	public void setGoleadores(ArrayList<Jugador> goleadores) {
		this.goleadores = goleadores;
	}
	public ArrayList<Jugador> getAsistentes() {
		return asistentes;
	}
	public void setAsistentes(ArrayList<Jugador> asistentes) {
		this.asistentes = asistentes;
	}
	public ArrayList<Jugador> getAmarillas() {
		return amarillas;
	}
	public void setAmarillas(ArrayList<Jugador> amarillas) {
		this.amarillas = amarillas;
	}
	public ArrayList<Jugador> getRojas() {
		return rojas;
	}
	public void setRojas(ArrayList<Jugador> rojas) {
		this.rojas = rojas;
	}
	
	public Partido(Equipo local, Equipo visitante, Date fecha) {
		this.fecha=fecha;
		this.local=local;
		this.visitante=visitante;
		golesLocal=0;
		golesVisitante=0;
		goleadores= new ArrayList<Jugador>();
		asistentes= new ArrayList<Jugador>();
		amarillas= new ArrayList<Jugador>();
		rojas= new ArrayList<Jugador>();
	}
	
	
	/** Considera iguales dos partidos con mismo equipo local
	 * mismo equipo visitante y misma fecha
	 * @param p Partido a comparar
	 * @return true si dos partidos son iguales
	 */
	public boolean equals (Partido p) {
		if (local.equals(p.getLocal()) && visitante.equals(p.getVisitante()) && fecha.equals(p.getFecha())) {
			return true;
		}else {
			return false;
		}
	}
	
	public String toString() {
		return local.getNombre() + " " + golesLocal + "- " + visitante.getNombre() + " " + golesVisitante;
	}
	
}

package clases;
import java.util.ArrayList;
import java.util.Date;

public class Jornada {
	private ArrayList<Partido>partidos; // numJornada + liga clave primaria
	private int numJornada;
	private Date fechaInicio;
	private Date fechaFinal;
	private Liga liga;
	
	
	public Jornada(Liga l,int numJornada) {
		this.liga=l;
		this.numJornada=numJornada;
	}
	
	public Liga getLiga() {
		return liga;
	}
	public void setLiga(Liga liga) {
		this.liga = liga;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public ArrayList<Partido> getPartidos() {
		return partidos;
	}
	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos = partidos;
	}
	public int getNumJornada() {
		return numJornada;
	}
	public void setNumJornada(int numJornada) {
		this.numJornada = numJornada;
	}
	
	
	public void addPartido(Partido p) {
		partidos.add(p);
	}
	
	public Jornada() {
		partidos= new ArrayList<Partido>();
	}
	
	
	public Jornada(int numJornada, Date fechaInicio, Date fechaFinal) {
		this.numJornada=numJornada;
		this.fechaFinal=fechaFinal;
		this.fechaInicio=fechaInicio;
		partidos= new ArrayList<Partido>();
	}
	
	/**Considera iguales cuando las jornadas tienen la misma fecha de inicio
	 * misma fecha de fin y mismo numero de jornada
	 * @param j Jornada a comparar
	 * @return true si son iguales, false en caso de que no lo sean
	 */
	public boolean equals(Jornada j) {
		if (fechaInicio.equals(j.getFechaInicio()) && getFechaFinal().equals(j.getFechaFinal()) && numJornada==j.getNumJornada()) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public String toString() {
		return "Jornada: " + numJornada;
	}
	
}

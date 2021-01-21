package clases;

import java.util.ArrayList;
import java.util.Date;

public class Noticia implements ConImagenes{
	private String titulo; // titulo clave primaria
	private String cuerpo;
	private String imagen;
	private String fuente;
	private ArrayList<Equipo> equipos;
	private ArrayList<Liga>ligas;
	private Date fecha;
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public ArrayList<Equipo> getEquipos() {
		return equipos;
	}
	public void setEquipos(ArrayList<Equipo> equipos) {
		this.equipos = equipos;
	}
	public ArrayList<Liga> getLigas() {
		return ligas;
	}
	public void setLigas(ArrayList<Liga> ligas) {
		this.ligas = ligas;
	}
	//TODO libreria de html o formatos intermedios / editor de texto enriquecido
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	public Noticia() {
		this.equipos= new ArrayList<Equipo>();
		this.ligas= new ArrayList<Liga>();
	}
	
	
	
	public Noticia(String titulo, String imagen) {
		this.titulo=titulo;
		this.imagen=imagen;
		this.equipos= new ArrayList<Equipo>();
		this.ligas= new ArrayList<Liga>();
	}
	
	/**Considera iguales dos noticias con mismo titulo
	 * @param n Noticia a comparar
	 * @return true si son iguales false si no lo son
	 */
	public boolean equals(Noticia n) {
		if (n.getTitulo().contentEquals(titulo) ) {
			return true;
		}else {
			return false;
		}
	}
	
	
}

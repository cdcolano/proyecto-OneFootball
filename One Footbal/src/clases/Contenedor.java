package clases;

import java.io.File;
import java.util.ArrayList;

public class Contenedor implements ConImagenes{
	protected ArrayList<Traspaso>traspasos;
	protected String logo;
	protected String nombre;
	protected ArrayList<Noticia>noticias;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Traspaso> getTraspasos() {
		return traspasos;
	}
	public void setTraspasos(ArrayList<Traspaso> traspasos) {
		this.traspasos = traspasos;
	}
	public String getImagen() {
		return logo;
	}
	public void setImagen(String logo) {
		this.logo = logo;
	}
	public ArrayList<Noticia> getNoticias() {
		return noticias;
	}
	public void setNoticias(ArrayList<Noticia> noticias) {
		this.noticias = noticias;
	}
	
	
	
}

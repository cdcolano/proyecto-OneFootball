package clases;

public class Noticia implements ConImagenes{
	private String titulo;
	private String cuerpo;
	private String imagen;
	private String fuente;
	
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
		
	}
	
	public Noticia(String titulo, String imagen) {
		this.titulo=titulo;
		this.imagen=imagen;
	}
	
	/**Considera iguales dos noticias con mismo titulo y mismo cuerpo
	 * @param n Noticia a comparar
	 * @return true si son iguales false si no lo son
	 */
	public boolean equals(Noticia n) {
		if (n.getTitulo().contentEquals(titulo) && n.getCuerpo().contentEquals(cuerpo)) {
			return true;
		}else {
			return false;
		}
	}
	
	
}

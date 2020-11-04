package clases;

import java.util.Date;

public class Traspaso {
	private Jugador jugador;
	private Equipo vendedor;
	private Equipo equipo;
	private long precio;
	private byte grado;
	private Date fecha;
	
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public Equipo getVendedor() {
		return vendedor;
	}
	public void setVendedor(Equipo vendedor) {
		this.vendedor = vendedor;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public long getPrecio() {
		return precio;
	}
	public void setPrecio(long precio) {
		this.precio = precio;
	}
	public byte getGrado() {
		return grado;
	}
	public void setGrado(byte grado) {
		this.grado = grado;
	}
	
	
	public Traspaso(Jugador jug, Equipo comprador, long precio ) {
		this.jugador=jug;
		this.equipo=comprador;
		this.vendedor=jug.getEquipo();
		this.precio=precio;
		fecha= new Date( System.currentTimeMillis());
	}
	
	
	public String toString() {
		return ("" + jugador.getNombre() + "Vendedor: " + vendedor.getNombre() + "Comprador: " + equipo.getNombre()+
		"precio: " + precio + "fecha" + fecha.toLocaleString());
	}
	
	
	/**Condiera iguales dos traspasos con mismo jugador involucrado, mismo equipo vendedor, mismo comprador
	 * y misma fecha
	 * @param tras Traspaso a comparar
	 * @return true si son iguales, false si no lo son
	 */
	public boolean equals(Traspaso tras) {
		if (jugador.equals(tras.getJugador()) && equipo.equals(tras.getEquipo())  && 
				vendedor.equals(tras.getVendedor()) && precio==tras.getPrecio() && fecha.equals(tras.getFecha())) {
			return true;
		}else {
			return false;
		}
	}
}

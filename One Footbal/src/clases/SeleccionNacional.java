package clases;

import java.util.ArrayList;

public class SeleccionNacional extends Equipo{
	ArrayList<Jugador>seleccionables;
	
	public SeleccionNacional(String nombre) {
		super(nombre);
		seleccionables= new ArrayList<Jugador>();
	}
	
	public SeleccionNacional() {
		super();
		seleccionables= new ArrayList<Jugador>();
	}
	/**
	 * @param s Seleccion a comparar
	 * @return true si son iguales, false si no lo son
	 */
	public boolean equals(SeleccionNacional s) {
		return super.equals(s);
	}

	public ArrayList<Jugador> getSeleccionables() {
		return seleccionables;
	}

	public void setSeleccionables(ArrayList<Jugador> seleccionables) {
		this.seleccionables = seleccionables;
	}
	
	
}

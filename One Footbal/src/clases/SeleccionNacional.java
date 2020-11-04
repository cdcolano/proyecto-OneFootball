package clases;

import java.util.ArrayList;

public class SeleccionNacional extends Equipo{
	ArrayList<Jugador>seleccionables;
	
	public SeleccionNacional(String nombre) {
		super(nombre);
		seleccionables= new ArrayList<Jugador>();
	}
	
}

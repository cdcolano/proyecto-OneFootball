package ventanas;

import javax.swing.JFrame;

import clases.Usuario;

public class VentanaJornada extends JFrame{
	public VentanaJornada(Usuario u) {
		VentanaInicio.anyadeBotonera(this, u);
	}
}

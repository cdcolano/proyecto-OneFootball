package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;

import clases.Liga;
import clases.Usuario;

public class VentanaJornada extends JFrame{
	
	
	public VentanaJornada(Usuario u, Liga l ) {
		VentanaInicio.anyadeBotonera(this, u);
		VentanaLiga.anyadePanelSup(this, l, u);
	}
}

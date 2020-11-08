package ventanas;

import javax.swing.JFrame;

import clases.Noticia;
import clases.Usuario;

public class VentanaNoticia extends JFrame {
	
	public VentanaNoticia(Usuario u, Noticia n) {
		VentanaInicio.anyadeBotonera(this, u);
	}
	
}

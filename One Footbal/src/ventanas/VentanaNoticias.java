package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Contenedor;
import clases.Noticia;
import clases.Usuario;

public class VentanaNoticias extends JFrame {

	
	public VentanaNoticias(Noticia n, JFrame v, Usuario u) {

		VentanaInicio.anyadeBotonera(this, u);
	}
}

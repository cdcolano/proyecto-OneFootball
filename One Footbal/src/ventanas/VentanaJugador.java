package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Usuario;

public class VentanaJugador extends JFrame{

	
	

	public VentanaJugador(Usuario u) {
		VentanaInicio.anyadeBotonera(this, u);
	}
	
}

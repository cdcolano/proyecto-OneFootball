package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Usuario;

public class VentanaSiguiendo extends JFrame{
	public VentanaSiguiendo(Usuario u) {

		
		VentanaInicio.anyadeBotonera(this, u);
	}
}

package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import clases.Usuario;

public class VentanaSiguiendo extends JFrame{
	public VentanaSiguiendo(Usuario u) {
		JTable tEquipos= new JTable();
		JTable tLigas= new JTable();
		JTable tJugadores= new JTable();
		
		VentanaInicio.anyadeBotonera(this, u);
	}
}

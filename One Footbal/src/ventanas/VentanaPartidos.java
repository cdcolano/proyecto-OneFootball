package ventanas;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import clases.Jornada;
import clases.Liga;
import clases.Usuario;

public class VentanaPartidos extends JFrame {
	private DefaultComboBoxModel<Jornada> cbmJornada;
	
	public VentanaPartidos( Usuario u) {
		JComboBox<Jornada>cbJornada= new JComboBox<Jornada>();
		
		for (Liga j : u.getLigasSeguidas()) {
			
		}
		VentanaInicio.anyadeBotonera(this, u);
	}
}

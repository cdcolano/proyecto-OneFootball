package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.*;

import clases.Equipo;
import clases.Jugador;
import clases.Traspaso;

public class VentanaTraspasos extends JFrame {
	ArrayList<Traspaso>traspasos;
	JScrollPane scTraspaso;
	
	public VentanaTraspasos(ArrayList<Traspaso> tras) {
		traspasos=tras;
		JPanel pCentral= new JPanel();
		scTraspaso= new JScrollPane(pCentral);
		
		for (Traspaso traspaso: traspasos) {
			JPanel jp= new JPanel();
			jp.setLayout(new BorderLayout());
			System.out.println(traspaso.toString());
			jp.add( new JLabel(traspaso.toString()));
			pCentral.add(jp);
		}
		//TODO hacer que en el panel central aparezca la imagen del jugador y del equipo de destino
		//debajo del jugador deberá aparecer nombre de equipo vendeder
		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		getContentPane().add(scTraspaso, BorderLayout.CENTER);
		setVisible(true);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	public static void main (String []args) {
		Equipo at= new Equipo("at");
		Jugador j= new Jugador("Jose", "ESP", 19, "DC", at);
		Equipo e= new Equipo("E");
		ArrayList<Traspaso>tras= new ArrayList<Traspaso> ();
		tras.add(new Traspaso(j, e, 10000));
		VentanaTraspasos v= new VentanaTraspasos(tras);
	}
	
}

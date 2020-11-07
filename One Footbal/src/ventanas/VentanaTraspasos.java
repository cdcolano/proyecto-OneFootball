package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.*;

import clases.Contenedor;
import clases.Equipo;
import clases.Jugador;
import clases.Traspaso;
import clases.Usuario;

public class VentanaTraspasos extends JFrame {
	ArrayList<Traspaso>traspasos;
	JScrollPane scTraspaso;
	
	public VentanaTraspasos(ArrayList<Traspaso> tras, Contenedor c, Usuario u) {
		//TODO añadir la imagen del contenedor en un JPanel arriba si es null nada posibilidad de aplicarlo a noticias
		traspasos=tras;
		JPanel pCentral= new JPanel();
		scTraspaso= new JScrollPane(pCentral);
		
		for (Traspaso traspaso: traspasos) {
			JPanel pTraspaso= new JPanel();
			pTraspaso.setLayout(new BorderLayout());
			JPanel pTraspasoSup=new JPanel();
			JLabel texto= new JLabel();
			if (traspaso.getGrado()<4) {
				texto.setText("Rumor");
			}else {
				texto.setText("Fichaje");
			}
			texto.setFont(new Font("helvitica", Font.BOLD, 24));
			pTraspasoSup.add(texto, FlowLayout.LEFT);
			pTraspasoSup.add(new JLabel("" + traspaso.getFecha()),FlowLayout.RIGHT);
			pTraspaso.add(pTraspasoSup,BorderLayout.NORTH);
			
			JPanel pTraspasoCen=new JPanel();
			
			JLabel img= new JLabel();
			img.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaTraspasos.class.getResource(traspaso.getJugador().getImagen())),
					30, 30));
			pTraspasoCen.add(img);
			
			JPanel pTraspasoCenA= new JPanel();
			pTraspasoCenA.setLayout(new GridLayout(3,1));
			JLabel lNombre= new JLabel(traspaso.getJugador().getNombre());
			lNombre.setFont(new Font("helvitica", Font.BOLD, 36));
			pTraspasoCenA.add(lNombre);
			pTraspasoCen.add(new JLabel(traspaso.getJugador().getPosicion()));
			if (traspaso.getGrado()<4) {
				JSlider slGrado= new JSlider(1, 4);
				slGrado.setValue(traspaso.getGrado());
				if (traspaso.getGrado()==1 || traspaso.getGrado()==2) {
					slGrado.setForeground(Color.RED);
				}else if (traspaso.getGrado()==3) {
					slGrado.setForeground(Color.ORANGE);
				}
				pTraspasoCen.add(slGrado);
			}else {
				JLabel precio= new JLabel(""+ traspaso.getPrecio()+ "€");
				precio.setForeground(Color.WHITE);
				precio.setBackground(Color.GREEN);
			}
			
			//TODO añadir parte de abajo de un traspaso
			
			
			
			
			
		}
		pCentral.setLayout( new GridLayout(traspasos.size(),1));
		getContentPane().add(scTraspaso, BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		VentanaInicio.anyadeBotonera(this, u);
	}
	
	
	public static void main (String []args) {
		Equipo at= new Equipo("at");
		Jugador j= new Jugador("Jose", "ESP", 19, "DC", at);
		Equipo e= new Equipo("E");
		ArrayList<Traspaso>tras= new ArrayList<Traspaso> ();
		tras.add(new Traspaso(j, e, 10000));
		//VentanaTraspasos v= new VentanaTraspasos(tras);
	}
	
}

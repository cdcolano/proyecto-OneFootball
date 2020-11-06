package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.TreeSet;

//TODO posibilidad de seleccionar un equipo , al seleccionar un equipo se cargan sus estaditicas en un JPanel abajo
//TODO si el equipo esta en una posicion del 18 o peor poner noombre o background en rojo
//si el equipo esya en una posicion del 4 o mejor poner nombre o background en azul
//si el equipo esta en una posicion entre el el 6 y el 4 poner nombre o background en verde
import javax.swing.*;

import clases.Equipo;
import clases.Liga;

public class VentanaLiga extends JFrame {
	JPanel pCentral;
	JList<Equipo>lEquipos;
	DefaultListModel<Equipo>mEquipos;
	TreeSet<Equipo>clasificacion;
	JPanel pEstadisticas;
	boolean clasif;
	
	public VentanaLiga(boolean clasif, Liga liga) {
		lEquipos= new JList<Equipo>();
		mEquipos= new DefaultListModel<Equipo>();
		
		if (clasif) {
			JPanel pBotoneraSup= new JPanel();
			JPanel pSimbolo= new JPanel();
			JPanel pContenedor= new JPanel();
			JScrollPane spClasificacion= new JScrollPane();
			clasificacion=liga.getEquipos();
			for (Equipo e: clasificacion) {
				mEquipos.addElement(e);
			}
			ImagenListCellRenderer imgCellRenderer= new ImagenListCellRenderer();
			lEquipos.setCellRenderer(imgCellRenderer );
			
			
			spClasificacion.add(lEquipos);
			getContentPane().add(pBotoneraSup, BorderLayout.NORTH);
			pContenedor.setLayout(new BoxLayout(pContenedor, BoxLayout.Y_AXIS));
			pContenedor.add(pSimbolo);
			pContenedor.add(spClasificacion);
			getContentPane().add(pContenedor);
			if (lEquipos.getSelectedValue()!=null) {
				Equipo eq= lEquipos.getSelectedValue();
				pEstadisticas.setLayout(new GridLayout(1,2));
				pEstadisticas.add(new JLabel ("Goles " + eq.getGolesAFavor()/eq.getNumPartidos() ));
				pEstadisticas.add(new JLabel ("Goles en contra" + eq.getGolesEnContra()/eq.getNumPartidos()));
			}
		}else{
			
		}
	}
}

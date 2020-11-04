package ventanas;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import clases.ConImagenes;
import clases.Equipo;
import clases.Noticia;
import clases.Usuario;

public class VentanaInicio extends JFrame{
	private JScrollPane panelCentral; //estara compuesto por más subpanles con los distintos contenidos
	private JPanel equiposSeguido; //hacer un Jpanel por equipoSeguido
	private JPanel topNews; //noticias con ultima fecha necesario BD
	private Usuario u;
	
	
	public VentanaInicio(Usuario u) {
		panelCentral= new JScrollPane();
		for (Equipo e :u.getEquiposSeguidos()) {
			JPanel pEquipo= new JPanel();
			JPanel pEquipoAct= new JPanel();
			JLabel img= new JLabel();
			img.setIcon(new ImageIcon(VentanaInicio.class.getResource(e.getImagen())));
			pEquipoAct.add(img);
			pEquipo.setLayout(new FlowLayout(FlowLayout.LEFT));
			pEquipo.add(new JLabel (e.getNombre()));
			pEquipo.add(pEquipoAct);
			if (e.getNoticias().size()>=6) {
				for (int i=e.getNoticias().size()-1; i>e.getNoticias().size()-6; i++) {	//solo las ultimas 5 noticias por equipo
					Noticia n= e.getNoticias().get(i);
					JPanel pNoticia=anyadePanalesNoticia(n);
					pEquipo.add(pNoticia);
					//TODO añadir MouseListener para poder cargar la ventana con la noticia en cuestion
					}
			}else {
				for (Noticia n: e.getNoticias()) {
					anyadePanalesNoticia(n);
				}
			}
		}
	}

	
	
	
	private JPanel anyadePanalesNoticia(Noticia n) {
		JPanel pNoticia=new JPanel();
		pNoticia.setLayout(new GridLayout(3, 1));
		JLabel img= new JLabel();
		img.setIcon(new ImageIcon(VentanaInicio.class.getResource(n.getImagen())));
		pNoticia.add(img);
		JLabel titulo= new JLabel(n.getTitulo());
		titulo.setFont(new Font("helvitica", Font.BOLD, 24));
		pNoticia.add(titulo);
		JLabel fuente= new JLabel (n.getFuente());
		fuente.setFont(new Font("helvitica", Font.PLAIN, 16));
		return pNoticia;
	}


}






   


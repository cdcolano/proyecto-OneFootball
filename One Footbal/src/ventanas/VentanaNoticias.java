package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import clases.Contenedor;
import clases.Equipo;
import clases.Liga;
import clases.Noticia;
import clases.Usuario;

public class VentanaNoticias extends JFrame {

	
	public VentanaNoticias(Usuario u, Contenedor c) {
		JScrollPane scpanelCentral= new JScrollPane();
		JPanel pEquipo= new JPanel();
		JPanel pEquipoAct= new JPanel();
		JLabel img= new JLabel();
		img.setIcon(new ImageIcon(VentanaInicio.class.getResource(c.getImagen())));
		pEquipoAct.add(img);
		pEquipo.setLayout(new FlowLayout(FlowLayout.LEFT));
		pEquipo.add(new JLabel (c.getNombre()));
		pEquipo.add(pEquipoAct);
		for (Noticia n: c.getNoticias()) {
			JPanel pNoticia=anyadePanalesNoticia(n);
			pNoticia.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount()>=2) {
						VentanaNoticia v= new VentanaNoticia( u, n);
					}
					
				}
			}); 
			pEquipo.add(pNoticia);
		}
		scpanelCentral.add(pEquipo);
		getContentPane().add(scpanelCentral, BorderLayout.CENTER);
		
		
		VentanaInicio.anyadeBotonera(this, u);
		if (c instanceof Liga) {
			Liga l=(Liga)c;
			VentanaLiga.anyadePanelSup(this, l, u);
		}else {
			Equipo e=(Equipo)c;
			VentanaEquipo.anyadePanelSup(this,e,u);
		}
	}
	
	private JPanel anyadePanalesNoticia(Noticia n) {
		JPanel pNoticia=new JPanel();
		pNoticia.setLayout(new BorderLayout());
		JLabel img= new JLabel();
		img.setIcon(new ImageIcon(VentanaInicio.class.getResource(n.getImagen())));
		pNoticia.add(img,BorderLayout.SOUTH);
		JLabel titulo= new JLabel(n.getTitulo());
		titulo.setFont(new Font("helvitica", Font.BOLD, 24));
		pNoticia.add(titulo,BorderLayout.CENTER);
		JLabel fuente= new JLabel (n.getFuente());
		fuente.setFont(new Font("helvitica", Font.PLAIN, 16));
		pNoticia.add(fuente,BorderLayout.NORTH);
		return pNoticia;
	}
}

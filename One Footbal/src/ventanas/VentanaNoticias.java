package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import clases.BD;
import clases.Contenedor;
import clases.Equipo;
import clases.Jornada;
import clases.Liga;
import clases.Noticia;
import clases.Traspaso;
import clases.Usuario;

/**Ventana que dispone varias noticias
 * al hacer doble click en alguna se abre la ventana exclusiva de esa noticia
 * @author cdcol
 *
 */
public class VentanaNoticias extends JFrame {
	private Liga l;
	
	/**
	 * @param u Usuario loggeadp
	 * @param c Elemento del que se desean mostrar las noticias
	 */
	public VentanaNoticias(Usuario u, Contenedor c) {
		if (c instanceof Liga) {
			ArrayList<Noticia>not= new ArrayList<Noticia>();
			l=(Liga)c;
			if (l.getNoticias()==null || l.getNoticias().size()==0) {
				l.setNoticias(new ArrayList<Noticia>());
				for(Equipo e:l.getEquipos()) {
					ArrayList<Noticia>noticiasBuenas=e.getNoticias();
					for (Noticia n:noticiasBuenas) {
						if (l.buscaNoticia(n)) {
							l.getNoticias().add(n);
						}
					}
				
				}
			}
		/*	for (Equipo e:l.getEquipos()) {
				not.addAll(e.getNoticias());
			}*/
			//l.setNoticias(not);
		}
		JScrollPane scpanelCentral= new JScrollPane();
		JPanel pEquipo= new JPanel();
		pEquipo.setLayout(new BorderLayout());
		JPanel pEquipoAct= new JPanel();
		JPanel pNoticias= new JPanel();
		pNoticias.setLayout(new GridLayout(c.getNoticias().size(),1));
		pEquipoAct.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel img= new JLabel();
		img.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaInicio.class.getResource(c.getImagen())),50,50));
		pEquipoAct.add(img);
		//pEquipo.setLayout(new FlowLayout(FlowLayout.LEFT));
		pEquipo.add(new JLabel (c.getNombre()));
		pEquipo.add(pEquipoAct,BorderLayout.NORTH);
		for (Noticia n: c.getNoticias()) {
			System.out.println("Noticia!!");
			JPanel pNoticia=VentanaInicio.anyadePanalesNoticia(n);
			pNoticia.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount()>=2 && pNoticia.getLocationOnScreen().x<=e.getLocationOnScreen().x &&
							pNoticia.getLocationOnScreen().x+ pNoticia.getWidth()>=e.getLocationOnScreen().x &&
							pNoticia.getLocationOnScreen().y<=e.getLocationOnScreen().y && 
							pNoticia.getLocationOnScreen().y+pNoticia.getHeight()>=e.getLocationOnScreen().y){
							VentanaNoticia v= new VentanaNoticia( u, n);
							VentanaNoticias.this.dispose();
					}
					
				}
			});
			pNoticias.add(pNoticia);
		}
		pEquipo.add(pNoticias,BorderLayout.CENTER);
		scpanelCentral=new JScrollPane(pEquipo);
		getContentPane().add(scpanelCentral, BorderLayout.CENTER);
		
		
		VentanaInicio.anyadeBotonera(this, u);
		if (c instanceof Liga) {
			VentanaLiga.anyadePanelSup(this, l, u);
		}else {
			Equipo e=(Equipo)c;
			VentanaEquipo.anyadePanelSup(this,e,u);
		}
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 300, 500, 500);
		setVisible(true);
	}
	
	/**añade los datos de una noticia a un Panel
	 * @param n Noticia a añadir
	 * @return panel de Noticia
	 */
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

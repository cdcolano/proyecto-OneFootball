package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Jornada;
import clases.Liga;
import clases.Partido;
import clases.Usuario;

public class VentanaPartidos extends JFrame {
	
	public VentanaPartidos( Usuario u) {
		JPanel pPrincipal= new JPanel();
		pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.Y_AXIS));
		for (Liga l : u.getLigasSeguidas()) {
			JPanel panelLiga= new JPanel();
			panelLiga.setLayout(new BorderLayout());
			JPanel panelLigaSup= new JPanel();
			JPanel panelLigaSupa= new JPanel();
			panelLigaSupa.setLayout(new GridLayout(2, 1));
			JLabel img= new JLabel();
			img.setIcon(new ImageIcon(VentanaPartidos.class.getResource(l.getImagen())));
			panelLigaSup.add(img);
			JLabel lNombreLiga= new JLabel (l.getNombre());
			lNombreLiga.setFont(new Font ("helvitica", Font.BOLD,20));
			panelLigaSupa.add(lNombreLiga);
			panelLigaSupa.add(new JLabel("Jornada"+ l.getJornadas().size()));
			panelLiga.setLayout(new BorderLayout());
			panelLiga.add(panelLigaSup,BorderLayout.SOUTH);
			panelLiga.add(anyadePanelUltimaJornada(l), BorderLayout.CENTER);
			pPrincipal.add(panelLiga);
		}
		VentanaInicio.anyadeBotonera(this, u);
	
	//TODO posibilidad de hacerlo con JTable
	}
	
	
	
	
	
	/**añade un panel con todos los partidos de la ultima jornada
	 * @param l liga de la que se añaden los partidos
	 * @return panel con los partidos añadidos
	 */
	public static JPanel anyadePanelUltimaJornada(Liga l) {
		JPanel pPrincipal= new JPanel();
		pPrincipal.setLayout(new GridLayout(l.getJornadas().get(l.getJornadas().size()-1).getPartidos().size(), 1));
		for (Partido p: l.getJornadas().get(l.getJornadas().size()-1).getPartidos()) {
			JPanel pPartido= new JPanel();
			pPartido.setLayout(new BorderLayout());
			SimpleDateFormat df= new SimpleDateFormat("aaaa.MM.dd HH: mm: ss zzz");
			pPartido.add(new JLabel("" +df.format(p.getFecha())),BorderLayout.EAST);
			JPanel pResultado= new JPanel();
			pResultado.setLayout(new GridLayout(2,1));
			JPanel pResultadoArriba= new JPanel();
			pResultadoArriba.setLayout(new BorderLayout());
			JPanel pResultadoAbajo= new JPanel();
			pResultadoAbajo.setLayout(new BorderLayout());
			JPanel pLocalIzq= new JPanel();
			JPanel pLocalDcha= new JPanel();
			JPanel pVisitanteIzq= new JPanel();
			JPanel pVisitanteDch= new JPanel();
			pLocalIzq.setLayout(new FlowLayout(FlowLayout.LEFT));
			pLocalDcha.setLayout(new FlowLayout(FlowLayout.RIGHT));
			pVisitanteIzq.setLayout(new FlowLayout(FlowLayout.LEFT));
			pVisitanteDch.setLayout(new FlowLayout(FlowLayout.RIGHT));
			pResultadoArriba.add(pLocalIzq, BorderLayout.CENTER);
			pResultadoArriba.add(pLocalDcha,BorderLayout.EAST);
			pResultadoAbajo.add(pVisitanteIzq, BorderLayout.CENTER);
			pResultadoAbajo.add(pVisitanteDch, BorderLayout.EAST);
			
			
			JLabel imgLocal= new JLabel();
			imgLocal.setIcon(new ImageIcon(VentanaPartidos.class.getResource(p.getLocal().getImagen())));
			pLocalIzq.add(imgLocal);
			pLocalIzq.add(new JLabel(p.getLocal().getNombre()));
			
			JLabel imgVisitante= new JLabel();
			imgVisitante.setIcon(new ImageIcon(VentanaPartidos.class.getResource(p.getVisitante().getImagen())));
			pVisitanteIzq.add(imgVisitante);
			pVisitanteIzq.add(new JLabel (p.getVisitante().getNombre()));
			
			
			pLocalDcha.add(new JLabel("" + p.getGolesLocal()));
			pVisitanteDch.add(new JLabel(""+p.getGolesVisitante()));
			
			pResultado.add(pResultadoArriba);
			pResultado.add(pResultadoAbajo);
			
			pPartido.add(pResultado,BorderLayout.CENTER);
			
			pPrincipal.add(pPartido);
			
		}
		return pPrincipal;
	}
	
	
	
}

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
import javax.swing.JScrollPane;

import clases.BD;
import clases.Jornada;
import clases.Liga;
import clases.Partido;
import clases.Usuario;

/**Ventana que muestra los partidos de las diferentes ligas que sigue el usuario loggeado
 * @author cdcol
 *
 */
public class VentanaPartidos extends JFrame {
	
	/**
	 * @param u Usuario loggeado
	 */
	public VentanaPartidos( Usuario u) {
		JPanel pPrincipal= new JPanel();
		pPrincipal.setLayout(new GridLayout(u.getLigasSeguidas().size(),1));
		for (Liga l : u.getLigasSeguidas()) {
			JPanel panelLiga= new JPanel();
			panelLiga.setLayout(new BorderLayout());
			JPanel panelLigaSup= new JPanel();
			JPanel panelLigaSupa= new JPanel();
			panelLigaSupa.setLayout(new GridLayout(2, 1));
			JLabel img= new JLabel();
			img.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaPartidos.class.getResource(l.getImagen())),50,50));
			panelLigaSup.add(img);
			JLabel lNombreLiga= new JLabel (l.getNombre());
			lNombreLiga.setFont(new Font ("helvitica", Font.BOLD,20));
			panelLigaSupa.add(lNombreLiga);
			panelLigaSupa.add(new JLabel("Jornada "+ l.getJornadas().size()));
			panelLigaSup.add(panelLigaSupa);
			panelLiga.setLayout(new BorderLayout());
			panelLiga.add(panelLigaSup,BorderLayout.NORTH);
			System.out.println(l.getJornadas().get(0).getPartidos().size() + "tamaño");
			panelLiga.add(anyadePanelUltimaJornada(l.getJornadas().get(l.getJornadas().size()-1)), BorderLayout.CENTER);
			pPrincipal.add(panelLiga);
		}
		JScrollPane scCental= new JScrollPane(pPrincipal);
		getContentPane().add(scCental,BorderLayout.CENTER);
		VentanaInicio.anyadeBotonera(this, u);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 300, 500, 500);
		setVisible(true);
	
	//TODO posibilidad de hacerlo con JTable
	}
	
	
	
	
	
	/**añade un panel con todos los partidos de la ultima jornada
	 * @param l liga de la que se añaden los partidos
	 * @return panel con los partidos añadidos
	 */
	public static JPanel anyadePanelUltimaJornada(Jornada j) {
		JPanel pPrincipal= new JPanel();
		j.setPartidos(BD.selectPartidos(j));
		System.out.println(j.getPartidos().size()+ "tamano 2");
		pPrincipal.setLayout(new GridLayout(j.getPartidos().size(), 1));
		for (Partido p: j.getPartidos()) {
			System.out.println(p.getLocal().getNombre() +"Equipo LoCal");
			JPanel pPartido= new JPanel();
			pPartido.setLayout(new BorderLayout());
			SimpleDateFormat df= new SimpleDateFormat("aaaa.MM.dd HH: mm: ss zzz");
			pPartido.add(new JLabel("" +df.format(p.getFecha())),BorderLayout.NORTH);
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
			pLocalDcha.setLayout(new FlowLayout(FlowLayout.LEFT));
			pVisitanteIzq.setLayout(new FlowLayout(FlowLayout.LEFT));
			pVisitanteDch.setLayout(new FlowLayout(FlowLayout.LEFT));
			pResultadoArriba.add(pLocalIzq, BorderLayout.CENTER);
			pResultadoArriba.add(pLocalDcha,BorderLayout.EAST);
			pResultadoAbajo.add(pVisitanteIzq, BorderLayout.CENTER);
			pResultadoAbajo.add(pVisitanteDch, BorderLayout.EAST);
			
			
			JLabel imgLocal= new JLabel();
			imgLocal.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaPartidos.class.getResource(p.getLocal().getImagen())),50,50));
			pLocalIzq.add(imgLocal);
			pLocalIzq.add(new JLabel(p.getLocal().getNombre()));
			
			JLabel imgVisitante= new JLabel();
			imgVisitante.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaPartidos.class.getResource(p.getVisitante().getImagen())),50,50));
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

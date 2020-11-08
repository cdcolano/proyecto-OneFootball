package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Jugador;
import clases.Usuario;

/**Ventana que muestra los datos de un Jugador
 * No se muestra el nombre del jugador imitando a OneFootball
 * @author cdcol
 *
 */
public class VentanaJugador extends JFrame{

	
	

	/**
	 * @param u Usuario loggeado
	 * @param j Jugador del que se mostraran los datos
	 */
	public VentanaJugador(Usuario u,Jugador j) {
		JPanel pSup= new JPanel();
		pSup.setLayout(new BorderLayout());
		JLabel imgJugador= new JLabel();
		imgJugador.setIcon(new ImageIcon(VentanaJugador.class.getResource(j.getImagen())));
		pSup.add(imgJugador,BorderLayout.CENTER);
		JPanel pEquipo= new JPanel();
		pEquipo.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel imgEquipo= new JLabel();
		imgEquipo.setIcon(new ImageIcon(VentanaJugador.class.getResource(j.getImagen())));
		pEquipo.add(imgEquipo);
		pEquipo.add(new JLabel (j.getEquipo().getNombre()));
		pSup.add(pEquipo,BorderLayout.SOUTH);
		JPanel pCentral= new JPanel();
		pCentral.setLayout(new GridLayout(4,2));
		
		
		JPanel pEdad= new JPanel();
		pEdad.setLayout(new GridLayout(2,1));
		JLabel lEdad= new JLabel(""+j.getEdad());
		lEdad.setFont(new Font("helvitica",Font.BOLD,30));
		pEdad.add(new JLabel("Edad"));
		pEdad.add(lEdad);
		pCentral.add(pEdad);
		
		
		JPanel pPais= new JPanel();
		pPais.setLayout(new GridLayout(2,1));
		JLabel lPais= new JLabel(j.getPais());
		lPais.setFont(new Font("helvitica",Font.BOLD,30));
		pPais.add(new JLabel("Pais"));
		pPais.add(lPais);
		pCentral.add(pPais);
		
		
		JPanel pPosicion= new JPanel();
		pPosicion.setLayout(new GridLayout(2,1));
		JLabel lPosicion= new JLabel(j.getPosicion());
		lPosicion.setFont(new Font("helvitica",Font.BOLD,30));
		pPosicion.add(new JLabel("Posicion"));
		pPosicion.add(lPosicion);
		pCentral.add(pPosicion);
		
		
		
		JPanel pAmarillas= new JPanel();
		pAmarillas.setLayout(new GridLayout(2,1));
		JLabel lAmarillas= new JLabel(""+ j.getNumAmarillas());
		lAmarillas.setFont(new Font("helvitica",Font.BOLD,30));
		pAmarillas.add(new JLabel("Tarjetas Amarillas"));
		pAmarillas.add(lAmarillas);
		pCentral.add(pAmarillas);
		
		JPanel pRojas= new JPanel();
		pRojas.setLayout(new GridLayout(2,1));
		JLabel lRojas= new JLabel(""+ j.getNumRojas());
		lAmarillas.setFont(new Font("helvitica",Font.BOLD,30));
		pAmarillas.add(new JLabel("Tarjetas Rojas"));
		pAmarillas.add(lRojas);
		pCentral.add(pRojas);
		
		
		
		JPanel pGoles= new JPanel();
		pGoles.setLayout(new GridLayout(2,1));
		JLabel lGoles= new JLabel(""+ j.getNumGoles());
		lGoles.setFont(new Font("helvitica",Font.BOLD,30));
		pGoles.add(new JLabel("Goles"));
		pGoles.add(lGoles);
		pCentral.add(pGoles);
		
		
		JPanel pAsistencias= new JPanel();
		pAsistencias.setLayout(new GridLayout(2,1));
		JLabel lAsistencias= new JLabel(""+ j.getNumAsistencias());
		lAsistencias.setFont(new Font("helvitica",Font.BOLD,30));
		pAsistencias.add(new JLabel("Asistencias"));
		pAsistencias.add(lAsistencias);
		pCentral.add(pAsistencias);
		
		
		
		JPanel pDorsal= new JPanel();
		pDorsal.setLayout(new GridLayout(2,1));
		JLabel lDorsal= new JLabel(""+ j.getDorsal());
		lDorsal.setFont(new Font("helvitica",Font.BOLD,30));
		pDorsal.add(new JLabel("Dorsal"));
		pDorsal.add(lDorsal);
		pCentral.add(pDorsal);
		
		JScrollPane scCentral= new JScrollPane(pCentral);
		getContentPane().add(scCentral,BorderLayout.CENTER);
		VentanaInicio.anyadeBotonera(this, u);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 300, 500, 500);
		setVisible(true);
	}
	
}

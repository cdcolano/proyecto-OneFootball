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
import clases.Liga;
import clases.Traspaso;
import clases.Usuario;

/**Ventana que muestra los traspasos de un Elemento
 * @author cdcol
 *
 */
public class VentanaTraspasos extends JFrame {
	ArrayList<Traspaso>traspasos;
	JScrollPane scTraspaso;
	
	/**
	 * @param c Elemento del que se desean mostrar los traspasos
	 * @param u Usuario loggeado
	 */
	public VentanaTraspasos( Contenedor c, Usuario u) {
		//TODO añadir la imagen del contenedor en un JPanel arriba si es null nada posibilidad de aplicarlo a noticias
		traspasos=c.getTraspasos();
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
			JPanel ptexto= new JPanel();
			ptexto.setLayout(new FlowLayout(FlowLayout.LEFT));
			ptexto.add(texto);
			
			pTraspaso.setLayout(new BorderLayout());
			pTraspasoSup.add(ptexto,BorderLayout.CENTER);
			JPanel pfecha=new JPanel();
			pfecha.setLayout(new FlowLayout(FlowLayout.RIGHT));
			pfecha.add(new JLabel("" + traspaso.getFecha()));
			pTraspasoSup.add(pfecha,BorderLayout.EAST);
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
			
			JPanel pTraspasoInf= new JPanel();
			JPanel pVendedor= new JPanel();
			JPanel pTraspasoInfCen= new JPanel();
			JPanel pComprador= new JPanel();
			
			pTraspasoInf.setLayout(new BorderLayout());
			pVendedor.setLayout(new FlowLayout(FlowLayout.LEFT));
			pTraspasoInfCen.setLayout(new FlowLayout(FlowLayout.CENTER));
			pComprador.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			
			pVendedor.add(new JLabel( traspaso.getVendedor().getNombre()),FlowLayout.LEFT);
			pTraspasoInfCen.add(new JLabel("se va a"), FlowLayout.CENTER);
			pComprador.add(new JLabel(traspaso.getEquipo().getNombre()));
			
			pTraspaso.add(pVendedor,BorderLayout.WEST);
			pTraspaso.add(pTraspasoInfCen, BorderLayout.CENTER);
			pTraspaso.add(pComprador, BorderLayout.EAST);
			
			
			
			pTraspaso.add( pTraspasoCen,BorderLayout.CENTER);
			pTraspaso.add(pTraspasoInf,BorderLayout.SOUTH);
			
			
			
		}
		if (c instanceof Liga) {
			Liga l= (Liga)c;
			VentanaLiga.anyadePanelSup(this, l, u);
		}else {
			Equipo e= (Equipo)c;
			VentanaEquipo.anyadePanelSup(this,e, u);
		}
		pCentral.setLayout( new GridLayout(traspasos.size(),1));
		getContentPane().add(scTraspaso, BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		VentanaInicio.anyadeBotonera(this, u);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 300, 500, 500);
		setVisible(true);
	}
	
	
	public static void main (String []args) {
		Equipo at= new Equipo("at");
	//	Jugador j= new Jugador("Jose", "ESP", 19, "DC", at);
		Equipo e= new Equipo("E");
		ArrayList<Traspaso>tras= new ArrayList<Traspaso> ();
		//tras.add(new Traspaso(j, e, 10000));
		//VentanaTraspasos v= new VentanaTraspasos(tras);
	}
	
}

package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.*;

import clases.BD;
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
		if (c instanceof Liga) {
			Liga l=(Liga)c;
			if (l.getTraspasos()==null || l.getTraspasos().size()==0) {
				for(Equipo e:l.getEquipos()) {
					l.getTraspasos().addAll(BD.selectTraspasos(e));
				
				}
			}
		System.out.println(l.getTraspasos().size() + "traspasos");
		}else {
			Equipo e=(Equipo)c;
			if (e.getTraspasos()==null || e.getTraspasos().size()==0) {
			e.getTraspasos().addAll(BD.selectTraspasos(e));
			System.out.println(e.getTraspasos().size() + "traspasos");
			}
		}
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
			JPanel pTraspasoCenSup= new JPanel();
			
			JLabel img= new JLabel();
			img.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaTraspasos.class.getResource(traspaso.getJugador().getImagen())),
					30, 30));
			pTraspasoCenSup.add(img);
			
			JPanel pTraspasoCenA= new JPanel();
			pTraspasoCenA.setLayout(new GridLayout(3,1));
			JLabel lNombre= new JLabel(traspaso.getJugador().getNombre());
			lNombre.setFont(new Font("helvitica", Font.BOLD, 36));
			pTraspasoCenA.add(lNombre);
			pTraspasoCenSup.add(new JLabel(traspaso.getJugador().getPosicion()));
			if (traspaso.getGrado()<4) {
				JSlider slGrado= new JSlider(1, 4);
				slGrado.setValue(traspaso.getGrado());
				if (traspaso.getGrado()==1 || traspaso.getGrado()==2) {
					slGrado.setForeground(Color.RED);
				}else if (traspaso.getGrado()==3) {
					slGrado.setForeground(Color.ORANGE);
				}
				pTraspasoCenSup.add(slGrado);
			}else {
				JLabel precio= new JLabel(""+ traspaso.getPrecio()+ "€");
				precio.setForeground(Color.MAGENTA);
				//precio.setBackground(Color.GREEN);
				pTraspasoCenSup.add(precio);
			}
			
			pTraspasoCen.setLayout(new BorderLayout());
			JPanel pVendedor= new JPanel();
			JPanel pTraspasoInf= new JPanel();
			JPanel pComprador= new JPanel();
			JPanel pTraspasoInfCen= new JPanel();
			
			pTraspasoInfCen.setLayout(new FlowLayout(FlowLayout.CENTER));
			pTraspasoInf.setLayout(new BorderLayout());
			pVendedor.setLayout(new FlowLayout(FlowLayout.LEFT));
			pComprador.setLayout(new FlowLayout(FlowLayout.RIGHT));
			JLabel imgVendedor= new JLabel();
			imgVendedor.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaTraspasos.class.getResource(traspaso.getVendedor().getImagen())),50,50));
			pVendedor.add(imgVendedor);
			pVendedor.add(new JLabel( traspaso.getVendedor().getNombre()));
			pTraspasoInfCen.add(new JLabel("se va a"));
			JLabel imgComprador= new JLabel();
			imgComprador.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaTraspasos.class.getResource(traspaso.getEquipo().getImagen())),50,50));
			pComprador.add(imgComprador);
			pComprador.add(new JLabel(traspaso.getEquipo().getNombre()));
			pTraspasoInf.add(pVendedor,BorderLayout.WEST);
			//pTraspasoInf.add(pTraspasoInfCen, BorderLayout.CENTER);
			pTraspasoInf.add(pComprador, BorderLayout.EAST);
			pTraspasoInf.add(pTraspasoInfCen,BorderLayout.CENTER);
			pTraspasoCen.add(pTraspasoCenSup,BorderLayout.CENTER);
			pTraspaso.add( pTraspasoCen,BorderLayout.CENTER);
			pTraspaso.add(pTraspasoInf,BorderLayout.SOUTH);
			
			
			pCentral.add(pTraspaso);
			
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

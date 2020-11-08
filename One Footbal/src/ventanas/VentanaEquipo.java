package ventanas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import clases.Equipo;
import clases.Jugador;
import clases.Liga;
import clases.Usuario;

public class VentanaEquipo extends JFrame{

	//TODO posibilidad de visualizar plantilla 
	public VentanaEquipo(Usuario u,Equipo e) {
		JTable tPlantilla= new JTable();
		DefaultTableModel mPlantilla= new DefaultTableModel();
		Object[]idPlantilla= {"","", "","" };
		mPlantilla.setColumnIdentifiers(idPlantilla);
		for (Jugador j: e.getJugadores()) {
			Object[]valores= {
					new ImageIcon(VentanaEquipo.class.getResource(j.getImagen())),
					j.getNombre(), j.getPais(), j.getDorsal()
			};
			mPlantilla.addRow(valores);
		}
		tPlantilla.setModel(mPlantilla);
		
		tPlantilla.getSelectionModel().addListSelectionListener((ListSelectionEvent ev)-> {
			if (ev.getFirstIndex()==ev.getLastIndex() && ev.getFirstIndex()!=-1) {
				int i=ev.getFirstIndex();
				for (Jugador j: e.getJugadores()) {
					String nom=(String)mPlantilla.getValueAt(i, 1);
					if (j.getNombre().contentEquals(nom)) {
						VentanaJugador v= new VentanaJugador(u, j);
					}
				}
				
			}
			 
				
		});
		
		JScrollPane spCentral= new JScrollPane(tPlantilla);
		getContentPane().add(spCentral,BorderLayout.CENTER);
		anyadePanelSup(this, e, u);
		VentanaInicio.anyadeBotonera(this, u);
	}
	
	
		public static void anyadePanelSup(JFrame vent, Equipo e,Usuario u) {
			JPanel pSuperior= new JPanel();
			JPanel pBotoneraSup= new JPanel();
			
			pSuperior.setLayout(new BorderLayout());
			JLabel nomEquipo= new JLabel(e.getNombre());
			nomEquipo.setIcon(new ImageIcon(VentanaLiga.class.getResource(e.getImagen())));
			nomEquipo.setFont(new Font("helvitica", Font.BOLD, 36));
			pSuperior.add(nomEquipo,BorderLayout.CENTER);
			JButton bClasificacion= new JButton("Clasificacion");
			JButton bTraspasos=new JButton("Traspasos");
			JButton bNoticias= new JButton("Noticias");
			JButton bPlantilla= new JButton("Plantilla");
			pBotoneraSup.add(bClasificacion);
			pBotoneraSup.add(bTraspasos);
			pBotoneraSup.add(bNoticias);
			pBotoneraSup.add(bPlantilla);
			pSuperior.add(pBotoneraSup,BorderLayout.SOUTH);
			
			
			bPlantilla.addActionListener((ActionEvent ev)-> {
				if (!(vent instanceof VentanaEquipo)) {
					VentanaEquipo v= new VentanaEquipo(u, e);
					vent.dispose();
				}
				
			});
			
			
			
			bClasificacion.addActionListener((ActionEvent arg0)-> {
				if (!(vent instanceof VentanaLiga)) {
					VentanaLiga v= new VentanaLiga(true, e.getLiga(), u);
					v.dispose();
				}else {
					VentanaLiga ventLiga=(VentanaLiga)vent;
					if (ventLiga.clasif==false) {
						ventLiga.clasif=true;
						ventLiga.revalidate();
					}
				}
					
				
			});
			
			
					
			
			
			
			bTraspasos.addActionListener((ActionEvent arg0)-> {
				if (!(vent instanceof VentanaTraspasos)) {
					VentanaTraspasos v= new VentanaTraspasos(e,u);
					v.dispose();
				}
					
				
			});
			
			bNoticias.addActionListener((ActionEvent arg0)-> {
				if (!(vent instanceof VentanaNoticias)) {
					VentanaNoticias v= new VentanaNoticias(u,e);
					v.dispose();
				}
					
				
			});
			
			
			
			vent.getContentPane().add(pSuperior,BorderLayout.NORTH);
			//TODO añadir botonera
		}
	
}

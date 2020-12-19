package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import clases.BD;
import clases.Equipo;
import clases.Jugador;
import clases.Liga;
import clases.Usuario;

/**ventana en la que se muestran Equipos, Jugadores y Ligas seguidos por el usuario y se
 * da la opcion de añadir a cada una de estas categorias
 * @author cdcol
 *
 */
public class VentanaSiguiendo extends JFrame{
	/**
	 * @param u Usuario loggeado
	 */
	public VentanaSiguiendo(Usuario u) {
		JTable tEquipos= new JTable();
		JTable tLigas= new JTable();
		JTable tJugadores= new JTable();
		DefaultTableModel mEquipos= new DefaultTableModel();//TODO editor para que las imagenes lleven a la ventana del equipo
		DefaultTableModel mLigas= new DefaultTableModel(); //TODO igual que arriba para ligas
		DefaultTableModel mJugadores= new DefaultTableModel(); // TODO igual para Jugador
		Object[] id= {"",""};
		mEquipos.setColumnIdentifiers(id);
		mJugadores.setColumnIdentifiers(id);
		mLigas.setColumnIdentifiers(id);
		
		JPanel pEquipos= new JPanel();
		JPanel pLigas= new JPanel();
		JPanel pJugadores= new JPanel();
		
		pEquipos.setLayout(new BorderLayout());
		pLigas.setLayout(new BorderLayout());
		pJugadores.setLayout(new BorderLayout());
		
		
		for (Equipo e:u.getEquiposSeguidos()) {
			Object[]valores= {
					e.getImagen(), 
					e.getNombre()
			}; 
			mEquipos.addRow(valores);
		}
		pEquipos.add(new JLabel("Equipos"), BorderLayout.NORTH);
		pEquipos.add(tEquipos,BorderLayout.CENTER);
		JButton anyadeEquipos= new JButton();
		anyadeEquipos.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaSiguiendo.class.getResource("/img/anadir.png")),30,30));
		JPanel pAnyadeEquipos= new JPanel();
		pAnyadeEquipos.add(anyadeEquipos);
		pEquipos.add(pAnyadeEquipos,BorderLayout.SOUTH);
		
		anyadeEquipos.addActionListener((ActionEvent e)->{
				//TODO una vez implantada la base de datos
				
			
		});
		tEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel lmEquipos= tEquipos.getSelectionModel();
		lmEquipos.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String nomEquipo=(String)mEquipos.getValueAt(e.getFirstIndex(), 1);
				Equipo eq= BD.selectEquipo(nomEquipo);
				VentanaEquipo v= new VentanaEquipo(u, eq);
				VentanaSiguiendo.this.dispose();
				
			}
		});
		
		
		for (Liga l:u.getLigasSeguidas()) {
			Object[]valores= {
					l.getImagen(), 
					l.getNombre()
			}; 
			mLigas.addRow(valores);
		}
		pLigas.add(new JLabel("Ligas"), BorderLayout.NORTH);
		pLigas.add(tLigas,BorderLayout.CENTER);
		JButton anyadeLiga= new JButton();
		anyadeLiga.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaSiguiendo.class.getResource("/img/anadir.png")),30,30));
		JPanel pAnyadeLiga= new JPanel();
		pAnyadeLiga.add(anyadeLiga);
		pLigas.add(pAnyadeLiga,BorderLayout.SOUTH);
		
		anyadeLiga.addActionListener((ActionEvent e)->{
				//TODO una vez implantada la base de datos
				
			
		});
		
		
		
		for (Jugador j:u.getJugadoresSeguidos()) {
			Object[]valores= {
					j.getImagen(), 
					j.getNombre()
			}; 
			mJugadores.addRow(valores);
		}
		pJugadores.add(new JLabel("Jugadores"), BorderLayout.NORTH);
		pJugadores.add(tJugadores,BorderLayout.CENTER);
		JButton anyadeJugador= new JButton();
		JPanel pAnyadeJugador= new JPanel();
		anyadeJugador.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaSiguiendo.class.getResource("/img/anadir.png")),30,30));
		pAnyadeJugador.add(anyadeJugador);
		pJugadores.add(pAnyadeJugador,BorderLayout.SOUTH);
		
		anyadeJugador.addActionListener((ActionEvent e)->{
				//TODO una vez implantada la base de datos
				
			
		});
		
		tEquipos.setModel(mEquipos);
		tJugadores.setModel(mJugadores);
		tLigas.setModel(mLigas);
		
		
		JPanel pCentral= new JPanel();
		JScrollPane scEquipos= new JScrollPane(pEquipos);
		JScrollPane scLigas= new JScrollPane (pLigas);
		JScrollPane scJugadores= new JScrollPane (pJugadores);
		pCentral.setLayout(new GridLayout(3,1));
		pCentral.add(scEquipos);
		pCentral.add(scLigas);
		pCentral.add(scJugadores);
		
		getContentPane().add(pCentral,BorderLayout.CENTER);
		
		
		
		VentanaInicio.anyadeBotonera(this, u);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 300, 500, 500);
		setVisible(true);
	}
	
//TODO	Paso a Ventanas Equipos,Jugadores, ligas
	//Posibilidad de buscar
}

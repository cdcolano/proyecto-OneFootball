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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		DefaultTableModel mEquipos= new DefaultTableModel();
		DefaultTableModel mLigas= new DefaultTableModel();
		DefaultTableModel mJugadores= new DefaultTableModel();
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
					new ImageIcon(VentanaSiguiendo.class.getResource(e.getImagen())), 
					e.getNombre()
			}; 
			mEquipos.addRow(valores);
		}
		
		pEquipos.add(tEquipos,BorderLayout.CENTER);
		JButton anyadeEquipos= new JButton();
		anyadeEquipos.setIcon(new ImageIcon(VentanaSiguiendo.class.getResource("/img/anadir.png")));
		pEquipos.add(anyadeEquipos,BorderLayout.SOUTH);
		
		anyadeEquipos.addActionListener((ActionEvent e)->{
				//TODO una vez implantada la base de datos
				
			
		});
		
		
		for (Liga l:u.getLigasSeguidas()) {
			Object[]valores= {
					new ImageIcon(VentanaSiguiendo.class.getResource(l.getImagen())), 
					l.getNombre()
			}; 
			mEquipos.addRow(valores);
		}
		
		pLigas.add(tLigas,BorderLayout.CENTER);
		JButton anyadeLiga= new JButton();
		anyadeLiga.setIcon(new ImageIcon(VentanaSiguiendo.class.getResource("/img/anadir.png")));
		pLigas.add(anyadeLiga,BorderLayout.SOUTH);
		
		anyadeLiga.addActionListener((ActionEvent e)->{
				//TODO una vez implantada la base de datos
				
			
		});
		
		
		
		for (Jugador j:u.getJugadoresSeguidos()) {
			Object[]valores= {
					new ImageIcon(VentanaSiguiendo.class.getResource(j.getImagen())), 
					j.getNombre()
			}; 
			mEquipos.addRow(valores);
		}
		
		pJugadores.add(tLigas,BorderLayout.CENTER);
		JButton anyadeJugador= new JButton();
		anyadeLiga.setIcon(new ImageIcon(VentanaSiguiendo.class.getResource("/img/anadir.png")));
		pJugadores.add(anyadeLiga,BorderLayout.SOUTH);
		
		anyadeJugador.addActionListener((ActionEvent e)->{
				//TODO una vez implantada la base de datos
				
			
		});
		
		
		JPanel pCentral= new JPanel();
		
		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		pCentral.add(tEquipos);
		pCentral.add(tLigas);
		pCentral.add(tJugadores);
		
		getContentPane().add(pCentral,BorderLayout.CENTER);
		
		
		
		VentanaInicio.anyadeBotonera(this, u);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 300, 500, 500);
		setVisible(true);
	}
}

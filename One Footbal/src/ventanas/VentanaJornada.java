package ventanas;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import clases.Jornada;
import clases.Liga;
import clases.Usuario;

/**Ventana que visualiza las Jornadas de una Liga 
 * se puede elegir que Jornada visualizar mediante el Combobox 
 * @author cdcol
 *
 */
public class VentanaJornada extends JFrame{
	DefaultComboBoxModel<Jornada>mJornadas;
	JPanel pCentral;
	
	/**
	 * @param u Usuario loggeado
	 * @param l Liga de la que se muestran las Jornadas
	 */
	public VentanaJornada(Usuario u, Liga l ) {
		mJornadas= new DefaultComboBoxModel<Jornada>();
		JComboBox cbJornadas= new JComboBox<Jornada>();
		pCentral= new JPanel();
		pCentral.setLayout(new BorderLayout());
		for (Jornada j: l.getJornadas()) {
			mJornadas.addElement(j);
		}
		
		cbJornadas.setModel(mJornadas);
		
		cbJornadas.addItemListener(new ItemListener() {
			
			/**
			 *Carga los datos sobre la jornada elegida
			 */
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem()!=null && e.getStateChange()==ItemEvent.SELECTED) {
					Jornada j=(Jornada)e.getItem();
					System.out.println(j.getNumJornada() + "NUMERO DE JORNADA");
					pCentral.removeAll();
					pCentral.add(cbJornadas,BorderLayout.NORTH);
					System.out.println(j.getPartidos().get(0).getLocal().getNombre());
					pCentral.add(VentanaPartidos.anyadePanelUltimaJornada(j),BorderLayout.CENTER);
					pCentral.revalidate();
				}
				
			}
		});
		cbJornadas.setSelectedIndex(mJornadas.getSize()-1);
		pCentral.add(VentanaPartidos.anyadePanelUltimaJornada(mJornadas.getElementAt(mJornadas.getSize()-1)),BorderLayout.CENTER);
		pCentral.add(cbJornadas,BorderLayout.NORTH);
		JScrollPane spCentral= new JScrollPane(pCentral);
		getContentPane().add(spCentral);
		VentanaInicio.anyadeBotonera(this, u);
		VentanaLiga.anyadePanelSup(this, l, u);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 300, 500, 500);
		setVisible(true);
	}
}

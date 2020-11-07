package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.TreeSet;

//TODO posibilidad de seleccionar un equipo , al seleccionar un equipo se cargan sus estaditicas en un JPanel abajo
//TODO si el equipo esta en una posicion del 18 o peor poner noombre o background en rojo
//si el equipo esya en una posicion del 4 o mejor poner nombre o background en azul
//si el equipo esta en una posicion entre el el 6 y el 4 poner nombre o background en verde
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import clases.Equipo;
import clases.Jugador;
import clases.Liga;
import clases.Usuario;

public class VentanaLiga extends JFrame {
	JPanel pCentral;
	JTable tClasificacion;
	DefaultTableModel mEquipos;
	DefaultTableModel mGoleadores;
	DefaultTableModel mAsistentes;
	DefaultTableModel mTarjetasAmarillas;
	DefaultTableModel mTarjetasRojas;
	TreeSet<Equipo>clasificacion;
	
	boolean clasif;
	public static final String[] ID_CLASIF= {"Logo", "Equipo", "Pts", "PJ", "GF", "GC"};
	public static final String[] ID_GOLEADORES= {"Goleadores", "", "", ""};
	public static final String[] ID_ASISTENTES= {"Asistentes", "", "", ""};
	public static final String[] ID_ROJAS= {"Rojas", "", "", ""};
	public static final String[] ID_AMARILLAS= {"Amarillas", "", "", ""};
	
	public VentanaLiga(boolean clasif, Liga liga, Usuario u) {
		tClasificacion= new JTable();
		mEquipos= new DefaultTableModel();
		mAsistentes= new DefaultTableModel();
		mGoleadores= new DefaultTableModel();
		mTarjetasAmarillas= new DefaultTableModel();
		mTarjetasRojas= new DefaultTableModel();
		mEquipos.setColumnIdentifiers(ID_CLASIF);
		JPanel pEstadisticas=new JPanel();
		JPanel pBotoneraSup= new JPanel();
		JPanel pSimbolo= new JPanel();
		JPanel pContenedor= new JPanel();
		JScrollPane spClasif= new JScrollPane();
		
		
		a;
		
		pBotoneraSup.add(new JButton());
		pBotoneraSup.add(new JButton());
		pBotoneraSup.add(new JButton());
		getContentPane().add(pBotoneraSup,BorderLayout.NORTH);
		//TODO añadir botonera
		
		if (clasif) {
			tClasificacion= new JTable();
			clasificacion=liga.getEquipos();
			for (Equipo eq: clasificacion) {
				ImageIcon img= new ImageIcon(VentanaLiga.class.getResource(eq.getImagen()));
				Object[] fila= {img, eq.getNombre(), eq.getPuntos(), eq.getNumPartidos(), eq.getGolesAFavor(), eq.getGolesEnContra()};
				mEquipos.addRow(fila);
			}
			    

			for (int i=0;i<4;i++) {
				for (int j=0; j<6;j++) {
					tClasificacion.getColumnModel().getColumn(j).setCellRenderer(new ColumnColorRenderer());
					}
			}
		
			pEstadisticas.setLayout(new GridLayout(1,2));
			
			
			
			
			
			pContenedor.setLayout(new BorderLayout());
			pContenedor.add(pSimbolo, BorderLayout.SOUTH);
			pContenedor.add(spClasif, BorderLayout.CENTER);
			pContenedor.add(pEstadisticas,BorderLayout.NORTH);
			getContentPane().add(pContenedor, BorderLayout.CENTER);
			
			

			tClasificacion.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		        	if (event.getFirstIndex()==event.getLastIndex() && event.getFirstIndex()!=-1) {
						int i=event.getFirstIndex();
						for (Equipo eq: clasificacion) {
							String nom=(String)mEquipos.getValueAt(i, 1);
							if (eq.equals(new Equipo(nom))) {
								pEstadisticas.add(new JLabel ("Goles " + eq.getGolesAFavor()/eq.getNumPartidos() ));
								pEstadisticas.add(new JLabel ("Goles en contra" + eq.getGolesEnContra()/eq.getNumPartidos()));
							}
						}
						
					}else {
						pEstadisticas.removeAll();
					}
		        }
		    });
			
			
			
		}else{
			JTable tGoleadores= new JTable();
			mGoleadores.setColumnIdentifiers(ID_GOLEADORES);
			anyadeElementos(liga.getMaximosGoleadores(), mGoleadores);;
			tGoleadores.setModel(mGoleadores);
			JScrollPane spGoleadores= new JScrollPane(tGoleadores);
			
			JTable tAsistentes= new JTable();
			mAsistentes.setColumnIdentifiers(ID_ASISTENTES);
			anyadeElementos(liga.getMaximosAsistentes(), mAsistentes);
			tAsistentes.setModel(mAsistentes);
			JScrollPane spAsistentes= new JScrollPane(tAsistentes);
			
			
			JTable tAmarillas= new JTable();
			mTarjetasAmarillas.setColumnIdentifiers(ID_AMARILLAS);
			anyadeElementos(liga.getTarjetasAmarillas(), mTarjetasAmarillas);
			tAmarillas.setModel(mTarjetasAmarillas);
			JScrollPane spAmarillas= new JScrollPane(tAmarillas);
			
			JTable tRojas= new JTable();
			mTarjetasRojas.setColumnIdentifiers(ID_ROJAS);
			anyadeElementos(liga.getTarjetasRojas(), mTarjetasRojas);
			tRojas.setModel(mTarjetasRojas);
			JScrollPane spRojas= new JScrollPane(tRojas);
			
			JPanel pCentro= new JPanel();
			
			pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
			pCentro.add(spGoleadores);
			pCentro.add(spAsistentes);
			pCentro.add(spAmarillas);
			pCentro.add(spRojas);
			
			
			getContentPane().add(pCentro,BorderLayout.CENTER);
			
			
			
		}

		
		VentanaInicio.anyadeBotonera(this, u);
	}
	
	
	private void anyadeElementos(TreeSet<Jugador>listaJugador, DefaultTableModel mJugador) {
		for (int i=0;i<3;i++) {
			Jugador[] jugadores= (Jugador[])listaJugador.toArray();
			Jugador jugador= jugadores[i];
			ImageIcon img= new ImageIcon(VentanaLiga.class.getResource(jugador.getImagen()));
			Object[] listaAtributos= {img, jugador.getNombre(), jugador.getEquipo(), jugador.getNumGoles()};
			mJugador.addRow(listaAtributos);
		}
	}
}




/**Ajusta los colores de las celulas para que se adecuen a la posicion en la tabla
 * Champions, Europa League o Descenso
 * @author cdcol
 *
 */
class ColumnColorRenderer extends DefaultTableCellRenderer {
	   
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
	      Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	      if (row<4) {//esta en Champions
	    	  cell.setBackground(Color.BLUE);
	      }else if (row<6) {//esta en europa league
	    	  cell.setBackground(Color.GREEN);
	      }else if (row>16) {//esta en descenso
	    	  cell.setBackground(Color.RED);
	      }
	      return cell;
	   }
}


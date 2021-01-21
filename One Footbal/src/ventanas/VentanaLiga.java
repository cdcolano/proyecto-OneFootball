package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Set;
import java.util.TreeSet;

import javax.sound.sampled.ReverbType;
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
import javax.swing.table.TableColumn;

import clases.Equipo;
import clases.Jugador;
import clases.Liga;
import clases.Usuario;
//TODO revisar BD orden Noticias topNews 
//si no salen siempre en el mismo orden asignar clave numerica
/**Ventana que muestra la clasificacion asi como las estadisticas de lideres en ciertas categorias en funcion 
 * del booleano clasificacion si este es true se muestra la clasificacion, si es false se muestran los lideres en las 
 * diferentes categorias
 * @author cdcol
 *
 */
public class VentanaLiga extends JFrame {
	JPanel pCentral;
	JTable tClasificacion;
	DefaultTableModel mEquipos;
	DefaultTableModel mGoleadores;
	DefaultTableModel mAsistentes;
	DefaultTableModel mTarjetasAmarillas;
	DefaultTableModel mTarjetasRojas;
	TreeSet<Equipo>clasificacion;
	int lastSelection;
	
	boolean clasif;
	public static final String[] ID_CLASIF= {"Puesto","Logo", "Equipo", "Pts", "PJ", "GF", "GC"};
	public static final String[] ID_GOLEADORES= {"Goleadores", "", "", ""};
	public static final String[] ID_ASISTENTES= {"Asistentes", "", "", ""};
	public static final String[] ID_ROJAS= {"Rojas", "", "", ""};
	public static final String[] ID_AMARILLAS= {"Amarillas", "", "", ""};
	
	/**
	 * @param clasif true si desea mostrar la clasificacion, false si desea mostrar
	 * los lideres por categorias
	 * @param liga liga de la que se desean mostrar los datos
	 * @param u Usuario loggeado
	 */
	public VentanaLiga(boolean clasif, Liga liga, Usuario u) {
		tClasificacion= new JTable();
		mEquipos= new DefaultTableModel();
		mAsistentes= new DefaultTableModel();
		mGoleadores= new DefaultTableModel();
		mTarjetasAmarillas= new DefaultTableModel();
		mTarjetasRojas= new DefaultTableModel();
		mEquipos.setColumnIdentifiers(ID_CLASIF);
		JPanel pEstadisticas=new JPanel();
		clasificacion= new TreeSet<Equipo>();
		JPanel pContenedor= new JPanel();
		JScrollPane spClasif= new JScrollPane(tClasificacion);
		this.clasif=clasif;
		tClasificacion.setModel(mEquipos);
		
		if (clasif) {
			clasificacion=liga.getEquipos();
			int j=1;
			for (Equipo eq: clasificacion) {
				ImageIcon img= new ImageIcon(VentanaLiga.class.getResource(eq.getImagen()));
				Object[] fila= {j,img, eq.getNombre(), eq.getPuntos(), eq.getNumPartidos(), eq.getGolesAFavor(), eq.getGolesEnContra()};
				mEquipos.addRow(fila);
				j++;
			}
			   
			
			for (int p=0;p<tClasificacion.getColumnCount();p++) {//TODO revisar este for
				tClasificacion.getColumnModel().getColumn(p).setCellRenderer(new ColumnColorRenderer());
				
			}
			tClasificacion.setRowHeight(50);
			
//TODO error si la clasificacion es menor 		
			pEstadisticas.setLayout(new GridLayout(1,2));
			
			
			
			
			
			pContenedor.setLayout(new BorderLayout());
			pContenedor.add(spClasif, BorderLayout.CENTER);
			pContenedor.add(pEstadisticas,BorderLayout.NORTH);
			getContentPane().add(pContenedor, BorderLayout.CENTER);
			lastSelection=-1;
			
			tClasificacion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			tClasificacion.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		        	System.out.println(event.getFirstIndex() +"=" + event.getLastIndex());
		        	if (event.getFirstIndex()==event.getLastIndex() && event.getFirstIndex()!=-1 && event.getFirstIndex()!=lastSelection)  {
	
						int i=event.getFirstIndex();
						lastSelection=i;
						pEstadisticas.removeAll();
						for (Equipo eq: clasificacion) {
							String nom=(String)mEquipos.getValueAt(i, 2);
							if (eq.equals(new Equipo(nom))) {
								pEstadisticas.add(new JLabel ("Goles por Partido" + eq.getGolesAFavor()/eq.getNumPartidos() ));
								pEstadisticas.add(new JLabel ("Goles en contra por Partido " + eq.getGolesEnContra()/eq.getNumPartidos()));
								pContenedor.revalidate();
								System.out.println(tClasificacion.getSelectedRow());
							}
						}
						
						
						
		        	}
		        	tClasificacion.clearSelection();
		        	tClasificacion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		        }
		    });
			
			
			
		}else{
			JTable tGoleadores= new JTable();
			mGoleadores.setColumnIdentifiers(ID_GOLEADORES);
			anyadeElementos(liga.getMaximosGoleadores(), mGoleadores,1);
			tGoleadores.setModel(mGoleadores);
			decoraTabla(tGoleadores);
			JScrollPane spGoleadores= new JScrollPane(tGoleadores);
			
			JTable tAsistentes= new JTable();
			mAsistentes.setColumnIdentifiers(ID_ASISTENTES);
			anyadeElementos(liga.getMaximosAsistentes(), mAsistentes,2);
			tAsistentes.setModel(mAsistentes);
			decoraTabla(tAsistentes);
			JScrollPane spAsistentes= new JScrollPane(tAsistentes);
			
			
			JTable tAmarillas= new JTable();
			mTarjetasAmarillas.setColumnIdentifiers(ID_AMARILLAS);
			anyadeElementos(liga.getTarjetasAmarillas(), mTarjetasAmarillas,3);
			tAmarillas.setModel(mTarjetasAmarillas);
			decoraTabla(tAmarillas);
			JScrollPane spAmarillas= new JScrollPane(tAmarillas);
			
			JTable tRojas= new JTable();
			mTarjetasRojas.setColumnIdentifiers(ID_ROJAS);
			anyadeElementos(liga.getTarjetasRojas(), mTarjetasRojas,4);
			tRojas.setModel(mTarjetasRojas);
			decoraTabla(tRojas);
			JScrollPane spRojas= new JScrollPane(tRojas);
			
			JPanel pCentro= new JPanel();
			
			pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS));
			pCentro.add(spGoleadores);
			pCentro.add(spAsistentes);
			pCentro.add(spAmarillas);
			pCentro.add(spRojas);
			
			
			getContentPane().add(pCentro,BorderLayout.CENTER);
			
			
		}
		VentanaLiga.anyadePanelSup(this, liga, u);
		
		VentanaInicio.anyadeBotonera(this, u);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 300, 700, 700);
		setVisible(true);
	}
	
	
	/**añade Elemeentos del tipo Jugador al modelo correspondiente
	 * @param listaJugador  lista de Jugadores a añadir
	 * @param mJugador modelo de la Tabla correspondiente
	 */
	private void anyadeElementos(Set<Jugador>listaJugador, DefaultTableModel mJugador,int num) { //TODO este metodo no funciona si no hay más datos de prueba
		int i=0;
		for (Jugador jugador:listaJugador) {
			System.out.println(jugador.getNombre() + mJugador.getColumnName(0));
			ImageIcon img= new ImageIcon(VentanaLiga.class.getResource(jugador.getImagen()));
			if (num==1) {
				
				Object[] listaAtributos= {img, jugador.getNombre(), 
						VentanaInicio.redimensionImgProd(new ImageIcon(VentanaLiga.class.getResource(jugador.getEquipo().getImagen())),50,50)
						, jugador.getNumGoles()};
				mJugador.addRow(listaAtributos);
			}else if (num==2) {
					
					Object[] listaAtributos= {img, jugador.getNombre(), 
							VentanaInicio.redimensionImgProd(new ImageIcon(VentanaLiga.class.getResource(jugador.getEquipo().getImagen())),50,50)
							, jugador.getNumAsistencias()};
					mJugador.addRow(listaAtributos);
				}else if (num==3) {
					Object[] listaAtributos= {img, jugador.getNombre(), 
							VentanaInicio.redimensionImgProd(new ImageIcon(VentanaLiga.class.getResource(jugador.getEquipo().getImagen())),50,50)
							, jugador.getNumAmarillas()};
					mJugador.addRow(listaAtributos);
				}else if (num==4) {
					Object[] listaAtributos= {img, jugador.getNombre(), 
							VentanaInicio.redimensionImgProd(new ImageIcon(VentanaLiga.class.getResource(jugador.getEquipo().getImagen())),50,50)
							, jugador.getNumRojas()};
					mJugador.addRow(listaAtributos);
				}
			
			if(i>=3) {
				break;
			}
		}
	}
	
	
	/**añade la botoneraa Superior comun en varias ventanas
	 * @param vent ventana a la que se desea añadir el panel
	 * @param l Liga de la que se desea añadir
	 * @param u Usuario loggeado
	 */
	public static void anyadePanelSup(JFrame vent, Liga l,Usuario u) {
		JPanel pSuperior= new JPanel();
		JPanel pBotoneraSup= new JPanel();
		
		pSuperior.setLayout(new BorderLayout());
		JLabel nomLiga= new JLabel(l.getNombre());
		nomLiga.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaLiga.class.getResource(l.getImagen())),150,150));
		nomLiga.setFont(new Font("helvitica", Font.BOLD, 36));
		pSuperior.add(nomLiga,BorderLayout.CENTER);
		JButton bJornada= new JButton("Jornada");
		JButton bClasificacion= new JButton("Clasificacion");
		JButton bTraspasos=new JButton("Traspasos");
		JButton bNoticias= new JButton("Noticias");
		JButton bEstadisticas= new JButton("Estadisticas");
		pBotoneraSup.add(bJornada);
		pBotoneraSup.add(bClasificacion);
		pBotoneraSup.add(bTraspasos);
		pBotoneraSup.add(bNoticias);
		pBotoneraSup.add(bEstadisticas);
		pSuperior.add(pBotoneraSup,BorderLayout.SOUTH);
		JProgressBar pbProgreso= new JProgressBar(0,100);
		Thread hilo2= new Thread(new Runnable() {
					
					@Override
					public void run() {
						while(vent.isVisible()) {
							int n=pbProgreso.getValue()+1;
							if (n>100) {
							n=n-100;
							}
							pbProgreso.setValue(n);
							try {
								Thread.sleep(100);
							}catch(Exception e) {
								e.printStackTrace();
							}
						}
						
					}
				});
		
		Thread hilo= new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				VentanaTraspasos v= new VentanaTraspasos(l,u);
				vent.dispose();
				
			}
		});
		
		
		bJornada.addActionListener((ActionEvent arg0)-> {
			if (!(vent instanceof VentanaJornada)) {
				VentanaJornada v= new VentanaJornada(u,l);
				vent.dispose();
			}
				
			
		});
		
		bClasificacion.addActionListener((ActionEvent arg0)-> {
			if (!(vent instanceof VentanaLiga)) {
				VentanaLiga v= new VentanaLiga(true, l, u);
				vent.dispose();
			}else {
				VentanaLiga ventLiga=(VentanaLiga)vent;
				if (ventLiga.clasif==false) {
					VentanaLiga v= new VentanaLiga(true, l, u);
					vent.dispose();
				}else {
					vent.revalidate();
				}
			}
				
			
		});
		
		bEstadisticas.addActionListener((ActionEvent arg0)-> {
			if (!(vent instanceof VentanaLiga)) {
				VentanaLiga v= new VentanaLiga(false,l,u);
				vent.dispose();
			}else {
				VentanaLiga ventLiga=(VentanaLiga)vent;
				if (ventLiga.clasif==true) {
					//ventLiga.clasif=false;
					//ventLiga.revalidate();
					VentanaLiga v= new VentanaLiga(false,l,u);
					vent.dispose();	
				}else {
					vent.revalidate();
					
				}
			}
				
			
		});
		
		
		bTraspasos.addActionListener((ActionEvent arg0)-> {
			if (!(vent instanceof VentanaTraspasos)) {
				pSuperior.add(pbProgreso,BorderLayout.NORTH);
				pSuperior.revalidate();
				vent.revalidate();
				hilo2.start();
				hilo.start();
				
			}
				
			
		});
		
		bNoticias.addActionListener((ActionEvent arg0)-> {
			if (!(vent instanceof VentanaNoticias)) {
				VentanaNoticias v= new VentanaNoticias(u,l);
				vent.dispose();
			}
				
			
		});
		
		
		
		vent.getContentPane().add(pSuperior,BorderLayout.NORTH);
		//TODO añadir botonera
	}
	
	
	private void decoraTabla(JTable tabla) {
		for (int p=0;p<tabla.getColumnCount();p++) {//TODO revisar este for
			tabla.getColumnModel().getColumn(p).setCellRenderer(new RendererStats());
		}
		tabla.setRowHeight(50);
	}
}




/**Ajusta los colores de las celdas para que se adecuen a la posicion en la tabla
 * Champions, Europa League o Descenso
 * @author cdcol
 *
 */
class ColumnColorRenderer extends DefaultTableCellRenderer {
	   
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
	      JLabel cell = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	      if (column==1) {
	    	  ImageIcon valor=(ImageIcon)value;
	    	  cell.setIcon(VentanaInicio.redimensionImgProd(valor, 50, 50));
	    	  cell.setText("");
	      }
	      if (row<4) {//esta en Champions
	    	  cell.setBackground(Color.BLUE);
	    	  cell.setForeground(Color.WHITE);
	      }else if (row<6) {//esta en europa league
	    	  cell.setBackground(Color.GREEN);
	    	  cell.setForeground(Color.WHITE);
	      }else if (row>16) {//esta en descenso
	    	  cell.setBackground(Color.RED);
	    	  cell.setForeground(Color.WHITE);
	      }
	      
	      return cell;
	   }
}
/**Ajusta las columnas correspondientes con un icono
 * @author cdcol
 *
 */
class RendererStats extends DefaultTableCellRenderer {
	   
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
	      JLabel cell = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	      if (column==0 || column==2) {
	    	  ImageIcon valor=(ImageIcon)value;
	    	  cell.setIcon(VentanaInicio.redimensionImgProd(valor, 50, 50));
	    	  cell.setText("");
	      }
	      return cell;
	   }
}





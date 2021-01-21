package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
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
	JTable tEquipos;
	JTable tLigas;
	JTable tJugadores;
	Usuario u;
	boolean accion;
	/**
	 * @param u Usuario loggeado
	 */
	public VentanaSiguiendo(Usuario u) {
		this.u=u;
		tEquipos= new JTable();
		tLigas= new JTable();
		tJugadores= new JTable();
		DefaultTableModel mEquipos= new DefaultTableModel();
		DefaultTableModel mLigas= new DefaultTableModel(); 
		DefaultTableModel mJugadores= new DefaultTableModel(); // 
		Object[] id= {"","",""};
		Object[] id2= {"","","","",""};
		mEquipos.setColumnIdentifiers(id);
		mJugadores.setColumnIdentifiers(id2);
		mLigas.setColumnIdentifiers(id);
		
		accion=false;
		
		JPanel pEquipos= new JPanel();
		JPanel pLigas= new JPanel();
		JPanel pJugadores= new JPanel();
		
		pEquipos.setLayout(new BorderLayout());
		pLigas.setLayout(new BorderLayout());
		pJugadores.setLayout(new BorderLayout());
		

		for (Equipo e:u.getEquiposSeguidos()) {
			Object[]valores= {
					e.getImagen(), 
					e.getNombre(),
					"/img/siguiendo.png"
			};
				mEquipos.addRow(valores);
		}
		pEquipos.add(new JLabel("Equipos"), BorderLayout.NORTH);
		pEquipos.add(tEquipos,BorderLayout.CENTER);
		
		ListSelectionModel lmEquipos= tEquipos.getSelectionModel();
		lmEquipos.addListSelectionListener((ListSelectionEvent e)-> {
				if(e.getFirstIndex()==e.getLastIndex() && !tEquipos.isColumnSelected(2) && !accion) {
					try {
						String nomEquipo=(String)mEquipos.getValueAt(e.getFirstIndex(), 1);
						Equipo eq= BD.selectEquipo(nomEquipo);
						VentanaEquipo v= new VentanaEquipo(u, eq);
						VentanaSiguiendo.this.dispose();
					}catch(ArrayIndexOutOfBoundsException ex) {}
				}else {
					accion=false;
				}
				
			}
		);
		
		
		for (Liga l:u.getLigasSeguidas()) {
			Object[]valores= {
					l.getImagen(), 
					l.getNombre(),
					"/img/siguiendo.png"
			};
			mLigas.addRow(valores);
		}
		pLigas.add(new JLabel("Ligas"), BorderLayout.NORTH);
		pLigas.add(tLigas,BorderLayout.CENTER);
		
		tLigas.getSelectionModel().addListSelectionListener((ListSelectionEvent e)-> {
				
			if (e.getFirstIndex()==e.getLastIndex() && e.getFirstIndex()!=-1 && !e.getValueIsAdjusting() && !tLigas.isColumnSelected(2)) {
				try {	
					String nomLiga=(String)mLigas.getValueAt(e.getFirstIndex(), 1);
					Liga l= BD.selectLiga(nomLiga);
					VentanaLiga v= new VentanaLiga(true, l, u);
					VentanaSiguiendo.this.dispose();
				}catch(ArrayIndexOutOfBoundsException ex) {}
			}else {
				accion=false;
			}
			
		});
		
		for (Jugador j:u.getJugadoresSeguidos()) {
			Object[]valores= {
					j.getImagen(), 
					j.getNombre(),
					j.getEquipo().getNombre(),
					VentanaInicio.redimensionImgProd(new ImageIcon(VentanaBusqueda.class.getResource(j.getEquipo().getImagen())),50,50),
					"/img/siguiendo.png"
			}; 
			mJugadores.addRow(valores);
		}
		pJugadores.add(new JLabel("Jugadores"), BorderLayout.NORTH);
		pJugadores.add(tJugadores,BorderLayout.CENTER);
		
		tJugadores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getFirstIndex()==e.getLastIndex() && e.getFirstIndex()!=-1 && !e.getValueIsAdjusting() && !tJugadores.isColumnSelected(4)){
					try {
						String nombre=(String)tJugadores.getValueAt(tJugadores.getSelectedRow(), 1);
						Equipo eq=BD.selectEquipo((String)tJugadores.getValueAt(tJugadores.getSelectedRow(), 2));
						Jugador j= BD.selectJugador(nombre,eq);
						VentanaJugador v= new VentanaJugador(u, j);
						VentanaSiguiendo.this.dispose();
					}catch(Exception ex) {}
				}else {
					accion=false;
				}
			}
		});
		
		tEquipos.setModel(mEquipos);
		tEquipos.getColumnModel().getColumn(0).setCellRenderer(new RendererSiguiendo());
		tEquipos.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
		tEquipos.getColumnModel().getColumn(2).setCellEditor(new ButtonEditorQuitaEquipo(new JCheckBox() , u,this));
		tEquipos.setRowHeight(50);
		tJugadores.setModel(mJugadores);
		tJugadores.getColumnModel().getColumn(0).setCellRenderer(new RendererSiguiendo());
		tJugadores.getColumnModel().getColumn(3).setCellRenderer(new RendererEquipoBueno(u,this));
		tJugadores.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		tJugadores.getColumnModel().getColumn(4).setCellEditor(new ButtonEditorQuitaJugador(new JCheckBox() , u,this));
		tJugadores.setRowHeight(50);
		tLigas.setModel(mLigas);
		tLigas.getColumnModel().getColumn(0).setCellRenderer(new RendererSiguiendo());
		tLigas.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
		tLigas.getColumnModel().getColumn(2).setCellEditor(new ButtonEditorQuitaLiga(new JCheckBox() , u,this));
		tLigas.setRowHeight(50);
		
		
		
		JPanel pCentral= new JPanel();
		JPanel pCentralFuera= new JPanel();
		pCentralFuera.setLayout(new BorderLayout());
		JScrollPane scEquipos= new JScrollPane(pEquipos);
		JScrollPane scLigas= new JScrollPane (pLigas);
		JScrollPane scJugadores= new JScrollPane (pJugadores);
		pCentral.setLayout(new GridLayout(3,1));
		pCentral.add(scEquipos);
		pCentral.add(scLigas);
		pCentral.add(scJugadores);
		
		pCentralFuera.add(pCentral,BorderLayout.CENTER);
		
		JButton bAnyade= new JButton();
		bAnyade.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaBusqueda.class.getResource("/img/anadir.png")), 30, 30));
		JPanel pBoton= new JPanel();
		pBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
		pBoton.add(bAnyade);
		pCentralFuera.add(pBoton,BorderLayout.SOUTH);
		JProgressBar pbProgreso= new JProgressBar(0,100);
		Thread hilo2= new Thread(new Runnable() {
					
					@Override
					public void run() {
						while(VentanaSiguiendo.this.isVisible()) {
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
				VentanaBusqueda v= new VentanaBusqueda(u);
				VentanaSiguiendo.this.dispose();				
			}
		});
		
		
		bAnyade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().add(pbProgreso,BorderLayout.NORTH);
				revalidate();
				hilo2.start();
				hilo.start();
			}
		});
		
		
		getContentPane().add(pCentralFuera,BorderLayout.CENTER);
		
		
		
		VentanaInicio.anyadeBotonera(this, u);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 300, 500, 500);
		setVisible(true);
	}
	
	
	
	
//TODO integrarlo todo para que se vea que es un unico proyecto
//TODO JUnit

}



class RendererSiguiendo extends DefaultTableCellRenderer {

	
	   
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
	      JLabel cell = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	      if (column==0) {
	    	  String valor=(String)value;
	    	  cell.setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(VentanaSiguiendo.class.getResource(valor)), 50, 50));
	    	  cell.setText("");
	    	/*  cell.addMouseListener(new MouseAdapter() {
	    		  @Override
					public void mouseClicked(MouseEvent e) {
						String nom=(String)table.getValueAt(row, 1);
						if (table==vent.tEquipos) {
							Equipo eq=BD.selectEquipo(nom);
							VentanaEquipo v= new VentanaEquipo(u, eq);
						}else if (table==vent.tLigas) {
							Liga l=BD.selectLiga(nom);
							VentanaLiga v= new VentanaLiga(true,l,u);
						}else {
							String nomEquipo=(String)table.getValueAt(row, 2);
							Equipo eq=BD.selectEquipo(nomEquipo);
							Jugador j=BD.selectJugador(nom, eq);
							VentanaJugador v= new VentanaJugador(u, j);
						}
						vent.dispose();
					}
	    		 
			});*/ 
	      }
	      
	      return cell;
	   }
}



class ButtonEditorQuitaJugador extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private Usuario u;
    private VentanaSiguiendo vent;
    public ButtonEditorQuitaJugador(JCheckBox checkBox,Usuario u,VentanaSiguiendo vent) {
        super(checkBox);
        this.u=u;
        this.vent=vent;
        button = new JButton();
        button.setOpaque(true);
       
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
    	vent.accion=true;
    	String nombre=(String)value;
        if (isSelected) {
        	button.setForeground(Color.LIGHT_GRAY);
        	button.setBackground(Color.WHITE);
        } else {
            button.setForeground(Color.WHITE);
            button.setBackground(Color.WHITE);
            button.setBorderPainted(false);
          //  setBackground(UIManager.getColor("Button.background"));
        }
        ImageIcon img=new ImageIcon(ButtonRenderer.class.getResource((String)value));
        if (img!=null)
        button.setIcon(VentanaInicio.redimensionImgProd(img,30,30));
        isPushed = true;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Equipo eq=BD.selectEquipo((String)table.getValueAt(row, 2));
            	Jugador j=BD.selectJugador((String)table.getValueAt(row, 1), eq);
            	u.removeJugador(j);
            	BD.deleteUsuarioJugador(u,j);
            	DefaultTableModel modelo=(DefaultTableModel)table.getModel();
            	if (table.isEditing())
                    table.getCellEditor().stopCellEditing();
            	modelo.removeRow(row);
            	table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditorQuitaJugador(new JCheckBox(), u, vent));
            	table.revalidate();
            	//table.repaint();
            }
        });
        return button;
    }
}



class ButtonEditorQuitaEquipo extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private Usuario u;
    private VentanaSiguiendo vent;
    public ButtonEditorQuitaEquipo(JCheckBox checkBox,Usuario u, VentanaSiguiendo vent) {
        super(checkBox);
        this.u=u;
        this.vent=vent;
        button = new JButton();
        button.setOpaque(true);
       
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
    	vent.accion=true;
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Equipo eq=BD.selectEquipo((String)table.getValueAt(row, 1));
            	u.removeEquipo(eq);
            	BD.deleteUsuarioEquipo(u,eq);
            	DefaultTableModel modelo=(DefaultTableModel)table.getModel();
            	if (table.isEditing())
                    table.getCellEditor().stopCellEditing();
            	modelo.removeRow(row);
            	table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditorQuitaEquipo(new JCheckBox(), u, vent));
            	table.revalidate();
            	
            }
        });
        return button;
    }
}


class ButtonEditorQuitaLiga extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private Usuario u;
    VentanaSiguiendo vent;
    public ButtonEditorQuitaLiga(JCheckBox checkBox,Usuario u,VentanaSiguiendo vent) {
        super(checkBox);
        this.u=u;
        this.vent=vent;
        button = new JButton();
        button.setOpaque(true);
       
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
    	vent.accion=true;
    	String nombre=(String)table.getValueAt(row, 1);
        if (nombre.isEmpty()) {
        	return new JLabel();
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(Color.WHITE);
            button.setBorderPainted(false);
          //  setBackground(UIManager.getColor("Button.background"));
        }
        ImageIcon img=new ImageIcon(ButtonRenderer.class.getResource((String)value));
        if (img!=null)
        button.setIcon(VentanaInicio.redimensionImgProd(img,30,30));
        isPushed = true;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Liga l=BD.selectLiga((String)table.getValueAt(row, 1));
            	u.removeLiga(l);
            	System.out.println(l.getNombre() + "Esta borrado");
            	BD.deleteUsuarioLiga(u, l);
            	DefaultTableModel modelo=(DefaultTableModel)table.getModel();
            	if (table.isEditing())
                    table.getCellEditor().stopCellEditing();
            	modelo.removeRow(row);
            	table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditorQuitaLiga(new JCheckBox(), u, vent));
            	table.revalidate();
            	
            }
        });
        return button;
    }
}

class RendererEquipoBueno extends DefaultTableCellRenderer {
	Usuario u;
	VentanaSiguiendo vent;
	public RendererEquipoBueno(Usuario u,VentanaSiguiendo vent) {
		super();
		this.u=u;
		this.vent=vent;
	}
	   
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
	      JLabel cell = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	      if (column==3) {
	    	  ImageIcon valor=(ImageIcon)value;
	    	  if (valor!=null)
	    	  cell.setIcon(VentanaInicio.redimensionImgProd(valor, 50, 50));
	    	  cell.setText("");
	      }
	      return cell;
	   }
}



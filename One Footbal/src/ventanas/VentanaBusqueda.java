package ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clases.BD;
import clases.Equipo;
import clases.Jugador;
import clases.Liga;
import clases.Usuario;

public class VentanaBusqueda extends JFrame{
	JTextField tfBuscador;
	ArrayList<Jugador>jugadores;
	ArrayList<Equipo>equipos;
	ArrayList<Liga>ligas;
	Usuario u;
	JTable tEquipos;
	
	public VentanaBusqueda(Usuario u) {
		this.u=u;
		tfBuscador= new JTextField(40);
		jugadores= new ArrayList<Jugador>();
		equipos= new ArrayList<Equipo>();
		ligas= new ArrayList<Liga>();
		ligas=BD.selectLigas();
		
		JTable tLigas= new JTable();
		tEquipos= new JTable();	
		JTable tJugadores= new JTable();
		DefaultTableModel mEquipos= new DefaultTableModel();//TODO editor para que las imagenes lleven a la ventana del equipo
		DefaultTableModel mLigas= new DefaultTableModel(); //TODO igual que arriba para ligas
		DefaultTableModel mJugadores= new DefaultTableModel(); // TODO igual para Jugador
		Object[] id= {"","",""};
		Object[] id2= {"","","","",""};
		mEquipos.setColumnIdentifiers(id);
		mJugadores.setColumnIdentifiers(id2);
		mLigas.setColumnIdentifiers(id);
		
		
		JPanel pEquipos= new JPanel();
		JPanel pLigas= new JPanel();
		JPanel pJugadores= new JPanel();
		
		pEquipos.setLayout(new BorderLayout());
		pLigas.setLayout(new BorderLayout());
		pJugadores.setLayout(new BorderLayout());
		
		for (Liga l:ligas) {
			Object[]valores= {
					l.getImagen(), 
					l.getNombre(),
					"/img/siguiendo.png"
			};
			equipos.addAll(l.getEquipos());
			System.out.println(l.getNombre());
			mLigas.addRow(valores);
		}
		
		pLigas.add(new JLabel("Ligas"), BorderLayout.NORTH);
		pLigas.add(tLigas,BorderLayout.CENTER);
			
		
		
		JPanel pCentral= new JPanel();
		
		
	
		

		for (Equipo e:equipos) {
			Object[]valores= {
					e.getImagen(), 
					e.getNombre(),
					"/img/siguiendo.png"
			}; 
			jugadores.addAll(e.getJugadores());
			mEquipos.addRow(valores);
		}
		pEquipos.add(new JLabel("Equipos"), BorderLayout.NORTH);
		pEquipos.add(tEquipos,BorderLayout.CENTER);
		
		
		
		
		
		for (Jugador j:jugadores) {
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
		
		
		
		
		tEquipos.setModel(mEquipos);
		tEquipos.getColumnModel().getColumn(0).setCellRenderer(new RendererSiguiendo());
		tEquipos.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
		tEquipos.getColumnModel().getColumn(2).setCellEditor(new ButtonEditorEquipo(new JCheckBox(), u));
		tEquipos.setRowHeight(50);
		tJugadores.setModel(mJugadores);
		tJugadores.getColumnModel().getColumn(0).setCellRenderer(new RendererSiguiendo());
		tJugadores.getColumnModel().getColumn(3).setCellRenderer(new RendererEquipo());
		tJugadores.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
		tJugadores.getColumnModel().getColumn(4).setCellEditor(new ButtonEditorJugador(new JCheckBox(), u));
		tJugadores.setRowHeight(50);
		tLigas.setModel(mLigas);
		tLigas.getColumnModel().getColumn(0).setCellRenderer(new RendererSiguiendo());
		tLigas.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
		tLigas.getColumnModel().getColumn(2).setCellEditor(new ButtonEditorLiga(new JCheckBox(), u));
		tLigas.setRowHeight(50);
		
		
		

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
	
		
	//TODO	Paso a Ventanas Equipos,Jugadores, ligas
		//Posibilidad de buscar
		
		getContentPane().add(tfBuscador,BorderLayout.NORTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		
		tfBuscador.addKeyListener(new KeyAdapter() {
		
			
			@Override
			public void keyReleased(KeyEvent e) {
				String texto= tfBuscador.getText();
				mJugadores.getDataVector().clear();
				mEquipos.getDataVector().clear();
				mLigas.getDataVector().clear();
				for (Equipo eq:equipos) {
					if (eq.getNombre().contains(texto)) {
						Object[] data= {eq.getImagen(),eq.getNombre(),"/img/siguiendo.png"};
						mEquipos.addRow(data);
					}
				}
				for (Jugador j:jugadores) {
					if (j.getNombre().contains(texto)) {
						Object[] data={
								j.getImagen(), 
								j.getNombre(),
								j.getEquipo().getNombre(),
								VentanaInicio.redimensionImgProd(new ImageIcon(VentanaBusqueda.class.getResource(j.getEquipo().getImagen())),50,50),
								"/img/siguiendo.png"
						}; 
						mJugadores.addRow(data);
					}
				}
				for (Liga l:ligas) {
					if (l.getNombre().contains(texto)) {
						Object[] data= {l.getImagen(),l.getNombre(),"/img/siguiendo.png"};
						mLigas.addRow(data);
					}
				}
				tEquipos.repaint();
				tLigas.repaint();
				tJugadores.repaint();
				
			}
			
			
		});
		
	}
	
	
	
	public static void main(String[]args) {
		VentanaBusqueda v= new VentanaBusqueda(BD.selectUsuario("a"));
	}
}





class ButtonEditorLiga extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private Usuario u;

    public ButtonEditorLiga(JCheckBox checkBox,Usuario u) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        this.u= u;
       
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
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
            	 if (table.isEditing())
                     table.getCellEditor().stopCellEditing();
               Liga l= BD.selectLiga((String)table.getValueAt(row, 1));
               if (!BD.insertarUsuarioLiga(l, u))
               u.addLigaSeguida(l);
              
            }
        });
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}



class ButtonEditorEquipo extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private Usuario u;

    public ButtonEditorEquipo(JCheckBox checkBox,Usuario u) {
        super(checkBox);
        button = new JButton();
        this.u=u;
        button.setOpaque(true);
      
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println(e.getSource());
            	if (table.isEditing())
                    table.getCellEditor().stopCellEditing();
            	Equipo eq=BD.selectEquipo((String)table.getValueAt(row, 1));
            	if (!BD.insertarUsuarioEquipo(eq, u))
            	u.addEquipoSeguido(eq);
            	
            }
        });
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}


class ButtonEditorJugador extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private Usuario u;
    public ButtonEditorJugador(JCheckBox checkBox,Usuario u) {
        super(checkBox);
        this.u=u;
        button = new JButton();
        button.setOpaque(true);
       
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
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
            	if (table.isEditing())
                    table.getCellEditor().stopCellEditing();
            	Equipo eq=BD.selectEquipo((String)table.getValueAt(row, 2));
            	Jugador j=BD.selectJugador((String)table.getValueAt(row, 1), eq);
            	if (!BD.insertarUsuarioJugador(j, u))
            	u.addJugadorSeguido(j);
        		
        		
            }
        });
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}


class RendererEquipo extends DefaultTableCellRenderer {
	   
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

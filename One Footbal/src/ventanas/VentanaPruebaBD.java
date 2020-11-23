package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clases.BD;
import clases.Equipo;
import clases.Jornada;
import clases.Jugador;
import clases.Liga;
import clases.Noticia;
import clases.Partido;
import clases.SeleccionNacional;
import clases.Traspaso;
import clases.Usuario;



public class VentanaPruebaBD extends JFrame{
	static JComboBox<String>cbOpciones= new JComboBox<String>();
	static JTable tCentral;
	private JLabel izquierdo1;
	private JLabel izquierdo2;
	private JLabel izquierdo3;
	private JLabel izquierdo4;
	private JLabel izquierdo5;
	private JLabel izquierdo6;
	private JLabel izquierdo7;
	private JLabel izquierdo8;
	private JLabel izquierdo9;
	private JLabel izquierdo10;
	private JLabel izquierdo11;
	private JTextField tfizquierdo1;
	private JTextField tfizquierdo2;
	private JTextField tfizquierdo3;
	private JTextField tfizquierdo4;
	private JTextField tfizquierdo5;
	private JTextField tfizquierdo6;
	private JTextField tfizquierdo7;
	private JTextField tfizquierdo8;
	private JTextField tfizquierdo9;
	private JTextField tfizquierdo10;
	private JTextField tfizquierdo11;
	JPanel pIzquierdo1;
	JPanel pIzquierdo2;
	JPanel pIzquierdo3;
	JPanel pIzquierdo4;
	JPanel pIzquierdo5;
	JPanel pIzquierdo6;
	JPanel pIzquierdo7;
	JPanel pIzquierdo8;
	JPanel pIzquierdo9;
	JPanel pIzquierdo10;
	JPanel pIzquierdo11;
	Connection con;
	ArrayList<JTextField>listaTexto;
	static Properties p= new Properties();
	private static final String NOM_FICHERO="BD.ini";
	
	
	public VentanaPruebaBD() {
		con=BD.initBD("oneFootball.db");
		add(cbOpciones,BorderLayout.NORTH);
		cbOpciones.addItem("Usuario");
		cbOpciones.addItem("Equipo");
		cbOpciones.addItem("Jornada");
		cbOpciones.addItem("Jugador");
		cbOpciones.addItem("Liga");
		cbOpciones.addItem("Noticia");
		cbOpciones.addItem("Partido");
		cbOpciones.addItem("Seleccion");
		cbOpciones.addItem("Traspaso");
		cbOpciones.addItem("UsuarioJugador");
		cbOpciones.addItem("UsuarioEquipo");
		cbOpciones.addItem("UsuarioLiga");
		cbOpciones.addItem("PartidoJugador");
		cbOpciones.addItem("EquipoNot");
		cbOpciones.addItem("LigaNot");
		
		tCentral= new JTable();
		
		
		izquierdo1= new JLabel();
		izquierdo2= new JLabel();
		izquierdo3= new JLabel();
		izquierdo4= new JLabel();
		izquierdo5= new JLabel();
		izquierdo6= new JLabel();
		izquierdo7= new JLabel();
		izquierdo8= new JLabel();
		izquierdo9= new JLabel();
		izquierdo10= new JLabel();
		izquierdo11= new JLabel();
		
		
		tfizquierdo1= new JTextField(45);
		tfizquierdo2= new JTextField(45);
		tfizquierdo3= new JTextField(45);
		tfizquierdo4= new JTextField(45);
		tfizquierdo5= new JTextField(45);
		tfizquierdo6= new JTextField(45);
		tfizquierdo7= new JTextField(45);
		tfizquierdo8= new JTextField(45);
		tfizquierdo9= new JTextField(45);
		tfizquierdo10= new JTextField(45);
		tfizquierdo11= new JTextField(45);
		
		
		
		JPanel pIzquierdo= new JPanel();
		pIzquierdo.setLayout(new BoxLayout(pIzquierdo, BoxLayout.Y_AXIS));
		
		//creacion JPanels para JTextfields de introduccion de datos
		pIzquierdo1= new JPanel();
		pIzquierdo2= new JPanel();
		pIzquierdo3= new JPanel();
		pIzquierdo4= new JPanel();
		pIzquierdo5= new JPanel();
		pIzquierdo6= new JPanel();
		pIzquierdo7= new JPanel();
		pIzquierdo8= new JPanel();
		pIzquierdo9= new JPanel();
		pIzquierdo10= new JPanel();
		pIzquierdo11= new JPanel();
		
		//FLOWLAYOUT LEFT
		pIzquierdo1.setLayout(new FlowLayout(FlowLayout.LEFT));
		pIzquierdo2.setLayout(new FlowLayout(FlowLayout.LEFT));
		pIzquierdo3.setLayout(new FlowLayout(FlowLayout.LEFT));
		pIzquierdo4.setLayout(new FlowLayout(FlowLayout.LEFT));
		pIzquierdo5.setLayout(new FlowLayout(FlowLayout.LEFT));
		pIzquierdo6.setLayout(new FlowLayout(FlowLayout.LEFT));
		pIzquierdo7.setLayout(new FlowLayout(FlowLayout.LEFT));
		pIzquierdo8.setLayout(new FlowLayout(FlowLayout.LEFT));
		pIzquierdo9.setLayout(new FlowLayout(FlowLayout.LEFT));
		pIzquierdo10.setLayout(new FlowLayout(FlowLayout.LEFT));
		pIzquierdo11.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
		//añadir componentes
		pIzquierdo1.add(izquierdo1);
		pIzquierdo1.add(tfizquierdo1);
		pIzquierdo2.add(izquierdo2);
		pIzquierdo2.add(tfizquierdo2);
		pIzquierdo3.add(izquierdo3);
		pIzquierdo3.add(tfizquierdo3);
		pIzquierdo4.add(izquierdo4);
		pIzquierdo4.add(tfizquierdo4);
		pIzquierdo5.add(izquierdo5);
		pIzquierdo5.add(tfizquierdo5);
		pIzquierdo6.add(izquierdo6);
		pIzquierdo6.add(tfizquierdo6);
		pIzquierdo7.add(izquierdo7);
		pIzquierdo7.add(tfizquierdo7);
		pIzquierdo8.add(izquierdo8);
		pIzquierdo8.add(tfizquierdo8);
		pIzquierdo9.add(izquierdo9);
		pIzquierdo9.add(tfizquierdo9);
		pIzquierdo10.add(izquierdo10);
		pIzquierdo10.add(tfizquierdo10);
		pIzquierdo11.add(izquierdo11);
		pIzquierdo11.add(tfizquierdo11);
		
		pIzquierdo.add(pIzquierdo1);
		pIzquierdo.add(pIzquierdo2);
		pIzquierdo.add(pIzquierdo3);
		pIzquierdo.add(pIzquierdo4);
		pIzquierdo.add(pIzquierdo5);
		pIzquierdo.add(pIzquierdo6);
		pIzquierdo.add(pIzquierdo7);
		pIzquierdo.add(pIzquierdo8);
		pIzquierdo.add(pIzquierdo9);
		pIzquierdo.add(pIzquierdo10);
		pIzquierdo.add(pIzquierdo11);
		
		getContentPane().add(pIzquierdo, BorderLayout.WEST);
		
		JButton bAnyadir= new JButton("Anadir");
		JButton bActualizar= new JButton("Actualizar");
		JButton bDelete= new JButton("Borrar");
		
		JPanel botonera= new JPanel();
		botonera.add(bAnyadir);
		botonera.add(bActualizar);
		botonera.add(bDelete);
		
		
		JScrollPane spCentral= new JScrollPane(tCentral);
		getContentPane().add(spCentral,BorderLayout.CENTER);
		getContentPane().add(botonera,BorderLayout.SOUTH);
		rellenaTablaUsuario();
		
		ArrayList<JLabel>listaLabel= new ArrayList<JLabel>();
		listaLabel.add(izquierdo1);
		listaLabel.add(izquierdo2);
		listaLabel.add(izquierdo3);
		listaLabel.add(izquierdo4);
		listaLabel.add(izquierdo5);
		listaLabel.add(izquierdo6);
		listaLabel.add(izquierdo7);
		listaLabel.add(izquierdo8);
		listaLabel.add(izquierdo9);
		listaLabel.add(izquierdo10);
		listaLabel.add(izquierdo11);
		
		listaTexto= new ArrayList<JTextField>();
		listaTexto.add(tfizquierdo1);
		listaTexto.add(tfizquierdo2);
		listaTexto.add(tfizquierdo3);
		listaTexto.add(tfizquierdo4);
		listaTexto.add(tfizquierdo5);
		listaTexto.add(tfizquierdo6);
		listaTexto.add(tfizquierdo7);
		listaTexto.add(tfizquierdo8);
		listaTexto.add(tfizquierdo9);
		listaTexto.add(tfizquierdo10);
		listaTexto.add(tfizquierdo11);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		for (int i=0;i<listaLabel.size();i++) {
			if (listaLabel.get(i).getText().contentEquals("")) {
				listaTexto.get(i).setEnabled(false);
			}else {
				listaTexto.get(i).setEnabled(true);
			}
		}
	this.addWindowListener(new WindowAdapter() {
	
		
		@Override
		public void windowClosed(WindowEvent e) {
			System.out.println("HOLA FUNCIONO");
			guardarDatos();
			dispose();			
		}
		
	
	});
			
			
		
		cargaDatos();
		
		cbOpciones.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String s= (String) e.getItem();
				if (s.contentEquals("Usuario")) {
					rellenaTablaUsuario();
				}else if (s.contentEquals("Equipo")) {
					rellenaTablaEquipo();
				}else if (s.contentEquals("Jornada")) {
					rellenaTablaJornada();
				}else if (s.contentEquals("Liga")) {
					rellenaLiga();
				}else if (s.contentEquals("Jugador")) {
					rellenaTablaJugador();
				}else if (s.contentEquals("Traspaso")) {
					rellenaTraspaso();
				}else if(s.contentEquals("Partido")) {
					rellenaPartido();
				}else if (s.contentEquals("Noticia")) {
					rellenaNoticia();
				}else if (s.contentEquals("Seleccion")) {
					rellenaSeleccion();
				}else if(s.contentEquals("LigaNot")) {
					rellenaLigaNot();
				}else if(s.contentEquals("EquipoNot")) {
					rellenaEquipoNot();
				}else if(s.contentEquals("UsuarioJugador")) {
					rellenaUsuarioJugador();
				}else if (s.contentEquals("UsuarioEquipo")) {
					rellenaUsuarioEquipo();
				}else if (s.contentEquals("UsuarioLiga")) {
					rellenaUsuarioLiga();
				}else if(s.contentEquals("PartidoJugador")) {
					rellenaPartidoJugador();
				}
				for (int i=0;i<listaLabel.size();i++) {
					if (listaLabel.get(i).getText().contentEquals("")) {
						listaTexto.get(i).setEnabled(false);
					}else {
						listaTexto.get(i).setEnabled(true);
					}
				}
			}
		});
	
	
		
		bAnyadir.addActionListener((ActionEvent e)->{
			String s=(String)cbOpciones.getSelectedItem();
			if (s.contentEquals("Usuario")) {
				insertarUsuario();
			}else if (s.contentEquals("Equipo")) {
				insertarEquipo();
			}else if (s.contentEquals("Jornada")) {
				insertarJornada();
			}else if (s.contentEquals("Liga")) {
				insertarLiga();
			}else if (s.contentEquals("Jugador")) {
				insertarJugador();
			}else if (s.contentEquals("Traspaso")) {
				insertarTraspaso();
			}else if(s.contentEquals("Partido")) {
				insertarPartido();
			}else if (s.contentEquals("Noticia")) {
				insertarNoticia();
			}else if (s.contentEquals("Seleccion")) {
				insertarSeleccion();
			}else if(s.contentEquals("LigaNot")) {
				insertarLigaNot();
			}else if(s.contentEquals("EquipoNot")) {
				insertarEquipoNot();
			}else if(s.contentEquals("UsuarioJugador")) {
				insertarUsuarioJugador();
			}else if (s.contentEquals("UsuarioEquipo")) {
				insertarUsuarioEquipo();
			}else if (s.contentEquals("UsuarioLiga")) {
				insertarUsuarioLiga();
			}else if(s.contentEquals("PartidoJugador")) {
				insertarPartidoJugador();
			}
		});
		
		
		bActualizar.addActionListener((ActionEvent e)->{
			String s=(String)cbOpciones.getSelectedItem();
			if (s.contentEquals("Usuario")) {
				actualizarUsuario();
			}else if (s.contentEquals("Equipo")) {
				actualizaEquipo();
			}else if (s.contentEquals("Jornada")) {
				actualizaJornada();
			}else if (s.contentEquals("Liga")) {
				actualizaLiga();
			}else if (s.contentEquals("Jugador")) {
				actualizaJugador();
			}else if (s.contentEquals("Traspaso")) {
				actualizaTraspaso();
			}else if(s.contentEquals("Partido")) {
				actualizaPartido();
			}else if (s.contentEquals("Noticia")) {
				actualizaNoticia();
			}else if (s.contentEquals("Seleccion")) {
				actualizaSeleccion();
			}else if(s.contentEquals("LigaNot")) {
				actualizaLigaNot();
			}else if(s.contentEquals("EquipoNot")) {
				actualizaEquipoNot();
			}else if(s.contentEquals("UsuarioJugador")) {
				actualizaUsuarioJugador();
			}else if (s.contentEquals("UsuarioEquipo")) {
				actualizaUsuarioEquipo();
			}else if (s.contentEquals("UsuarioLiga")) {
				actualizaUsuarioLiga();
			}else if(s.contentEquals("PartidoJugador")) {
				actualizaPartidoJugador();
			}
		});
		
		
		bDelete.addActionListener((ActionEvent e)->{
			String s=(String)cbOpciones.getSelectedItem();
			if (s.contentEquals("Usuario")) {
				deleteUsuario();
			}else if (s.contentEquals("Equipo")) {
				deleteEquipo();
			}else if (s.contentEquals("Jornada")) {
				deleteJorndada();
			}else if (s.contentEquals("Liga")) {
				deleteLiga();
			}else if (s.contentEquals("Jugador")) {
				deleteJugador();
			}else if (s.contentEquals("Traspaso")) {
				deleteTraspaso();
			}else if(s.contentEquals("Partido")) {
				deletePartido();
			}else if (s.contentEquals("Noticia")) {
				deleteNoticia();
			}else if (s.contentEquals("Seleccion")) {
				deleteSeleccion();
			}else if(s.contentEquals("LigaNot")) {
				deleteLigaNot();
			}else if(s.contentEquals("EquipoNot")) {
				deleteEquipoNot();
			}else if(s.contentEquals("UsuarioJugador")) {
				deleteUsuarioJugador();
			}else if (s.contentEquals("UsuarioEquipo")) {
				deleteUsuarioEquipo();
			}else if (s.contentEquals("UsuarioLiga")) {
				deleteUsuarioLiga();
			}else if(s.contentEquals("PartidoJugador")) {
				deletePartidoJugador();
			}
		});
		
	}
	
	public void deleteUsuarioJugador() {
		int indice=tCentral.getSelectedRow();
		Usuario u= new Usuario();
		Jugador j= new Jugador();
		if (indice!=-1) {
			String nombreJug=(String)tCentral.getModel().getValueAt(indice, 0);
			String nombreEquipo=(String)tCentral.getModel().getValueAt(indice, 1);
			String nomCorreo=(String)tCentral.getModel().getValueAt(indice, 2);
			j.setNombre(nombreJug);
			j.setEquipo(new Equipo(nombreEquipo));
			u.setCorreoElec(nomCorreo);
			BD.deleteJugadorUsuario(u, j);
			rellenaUsuarioJugador();
		}
	}
	
	public void deleteUsuarioEquipo() {
		int indice=tCentral.getSelectedRow();
		Usuario u= new Usuario();
		Equipo e= new Equipo();
		if (indice!=-1) {
			String nombreEquipo=(String)tCentral.getModel().getValueAt(indice, 0);
			String nomCorreo=(String)tCentral.getModel().getValueAt(indice, 1);
			e.setNombre(nombreEquipo);
			u.setCorreoElec(nomCorreo);
			BD.deleteEquipoUsuario(u, e);
			rellenaUsuarioEquipo();
		}
	}
	
	public void deleteUsuarioLiga() {
		int indice=tCentral.getSelectedRow();
		Usuario u= new Usuario();
		Liga l= new Liga();
		if (indice!=-1) {
			String nombreEquipo=(String)tCentral.getModel().getValueAt(indice, 0);
			String nomCorreo=(String)tCentral.getModel().getValueAt(indice, 1);
			l.setNombre(nombreEquipo);
			u.setCorreoElec(nomCorreo);
			BD.deleteLigaUsuario(u, l);
			rellenaUsuarioLiga();
		}
	}
	
	
	public void deleteEquipoNot() {
		int indice=tCentral.getSelectedRow();
		Equipo e= new Equipo();
		Noticia n= new Noticia();
		if (indice!=-1) {
			String nomLiga=(String)tCentral.getModel().getValueAt(indice, 0);
			String titulo=(String)tCentral.getModel().getValueAt(indice, 1);
			e.setNombre(nomLiga);
			n.setTitulo(titulo);
			BD.deleteEquipoNot(n, e);
			rellenaEquipoNot();
		}
	}
	
	
	
	public void deleteLigaNot() {
		int indice=tCentral.getSelectedRow();
		Liga l= new Liga();
		Noticia n= new Noticia();
		if (indice!=-1) {
			String nomLiga=(String)tCentral.getModel().getValueAt(indice, 0);
			String titulo=(String)tCentral.getModel().getValueAt(indice, 1);
			l.setNombre(nomLiga);
			n.setTitulo(titulo);
			BD.deleteLigaNot(n, l);
			rellenaLigaNot();
		}
	}
	
	
	public void deletePartidoJugador() {
		int indice=tCentral.getSelectedRow();
		Partido p= new Partido();
		Jugador j= new Jugador();
		if (indice!=-1) {
			String nombre=(String)tCentral.getModel().getValueAt(indice, 0);
			String nomEquipoJugador=(String)tCentral.getModel().getValueAt(indice, 1);
			String nomEquipoLocal=(String)tCentral.getModel().getValueAt(indice, 2);
			String nomEquipoVisitante=(String)tCentral.getModel().getValueAt(indice, 3);
			Long fecha=(Long)tCentral.getModel().getValueAt(indice, 8);
			j.setNombre(nombre);
			j.setEquipo(new Equipo(nomEquipoJugador));
			p.setLocal(new Equipo(nomEquipoLocal));
			p.setVisitante(new Equipo(nomEquipoVisitante));
			p.setFecha(new Date(fecha));
			BD.deletePartidoJugador(p, j);
			rellenaPartidoJugador();
		}
	}
	
	
	public void deleteUsuario() {
		int indice=tCentral.getSelectedRow();
		Usuario u= new Usuario();
		if (indice!=-1) {
			String nombre=(String)tCentral.getModel().getValueAt(indice, 0);
			u.setNombre(nombre);
			String correo=(String)tCentral.getModel().getValueAt(indice, 1);
			u.setCorreoElec(correo);
			String contrasena=(String)tCentral.getModel().getValueAt(indice, 2);
			u.setContrasena(contrasena);
			BD.deleteUsuario(u);
			rellenaTablaUsuario();
			
		}
	}
	
	public void deleteEquipo() {
		int indice=tCentral.getSelectedRow();
		Equipo e= new Equipo();
		if (indice!=-1) {
			String nombre=(String)tCentral.getModel().getValueAt(indice, 0);
			String img=(String)tCentral.getModel().getValueAt(indice, 1);
			int puntos=(Integer)tCentral.getModel().getValueAt(indice, 2);
			int golesAFavor=(Integer)tCentral.getModel().getValueAt(indice, 3);
			int golesEnContra=(Integer)tCentral.getModel().getValueAt(indice,4 );
			String liga= (String)tCentral.getModel().getValueAt(indice,5);
			e.setNombre(nombre);
			e.setImagen(img);
			e.setPuntos(puntos);
			e.setGolesAFavor(golesAFavor);
			e.setGolesEnContra(golesEnContra);
			e.setLiga(new Liga(liga));
			BD.deleteEquipo(e);
			rellenaTablaEquipo();
		}
	}
	
	
	private void deleteJorndada() {
		int indice=tCentral.getSelectedRow();
		Jornada j= new Jornada();
		if (indice!=-1) {
			int numJornada=(Integer)tCentral.getModel().getValueAt(indice, 0);
			long fInicio=(Long)tCentral.getModel().getValueAt(indice, 1);
			long fFin=(Long)tCentral.getModel().getValueAt(indice, 2);
			String nomLiga=(String)tCentral.getModel().getValueAt(indice, 3);
			j.setNumJornada(numJornada);
			j.setFechaInicio(new Date(fInicio));
			j.setFechaFinal(new Date(fFin));
			j.setLiga(new Liga(nomLiga));
			BD.deleteJornada(j);
			rellenaTablaJornada();
		}
	}
	
	private void deleteLiga() {
		int indice=tCentral.getSelectedRow();
		Liga l= new Liga();
		if (indice!=-1) {
			String nombre=(String)tCentral.getModel().getValueAt(indice, 0);
			String img=(String)tCentral.getModel().getValueAt(indice, 1);
			l.setNombre(nombre);
			l.setImagen(img);
			BD.deleteLiga(l);
			rellenaLiga();
		}
	}
	
	
	private void deleteJugador() {
		Jugador j= new Jugador();
		int indice=tCentral.getSelectedRow();
		if (indice!=-1) {
			String nombre=(String)tCentral.getModel().getValueAt(indice, 0);
			String pais=(String)tCentral.getModel().getValueAt(indice, 1);
			String posicion=(String)tCentral.getModel().getValueAt(indice, 2);
			int edad=(Integer)tCentral.getModel().getValueAt(indice, 3);
			int numGoles=(Integer)tCentral.getModel().getValueAt(indice, 4);
			int numDorsal=(Integer)tCentral.getModel().getValueAt(indice, 5);
			int numAmarillas=(Integer)tCentral.getModel().getValueAt(indice, 6);
			int numRojas=(Integer)tCentral.getModel().getValueAt(indice, 7);
			int numAsistencias=(Integer)tCentral.getModel().getValueAt(indice, 8);
			String equipo=(String)tCentral.getModel().getValueAt(indice, 9);
			String img=(String)tCentral.getModel().getValueAt(indice, 10);
			j.setNombre(nombre);
			j.setPais(pais);
			j.setPosicion(posicion);
			j.setEdad(edad);
			j.setNumGoles(numGoles);
			j.setDorsal(numDorsal);
			j.setNumAmarillas(numAmarillas);
			j.setNumRojas(numRojas);
			j.setNumAsistencias(numAsistencias);
			j.setEquipo(new Equipo(equipo));
			j.setImagen(img);
			BD.deleteJugador(j);
			rellenaTablaJugador();
		}
	}
	
	private void deletePartido() {
		Partido p= new Partido();
		int indice=tCentral.getSelectedRow();
		if (indice!=-1) {
				String local=(String)tCentral.getModel().getValueAt(indice, 0);
				String visitante=(String)tCentral.getModel().getValueAt(indice, 1);
				Long fecha=(Long)tCentral.getModel().getValueAt(indice, 2);
				int golesLocal=(Integer)tCentral.getModel().getValueAt(indice, 3);
				int golesVisitante=(Integer)tCentral.getModel().getValueAt(indice, 6);
				int numJornada=(Integer)tCentral.getModel().getValueAt(indice, 4);
				String nomLiga=(String)tCentral.getModel().getValueAt(indice, 5);
				p.setLocal(new Equipo(local));
				p.setVisitante(new Equipo(visitante));
				p.setFecha(new Date(fecha));
				p.setGolesLocal(golesLocal);
				p.setGolesVisitante(golesVisitante);
				p.setJornada(new Jornada(new Liga(nomLiga), numJornada));
				BD.deletePartido(p);
				rellenaPartido();
			
		}
	}
	
	
	private void deleteNoticia() {
		Noticia n= new Noticia();
		int indice=tCentral.getSelectedRow();
		if (indice!=-1) {
			String titulo=(String)tCentral.getModel().getValueAt(indice, 0);
			String img=(String)tCentral.getModel().getValueAt(indice, 1);
			String cuerpo=(String)tCentral.getModel().getValueAt(indice, 2);
			String fuente=(String)tCentral.getModel().getValueAt(indice, 3);
			n.setTitulo(titulo);
			n.setImagen(img);
			n.setCuerpo(cuerpo);
			n.setFuente(fuente);
			BD.deleteNoticia(n);
			rellenaNoticia();
		}
	}
	
	private void deleteTraspaso() {
		Traspaso t= new Traspaso();
		int indice=tCentral.getSelectedRow();
		if (indice!=-1) {
			String nomJugador= (String)tCentral.getModel().getValueAt(indice, 0);
			String nomEquipoVen=(String)tCentral.getModel().getValueAt(indice, 1);
			String nomEquipoCom=(String)tCentral.getModel().getValueAt(indice, 2);
			long precio=(Long)tCentral.getModel().getValueAt(indice, 3);
			byte grado=(Byte)tCentral.getModel().getValueAt(indice, 4);
			long fecha=(Long)tCentral.getModel().getValueAt(indice, 5);
			t.setJugador(new Jugador(nomJugador));
			t.setVendedor(new Equipo(nomEquipoVen));
			t.setEquipo(new Equipo(nomEquipoCom));
			t.setPrecio(precio);
			t.setGrado(grado);
			t.setFecha(new Date(fecha));
			BD.deleteTraspaso(t);
			rellenaTraspaso();
		}

	}
	
	private void deleteSeleccion() {
		int indice=tCentral.getSelectedRow();
		SeleccionNacional sel= new SeleccionNacional();
		if (indice!=-1) {
			String nombre=(String)tCentral.getModel().getValueAt(indice, 0);
			String img=(String)tCentral.getModel().getValueAt(indice, 1);
			int puntos=(Integer)tCentral.getModel().getValueAt(indice, 2);
			int golesAFavor=(Integer)tCentral.getModel().getValueAt(indice, 3);
			int golesEnContra=(Integer)tCentral.getModel().getValueAt(indice,4 );
			String liga= (String)tCentral.getModel().getValueAt(indice,5);
			sel.setNombre(nombre);
			sel.setImagen(img);
			sel.setPuntos(puntos);
			sel.setGolesAFavor(golesAFavor);
			sel.setGolesEnContra(golesEnContra);
			sel.setLiga(new Liga(liga));
			BD.deleteSeleccion(sel);
			rellenaSeleccion();
		}
	}
	
	
	
	private void actualizaUsuarioJugador() {
		int indice=tCentral.getSelectedRow();
		boolean vacio=false;
		for (int i=0;i<3;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		Jugador j= new Jugador();
		Usuario u= new Usuario();
		if (!vacio && indice!=-1) {
			String nombreJug=(String)tCentral.getModel().getValueAt(indice, 0);
			String nombreEquipo=(String)tCentral.getModel().getValueAt(indice, 1);
			String nomCorreo=(String)tCentral.getModel().getValueAt(indice, 2);
			j.setNombre(nombreJug);
			j.setEquipo(new Equipo(nombreEquipo));
			u.setCorreoElec(nomCorreo);
			BD.updateUsuarioJugador(u, j, tfizquierdo3.getText(), tfizquierdo1.getText(), tfizquierdo2.getText());
			rellenaUsuarioJugador();
		}
	}
	
	private void actualizaUsuarioEquipo() {
		int indice=tCentral.getSelectedRow();
		boolean vacio=false;
		for (int i=0;i<2;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		Equipo e= new Equipo();
		Usuario u= new Usuario();
		if (!vacio && indice!=-1) {
			String nombreEquipo=(String)tCentral.getModel().getValueAt(indice, 0);
			String nomCorreo=(String)tCentral.getModel().getValueAt(indice, 1);
			e.setNombre(nombreEquipo);
			u.setCorreoElec(nomCorreo);
			BD.updateUsuarioEquipo(u, e, tfizquierdo2.getText(), tfizquierdo1.getText());
			rellenaUsuarioEquipo();
		}
	}
	
	
	private void actualizaUsuarioLiga() {
		int indice=tCentral.getSelectedRow();
		boolean vacio=false;
		for (int i=0;i<2;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		Liga l= new Liga();
		Usuario u= new Usuario();
		if (!vacio && indice!=-1) {
			String nombreEquipo=(String)tCentral.getModel().getValueAt(indice, 0);
			String nomCorreo=(String)tCentral.getModel().getValueAt(indice, 1);
			l.setNombre(nombreEquipo);
			u.setCorreoElec(nomCorreo);
			BD.updateUsuarioLiga(u, l, tfizquierdo2.getText(), tfizquierdo1.getText());
			rellenaUsuarioLiga();
		}
	}
	
	private void actualizaEquipoNot() {
		Equipo e= new Equipo();
		Noticia n= new Noticia();
		int indice=tCentral.getSelectedRow();
		boolean vacio=false;
		for (int i=0;i<2;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio && indice!=-1) {
			String nomLiga=(String)tCentral.getModel().getValueAt(indice, 0);
			String titulo=(String)tCentral.getModel().getValueAt(indice, 1);
			e.setNombre(nomLiga);
			n.setTitulo(titulo);
			BD.updateEquipoNot(e, n, tfizquierdo1.getText(), tfizquierdo2.getText());
			rellenaLigaNot();
		}
	}
	
	private void actualizaLigaNot() {
		Liga l= new Liga();
		Noticia n= new Noticia();
		int indice=tCentral.getSelectedRow();
		boolean vacio=false;
		for (int i=0;i<2;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio && indice!=-1) {
			String nomLiga=(String)tCentral.getModel().getValueAt(indice, 0);
			String titulo=(String)tCentral.getModel().getValueAt(indice, 1);
			l.setNombre(nomLiga);
			n.setTitulo(titulo);
			BD.updateLigaNot(l, n, tfizquierdo1.getText(), tfizquierdo2.getText());
			rellenaLigaNot();
		}
	}

	private void actualizaPartidoJugador() {
		// TODO Auto-generated method stub
		Partido p= new Partido();
		Jugador j= new Jugador();
		int indice=tCentral.getSelectedRow();
		boolean vacio=false;
		for (int i=0;i<9;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio && indice!=-1) {
			try {
				String nombre=(String)tCentral.getModel().getValueAt(indice, 0);
				String nomEquipoJugador=(String)tCentral.getModel().getValueAt(indice, 1);
				String nomEquipoLocal=(String)tCentral.getModel().getValueAt(indice, 2);
				String nomEquipoVisitante=(String)tCentral.getModel().getValueAt(indice, 3);
				Long fecha=(Long)tCentral.getModel().getValueAt(indice, 8);
				j.setNombre(nombre);
				j.setEquipo(new Equipo(nomEquipoJugador));
				p.setLocal(new Equipo(nomEquipoLocal));
				p.setVisitante(new Equipo(nomEquipoVisitante));
				p.setFecha(new Date(fecha));
				BD.updatePartidoJugador(p, j, tfizquierdo1.getText(), tfizquierdo2.getText(),
						tfizquierdo3.getText(), tfizquierdo4.getText(), Integer.parseInt(tfizquierdo5.getText()),
						Integer.parseInt(tfizquierdo6.getText()), Integer.parseInt(tfizquierdo7.getText()),
						Integer.parseInt(tfizquierdo8.getText()), new Date(Long.parseLong(tfizquierdo9.getText())));
				rellenaPartidoJugador();
				
			}catch(NumberFormatException e) {
				System.out.println("ERROR EN LA CONVERSION NUMERICA");
			}
		}else{
			System.out.println("falla aqui "+ indice+ " "+ vacio);
		}
	}
	
	
	

	private void actualizaSeleccion() {
		SeleccionNacional sel= new SeleccionNacional();
		int indice=tCentral.getSelectedRow();
		if (indice!=-1 && !tfizquierdo1.getText().contentEquals("") && !tfizquierdo2.getText().contentEquals("")&& 
				!tfizquierdo3.getText().contentEquals("")) {
			String nombre=(String)tCentral.getModel().getValueAt(indice, 0);
			String img=(String)tCentral.getModel().getValueAt(indice, 1);
			int puntos=(Integer)tCentral.getModel().getValueAt(indice, 2);
			int golesAFavor=(Integer)tCentral.getModel().getValueAt(indice, 3);
			int golesEnContra=(Integer)tCentral.getModel().getValueAt(indice,4 );
			String liga= (String)tCentral.getModel().getValueAt(indice,5);
			sel.setNombre(nombre);
			sel.setImagen(img);
			sel.setPuntos(puntos);
			sel.setGolesAFavor(golesAFavor);
			sel.setGolesEnContra(golesEnContra);
			sel.setLiga(new Liga(liga));
			try{
				BD.updateSeleccion(sel, tfizquierdo1.getText(), tfizquierdo2.getText(), Integer.parseInt(tfizquierdo3.getText()), 
					Integer.parseInt(tfizquierdo4.getText()),Integer.parseInt(tfizquierdo5.getText()), tfizquierdo6.getText());
				rellenaSeleccion();
			}catch(NumberFormatException ex) {
				System.out.println("ERROR EN CONVERSION NUMERICA");
			}
			
		}	
		
	}

	private void actualizaNoticia() {
		Noticia n= new Noticia();
		int indice=tCentral.getSelectedRow();
		boolean vacio=false;
		for (int i=0;i<4;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio && indice!=-1) {
			String titulo=(String)tCentral.getModel().getValueAt(indice, 0);
			String img=(String)tCentral.getModel().getValueAt(indice, 1);
			String cuerpo=(String)tCentral.getModel().getValueAt(indice, 2);
			String fuente=(String)tCentral.getModel().getValueAt(indice, 3);
			n.setTitulo(titulo);
			n.setImagen(img);
			n.setCuerpo(cuerpo);
			n.setFuente(fuente);
			BD.updateNoticia(n, tfizquierdo1.getText(), tfizquierdo2.getText(), tfizquierdo3.getText(), tfizquierdo4.getText());
			rellenaNoticia();
			
		}
		
	}

	private void actualizaPartido() {
		Partido p= new Partido();
		int indice=tCentral.getSelectedRow();
		boolean vacio=false;
		for (int i=0;i<7;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) { 	
				System.out.println("esta vacio");
				vacio=true;
			}
		}
		if (!vacio && indice!=-1) {
			try {
				String local=(String)tCentral.getModel().getValueAt(indice, 0);
				String visitante=(String)tCentral.getModel().getValueAt(indice, 1);
				Long fecha=(Long)tCentral.getModel().getValueAt(indice, 2);
				int golesLocal=(Integer)tCentral.getModel().getValueAt(indice, 3);
				int golesVisitante=(Integer)tCentral.getModel().getValueAt(indice, 6);
				int numJornada=(Integer)tCentral.getModel().getValueAt(indice, 4);
				String nomLiga=(String)tCentral.getModel().getValueAt(indice, 5);
				p.setLocal(new Equipo(local));
				p.setVisitante(new Equipo(visitante));
				p.setFecha(new Date(fecha));
				p.setGolesLocal(golesLocal);
				p.setGolesVisitante(golesVisitante);
				p.setJornada(new Jornada(new Liga(nomLiga), numJornada));
				BD.updatePartido(p, tfizquierdo1.getText(), tfizquierdo2.getText(), new Date(Long.parseLong(tfizquierdo3.getText())),
						Integer.parseInt(tfizquierdo4.getText()), Integer.parseInt(tfizquierdo5.getText()),tfizquierdo6.getText() , 
						Integer.parseInt(tfizquierdo7.getText()));
				rellenaPartido();
			}catch(NumberFormatException e) {
				System.out.println("Error en la conversion numerica no se ha anyadido");
			}
		}else {
			System.out.println("falla aqui");
		}
		
		
	}

	private void actualizaTraspaso() {
		Traspaso t= new Traspaso();
		int indice=tCentral.getSelectedRow();
		boolean vacio=false;
		for (int i=0;i<6;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio && indice!=-1) {
			try {
				String nomJugador= (String)tCentral.getModel().getValueAt(indice, 0);
				String nomEquipoVen=(String)tCentral.getModel().getValueAt(indice, 1);
				String nomEquipoCom=(String)tCentral.getModel().getValueAt(indice, 2);
				long precio=(Long)tCentral.getModel().getValueAt(indice, 3);
				byte grado=(Byte)tCentral.getModel().getValueAt(indice, 4);
				long fecha=(Long)tCentral.getModel().getValueAt(indice, 5);
				t.setJugador(new Jugador(nomJugador));
				t.setVendedor(new Equipo(nomEquipoVen));
				t.setEquipo(new Equipo(nomEquipoCom));
				t.setPrecio(precio);
				t.setGrado(grado);
				t.setFecha(new Date(fecha));
				BD.updateTraspaso(t, tfizquierdo1.getText(), tfizquierdo2.getText(), tfizquierdo3.getText(),
						Long.parseLong(tfizquierdo4.getText()), Byte.parseByte(tfizquierdo5.getText()), new Date(Long.parseLong(tfizquierdo6.getText())));
				rellenaTraspaso();
			}catch(NumberFormatException e) {
				System.out.println("Error en la conversion numerica no se ha anyadido");
			}
		}
	}
	
	
	
	private void actualizaJugador() {
		Jugador j= new Jugador();
		int indice=tCentral.getSelectedRow();
		boolean vacio=false;
		for (int i=0;i<listaTexto.size();i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio && indice!=-1) {
			try {
				String nombre=(String)tCentral.getModel().getValueAt(indice, 0);
				String pais=(String)tCentral.getModel().getValueAt(indice, 1);
				String posicion=(String)tCentral.getModel().getValueAt(indice, 2);
				int edad=(Integer)tCentral.getModel().getValueAt(indice, 3);
				int numGoles=(Integer)tCentral.getModel().getValueAt(indice, 4);
				int numDorsal=(Integer)tCentral.getModel().getValueAt(indice, 5);
				int numAmarillas=(Integer)tCentral.getModel().getValueAt(indice, 6);
				int numRojas=(Integer)tCentral.getModel().getValueAt(indice, 7);
				int numAsistencias=(Integer)tCentral.getModel().getValueAt(indice, 8);
				String equipo=(String)tCentral.getModel().getValueAt(indice, 9);
				String img=(String)tCentral.getModel().getValueAt(indice, 10);
				j.setNombre(nombre);
				j.setPais(pais);
				j.setPosicion(posicion);
				j.setEdad(edad);
				j.setNumGoles(numGoles);
				j.setDorsal(numDorsal);
				j.setNumAmarillas(numAmarillas);
				j.setNumRojas(numRojas);
				j.setNumAsistencias(numAsistencias);
				j.setEquipo(new Equipo(equipo));
				j.setImagen(img);
				BD.updateJugador(j, tfizquierdo1.getText(), tfizquierdo2.getText(), tfizquierdo3.getText(),
						Integer.parseInt(tfizquierdo4.getText()), Integer.parseInt(tfizquierdo5.getText()), Integer.parseInt(tfizquierdo6.getText())
						, Integer.parseInt(tfizquierdo7.getText()), Integer.parseInt(tfizquierdo8.getText()),
						Integer.parseInt(tfizquierdo9.getText()), tfizquierdo10.getText(), tfizquierdo11.getText());
				rellenaTablaJugador();
				
			}catch(NumberFormatException ex) {
				System.out.println("error en la conversion numerica");
			}
		}
	}

	private void actualizarUsuario() {
		Usuario u= new Usuario();
		int indice=tCentral.getSelectedRow();
		if (indice!=-1 && !tfizquierdo1.getText().contentEquals("") && !tfizquierdo2.getText().contentEquals("")&& 
				!tfizquierdo3.getText().contentEquals("")) {
			String nombre=(String)tCentral.getModel().getValueAt(indice, 0);
			u.setNombre(nombre);
			String correo=(String)tCentral.getModel().getValueAt(indice, 1);
			u.setCorreoElec(correo);
			String contrasena=(String)tCentral.getModel().getValueAt(indice, 2);
			u.setContrasena(contrasena);
			BD.updateUsuario(u, tfizquierdo1.getText(), tfizquierdo2.getText(), tfizquierdo3.getText());
			rellenaTablaUsuario();
		}	
	}
	
	private void actualizaEquipo() {
		Equipo e= new Equipo();
		int indice=tCentral.getSelectedRow();
		if (indice!=-1 && !tfizquierdo1.getText().contentEquals("") && !tfizquierdo2.getText().contentEquals("")&& 
				!tfizquierdo3.getText().contentEquals("")) {
			String nombre=(String)tCentral.getModel().getValueAt(indice, 0);
			String img=(String)tCentral.getModel().getValueAt(indice, 1);
			int puntos=(Integer)tCentral.getModel().getValueAt(indice, 2);
			int golesAFavor=(Integer)tCentral.getModel().getValueAt(indice, 3);
			int golesEnContra=(Integer)tCentral.getModel().getValueAt(indice,4 );
			String liga= (String)tCentral.getModel().getValueAt(indice,5);
			e.setNombre(nombre);
			e.setImagen(img);
			e.setPuntos(puntos);
			e.setGolesAFavor(golesAFavor);
			e.setGolesEnContra(golesEnContra);
			e.setLiga(new Liga(liga));
			try{
				BD.updateEquipo(e, tfizquierdo1.getText(), tfizquierdo2.getText(), Integer.parseInt(tfizquierdo3.getText()), 
					Integer.parseInt(tfizquierdo4.getText()),Integer.parseInt(tfizquierdo5.getText()), tfizquierdo6.getText());
				rellenaTablaEquipo();
			}catch(NumberFormatException ex) {
				System.out.println("ERROR EN CONVERSION NUMERICA");
			}
			
		}	
	}
	
	
	private void actualizaLiga() {
		Liga l= new Liga();
		int indice=tCentral.getSelectedRow();
		if (indice!=-1 && !tfizquierdo1.getText().contentEquals("") && !tfizquierdo2.getText().contentEquals("")) {
			String nombre=(String)tCentral.getModel().getValueAt(indice, 0);
			String img=(String)tCentral.getModel().getValueAt(indice, 1);
			l.setNombre(nombre);
			l.setImagen(img);
			BD.updateLiga(l, tfizquierdo1.getText(), tfizquierdo2.getText());
			rellenaLiga();
		}
		
	}
	
	private void actualizaJornada() {
		Jornada j= new Jornada();
		int indice=tCentral.getSelectedRow();
		if (indice!=-1 &&!tfizquierdo1.getText().contentEquals("") && !tfizquierdo2.getText().contentEquals("")&& 
				!tfizquierdo3.getText().contentEquals("") && !tfizquierdo4.getText().contentEquals("") ) {
			int numJornada=(Integer)tCentral.getModel().getValueAt(indice, 0);
			long fInicio=(Long)tCentral.getModel().getValueAt(indice, 1);
			long fFin=(Long)tCentral.getModel().getValueAt(indice, 2);
			String nomLiga=(String)tCentral.getModel().getValueAt(indice, 3);
			j.setNumJornada(numJornada);
			j.setFechaInicio(new Date(fInicio));
			j.setFechaFinal(new Date(fFin));
			j.setLiga(new Liga(nomLiga));
			try {
				BD.updateJornada(j, Integer.parseInt(tfizquierdo1.getText()), new Date(Long.parseLong(tfizquierdo2.getText())),
						new Date(Long.parseLong(tfizquierdo3.getText())), tfizquierdo4.getText());
				rellenaTablaJornada();
			}catch(NumberFormatException ex) {
				System.out.println("ERROR EN LA CONVERSION NUMERICA");
			}
		}
	}
	
	//TODO actualizar el resto de tablas
	//TODO diseñar actualizacion en BD tablas n a m
	//TODO diseñar Properties
	//TODO DELETE 
	



	private void insertarUsuarioLiga(){
		boolean vacio=false;
		for (int i=0;i<2;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		Liga l= new Liga();
		Usuario u= new Usuario();
		if (!vacio) {
			l.setNombre(tfizquierdo1.getText());
			u.setCorreoElec(tfizquierdo2.getText());
			BD.insertarUsuarioLiga(l, u);
			rellenaUsuarioLiga();
		}
	}
	
	
	
	private void insertarUsuarioEquipo(){
		boolean vacio=false;
		for (int i=0;i<2;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		Equipo e= new Equipo();
		Usuario u= new Usuario();
		if (!vacio) {
			e.setNombre(tfizquierdo1.getText());
			u.setCorreoElec(tfizquierdo2.getText());
			BD.insertarUsuarioEquipo(e, u);
			rellenaUsuarioEquipo();
		}
	}
	
	
	
	
	private void insertarUsuarioJugador(){
		boolean vacio=false;
		for (int i=0;i<3;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		Jugador j= new Jugador();
		Usuario u= new Usuario();
		if (!vacio) {
			j.setNombre(tfizquierdo1.getText());
			j.setEquipo(new Equipo(tfizquierdo2.getText()));
			u.setCorreoElec(tfizquierdo3.getText());
			BD.insertarUsuarioJugador(j, u);
			rellenaUsuarioJugador();
		}
	}
	
	
	/*
	 * izquierdo1.setText("nomJugador");
		izquierdo2.setText("nomEquipoJugador");
		izquierdo3.setText("nomEquipoLocal");
		izquierdo4.setText("nomEquipoVisitante");
		izquierdo5.setText("numGoles");
		izquierdo6.setText("numAsistencias");
		izquierdo7.setText("numRojas");
		izquierdo8.setText("numAmarillas");
		izquierdo9.setText("fecha");
		izquierdo10.setText("");
		izquierdo11.setText("");
		tCentral.setModel(mPartidoJugador);
	 */
	private void insertarPartidoJugador() {
		Partido p= new Partido();
		Jugador j= new Jugador();
		boolean vacio=false;
		for (int i=0;i<9;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio) {
			try {
				j.setNombre(tfizquierdo1.getText());
				j.setEquipo(new Equipo(tfizquierdo2.getText()));
				p.setLocal(new Equipo(tfizquierdo3.getText()));
				p.setVisitante(new Equipo(tfizquierdo4.getText()));
				int numGoles=Integer.parseInt(tfizquierdo5.getText());
				int numAsistencias= Integer.parseInt(tfizquierdo6.getText());
				int numRojas= Integer.parseInt(tfizquierdo7.getText());
				int numAmarillas= Integer.parseInt(tfizquierdo8.getText());
				p.setFecha(new Date(Long.parseLong(tfizquierdo9.getText())));
				BD.insertarPartidoJugador(p, j, numGoles, numAsistencias, numRojas, numAmarillas);
				rellenaPartidoJugador();
				
			}catch(NumberFormatException e) {
				System.out.println("ERROR EN LA CONVERSION NUMERICA");
			}
		}
	}
	
	
	private void insertarEquipoNot() {
		Equipo e= new Equipo();
		Noticia n= new Noticia();
		boolean vacio=false;
		for (int i=0;i<2;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio) {
			e.setNombre(tfizquierdo1.getText());
			n.setTitulo(tfizquierdo2.getText());
			BD.insertarEquipoNot(e, n);
			rellenaEquipoNot();
		}
	}
	
	/*
	 * izquierdo1.setText("nomLiga");
		izquierdo2.setText("titulo");
		izquierdo3.setText("");
		izquierdo4.setText("");
		izquierdo5.setText("");
		izquierdo6.setText("");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	 */
	private void insertarLigaNot() {
		Liga l= new Liga();
		Noticia n= new Noticia();
		boolean vacio=false;
		for (int i=0;i<2;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio) {
			l.setNombre(tfizquierdo1.getText());
			n.setTitulo(tfizquierdo2.getText());
			BD.insertarLigaNot(l, n);
			rellenaLigaNot();
		}
	}
	
	
	
	/*
	 * izquierdo1.setText("local");
		izquierdo2.setText("visitante");
		izquierdo3.setText("fecha");
		izquierdo4.setText("golesLocal");
		izquierdo5.setText("numJornada");
		izquierdo6.setText("nomLiga");
		izquierdo7.setText("golesVisitante");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	 */
	
	private void insertarSeleccion() {
		SeleccionNacional sel= new SeleccionNacional();
		boolean vacio=false;
		for (int i=0;i<6;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (vacio==false) {
			try {
				sel.setNombre(tfizquierdo1.getText());
				sel.setImagen(tfizquierdo2.getText());
				sel.setPuntos(Integer.parseInt(tfizquierdo3.getText()));
				sel.setGolesAFavor(Integer.parseInt(tfizquierdo4.getText()));
				sel.setGolesEnContra(Integer.parseInt(tfizquierdo5.getText()));
				sel.setLiga(new Liga(tfizquierdo6.getText()));
				BD.insertarSeleccion(sel);
				rellenaSeleccion();
			}catch(NumberFormatException ex) {
				System.out.println("No se ha anyadido por fallo en conversion numerica");
			}
		}
	}
	
	
	
	private void insertarPartido() {
		Partido p= new Partido();
		boolean vacio=false;
		for (int i=0;i<7;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio) {
			try {
				p.setLocal(new Equipo(tfizquierdo1.getText()));
				p.setVisitante(new Equipo(tfizquierdo2.getText()));
				p.setFecha(new Date(Long.parseLong(tfizquierdo3.getText())));
				p.setGolesLocal(Integer.parseInt(tfizquierdo4.getText()));
				p.setGolesVisitante(Integer.parseInt(tfizquierdo7.getText()));
				int numJornada=Integer.parseInt(tfizquierdo5.getText());
				p.setJornada(new Jornada(new Liga(tfizquierdo6.getText()), numJornada));
				BD.insertarPartido(p);
				rellenaPartido();
			}catch(NumberFormatException e) {
				System.out.println("Error en la conversion numerica no se ha anyadido");
			}
		}
		
	}

	/*
	 * 	
		izquierdo1.setText("nomJugador");
		izquierdo2.setText("nomEquipoVendedor");
		izquierdo3.setText("nomEquipoComprador");
		izquierdo4.setText("precio");
		izquierdo5.setText("grado");
		izquierdo6.setText("fecha");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	 */
	private void insertarTraspaso() {
		Traspaso t= new Traspaso();
		boolean vacio=false;
		for (int i=0;i<6;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio) {
			try {
				t.setJugador(new Jugador(tfizquierdo1.getText()));
				t.setVendedor(new Equipo(tfizquierdo2.getText()));
				t.setEquipo(new Equipo(tfizquierdo3.getText()));
				t.setPrecio(Long.parseLong(tfizquierdo4.getText()));
				t.setGrado(Byte.parseByte(tfizquierdo5.getText()));
				t.setFecha(new Date(Long.parseLong(tfizquierdo6.getText())));
				BD.insertarTraspaso(t);
				rellenaTraspaso();
			}catch(NumberFormatException e) {
				System.out.println("Error en la conversion numerica no se ha anyadido");
			}
		}
	}
	
	/*
	 * 	tCentral.setModel(mNoticia);
		izquierdo1.setText("titulo");
		izquierdo2.setText("img");
		izquierdo3.setText("cuerpo");
		izquierdo4.setText("fuente");
		izquierdo5.setText("");
		izquierdo6.setText("");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	 */
	private void insertarNoticia() {
		Noticia n= new Noticia();
		boolean vacio=false;
		for (int i=0;i<4;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio) {
			n.setTitulo(tfizquierdo1.getText());
			n.setImagen(tfizquierdo2.getText());
			n.setCuerpo(tfizquierdo3.getText());
			n.setFuente(tfizquierdo4.getText());
			BD.insertarNoticia(n);
			rellenaNoticia();
			
		}
		
	}

	private void insertarLiga() {
		Liga l= new Liga();
		if (!tfizquierdo1.getText().contentEquals("") && !tfizquierdo2.getText().contentEquals("")) {
			l.setNombre(tfizquierdo1.getText());
			l.setImagen(tfizquierdo2.getText());
			BD.insertarLiga(l);
			rellenaLiga();
		}
		
	}
	
	
	/*
	 * izquierdo1.setText("nombre");
		izquierdo2.setText("pais");
		izquierdo3.setText("posicion");
		izquierdo4.setText("edad");
		izquierdo5.setText("numGoles");
		izquierdo6.setText("dorsal");
		izquierdo7.setText("numAmarillas");
		izquierdo8.setText("numRojas");
		izquierdo9.setText("numAsistencias");
		izquierdo10.setText("nomEquipo");
		izquierdo11.setText("img");
	 */
	private void insertarJugador() {
		Jugador j= new Jugador() ;
		boolean vacio=false;
		for (int i=0;i<listaTexto.size();i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (!vacio) {
			try {
				j.setNombre(tfizquierdo1.getText());
				j.setPais(tfizquierdo2.getText());
				j.setPosicion(tfizquierdo3.getText());
				j.setEdad(Integer.parseInt(tfizquierdo4.getText()));
				j.setNumGoles(Integer.parseInt(tfizquierdo5.getText()));
				j.setDorsal(Integer.parseInt(tfizquierdo6.getText()));
				j.setNumAmarillas(Integer.parseInt(tfizquierdo7.getText()));
				j.setNumRojas(Integer.parseInt(tfizquierdo8.getText()));
				j.setNumAsistencias(Integer.parseInt(tfizquierdo9.getText()));
				j.setEquipo(new Equipo(tfizquierdo10.getText()));
				j.setImagen(tfizquierdo11.getText());
				BD.insertarJugador(j);
				rellenaTablaJugador();
			}catch(NumberFormatException e) {
				System.out.println("hola");
			}
		}else {
			System.out.println("hola2");
		}
	}

	private void insertarJornada() {
		Jornada j= new Jornada();
		if (!tfizquierdo1.getText().contentEquals("") && !tfizquierdo2.getText().contentEquals("")&& 
				!tfizquierdo3.getText().contentEquals("") && !tfizquierdo4.getText().contentEquals("")) {
			try {
				j.setNumJornada(Integer.parseInt(tfizquierdo1.getText()));
				j.setFechaInicio(new Date(Long.parseLong(tfizquierdo2.getText())));
				j.setFechaFinal(new Date(Long.parseLong(tfizquierdo3.getText())));
				j.setLiga(new Liga(tfizquierdo4.getText()));
				BD.insertarJornada(j);
				rellenaTablaJornada();
			}catch(NumberFormatException e) {
				System.out.println("No se ha anyadido por fallo en conversion numerica");
			}
		}
		
	}

	private void insertarUsuario() {
		Usuario u= new Usuario();
		if (!tfizquierdo1.getText().contentEquals("") && !tfizquierdo2.getText().contentEquals("")&& 
				!tfizquierdo3.getText().contentEquals("")) {
			u.setNombre(tfizquierdo1.getText());
			u.setCorreoElec(tfizquierdo2.getText());
			u.setContrasena(tfizquierdo3.getText());
			BD.insertarUsuario(u);
			rellenaTablaUsuario();
		}
	}
	
	private void insertarEquipo() {
		Equipo e= new Equipo();
		boolean vacio=false;
		for (int i=0;i<6;i++) {
			if (listaTexto.get(i).getText().contentEquals("")) {
				vacio=true;
			}
		}
		if (vacio==false) {
			try {
				e.setNombre(tfizquierdo1.getText());
				e.setImagen(tfizquierdo2.getText());
				e.setPuntos(Integer.parseInt(tfizquierdo3.getText()));
				e.setGolesAFavor(Integer.parseInt(tfizquierdo4.getText()));
				e.setGolesEnContra(Integer.parseInt(tfizquierdo5.getText()));
				e.setLiga(new Liga(tfizquierdo6.getText()));
				BD.insertarEquipo(e);
				rellenaTablaEquipo();
			}catch(NumberFormatException ex) {
				System.out.println("No se ha anyadido por fallo en conversion numerica");
			}
		}
	}

	public static void main(String[]args) {
		VentanaPruebaBD v= new VentanaPruebaBD();
		v.setVisible(true);
	}
	
	public void rellenaTablaUsuario() {
		DefaultTableModel mUsuario= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("Usuario");
		Object[] id={	"nombre", "correoElec", "contrasena"};
		mUsuario.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= {rs.getString("nombre"), rs.getString("correoElect"), rs.getString("contrasena")};
					mUsuario.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tCentral.setModel(mUsuario);
		
		izquierdo1.setText("nombre");
		izquierdo2.setText("correoElec");
		izquierdo3.setText("contrasena");
		izquierdo4.setText("");
		izquierdo5.setText("");
		izquierdo6.setText("");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	}
	
	public void rellenaTablaEquipo() {
		DefaultTableModel mEquipo= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("Equipo");
		Object[] id={	"nom", "img", "puntos", "golesAFavor", "golesEnContra", "liga"};
		mEquipo.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= {rs.getString("nom"), rs.getString("img"), rs.getInt("puntos"),
							rs.getInt("golesAFavor"),rs.getInt("golesEnContra"),rs.getString("liga")};
					mEquipo.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tCentral.setModel(mEquipo);
		
		izquierdo1.setText("nom");
		izquierdo2.setText("img");
		izquierdo3.setText("puntos");
		izquierdo4.setText("goles a favor");
		izquierdo5.setText("goles en contra");
		izquierdo6.setText("liga");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	}
	
	public void rellenaTablaJugador() {
		DefaultTableModel mJugador= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("Jugador");
		Object[] id={	"nombre", "pais", "posicion", "edad", "numGoles", "dorsal",
				"numAmarillas", "numRojas", "numAsistencias", "Equipo", "img"};
		mJugador.setColumnIdentifiers(id);
		try {
			if(rs!=null) {
				while(rs.next()) {
					Object[] data= {rs.getString("nombre"), rs.getString("pais"), rs.getString("posicion"),
							rs.getInt("edad"),rs.getInt("numGoles"),rs.getInt("dorsal"),rs.getInt("numAmarillas"),
							rs.getInt("numRojas"),rs.getInt("numAsistencias"),
							rs.getString("nomEquipo"),rs.getString("img")};
					mJugador.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tCentral.setModel(mJugador);
		
		izquierdo1.setText("nombre");
		izquierdo2.setText("pais");
		izquierdo3.setText("posicion");
		izquierdo4.setText("edad");
		izquierdo5.setText("numGoles");
		izquierdo6.setText("dorsal");
		izquierdo7.setText("numAmarillas");
		izquierdo8.setText("numRojas");
		izquierdo9.setText("numAsistencias");
		izquierdo10.setText("nomEquipo");
		izquierdo11.setText("img");
		
	}
	
	
	public void rellenaTablaJornada() {
		DefaultTableModel mJornada= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("Jornada");
		Object[] id={	"numJornada", "fechaIni", "fechaFin", "liga"};
		mJornada.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= { rs.getInt("numJornada"),
							rs.getLong("fechaInicio"),rs.getLong("fechaFin"),rs.getString("liga")};
					mJornada.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tCentral.setModel(mJornada);
		izquierdo1.setText("numJornada");
		izquierdo2.setText("fechaInicio");
		izquierdo3.setText("fechaFin");
		izquierdo4.setText("liga");
		izquierdo5.setText("");
		izquierdo6.setText("");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	}
	
	public void rellenaLiga() {
		DefaultTableModel mLiga= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("Liga");
		Object[] id={	"nom", "img"};
		mLiga.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= { rs.getString("nom"),rs.getString("img")};
					mLiga.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}else {
				System.out.println("BD no encontrada");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tCentral.setModel(mLiga);
		izquierdo1.setText("nom");
		izquierdo2.setText("img");
		izquierdo3.setText("");
		izquierdo4.setText("");
		izquierdo5.setText("");
		izquierdo6.setText("");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	}
	
	public void rellenaNoticia() {
		DefaultTableModel mNoticia= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("Noticia");
		Object[] id={	"titulo", "img", "cuerpo", "fuente"};
		mNoticia.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= { rs.getString("titulo"),
							rs.getString("img"),rs.getString("cuerpo"),rs.getString("fuente")};
					mNoticia.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tCentral.setModel(mNoticia);
		izquierdo1.setText("titulo");
		izquierdo2.setText("img");
		izquierdo3.setText("cuerpo");
		izquierdo4.setText("fuente");
		izquierdo5.setText("");
		izquierdo6.setText("");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	}
	
	public void rellenaPartido() {
		DefaultTableModel mPartido= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("Partido");
		Object[] id={	"local", "visitante", "fecha", "golesLocal","numJornada","nomLiga","golesVisitante"};
		mPartido.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= { rs.getString("local"),rs.getString("visitante"), 
							rs.getLong("fecha"),rs.getInt("golesLocal"),rs.getInt("numJornada"),
							rs.getString("nomLiga"),rs.getInt("golesVisitante")};
					mPartido.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tCentral.setModel(mPartido);
		izquierdo1.setText("local");
		izquierdo2.setText("visitante");
		izquierdo3.setText("fecha");
		izquierdo4.setText("golesLocal");
		izquierdo5.setText("numJornada");
		izquierdo6.setText("nomLiga");
		izquierdo7.setText("golesVisitante");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	}
	
	public void rellenaSeleccion() {
		DefaultTableModel mEquipo= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("Seleccion");
		Object[] id={	"nom", "img", "puntos", "golesAFavor", "golesEnContra", "liga"};
		mEquipo.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= {rs.getString("nom"), rs.getString("img"), rs.getInt("puntos"),
							rs.getInt("golesAFavor"),rs.getInt("golesEnContra"),rs.getString("liga")};
					mEquipo.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tCentral.setModel(mEquipo);
		izquierdo1.setText("nom");
		izquierdo2.setText("img");
		izquierdo3.setText("puntos");
		izquierdo4.setText("goles a favor");
		izquierdo5.setText("goles en contra");
		izquierdo6.setText("liga");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	}
	
	public void rellenaTraspaso() {
		DefaultTableModel mTraspaso= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("Traspaso");
		Object[] id={	"nomJugador", "nomEquipoVendedor", "nomEquipoComprador",
				"precio", "grado", "fecha"};
		mTraspaso.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= {rs.getString("nomJugador"), rs.getString("nomEquipoVendedor"),rs.getString("nomEquipoComprador"),
							rs.getLong("precio"), rs.getByte("grado"),rs.getLong("fecha")};
					mTraspaso.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tCentral.setModel(mTraspaso);
		
		izquierdo1.setText("nomJugador");
		izquierdo2.setText("nomEquipoVendedor");
		izquierdo3.setText("nomEquipoComprador");
		izquierdo4.setText("precio");
		izquierdo5.setText("grado");
		izquierdo6.setText("fecha");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	}
	
	public void rellenaEquipoNot() {
		DefaultTableModel mEquipoNot= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("EquipoNot");
		Object[] id={	"nomEquipo", "titulo"};
		mEquipoNot.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= {rs.getString("nomEquipo"), rs.getString("tituloNot")};
					mEquipoNot.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tCentral.setModel(mEquipoNot);
		izquierdo1.setText("nomEquipo");
		izquierdo2.setText("titulo");
		izquierdo3.setText("");
		izquierdo4.setText("");
		izquierdo5.setText("");
		izquierdo6.setText("");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	}
	
	public void rellenaLigaNot() {
		DefaultTableModel mLigaNot= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("LigaNot");
		Object[] id={	"nomLiga", "titulo"};
		mLigaNot.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= {rs.getString("nomLiga"), rs.getString("tituloNot")};
					mLigaNot.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tCentral.setModel(mLigaNot);
		izquierdo1.setText("nomLiga");
		izquierdo2.setText("titulo");
		izquierdo3.setText("");
		izquierdo4.setText("");
		izquierdo5.setText("");
		izquierdo6.setText("");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	}
	
	public void rellenaPartidoJugador() {
		DefaultTableModel mPartidoJugador= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("PartidoJugador");
		Object[] id={	"nomJugador", "nomEquipoJugador", "nomEquipoLocal",
				"nomEquipoVisitante", "numGoles", "numAsistencias", "numRojas",
				"numAmarillas", "fecha"};
		mPartidoJugador.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= {rs.getString("nomJugador"), rs.getString("nomEquipoJugador"),
							rs.getString("nomEquipoLocal"), rs.getString("nomEquipoVisitante"),
							rs.getInt("numGoles"), rs.getInt("numAsistencias"), rs.getInt("numRojas"), 
							rs.getInt("numAmarillas"), rs.getLong("fecha")};
					mPartidoJugador.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		izquierdo1.setText("nomJugador");
		izquierdo2.setText("nomEquipoJugador");
		izquierdo3.setText("nomEquipoLocal");
		izquierdo4.setText("nomEquipoVisitante");
		izquierdo5.setText("numGoles");
		izquierdo6.setText("numAsistencias");
		izquierdo7.setText("numRojas");
		izquierdo8.setText("numAmarillas");
		izquierdo9.setText("fecha");
		izquierdo10.setText("");
		izquierdo11.setText("");
		tCentral.setModel(mPartidoJugador);
	}
	
	public void rellenaUsuarioJugador() {
		DefaultTableModel mUsuarioJugador= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("UsuarioJugador");
		Object[] id={	"nomJugador", "nomEquipo", "correoElec"};
		mUsuarioJugador.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= {rs.getString("nomJugador"), rs.getString("nomEquipo"), rs.getString("correoUsuario")};
					mUsuarioJugador.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tCentral.setModel(mUsuarioJugador);
		izquierdo1.setText("nomJugador");
		izquierdo2.setText("nomEquipo");
		izquierdo3.setText("correoElec");
		izquierdo4.setText("");
		izquierdo5.setText("");
		izquierdo6.setText("");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
	}
	
	public void rellenaUsuarioEquipo() {
			DefaultTableModel mUsuarioEquipo= new DefaultTableModel();
			ResultSet rs=BD.selectTodas("UsuarioEquipo");
			Object[] id={ "nomEquipo", "correoElec"};
			mUsuarioEquipo.setColumnIdentifiers(id);
			try {
				if (rs!=null) {
					while(rs.next()) {
						Object[] data= { rs.getString("nomEquipo"), rs.getString("correoUsuario")};
						mUsuarioEquipo.addRow(data);
					}
					BD.cerrarBD(con, rs.getStatement());
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			izquierdo1.setText("nomEquipo");
			izquierdo2.setText("correoElec");
			izquierdo3.setText("");
			izquierdo4.setText("");
			izquierdo5.setText("");
			izquierdo6.setText("");
			izquierdo7.setText("");
			izquierdo8.setText("");
			izquierdo9.setText("");
			izquierdo10.setText("");
			izquierdo11.setText("");
			tCentral.setModel(mUsuarioEquipo);
	}
	
	public void rellenaUsuarioLiga() {
		DefaultTableModel mUsuarioEquipo= new DefaultTableModel();
		ResultSet rs=BD.selectTodas("UsuarioLiga");
		Object[] id={ "nomEquipo", "correoElec"};
		mUsuarioEquipo.setColumnIdentifiers(id);
		try {
			if (rs!=null) {
				while(rs.next()) {
					Object[] data= { rs.getString("nomLiga"), rs.getString("correoUsuario")};
					mUsuarioEquipo.addRow(data);
				}
				BD.cerrarBD(con, rs.getStatement());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tCentral.setModel(mUsuarioEquipo);
		izquierdo1.setText("nomLiga");
		izquierdo2.setText("correoElec");
		izquierdo3.setText("");
		izquierdo4.setText("");
		izquierdo5.setText("");
		izquierdo6.setText("");
		izquierdo7.setText("");
		izquierdo8.setText("");
		izquierdo9.setText("");
		izquierdo10.setText("");
		izquierdo11.setText("");
}
	
	private void guardarDatos() {
		if (getWidth()>0 && getHeight()>0 && getX()>=0 && getY()>=0)
		p.put("ancho",""+  this.getWidth());
		p.put("alto", ""+ this.getHeight());
		p.put("x", ""+this.getX());
		p.put("y" ,""+ this.getY());
		
		
		try {
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(NOM_FICHERO));
			p.save(oos,"");
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void cargaDatos() {
		File fichero= new File(NOM_FICHERO);
		if (fichero.exists()) {
			try {
				ObjectInputStream ois= new ObjectInputStream(new FileInputStream(NOM_FICHERO));
				p.load(ois);
				try {
					int ancho=Integer.parseInt((String)p.get("ancho"));
					int alto=Integer.parseInt((String)p.get("alto"));
					int x= Integer.parseInt((String)p.get("x"));
					int y= Integer.parseInt((String)p.get("y"));
					this.setBounds(x,y, ancho, alto);
				}catch(NumberFormatException ex) {
					ex.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}

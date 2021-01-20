package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


import clases.BD;
import clases.Usuario;

//TODO ventana de estadisticas revisar 
//TODO ventana de busqueda 
//TODO barras de progreso en el acceso a BD 
//TODO recur  con Noticias->implementar Date en Noticias y buscador de Noticias
public class VentanaLogIn extends JFrame {
	HashMap<String, Usuario>logIn;
	JTextField tNombre;
	JTextField tCorreo;
	JPasswordField tContrasena;
	boolean modoRegistro;
	JPanel pNombre;
	JPanel pCentral;
	
	
	public VentanaLogIn() {
		modoRegistro=true;
		 
		pCentral= new JPanel();
		tNombre= new JTextField(30);
		tContrasena= new JPasswordField(30);
		tCorreo= new JTextField(30);
		JPanel pContrasena= new JPanel();
		pNombre= new JPanel();
		JPanel pCorreo= new JPanel();
		pContrasena.setLayout(new FlowLayout(FlowLayout.LEFT));
		pNombre.setLayout(new FlowLayout(FlowLayout.LEFT));
		pCorreo.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		pContrasena.add(new JLabel ("Contrasena:"));
		pContrasena.add(tContrasena);
		pNombre.add(new JLabel ("Nombre:"));
		pNombre.add(tNombre);
		pCorreo.add(new JLabel("Correo"));
		pCorreo.add(tCorreo);
		prepareMenuBar();	
			
		pCentral.setLayout(new GridLayout(3, 1));
		pCentral.add(pNombre);
	
		pCentral.add(pCorreo);
		pCentral.add(pContrasena);
		JPanel pInferior= new JPanel();
		pInferior.setLayout(new BorderLayout());
		JButton bAceptar= new JButton("Aceptar");
		pInferior.add(bAceptar, BorderLayout.CENTER);
		JProgressBar pbProgreso= new JProgressBar(0, 100);
		getContentPane().add(pCentral,BorderLayout.CENTER);
		getContentPane().add(pInferior,BorderLayout.SOUTH);
		Thread hilo= new Thread(new Runnable() {
			
			@Override
			public void run() {
				Usuario u=BD.selectUsuario(tCorreo.getText());
				if (u!=null && u.getContrasena().contentEquals(tContrasena.getText())) {
					VentanaInicio v= new VentanaInicio(u);
					VentanaLogIn.this.dispose();
				}
			}
		});
		
		Thread hilo2= new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(VentanaLogIn.this.isVisible()) {
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
		bAceptar.addActionListener((ActionEvent e)-> {
			if (modoRegistro) {
				if (!logIn.containsKey(tCorreo.getText())) {
					if (validaCampos()) {
						pInferior.add(pbProgreso,BorderLayout.SOUTH);
						Usuario u= new Usuario();
						u.setNombre(tNombre.getText());
						u.setCorreoElec(tCorreo.getText());
						u.setContrasena(tContrasena.getText());
						BD.insertarUsuario(u);
						VentanaInicio v= new VentanaInicio(u);
						VentanaLogIn.this.dispose();
					}
				}
			}else {
				pInferior.add(pbProgreso,BorderLayout.SOUTH);
				revalidate();
				hilo.start();
				hilo2.start();
				
				//TODO usuario o contraseña incorrectos
			}
		});
		
		
		tContrasena.addKeyListener(new KeyAdapter() {
			
			
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					if (modoRegistro) {
						if (logIn.containsKey(tCorreo.getText())) {
							if (validaCampos()) {
								Usuario u= new Usuario();
								u.setNombre(tNombre.getText());
								u.setCorreoElec(tCorreo.getText());
								u.setContrasena(tContrasena.getText());
								BD.insertarUsuario(u);
								VentanaInicio v= new VentanaInicio(u);
								VentanaLogIn.this.dispose();
							}
						}
					}else {
						pInferior.add(pbProgreso,BorderLayout.SOUTH);
						revalidate();
						hilo.start();
						hilo2.start();
						}//TODO usuario o contraseña incorrectos
					
					
				}
				
			}

		});
		
		this.pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	
	
		
		//TODO ventana simple de logIn copiar del proyecto pasado.
		
	}
	
	public static void main(String[]args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}
			VentanaLogIn v2= new VentanaLogIn();
	}
	private void prepareMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileJMenu = new JMenu("MODO REGISTRO/INICIO DE SESION");
		menuBar.add(fileJMenu);
		
		JMenuItem registrarItem = new JMenuItem("Registrarse...");
		fileJMenu.add(registrarItem);
		
		registrarItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				pCentral.setLayout(new GridLayout(3, 1));
				pCentral.add(pNombre);
				modoRegistro=true;
				revalidate();
			}
			
		});
		
		JMenuItem inicioItem = new JMenuItem("IniciarSesion");
		fileJMenu.add(inicioItem);
		
		inicioItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pCentral.remove(pNombre);
				pCentral.setLayout(new GridLayout(2, 1));
				modoRegistro=false;
				revalidate();
			}
		});
		
		getContentPane().add(menuBar,BorderLayout.NORTH);
		
	}
	
	
	public boolean validaCampos() {
		if (tNombre.getText().isEmpty() || tContrasena.getText().isEmpty()|| tCorreo.getText().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
}

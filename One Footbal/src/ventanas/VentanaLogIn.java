package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import clases.BD;
import clases.Usuario;

public class VentanaLogIn extends JFrame {
	HashMap<String, Usuario>logIn;
	JTextField tNombre;
	JTextField tCorreo;
	JPasswordField tContrasena;
	
	
	public VentanaLogIn(boolean modoRegistro) {
		logIn= new HashMap<String, Usuario>();
		ArrayList<Usuario>usuarios=BD.selectUsuarios();
		for (Usuario u:usuarios) {
			System.out.println(u.getCorreoElec());
			logIn.put(u.getCorreoElec(), u);
		}
		 
		JPanel pCentral= new JPanel();
		tNombre= new JTextField(30);
		tContrasena= new JPasswordField(30);
		tCorreo= new JTextField(30);
		JPanel pContrasena= new JPanel();
		JPanel pNombre= new JPanel();
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
		
		
		if (!modoRegistro) {
			pCentral.setLayout(new GridLayout(2, 1));
		}else {
			pCentral.setLayout(new GridLayout(3, 1));
			pCentral.add(pNombre);
		}
		pCentral.add(pCorreo);
		pCentral.add(pContrasena);
		JPanel pInferior= new JPanel();
		JButton bAceptar= new JButton("Aceptar");
		pInferior.add(bAceptar);
		getContentPane().add(pCentral,BorderLayout.CENTER);
		getContentPane().add(pInferior,BorderLayout.SOUTH);
		bAceptar.addActionListener((ActionEvent e)-> {
			if (modoRegistro) {
				if (logIn.containsKey(tCorreo.getText())) {
					if (validaCampos()) {
						Usuario u= new Usuario();
						u.setNombre(tNombre.getText());
						u.setCorreoElec(tCorreo.getText());
						u.setContrasena(tContrasena.getText());
						BD.insertarUsuario(u);
						VentanaInicio v= new VentanaInicio(u);
					}
				}
			}else {
				if (logIn.containsKey(tCorreo.getText())&& logIn.get(tCorreo.getText()).getContrasena().contentEquals(tContrasena.getText())) {
					Usuario u = BD.selectUsuario(tCorreo.getText());
					u.setEquiposSeguidos(BD.selectEquiposUsuario(u));
					u.setJugadoresSeguidos(BD.selectJugadoresUsuario(u));
					u.setLigasSeguidas(BD.selectLigasUsuario(u));
					VentanaInicio v= new VentanaInicio(u);
				}//TODO usuario o contraseña incorrectos
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
							}
						}
					}else {
						if (logIn.containsKey(tCorreo.getText())&& logIn.get(tCorreo.getText()).getContrasena().contentEquals(tContrasena.getText())) {
							Usuario u = BD.selectUsuario(tCorreo.getText());
							u.setEquiposSeguidos(BD.selectEquiposUsuario(u));
							u.setJugadoresSeguidos(BD.selectJugadoresUsuario(u));
							u.setLigasSeguidas(BD.selectLigasUsuario(u));
							VentanaInicio v= new VentanaInicio(u);
						}//TODO usuario o contraseña incorrectos
					}
					
				}
				
			}

		});
		
		this.pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	
	
		
		//TODO ventana simple de logIn copiar del proyecto pasado.
		
	}
	
	public static void main(String[]args) {
		VentanaLogIn v2= new VentanaLogIn(false);
	}
	
	
	public boolean validaCampos() {
		if (tNombre.getText().isEmpty() || tContrasena.getText().isEmpty()|| tCorreo.getText().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
}

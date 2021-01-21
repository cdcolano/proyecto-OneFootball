package clases;

import javax.swing.UIManager;

import ventanas.VentanaLogIn;

public class Main {
	
	public static void main(String[]args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}
			VentanaLogIn v2= new VentanaLogIn();
	}
}

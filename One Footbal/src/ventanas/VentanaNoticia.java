package ventanas;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import clases.Noticia;
import clases.Usuario;

/**Ventana que muestra una sola noticia con su cuerpo desarrollado
 * @author cdcol
 *
 */
public class VentanaNoticia extends JFrame {
	
	/**
	 * @param u Usuario loggeado
	 * @param n Noticia a mostrar
	 */
	public VentanaNoticia(Usuario u, Noticia n) {

		JPanel pCentral= new JPanel();
		JPanel pNoticia=new JPanel();
		pNoticia.setLayout(new BorderLayout());
		JLabel img= new JLabel();
		img.setIcon(new ImageIcon(VentanaInicio.class.getResource(n.getImagen())));
		pNoticia.add(img,BorderLayout.SOUTH);
		JLabel titulo= new JLabel(n.getTitulo());
		titulo.setFont(new Font("helvitica", Font.BOLD, 24));
		pNoticia.add(titulo,BorderLayout.CENTER);
		JLabel fuente= new JLabel (n.getFuente());
		fuente.setFont(new Font("helvitica", Font.PLAIN, 16));
		pNoticia.add(fuente,BorderLayout.NORTH);
		
		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		pCentral.add(pNoticia);
		JTextArea taTexto= new JTextArea(n.getCuerpo());
		taTexto.setLineWrap(true);
		taTexto.setWrapStyleWord(true);
		taTexto.setEditable(false);
		pCentral.add(taTexto);
		JScrollPane spCentral= new JScrollPane(pCentral);
		getContentPane().add(spCentral,BorderLayout.CENTER);
		VentanaInicio.anyadeBotonera(this, u);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 300, 500, 500);
		setVisible(true);
	}
	
}

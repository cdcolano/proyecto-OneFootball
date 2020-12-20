package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import clases.BD;
import clases.ConImagenes;
import clases.Equipo;
import clases.Noticia;
import clases.Usuario;
//TODO Ventana Login que cargue datos para el usuario
//TODO main para integracion de todos los equipos
/**Ventana que imita el comportamiento de la ventana inicial de oneFootball
 * mostrando noticias de diferentes equipos a los que se sigue aparte de las TOP NEWS
 * @author cdcol
 *
 */
public class VentanaInicio extends JFrame {
	private JScrollPane scpanelCentral; //estara compuesto por más subpanles con los distintos contenidos
	private JPanel pTopNews; //noticias con ultima fecha necesario BD
	private ArrayList<Noticia>topNews;
	private Usuario u;
	private JPanel panelCentral;
	
	
	/**
	 * @param u Usuario loggeado
	 */
	public VentanaInicio(Usuario u) {
		topNews= new ArrayList<Noticia>();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ResultSet rs= BD.selectTodas("Noticia");
		try {
			while (rs.next()) {
				Noticia not= BD.selectNoticia(rs.getString("titulo"));
				not.setEquipos(BD.selectEquiposNot(not));
				not.setLigas(BD.selectLigasNot(not));
				topNews.add(not);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Noticia n:topNews) {
			System.out.println(n.getTitulo() + "Not");
		}
		this.u=u;
		
		JPanel pCentral= new JPanel();
		scpanelCentral= new JScrollPane(pCentral);
		pCentral.setLayout(new GridLayout(u.getEquiposSeguidos().size()+1,1));
		pTopNews=new JPanel();
		JPanel pTopNewsConTit= new JPanel();
		pTopNewsConTit.setLayout(new BorderLayout());
		pTopNews.setLayout(new GridLayout(topNews.size() , 1));//TODO esto hace que haya demasiada distancia entre titulo TOP NEWS y noticias
		JPanel pTituloTopNews= new JPanel();
		pTituloTopNews.setLayout(new FlowLayout (FlowLayout.LEFT));
		JLabel imgFuego= new JLabel();
		imgFuego.setIcon(new ImageIcon(VentanaInicio.class.getResource("/img/fuego.png")));
		JLabel ltopNews= new JLabel("TOP NEWS");
		ltopNews.setFont(new Font("helvitica", Font.BOLD, 30));
		pTituloTopNews.add(ltopNews);
		pTopNewsConTit.add(pTituloTopNews,BorderLayout.NORTH);
		pTopNewsConTit.add(pTopNews,BorderLayout.CENTER);
		
		//pTopNews.add(pTituloTopNews);
		for (Noticia not: topNews) {
			JPanel pNoticia= anyadePanalesNoticia(not);
			pNoticia.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount()>=2 ){//&& pNoticia.getLocationOnScreen().x<=e.getLocationOnScreen().x &&
					//	pNoticia.getLocationOnScreen().x+ pNoticia.getWidth()>=e.getLocationOnScreen().x &&
						//pNoticia.getLocationOnScreen().y<=e.getLocationOnScreen().y && 
						//pNoticia.getLocationOnScreen().y+pNoticia.getHeight()>=e.getLocationOnScreen().y
						VentanaNoticia v= new VentanaNoticia( u, not);
						VentanaInicio.this.dispose();
					}
					
				}
			});
			pTopNews.add(pNoticia);
		}
		pCentral.add(pTopNewsConTit);
		
		for (Equipo e :u.getEquiposSeguidos()) {
			JPanel pNoticias= new JPanel();
			pNoticias.setLayout(new FlowLayout(FlowLayout.LEFT));
			JPanel pEquipo= new JPanel();
			pEquipo.setLayout(new BorderLayout());
			JPanel pEquipoAct= new JPanel();
			pEquipoAct.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel img= new JLabel();
			img.setIcon(redimensionImgProd(new ImageIcon(VentanaInicio.class.getResource(e.getImagen())),50,50)); //TODO redimension
			pEquipoAct.add(img);
			pEquipoAct.add(new JLabel (e.getNombre()));
			pEquipo.add(pEquipoAct);
			if (e.getNoticias().size()>=6) {
				for (int i=e.getNoticias().size()-1; i>e.getNoticias().size()-6; i++) {	//solo las ultimas 5 noticias por equipo
					Noticia n= e.getNoticias().get(i);
					JPanel pNoticia=anyadePanalesNoticia(n);
					pNoticia.addMouseListener(new MouseAdapter() {
						
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount()>=2 ){//&& pNoticia.getLocationOnScreen().x<=e.getLocationOnScreen().x &&
							//	pNoticia.getLocationOnScreen().x+ pNoticia.getWidth()>=e.getLocationOnScreen().x &&
								//pNoticia.getLocationOnScreen().y<=e.getLocationOnScreen().y && 
								//pNoticia.getLocationOnScreen().y+pNoticia.getHeight()>=e.getLocationOnScreen().y
								VentanaNoticia v= new VentanaNoticia( u, n);
								VentanaInicio.this.dispose();
							}
							
						}
					}); 
					pNoticias.add(pNoticia);
					}
			}else {
				for (Noticia n: e.getNoticias()) {
					JPanel pNoticia=anyadePanalesNoticia(n);
					pNoticia.addMouseListener(new MouseAdapter() {
						
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount()>=2) {
								VentanaNoticia v= new VentanaNoticia( u, n);
							}
							
						}
					}); 
					pNoticias.add(pNoticia);
				}
				pEquipo.add(pEquipoAct,BorderLayout.NORTH);
				pEquipo.add(pNoticias,BorderLayout.CENTER);
				pCentral.add(pEquipo);
				
			}
		}
		
		getContentPane().add(scpanelCentral, BorderLayout.CENTER);
		anyadeBotonera(this, u);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 300, 500, 500);
		setVisible(true);	
	}

	
	
	
	/**crea Paneles con los datos de una noticia
	 * @param n Noticia que se va a añadir
	 * @return Panel con los datos de la noticia
	 */
	public static JPanel anyadePanalesNoticia(Noticia n) {
		JPanel pNoticia=new JPanel();
		pNoticia.setLayout(new BorderLayout());
		JLabel img= new JLabel();
		if (!n.getImagen().isEmpty())
		img.setIcon(new ImageIcon(VentanaInicio.class.getResource(n.getImagen())));
		pNoticia.add(img,BorderLayout.SOUTH);
		JLabel titulo= new JLabel(n.getTitulo());
		titulo.setFont(new Font("helvitica", Font.BOLD, 24));
		pNoticia.add(titulo,BorderLayout.CENTER);
		JLabel fuente= new JLabel (n.getFuente());
		fuente.setFont(new Font("helvitica", Font.PLAIN, 16));
		pNoticia.add(fuente,BorderLayout.NORTH);
		return pNoticia;
		//TODO añadir tamaño por defecto
	}
	
	/**redimensiona la imagen con formato 200x200
	 * @param imageIcon imagen a redimensionar
	 * @return imagen redimensionada
	 */
	public static ImageIcon redimensionImgProd(ImageIcon imageIcon, int ancho, int alto) {
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(ancho,alto ,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			return imageIcon = new ImageIcon(newimg);  // transform it back
	}
	
	/**añade Botones comunes en todas las Ventanas 
	 * @param vent ventana a la que se añadiran los botones
	 * @param u Usuario loggeado
	 */
	public static void anyadeBotonera(JFrame vent,Usuario u) {

		JButton bInicio= new JButton();
		JButton bSeguidos= new JButton();
		JButton bPartidos= new JButton();

		bSeguidos.addActionListener((ActionEvent event)-> {
			if (! (vent instanceof VentanaSiguiendo)) {
				VentanaSiguiendo v= new VentanaSiguiendo(u);
				vent.dispose();
			}
		}
		);
		
		bInicio.addActionListener((ActionEvent e)->{
			if (! (vent instanceof VentanaInicio)) {
				VentanaInicio v= new VentanaInicio(u);
				vent.dispose();
			}
		});
		
		
		bPartidos.addActionListener((ActionEvent e)-> {
			if (! (vent instanceof VentanaPartidos)) {
				VentanaPartidos p= new VentanaPartidos(u);
				vent.dispose();
			}
				
			}
		);
		
		ImageIcon iInicio=new ImageIcon(VentanaNoticias.class.getResource("/img/inicio.png")); //TODO redimensionar
		ImageIcon iPartido=new ImageIcon(VentanaNoticias.class.getResource("/img/partidos.png"));//TODO redimensionar
		ImageIcon iSeguidos=new ImageIcon(VentanaNoticias.class.getResource("/img/siguiendo.png"));//TODO redimensionar
		bInicio.setIcon(redimensionImgProd(iInicio, 30, 30)); 
		bPartidos.setIcon(redimensionImgProd(iPartido, 30, 30));//TODO copia con ActionListeners
		bSeguidos.setIcon(redimensionImgProd(iSeguidos, 30, 30));
		
		JPanel botoneraInfa= new JPanel();
		JPanel botoneraInfb= new JPanel();
		JPanel botoneraInfc= new JPanel();
		
		
		botoneraInfa.setLayout(new GridLayout(2, 1));
		botoneraInfc.setLayout(new GridLayout(2, 1));
		botoneraInfb.setLayout(new GridLayout(2, 1));
		
		botoneraInfa.add(bInicio);
		botoneraInfb.add(bSeguidos);
		botoneraInfc.add(bPartidos);
		
		
		botoneraInfa.add(new JLabel ("Inicio"));
		botoneraInfb.add(new JLabel("Seguidos"));
		botoneraInfc.add(new JLabel("Partidos"));
		
		JPanel pBotoneraInf= new JPanel();
		
		pBotoneraInf.add(botoneraInfa);
		pBotoneraInf.add(botoneraInfb);
		pBotoneraInf.add(botoneraInfc);
		
		vent.getContentPane().add(pBotoneraInf,BorderLayout.SOUTH);

	}

}








   


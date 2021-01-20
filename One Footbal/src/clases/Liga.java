package clases;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Liga extends Contenedor{
	
	private TreeSet<Equipo> equipos; // nombre clave primaria
	private TreeSet<Jugador> maximosGoleadores;
	private TreeSet<Jugador> maximosAsistentes;
	private TreeSet<Jugador> tarjetasAmarillas;
	private TreeSet<Jugador> tarjetasRojas;
	private ArrayList<Jornada> jornadas;

	
		

	public Liga() {
		equipos= new TreeSet<Equipo>();
		maximosGoleadores= new TreeSet<Jugador>(new Comparator<Jugador>() {

			@Override
			public int compare(Jugador o1, Jugador o2) {
				if (o1.getNumGoles()<o2.getNumGoles()) {
					return 1;
				}else if (o1.getNumGoles()==o2.getNumGoles()) {
					return 0;
				}
				else {
					return -1;
				}
			}
		
		
			
		});
		
		maximosAsistentes=new TreeSet<Jugador>(new Comparator<Jugador>() {

			@Override
			public int compare(Jugador o1, Jugador o2) {
				if (o1.getNumAsistencias()>o2.getNumAsistencias()) {
					return 1;
				}else if (o1.getNumAsistencias()==o2.getNumAsistencias()) {
					return 0;
				}
				else {
					return -1;
				}
			}
		
		});
		tarjetasAmarillas= new TreeSet<Jugador>(new Comparator<Jugador>() {

			@Override
			public int compare(Jugador o1, Jugador o2) {
				if (o1.getNumAmarillas()>o2.getNumAmarillas()) {
					return 1;
				}else if (o1.getNumAmarillas()==o2.getNumAmarillas()) {
					return 0;
				}
				else {
					return -1;
				}
			}
		
		});
		tarjetasRojas=new TreeSet<Jugador>((Jugador o1, Jugador o2)-> {
			if (o1.getNumRojas()<o2.getNumRojas()) {
				return 1;
			}else if (o1.getNumRojas()==o2.getNumRojas()) {
				return 0;
			}
			else {
				return -1;
			}
		}
		
	);
		traspasos= new ArrayList<Traspaso>();
		jornadas=new ArrayList<Jornada>();
	}
	


	public Liga(String nombre) {
		this();
		this.nombre=nombre;
		
	}


	public TreeSet<Equipo> getEquipos() {
		return equipos;
	}


	public void setEquipos(TreeSet<Equipo> equipos) {
		this.equipos = equipos;
	}


	public TreeSet<Jugador> getMaximosGoleadores() {
		return maximosGoleadores;
	}


	public void setMaximosGoleadores(TreeSet<Jugador> maximosGoleadores) {
		this.maximosGoleadores = maximosGoleadores;
	}


	public TreeSet<Jugador> getMaximosAsistentes() {
		return maximosAsistentes;
	}


	public void setMaximosAsistentes(TreeSet<Jugador> maximosAsistentes) {
		this.maximosAsistentes = maximosAsistentes;
	}


	public TreeSet<Jugador> getTarjetasAmarillas() {
		return tarjetasAmarillas;
	}


	public void setTarjetasAmarillas(TreeSet<Jugador> tarjetasAmarillas) {
		this.tarjetasAmarillas = tarjetasAmarillas;
	}


	public TreeSet<Jugador> getTarjetasRojas() {
		return tarjetasRojas;
	}


	public void setTarjetasRojas(TreeSet<Jugador> tarjetasRojas) {
		this.tarjetasRojas = tarjetasRojas;
	}


	public ArrayList<Traspaso> getTraspasos() {
		return traspasos;
	}


	public void setTraspasos(ArrayList<Traspaso> traspasos) {
		this.traspasos = traspasos;
	}


	public ArrayList<Jornada> getJornadas() {
		return jornadas;
	}


	public void setJornadas(ArrayList<Jornada> jornadas) {
		this.jornadas = jornadas;
	}
	
	public void addAsistente(Jugador j) {
		maximosAsistentes.add(j);
	}
	
	public void addGoleador(Jugador j) {
		maximosGoleadores.add(j);
	}
	
	public void addAmarilla(Jugador j) {
		tarjetasAmarillas.add(j);
	}
	
	public void addRoja(Jugador j) {
		tarjetasRojas.add(j);
	}
	
	
	/**Considera iguales dos ligas con el mismo nombre
	 * @param l Liga a comparar
	 * @return true si son iguales, false si no lo son
	 */
	public boolean equals(Liga l) {
		if (l.getNombre().contentEquals(nombre)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public boolean buscaTraspaso(Traspaso t) {
		for (Traspaso tras:traspasos) {
			if (t.equals(tras)) {
				return false;
			}
		}
		return true;
	}



	public int compareTo(Liga ligaSel) {
		// TODO Auto-generated method stub
		return nombre.compareTo(ligaSel.getNombre());
	}
	
}


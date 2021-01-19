package clases;

import java.util.ArrayList;
import java.util.Comparator;

public class Usuario {
	String nombre;
	String correoElec;
	String contrasena;
	ArrayList<Equipo>equiposSeguidos;
	ArrayList<Jugador>jugadoresSeguidos;
	ArrayList<Liga> ligasSeguidas;
	
	public Usuario() {
		equiposSeguidos= new ArrayList<Equipo>();
		
		jugadoresSeguidos= new ArrayList<Jugador>();
		
		ligasSeguidas= new ArrayList<Liga>();
		
	}
	
	public Usuario (String nombre, String correoElec, String contrasena) {
		this();
		this.contrasena=contrasena;
		this.correoElec= correoElec;
		this.nombre= nombre;
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreoElec() {
		return correoElec;
	}
	public void setCorreoElec(String correoElec) {
		this.correoElec = correoElec;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public ArrayList<Equipo> getEquiposSeguidos() {
		return equiposSeguidos;
	}
	public void setEquiposSeguidos(ArrayList<Equipo> equiposSeguidos) {
		this.equiposSeguidos = equiposSeguidos;
	}
	public ArrayList<Jugador> getJugadoresSeguidos() {
		return jugadoresSeguidos;
	}
	public void setJugadoresSeguidos(ArrayList<Jugador> jugadoresSeguidos) {
		this.jugadoresSeguidos = jugadoresSeguidos;
	}
	public ArrayList<Liga> getLigasSeguidas() {
		return ligasSeguidas;
	}
	public void setLigasSeguidas(ArrayList<Liga> ligasSeguidas) {
		this.ligasSeguidas = ligasSeguidas;
	}
	
	public void addLigaSeguida(Liga l) {
		ligasSeguidas.add(l);
	}
	public void addEquipoSeguido(Equipo e) {
		equiposSeguidos.add(e);
	}
	
	public void addJugadorSeguido(Jugador j) {
		jugadoresSeguidos.add(j);
	}
	
	
	public String toString() {
		return "Nombre: "+ nombre + "Correo: " + correoElec;
	}
	
	
	public void removeJugador(Jugador j) {
		jugadoresSeguidos=quickSortJugador(jugadoresSeguidos, 0, jugadoresSeguidos.size()-1);
		int indice=buscaJugador(jugadoresSeguidos, j, 0, jugadoresSeguidos.size()-1);
		jugadoresSeguidos.remove(indice);
	}
	
	public void removeLiga(Liga l) {
		ligasSeguidas=quickSortLiga(ligasSeguidas, 0, ligasSeguidas.size()-1);
		int indice= buscaLiga(ligasSeguidas, l, 0, ligasSeguidas.size()-1);
		ligasSeguidas.remove(indice);
	}
	
	public void removeEquipo(Equipo e) {
		equiposSeguidos=quickSortEquipo(equiposSeguidos, 0, equiposSeguidos.size()-1);
		int indice=buscaEquipo(equiposSeguidos, e, 0, equiposSeguidos.size()-1);
		equiposSeguidos.remove(indice);
	}
	
	
	
	/**Considera iguales dos Usuarios con el mismo correo electronico
	 * @param u Usuario a comparar
	 * @return true si son iguales false si no lo son
	 */
	public boolean equals(Usuario u) {
		if (u.getCorreoElec().contentEquals(correoElec)) {
			return true;
		}else {
			return false;
		}
	}
	
	public ArrayList<Equipo> quickSortEquipo(ArrayList<Equipo> lista, int inferior, int superior) {
		Equipo mitad= lista.get((inferior + superior) /2 );
		Equipo x;
		int izquierda=inferior;
		int derecha=superior;
		do {
			while (lista.get(izquierda).getNombre().compareTo(mitad.getNombre())<0 && izquierda<superior) {
				izquierda++;
			}
			while (mitad.getNombre().compareTo(lista.get(derecha).getNombre())<0 && derecha>inferior) {
				derecha--;
			}
		if (izquierda<derecha) {
			x=lista.get(izquierda);
			lista.set(izquierda, lista.get(derecha));
			lista.set(derecha, x);
			izquierda++;
			derecha--;
		}else {
			break;
		}
		
		}while (true);
		if (inferior<derecha) {
			quickSortEquipo(lista, inferior, derecha);
			quickSortEquipo(lista, derecha +1 , superior);
			
		}
		return lista;
	}
	
	
	public  ArrayList<Liga> quickSortLiga(ArrayList<Liga> lista, int inferior, int superior) {
		Liga mitad= lista.get((inferior + superior) /2 );
		Liga x;
		int izquierda=inferior;
		int derecha=superior;
		do {
			while (lista.get(izquierda).getNombre().compareTo(mitad.getNombre())<0 && izquierda<superior) {
				izquierda++;
			}
			while (mitad.getNombre().compareTo(lista.get(derecha).getNombre())<0 && derecha>inferior) {
				derecha--;
			}
		if (izquierda<derecha) {
			x=lista.get(izquierda);
			lista.set(izquierda, lista.get(derecha));
			lista.set(derecha, x);
			izquierda++;
			derecha--;
		}else {
			break;
		}
		
		}while (true);
		if (inferior<derecha) {
			quickSortLiga(lista, inferior, derecha);
			quickSortLiga(lista, derecha +1 , superior);
			
		}
		return lista;
	}
	
	
	public ArrayList<Jugador> quickSortJugador(ArrayList<Jugador> lista, int inferior, int superior) {
		Jugador mitad= lista.get((inferior + superior) /2 );
		Jugador x;
		int izquierda=inferior;
		int derecha=superior;
		do {
			while (lista.get(izquierda).getNombre().compareTo(mitad.getNombre())<0 && izquierda<superior) {
				izquierda++;
			}
			while (mitad.getNombre().compareTo(lista.get(derecha).getNombre())<0 && derecha>inferior) {
				derecha--;
			}
		if (izquierda<derecha) {
			x=lista.get(izquierda);
			lista.set(izquierda, lista.get(derecha));
			lista.set(derecha, x);
			izquierda++;
			derecha--;
		}else {
			break;
		}
		
		}while (true);
		if (inferior<derecha) {
			quickSortJugador(lista, inferior, derecha);
			quickSortJugador(lista, derecha +1 , superior);
			
		}
		return lista;
	}
	
	
	public int buscaJugador(ArrayList<Jugador> lista, Jugador j, int indiceInf, int indiceSup) {
		int indice=(indiceInf+ indiceSup)/2;
		Jugador jugadorSel= lista.get(indice);
		if (indiceInf>indiceSup) {
			return -1;
		}
		if (j.equals(jugadorSel)) {
			return indice;
		}
		else {
			if (j.compareTo(jugadorSel)>0) {
				return buscaJugador(lista, j, indice, lista.size()-1);
			}else
				return buscaJugador(lista, j, 0, indice);
		}
		
	}
	
	public int buscaLiga(ArrayList<Liga> lista, Liga l, int indiceInf, int indiceSup) {
		int indice=(indiceInf+ indiceSup)/2;
		Liga ligaSel= lista.get(indice);
		if (indiceInf>indiceSup) {
			return -1;
		}
		if (l.equals(ligaSel)) {
			return indice;
		}
		else {
			if (l.compareTo(ligaSel)>0) {
				return buscaLiga(lista, l, indice, lista.size()-1);
			}else
				return buscaLiga(lista, l, 0, indice);
		}
		
	}
	
	public static int buscaEquipo(ArrayList<Equipo> lista, Equipo e, int indiceInf, int indiceSup) {
		int indice=(indiceInf+ indiceSup)/2;
		for (Equipo eq:lista) {
			System.out.println(eq.getNombre());
		}
		Equipo equipoSel= lista.get(indice);
		if (indiceInf>indiceSup) {
			return -1;
		}
		if (e.equals(equipoSel)) {
			return indice;
		}
		else {
			Comparator<Equipo>comparador= new Comparator<Equipo>() {
				
				@Override
				public int compare(Equipo o1, Equipo o2) {
					return  o1.getNombre().compareTo(o2.getNombre());
				}
			};
			if (comparador.compare(e, equipoSel)>0) {
				return buscaEquipo(lista, e, indice, lista.size());
			}else
				return buscaEquipo(lista, e, 0, indice);
		}
		
	}
	
	
	
}

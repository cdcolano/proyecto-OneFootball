package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.omg.CORBA.portable.IndirectionException;

import clases.Equipo;
import clases.Jugador;
import clases.Liga;
import clases.Usuario;

public class ListasUsuario {

	@Test
	public void test() {
		Usuario u= new Usuario("a", "a", "a");
		ArrayList<Jugador>jugador= new ArrayList<Jugador>();
		ArrayList<Equipo>equipos= new ArrayList<Equipo>();
		ArrayList<Liga>ligas= new ArrayList<Liga>();
		
		Equipo a= new Equipo("athletic");
		Equipo b= new Equipo ("madrid");
		Equipo c= new Equipo ("barcelona");
		Equipo d= new Equipo ("atletico");
		Equipo e= new Equipo ("levante");
		
		
		Jugador j1= new Jugador("a",a);
		Jugador j2= new Jugador("a",b);
		Jugador j3= new Jugador("c",c);
		Jugador j4= new Jugador("c",d);
		Jugador j5= new Jugador("b",e);
		
		
		Liga l1= new Liga("a");
		Liga l2= new Liga("e");
		Liga l3= new Liga("w");
		Liga l4= new Liga("v");
		Liga l5= new Liga("t");
		
		
		equipos.add(a);
		equipos.add(b);
		equipos.add(c);
		equipos.add(d);
		equipos.add(e);
		
		
		jugador.add(j1);
		jugador.add(j2);
		jugador.add(j3);
		jugador.add(j4);
		jugador.add(j5);
		
		ligas.add(l1);
		ligas.add(l2);
		ligas.add(l3);
		ligas.add(l4);
		ligas.add(l5);
		
		
		u.setLigasSeguidas(ligas);
		u.setEquiposSeguidos(equipos);
		u.setJugadoresSeguidos(jugador);
		
		u.removeEquipo(b);
		for (Equipo eq:u.getEquiposSeguidos()) {
			if (eq.equals(b)) {
				fail();
			}
		}
		
		u.addEquipoSeguido(b);
		boolean falla=true;
		for (Equipo eq:u.getEquiposSeguidos()) {
			if (eq.equals(b)) {
				falla=false;
			}
		}
		if (falla) {
			fail();
		}
		
		
		u.removeJugador(j1);
		for (Jugador j:u.getJugadoresSeguidos()) {
			if (j.equals(j1)) {
				fail();
			}
		}
		
		u.addJugadorSeguido(j1);
		boolean falla2=true;
		for (Jugador j:u.getJugadoresSeguidos()) {
			if (j.equals(j1)) {
				falla2=false;
			}
		}
		
		
		
		if (falla2) {
			fail();
		}
		
		
		u.removeLiga(l5);
		for (Liga l:u.getLigasSeguidas()) {
			if (l.equals(l5)) {
				fail();
			}
		}
		
		u.addLigaSeguida(l5);
		boolean falla3=true;
		for (Liga l:u.getLigasSeguidas()) {
			if (l.equals(l5)) {
				falla3=false;
			}
		}
		if (falla3) {
			fail();
		}
		
		
		
		
	}

}

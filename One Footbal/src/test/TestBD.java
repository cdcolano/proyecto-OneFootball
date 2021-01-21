package test;

import static org.junit.Assert.*;

import org.junit.Test;

import clases.BD;
import clases.Equipo;
import clases.Jugador;
import clases.Liga;
import clases.Traspaso;
import clases.Usuario;

public class TestBD {

	@Test
	public void test() {
		Equipo e= new Equipo("athletic");
		Equipo equipoBD=BD.selectEquipo(e.getNombre());
		assertEquals(equipoBD.getLiga().getNombre(),"la liga");
		assertEquals(equipoBD.getGolesAFavor(), 2);
		assertEquals(equipoBD.getGolesEnContra(),4 );
		assertEquals(4, equipoBD.getPartidos().size());
		assertEquals(equipoBD.getPuntos(), 39);
		if (!e.equals(equipoBD)) {
			fail();
		}
		
	}
	
	
	@Test
	public void testJug() {
		Jugador j= new Jugador("messi");
		Equipo e= new Equipo("barcelona");
		Jugador jBD =BD.selectJugador(j.getNombre(),e);
		assertEquals(jBD.getDorsal(),10);
		assertEquals(jBD.getEdad(),35);
		assertEquals(jBD.getEquipo(),e);
		assertEquals(jBD.getImagen(),"/img/jugadores/messi.jpg");
		assertEquals(jBD.getNumAmarillas(),2);
		assertEquals(jBD.getNumRojas(),1);
		assertEquals(jBD.getNumGoles(),3);
		assertEquals(jBD.getNumAsistencias(),10);
		assertEquals(jBD.getPais(),"Argentina");
		assertEquals(jBD.getPosicion(),"ED");
	}
	
	@Test
	public void testUsuarioRegistro() {
		Usuario u= new Usuario("x","x","x");
		BD.insertarUsuario(u);
		if (BD.selectUsuario("x")==null) {
			fail();
		};
		BD.deleteUsuario(u);
		if (BD.selectUsuario("x")!=null) {
			fail();
		};
		
	}
	
	@Test
	public void testLiga() {
		Liga l= new Liga("la liga");
		Liga lBD=BD.selectLiga(l.getNombre());
		assertEquals(lBD.getImagen(), "/img/liga/laLiga.jpg");
		assertEquals(lBD.getJornadas().size(),2);
		assertEquals(lBD.getMaximosGoleadores().first().getNombre(),"muniain");
		assertEquals(lBD.getTarjetasAmarillas().first().getNombre(),"williams");
		assertEquals(lBD.getTarjetasRojas().first().getNombre(), "messi");
		assertEquals(lBD.getMaximosAsistentes().first().getNombre(), "messi");
		
	}
	

}

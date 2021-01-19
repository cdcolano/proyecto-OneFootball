package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import clases.Equipo;
import clases.Usuario;

public class TestRecur {

	@Test
	public void test() {
		Usuario u= new Usuario("a", "a", "a");
		Equipo a= new Equipo("athletic");
		Equipo b= new Equipo ("madrid");
		Equipo c= new Equipo ("barcelona");
		Equipo d= new Equipo ("atletico");
		Equipo e= new Equipo ("levante");
		ArrayList<Equipo>equipos= new ArrayList<Equipo>();
		ArrayList<Equipo>resultado= new ArrayList<Equipo>();
		equipos.add(a);
		resultado.add(a);
		equipos.add(b);
		resultado.add(d);
		equipos.add(c);
		equipos.add(d);
		equipos.add(e);
		resultado.add(c);
		resultado.add(e);
		resultado.add(b);
		u.setEquiposSeguidos(equipos);
		ArrayList<Equipo>ordenado= new ArrayList<Equipo>();
		ordenado=u.quickSortEquipo(equipos, 0, equipos.size()-1);
		for (int i=0;i<ordenado.size();i++) {
			System.out.println(ordenado.get(i).getNombre()+ "=" + resultado.get(i).getNombre());
			if (!ordenado.get(i).equals(resultado.get(i))) {
				System.out.println(i);
				System.out.println(ordenado.get(i).getNombre());
				fail();
			}
		}
		
		if (u.buscaEquipo(ordenado, a, 0, ordenado.size())!=0) {
			fail();
		}
		if (u.buscaEquipo(ordenado, b, 0, ordenado.size())!=4) {
			fail();
		}
		if (u.buscaEquipo(ordenado, c, 0, ordenado.size())!=2) {
			fail();
		}
		if (u.buscaEquipo(ordenado, d, 0, ordenado.size())!=1) {
			fail();
		}
		if (u.buscaEquipo(ordenado, e, 0, ordenado.size())!=3) {
			fail();
		}
		
	}

}

package clases;


import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeSet;



public class BD {

	/** Inicializa una BD SQLITE y devuelve una conexión con ella
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexión con la base de datos indicada. Si hay algún error, se devuelve null
	 */
	
	public static Connection initBD( String nombreBD ) {
		try {
		    Class.forName("org.sqlite.JDBC");
		    Connection con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
		    return con;
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}
	
	/** Crea las tablas de la base de datos. Si ya existen, las deja tal cual. Devuelve un statement para trabajar con esa base de datos
	 * @param con	Conexión ya creada y abierta a la base de datos
	 * @return	sentencia de trabajo si se crea correctamente, null si hay cualquier error
	 */
	public static Statement usarCrearTablasBD( Connection con ) {
		
		//statement.executeUpdate : Cuando queramos hacer create, insert, delete, update, drop
		//statement.executeQuery : Cuando queramos hacer select
		
		
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate("create table Usuario "+
						   "(nombre string, "+
						   " correoElect string, "
						   + "contrasena string)");
			
			statement.executeUpdate("create table Liga "+
						   "(nom string, "+
						   " img string "+
						   ")");
			
			statement.executeUpdate("create table Equipo " + 
						   " (nom string, " + 
							"img string, " + 
						   "puntos int, " + 
							"golesAFavor int, "
							+ "golesEnContra int, "
							+ "liga string" + ")");
			
			statement.executeUpdate("create table Jornada " +
							" (numJornada int, "
							+ "fechaInicio bigint, "
							+ "fechaFin bigint, "+
							"liga string)");
			
			statement.executeUpdate("create table Partido "+ 
								"(local string, "+
								"visitante string, "+
								"fecha bigint, "
								+ "golesLocal int, "
								+ "numJornada int, "
								+ "nomLiga string, "
								+ "golesVisitante int)"
					 );
			statement.executeUpdate("create table Noticia "  + 
					 			"(titulo string, "
					 			+ "img string, "
					 			+ "cuerpo string, "
					 			+ "fuente string) ");
			
			statement.executeUpdate("create table Traspaso "
								+ "(nomJugador string, "
								+ "nomEquipoVendedor string, "
								+ "nomEquipoComprador string, "
								+ "precio int, "
								+ "grado int, "
								+ "fecha bigint)");
			
			statement.executeUpdate("create table Jugador "
								+ "(nombre string, "
								+ "pais string, "
								+ "posicion string, "
								+ "edad int, "
								+ "numGoles int, "
								+ "dorsal int, "
								+ "numAmarillas int, "
								+ "numRojas int, "
								+ "numAsistencias int, "
								+ "nomEquipo string, "
								+ "img string)");
			
			statement.executeUpdate("create table Seleccion " + 
						   " (nom string, " + 
							"img string, " + 
						   "puntos int, " + 
							"golesAFavor int, "
							+ "golesEnContra int, "
							+ "liga string" + ")");
			
			statement.executeUpdate("create table UsuarioJugador " + 
							"(nomJugador string, "
							+ "nomEquipo string, "
							+ "correoUsuario string)");
			
			statement.executeUpdate("create table UsuarioEquipo " + 
					"(nomEquipo string, "
					+ "correoUsuario string)");
			
			statement.executeUpdate("create table UsuarioLiga " + 
					"(nomLiga string, "
					+ "correoUsuario string)");
			statement.executeUpdate("create table PartidoJugador " + 
					"(nomJugador string, "
					+ "nomEquipoJugador string, "
					+ "nomEquipoLocal string, "
					+ "nomEquipoVisitante string, "
					+ "numGoles int, "
					+ "numAsistencias int, "
					+ "numRojas int, "
					+ "numAmarillas int, "
					+ "fecha bigint)");
			statement.executeUpdate("create table EquipoNot " + 
					"(nomEquipo string, "
					+ "tituloNot string)");
			statement.executeUpdate("create table LigaNot " + 
					"(nomLiga string, "
					+ "tituloNot string)");
			//TODO relaciones de m a n
			//Usuario jugador
			//Usuario Liga
			//Usuario equipo
			//partido jugadores
			//noticia Equipo
			//noticia liga
			//TODO comprobar relaciones de 1 a n
			//Jornada->Partido
			//Liga->Equipo
			//Liga->Jornada
			//Liga->Jugadores
			//liga->noticias
			//equipo->noticias
			//Seleccion->jugadores
			//TODO relaciones 1 a 1
			//Partido equipos *2 tiene 2 relaciones 1 a 1 local y visitante
			//Equipo y traspasos*2 comprador y vendedor
			
			return statement;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/** Reinicia en blanco las tablas de la base de datos. 
	 * UTILIZAR ESTE MËTODO CON PRECAUCIÓN. Borra todos los datos que hubiera ya en las tablas
	 * @param con	Conexión ya creada y abierta a la base de datos
	 * @return	sentencia de trabajo si se borra correctamente, null si hay cualquier error
	 */
	public static Statement reiniciarBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate("drop table if exists Usuario");
			statement.executeUpdate("drop table if exists Liga");
			statement.executeUpdate("drop table if exists Equipo");
			statement.executeUpdate("drop table if exists Jornada");
			statement.executeUpdate("drop table if exists Partido");
			statement.executeUpdate("drop table if exists Jugador");
			statement.executeUpdate("drop table if exists Noticia");
			statement.executeUpdate("drop table if exists Seleccion");
			statement.executeUpdate("drop table if exists Traspaso");
			statement.executeUpdate("drop table if exists UsuarioJugador");
			statement.executeUpdate("drop table if exists UsuarioEquipo");
			statement.executeUpdate("drop table if exists UsuarioLiga");
			statement.executeUpdate("drop table if exists PartidoJugador");
			statement.executeUpdate("drop table if exists EquipoNot");
			statement.executeUpdate("drop table if exists LigaNot");
			
			
			//
	//		a
			return usarCrearTablasBD( con );
		} catch (SQLException e) {
			return null;
		}
	}
	
	/** Cierra la base de datos abierta
	 * @param con	Conexión abierta de la BD
	 * @param st	Sentencia abierta de la BD
	 */
	public static void cerrarBD( Connection con, Statement st ) {
		try {
			if (st!=null) st.close();
			if (con!=null) con.close();
		} catch (SQLException e) {
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////
	//                      Operaciones sobre tablas                   //
	/////////////////////////////////////////////////////////////////////
	
	public static Usuario selectUsuario(String correoElec) {
		//statement.executeUpdate : Cuando queramos hacer create, insert, delete, update, drop
		//statement.executeQuery : Cuando queramos hacer select
		
		
		
		
		String sql = "SELECT * FROM Usuario WHERE correoElect='"+correoElec+"'";
		Connection con = initBD("oneFootball.db");
		Statement st;
		try {
			st = con.createStatement(); //Creo el objeto sentencia
			ResultSet rs = st.executeQuery(sql); //Ejecutamos la consulta
			if(rs.next()) {
			//	System.out.println(rs.getString("nombre"));//rs.next() -> Devuelve true si rs tiene datos, false en caso contrario
				Usuario u= new Usuario();
				u.setContrasena(rs.getString("contrasena"));
				u.setCorreoElec(correoElec);
				u.setNombre(rs.getString("nombre"));
				u.setEquiposSeguidos(selectEquiposUsuario(u));
				u.setLigasSeguidas(selectLigasUsuario(u));
				u.setJugadoresSeguidos(selectJugadoresUsuario(u));
				cerrarBD(con, st);
				return u;
			}else {
				cerrarBD(con, st);
				return null;
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	
	}
	
	public static ArrayList<Equipo> selectEquiposUsuario(Usuario u) {
		String sql = "SELECT * FROM UsuarioEquipo WHERE correoUsuario ='"+u.getCorreoElec()+"'";
		Connection con = initBD("oneFootball.db");
		Statement st;
		try {
			st = con.createStatement(); //Creo el objeto sentencia
			ArrayList<Equipo>equipos= new ArrayList<Equipo>();
			ResultSet rs = st.executeQuery(sql); //Ejecutamos la consulta
			while(rs.next()) { 
				equipos.add(selectEquipo(rs.getString("nomEquipo")));
			}
			cerrarBD(con, st);
			return equipos;
		}catch(SQLException ex) {			
			ex.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Liga> selectLigasUsuario(Usuario u) {
		String sql = "SELECT * FROM UsuarioLiga WHERE correoUsuario ='"+u.getCorreoElec()+"'";
		Connection con = initBD("oneFootball.db");
		Statement st;
		try {
			st = con.createStatement(); //Creo el objeto sentencia
			ArrayList<Liga>ligas= new ArrayList<Liga>();
			ResultSet rs = st.executeQuery(sql); //Ejecutamos la consulta
			while(rs.next()) { 
				ligas.add(selectLiga(rs.getString("nomLiga")));
			}
			cerrarBD(con, st);
			return ligas;
		}catch(SQLException ex) {			
			ex.printStackTrace();
			return null;
		}
	}
	
	
	
	
	public static ArrayList<Liga> selectLigas() {
		String sql = "SELECT * FROM Liga";
		Connection con = initBD("oneFootball.db");
		Statement st;
		try {
			st = con.createStatement(); //Creo el objeto sentencia
			ArrayList<Liga>ligas= new ArrayList<Liga>();
			ResultSet rs = st.executeQuery(sql); //Ejecutamos la consulta
			while(rs.next()) { 
				Liga l= new Liga(rs.getString("nom"));
				l.setImagen( rs.getString("img"));
				l.setEquipos(selectEquipos(l));
				l.setJornadas(selectJornadas(l));
				l.setMaximosGoleadores(selectJugadoresGoleadores(l));
				ArrayList<Noticia>n= new ArrayList<Noticia>();
				for (Equipo e:l.getEquipos()) {
					n.addAll(e.getNoticias());
				}
			//	l.setTraspasos(selectTraspasos(l));
				l.setTarjetasAmarillas(selectJugadoresAmarillas(l));
				l.setTarjetasRojas(selectJugadoresRojas(l));
				l.setMaximosGoleadores(selectJugadoresGoleadores(l));
				l.setMaximosAsistentes(selectJugadoresAsistentes(l));
				ligas.add(l);
			}
			cerrarBD(con, st);
			return ligas;
		}catch(SQLException ex) {			
			ex.printStackTrace();
			return null;
		}
	}
	

	public static ArrayList<Equipo> selectEquipos() {
		String sql = "SELECT * FROM Equipo";
		Connection con = initBD("oneFootball.db");
		Statement st;
		try {
			st = con.createStatement(); //Creo el objeto sentencia
			ArrayList<Equipo>equipos= new ArrayList<Equipo>();
			ResultSet rs = st.executeQuery(sql); //Ejecutamos la consulta
			while(rs.next()) { 
				Equipo e= new Equipo(rs.getString("nom"));
				e.setImagen( rs.getString("img"));
				e.setGolesAFavor(rs.getInt("golesAFavor"));
				e.setGolesEnContra(rs.getInt("golesEnContra"));
				ArrayList<Jugador>jugadores= new ArrayList<Jugador>();
				e.setJugadores(selectJugadores(e));
				e.setPuntos(rs.getInt("puntos"));
				e.setNoticias(selectNoticias(e));
				e.setLiga(selectLiga(rs.getString("liga")));
				e.setPartidos(selectPartidos(e)); //TODO implantar con un or
				//e.setTraspasos(selectTraspasos(e));
				//TODO implantar con un or
				cerrarBD(con, st);
			}
			cerrarBD(con, st);
			return equipos;
		}catch(SQLException ex) {			
			ex.printStackTrace();
			return null;
		}
	}
	
	

	
	
	
	public static ArrayList<Jugador> selectJugadoresUsuario(Usuario u) {
		String sql = "SELECT * FROM UsuarioJugador WHERE correoUsuario ='"+u.getCorreoElec()+"'";
		Connection con = initBD("oneFootball.db");
		Statement st;
		try {
			st = con.createStatement(); //Creo el objeto sentencia
			ArrayList<Jugador>jugadores= new ArrayList<Jugador>();
			ResultSet rs = st.executeQuery(sql); //Ejecutamos la consulta
			while(rs.next()) { 
				jugadores.add(selectJugador(rs.getString("nomJugador"), selectEquipo(rs.getString("nomEquipo"))));
			}
			cerrarBD(con, st);
			return jugadores;
		}catch(SQLException ex) {			
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param nombre Nombre del usuario
	 * @param con Contraseña insertada por el usuario
	 * @return 0 - Si el usuario no está registrado
	 *         1 - Si el nick es correcto pero la contraseña no
	 *         2 - Si el nick es correcto y la contraseña también 
	 */
	public static int comprobarUsuario(String nombre, String con) {
		int resul = 0;
		String s = "SELECT * FROM Usuario WHERE nombre = '"+nombre+"'";
		
		Connection c = initBD("oneFootball.db");
		try {
			Statement st = c.createStatement();
			//Una select SIEMPRE devuelve un ResultSet
			ResultSet rs = st.executeQuery(s);
			if(rs.next()) { //Si hemos encontrado el usuario cuyo nick coincide con el recibido por parámetro
				String contrasenia = rs.getString("contrasena");
				if(contrasenia.equals(con))
					resul = 2;
				else
					resul = 1;
			}
			cerrarBD(c, st);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resul;
		
	}
	
	public static void insertarUsuario(String nombre, String correoElec,String con) {
		
		String s = "INSERT INTO Usuario VALUES('"+nombre+"','" + correoElec + "','"+  con+"')";
		Connection c = BD.initBD("oneFootball.db");
		try {
			Statement st = c.createStatement();
			st.executeUpdate(s);
			cerrarBD(c, st);
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
		
	}
	// 
	public static void insertarLiga(Liga l) {
		String s = "INSERT INTO Liga VALUES('"+l.getNombre()+"','"+l.getImagen()+  "' )";

		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
	}
	
	/*
	 * 
			statement.executeUpdate("create table Equipo " + 
						   " (nom string," + 
							"img string," + 
						   "puntos int," + 
							"golesAFavor int,"
							+ "golesEnContra int,"
							+ "liga string" + ")");
			
	 */
	public static void insertarEquipo(Equipo e) {
		String s = "INSERT INTO Equipo VALUES('"+e.getNombre()+"', '"+e.getImagen()+  "'," + e.getPuntos() + "," +e.getGolesAFavor() + "," +e.getGolesEnContra()
		+ ",'" + e.getLiga().getNombre() + "')";
		Connection con = BD.initBD("oneFootball.db");
		try {
			if (selectEquipo(e.getNombre())==null) {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/*
	 * statement.executeUpdate("create table Jornada " +
							" (numJornada int,"
							+ "fechaInicio bigint,"
							+ "fechaFin bigint,"+
							"liga string)");
	 */
	public static void insertarJornada(Jornada j) {
		String s = "INSERT INTO Jornada VALUES("+j.getNumJornada()+","+j.getFechaInicio().getTime()+  "," + j.getFechaFinal().getTime() 
				+ ",'"+ j.getLiga().getNombre() + "')";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	/*
	 * 	
			statement.executeUpdate("create table Jugador "
								+ "nombre string,"
								+ "pais string,"
								+ "posicion string,"
								+ "edad int,"
								+ "numGoles int,"
								+ "dorsal int,"
								+ "numAmarillas int,"
								+ "numRojas int,"
								+ "numAsistencias int,"
								+ "nomEquipo string,"
								+ "imagen string)");
	 */
	public static void insertarJugador(Jugador j) {
		if (selectJugador(j.getNombre(), j.getEquipo())==null) {
			String s = "INSERT INTO Jugador VALUES('"+j.getNombre()+"','"+j.getPais()+ "','" + j.getPosicion()+"'," + 
			 + j.getEdad()+"," + j.getNumGoles()+ "," + j.getDorsal()+","+ j.getNumAmarillas()+","+ j.getNumRojas()+","+
					j.getNumAsistencias()+ ",'"+ j.getEquipo().getNombre()+"','"+ j.getImagen() + "')";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/*
	 * 			statement.executeUpdate("create table Noticia "  + 
					 			"(titulo string,"
					 			+ "img string,"
					 			+ "cuerpo string,"
					 			+ "fuente string)");
	 */
	public static void insertarNoticia(Noticia n) {
		if (selectNoticia(n.getTitulo())==null) {
			String s= "INSERT INTO Noticia VALUES('"+n.getTitulo()+"','"+n.getImagen()+  "','" + n.getCuerpo() 
					+ "','"+ n.getFuente()+ "',"+ n.getFecha().getTime()+ ")";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	/*
	 * 	statement.executeUpdate("create table Partido "+ 
								"(local string,"+
								"visitante string,"+
								"fecha bigint,"
								+ "golesLocal int,"
								+ "numJornada int,"
								+ "nomLiga string"
								+ "golesVisitante int)"
	 */
	public static void insertarPartido(Partido p) {
		if (selectPartido(p.getLocal(), p.getVisitante(), p.getFecha())==null) {
			String s= "INSERT INTO Partido VALUES('"+p.getLocal().getNombre()+"','"+p.getVisitante().getNombre()+ "'," + p.getFecha().getTime() 
					+ ","+ p.getGolesLocal()+ ","+ p.getJornada().getNumJornada()+ ",'"+ p.getJornada().getLiga().getNombre()+"',"+ p.getGolesVisitante()+")";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/*
	 * statement.executeUpdate("create table Seleccion " + 
						   " (nom string," + 
							"img string," + 
						   "puntos int," + 
							"golesAFavor int,"
							+ "golesEnContra int,"
							+ "liga string" + ")")
	 */
	public static void insertarSeleccion(SeleccionNacional sel) {
		if(selectSeleccion(sel.getNombre())==null) {
			String s = "INSERT INTO Seleccion VALUES('"+sel.getNombre()+"','"+sel.getImagen()+  "'," + sel.getPuntos() + "," +sel.getGolesAFavor() + "," +sel.getGolesEnContra()
			+ ",'" + sel.getLiga().getNombre() + "')";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	/*
	 * statement.executeUpdate("create table Traspaso "
								+ "nomJugador string,"
								+ "nomEquipoVendedor string,"
								+ "nomEquipoComprador string,"
								+ "precio int,"
								+ "grado int,"
								+ "fecha bigint)");
	 */
	public static void insertarTraspaso(Traspaso t) {
		if (selectTraspaso(t.getJugador(), t.getFecha())==null) {
			String s="INSERT INTO Traspaso VALUES('"+t.getJugador().getNombre()+"','"+t.getVendedor().getNombre()+ "','" + t.getEquipo().getNombre() + "'," 
		+t.getPrecio()+ "," +t.getGrado()	+ "," + t.getFecha().getTime() + ")";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	
	/*
	 * statement.executeUpdate("create table Usuario "+
						   "(nombre string, "+
						   " correoElect string,"
						   + "contrasena string)");
	 */
	public static void insertarUsuario(Usuario u) {
		if (selectUsuario(u.getCorreoElec())==null) {
			String s="INSERT INTO Usuario VALUES('"+u.getNombre()+"','"+u.getCorreoElec()+ "','" + u.getContrasena() +  "')";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}else {
			System.out.println("esta aqui");
		}
	}
	
	
	/*
	 * statement.executeUpdate("create table UsuarioJugador" + 
							"(nomJugador string,"
							+ "nomEquipo string,"
							+ "correoUsuario string)");
	 */
	public static boolean insertarUsuarioJugador(Jugador j, Usuario u) {
		boolean existe=false;
		if(selectJugadoresUsuario(u)!=null) {
			for (Jugador jug2: selectJugadoresUsuario(u)) {
				if (jug2.equals(j)) {
					existe=true;
				}
			}
		}
		if (!existe) {
			String s="INSERT INTO UsuarioJugador VALUES('"+j.getNombre()+"','"+ j.getEquipo().getNombre()+ "','" + u.getCorreoElec()+  "')";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return existe;
	}
	
	public static boolean insertarUsuarioEquipo(Equipo e, Usuario u) {
		boolean existe=false;
		if(selectEquiposUsuario(u)!=null) {
			for (Equipo eq2: selectEquiposUsuario(u)) {
				if (eq2.equals(e)) {
					existe=true;
				}
			}
		}
		if (!existe) {
			String s="INSERT INTO UsuarioEquipo VALUES('"+e.getNombre()+"','"+ u.getCorreoElec()+  "')";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return existe;
	}
	
	
	
	
	public static boolean insertarUsuarioLiga(Liga l, Usuario u) {
		boolean existe=false;
		for (Liga li2: selectLigasUsuario(u)) {
			if (li2.equals(l)) {
				existe=true;
			}
		}
		if (!existe) {
			String s="INSERT INTO UsuarioLiga VALUES('"+l.getNombre()+"','"+ u.getCorreoElec()+  "')";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return existe;
	}
	
	
	/*
	 * statement.executeUpdate("create table PartidoJugador" + 
					"(nomJugador string,"
					+ "nomEquipoJugador string,"
					+ "nomEquipoLocal string,"
					+ "nomEquipoVisitante string,"
					+ "numGoles int,"
					+ "numAsistencias int,"
					+ "numRojas int,"
					+ "numAmarillas int,"
					+ "fecha bigint)");
	 */
	public static void insertarPartidoJugador(Partido p, Jugador j, int numGoles, int numAsistencias, int numRojas, int numAmarillas) {
		boolean existe=false;
		if (SelectTodosJugadoresPartido(p)!=null) {
			for (Jugador jug2: SelectTodosJugadoresPartido(p)) {
				if (jug2.equals(j)) {
					existe=true;
				}
			}
		}
		if (!existe) {
			String s="INSERT INTO PartidoJugador VALUES('"+j.getNombre()+"','"+ j.getEquipo().getNombre()+ "','"+ p.getLocal().getNombre()+"','"+
		p.getVisitante().getNombre()+ "',"+ numGoles +"," + numAsistencias + "," + numRojas +"," + numAmarillas + "," +p.getFecha().getTime()+
		")";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/*
	 * 	statement.executeUpdate("create table EquipoNot" + 
					"(nomEquipo string,"
					+ "tituloNot string)");
	 */
	public static void insertarEquipoNot(Equipo e, Noticia n) {
		boolean existe=false;
		for (Equipo eq2: selectEquiposNot(n)) {
			if (eq2.equals(e)) {
				existe=true;
			}
		}
		if (!existe) {
			String s="INSERT INTO EquipoNot VALUES('"+e.getNombre()+"','"+ n.getTitulo()+ "')";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	public static void insertarLigaNot(Liga l,Noticia n) {
		boolean existe=false;
		if (selectLigasNot(n)!=null) {
			for (Liga li2: selectLigasNot(n)) {
				if (li2.equals(l)) {
					existe=true;
				}
			}
		}
		if (!existe) {
			String s="INSERT INTO LigaNot VALUES('"+l.getNombre()+"','"+ n.getTitulo()+ "')";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void rellenaTraspasos(Equipo e) {
		e.setTraspasos(selectTraspasos(e));
	}
	
	
	//TODO AKIIIIIIIII
	public static Equipo selectEquipo (String nomEquipo) {
		
		String s = "SELECT * FROM Equipo WHERE nom='" + nomEquipo +"'";
		
		Connection con = initBD("OneFootball.db");
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(s);
			if (rs.next()) {
				Equipo e= new Equipo(nomEquipo);
				e.setImagen( rs.getString("img"));
				e.setGolesAFavor(rs.getInt("golesAFavor"));
				e.setGolesEnContra(rs.getInt("golesEnContra"));
				ArrayList<Jugador>jugadores= new ArrayList<Jugador>();
				e.setJugadores(selectJugadores(e));
				e.setPuntos(rs.getInt("puntos"));
				e.setNoticias(selectNoticias(e));
				e.setLiga(selectLiga(rs.getString("liga")));
				e.setPartidos(selectPartidos(e)); //TODO implantar con un or
				//e.setTraspasos(selectTraspasos(e));
				//TODO implantar con un or
				cerrarBD(con, st);
				return e;
			}else {
				cerrarBD(con, st);
				return null;
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
				//TODO logger
				return null;
		}
	}
	
	private static ArrayList<Jugador> SelectTodosJugadoresPartido(Partido p) {
		String s = "SELECT * FROM PartidoJugador WHERE nomEquipolocal='" + p.getLocal().getNombre() +"' and nomEquipoVisitante='" + p.getVisitante().getNombre() 
				+"'" +" and fecha=" + p.getFecha().getTime();
		Connection con = initBD("OneFootball.db");
		try {
			Statement st = con.createStatement();
			ArrayList<Jugador>jugadores= new ArrayList<Jugador>();
			ResultSet rs = st.executeQuery(s);	
			while (rs.next()) {
				jugadores.add(selectJugador(rs.getString("nomJugador"), selectEquipo(rs.getString("nomEquipoJugador"))));
			}
			cerrarBD(con, st);
			return jugadores;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	
	}
		
		
	public static ArrayList<Traspaso> selectTraspasos(Equipo e) {
		String s = "SELECT * FROM Traspaso WHERE nomEquipoVendedor='" + e.getNombre() +"' or nomEquipoComprador='" + e.getNombre() + "'";
		ArrayList<Traspaso>traspasos= new ArrayList<Traspaso>();
		Connection con = initBD("OneFootball.db");
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(s);	
			while(rs.next()) {
				Traspaso t= new Traspaso();
				if (rs.getString("nomEquipoVendedor").contentEquals(e.getNombre())) {
					t.setVendedor(e);
				}else if (rs.getString("nomEquipoComprador").contentEquals(e.getNombre())) {
					t.setEquipo(e);
				}
				t.setEquipo(selectEquipo(rs.getString("nomEquipoComprador")));
				t.setVendedor(selectEquipo(rs.getString("nomEquipoVendedor")));
				t.setJugador(selectJugador(rs.getString("nomJugador"), t.getVendedor()));
				t.setGrado(rs.getByte("grado"));
				t.setPrecio(rs.getLong("precio"));
				t.setFecha(new Date(rs.getLong("fecha")));
				
				traspasos.add(t);
			}
			cerrarBD(con, st);
			return traspasos;
		
		}catch (SQLException ex) {
			ex.printStackTrace();
			return null;//TODO logger
		}
	}

		public static Liga selectLiga (String nomLiga) {
			
			String s = "SELECT * FROM Liga WHERE nom='" + nomLiga +"'";
			
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);	
				if (rs.next()) {
					Liga l= new Liga(nomLiga);
						
					l.setImagen( rs.getString("img"));
					l.setEquipos(selectEquipos(l));
					l.setJornadas(selectJornadas(l));
					l.setMaximosGoleadores(selectJugadoresGoleadores(l));
					ArrayList<Noticia>n= new ArrayList<Noticia>();
					for (Equipo e:l.getEquipos()) {
						n.addAll(e.getNoticias());
					}
				//	l.setTraspasos(selectTraspasos(l));
					l.setTarjetasAmarillas(selectJugadoresAmarillas(l));
					l.setTarjetasRojas(selectJugadoresRojas(l));
					l.setMaximosGoleadores(selectJugadoresGoleadores(l));
					l.setMaximosAsistentes(selectJugadoresAsistentes(l));
					cerrarBD(con, st);
					return l;
				}else {
					cerrarBD(con, st);
					return null;
				}
				
			} catch (SQLException e) {
					e.printStackTrace();
					return null;
			}
			
		}
		
		
		
		private static ArrayList<Traspaso> selectTraspasos(Liga l) {
			ArrayList<Traspaso>traspasos= new ArrayList<Traspaso>();
			for (Equipo e: l.getEquipos()) {
				l.getTraspasos().addAll(e.getTraspasos());
			}
			return traspasos;
		}

		private static ArrayList<Noticia> selectNoticias(Liga l) {
			String s = "SELECT * FROM LigaNot WHERE nomLiga='" + l.getNombre() +"'";
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ArrayList<Noticia>noticias= new ArrayList<Noticia>();
				ResultSet rs = st.executeQuery(s);	
				while(rs.next()) {
					noticias.add(selectNoticia(rs.getString("tituloNot")));
				}
				cerrarBD(con, st);
				return noticias;
			}catch(SQLException ex) {
				ex.printStackTrace();
				return null;
			}
		}

		public static TreeSet<Jugador> selectJugadoresGoleadores(Liga l) {
			String in="('";
			int i=0;
			for (Equipo e:l.getEquipos()) { 
				if (i==l.getEquipos().size()-1) {
					in=in + e.getNombre() + "')";
				}else {
					in= in + e.getNombre() + "','";
				}
				i++;
			}
			System.out.println(in);
			String s = "SELECT * FROM Jugador WHERE nomEquipo in"+ in + " and numGoles>0";
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				TreeSet<Jugador>jugadores=  new TreeSet<Jugador>((Jugador o1, Jugador o2)-> {
					if (o1.getNumGoles()>o2.getNumGoles()) {
						return 1;
					}else if (o1.getNumGoles()==o2.getNumGoles()) {
						return 0;
					}
					else {
						return -1;
					}
				}
				
			);
				ResultSet rs = st.executeQuery(s);	
				while(rs.next()) {
					Jugador j= new Jugador();
					j.setNombre(rs.getString("nombre"));
					j.setEquipo(selectEquipo(l,rs.getString("nomEquipo")));
					j.setDorsal(rs.getInt("dorsal"));
					j.setEdad(rs.getInt("edad"));
					j.setImagen(rs.getString("img"));
					j.setLiga(l);
					j.setPais(rs.getString("pais"));
					j.setPosicion(rs.getString("posicion"));
					j.setNumAmarillas(rs.getInt("numAmarillas"));
					j.setNumRojas(rs.getInt("numRojas"));
					j.setNumGoles(rs.getInt("numGoles"));
					j.setNumAsistencias(rs.getInt("numAsistencias"));
					jugadores.add(j);
				}
				cerrarBD(con, st);
				return jugadores;
	
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
		// TODO AKIIII acelga
		public static Equipo selectEquipo(Liga l, String nomEquipo) {

			String s = "SELECT * FROM Equipo WHERE nom='" + nomEquipo +"'";
			
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);
				if (rs.next()) {
					Equipo e= new Equipo(nomEquipo);
					e.setImagen( rs.getString("img"));
					e.setGolesAFavor(rs.getInt("golesAFavor"));
					e.setGolesEnContra(rs.getInt("golesEnContra"));
					ArrayList<Jugador>jugadores= new ArrayList<Jugador>();
					e.setJugadores(selectJugadores(e));
					e.setPuntos(rs.getInt("puntos"));
					e.setNoticias(selectNoticias(e));
					e.setLiga(l);
					e.setPartidos(selectPartidos(e)); 
					//e.setTraspasos(selectTraspasos(e));
					cerrarBD(con, st);
					return e;
				}else {
					cerrarBD(con, st);
					return null;
				}
				
			} catch (SQLException e) {
					e.printStackTrace();
					//
					return null;
			}
		}
		
		public static TreeSet<Jugador> selectJugadoresAsistentes(Liga l) {
			String in="('";
			int i=0;
			for (Equipo e:l.getEquipos()) { 
				if (i==l.getEquipos().size()-1) {
					in=in + e.getNombre() + "')";
				}else {
					in= in + e.getNombre() + "','";
				}
				i++;
			}
			String s = "SELECT * FROM Jugador WHERE nomEquipo in" + in + " and numAsistencias>0";
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				TreeSet<Jugador>jugadores=  new TreeSet<Jugador>((Jugador o1, Jugador o2)-> {
					if (o1.getNumAsistencias()>o2.getNumAsistencias()) {
						return 1;
					}else if (o1.getNumAsistencias()==o2.getNumAsistencias()) {
						return 0;
					}
					else {
						return -1;
					}
				}
				
			);
				ResultSet rs = st.executeQuery(s);	
				while(rs.next()) {
					Jugador j= new Jugador();
					j.setNombre(rs.getString("nombre"));
					j.setEquipo(selectEquipo(l,rs.getString("nomEquipo")));
					j.setDorsal(rs.getInt("dorsal"));
					j.setEdad(rs.getInt("edad"));
					j.setImagen(rs.getString("img"));
					j.setLiga(l);
					j.setPais(rs.getString("pais"));
					j.setPosicion(rs.getString("posicion"));
					j.setNumAmarillas(rs.getInt("numAmarillas"));
					j.setNumRojas(rs.getInt("numRojas"));
					j.setNumGoles(rs.getInt("numGoles"));
					j.setNumAsistencias(rs.getInt("numAsistencias"));
					jugadores.add(j);
				}
				cerrarBD(con, st);
				return jugadores;
	
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public static TreeSet<Jugador> selectJugadoresAmarillas(Liga l) {
			String in="('";
			int i=0;
			for (Equipo e:l.getEquipos()) { 
				if (i==l.getEquipos().size()-1) {
					in=in + e.getNombre() + "')";
				}else {
					in= in + e.getNombre() + "','";
				}
				i++;
			}
			String s = "SELECT * FROM Jugador WHERE nomEquipo in " +in + " and numAmarillas>0";
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				TreeSet<Jugador>jugadores=  new TreeSet<Jugador>((Jugador o1, Jugador o2)-> {
					if (o1.getNumAmarillas()>o2.getNumAmarillas()) {
						return 1;
					}else if (o1.getNumAmarillas()==o2.getNumAmarillas()) {
						return 0;
					}
					else {
						return -1;
					}
				}
				
			);
				ResultSet rs = st.executeQuery(s);	
				while(rs.next()) {
					Jugador j= new Jugador();
					j.setNombre(rs.getString("nombre"));
					j.setEquipo(selectEquipo(l,rs.getString("nomEquipo")));
					j.setDorsal(rs.getInt("dorsal"));
					j.setEdad(rs.getInt("edad"));
					j.setImagen(rs.getString("img"));
					j.setLiga(l);
					j.setPais(rs.getString("pais"));
					j.setPosicion(rs.getString("posicion"));
					j.setNumAmarillas(rs.getInt("numAmarillas"));
					j.setNumRojas(rs.getInt("numRojas"));
					j.setNumGoles(rs.getInt("numGoles"));
					j.setNumAsistencias(rs.getInt("numAsistencias"));
					jugadores.add(j);
				}
				cerrarBD(con, st);
				return jugadores;
	
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public static TreeSet<Jugador> selectJugadoresRojas(Liga l) {
			String in="('";
			int i=0;
			for (Equipo e:l.getEquipos()) { 
				if (i==l.getEquipos().size()-1) {
					in=in + e.getNombre() + "')";
				}else {
					in= in + e.getNombre() + "','";
				}
				i++;
			}
			String s = "SELECT * FROM Jugador WHERE nomEquipo in" + in +" and numRojas>0";
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				TreeSet<Jugador>jugadores=new TreeSet<Jugador>((Jugador o1, Jugador o2)-> {
					if (o1.getNumRojas()>o2.getNumRojas()) {
						return 1;
					}else if (o1.getNumRojas()==o2.getNumRojas()) {
						return 0;
					}
					else {
						return -1;
					}
				}
				
			);
				ResultSet rs = st.executeQuery(s);	
				while(rs.next()) {
					Jugador j= new Jugador();
					j.setNombre(rs.getString("nombre"));
					j.setEquipo(selectEquipo(l,rs.getString("nomEquipo")));
					j.setDorsal(rs.getInt("dorsal"));
					j.setEdad(rs.getInt("edad"));
					j.setImagen(rs.getString("img"));
					j.setLiga(l);
					j.setPais(rs.getString("pais"));
					j.setPosicion(rs.getString("posicion"));
					j.setNumAmarillas(rs.getInt("numAmarillas"));
					j.setNumRojas(rs.getInt("numRojas"));
					j.setNumGoles(rs.getInt("numGoles"));
					j.setNumAsistencias(rs.getInt("numAsistencias"));
					jugadores.add(j);
				}
				cerrarBD(con, st);
				return jugadores;
	
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		

		public static ArrayList<Jornada> selectJornadas(Liga l) {
			String s = "SELECT * FROM Jornada WHERE liga='" + l.getNombre() +"'";
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ArrayList<Jornada>jornadas= new ArrayList<Jornada>();
				ResultSet rs = st.executeQuery(s);	
				while(rs.next()) {
					Jornada jor= new Jornada();
					jor.setFechaInicio(new Date(rs.getLong("fechaInicio")));
					jor.setFechaFinal(new Date(rs.getLong("fechaFin")));
					jor.setNumJornada(rs.getInt("numJornada"));
					jor.setLiga(l);
					jor.setPartidos(selectPartidos(jor));
					jornadas.add(jor);//
				}
				cerrarBD(con, st);
				return jornadas;
	
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		//TODO AKIIIII acelga
		public static  TreeSet<Equipo>selectEquipos(Liga l){
			String s = "SELECT * FROM Equipo WHERE liga='" + l.getNombre() +"'";
			Connection con = initBD("OneFootball.db");
			try {Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);	
				TreeSet<Equipo>listaEquipos= new TreeSet<Equipo>();
				while(rs.next()) {
						Equipo e= new Equipo(rs.getString("nom"));
						e.setImagen( rs.getString("img"));
						e.setLiga(l);
						e.setGolesAFavor(rs.getInt("golesAFavor"));
						e.setGolesEnContra(rs.getInt("golesEnContra"));
						ArrayList<Jugador>jugadores= new ArrayList<Jugador>();
						e.setJugadores(selectJugadores(e));
						e.setPuntos(rs.getInt("puntos"));
						e.setNoticias(selectNoticias(e));
						e.setPartidos(selectPartidos(e)); //TODO implantar con un or
					//	e.setTraspasos(selectTraspasos(e));
						listaEquipos.add(e);
				}
			cerrarBD(con, st);
			return listaEquipos;
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
			
			
		/**Busca jugadores que juegan en un equipo 
		 * @param e Equipo en el que juegan
		 * @return lista de jugadores
		 */
		private static ArrayList<Jugador> selectJugadores(Equipo e) {
			String s = "SELECT * FROM Jugador WHERE nomEquipo='" + e.getNombre() +"'";
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);
				ArrayList<Jugador>jugadores= new ArrayList<Jugador>();
				while (rs.next()) {
					Jugador j= new Jugador();
					j.setNombre(rs.getString("nombre"));
					j.setEquipo(e);
					j.setDorsal(rs.getInt("dorsal"));
					j.setEdad(rs.getInt("edad"));
					j.setImagen(rs.getString("img"));
					j.setLiga(e.getLiga());
					j.setPais(rs.getString("pais"));
					j.setPosicion(rs.getString("posicion"));
					j.setNumAmarillas(rs.getInt("numAmarillas"));
					j.setNumRojas(rs.getInt("numRojas"));
					j.setNumGoles(rs.getInt("numGoles"));
					j.setNumAsistencias(rs.getInt("numAsistencias"));
					jugadores.add(j);
				}
				cerrarBD(con, st);
				return jugadores;
			}catch (SQLException ex) {
				ex.printStackTrace();
				return null;
			}
		}
		
		
		public static ArrayList<Traspaso> selectTraspaso(Equipo e1, Equipo e2) {
			String s = "SELECT * FROM Traspaso WHERE nomEquipoVendedor='" + e1.getNombre() +"' and nomEquipoComprador='" + e2.getNombre()+ "'";
			Connection con = initBD("OneFootball.db");
			ArrayList<Traspaso>traspasos= new ArrayList<Traspaso>();
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);	
				while (rs.next()) {
					Traspaso t= new Traspaso();
					t.setEquipo(e2);
					t.setVendedor(e1);
					t.setJugador(BD.selectJugador(rs.getString("nomJugador"), e1));
					t.setGrado(rs.getByte("grado"));
					t.setPrecio(rs.getLong("precio"));
					t.setFecha(new Date(rs.getLong("fecha")));
					cerrarBD(con, st);
					traspasos.add(t);
				}
					cerrarBD(con, st);
					return traspasos;
			
			}catch (SQLException e) {
				e.printStackTrace();
				return traspasos;//
			}
		}
		
		

		public static Traspaso selectTraspaso(Jugador j, Date fecha) {
			String s = "SELECT * FROM Traspaso WHERE nomJugador='" + j.getNombre() +"' and fecha=" + fecha.getTime();
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);	
				if (rs.next()) {
					Traspaso t= new Traspaso(j,fecha);
					t.setEquipo(selectEquipo(rs.getString("nomEquipoComprador")));
					t.setVendedor(selectEquipo(rs.getString("nomEquipoVendedor")));
					t.setGrado(rs.getByte("grado"));
					t.setPrecio(rs.getLong("precio"));
					t.setFecha(new Date(rs.getLong("fecha")));
					cerrarBD(con, st);
					return t;
				}else {
					cerrarBD(con, st);
					return null;
				}
				
			
			}catch (SQLException e) {
				e.printStackTrace();
				return null;//
			}
		}
		
		
		
		public static Jornada selectJornada(int numJornada, Liga l) {
			String s = "SELECT * FROM Jornada WHERE liga='" + l.getNombre() +"' and numJornada=" + numJornada;
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);	
				if (rs.next()) {
					Jornada jor= new Jornada();
					jor.setFechaInicio(new Date(rs.getLong("fechaInicio")));
					jor.setFechaFinal(new Date(rs.getLong("fechaFin")));
					jor.setNumJornada(numJornada);
					jor.setLiga(l);
					jor.setPartidos(selectPartidos(jor));
					cerrarBD(con, st);
					return jor;
				}else {
					cerrarBD(con, st);
					return null;
				}
			
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
		public static ArrayList<Partido> selectPartidos(Jornada jor) {
			String s = "SELECT * FROM Partido WHERE nomLiga='" + jor.getLiga().getNombre() +"' and numJornada=" + jor.getNumJornada();
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ArrayList<Partido>partidos= new ArrayList<Partido>();
				ResultSet rs = st.executeQuery(s);
				while (rs.next()) {
					Partido p= new Partido();
					p.setLiga(jor.getLiga());
					p.setJornada(jor);
					p.setFecha(new Date(rs.getLong("fecha")));
					p.setLocal(selectEquipoPartido(p,rs.getString("local")));
					p.setVisitante(selectEquipoPartido(p,rs.getString("visitante")));
					p.setGolesLocal(rs.getInt("golesLocal"));
					p.setGolesVisitante(rs.getInt("golesVisitante"));
					p.setAmarillas(selectJugadoresAmarillas(p));
					p.setRojas(selectJugadoresRojas(p));
					p.setGoleadores(selectJugadoresGoleadores(p));
					p.setAsistentes(selectJugadoresAsistencias(p));
					partidos.add(p);
				}
				cerrarBD(con, st);
				return partidos;
			}catch (SQLException ex) {
				ex.printStackTrace();
				return null;
			}
		}
		

		private static ArrayList<Jugador> selectJugadoresAmarillas(Partido p) {
			String s = "SELECT * FROM PartidoJugador WHERE nomEquipolocal='" + p.getLocal().getNombre() +"' and nomEquipoVisitante='" + p.getVisitante().getNombre() 
					+"'and numAmarillas>0" +" and fecha=" + p.getFecha().getTime();
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ArrayList<Jugador>jugadores= new ArrayList<Jugador>();
				ResultSet rs = st.executeQuery(s);	
				while (rs.next()) {
					jugadores.add(selectJugador(rs.getString("nomJugador"), selectEquipo(rs.getString("nomEquipoJugador"))));
				}
				cerrarBD(con, st);
				return jugadores;
			}catch(Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		
		private static ArrayList<Jugador> selectJugadoresRojas(Partido p) {
			String s = "SELECT * FROM PartidoJugador WHERE nomEquipolocal='" + p.getLocal().getNombre() +"' and nomEquipoVisitante='" + p.getVisitante().getNombre() 
					+"'and numRojas>0" +" and fecha=" + p.getFecha().getTime();
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ArrayList<Jugador>jugadores= new ArrayList<Jugador>();
				ResultSet rs = st.executeQuery(s);	
				while (rs.next()) {
					jugadores.add(selectJugador(rs.getString("nomJugador"), selectEquipo(rs.getString("nomEquipoJugador"))));
				}
				cerrarBD(con, st);
				return jugadores;
			}catch(Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		
		private static ArrayList<Jugador> selectJugadoresGoleadores(Partido p) {
			String s = "SELECT * FROM PartidoJugador WHERE nomEquipolocal='" + p.getLocal().getNombre() +"' and nomEquipoVisitante='" + p.getVisitante().getNombre() 
					+"'and numGoles>0" +" and fecha=" + p.getFecha().getTime();
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ArrayList<Jugador>jugadores= new ArrayList<Jugador>();
				ResultSet rs = st.executeQuery(s);	
				while (rs.next()) {
					jugadores.add(selectJugador(rs.getString("nomJugador"), selectEquipo(rs.getString("nomEquipoJugador"))));
				}
				cerrarBD(con, st);
				return jugadores;
			}catch(Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		
		private static ArrayList<Jugador> selectJugadoresAsistencias(Partido p) {
			String s = "SELECT * FROM PartidoJugador WHERE nomEquipolocal='" + p.getLocal().getNombre() +"' and nomEquipoVisitante='" + p.getVisitante().getNombre() 
					+"'and numAsistencias>0" +" and fecha=" + p.getFecha().getTime();
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ArrayList<Jugador>jugadores= new ArrayList<Jugador>();
				ResultSet rs = st.executeQuery(s);	
				while (rs.next()) {
					jugadores.add(selectJugador(rs.getString("nomJugador"), selectEquipo(rs.getString("nomEquipoJugador"))));
				}
				cerrarBD(con, st);
				return jugadores;
			}catch(Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}




		public static Partido selectPartido(Equipo local,Equipo visitante, Date fecha) {
			String s = "SELECT * FROM Partido WHERE local='" + local.getNombre() +"' and visitante='" + visitante.getNombre() +"' and fecha=" + fecha.getTime();
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);	
				if (rs.next()) {	//primary key por tanto solo hay uno
					Partido p= new Partido(local,visitante , fecha);
					p.setLiga(selectLiga(rs.getString("nomLiga")));
					p.setJornada(selectJornada(rs.getInt("numJornada"), p.getLiga()));
					p.setGolesLocal(rs.getInt("golesLocal"));
					p.setGolesVisitante(rs.getInt("golesVisitante"));
					p.setAmarillas(selectJugadoresAmarillas(p));
					p.setRojas(selectJugadoresRojas(p));
					p.setGoleadores(selectJugadoresGoleadores(p));
					p.setAsistentes(selectJugadoresAsistencias(p));
					cerrarBD(con, st);
					return p;
				}else {
					cerrarBD(con, st);
					return null;
				}
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
//		b
		public static Jugador selectJugador(String nombre, Equipo e) {
			String s = "SELECT * FROM Jugador WHERE nombre='" + nombre +"' and nomEquipo='" + e.getNombre() +"'" ;
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);	
				if (rs.next()) {
					Jugador j= new Jugador();
					j.setNombre(nombre);
					j.setEquipo(e);
					j.setEdad(rs.getInt("edad"));
					j.setDorsal(rs.getInt("dorsal"));
					j.setLiga(e.getLiga());
					j.setImagen(rs.getString("img"));
					j.setNumRojas(rs.getInt("numRojas"));
					j.setNumAmarillas(rs.getInt("numAmarillas"));
					j.setPosicion(rs.getString("posicion"));
					j.setNumGoles(rs.getInt("numGoles"));
					j.setNumAsistencias(rs.getInt("numAsistencias"));
					j.setPais(rs.getString("pais"));
					cerrarBD(con, st);
					return j;
				}else {
					cerrarBD(con, st);
					return null;
				}
			
			}catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
			
			
			public static Noticia selectNoticia(String titulo) {
				String s = "SELECT * FROM Noticia WHERE titulo='" + titulo +"'" ;
				Connection con = initBD("OneFootball.db");
				try {
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(s);	
					if (rs.next()) {
						Noticia n= new Noticia();
						n.setTitulo(titulo);
						n.setFuente(rs.getString("fuente"));
						n.setImagen(rs.getString("img"));
						n.setCuerpo(rs.getString("cuerpo"));
						n.setFecha(new Date(rs.getLong("fecha")));
						cerrarBD(con, st);
						return n;
					}else {
						return null;
					}
			}catch(SQLException ex) {
				ex.printStackTrace();
				return null;
			}
		}
			
		
				
				
		public static ArrayList<Liga> selectLigasNot(Noticia n) {
			String s = "SELECT * FROM LigaNot WHERE tituloNot='" + n.getTitulo()+"'" ;
			ArrayList<Liga>ligas= new ArrayList<Liga>();
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);	
				while (rs.next()) {
					ligas.add(selectLiga(rs.getString("nomLiga")));
				}
				cerrarBD(con, st);
				return ligas;
			}catch(SQLException ex) {
				ex.printStackTrace();
				return null;
			}
		}

		public static ArrayList<Equipo> selectEquiposNot(Noticia n) {
			String s = "SELECT * FROM EquipoNot WHERE tituloNot='" + n.getTitulo()+"'" ;
			ArrayList<Equipo>equipos= new ArrayList<Equipo>();
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);	
				while (rs.next()) {
					equipos.add(selectEquipo(rs.getString("nomEquipo")));
				}
				cerrarBD(con, st);
				return equipos;
			}catch(SQLException ex) {
				ex.printStackTrace();
				return null;
			}
		}

		public static SeleccionNacional selectSeleccion (String pais) {
			
			String s = "SELECT * FROM Seleccion WHERE nom='" + pais +"'";
			
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);
				if (rs.next()) {
					SeleccionNacional sel= new SeleccionNacional(pais);
						
					sel.setImagen( rs.getString("img"));
					sel.setLiga(selectLiga(rs.getString("liga")));
					sel.setGolesAFavor(rs.getInt("golesAFavor"));
					sel.setGolesEnContra(rs.getInt("golesEnContra"));
					sel.setPuntos(rs.getInt("puntos"));
					sel.setNoticias(selectNoticias(sel));
					sel.setPartidos(selectPartidos(sel)); 
					sel.setTraspasos(new ArrayList<Traspaso>()); 
					sel.setSeleccionables(selectSeleccionables(sel));
					cerrarBD(con, st);
					return sel;
				}else {
					cerrarBD(con, st);
					return null;
				}
				
			} catch (SQLException e) {
					e.printStackTrace();
					return null;
			}
		}
		
		
		private static ArrayList<Noticia> selectNoticias(Equipo e) {
			String s = "SELECT * FROM EquipoNot WHERE nomEquipo='" + e.getNombre() +"'";
			
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);
				ArrayList<Noticia>noticias= new ArrayList<Noticia>();
				while (rs.next()) {
					noticias.add(selectNoticia(rs.getString("tituloNot")));					
				}
				cerrarBD(con, st);
				return noticias;
			}catch(SQLException ex) {
				ex.printStackTrace();
				return null;
			}
		}

		public static ArrayList<Partido> selectPartidos(Equipo e) {
			
			String s = "SELECT * FROM Partido WHERE local='" + e.getNombre() +"' or visitante='" + e.getNombre() +"'";
			
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ArrayList<Partido>partidos= new ArrayList<Partido>();
				ResultSet rs = st.executeQuery(s);
				while (rs.next()) {
					Partido p= new Partido();
					System.out.println(e.getNombre());
					System.out.println(e.getLiga());
					p.setLiga(e.getLiga());
					p.setJornada(selectJornada(rs.getInt("numJornada"), e.getLiga()));
					p.setFecha(new Date(rs.getLong("fecha")));
					p.setLocal(selectEquipoPartido(p,rs.getString("local")));
					p.setVisitante(selectEquipoPartido(p, rs.getString("visitante")));
					p.setGolesLocal(rs.getInt("golesLocal"));
					p.setGolesVisitante(rs.getInt("golesVisitante"));
					p.setAmarillas(selectJugadoresAmarillas(p));
					p.setRojas(selectJugadoresRojas(p));
					p.setGoleadores(selectJugadoresGoleadores(p));
					p.setAsistentes(selectJugadoresAsistencias(p));
					partidos.add(p);
				}
				cerrarBD(con, st);
				return partidos;
			}catch (SQLException ex) {
				ex.printStackTrace();
				return null;
			}
		}
		
		//TODO AKI ACELGA
		public static Equipo selectEquipoPartido(Partido p,String nomEquipo) {
			String s = "SELECT * FROM Equipo WHERE nom='" + nomEquipo +"'";
			
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);
				if (rs.next()) {
					Equipo e= new Equipo(nomEquipo);
					e.setImagen( rs.getString("img"));
					e.setGolesAFavor(rs.getInt("golesAFavor"));
					e.setGolesEnContra(rs.getInt("golesEnContra"));
					ArrayList<Jugador>jugadores= new ArrayList<Jugador>();
					e.setJugadores(selectJugadores(e));
					e.setPuntos(rs.getInt("puntos"));
					e.setNoticias(selectNoticias(e));
					e.setLiga(p.getLiga()); 
					//e.setTraspasos(selectTraspasos(e));
					cerrarBD(con, st);
					return e;
				}else {
					cerrarBD(con, st);
					return null;
				}
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
		

		public static ArrayList<Jugador> selectSeleccionables(SeleccionNacional sel){

			String s = "SELECT * FROM Jugador WHERE pais='" + sel.getNombre() +"'";
			ArrayList<Jugador>seleccionables= new ArrayList<Jugador>();
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);
				while (rs.next()) {
					Jugador j= new Jugador();
					j.setNombre(rs.getString("nombre"));
					j.setDorsal(rs.getInt("dorsal"));
					j.setEquipo(selectEquipo(rs.getString("nomEquipo")));
					j.setLiga(j.getEquipo().getLiga());
					j.setImagen(rs.getString("img"));
					j.setNumAmarillas(rs.getInt("numAmarillas"));
					j.setNumRojas(rs.getInt("numRojas"));
					j.setNumGoles(rs.getInt("numGoles"));
					j.setNumAsistencias(rs.getInt("numAsistencias"));
					j.setPais(sel.getNombre());
					j.setPosicion(rs.getString("posicion"));
					seleccionables.add(j);
				}
				cerrarBD(con, st);
				return seleccionables;
			}catch(SQLException ex) {
				ex.printStackTrace();
				return seleccionables;
			}
		}
		
		
		
		public static ResultSet selectTodas(String tabla) {
			String s = "SELECT * FROM " + tabla;
			Connection con = initBD("OneFootball.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(s);
				return rs;
		}catch(SQLException ex) {
			return null;
		}
	}
		

		
		
		
		
		
		public static void deleteEquipo(Equipo e) {
			String s="DELETE FROM Equipo WHERE nom='"+e.getNombre()+"'";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
				cerrarBD(con, st);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		
		public static void deleteLiga(Liga l) {
			String s="DELETE FROM Liga WHERE nom='"+l.getNombre()+"'";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
				cerrarBD(con, st);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		
	public static void deleteJornada(Jornada j) {
		String s="DELETE FROM Jornada WHERE liga='"+j.getLiga().getNombre()+"' and numJornada=" + j.getNumJornada();
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deleteJugador(Jugador j) {
		String s="DELETE FROM Jugador WHERE nombre='"+j.getNombre()+"' and nomEquipo='" + j.getEquipo().getNombre()+ "'";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void deleteNoticia(Noticia n) {
		String s="DELETE FROM Noticia WHERE titulo='"+n.getTitulo()+ "'";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deletePartido(Partido p) {
		String s="DELETE FROM Partido WHERE local='"+p.getLocal().getNombre()+ "' and visitante='" + p.getVisitante().getNombre() +"' and fecha=" + p.getFecha().getTime();
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deleteSeleccion(SeleccionNacional sel) {
		String s="DELETE FROM Seleccion WHERE nom='"+ sel.getNombre()+ "'";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deleteTraspaso(Traspaso t) {
		String s="DELETE FROM Traspaso WHERE nomJugador='"+ t.getJugador().getNombre()+ "' and fecha=" + t.getFecha().getTime();
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deletePartidoJugador(Partido p, Jugador j) {
		String s="DELETE FROM PartidoJugador WHERE nomJugador='"+ j.getNombre()+ "' and nomEquipoJugador='"+ j.getEquipo().getNombre()+ 
				"' and nomEquipoLocal='" + p.getLocal().getNombre() + "' and nomEquipoVisitante='" +p.getVisitante().getNombre() + 
	"' and fecha=" + p.getFecha().getTime();
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deleteLigaNot(Noticia n, Liga l) {
		String s="DELETE FROM LigaNot WHERE nomLiga='"+ l.getNombre()+ "' and tituloNot='"+ n.getTitulo()+ 
				"' ";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deleteEquipoNot(Noticia n, Equipo e) {
		String s="DELETE FROM EquipoNot WHERE nomEquipo='"+ e.getNombre()+ "' and tituloNot='"+ n.getTitulo()+ 
				"' ";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deleteJugadorUsuario(Usuario u , Jugador j) {
		String s="DELETE FROM UsuarioJugador WHERE nomJugador='"+ j.getNombre()+ "' and nomEquipo='"+ j.getEquipo().getNombre()
				+"' and correoUsuario='"+ u.getCorreoElec()+ 
				"'";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deleteEquipoUsuario(Usuario u , Equipo e) {
		String s="DELETE FROM UsuarioEquipo WHERE nomEquipo='"+ e.getNombre()
				+"' and correoUsuario='"+ u.getCorreoElec()+ 
				"' ";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deleteLigaUsuario(Usuario u , Liga l) {
		String s="DELETE FROM UsuarioLiga WHERE nomLiga='"+ l.getNombre()
				+"' and correoUsuario='"+ u.getCorreoElec()+ 
				"' ";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void deleteUsuario(Usuario u) {
		String s="DELETE FROM Usuario WHERE correoElec='"+ u.getCorreoElec()+ "'";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
		
		
	public static void updateUsuario(Usuario u, String nombre, String correoElec, String contrasena) {
		String s="UPDATE Usuario set nombre='" + nombre +"', correoElect='" + correoElec+ "', contrasena='"+ contrasena +
	"' WHERE correoElect='"+ u.getCorreoElec()+ "'";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	
	/*
	 * create table Equipo " + 
						   " (nom string, " + 
							"img string, " + 
						   "puntos int, " + 
							"golesAFavor int, "
							+ "golesEnContra int, "
							+ "liga string" + ")"
	 */
	public static void updateEquipo(Equipo e, String nombre,String img, int puntos, int golesAFavor, int golesEnContra,String nomLiga ) {
		String s="UPDATE Equipo set nom='" + nombre +"', img='" + img + "', puntos="+ puntos +
				", golesAFavor="+ golesAFavor + ", golesEnContra="+ golesEnContra+ ", liga='" +nomLiga+"'"+
				" WHERE nom='"+ e.getNombre()+ "'";
					Connection con = BD.initBD("oneFootball.db");
					try {
						Statement st = con.createStatement();
						st.executeUpdate(s);
						cerrarBD(con, st);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
	}
	
	public static void updateLiga(Liga l, String nomLiga, String img) {
		String s="UPDATE Liga set nom='" + nomLiga +"', img='" + img + "'"+
				" WHERE nom='"+ l.getNombre()+ "'";
					Connection con = BD.initBD("oneFootball.db");
					try {
						Statement st = con.createStatement();
						st.executeUpdate(s);
						cerrarBD(con, st);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
			
	}
	
	/*
	 * numJornada int, "
		+ "fechaInicio bigint, 
		+ "fechaFin bigint, "+
		"liga string)"
	 */
	public static void updateJornada(Jornada j, int numJornada, Date fechaInicio, Date fechaFin, String nomLiga ) {
			String s="UPDATE Jornada set numJornada=" + numJornada+ ", fechaInicio=" + fechaInicio.getTime() + ", fechaFin="+
					fechaFin.getTime() + ", liga='" + nomLiga + "'" + 
					" WHERE numJornada="+ j.getNumJornada()+ " and liga='" + nomLiga +"'";
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
				cerrarBD(con, st);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
	}
	
	/*
	 * (local string, "+
		"visitante string, "+
		"fecha bigint, "
	+ "golesLocal int, "
	+ "numJornada int, "
	+ "nomLiga string, "
 	+ "golesVisitante int)
	 */
	public static void updatePartido(Partido p, String local, String visitante, Date fecha, int golesLocal, int numJornada,
			String nomLiga, int golesVisitante) {
		String s="UPDATE Partido set local='" + local+ "', visitante='" + visitante + "', fecha="+
				fecha.getTime() + ", golesLocal="+ golesLocal + ", numJornada=" + numJornada + ", nomliga='" + nomLiga + "', golesVisitante=" +
				golesVisitante + 
				" WHERE local='"+ p.getLocal().getNombre()+ "' and visitante='" + p.getVisitante().getNombre() +"' and fecha=" + p.getFecha().getTime();
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/*
	 * "create table Traspaso "
								+ "(nomJugador string, "
								+ "nomEquipoVendedor string, "
								+ "nomEquipoComprador string, "
								+ "precio int, "
								+ "grado int, "
								+ "fecha bigint)");
	 */
	public static void updateTraspaso(Traspaso t, String nomJugador, String nomEquipoVendedor, String nomEquipoComprador, long precio, byte grado, Date fecha) {
		String s="UPDATE Traspaso set nomJugador='" + nomJugador+ "', nomEquipoComprador='" + nomEquipoComprador + "', nomEquipoVendedor='"+ 
	nomEquipoVendedor +"', fecha="+ fecha.getTime() + ", precio="+ precio + ", grado=" + grado +
				" WHERE nomJugador='"+ t.getJugador().getNombre()+ "' and fecha=" + t.getFecha().getTime();
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/*
	 * statement.executeUpdate("create table Noticia "  + 
					 			"(titulo string, "
					 			+ "img string, "
					 			+ "cuerpo string, "
					 			+ "fuente string) ");
	 */
	public static void updateNoticia(Noticia n, String titulo, String img, String cuerpo, String fuente) {
		String s="UPDATE Noticia set titulo='" + titulo+ "', img='" + img + "', cuerpo='"+ 
				cuerpo +"', fuente='"+ fuente+ "'"+
				" WHERE titulo='"+ n.getTitulo() + "'";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void updateSeleccion(SeleccionNacional sel, String nombre,String img, int puntos, int golesAFavor, int golesEnContra,String nomLiga ) {
		String s="UPDATE Seleccion set nom='" + nombre +"', img='" + img + "', puntos="+ puntos +
				", golesAFavor="+ golesAFavor + ", golesEnContra="+ golesEnContra+ ", liga='" +nomLiga+"'"+
				" WHERE nom='"+ sel.getNombre()+ "'";
					Connection con = BD.initBD("oneFootball.db");
					try {
						Statement st = con.createStatement();
						st.executeUpdate(s);
						cerrarBD(con, st);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
	}
	
	
	/*
	 * 							"(nombre string, "
								+ "pais string, "
								+ "posicion string, "
								+ "edad int, "
								+ "numGoles int, "
								+ "dorsal int, "
								+ "numAmarillas int, "
								+ "numRojas int, "
								+ "numAsistencias int, "
								+ "nomEquipo string, "
								+ "img string)")
	 */
	public static void updateJugador(Jugador j,String nombre, String pais, String posicion, int edad, int numGoles, int dorsal, int numAmarillas,
			int numRojas, int numAsistencias, String nomEquipo, String img) {
		String s="UPDATE Jugador set nombre='" + nombre +"', pais='" + pais+"',posicion='"+ posicion + "', img='"+
				 img + "', nomEquipo='"+ nomEquipo +
				"', numGoles="+ numGoles + ", edad="+ edad+ ", numAmarillas=" +numAmarillas+", numRojas="+ numRojas+
				", numAsistencias="+ numAsistencias+ ", dorsal=" + dorsal+
				" WHERE nombre='"+ j.getNombre()+ "' and nomEquipo='" + j.getEquipo().getNombre() + "'";
					Connection con = BD.initBD("oneFootball.db");
					try {
						Statement st = con.createStatement();
						st.executeUpdate(s);
						cerrarBD(con, st);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
	
	}
	
	/*
	 * "(nomJugador string, "
					+ "nomEquipoJugador string, "
					+ "nomEquipoLocal string, "
					+ "nomEquipoVisitante string, "
					+ "numGoles int, "
					+ "numAsistencias int, "
					+ "numRojas int, "
					+ "numAmarillas int, "
					+ "fecha bigint)");
	 */
	public static void updatePartidoJugador(Partido p, Jugador j, String nomJugador, String nomEquipoJugador, String nomEquipoLocal,
			String nomEquipoVisitante, int numGoles, int numAsistencias, int numRojas, int numAmarillas, Date fecha) {
		String s="UPDATE PartidoJugador set nomJugador='" + nomJugador +"', nomEquipoJugador='" + nomEquipoJugador+
				"', nomEquipoVisitante='"+ nomEquipoVisitante + "', nomEquipoLocal='"+
				 nomEquipoLocal + "', numGoles="+ numGoles +
				", numAmarillas=" +numAmarillas+", numRojas="+ numRojas+
				", numAsistencias="+ numAsistencias+ ", fecha=" + fecha.getTime()+
				" WHERE nomJugador='"+ j.getNombre()+ "' and nomEquipoJugador='" + j.getEquipo().getNombre() + "' and nomEquipoLocal='"+
				p.getLocal().getNombre() + "' and nomEquipoVisitante='" + p.getVisitante().getNombre() + "' and fecha=" + p.getFecha().getTime();
					Connection con = BD.initBD("oneFootball.db");
					try {
						Statement st = con.createStatement();
						st.executeUpdate(s);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
	
	}
	
	public static void updateLigaNot(Liga l, Noticia n, String nomLiga, String titulo) {
		String s="UPDATE LigaNot set nomLiga='" + nomLiga +"', tituloNot='" + titulo + "'"+
				" WHERE nomLiga='"+ l.getNombre()+ "' and tituloNot='" + n.getTitulo() + "'";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public  static void updateEquipoNot(Equipo e, Noticia n, String nomLiga, String titulo) {
		String s="UPDATE EquipoNot set nomEquipo='" + nomLiga +"', tituloNot='" + titulo + "'"+
				" WHERE nomEquipo='"+ e.getNombre()+ "' and tituloNot='" + n.getTitulo() + "'";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void updateUsuarioJugador(Usuario u, Jugador j, String correo, String nomJugador, String nomEquipo) {
		String s="UPDATE UsuarioJugador set nomJugador='" + nomJugador +"', nomEquipo='" + nomEquipo + "', correoUsuario='"+ correo+ "'"+
				" WHERE nomJugador='"+ j.getNombre()+ "' and nomEquipo='" + j.getEquipo().getNombre() + "' and correoUsuario='" + u.getCorreoElec()+"'";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void updateUsuarioEquipo(Usuario u, Equipo e, String correo, String nomEquipo) {
		String s="UPDATE UsuarioEquipo set nomEquipo='" + nomEquipo + "', correoUsuario='"+ correo+ "'"+
				" WHERE nomEquipo='" + e.getNombre() + "' and correoUsuario='" + u.getCorreoElec()+"'";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void updateUsuarioLiga(Usuario u, Liga l, String correo, String nomLiga) {
		String s="UPDATE UsuarioLiga set nomLiga='" + nomLiga + "', correoUsuario='"+ correo+ "'"+
				" WHERE nomLiga='" + l.getNombre() + "' and correoUsuario='" + u.getCorreoElec()+"'";
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static ArrayList<Usuario>selectUsuarios(){
		ArrayList<Usuario>usuarios= new ArrayList<Usuario>();
		try {
			ResultSet rs=selectTodas("Usuario");
			while (rs.next()) {
				usuarios.add(selectUsuario(rs.getString("correoElect")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
	/**
	 * Metodo que añade una columna para la fecha que se introdujo despues
	 * solo se utiliza una vez
	 */
	public static void actualizaTablaNoticias() {
		String s="ALTER TABLE NOTICIA ADD FECHA BIGINT";
		String s2="Update Noticia set fecha=" + System.currentTimeMillis();
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			Statement st2=con.createStatement();
			st2.executeUpdate(s2);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void deleteUsuarioJugador(Usuario u,Jugador j) {
			String s="DELETE FROM UsuarioJugador WHERE correoUsuario='"+ u.getCorreoElec()+ "' and nomJugador='" + j.getNombre()+
					"' and nomEquipo ='" + j.getEquipo().getNombre() + "'" ;
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
				cerrarBD(con, st);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		
	}
	
	
	public static void deleteUsuarioEquipo(Usuario u,Equipo e) {
		String s="DELETE FROM UsuarioEquipo WHERE correoUsuario='"+ u.getCorreoElec()+ "' and nomEquipo ='" + e.getNombre() + "'" ;
		Connection con = BD.initBD("oneFootball.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
		
		
		public static void deleteUsuarioLiga(Usuario u,Liga l) {
			String s="DELETE FROM UsuarioLiga WHERE correoUsuario='"+ u.getCorreoElec()+ "' and nomLiga='" + l.getNombre()+ "'" ;
			Connection con = BD.initBD("oneFootball.db");
			try {
				Statement st = con.createStatement();
				st.executeUpdate(s);
				cerrarBD(con, st);
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
	

	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


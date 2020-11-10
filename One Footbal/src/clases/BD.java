package clases;


import java.sql.*;
import java.util.ArrayList;

import Datos.Libro;

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
						   "(nick string, "+
						   " con string)");
			statement.executeUpdate("create table Libro "+
						   "(cod string, "+
						   " denom string, "+
						   " unidades int,"+
						   " rutaFoto string,"+
						   " curso int,"+
						   " asignatura string)");

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
	
	public static boolean existeUsuario(String nick) {
		//statement.executeUpdate : Cuando queramos hacer create, insert, delete, update, drop
		//statement.executeQuery : Cuando queramos hacer select
		
		
		boolean existe = false;
		
		String sql = "SELECT * FROM Usuario WHERE nick ='"+nick+"'";
		Connection con = initBD("academia.db");
		Statement st;
		try {
			st = con.createStatement(); //Creo el objeto sentencia
			ResultSet rs = st.executeQuery(sql); //Ejecutamos la consulta
			if(rs.next()) { //rs.next() -> Devuelve true si rs tiene datos, false en caso contrario
				existe = true;
			}
			cerrarBD(con, st);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe;
	}
	
	/**
	 * 
	 * @param nick Nick insertado por el usuario
	 * @param con Contraseña insertada por el usuario
	 * @return 0 - Si el usuario no está registrado
	 *         1 - Si el nick es correcto pero la contraseña no
	 *         2 - Si el nick es correcto y la contraseña también 
	 */
	public static int comprobarUsuario(String nick, String con) {
		int resul = 0;
		String s = "SELECT * FROM Usuario WHERE nick = '"+nick+"'";
		
		Connection c = initBD("academia.db");
		try {
			Statement st = c.createStatement();
			//Una select SIEMPRE devuelve un ResultSet
			ResultSet rs = st.executeQuery(s);
			if(rs.next()) { //Si hemos encontrado el usuario cuyo nick coincide con el recibido por parámetro
				String contrasenia = rs.getString("con");
				if(contrasenia.equals(con))
					resul = 2;
				else
					resul = 1;
			}
			cerrarBD(c, st);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resul;
		
	}
	
	public static void insertarUsuario(String nick, String con) {
		
		String s = "INSERT INTO Usuario VALUES('"+nick+"','"+con+"')";
		Connection c = BD.initBD("academia.db");
		try {
			Statement st = c.createStatement();
			st.executeUpdate(s);
			cerrarBD(c, st);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void insertarLibro(String cod, String denom, int unidades, String rutaFoto, int curso, String asignatura) {
		String s = "INSERT INTO Libro VALUES('"+cod+"','"+denom+"',"+unidades+",'"+rutaFoto+"',"+curso+",'"+asignatura+"')";
		
		Connection con = BD.initBD("academia.db");
		try {
			Statement st = con.createStatement();
			st.executeUpdate(s);
			cerrarBD(con, st);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> obtenerTodasLasRutas() {
		ArrayList<String> rutas = new ArrayList<>();
		String s = "SELECT rutaFoto FROM Libro";
		
		Connection con = initBD("academia.db");
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(s);
			while(rs.next()) { //Mientras rs tenga datos
				rutas.add(rs.getString("rutaFoto"));
			}
			cerrarBD(con, st);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rutas;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
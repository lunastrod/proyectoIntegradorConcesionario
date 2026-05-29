package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.dam.model.data.Trabajador;

/*
CREATE TABLE IF NOT EXISTS "Trabajador" (
	"id_trabajador"	INTEGER,
	"nombre_apellidos"	varchar(100),
	"password_trabajador"	varchar(100),
	"es_admin"	INTEGER,
	CONSTRAINT "pk_id_trabajo" PRIMARY KEY("id_trabajador" AUTOINCREMENT)
);
 */
public class TrabajadorDAO {
	private AccesoBD bd;
	public static final String NOM_TABLA = "Trabajador";
	public static final String COL_ID_TRABAJADOR = "id_trabajador";
	public static final String COL_NOMBRE_TRABAJADOR = "nombre_apellidos";
	public static final String COL_PASSWORD_TRABAJADOR = "password_trabajador";
	public static final String COL_ES_ADMIN = "es_admin";
	
	public TrabajadorDAO(AccesoBD bd) {
		this.bd = bd;
	}
	
	public int insert(Trabajador t) {
		String sentencia = "INSERT INTO " + NOM_TABLA + " (" + COL_NOMBRE_TRABAJADOR
				+ ", " + COL_PASSWORD_TRABAJADOR + ", " + COL_ES_ADMIN + ") VALUES (?, ?, ?)";
		
		Connection con = null;
		PreparedStatement stmt = null;
		int res = -1;
		
		try {
			con = bd.getConexion();
			stmt = con.prepareStatement(sentencia);
			stmt.setString(1, t.getNombreApellidos());
			stmt.setString(2, t.getPasswordTrabajador());
			stmt.setInt(3, t.getEsAdmin());
			res = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public int delete(String nombreApellidos) {
		String sentencia = "DELETE FROM " + NOM_TABLA + " WHERE " 
	+ COL_NOMBRE_TRABAJADOR + " = ?";
		
		Connection con = null;
		PreparedStatement stmt = null;
		int res = -1;
		
		try {
			con = bd.getConexion();
			stmt = con.prepareStatement(sentencia);
			stmt.setString(1, nombreApellidos);
			res = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	/*
	public int update(Trabajador t) {
		String sentencia = "UPDATE " + NOM_TABLA + " SET " + COL_NOMBRE_TRABAJADOR + " = ?, "
				+ COL_PASSWORD_TRABAJADOR + " = ? WHERE " + COL_ID_TRABAJADOR + " = ?";
		
		Connection con = null;
		PreparedStatement stmt = null;
		int res = -1;
		try {
			con = bd.getConexion();
			stmt = con.prepareStatement(sentencia);
			stmt.setString(1, t.getNombreApellidos());
			stmt.setString(2, t.getPasswordTrabajador());
			res = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	*/
	public ArrayList<Trabajador> selectAllTrabajadores() {
		String sentencia = "SELECT * FROM " + NOM_TABLA + " ORDER BY " + COL_ID_TRABAJADOR;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
		try {
			con = bd.getConexion();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sentencia);
			while (rs.next()) {
				int idTrabajador = rs.getInt(COL_ID_TRABAJADOR);
				String nombreTrabajador = rs.getString(COL_NOMBRE_TRABAJADOR);
				String passwordTrabajador = rs.getString(COL_PASSWORD_TRABAJADOR);
				int esAdmin = rs.getInt(COL_ES_ADMIN);
				trabajadores.add(new Trabajador(idTrabajador, nombreTrabajador, passwordTrabajador, esAdmin));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            try {if (rs != null) {rs.close();}
            } catch (Exception e) {e.printStackTrace();}
            try {if (stmt != null) {stmt.close();}
            } catch (Exception e) {e.printStackTrace();}
            try {if (con != null) {con.close();}}
            catch (Exception e) {e.printStackTrace();}
        }
		return trabajadores;
		
	}
}

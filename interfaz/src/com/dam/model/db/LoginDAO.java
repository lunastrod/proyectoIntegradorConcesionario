package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dam.model.data.Login;

public class LoginDAO{
	
	private static final String NOM_TABLA = "Trabajador";
	private static final String COL_NOMBRE = "nombre_apellidos";
	private static final String COL_PASSWORD = "password_trabajador";
	
	private AccesoBD bd;
	
	public LoginDAO(AccesoBD bd) {
		this.bd = bd;
	}
	
	public boolean iniciarSesion(Login login) {
		boolean resultado = false;
		String nombre=login.getUsuario();
		String passwd=login.getPasswd();
		
		String query = "SELECT * FROM " + NOM_TABLA + " WHERE "
		+ COL_NOMBRE + " = ? AND " + COL_PASSWORD + " = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;

		try {
			con = bd.getConexion();

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nombre);
			pstmt.setString(2, passwd);

			rslt = pstmt.executeQuery();
			
			if (rslt.next()) {
				resultado = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rslt != null) {
					rslt.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}
	
	public int crearUsuario(Login login) {
		int res = 0;
		
		String query = "INSERT INTO " + NOM_TABLA + " ("
		+ COL_NOMBRE + ", " + COL_PASSWORD + ") VALUES (?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = bd.getConexion();

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, login.getUsuario());
			pstmt.setString(2, login.getPasswd());

			res = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public int eliminarUsuario(String nombre) {
		int res = 0;
		
		String query = "DELETE FROM " + NOM_TABLA + " WHERE " + COL_NOMBRE + " = ?"; 
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = bd.getConexion();

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nombre);

			res = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}

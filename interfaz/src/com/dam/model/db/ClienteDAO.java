package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.dam.model.data.Cliente;

/*
CREATE TABLE IF NOT EXISTS "Cliente" (
	"id_cliente"	INTEGER,
	"nombre_apellidos"	varchar(100),
	"metodo_pago"	varchar(50),
	CONSTRAINT "pk_id_cli" PRIMARY KEY("id_cliente" AUTOINCREMENT)
);
*/

public class ClienteDAO {
	
	private AccesoBD bd;
	public static final String NOM_TABLA = "Cliente";
	public static final String COL_ID_CLIENTE = "id_cliente";
	public static final String COL_NOMBRE_APELLIDOS = "nombre_apellidos";
	public static final String COL_METODO_PAGO = "metodo_pago";
	
	public ClienteDAO(AccesoBD bd) {
		this.bd = bd;
	}
	
	public int insert(Cliente c) {
		String sentencia = "INSERT INTO " + NOM_TABLA + " (" + COL_NOMBRE_APELLIDOS
				+ ", " + COL_METODO_PAGO + ") VALUES (?, ?)";
		
		Connection con = null;
		PreparedStatement stmt = null;
		int res = -1;
		
		try {
			con = bd.getConexion();
			stmt = con.prepareStatement(sentencia);
			stmt.setString(1, c.getNombreApellidos());
			stmt.setString(2, c.getMetodoPago());
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
	
	public ArrayList<Cliente> selectAllTrabajadores() {
		String sentencia = "SELECT * FROM " + NOM_TABLA + " ORDER BY " + COL_ID_CLIENTE;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			con = bd.getConexion();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sentencia);
			while (rs.next()) {
				int clienteId = rs.getInt(COL_ID_CLIENTE);
				String nombreCliente = rs.getString(COL_NOMBRE_APELLIDOS);
				String metodoPago = rs.getString(COL_METODO_PAGO);
				clientes.add(new Cliente(clienteId, nombreCliente, metodoPago));
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
		return clientes;
		
	}
	
}

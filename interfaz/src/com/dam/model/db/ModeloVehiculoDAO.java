package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.dam.model.data.ModeloVehiculo;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
CREATE TABLE IF NOT EXISTS "Modelo" (
	"id_modelo"	INTEGER,
	"nombre_modelo"	varchar(80),
	"numero_plazas"	INTEGER,
	"numero_puertas"	INTEGER,
	"tipo_vehiculo"	varchar(100),
	"tipo_propulsion"	varchar(100),
	"traccion"	varchar(20),
	"marca"	varchar(70),
	"tipo_transmision"	varchar(30),
	CONSTRAINT "pk_id" PRIMARY KEY("id_modelo" AUTOINCREMENT)
);
*/

public class ModeloVehiculoDAO {
    private AccesoBD bd;
    public static final String NOM_TABLA = "Modelo";
    public static final String COL_ID_MODELO = "id_modelo";
    public static final String COL_NOMBRE_MODELO = "nombre_modelo";
    public static final String COL_NUMERO_PLAZAS = "numero_plazas";
    public static final String COL_NUMERO_PUERTAS = "numero_puertas";
    public static final String COL_TIPO_VEHICULO = "tipo_vehiculo";
    public static final String COL_TIPO_PROPULSION = "tipo_propulsion";
    public static final String COL_TRACCION = "traccion";
    public static final String COL_MARCA = "marca";
    public static final String COL_TIPO_TRANSMISION = "tipo_transmision";
    
    public ModeloVehiculoDAO(AccesoBD bd) {
        this.bd = bd;
    }

    public int insert(ModeloVehiculo m) {
        String sentencia="INSERT INTO " + NOM_TABLA + " (" +
        COL_NOMBRE_MODELO + ", "+ COL_NOMBRE_MODELO + ", " + COL_NUMERO_PLAZAS + 
        ", " + COL_NUMERO_PUERTAS + ", " + COL_TIPO_VEHICULO + ", " + COL_TIPO_PROPULSION + 
        ", " + COL_TRACCION + ", " + COL_MARCA + ", " + COL_TIPO_TRANSMISION 
        + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con= null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try{
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, m.getNombreModelo());
            stmt.setInt(2, m.getNumeroPlazas());
            stmt.setInt(3, m.getNumeroPuertas());
            stmt.setString(4, m.getTipoPropulsion());
            stmt.setString(5, m.getTraccion());
            stmt.setString(6, m.getMarca());
            stmt.setString(7, m.getTipoTransmision());
            resultado=stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {if (stmt != null) {stmt.close();}
            } catch (Exception e) {e.printStackTrace();}
            try {if (con != null) {con.close();}}
            catch (Exception e) {e.printStackTrace();}
        }
        return resultado;
    }

    public int delete(String nombreModelo) {
        String sentencia="DELETE FROM " + NOM_TABLA + " WHERE " + COL_NOMBRE_MODELO + " = ?";
        Connection con= null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try{
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, nombreModelo);
            resultado=stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {if (stmt != null) {stmt.close();}
            } catch (Exception e) {e.printStackTrace();}
            try {if (con != null) {con.close();}}
            catch (Exception e) {e.printStackTrace();}
        }
        return resultado;
    }

    public int update(ModeloVehiculo m) {
        String sentencia="UPDATE " + NOM_TABLA + " SET " + COL_NOMBRE_MODELO + " = ?, " + COL_NUMERO_PLAZAS +
        		" = ?, " + COL_NUMERO_PUERTAS + " = ?, " + COL_TIPO_VEHICULO + " = ?, " +
        		COL_TIPO_PROPULSION + " = ?, " + COL_TRACCION + " = ?, " + COL_MARCA + " = ? WHERE " + COL_ID_MODELO + " = ?";
        Connection con= null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try{
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, m.getNombreModelo());
            stmt.setInt(2, m.getNumeroPlazas());
            stmt.setInt(3, m.getNumeroPuertas());
            stmt.setString(4, m.getTipoPropulsion());
            stmt.setString(5, m.getTraccion());
            stmt.setString(6, m.getMarca());
            stmt.setString(7, m.getTipoTransmision());
            stmt.setInt(8, m.getIdModelo());
            resultado=stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {if (stmt != null) {stmt.close();}
            } catch (Exception e) {e.printStackTrace();}
            try {if (con != null) {con.close();}}
            catch (Exception e) {e.printStackTrace();}
        }
        return resultado;
    }
    
    public ArrayList<String> selectMarcas() {
    	
        String sentencia = "SELECT DISTINCT " + COL_MARCA + " FROM " + NOM_TABLA;
        Connection con= null;
        Statement stmt = null;
        ResultSet rs = null;
        
        ArrayList<String> marcas = new ArrayList<String>();
        try{
            con = bd.getConexion();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sentencia);
            while(rs.next()) {
            	
            	marcas.add(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        return marcas;
    }
    
    public ArrayList<ModeloVehiculo> selectModeloPorMarca(String marca) {
        String sentencia = "SELECT * FROM " + NOM_TABLA + " WHERE " + COL_MARCA + " = ? ORDER BY " + COL_MARCA + ", " + COL_NOMBRE_MODELO;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ModeloVehiculo> modelos = new ArrayList<ModeloVehiculo>();
        
        try {
        	con = bd.getConexion();
        	stmt = con.prepareStatement(sentencia);
        	stmt.setString(1, marca);
        	rs = stmt.executeQuery();
        	while (rs.next()) {
                int idModelo = rs.getInt(1);
                String nombreModelo = rs.getString(COL_NOMBRE_MODELO);
                int numeroPlazas = rs.getInt(COL_NUMERO_PLAZAS);
                int numeroPuertas = rs.getInt(COL_NUMERO_PUERTAS);
                String tipoPropulsion = rs.getString(COL_TIPO_PROPULSION);
                String traccion = rs.getString(COL_TRACCION);
                String nombreMarca = rs.getString(marca);
                String tipoTransmision = rs.getString(COL_TIPO_TRANSMISION);
                modelos.add(new ModeloVehiculo(idModelo, nombreModelo, numeroPlazas, numeroPuertas,
                		tipoPropulsion, traccion, nombreMarca, tipoTransmision));
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
        return modelos;
    	
    }

    public ArrayList<ModeloVehiculo> selectTodos(){
        String sentencia = "SELECT * FROM " + NOM_TABLA + " ORDER BY " + COL_MARCA + ", " + COL_NOMBRE_MODELO;
        Connection con= null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ModeloVehiculo> modelos = new ArrayList<ModeloVehiculo>();
        try{
            con = bd.getConexion();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sentencia);
            while(rs.next()){
                int idModelo = rs.getInt(COL_ID_MODELO);
                String nombreModelo = rs.getString(COL_NOMBRE_MODELO);
                int numeroPlazas = rs.getInt(COL_NUMERO_PLAZAS);
                int numeroPuertas = rs.getInt(COL_NUMERO_PUERTAS);
                String tipoPropulsion = rs.getString(COL_TIPO_PROPULSION);
                String traccion = rs.getString(COL_TRACCION);
                String nombreMarca = rs.getString(COL_MARCA);
                String tipoTransmision = rs.getString(COL_TIPO_TRANSMISION);
                modelos.add(new ModeloVehiculo(idModelo, nombreModelo, numeroPlazas, numeroPuertas, tipoPropulsion, traccion, nombreMarca, tipoTransmision));
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
        return modelos;
    }

}

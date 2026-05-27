package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Vehiculo;

import java.sql.ResultSet;

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

public class VehiculoDAO {
    private AccesoBD bd;
    private static final String NOM_TABLA="Vehiculo";

    public static final String COL_ID_VEHICULO = "id_vehiculo";
    public static final String COL_MODELO = "modelo";
    public static final String COL_PRECIO = "precio";
    
    public VehiculoDAO(AccesoBD bd) {
        this.bd = bd;
    }

    public int insert(Vehiculo v) {
        String sentencia="INSERT INTO "+NOM_TABLA+" ("+COL_ID_VEHICULO+", "+COL_MODELO+", "+COL_PRECIO+") VALUES (?,?,?)";
        Connection con= null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try{
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setInt(1, v.getIdVehiculo());
            stmt.setInt(2, v.getModeloVehiculo().getIdModelo());
            stmt.setInt(3, v.getPrecio());
            resultado=stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {if (stmt != null) {stmt.close();}
            } catch (Exception e) {e.printStackTrace();}
            try {if (con != null) {con.close();}
            } catch (Exception e) {e.printStackTrace();}
        }
        return resultado;
    }

    public int delete(int idVehiculo) {
        String sentencia="DELETE FROM "+NOM_TABLA+" WHERE "+COL_ID_VEHICULO+" = ?";
        Connection con= null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try{
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setInt(1, idVehiculo);
            resultado=stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {if (stmt != null) {stmt.close();}
            } catch (Exception e) {e.printStackTrace();}
            try {if (con != null) {con.close();}
            } catch (Exception e) {e.printStackTrace();}
        }
        return resultado;
    }

    public ArrayList<Vehiculo> selectTodos(){
        String sentencia="SELECT "+NOM_TABLA+"."+COL_ID_VEHICULO+", "+
        NOM_TABLA+"."+COL_MODELO+", "+
        NOM_TABLA+"."+COL_PRECIO+ ", "+
        ModeloVehiculoDAO.COL_NOMBRE_MODELO+", "+
        ModeloVehiculoDAO.COL_NOMBRE_MARCA+
        " FROM "+NOM_TABLA+" JOIN "+ModeloVehiculoDAO.NOM_TABLA+" ON "+NOM_TABLA+"."+COL_MODELO+" = "+ModeloVehiculoDAO.NOM_TABLA+"."+ModeloVehiculoDAO.COL_ID_MODELO;
        System.out.println(sentencia);

        Connection con= null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        try{
            con = bd.getConexion();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sentencia);
            while(rs.next()){
                vehiculos.add(new Vehiculo(rs.getInt(COL_ID_VEHICULO), new ModeloVehiculo(rs.getInt(COL_MODELO), null, null), rs.getInt(COL_PRECIO)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {if (rs != null) {rs.close();}
            } catch (Exception e) {e.printStackTrace();}
            try {if (stmt != null) {stmt.close();}
            } catch (Exception e) {e.printStackTrace();}
            try {if (con != null) {con.close();}
            } catch (Exception e) {e.printStackTrace();}
        }
        return vehiculos;
    }
}

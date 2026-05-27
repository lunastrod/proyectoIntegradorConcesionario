package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.dam.model.data.ModeloVehiculo;

import java.sql.ResultSet;

/*
CREATE TABLE IF NOT EXISTS "Modelo" (
    "id_modelo" INTEGER,
    "nombre_modelo" varchar(80),
    "numero_plazas" INTEGER,
    "numero_puertas" INTEGER,
    "tipo_vehiculo" varchar(100),
    "tipo_propulsion" varchar(100),
    "traccion" varchar(20),
    "marca" varchar(70),
    "tipo_transmision" varchar(30),
    CONSTRAINT "pk_id" PRIMARY KEY("id_modelo" AUTOINCREMENT)
);
*/

//TODO nombre_modelo unique

public class ModeloVehiculoDAO {
    private AccesoBD bd;
    public static final String NOM_TABLA="Modelo";

    public static final String COL_ID_MODELO = "id_modelo";
    public static final String COL_NOMBRE_MODELO = "nombre_modelo";
    public static final String COL_NUMERO_PLAZAS = "numero_plazas";
    public static final String COL_NUMERO_PUERTAS = "numero_puertas";
    public static final String COL_TIPO_VEHICULO = "tipo_vehiculo";
    public static final String COL_TIPO_PROPULSION = "tipo_propulsion";
    public static final String COL_TRACCION = "traccion";
    public static final String COL_MARCA = "marca";
    public static final String COL_TIPO_TRANSMISION = "tipo_transmision";
    public static final String COL_NOMBRE_MARCA = COL_MARCA;
    
    public ModeloVehiculoDAO(AccesoBD bd) {
        this.bd = bd;
    }

    public int insert(ModeloVehiculo m) {
        String sentencia="INSERT INTO "+NOM_TABLA+" ("+
        COL_NOMBRE_MODELO+", "+
        COL_MARCA+
        ") VALUES (?,?)";

        Connection con= null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try{
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, m.getNombreModelo());
            stmt.setString(2, m.getNombreMarca());
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
        String sentencia="DELETE FROM "+NOM_TABLA+" WHERE "+COL_NOMBRE_MODELO+" = ?";
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
        String sentencia="UPDATE "+NOM_TABLA+" SET "+COL_NOMBRE_MODELO+" = ?, "+COL_MARCA+" = ? WHERE "+COL_ID_MODELO+" = ?";
        Connection con= null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try{
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, m.getNombreModelo());
            stmt.setString(2, m.getNombreMarca());
            stmt.setInt(3, m.getIdModelo());
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

    public ArrayList<ModeloVehiculo> selectTodos(){
        String sentencia="SELECT * FROM "+NOM_TABLA+" ORDER BY "+COL_MARCA+", "+COL_NOMBRE_MODELO;
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
                String nombreMarca = rs.getString(COL_MARCA);
                modelos.add(new ModeloVehiculo(idModelo,nombreModelo,nombreMarca));
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

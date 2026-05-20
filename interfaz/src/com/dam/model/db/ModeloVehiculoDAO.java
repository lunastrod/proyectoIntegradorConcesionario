package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.dam.model.data.ModeloVehiculo;

import java.sql.ResultSet;

/*
    CREATE TABLE Modelo (
    id_modelo INT PRIMARY KEY,
    nombre_modelo varchar(80),
    nombre_marca varchar(50),
    foreign key (nombre_marca) references Marca(nombre_marca)
    );
*/

//TODO nombre_modelo unique

public class ModeloVehiculoDAO {
    private AccesoBD bd;
    private static final String NOM_TABLA="Modelo";

    public static final String COL_ID_MODELO = "id_modelo";
    public static final String COL_NOMBRE_MODELO = "nombre_modelo";
    public static final String COL_NOMBRE_MARCA = "nombre_marca";
    
    public ModeloVehiculoDAO(AccesoBD bd) {
        this.bd = bd;
    }

    public int insert(ModeloVehiculo m) {
        String sentencia="INSERT INTO "+NOM_TABLA+" ("+
        COL_ID_MODELO+", "+
        COL_NOMBRE_MODELO+", "+
        COL_NOMBRE_MARCA+
        ") VALUES (?,?,?)";

        Connection con= null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try{
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setInt(1, m.getIdModelo());
            stmt.setString(2, m.getNombreModelo());
            stmt.setString(3, m.getNombreMarca());
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
        String sentencia="UPDATE "+NOM_TABLA+" SET "+COL_NOMBRE_MODELO+" = ?, "+COL_NOMBRE_MARCA+" = ? WHERE "+COL_NOMBRE_MODELO+" = ?";
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
        String sentencia="SELECT * FROM "+NOM_TABLA+"ORDER BY "+COL_NOMBRE_MARCA+", "+COL_NOMBRE_MODELO;
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
                String nombreMarca = rs.getString(COL_NOMBRE_MARCA);
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

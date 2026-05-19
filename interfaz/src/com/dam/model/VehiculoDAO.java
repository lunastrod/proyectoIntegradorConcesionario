package com.dam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

/*
CREATE TABLE Vehiculo (
id_vehiculo INT PRIMARY KEY,
modelo int,
foreign key (modelo) references Modelo(id_modelo),
precio int
);
*/

public class VehiculoDAO {
    private AccesoBD bd;
    private static final String NOM_TABLA="Vehiculo";

    private static final String COL_ID_VEHICULO = "id_vehiculo";
    private static final String COL_MODELO = "modelo";
    private static final String COL_PRECIO = "precio";
    
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
}

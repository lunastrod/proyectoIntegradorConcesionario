package com.dam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/*
    CREATE TABLE Modelo (
    id_modelo INT PRIMARY KEY,
    nombre_modelo varchar(80),
    nombre_marca varchar(50),
    foreign key (nombre_marca) references Marca(nombre_marca)
    );
*/

public class ModeloVehiculoDAO {
    private AccesoBD bd;

    private static final String COL_ID_MODELO = "id_modelo";
    private static final String COL_NOMBRE_MODELO = "nombre_modelo";
    private static final String COL_NOMBRE_MARCA = "nombre_marca";
    
    public ModeloVehiculoDAO(AccesoBD bd) {
        this.bd = bd;
    }

    public int insertar(ModeloVehiculo m) {
        String sentencia="INSERT INTO Modelo ("+
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
            stmt.executeUpdate(sentencia, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                resultado = rs.getInt(1);
            }
        }


    }


}

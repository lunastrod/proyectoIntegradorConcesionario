package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Vehiculo;

import java.sql.ResultSet;

/*
CREATE TABLE IF NOT EXISTS "Vehiculo" (
	"id_vehiculo"	INTEGER,
	"modelo"	INTEGER,
	"precio"	INTEGER,
	"matricula"	varchar(40) UNIQUE,
	"color"	varchar(40),
	"year"	INTEGER,
	"kilometraje"	INTEGER,
	"potencia_cv"	INTEGER,
	"cilindrada"	INTEGER,
	"peso_kg"	INTEGER,
	CONSTRAINT "pk_id_car" PRIMARY KEY("id_vehiculo" AUTOINCREMENT),
	CONSTRAINT "fk_model" FOREIGN KEY("modelo") REFERENCES "Modelo"("id_modelo")
);
*/

public class VehiculoDAO {
    private AccesoBD bd;
    public static final String NOM_TABLA = "Vehiculo";
    public static final String COL_ID_VEHICULO = "id_vehiculo";
    public static final String COL_MODELO = "modelo";
    public static final String COL_PRECIO = "precio";
    public static final String COL_MATRICULA = "matricula";
    public static final String COL_COLOR = "color";
    public static final String COL_YEAR = "year";
    public static final String COL_KILOMETRAJE = "kilometraje";
    public static final String COL_POTENCIA_CV = "potencia_cv";
    public static final String COL_CILINDRADA = "cilindrada";
    public static final String COL_PESO_KG = "peso_kg";
    
    public VehiculoDAO(AccesoBD bd) {
        this.bd = bd;
    }

    public int insert(Vehiculo v) {
        String sentencia="INSERT INTO " + NOM_TABLA + " (" + COL_ID_VEHICULO + ", " + COL_MODELO
        		+ ", " + COL_PRECIO + ", " + COL_MATRICULA + ", " + COL_COLOR + ", " + COL_YEAR + ", " + COL_KILOMETRAJE 
        		+ ", " + COL_POTENCIA_CV + ", " + COL_CILINDRADA + ", " + COL_PESO_KG 
        		+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con= null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try{
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setInt(1, v.getIdVehiculo());
            stmt.setInt(2, v.getModelo().getIdModelo());
            stmt.setInt(3, v.getPrecio());
            stmt.setString(4, v.getMatricula());
            stmt.setString(5, v.getColor());
            stmt.setInt(6, v.getYear());
            stmt.setInt(7, v.getKilometraje());
            stmt.setInt(8, v.getPotenciaCV());
            stmt.setInt(9, v.getCilindrada());
            stmt.setInt(10, v.getPesoKG());
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
        String sentencia="DELETE FROM " + NOM_TABLA + " WHERE " + COL_ID_VEHICULO + " = ?";
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
    
    public ArrayList<Vehiculo> selectVehiculoPorId(int id) {
    	String sentencia = "SELECT * FROM" + NOM_TABLA + " JOIN " + ModeloVehiculoDAO.NOM_TABLA
    			+ " ON " + NOM_TABLA + "." + COL_MODELO + " = " + ModeloVehiculoDAO.NOM_TABLA
    			+ "." + ModeloVehiculoDAO.COL_ID_MODELO + " WHERE " + COL_ID_VEHICULO + " = ?";
    	
    	Connection con = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	ArrayList<Vehiculo> vehiculo = new ArrayList<Vehiculo>();
    	
    	try {
    		con = bd.getConexion();
    		stmt = con.prepareStatement(sentencia);
    		stmt.setInt(1, id);
    		rs = stmt.executeQuery();
    		while (rs.next()) {
    			int idVehiculo = rs.getInt(1);
            	int precio = rs.getInt(COL_PRECIO);
            	String matricula = rs.getString(COL_MATRICULA);
            	String color = rs.getString(COL_COLOR);
            	int year = rs.getInt(COL_YEAR);
            	int kilometraje = rs.getInt(COL_KILOMETRAJE);
            	int potenciaCv = rs.getInt(COL_POTENCIA_CV);
            	int cilindrada = rs.getInt(COL_CILINDRADA);
            	int pesoKG = rs.getInt(COL_PESO_KG);
            	
                int idModelo = rs.getInt(ModeloVehiculoDAO.COL_ID_MODELO);
                String nombreModelo = rs.getString(ModeloVehiculoDAO.COL_NOMBRE_MODELO);
                int numeroPlazas = rs.getInt(ModeloVehiculoDAO.COL_NUMERO_PLAZAS);
                int numeroPuertas = rs.getInt(ModeloVehiculoDAO.COL_NUMERO_PUERTAS);
                String tipoVehiculo = rs.getString(ModeloVehiculoDAO.COL_TIPO_VEHICULO);
                String tipoPropulsion = rs.getString(ModeloVehiculoDAO.COL_TIPO_PROPULSION);
                String traccion = rs.getString(ModeloVehiculoDAO.COL_TRACCION);
                String nombreMarca = rs.getString(ModeloVehiculoDAO.COL_MARCA);
                String tipoTransmision = rs.getString(ModeloVehiculoDAO.COL_TIPO_TRANSMISION);
                
                ModeloVehiculo modelo = new ModeloVehiculo(idModelo, nombreModelo, numeroPlazas,
                		numeroPuertas, tipoVehiculo, tipoPropulsion, traccion, nombreMarca, tipoTransmision);
                
                vehiculo.add(new Vehiculo(idVehiculo, modelo, precio, matricula, color, 
                		year, kilometraje, potenciaCv, cilindrada, pesoKG));
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
        return vehiculo;
    	
    }

    public ArrayList<Vehiculo> selectTodos(){
        String sentencia="SELECT * FROM " + NOM_TABLA + " JOIN " + ModeloVehiculoDAO.NOM_TABLA 
        		+ " ON " + NOM_TABLA + "." + COL_MODELO + " = " 
        		+ ModeloVehiculoDAO.NOM_TABLA + "." + ModeloVehiculoDAO.COL_ID_MODELO;
        System.out.println(sentencia);

        Connection con= null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        try {
            con = bd.getConexion();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sentencia);
            while(rs.next()){
            	int idVehiculo = rs.getInt(COL_ID_VEHICULO);
            	int precio = rs.getInt(COL_PRECIO);
            	String matricula = rs.getString(COL_MATRICULA);
            	String color = rs.getString(COL_COLOR);
            	int year = rs.getInt(COL_YEAR);
            	int kilometraje = rs.getInt(COL_KILOMETRAJE);
            	int potenciaCv = rs.getInt(COL_POTENCIA_CV);
            	int cilindrada = rs.getInt(COL_CILINDRADA);
            	int pesoKG = rs.getInt(COL_PESO_KG);
            	
                int idModelo = rs.getInt(ModeloVehiculoDAO.COL_ID_MODELO);
                String nombreModelo = rs.getString(ModeloVehiculoDAO.COL_NOMBRE_MODELO);
                int numeroPlazas = rs.getInt(ModeloVehiculoDAO.COL_NUMERO_PLAZAS);
                int numeroPuertas = rs.getInt(ModeloVehiculoDAO.COL_NUMERO_PUERTAS);
                String tipoVehiculo = rs.getString(ModeloVehiculoDAO.COL_TIPO_VEHICULO);
                String tipoPropulsion = rs.getString(ModeloVehiculoDAO.COL_TIPO_PROPULSION);
                String traccion = rs.getString(ModeloVehiculoDAO.COL_TRACCION);
                String nombreMarca = rs.getString(ModeloVehiculoDAO.COL_MARCA);
                String tipoTransmision = rs.getString(ModeloVehiculoDAO.COL_TIPO_TRANSMISION);
                
                ModeloVehiculo modelo = new ModeloVehiculo(idModelo, nombreModelo, numeroPlazas,
                		numeroPuertas, tipoVehiculo, tipoPropulsion, traccion, nombreMarca, tipoTransmision);
                
                vehiculos.add(new Vehiculo(idVehiculo, modelo, precio, matricula, color, 
                		year, kilometraje, potenciaCv, cilindrada, pesoKG));
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

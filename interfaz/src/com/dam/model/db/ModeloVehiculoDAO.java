package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.dam.model.data.ModeloVehiculo;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sentencia = "INSERT INTO " + NOM_TABLA + " ("
                + COL_NOMBRE_MODELO + ", " + COL_NUMERO_PLAZAS
                + ", " + COL_NUMERO_PUERTAS + ", " + COL_TIPO_VEHICULO + ", " + COL_TIPO_PROPULSION
                + ", " + COL_TRACCION + ", " + COL_MARCA + ", " + COL_TIPO_TRANSMISION
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try {
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, m.getNombreModelo());
            stmt.setInt(2, m.getNumeroPlazas());
            stmt.setInt(3, m.getNumeroPuertas());
            stmt.setString(4, m.getTipoModelo());
            stmt.setString(5, m.getTipoPropulsion());
            stmt.setString(6, m.getTraccion());
            stmt.setString(7, m.getMarca());
            stmt.setString(8, m.getTipoTransmision());
            resultado = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return resultado;
    }

    public int delete(String nombreModelo) {
        String sentencia = "DELETE FROM " + NOM_TABLA + " WHERE " + COL_NOMBRE_MODELO + " = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try {
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, nombreModelo);
            resultado = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return resultado;
    }

    /** Parámetros corregidos: 1-nombre, 2-plazas, 3-puertas, 4-tipo_vehiculo,
     *  5-propulsion, 6-traccion, 7-marca, 8-transmision, 9-id */
    public int update(ModeloVehiculo m) {
        String sentencia = "UPDATE " + NOM_TABLA + " SET "
                + COL_NOMBRE_MODELO + " = ?, "
                + COL_NUMERO_PLAZAS + " = ?, "
                + COL_NUMERO_PUERTAS + " = ?, "
                + COL_TIPO_VEHICULO + " = ?, "
                + COL_TIPO_PROPULSION + " = ?, "
                + COL_TRACCION + " = ?, "
                + COL_MARCA + " = ?, "
                + COL_TIPO_TRANSMISION + " = ? "
                + "WHERE " + COL_ID_MODELO + " = ?";

        Connection con = null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try {
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, m.getNombreModelo());
            stmt.setInt(2, m.getNumeroPlazas());
            stmt.setInt(3, m.getNumeroPuertas());
            stmt.setString(4, m.getTipoModelo());
            stmt.setString(5, m.getTipoPropulsion());
            stmt.setString(6, m.getTraccion());
            stmt.setString(7, m.getMarca());
            stmt.setString(8, m.getTipoTransmision());
            stmt.setInt(9, m.getIdModelo());
            resultado = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return resultado;
    }

    public ArrayList<String> selectMarcas() {
        String sentencia = "SELECT DISTINCT " + COL_MARCA + " FROM " + NOM_TABLA;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> marcas = new ArrayList<String>();
        try {
            con = bd.getConexion();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sentencia);
            while (rs.next()) {
                marcas.add(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); if (stmt != null) stmt.close(); if (con != null) con.close(); }
            catch (SQLException e) { e.printStackTrace(); }
        }
        return marcas;
    }

    public ArrayList<ModeloVehiculo> selectModeloPorMarca(String marca) {
        String sentencia = "SELECT * FROM " + NOM_TABLA + " WHERE " + COL_MARCA
                + " = ? ORDER BY " + COL_MARCA + ", " + COL_NOMBRE_MODELO;
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
                modelos.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return modelos;
    }

    public ArrayList<ModeloVehiculo> selectTodos() {
        String sentencia = "SELECT * FROM " + NOM_TABLA
                + " ORDER BY " + COL_MARCA + ", " + COL_NOMBRE_MODELO;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ModeloVehiculo> modelos = new ArrayList<ModeloVehiculo>();
        try {
            con = bd.getConexion();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sentencia);
            while (rs.next()) {
                modelos.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return modelos;
    }

    private ModeloVehiculo mapRow(ResultSet rs) throws SQLException {
        int idModelo = rs.getInt(COL_ID_MODELO);
        String nombreModelo = rs.getString(COL_NOMBRE_MODELO);
        int numeroPlazas = rs.getInt(COL_NUMERO_PLAZAS);
        int numeroPuertas = rs.getInt(COL_NUMERO_PUERTAS);
        String tipoVehiculo = rs.getString(COL_TIPO_VEHICULO);
        String tipoPropulsion = rs.getString(COL_TIPO_PROPULSION);
        String traccion = rs.getString(COL_TRACCION);
        String nombreMarca = rs.getString(COL_MARCA);
        String tipoTransmision = rs.getString(COL_TIPO_TRANSMISION);
        return new ModeloVehiculo(idModelo, nombreModelo, numeroPlazas, numeroPuertas,
                tipoVehiculo, tipoPropulsion, traccion, nombreMarca, tipoTransmision);
    }
}
package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import com.dam.model.data.ModeloVehiculo;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
DROP TABLE IF EXISTS "Modelo";
CREATE TABLE "Modelo" (
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
/**
 * Objeto de acceso a datos para la entidad {@link ModeloVehiculo}.
 * <p>
 * Proporciona operaciones CRUD completas sobre la tabla Modelo
 * de la base de datos, así como consultas filtradas por marca.
 * Cada método gestiona su propia conexión, abriéndola al inicio
 * y cerrándola en el bloque finally.
 * @see ModeloVehiculo
 * @see AccesoBD
 */
public class ModeloVehiculoDAO {

    /** Instancia de acceso a la base de datos usada para obtener conexiones. */
    private AccesoBD bd;

    /** Nombre de la tabla en la base de datos. */
    public static final String NOM_TABLA = "Modelo";

    /** Nombre de la columna ID del modelo. */
    public static final String COL_ID_MODELO = "id_modelo";

    /** Nombre de la columna con el nombre del modelo. */
    public static final String COL_NOMBRE_MODELO = "nombre_modelo";

    /** Nombre de la columna con el número de plazas. */
    public static final String COL_NUMERO_PLAZAS = "numero_plazas";

    /** Nombre de la columna con el número de puertas. */
    public static final String COL_NUMERO_PUERTAS = "numero_puertas";

    /** Nombre de la columna con el tipo de vehículo. */
    public static final String COL_TIPO_VEHICULO = "tipo_vehiculo";

    /** Nombre de la columna con el tipo de propulsión. */
    public static final String COL_TIPO_PROPULSION = "tipo_propulsion";

    /** Nombre de la columna con el tipo de tracción. */
    public static final String COL_TRACCION = "traccion";

    /** Nombre de la columna con la marca del modelo. */
    public static final String COL_MARCA = "marca";

    /** Nombre de la columna con el tipo de transmisión. */
    public static final String COL_TIPO_TRANSMISION = "tipo_transmision";

    /**
     * Crea un nuevo ModeloVehiculoDAO con la instancia de acceso
     * a la base de datos indicada.
     * @param bd instancia de AccesoBD para obtener conexiones
     */
    public ModeloVehiculoDAO(AccesoBD bd) {
        this.bd = bd;
    }

    /**
     * Inserta un nuevo modelo de vehículo en la base de datos.
     * <p>
     * El ID del modelo es asignado automáticamente
     * por la base de datos mediante AUTOINCREMENT.
     * @param m modelo de vehículo a insertar
     * @return número de filas afectadas; 1 si se insertó correctamente,
     *         -1 si ocurrió un error
     */
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

    /**
     * Elimina el modelo de vehículo con el nombre indicado.
     * <p>
     * Si el modelo tiene vehículos asociados mediante una clave foránea,
     * la base de datos impedirá la eliminación y el método devolverá -1.
     * @param nombreModelo nombre del modelo a eliminar.
     * @return número de filas afectadas; 1 si se eliminó correctamente,
     *         -1 si ocurrió un error o el modelo tiene vehículos asociados.
     */
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

     /**
     * Actualiza los datos de un modelo de vehículo existente en la base de datos.
     * <p>
     * Identifica el registro a actualizar por el identificador del modelo.
     * @param m modelo con los nuevos datos; su identificador debe coincidir
     *          con un registro existente en la base de datos.
     * @return número de filas afectadas; 1 si se actualizó correctamente,
     *         -1 si ocurrió un error.
     */
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

    /**
     * Recupera todas las marcas distintas registradas en la tabla de modelos.
     * <p>
     * Se usa para poblar los desplegables de marca en la interfaz.
     * @return lista con los nombres de las marcas sin repetición;
     *         lista vacía si no hay ninguna o si ocurrió un error.
    */
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

    /**
     * Recupera todos los modelos de vehículo pertenecientes a una marca concreta,
     * ordenados por marca y nombre de modelo.
     * <p>
     * Se usa para llenar el desplegable de modelos tras seleccionar una marca.
     * @param marca nombre de la marca por la que filtrar.
     * @return lista de modelos de la marca indicada; lista vacía si no hay
     *         ninguno o si ocurrió un error.
     */
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
                modelos.add(obtenerModelo(rs));
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

    /**
     * Recupera todos los modelos de vehículo registrados en la base de datos,
     * ordenados por marca y nombre del modelo.
     * @return lista con todos los modelos; lista vacía si no hay ninguno
     *         o si ocurrió un error.
     */
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
                modelos.add(obtenerModelo(rs));
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

    /**
     * Construye un objeto {@link ModeloVehiculo} a partir de la fila
     * actual del ResultSet.
     * <p>
     * Método auxiliar usado por los métodos de consulta para evitar
     * duplicar la lógica de mapeo.
     * @param rs ResultSet posicionado en la fila a mapear
     * @return objeto ModeloVehiculo con los datos de la fila
     * @throws SQLException si ocurre un error al leer alguna columna del ResultSet
     */
    private ModeloVehiculo obtenerModelo(ResultSet rs) throws SQLException {
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
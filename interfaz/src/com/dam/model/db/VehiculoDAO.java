package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Vehiculo;
import java.sql.ResultSet;
/*
DROP TABLE IF EXISTS "Vehiculo";
CREATE TABLE "Vehiculo" (
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
/**
 * Objeto de acceso a datos para la entidad {@link Vehiculo}.
 * <p>
 * Proporciona operaciones CRUD completas sobre la tabla Vehiculo
 * de la base de datos. Las consultas realizan un JOIN con la tabla
 * Modelo para devolver objetos {@link Vehiculo} completamente
 * llenados con su {@link ModeloVehiculo} asociado.
 * Cada método gestiona su propia conexión, abriéndola al inicio
 * y cerrándola en el bloque finally.
 * @see Vehiculo
 * @see ModeloVehiculo
 * @see AccesoBD
 */
public class VehiculoDAO {

    /** Instancia de acceso a la base de datos usada para obtener conexiones. */
    private AccesoBD bd;

    /** Nombre de la tabla en la base de datos. */
    public static final String NOM_TABLA = "Vehiculo";

    /** Nombre de la columna ID del vehículo. */
    public static final String COL_ID_VEHICULO = "id_vehiculo";

    /** Nombre de la columna con la clave foránea al modelo del vehículo. */
    public static final String COL_MODELO = "modelo";

    /** Nombre de la columna con el precio del vehículo en euros. */
    public static final String COL_PRECIO = "precio";

    /** Nombre de la columna con la matrícula del vehículo. */
    public static final String COL_MATRICULA = "matricula";

    /** Nombre de la columna con el color del vehículo en formato RGB personalizado. */
    public static final String COL_COLOR = "color";

    /** Nombre de la columna con el año de fabricación del vehículo. */
    public static final String COL_YEAR = "year";

    /** Nombre de la columna con el kilometraje del vehículo en kilómetros. */
    public static final String COL_KILOMETRAJE = "kilometraje";

    /** Nombre de la columna con la potencia del motor en caballos de vapor (CV). */
    public static final String COL_POTENCIA_CV = "potencia_cv";

    /** Nombre de la columna con la cilindrada del motor en centímetros cúbicos (cc). */
    public static final String COL_CILINDRADA = "cilindrada";

    /** Nombre de la columna con el peso del vehículo en kilogramos (kg). */
    public static final String COL_PESO_KG = "peso_kg";

    /**
     * Crea un nuevo VehiculoDAO con la instancia de acceso
     * a la base de datos indicada.
     * @param bd instancia de AccesoBD para obtener conexiones
     */
    public VehiculoDAO(AccesoBD bd) {
        this.bd = bd;
    }

    /**
     * Inserta un nuevo vehículo en la base de datos.
     * <p>
     * El ID del vehículo es asignado automáticamente
     * por la base de datos mediante AUTOINCREMENT.
     * @param v vehículo a insertar; su modelo debe existir previamente en la base de datos
     * @return número de filas afectadas; 1 si se insertó correctamente,
     *         -1 si ocurrió un error
     */
    public int insert(Vehiculo v) {
        String sentencia = "INSERT INTO " + NOM_TABLA + " (" + COL_MODELO
                + ", " + COL_PRECIO + ", " + COL_MATRICULA + ", " + COL_COLOR
                + ", " + COL_YEAR + ", " + COL_KILOMETRAJE
                + ", " + COL_POTENCIA_CV + ", " + COL_CILINDRADA + ", " + COL_PESO_KG
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try {
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setInt(1, v.getModelo().getIdModelo());
            stmt.setInt(2, v.getPrecio());
            stmt.setString(3, v.getMatricula());
            stmt.setString(4, v.getColor());
            stmt.setInt(5, v.getYear());
            stmt.setInt(6, v.getKilometraje());
            stmt.setInt(7, v.getPotenciaCV());
            stmt.setInt(8, v.getCilindrada());
            stmt.setInt(9, v.getPesoKG());
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
     * Actualiza los datos de un vehículo existente en la base de datos.
     * <p>
     * Identifica el registro a actualizar por el ID del vehículo.
     * @param v vehículo con los nuevos datos; su ID debe coincidir
     *          con un registro existente en la base de datos.
     * @return número de filas afectadas; 1 si se actualizó correctamente,
     *         -1 si ocurrió un error.
     */
    public int update(Vehiculo v) {
        String sentencia = "UPDATE " + NOM_TABLA + " SET "
                + COL_MODELO + " = ?, "
                + COL_PRECIO + " = ?, "
                + COL_MATRICULA + " = ?, "
                + COL_COLOR + " = ?, "
                + COL_YEAR + " = ?, "
                + COL_KILOMETRAJE + " = ?, "
                + COL_POTENCIA_CV + " = ?, "
                + COL_CILINDRADA + " = ?, "
                + COL_PESO_KG + " = ? "
                + "WHERE " + COL_ID_VEHICULO + " = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try {
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setInt(1, v.getModelo().getIdModelo());
            stmt.setInt(2, v.getPrecio());
            stmt.setString(3, v.getMatricula());
            stmt.setString(4, v.getColor());
            stmt.setInt(5, v.getYear());
            stmt.setInt(6, v.getKilometraje());
            stmt.setInt(7, v.getPotenciaCV());
            stmt.setInt(8, v.getCilindrada());
            stmt.setInt(9, v.getPesoKG());
            stmt.setInt(10, v.getIdVehiculo());
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
     * Elimina el vehículo con el ID indicado.
     * <p>
     * Si el vehículo tiene ventas asociadas mediante clave foránea,
     * la base de datos impedirá la eliminación y el método devolverá -1.
     * @param idVehiculo ID del vehículo a eliminar.
     * @return número de filas afectadas; 1 si se eliminó correctamente,
     *         -1 si ocurrió un error o el vehículo tiene ventas asociadas.
     */
    public int delete(int idVehiculo) {
        String sentencia = "DELETE FROM " + NOM_TABLA + " WHERE " + COL_ID_VEHICULO + " = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        int resultado = -1;
        try {
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setInt(1, idVehiculo);
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
     * Recupera los vehículos cuyo ID coincide con el indicado.
     * <p>
     * Realiza un JOIN con la tabla Modelo para devolver el vehículo
     * con su modelo completamente poblado.
     * @param id ID del vehículo a buscar.
     * @return lista con el vehículo encontrado; lista vacía si no existe
     *         ninguno con ese ID o si ocurrió un error.
     */
    public ArrayList<Vehiculo> selectVehiculoPorId(int id) {
        String sentencia = "SELECT * FROM " + NOM_TABLA + " JOIN " + ModeloVehiculoDAO.NOM_TABLA
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
                vehiculo.add(extraeVehiculo(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return vehiculo;
    }
    /**
     * Recupera todos los vehículos registrados en la base de datos.
     * <p>
     * Realiza un JOIN con la tabla Modelo para devolver cada
     * vehículo con su modelo completamente lleno.
     * @return lista con todos los vehículos; lista vacía si no hay
     *         ninguno o si ocurrió un error.
     */
    public ArrayList<Vehiculo> selectTodos() {
        String sentencia = "SELECT * FROM " + NOM_TABLA + " JOIN " + ModeloVehiculoDAO.NOM_TABLA
                + " ON " + NOM_TABLA + "." + COL_MODELO + " = "
                + ModeloVehiculoDAO.NOM_TABLA + "." + ModeloVehiculoDAO.COL_ID_MODELO;

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        try {
            con = bd.getConexion();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sentencia);
            while (rs.next()) {
                vehiculos.add(extraeVehiculo(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return vehiculos;
    }
    /**
     * Construye un objeto {@link Vehiculo} a partir de la fila actual
     * del ResultSet, incluyendo su {@link ModeloVehiculo} asociado.
     * <p>
     * Método auxiliar usado por los métodos de consulta para evitar
     * duplicar la lógica de mapeo. Requiere que el ResultSet provenga
     * de una consulta con JOIN sobre la tabla Modelo.
     * @param rs ResultSet posicionado en la fila a mapear.
     * @return objeto Vehiculo con todos sus datos y modelo poblado.
     * @throws Exception si ocurre un error al leer alguna columna del ResultSet.
     */
    private Vehiculo extraeVehiculo(ResultSet rs) throws Exception {
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

        return new Vehiculo(idVehiculo, modelo, precio, matricula, color,
                year, kilometraje, potenciaCv, cilindrada, pesoKG);
    }
}
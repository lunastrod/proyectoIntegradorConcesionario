package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.dam.model.data.Trabajador;
/*
DROP TABLE IF EXISTS "Trabajador";
CREATE TABLE "Trabajador" (
	"id_trabajador"	INTEGER,
	"nombre_apellidos"	varchar(100),
	"password_trabajador"	varchar(100),
	"es_admin"	INTEGER,
	CONSTRAINT "pk_id_trabajo" PRIMARY KEY("id_trabajador" AUTOINCREMENT)
);
*/
/**
 * Objeto de acceso a datos para la entidad {@link Trabajador}.
 * <p>
 * Proporciona operaciones para insertar, borrar y consultar
 * sobre la tabla Trabajador de la base de datos, incluyendo
 * la validación de credenciales para el inicio de sesión.
 * Cada método gestiona su propia conexión, abriéndola al inicio
 * y cerrándola en el bloque finally.
 * @see Trabajador
 * @see AccesoBD
 */
public class TrabajadorDAO {

    /** Instancia de acceso a la base de datos usada para obtener conexiones. */
    private AccesoBD bd;

    /** Nombre de la tabla en la base de datos. */
    public static final String NOM_TABLA = "Trabajador";

    /** Nombre de la columna ID del trabajador. */
    public static final String COL_ID_TRABAJADOR = "id_trabajador";

    /** Nombre de la columna con el nombre y apellidos del trabajador. */
    public static final String COL_NOMBRE_TRABAJADOR = "nombre_apellidos";

    /** Nombre de la columna con la contraseña del trabajador. */
    public static final String COL_PASSWORD_TRABAJADOR = "password_trabajador";

    /** Nombre de la columna que indica si el trabajador es administrador. */
    public static final String COL_ES_ADMIN = "es_admin";

    /**
     * Crea un nuevo TrabajadorDAO con la instancia de acceso
     * a la base de datos indicada.
     * @param bd instancia de AccesoBD para obtener conexiones
     */
    public TrabajadorDAO(AccesoBD bd) {
        this.bd = bd;
    }

    /**
     * Inserta un nuevo trabajador en la base de datos.
     * <p>
     * El ID del trabajador es asignado automáticamente
     * por la base de datos mediante AUTOINCREMENT.
     * @param t trabajador a insertar.
     * @return número de filas afectadas; 1 si se insertó correctamente,
     *         -1 si ocurrió un error.
     */
    public int insert(Trabajador t) {
        String sentencia = "INSERT INTO " + NOM_TABLA + " (" + COL_NOMBRE_TRABAJADOR
                + ", " + COL_PASSWORD_TRABAJADOR + ", " + COL_ES_ADMIN + ") VALUES (?, ?, ?)";
        Connection con = null;
        PreparedStatement stmt = null;
        int res = -1;
        try {
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, t.getNombreApellidos());
            stmt.setString(2, t.getPasswordTrabajador());
            stmt.setInt(3, t.getEsAdmin());
            res = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return res;
    }
    /**
     * Elimina el trabajador con el nombre y apellidos indicados.
     * <p>
     * Si el trabajador tiene ventas asociadas mediante clave foránea,
     * la base de datos impedirá la eliminación y el método devolverá -1.
     * @param nombreApellidos nombre y apellidos exactos del trabajador a eliminar.
     * @return número de filas afectadas; 1 si se eliminó correctamente,
     *         -1 si ocurrió un error o el trabajador tiene ventas asociadas.
     */
    public int delete(String nombreApellidos) {
        String sentencia = "DELETE FROM " + NOM_TABLA + " WHERE " + COL_NOMBRE_TRABAJADOR + " = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        int res = -1;
        try {
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, nombreApellidos);
            res = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return res;
    }
    /**
     * Nos retorna un todos los trabajadores que se encuentran en la base de datos con el ID,
     * nombres, apellidos, contraseñas y tipo de trabajador.
     * @return Todos los trabajadores en la base de datos, null si no hay nada.
     */
    public ArrayList<Trabajador> selectAllTrabajadores() {
        String sentencia = "SELECT * FROM " + NOM_TABLA + " ORDER BY " + COL_ID_TRABAJADOR;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
        try {
            con = bd.getConexion();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sentencia);
            while (rs.next()) {
                trabajadores.add(extraeTrabajador(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return trabajadores;
    }
    /**
     * Busca un trabajador por su nombre y apellidos.
     * @param nombre nombre y apellidos exactos del trabajador a buscar
     * @return trabajador encontrado, o null si no existe ninguno
     *         con ese nombre o si ocurrió un error.
     */
    public Trabajador getTrabajadorPorNombre(String nombre) {
        String sentencia = "SELECT * FROM " + NOM_TABLA + " WHERE " + COL_NOMBRE_TRABAJADOR + " = ?";
        return ejecutarConsultaUnico(sentencia, nombre);
    }
    /**
     * Valida las credenciales de un trabajador y lo devuelve si son correctas.
     * <p>
     * Se usa en el proceso de inicio de sesión para autenticar al trabajador
     * y determinar si tiene permisos de administrador.
     * @param nombre   nombre y apellidos del trabajador.
     * @param password contraseña introducida en el formulario de login.
     * @return trabajador cuyas credenciales coinciden, o null si
     *         el nombre o la contraseña son incorrectos.
     */
    /** Devuelve el trabajador si las credenciales son correctas, null si no coinciden. */
    public Trabajador getTrabajadorPorCredenciales(String nombre, String password) {
        String sentencia = "SELECT * FROM " + NOM_TABLA
                + " WHERE " + COL_NOMBRE_TRABAJADOR + " = ?"
                + " AND " + COL_PASSWORD_TRABAJADOR + " = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Trabajador trabajador = null;
        try {
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, nombre);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                trabajador = extraeTrabajador(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return trabajador;
    }
    /**
     * Ejecuta una consulta que devuelve un único trabajador usando
     * un solo parámetro de tipo texto.
     * <p>
     * Método auxiliar reutilizado por las consultas que filtran
     * por un único campo de texto.
     * @param sentencia sentencia SQL con un único parámetro de sustitución.
     * @param param     valor del parámetro a sustituir en la sentencia.
     * @return trabajador encontrado, o null si no hay resultados
     *         o si ocurrió un error.
     */
    private Trabajador ejecutarConsultaUnico(String sentencia, String param) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Trabajador trabajador = null;
        try {
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, param);
            rs = stmt.executeQuery();
            if (rs.next()) {
                trabajador = extraeTrabajador(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return trabajador;
    }
    /**
     * Construye un objeto {@link Trabajador} a partir de la fila
     * actual del ResultSet.
     * <p>
     * Método auxiliar usado por los métodos de consulta para evitar
     * duplicar la lógica de mapeo.
     * @param rs ResultSet posicionado en la fila a mapear.
     * @return objeto Trabajador con los datos de la fila.
     * @throws Exception si ocurre un error al leer alguna columna del ResultSet.
     */
    private Trabajador extraeTrabajador(ResultSet rs) throws Exception {
        int idTrabajador = rs.getInt(COL_ID_TRABAJADOR);
        String nombreTrabajador = rs.getString(COL_NOMBRE_TRABAJADOR);
        String passwordTrabajador = rs.getString(COL_PASSWORD_TRABAJADOR);
        int esAdmin = rs.getInt(COL_ES_ADMIN);
        return new Trabajador(idTrabajador, nombreTrabajador, passwordTrabajador, esAdmin);
    }
}
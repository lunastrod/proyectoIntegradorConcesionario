package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.dam.model.data.Cliente;
/*
CREATE TABLE IF NOT EXISTS "Cliente" (
	"id_cliente"	INTEGER,
	"nombre_apellidos"	varchar(100),
	"metodo_pago"	varchar(50),
	CONSTRAINT "pk_id_cli" PRIMARY KEY("id_cliente" AUTOINCREMENT)
);
*/
/**
 * Objeto de acceso a datos para la entidad {@link Cliente}.
 * <p>
 * Proporciona operaciones para insertar, consultar y buscar con parámetros
 * sobre la tabla Cliente de la base de datos.
 * Cada método gestiona su propia conexión, abriéndola al inicio
 * y cerrándola en el bloque finally.
 * @see Cliente
 * @see AccesoBD
 */
public class ClienteDAO {

    /** Instancia de acceso a la base de datos usada para obtener conexiones. */
    private AccesoBD bd;

    /** Nombre de la tabla en la base de datos. */
    public static final String NOM_TABLA = "Cliente";

    /** Nombre de la columna ID del cliente. */
    public static final String COL_ID_CLIENTE = "id_cliente";

    /** Nombre de la columna con el nombre y apellidos del cliente. */
    public static final String COL_NOMBRE_APELLIDOS = "nombre_apellidos";

    /** Nombre de la columna con el método de pago del cliente. */
    public static final String COL_METODO_PAGO = "metodo_pago";

    /**
     * Crea un nuevo ClienteDAO con la instancia de acceso a la base de datos indicada.
     * @param bd instancia de AccesoBD para obtener conexiones.
     */
    public ClienteDAO(AccesoBD bd) {
        this.bd = bd;
    }

    /**
     * Inserta un nuevo cliente en la base de datos.
     * <p>
     * El ID del cliente es asignado automáticamente
     * por la base de datos mediante AUTOINCREMENT.
     * @param c cliente a insertar.
     * @return número de filas afectadas; 1 si se insertó correctamente, -1 si ocurrió un error.
     */
    public int insert(Cliente c) {
        String sentencia = "INSERT INTO " + NOM_TABLA + " (" + COL_NOMBRE_APELLIDOS
                + ", " + COL_METODO_PAGO + ") VALUES (?, ?)";
        Connection con = null;
        PreparedStatement stmt = null;
        int res = -1;
        try {
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, c.getNombreApellidos());
            stmt.setString(2, c.getMetodoPago());
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
     * Obtiene todos los clientes de la base de datos ordenados por ID.
     * @return lista con todos los clientes; lista vacía si no hay ninguno
     *         o si ocurrió un error
     */
    public ArrayList<Cliente> selectAllClientes() {
        String sentencia = "SELECT * FROM " + NOM_TABLA + " ORDER BY " + COL_ID_CLIENTE;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        try {
            con = bd.getConexion();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sentencia);
            while (rs.next()) {
                int clienteId = rs.getInt(COL_ID_CLIENTE);
                String nombreCliente = rs.getString(COL_NOMBRE_APELLIDOS);
                String metodoPago = rs.getString(COL_METODO_PAGO);
                clientes.add(new Cliente(clienteId, nombreCliente, metodoPago));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return clientes;
    }

    /**
     * Busca un cliente por su nombre y apellidos.
     * <p>
     * Se usa tras insertar un cliente nuevo para obtener el registro
     * completo con el ID asignado por la base de datos.
     * @param nombre nombre y apellidos exactos del cliente a buscar.
     * @return cliente encontrado, o null si no existe ninguno
     *         con ese nombre o si ocurrió un error.
     */
    public Cliente selectPorNombre(String nombre) {
        String sentencia = "SELECT * FROM " + NOM_TABLA + " WHERE " + COL_NOMBRE_APELLIDOS + " = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            con = bd.getConexion();
            stmt = con.prepareStatement(sentencia);
            stmt.setString(1, nombre);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(COL_ID_CLIENTE);
                String nombreApellidos = rs.getString(COL_NOMBRE_APELLIDOS);
                String metodoPago = rs.getString(COL_METODO_PAGO);
                cliente = new Cliente(id, nombreApellidos, metodoPago);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return cliente;
    }
}
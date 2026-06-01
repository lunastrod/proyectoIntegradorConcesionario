package com.dam.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.dam.model.data.Cliente;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Trabajador;
import com.dam.model.data.Vehiculo;
import com.dam.model.data.Venta;
/*
DROP TABLE IF EXISTS "Venta";
CREATE TABLE "Venta" (
	"id_venta"	INTEGER,
	"id_cliente"	INTEGER,
	"id_trabajador"	INTEGER,
	"id_vehiculo"	INTEGER,
	"fecha"	DATETIME DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT "pk_id_venta" PRIMARY KEY("id_venta" AUTOINCREMENT),
	CONSTRAINT "fk_id_cli" FOREIGN KEY("id_cliente") REFERENCES "Cliente"("id_cliente"),
	CONSTRAINT "fk_id_trabajo" FOREIGN KEY("id_trabajador") REFERENCES "Trabajador"("id_trabajador"),
	CONSTRAINT "fk_id_car" FOREIGN KEY("id_vehiculo") REFERENCES "Vehiculo"("id_vehiculo")
);
*/
/**
 * Objeto de acceso a datos para la entidad {@link Venta}.
 * <p>
 * Proporciona operaciones de inserción y consulta sobre la tabla
 * Venta de la base de datos. Las consultas realizan múltiples
 * JOIN con las tablas Cliente, Trabajador, Vehiculo y Modelo
 * para devolver objetos {@link Venta} completamente poblados.
 * Cada método gestiona su propia conexión, abriéndola al inicio
 * y cerrándola en el bloque finally.
 *
 * @see Venta
 * @see Cliente
 * @see Trabajador
 * @see Vehiculo
 * @see AccesoBD
 */
public class VentaDAO {

    /** Instancia de acceso a la base de datos usada para obtener conexiones. */
    private AccesoBD bd;

    /** Nombre de la tabla en la base de datos. */
    public static final String NOM_TABLA = "Venta";

    /** Nombre de la columna identificador de la venta. */
    public static final String COL_ID_VENTA = "id_venta";

    /** Nombre de la columna con la clave foránea al cliente de la venta. */
    public static final String COL_ID_CLIENTE = "id_cliente";

    /** Nombre de la columna con la clave foránea al trabajador que atendió la venta. */
    public static final String COL_ID_TRABAJADOR = "id_trabajador";

    /** Nombre de la columna con la clave foránea al vehículo vendido. */
    public static final String COL_ID_VEHICULO = "id_vehiculo";

    /**
     * Nombre de la columna con la fecha y hora de la venta.
     * Su valor es generado automáticamente por la base de datos
     * mediante CURRENT_TIMESTAMP si no se indica explícitamente.
     */
    public static final String COL_FECHA = "fecha";

    /**
     * Crea un nuevo VentaDAO con la instancia de acceso
     * a la base de datos indicada.
     *
     * @param bd instancia de AccesoBD para obtener conexiones
     */
    public VentaDAO(AccesoBD bd) {
        this.bd = bd;
    }

    /**
     * Inserta una nueva venta en la base de datos.
     * <p>
     * El identificador de la venta y la fecha son asignados automáticamente
     * por la base de datos: el primero mediante AUTOINCREMENT y la segunda
     * mediante CURRENT_TIMESTAMP. Por ello, solo se persisten el cliente,
     * el trabajador y el vehículo asociados.
     *
     * @param v venta a insertar; el cliente, trabajador y vehículo deben
     *          existir previamente en la base de datos
     * @return número de filas afectadas; 1 si se insertó correctamente,
     *         -1 si ocurrió un error
     */

	public int insert(Venta v) {
		String sentencia = "INSERT INTO " + NOM_TABLA + " ("
				+ COL_ID_CLIENTE + ", " + COL_ID_TRABAJADOR + ", " + COL_ID_VEHICULO
				+ ") VALUES (?, ?, ?)";

		Connection con = null;
		PreparedStatement stmt = null;
		int res = -1;
		try {
			con = bd.getConexion();
			stmt = con.prepareStatement(sentencia);
			stmt.setInt(1, v.getCliente().getIdCliente());
			stmt.setInt(2, v.getTrabajador().getIdTrabajador());
			stmt.setInt(3, v.getVehiculo().getIdVehiculo());
			res = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
     * Recupera todas las ventas registradas en la base de datos.
     * <p>
     * Realiza JOIN con las tablas Cliente, Trabajador, Vehiculo y Modelo
     * para devolver cada venta con todos sus objetos asociados
     * completamente poblados.
     *
     * @return lista con todas las ventas; lista vacía si no hay
     *         ninguna o si ocurrió un error
     */
	public ArrayList<Venta> selectVentas() {
		String sentencia = "SELECT * FROM " + NOM_TABLA 
				+ " JOIN " + ClienteDAO.NOM_TABLA 
				+ " ON " + NOM_TABLA + "." + COL_ID_CLIENTE + " = " + ClienteDAO.NOM_TABLA + "." + ClienteDAO.COL_ID_CLIENTE 
				+ " JOIN " + TrabajadorDAO.NOM_TABLA 
				+ " ON " + NOM_TABLA + "." + COL_ID_TRABAJADOR + " = " + TrabajadorDAO.NOM_TABLA + "." + TrabajadorDAO.COL_ID_TRABAJADOR 
				+ " JOIN " + VehiculoDAO.NOM_TABLA
				+ " ON " + NOM_TABLA + "." + COL_ID_VEHICULO + " = " + VehiculoDAO.NOM_TABLA + "." + VehiculoDAO.COL_ID_VEHICULO
				+ " JOIN " + ModeloVehiculoDAO.NOM_TABLA 
				+ " ON " + VehiculoDAO.NOM_TABLA + "." + VehiculoDAO.COL_MODELO + " = " + ModeloVehiculoDAO.NOM_TABLA + "." + ModeloVehiculoDAO.COL_ID_MODELO;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		try {
			con = bd.getConexion();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sentencia);
			while (rs.next()) {
				int idVenta = rs.getInt(COL_ID_VENTA);
				
				int idCliente = rs.getInt(ClienteDAO.COL_ID_CLIENTE);
				String nombreCliente = rs.getString(ClienteDAO.COL_NOMBRE_APELLIDOS);
				String metodoPago = rs.getString(ClienteDAO.COL_METODO_PAGO);
				
				int idTrabajador = rs.getInt(TrabajadorDAO.COL_ID_TRABAJADOR);
				String nombreApellidos = rs.getString(TrabajadorDAO.COL_NOMBRE_TRABAJADOR);
				String passwordTrabajador = rs.getString(TrabajadorDAO.COL_PASSWORD_TRABAJADOR);
				int esAdmin = rs.getInt(TrabajadorDAO.COL_ES_ADMIN);
				
            	int idVehiculo = rs.getInt(VehiculoDAO.COL_ID_VEHICULO);
            	int precio = rs.getInt(VehiculoDAO.COL_PRECIO);
            	String matricula = rs.getString(VehiculoDAO.COL_MATRICULA);
            	String color = rs.getString(VehiculoDAO.COL_COLOR);
            	int year = rs.getInt(VehiculoDAO.COL_YEAR);
            	int kilometraje = rs.getInt(VehiculoDAO.COL_KILOMETRAJE);
            	int potenciaCv = rs.getInt(VehiculoDAO.COL_POTENCIA_CV);
            	int cilindrada = rs.getInt(VehiculoDAO.COL_CILINDRADA);
            	int pesoKG = rs.getInt(VehiculoDAO.COL_PESO_KG);
				
                int idModelo = rs.getInt(ModeloVehiculoDAO.COL_ID_MODELO);
                String nombreModelo = rs.getString(ModeloVehiculoDAO.COL_NOMBRE_MODELO);
                int numeroPlazas = rs.getInt(ModeloVehiculoDAO.COL_NUMERO_PLAZAS);
                int numeroPuertas = rs.getInt(ModeloVehiculoDAO.COL_NUMERO_PUERTAS);
                String tipoVehiculo = rs.getString(ModeloVehiculoDAO.COL_TIPO_VEHICULO);
                String tipoPropulsion = rs.getString(ModeloVehiculoDAO.COL_TIPO_PROPULSION);
                String traccion = rs.getString(ModeloVehiculoDAO.COL_TRACCION);
                String nombreMarca = rs.getString(ModeloVehiculoDAO.COL_MARCA);
                String tipoTransmision = rs.getString(ModeloVehiculoDAO.COL_TIPO_TRANSMISION);
                
                String fechaVenta = rs.getString(COL_FECHA);
                
                Cliente cliente = new Cliente(idCliente, nombreCliente, metodoPago);
                
                ModeloVehiculo modelo = new ModeloVehiculo(idModelo, nombreModelo, numeroPlazas,
                		numeroPuertas, tipoVehiculo, tipoPropulsion, traccion, nombreMarca, tipoTransmision);
                
                Vehiculo vehiculo = new Vehiculo(idVehiculo, modelo, precio, matricula, color,
                		year, kilometraje, potenciaCv, cilindrada, pesoKG);
                
                Trabajador trabajador = new Trabajador(idTrabajador, nombreApellidos, passwordTrabajador, 
                		esAdmin);
                
                ventas.add(new Venta(idVenta, cliente, trabajador, vehiculo, fechaVenta));
				
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
		return ventas;
		
	}
}

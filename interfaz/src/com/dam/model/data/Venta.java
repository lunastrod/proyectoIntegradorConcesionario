package com.dam.model.data;
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
 * Representa una venta realizada en el concesionario.
 * <p>
 * Registra la transacción entre un {@link Cliente} y el concesionario,
 * incluyendo el {@link Vehiculo} vendido, el {@link Trabajador} que atendió
 * la venta y la fecha en que se realizó la transacción.
 * <p>
 * Cada venta queda persistida en la base de datos a través de
 * {@link com.dam.model.db.VentaDAO}.
 * @see Cliente
 * @see Trabajador
 * @see Vehiculo
 * @see com.dam.model.db.VentaDAO
 */
public class Venta {

    /** ID único de la venta en la base de datos. */
    private int idVenta;

    /** Cliente que ha adquirido el vehículo. */
    private Cliente cliente;

    /** Trabajador del concesionario que ha atendido la venta. */
    private Trabajador trabajador;

    /** Vehículo que ha sido vendido en esta transacción. */
    private Vehiculo vehiculo;

    /**
     * Fecha y hora en que se realizó la venta.
     * <p>
     * Se almacena como cadena de texto con el formato
     * proporcionado por SQLite DATETIME DEFAULT CURRENT_TIMESTAMP,
     * por ejemplo "2024-03-15 10:30:00".
     */
    private String fecha;

    /**
     * Crea una venta con todos sus atributos.
     * <p>
     * Se usa al recuperar ventas existentes de la base de datos.
     * Para registrar una nueva venta, el idVenta debe ser -1,
     * ya que la base de datos lo asigna automáticamente, y fecha
     * puede ser una cadena vacía, pues la base de datos aplica
     * CURRENT_TIMESTAMP por defecto.
     * @param idVenta    ID único de la venta (-1 si es nueva)
     * @param cliente    cliente que adquiere el vehículo
     * @param trabajador trabajador que atiende la venta
     * @param vehiculo   vehículo vendido
     * @param fecha      fecha y hora de la venta en formato "YYYY-MM-DD HH:MM:SS"}
     */
    public Venta(int idVenta, Cliente cliente, Trabajador trabajador, Vehiculo vehiculo, String fecha) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.trabajador = trabajador;
        this.vehiculo = vehiculo;
        this.fecha = fecha;
    }

    /**
     * Devuelve el ID único de la venta.
     * @return id de la venta
     */
    public int getIdVenta() {
        return idVenta;
    }

    /**
     * Devuelve el cliente que ha adquirido el vehículo.
     * @return cliente de la venta
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Devuelve el trabajador que ha atendido la venta.
     * @return trabajador que gestionó la venta
     */
    public Trabajador getTrabajador() {
        return trabajador;
    }

    /**
     * Devuelve el vehículo que ha sido vendido en esta transacción.
     * @return vehículo vendido
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * Devuelve la fecha y hora en que se realizó la venta.
     * <p>
     * El valor proviene directamente de la base de datos, donde se genera
     * automáticamente mediante CURRENT_TIMESTAMP en el momento
     * de insertar el registro.
     * @return fecha y hora de la venta en formato "YYYY-MM-DD HH:MM:SS"
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Devuelve una representación textual completa de la venta,
     * incluyendo el ID, el cliente, el trabajador,
     * el vehículo y la fecha.
     * <p>
     * Se usa principalmente para depuración y trazas en consola
     * tras registrar una nueva venta.
     *
     * @return cadena multilínea con todos los datos de la venta
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta ").append(idVenta).append("\n");
        sb.append("Realizada por: ").append(cliente).append("\n");
        sb.append("Atendida por: ").append(trabajador).append("\n");
        sb.append("Vehiculo: ").append(vehiculo).append("\n");
        sb.append("Fecha de su venta: ").append(fecha).append("\n");
        return sb.toString();
    }
}
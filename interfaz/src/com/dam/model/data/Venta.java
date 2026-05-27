package com.dam.model.data;

/*
CREATE TABLE IF NOT EXISTS "Venta" (
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

public class Venta {
    private int idVenta;
    private Cliente cliente;
    private Trabajador trabajador;
    private Vehiculo vehiculo;
    private int fecha;
    public Venta(int idVenta, Cliente cliente, Trabajador trabajador, Vehiculo vehiculo, int fecha) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.trabajador = trabajador;
        this.vehiculo = vehiculo;
        this.fecha = fecha;
    }

    public int getIdVenta() {
        return idVenta;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public Trabajador getTrabajador() {
        return trabajador;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public int getFecha() {
    	return fecha;
    }

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

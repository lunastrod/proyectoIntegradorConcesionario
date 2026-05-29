package com.dam.model.db;

import com.dam.model.data.Venta;

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
public class VentaDAO {
	private AccesoBD bd;
	public static final String NOM_TABLA = "Venta";
	public static final String COL_ID_VENTA = "id_venta";
	public static final String COL_ID_CLIENTE = "id_cliente";
	public static final String COL_ID_TRABAJADOR = "id_trabajador";
	public static final String COL_ID_VEHICULO = "id_vehiculo";
	public static final String COL_FECHA = "fecha";
	
	public VentaDAO(AccesoBD bd) {
		this.bd = bd;
	}
	
	public int insert(Venta v) {
		
		return 0;
	}
}

package com.dam.model.data;

/*
CREATE TABLE IF NOT EXISTS "Cliente" (
	"id_cliente"	INTEGER,
	"nombre_apellidos"	varchar(100),
	"metodo_pago"	varchar(50),
	CONSTRAINT "pk_id_cli" PRIMARY KEY("id_cliente" AUTOINCREMENT)
);
*/

/**
 * Representa un cliente del concesionario
 */
public class Cliente {
    /** Identificador único del cliente. */
    private int idCliente;

    /** Nombre y apellidos del cliente. */
    private String nombreApellidos;

    /** Método de pago asociado al cliente. */
    private String metodoPago;

    /**
     * @param idCliente        identificador único del cliente
     * @param nombreApellidos  nombre y apellidos
     * @param metodoPago       método de pago (ej: "tarjeta", "efectivo")
     */
    public Cliente(int idCliente, String nombreApellidos, String metodoPago) {
        this.idCliente = idCliente;
        this.nombreApellidos = nombreApellidos;
        this.metodoPago = metodoPago;
    }

    /**
     * @return identificador del cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @return nombre y apellidos del cliente
     */
    public String getNombreApellidos() {
        return nombreApellidos;
    }

    /**
     * @return método de pago del cliente
     */
    public String getMetodoPago() {
        return metodoPago;
    }

    /**
     * @return cadena con el id, nombre y método de pago del cliente
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(idCliente).append(" ")
          .append(nombreApellidos).append(", método de pago: ").append(metodoPago);
        return sb.toString();
    }
}

package com.dam.model.data;

/*
CREATE TABLE IF NOT EXISTS "Cliente" (
	"id_cliente"	INTEGER,
	"nombre_apellidos"	varchar(100),
	"metodo_pago"	varchar(50),
	CONSTRAINT "pk_id_cli" PRIMARY KEY("id_cliente" AUTOINCREMENT)
);
*/

public class Cliente {
    private int idCliente;
    private String nombreApellidos;
    private String metodoPago;

    public Cliente(int idCliente, String nombreApellidos, String metodoPago) {
        this.idCliente = idCliente;
        this.nombreApellidos = nombreApellidos;
        this.metodoPago = metodoPago;
    }

    public int getIdCliente() {
        return idCliente;
    }
    public String getNombreApellidos() {
        return nombreApellidos;
    }
    
    public String getMetodoPago() {
    	return metodoPago;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(idCliente).append(" ").append(nombreApellidos).append(", método de pago: ").append(metodoPago);
        return sb.toString();
    }
}

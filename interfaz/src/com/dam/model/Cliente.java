package com.dam.model;

/*
CREATE TABLE Cliente (
id_cliente INT PRIMARY KEY,
nombre_apellidos varchar(100)
);

CREATE TABLE Trabajador (
id_trabajador INT PRIMARY KEY,
nombre_apellidos varchar(100)
);
*/

public class Cliente {
    private int idCliente;
    private String nombreApellidos;

    public Cliente(int idCliente, String nombreApellidos) {
        this.idCliente = idCliente;
        this.nombreApellidos = nombreApellidos;
    }

    public int getIdCliente() {
        return idCliente;
    }
    public String getNombreApellidos() {
        return nombreApellidos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente ").append(idCliente).append(" ").append(nombreApellidos);
        return sb.toString();
    }
}

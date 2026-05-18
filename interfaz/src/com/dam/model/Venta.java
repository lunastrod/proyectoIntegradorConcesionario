package com.dam.model;

/*
CREATE TABLE Venta (
id_venta INT PRIMARY KEY,
id_cliente int,
foreign key (id_cliente) references Cliente(id_cliente),
id_trabajador int,
foreign key (id_trabajador) references Trabajador(id_trabajador),
id_vehiculo int,
foreign key (id_vehiculo) references Vehiculo(id_vehiculo)
);
*/

public class Venta {
    private int idVenta;
    private Cliente cliente;
    private Trabajador trabajador;
    private Vehiculo vehiculo;
    public Venta(int idVenta, Cliente cliente, Trabajador trabajador, Vehiculo vehiculo) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.trabajador = trabajador;
        this.vehiculo = vehiculo;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta ").append(idVenta).append("\n");
        sb.append("Realizada por: ").append(cliente).append("\n");
        sb.append("Atendida por: ").append(trabajador).append("\n");
        sb.append("Vehiculo: ").append(vehiculo).append("\n");
        return sb.toString();
    }
}

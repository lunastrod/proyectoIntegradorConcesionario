package com.dam.model.data;

/*
CREATE TABLE Cliente (
id_cliente INT PRIMARY KEY,
nombre_apellidos varchar(100)
);

CREATE TABLE Trabajador (
id_trabajador INT PRIMARY KEY,
nombre_apellidos varchar(100)
);

CREATE TABLE Marca (
nombre_marca varchar(50) PRIMARY KEY
);

CREATE TABLE ModeloVehiculo (
id_modelo INT PRIMARY KEY,
nombre_modelo varchar(80),
nombre_marca varchar(50),
foreign key (nombre_marca) references Marca(nombre_marca)
);

CREATE TABLE Vehiculo (
id_vehiculo INT PRIMARY KEY,
modelo int,
foreign key (modelo) references ModeloVehiculo(id_modelo),
precio int
);

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

public class Vehiculo {
    private int idVehiculo;
    private ModeloVehiculo modelo;
    private int precio;

    public Vehiculo(int idVehiculo, ModeloVehiculo modelo, int precio) {
        this.idVehiculo = idVehiculo;
        this.modelo = modelo;
        this.precio = precio;
    }
    public int getIdVehiculo() {
        return idVehiculo;
    }
    public ModeloVehiculo getModeloVehiculo() {
        return modelo;
    }
    public int getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehiculo ").append(idVehiculo).append(" ").append(modelo);
        sb.append(" - Precio: ").append(precio).append(" euros");

        return sb.toString();
    }
}

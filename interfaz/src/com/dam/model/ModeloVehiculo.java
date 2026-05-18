package com.dam.model;

/*
CREATE TABLE Marca (
nombre_marca varchar(50) PRIMARY KEY
);

CREATE TABLE Modelo (
id_modelo INT PRIMARY KEY,
nombre_modelo varchar(80),
nombre_marca varchar(50),
foreign key (nombre_marca) references Marca(nombre_marca)
);
*/

public class ModeloVehiculo {
    private int idModelo;
    private String nombreModelo;
    private String nombreMarca;

    public ModeloVehiculo(int idModelo, String nombreModelo, String nombreMarca) {
        this.idModelo = idModelo;
        this.nombreModelo = nombreModelo;
        this.nombreMarca = nombreMarca;
    }

    public int getIdModelo() {
        return idModelo;
    }
    public String getNombreModelo() {
        return nombreModelo;
    }
    public String getNombreMarca() {
        return nombreMarca;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Modelo ").append(idModelo).append(" ").append(nombreMarca).append(" ").append(nombreModelo);
        return sb.toString();
    }
}

package com.dam.model.data;

/*
CREATE TABLE Trabajador (
id_trabajador INT PRIMARY KEY,
nombre_apellidos varchar(100),
password_trabajador varchar(100)
);
 */

public class Trabajador {
    private int idTrabajador;
    private String nombreApellidos;
    private String passwordTrabajador;
    
    public Trabajador(int idTrabajador, String nombreApellidos, String passwordTrabajador) {
        this.idTrabajador = idTrabajador;
        this.nombreApellidos = nombreApellidos;
        this.passwordTrabajador = passwordTrabajador;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }
    public String getNombreApellidos() {
        return nombreApellidos;
    }
    
    public String getPasswordTrabajador() {
    	return passwordTrabajador;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Trabajador ").append(idTrabajador).append(" ")
        .append(nombreApellidos).append(" - contraseña: ").append(passwordTrabajador);
        return sb.toString();
    }
}

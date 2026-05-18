package com.dam.model;

public class Trabajador {
    private int idTrabajador;
    private String nombreApellidos;
    public Trabajador(int idTrabajador, String nombreApellidos) {
        this.idTrabajador = idTrabajador;
        this.nombreApellidos = nombreApellidos;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }
    public String getNombreApellidos() {
        return nombreApellidos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Trabajador ").append(idTrabajador).append(" ").append(nombreApellidos);
        return sb.toString();
    }
}

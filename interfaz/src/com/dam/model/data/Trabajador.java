package com.dam.model.data;

/*
CREATE TABLE IF NOT EXISTS "Trabajador" (
	"id_trabajador"	INTEGER,
	"nombre_apellidos"	varchar(100),
	"password_trabajador"	varchar(100),
	"es_admin"	INTEGER,
	CONSTRAINT "pk_id_trabajo" PRIMARY KEY("id_trabajador" AUTOINCREMENT)
);
 */

public class Trabajador {
    private int idTrabajador;
    private String nombreApellidos;
    private String passwordTrabajador;
    private int esAdmin;
    
    public Trabajador(int idTrabajador, String nombreApellidos, String passwordTrabajador, int esAdmin) {
        this.idTrabajador = idTrabajador;
        this.nombreApellidos = nombreApellidos;
        this.passwordTrabajador = passwordTrabajador;
        this.esAdmin = esAdmin;
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
    
    public int getEsAdmin() {
    	return esAdmin;
    }
    
    public String traducirAdmin(int esAdmin) {
    	String estado = "";
    	
    	if (esAdmin == 0) {
    		estado = "Trabajador común";
    	} else if (esAdmin == 1) {
    		estado = "Administrador";
    	}
    	
		return estado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Trabajador ").append(idTrabajador).append(" ")
        .append(nombreApellidos).append(" - contraseña: ").append(passwordTrabajador)
        .append(" - Estado: ").append(traducirAdmin(esAdmin));
        return sb.toString();
    }
}

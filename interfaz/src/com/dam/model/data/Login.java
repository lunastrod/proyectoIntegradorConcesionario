package com.dam.model.data;

public class Login {
    private String usuario;
    private String passwd;
    
    public Login(String usuario, String passwd) {
        this.usuario = usuario;
        this.passwd = passwd;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public String getPasswd() {
        return passwd;
    }

    @Override
    public String toString() {
        return "Intento de Login usuario: " + usuario + " passwd: " + passwd;
    }
}

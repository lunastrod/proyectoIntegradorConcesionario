package com.dam.model.data;

/**
 * Representa las credenciales introducidas por un trabajador
 * para iniciar sesión en la aplicación del concesionario.
 */
public class Login {

    /** Nombre de usuario introducido en el formulario de login. */
    private String usuario;

    /** Contraseña introducida en el formulario de login. */
    private String passwd;

    /**
     * Crea un nuevo objeto Login con las credenciales proporcionadas.
     *
     * @param usuario nombre de usuario del trabajador
     * @param passwd  contraseña del trabajador
     */
    public Login(String usuario, String passwd) {
        this.usuario = usuario;
        this.passwd = passwd;
    }

    /**
     * Devuelve el nombre de usuario introducido.
     * @return nombre de usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Devuelve la contraseña introducida.
     * @return contraseña en texto plano
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * Devuelve una representación textual del intento de login.
     * <p>
     * @return cadena con el usuario y la contraseña introducidas.
     */
    @Override
    public String toString() {
        return "Intento de Login usuario: " + usuario + " passwd: " + passwd;
    }
}
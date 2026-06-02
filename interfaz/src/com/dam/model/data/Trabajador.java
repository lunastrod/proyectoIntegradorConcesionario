package com.dam.model.data;
/*
DROP TABLE IF EXISTS "Trabajador";
CREATE TABLE "Trabajador" (
	"id_trabajador"	INTEGER,
	"nombre_apellidos"	varchar(100),
	"password_trabajador"	varchar(100),
	"es_admin"	INTEGER,
	CONSTRAINT "pk_id_trabajo" PRIMARY KEY("id_trabajador" AUTOINCREMENT)
);
*/
/**
 * Representa a un trabajador del concesionario.
 * <p>
 * Un trabajador puede tener dos roles: empleado común o administrador,
 * indicado por el campo "esAdmin". Las credenciales almacenadas
 * se usan para autenticar al trabajador a través del formulario de login.
 * @see com.dam.model.db.TrabajadorDAO
 */
public class Trabajador {

    /** Identificador único del trabajador en la base de datos. */
    private int idTrabajador;

    /** Nombre y apellidos del trabajador. */
    private String nombreApellidos;

    /** Contraseña del trabajador para iniciar sesión en la aplicación. */
    private String passwordTrabajador;

    /**
     * Indica si el trabajador tiene permisos de administrador.
     * <ul>
     *   <li> 0 — trabajador común</li>
     *   <li> 1 — administrador</li>
     * </ul>
     */
    private int esAdmin;

    /**
     * Crea un trabajador con todos sus atributos, incluyendo el identificador
     * de base de datos. Usado al recuperar registros existentes.
     * @param idTrabajador       identificador único del trabajador
     * @param nombreApellidos    nombre y apellidos del trabajador
     * @param passwordTrabajador contraseña del trabajador
     * @param esAdmin            1 si es administrador, 0 si es empleado común
     */
    public Trabajador(int idTrabajador, String nombreApellidos, String passwordTrabajador, int esAdmin) {
        this.idTrabajador = idTrabajador;
        this.nombreApellidos = nombreApellidos;
        this.passwordTrabajador = passwordTrabajador;
        this.esAdmin = esAdmin;
    }

    /**
     * Crea un trabajador sin identificador, usado al registrar
     * un nuevo trabajador antes de que tenga persistencia en la base de datos.
     * @param nombreApellidos    nombre y apellidos del trabajador
     * @param passwordTrabajador contraseña del trabajador
     * @param esAdmin            1 si es administrador, 0 si es empleado común
     */
    public Trabajador(String nombreApellidos, String passwordTrabajador, int esAdmin) {
        this.nombreApellidos = nombreApellidos;
        this.passwordTrabajador = passwordTrabajador;
        this.esAdmin = esAdmin;
    }

    /**
     * Devuelve el identificador único del trabajador.
     * @return id del trabajador
     */
    public int getIdTrabajador() {
        return idTrabajador;
    }

    /**
     * Devuelve el nombre y apellidos del trabajador.
     * @return nombre y apellidos
     */
    public String getNombreApellidos() {
        return nombreApellidos;
    }

    /**
     * Devuelve la contraseña del trabajador en texto plano.
     * <p>
     * <strong>Precaución:</strong> no exponer este valor en logs
     * ni en entornos accesibles por terceros.
     * @return contraseña del trabajador
     */
    public String getPasswordTrabajador() {
        return passwordTrabajador;
    }

    /**
     * Devuelve el valor numérico que indica el rol del trabajador.
     * @return 1 si es administrador, 0 si es empleado común
     */
    public int getEsAdmin() {
        return esAdmin;
    }

    /**
     * Traduce el valor numérico del rol a una cadena legible.
     * @param esAdmin valor numérico del rol (0 o 1)
     * @return Administrador si esAdmin es 1,
     *         Trabajador común si es 0,
     *         o cadena vacía si el valor no es reconocido
     */
    public String traducirAdmin(int esAdmin) {
        String estado = "";

        if (esAdmin == 0) {
            estado = "Trabajador común";
        } else if (esAdmin == 1) {
            estado = "Administrador";
        }

        return estado;
    }
    /*
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Trabajador ").append(idTrabajador).append(" ")
        .append(nombreApellidos).append(" - contraseña: ").append(passwordTrabajador)
        .append(" - Estado: ").append(traducirAdmin(esAdmin));
        return sb.toString();
    }
    */
    /**
     * Devuelve el nombre y apellidos del trabajador como representación textual.
     * <p>
     * Este formato simplificado se usa en los desplegables de JComboBox
     * de la interfaz para mostrar al trabajador de forma legible.
     * @return nombre y apellidos del trabajador
     */
    @Override
    public String toString() {
        return nombreApellidos;
    }
}
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

    /** ID único del trabajador en la base de datos. */
    private int idTrabajador;

    /** Nombre y apellidos del trabajador. */
    private String nombreTrabajador;

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
     * Crea un trabajador con todos sus atributos, incluyendo el ID
     * de base de datos. Usado al recuperar registros existentes.
     * @param idTrabajador       ID único del trabajador
     * @param nombreTrabajador    nombre y apellidos del trabajador
     * @param passwordTrabajador contraseña del trabajador
     * @param esAdmin            1 si es administrador, 0 si es empleado común
     */
    public Trabajador(int idTrabajador, String nombreTrabajador, String passwordTrabajador, int esAdmin) {
        this.idTrabajador = idTrabajador;
        this.nombreTrabajador = nombreTrabajador;
        this.passwordTrabajador = passwordTrabajador;
        this.esAdmin = esAdmin;
    }

    /**
     * Crea un trabajador sin ID, usado al registrar
     * un nuevo trabajador antes de que tenga persistencia en la base de datos.
     * @param nombreTrabajador    nombre y apellidos del trabajador
     * @param passwordTrabajador contraseña del trabajador
     * @param esAdmin            1 si es administrador, 0 si es empleado común
     */
    public Trabajador(String nombreTrabajador, String passwordTrabajador, int esAdmin) {
        this.nombreTrabajador = nombreTrabajador;
        this.passwordTrabajador = passwordTrabajador;
        this.esAdmin = esAdmin;
    }

    /**
     * Devuelve el ID único del trabajador.
     * @return id del trabajador
     */
    public int getIdTrabajador() {
        return idTrabajador;
    }

    /**
     * Devuelve el nombre y apellidos del trabajador.
     * @return nombre y apellidos
     */
    public String getNombreTrabajador() {
        return nombreTrabajador;
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

    /**
     * Valida si una contraseña cumple con los requisitos de seguridad.
     * <p>
     * Comprueba que la longitud sea de al menos 8 caracteres y menor de 90, 
     * y que contenga al menos una mayúscula, una minúscula, un número y un símbolo.
     * @param contrasena contraseña a comprobar
     * @return true si la contraseña es válida, false en caso contrario
     */
    public static boolean contrasenaValida(String contrasena) {
        boolean valida = false;

        if (contrasena != null) {
            int longitud = contrasena.length();
            
            if (longitud >= 8 && longitud <= 90) {
                boolean tieneMayuscula = false;
                boolean tieneMinuscula = false;
                boolean tieneNumero = false;
                boolean tieneSimbolo = false;

                for (int i = 0; i < longitud && !(tieneMayuscula && tieneMinuscula && tieneNumero && tieneSimbolo); i++) {
                    char c = contrasena.charAt(i);

                    if (Character.isUpperCase(c)) {
                        tieneMayuscula = true;
                    } else if (Character.isLowerCase(c)) {
                        tieneMinuscula = true;
                    } else if (Character.isDigit(c)) {
                        tieneNumero = true;
                    } else if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                        tieneSimbolo = true;
                    }
                }

                valida = tieneMayuscula && tieneMinuscula && tieneNumero && tieneSimbolo;
            }
        }

        return valida;
    }

    /**
     * Devuelve el nombre y apellidos del trabajador como representación textual.
     * <p>
     * Este formato simplificado se usa en los desplegables de JComboBox
     * de la interfaz para mostrar al trabajador de forma legible.
     * @return nombre y apellidos del trabajador
     */
    @Override
    public String toString() {
        return nombreTrabajador;
    }
}
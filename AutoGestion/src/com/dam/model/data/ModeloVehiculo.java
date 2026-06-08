package com.dam.model.data;
/*
DROP TABLE IF EXISTS "Modelo";
CREATE TABLE "Modelo" (
	"id_modelo"	INTEGER,
	"nombre_modelo"	varchar(80),
	"numero_plazas"	INTEGER,
	"numero_puertas"	INTEGER,
	"tipo_vehiculo"	varchar(100),
	"tipo_propulsion"	varchar(100),
	"traccion"	varchar(20),
	"marca"	varchar(70),
	"tipo_transmision"	varchar(30),
	CONSTRAINT "pk_id" PRIMARY KEY("id_modelo" AUTOINCREMENT)
);
*/
/**
 * Representa el modelo de un vehículo del concesionario.
 * <p>
 * Contiene las características técnicas comunes a todos los vehículos
 * que partan de un modelo en concreto.
 * De un solo modelo pueden salir uno o muchos vehículos.
 * @see Vehiculo
 */
public class ModeloVehiculo {

    /**
     * Tipos de transmisión disponibles para un modelo de vehículo.
     * Usado para los desplegables de la interfaz.
     */
    public static String[] TIPOS_TRANSMISION = {"Manual", "Automático"};

    /**
     * Tipos de propulsión disponibles para un modelo de vehículo.
     * Usado para los desplegables de la interfaz.
     */
    public static String[] TIPOS_PROPULSION = {"Diesel", "Gasolina", "Eléctrico", "Híbrido", "Híbrido enchufable"};

    /**
     * Tipos de tracción disponibles para un modelo de vehículo.
     * Usado para los desplegables de la interfaz.
     */
    public static String[] TIPOS_TRACCION = {"Delantera", "Trasera", "4x4", "AWD"};

    /**
     * Tipos de vehículo disponibles para clasificar un modelo.
     * Usado para los desplegables de la interfaz.
     */
    public static String[] TIPOS_VEHICULOS = {
        "Autobús", "Berlina", "Camión articulado", "Camión rígido", "Camioneta",
        "Ciclomotor", "Compacto", "Deportivo", "Descapotable", "Familiar",
        "Furgoneta", "Motocicleta", "SUV", "Todoterreno"
    };

    /** ID único del modelo en la base de datos. */
    private int idModelo;

    /** Nombre comercial del modelo (p. ej. "Corolla", "Corsa"). */
    private String nombreModelo;

    /** Número de plazas del modelo. */
    private int numeroPlazas;

    /** Número de puertas del modelo. */
    private int numeroPuertas;

    /** Categoría del vehículo (p. ej. "SUV", "Deportivo"). */
    private String tipoModelo;

    /** Tipo de propulsión del modelo (p. ej. "Gasolina", "Eléctrico"). */
    private String tipoPropulsion;

    /** Tipo de tracción del modelo (p. ej. "Delantera", "4x4"). */
    private String traccion;

    /** Nombre de la marca a la que pertenece el modelo (p. ej. "Marussia"). */
    private String marca;

    /** Tipo de transmisión del modelo (p. ej. "Manual", "Automático"). */
    private String tipoTransmision;

    /**
     * Crea un nuevo ModeloVehiculo con todos sus atributos.
     *
     * @param idModelo        ID único del modelo.
     * @param nombreModelo    nombre comercial del modelo.
     * @param numeroPlazas    número de plazas.
     * @param numeroPuertas   número de puertas.
     * @param tipoModelo      categoría del vehículo.
     * @param tipoPropulsion  tipo de propulsión.
     * @param traccion        tipo de tracción.
     * @param marca           nombre de la marca.
     * @param tipoTransmision tipo de transmisión.
     */
    public ModeloVehiculo(int idModelo, String nombreModelo, int numeroPlazas, int numeroPuertas,
            String tipoModelo, String tipoPropulsion, String traccion,
            String marca, String tipoTransmision) {
        this.idModelo = idModelo;
        this.nombreModelo = nombreModelo;
        this.numeroPlazas = numeroPlazas;
        this.numeroPuertas = numeroPuertas;
        this.tipoModelo = tipoModelo;
        this.tipoPropulsion = tipoPropulsion;
        this.traccion = traccion;
        this.marca = marca;
        this.tipoTransmision = tipoTransmision;
    }

    /**
     * Devuelve el ID único del modelo.
     * @return id del modelo
     */
    public int getIdModelo() {
        return idModelo;
    }

    /**
     * Devuelve el nombre comercial del modelo.
     * @return nombre del modelo
     */
    public String getNombreModelo() {
        return nombreModelo;
    }

    /**
     * Devuelve el número de plazas del modelo.
     * @return número de plazas
     */
    public int getNumeroPlazas() {
        return numeroPlazas;
    }

    /**
     * Devuelve el número de puertas del modelo.
     * @return número de puertas
     */
    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    /**
     * Devuelve la categoría del vehículo (p. ej. "SUV", "Berlina").
     * @return tipo de vehículo
     */
    public String getTipoModelo() {
        return tipoModelo;
    }

    /**
     * Devuelve el tipo de propulsión del modelo.
     * @return tipo de propulsión
     */
    public String getTipoPropulsion() {
        return tipoPropulsion;
    }

    /**
     * Devuelve el tipo de tracción del modelo.
     * @return tipo de tracción
     */
    public String getTraccion() {
        return traccion;
    }

    /**
     * Devuelve el nombre de la marca a la que pertenece el modelo.
     * @return nombre de la marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Devuelve el tipo de transmisión del modelo.
     * @return tipo de transmisión
     */
    public String getTipoTransmision() {
        return tipoTransmision;
    }
    /**
     * Devuelve una representación resumida del modelo formada
     * por la marca y el nombre del modelo.
     * @return cadena con el formato "{marca} {nombreModelo}"
     */
    @Override
    public String toString() {
        return marca + " " + nombreModelo;
    }
}
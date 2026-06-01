package com.dam.model.data;
/*
DROP TABLE IF EXISTS "Vehiculo";
CREATE TABLE "Vehiculo" (
	"id_vehiculo"	INTEGER,
	"modelo"	INTEGER,
	"precio"	INTEGER,
	"matricula"	varchar(40) UNIQUE,
	"color"	varchar(40),
	"year"	INTEGER,
	"kilometraje"	INTEGER,
	"potencia_cv"	INTEGER,
	"cilindrada"	INTEGER,
	"peso_kg"	INTEGER,
	CONSTRAINT "pk_id_car" PRIMARY KEY("id_vehiculo" AUTOINCREMENT),
	CONSTRAINT "fk_model" FOREIGN KEY("modelo") REFERENCES "Modelo"("id_modelo")
);
*/
/**
 * Representa un vehículo concreto disponible en el concesionario.
 * <p>
 * Cada vehículo está asociado a un ModeloVehiculo que define
 * sus características técnicas comunes, mientras que esta clase almacena
 * los atributos específicos de la unidad física: matrícula, color,
 * kilometraje, precio, etc.
 *
 * @see ModeloVehiculo
 * @see com.dam.model.db.VehiculoDAO
 */
public class Vehiculo {

    /** Identificador único del vehículo en la base de datos. */
    private int idVehiculo;

    /**
     * Modelo al que pertenece este vehículo.
     * Contiene las características técnicas comunes al modelo.
     */
    private ModeloVehiculo modelo;

    /** Precio de venta del vehículo en euros. */
    private int precio;

    /**
     * Matrícula del vehículo.
     * Valor único en la base de datos.
     */
    private String matricula;

    /**
     * Color de la pintura del vehículo, almacenado en formato
     * RGB.
     */
    private String color;

    /** Año de fabricación del vehículo. */
    private int year;

    /** Kilometraje actual del vehículo en kilómetros. */
    private int kilometraje;

    /** Potencia del motor del vehículo en caballos de vapor (CV). */
    private int potenciaCV;

    /** Cilindrada del motor del vehículo en centímetros cúbicos (cc). */
    private int cilindrada;

    /** Peso del vehículo en kilogramos (kg). */
    private int pesoKG;

    /**
     * Crea un vehículo con todos sus atributos.
     * <p>
     * Se usa tanto al recuperar vehículos de la base de datos como al
     * construir uno nuevo antes de persistirlo (en ese caso idVehiculo
     * debe ser -1, ya que la base de datos lo asigna automáticamente).
     *
     * @param idVehiculo  identificador único del vehículo (-1 si es nuevo)
     * @param modelo      modelo al que pertenece el vehículo
     * @param precio      precio de venta en euros
     * @param matricula   matrícula del vehículo
     * @param color       color en formato RGB
     * @param year        año de fabricación
     * @param kilometraje kilometraje actual en kilómetros
     * @param potenciaCV  potencia del motor en CV
     * @param cilindrada  cilindrada del motor en cc
     * @param pesoKG      peso del vehículo en kg
     */
    public Vehiculo(int idVehiculo, ModeloVehiculo modelo, int precio, String matricula,
            String color, int year, int kilometraje, int potenciaCV, int cilindrada, int pesoKG) {
        this.idVehiculo = idVehiculo;
        this.modelo = modelo;
        this.precio = precio;
        this.matricula = matricula;
        this.color = color;
        this.year = year;
        this.kilometraje = kilometraje;
        this.potenciaCV = potenciaCV;
        this.cilindrada = cilindrada;
        this.pesoKG = pesoKG;
    }

    /**
     * Devuelve el identificador único del vehículo.
     *
     * @return id del vehículo
     */
    public int getIdVehiculo() {
        return idVehiculo;
    }

    /**
     * Devuelve el modelo al que pertenece este vehículo.
     *
     * @return modelo del vehículo
     */
    public ModeloVehiculo getModelo() {
        return modelo;
    }

    /**
     * Devuelve el precio de venta del vehículo en euros.
     *
     * @return precio en euros
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Devuelve la matrícula del vehículo.
     *
     * @return matrícula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Devuelve el color del vehículo en formato RGB.
     * @return color en formato RGB personalizado
     */
    public String getColor() {
        return color;
    }

    /**
     * Devuelve el año de fabricación del vehículo.
     *
     * @return año de fabricación
     */
    public int getYear() {
        return year;
    }

    /**
     * Devuelve el kilometraje actual del vehículo en kilómetros.
     *
     * @return kilometraje en km
     */
    public int getKilometraje() {
        return kilometraje;
    }

    /**
     * Devuelve la potencia del motor del vehículo en caballos de vapor.
     *
     * @return potencia en CV
     */
    public int getPotenciaCV() {
        return potenciaCV;
    }

    /**
     * Devuelve la cilindrada del motor del vehículo en centímetros cúbicos.
     *
     * @return cilindrada en cc
     */
    public int getCilindrada() {
        return cilindrada;
    }

    /**
     * Devuelve el peso del vehículo en kilogramos.
     *
     * @return peso en kg
     */
    public int getPesoKG() {
        return pesoKG;
    }

    /**
     * Genera una cadena con las especificaciones técnicas completas del vehículo,
     * combinando los atributos propios de la unidad y los del modelo asociado.
     * <p>
     * Se usa para mostrar el detalle del vehículo en el panel de PInformacionVehiculo.
     *
     * @return cadena multilínea con todas las especificaciones técnicas
     */
    public String getEspecificacionesTecnicas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Año: ").append(year).append("\n");
        sb.append("Kilometraje: ").append(kilometraje).append("\n");
        sb.append("Matrícula: ").append(matricula).append("\n");
        sb.append("Número de plazas: ").append(modelo.getNumeroPlazas()).append("\n");
        sb.append("Número de puertas: ").append(modelo.getNumeroPuertas()).append("\n");
        sb.append("Color de pintura: ").append(color).append("\n");
        sb.append("Peso (Kg): ").append(pesoKG).append("\n");
        sb.append("Potencia (cv): ").append(potenciaCV).append("\n");
        sb.append("Cilindrada (cc): ").append(cilindrada).append("\n");
        sb.append("Tipo de propulsión: ").append(modelo.getTipoPropulsion()).append("\n");
        sb.append("Tipo de tracción: ").append(modelo.getTraccion()).append("\n");
        sb.append("Tipo de transmisión: ").append(modelo.getTipoTransmision()).append("\n");
        return sb.toString();
    }
	/*
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehiculo ").append(idVehiculo).append(" ").append(modelo);
        sb.append(" - Precio: ").append(precio).append(" euros").append(" - Matrícula: ").append(matricula)
        .append(" - Color: ").append(color).append(" - Año: ").append(year).append(" - Kilometraje: ").append(kilometraje)
        .append(" - Potencia (CV): ").append(potenciaCV).append(" - Cilindros: ").append(cilindrada).append(" - Peso (KG): ")
        .append(pesoKG);

        return sb.toString();
    }
	*/
    /**
     * Devuelve una representación resumida del vehículo formada por
     * la matrícula, el nombre del modelo y la marca.
     * <p>
     * Este formato se usa en los listados de la interfaz
	 * (p. ej JList del panel de PNuevoVehiculo).
     * @return cadena con el formato "[matricula] nombreModelo marca"
     */
    @Override
    public String toString() {
        return "[" + matricula + "] " + modelo.getNombreModelo() + " " + modelo.getMarca();
    }
}
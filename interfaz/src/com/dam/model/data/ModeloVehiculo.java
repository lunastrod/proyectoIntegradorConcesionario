package com.dam.model.data;

/*
CREATE TABLE IF NOT EXISTS "Modelo" (
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

public class ModeloVehiculo {
	public static String[] TIPOS_TRANSMISION={"Manual","Automático"};
    public static String[] TIPOS_PROPULSION={"Diesel","Gasolina","Eléctrico","Híbrido","Híbrido enchufable"};
    public static String[] TIPOS_TRACCION={"Delantera","Trasera","4x4","AWD"};
	public static String[] TIPOS_VEHICULOS={"Autobús","Berlina","Camión articulado","Camión rígido","Camioneta","Ciclomotor","Compacto","Deportivo","Descapotable","Familiar","Furgoneta","Motocicleta","SUV","Todoterreno"};

    private int idModelo;
    private String nombreModelo;
    private int numeroPlazas;
    private int numeroPuertas;
    private String tipoModelo;
    private String tipoPropulsion;
    private String traccion;
    private String marca;
    private String tipoTransmision;

    public ModeloVehiculo(int idModelo, String nombreModelo, int numeroPlazas, int numeroPuertas, String tipoModelo, String tipoPropulsion,
			String traccion, String marca, String tipoTransmision) {
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
    
	public int getIdModelo() {
		return idModelo;
	}

	public String getNombreModelo() {
		return nombreModelo;
	}

	public int getNumeroPlazas() {
		return numeroPlazas;
	}

	public int getNumeroPuertas() {
		return numeroPuertas;
	}
	
	public String getTipoModelo() {
		return tipoModelo;
	}

	public String getTipoPropulsion() {
		return tipoPropulsion;
	}

	public String getTraccion() {
		return traccion;
	}

	public String getMarca() {
		return marca;
	}

	public String getTipoTransmision() {
		return tipoTransmision;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Modelo ").append(idModelo).append(" ").append(nombreModelo).append(" - Número de plazas: ")
        .append(numeroPlazas).append(" - Número de puertas: ").append(numeroPuertas).append(" - Tipo de propulsión: ").append(tipoPropulsion)
        .append(" - Tracción: ").append(traccion).append(" - Marca: ").append(marca).append(" - Tipo de transmisión: ").append(tipoTransmision);
        return sb.toString();
    }
}

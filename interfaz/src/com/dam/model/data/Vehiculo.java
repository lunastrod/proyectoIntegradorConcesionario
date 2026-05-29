package com.dam.model.data;

/*
CREATE TABLE IF NOT EXISTS "Vehiculo" (
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

public class Vehiculo {
    private int idVehiculo;
    private ModeloVehiculo modelo;
    private int precio;
    private String matricula;
    private String color;
    private int year;
    private int kilometraje;
    private int potenciaCV;
    private int cilindrada;
    private int pesoKG;

    public Vehiculo(int idVehiculo, ModeloVehiculo modelo, int precio, String matricula, String color, int year,
			int kilometraje, int potenciaCV, int cilindrada, int pesoKG) {
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

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public ModeloVehiculo getModelo() {
		return modelo;
	}

	public int getPrecio() {
		return precio;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getColor() {
		return color;
	}

	public int getYear() {
		return year;
	}

	public int getKilometraje() {
		return kilometraje;
	}

	public int getPotenciaCV() {
		return potenciaCV;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public int getPesoKG() {
		return pesoKG;
	}

    public String getEspecificacionesTecnicas(){
        StringBuilder sb = new StringBuilder();
        sb.append("Año: ").append(year).append("\n");
        sb.append("Kilometraje: ").append(kilometraje).append("\n");
        sb.append("Matrícula: ").append(matricula).append("\n");
        sb.append("Color de pintura: ").append(color).append("\n");
        sb.append("Potencia (cv): ").append(potenciaCV).append("\n");
        sb.append("Número de Cilindros: ").append(cilindrada).append("\n");
        sb.append("Peso (Kg): ").append(pesoKG).append("\n");
        return sb.toString();
    }
	
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
}

package com.dam.model.db;

import java.sql.Connection;
import java.sql.Statement;
import com.dam.model.db.AccesoBD;

public class SetupBD {

    private AccesoBD bd;

    public SetupBD(AccesoBD bd) {
        this.bd = bd;
    }

    /** Drops, recreates all tables and inserts sample data. */
    public void crearEsquema() {
        Connection con = null;
        Statement stmt = null;
        try {
            con = bd.getConexion();
            stmt = con.createStatement();

            stmt.executeUpdate("PRAGMA foreign_keys = ON");

            stmt.executeUpdate("DROP TABLE IF EXISTS Venta");
            stmt.executeUpdate("DROP TABLE IF EXISTS Vehiculo");
            stmt.executeUpdate("DROP TABLE IF EXISTS Modelo");
            stmt.executeUpdate("DROP TABLE IF EXISTS Trabajador");
            stmt.executeUpdate("DROP TABLE IF EXISTS Cliente");

            stmt.executeUpdate(
                "CREATE TABLE Cliente (" +
                "id_cliente INTEGER, " +
                "nombre_apellidos VARCHAR(100), " +
                "metodo_pago VARCHAR(50), " +
                "CONSTRAINT pk_id_cli PRIMARY KEY(id_cliente AUTOINCREMENT))");

            stmt.executeUpdate(
                "CREATE TABLE Modelo (" +
                "id_modelo INTEGER, " +
                "nombre_modelo VARCHAR(80), " +
                "numero_plazas INTEGER, " +
                "numero_puertas INTEGER, " +
                "tipo_vehiculo VARCHAR(100), " +
                "tipo_propulsion VARCHAR(100), " +
                "traccion VARCHAR(20), " +
                "marca VARCHAR(70), " +
                "tipo_transmision VARCHAR(30), " +
                "CONSTRAINT pk_id PRIMARY KEY(id_modelo AUTOINCREMENT))");

            stmt.executeUpdate(
                "CREATE TABLE Trabajador (" +
                "id_trabajador INTEGER, " +
                "nombre_apellidos VARCHAR(100), " +
                "password_trabajador VARCHAR(100), " +
                "es_admin INTEGER, " +
                "CONSTRAINT pk_id_trabajo PRIMARY KEY(id_trabajador AUTOINCREMENT))");

            stmt.executeUpdate(
                "CREATE TABLE Vehiculo (" +
                "id_vehiculo INTEGER, " +
                "modelo INTEGER, " +
                "precio INTEGER, " +
                "matricula VARCHAR(40) UNIQUE, " +
                "color VARCHAR(40), " +
                "year INTEGER, " +
                "kilometraje INTEGER, " +
                "potencia_cv INTEGER, " +
                "cilindrada INTEGER, " +
                "peso_kg INTEGER, " +
                "CONSTRAINT pk_id_car PRIMARY KEY(id_vehiculo AUTOINCREMENT), " +
                "CONSTRAINT fk_model FOREIGN KEY(modelo) REFERENCES Modelo(id_modelo))");

            stmt.executeUpdate(
                "CREATE TABLE Venta (" +
                "id_venta INTEGER, " +
                "id_cliente INTEGER, " +
                "id_trabajador INTEGER, " +
                "id_vehiculo INTEGER, " +
                "fecha DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "CONSTRAINT pk_id_venta PRIMARY KEY(id_venta AUTOINCREMENT), " +
                "CONSTRAINT fk_id_cli FOREIGN KEY(id_cliente) REFERENCES Cliente(id_cliente), " +
                "CONSTRAINT fk_id_trabajo FOREIGN KEY(id_trabajador) REFERENCES Trabajador(id_trabajador), " +
                "CONSTRAINT fk_id_car FOREIGN KEY(id_vehiculo) REFERENCES Vehiculo(id_vehiculo))");

            stmt.executeUpdate(
                "INSERT INTO Cliente (id_cliente, nombre_apellidos, metodo_pago) VALUES " +
                "(2, 'Ana Ruiz López', 'Tarjeta de crédito'), " +
                "(3, 'Pedro Infante Rivera', 'Transferencia bancaria')");

            stmt.executeUpdate(
                "INSERT INTO Modelo (id_modelo, nombre_modelo, numero_plazas, numero_puertas, tipo_vehiculo, tipo_propulsion, traccion, marca, tipo_transmision) VALUES " +
                "(17, 'Clase A', 5, 4, 'Compacto', 'Gasolina', 'Delantera', 'Mercedes-Benz', 'Automático'), " +
                "(18, 'Golf', 5, 4, 'Compacto', 'Diesel', 'Delantera', 'Volkswagen', 'Manual'), " +
                "(19, 'Model 3', 5, 4, 'Turismo', 'Eléctrico', 'AWD', 'Tesla', 'Automático'), " +
                "(20, 'Actros', 2, 2, 'Camión', 'Diesel', 'Trasera', 'Mercedes-Benz', 'Manual'), " +
                "(21, 'Civic', 5, 4, 'Turismo', 'Híbrido', 'Delantera', 'Honda', 'Automático'), " +
                "(22, 'Ducati 959', 2, 0, 'Motocicleta', 'Gasolina', 'Trasera', 'Ducati', 'Manual'), " +
                "(23, 'Moviq', 10, 2, 'Autobús urbano', 'Eléctrico', 'Delantera', 'IVECO', 'Automático'), " +
                "(24, 'Transit', 3, 4, 'Furgoneta', 'Diesel', 'Delantera', 'Ford', 'Manual'), " +
                "(25, 'Cayenne', 5, 4, 'SUV', 'Híbrido enchufable', 'AWD', 'Porsche', 'Automático'), " +
                "(26, 'Mustang', 4, 2, 'Deportivo', 'Gasolina', 'Trasera', 'Ford', 'Manual'), " +
                "(27, 'Outlander', 7, 4, 'SUV', 'Híbrido enchufable', '4x4', 'Mitsubishi', 'Automático'), " +
                "(28, 'Hilux', 5, 4, 'Pick-up', 'Diesel', '4x4', 'Toyota', 'Manual'), " +
                "(29, 'Ibiza', 5, 4, 'Compacto', 'Gasolina', 'Delantera', 'SEAT', 'Manual'), " +
                "(30, 'Clase S', 5, 4, 'Berlina', 'Híbrido enchufable', 'AWD', 'Mercedes-Benz', 'Automático'), " +
                "(31, 'Sprinter', 2, 4, 'Furgoneta', 'Diesel', 'Delantera', 'Mercedes-Benz', 'Manual')");

            stmt.executeUpdate(
                "INSERT INTO Trabajador (id_trabajador, nombre_apellidos, password_trabajador, es_admin) VALUES " +
                "(85, 'Carlos Gómez Pérez', 'admin1234', 1), " +
                "(86, 'Laura Martínez Soler', 'empleado2026', 0)");

            stmt.executeUpdate(
                "INSERT INTO Vehiculo (id_vehiculo, modelo, precio, matricula, color, year, kilometraje, potencia_cv, cilindrada, peso_kg) VALUES " +
                "(17, 17, 28500, '4521 BKR', 'Negro', 2021, 32000, 163, 1332, 1365), " +
                "(18, 18, 19900, '7834 MPT', 'Blanco', 2019, 87000, 115, 1968, 1370), " +
                "(19, 19, 41200, '3301 ZFG', 'Rojo', 2022, 8000, 358, 0, 1844), " +
                "(20, 20, 89000, '9821 TRK', 'Gris', 2020, 210000, 430, 12800, 7500), " +
                "(21, 21, 24300, '6612 HNV', 'Azul', 2021, 45000, 143, 1498, 1395), " +
                "(22, 22, 18700, '1123 DCV', 'Naranja', 2020, 19000, 157, 955, 209), " +
                "(23, 23, 320000, '2255 BUS', 'Blanco', 2023, 5000, 240, 0, 9800), " +
                "(24, 24, 31500, '8847 FGT', 'Blanco', 2018, 135000, 130, 1995, 2100), " +
                "(25, 25, 97000, '5593 PKQ', 'Negro', 2022, 12000, 462, 2995, 2195), " +
                "(26, 26, 54900, '4478 MST', 'Azul', 2020, 27000, 450, 4951, 1668), " +
                "(27, 27, 38600, '7765 OTL', 'Plata', 2021, 53000, 224, 2360, 1990), " +
                "(28, 28, 34200, '3349 HLX', 'Gris', 2019, 98000, 204, 2755, 2100), " +
                "(29, 29, 14800, '9901 IBZ', 'Rojo', 2020, 61000, 95, 1498, 1075), " +
                "(30, 30, 112000, '6630 MBS', 'Negro', 2023, 3000, 510, 2999, 2215), " +
                "(31, 31, 42000, '1182 SPR', 'Blanco', 2019, 175000, 143, 2143, 3050)");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
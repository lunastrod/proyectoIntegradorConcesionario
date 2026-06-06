package com.dam.test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;
import com.dam.model.data.Vehiculo;

public class ConcesionarioFuncionalTest {
    @Test
    public void testMatriculaValida() {
        assertTrue(Vehiculo.matriculaValida("4521 BKR"));
        assertTrue(Vehiculo.matriculaValida("0000 AAA"));
        assertFalse(Vehiculo.matriculaValida("452 BKR"));
        assertFalse(Vehiculo.matriculaValida("4521 BK"));
        assertFalse(Vehiculo.matriculaValida("4521BKR"));
        assertFalse(Vehiculo.matriculaValida("4521 bkr"));
        assertFalse(Vehiculo.matriculaValida("ABCD EFG"));
        assertFalse(Vehiculo.matriculaValida(""));
        assertFalse(Vehiculo.matriculaValida(null));
    }

    private Vehiculo crearVehiculoConColor(String cadenaColor) {
        return new Vehiculo(1, null, 25000, "1234 ABC", cadenaColor, 2026, 10000, 150, 1998, 1400);
    }

    @Test
    public void testGetColorParsedValido() {
        // Test con un vehículo que tiene un color hexadecimal válido
        Vehiculo v1 = crearVehiculoConColor("#1E1E23");
        assertEquals(new Color(30, 30, 35), v1.getColorParsed());

        // Test con el color en formato minúsculas (Color.decode lo soporta nativamente)
        Vehiculo v2 = crearVehiculoConColor("#f0f2f5");
        assertEquals(new Color(240, 242, 245), v2.getColorParsed());
        
        // Test con un color básico del sistema (Rojo Puro)
        Vehiculo v3 = crearVehiculoConColor("#FF0000");
        assertEquals(Color.RED, v3.getColorParsed());
    }

    @Test
    public void testGetColorParsedInvalido() {
        // Formato incorrecto (falta el carácter inicial #)
        Vehiculo v1 = crearVehiculoConColor("1E1E23");
        assertEquals(Color.WHITE, v1.getColorParsed());

        // Contiene caracteres no hexadecimales (fuera del rango A-F o 0-9)
        Vehiculo v2 = crearVehiculoConColor("#G0G2G5");
        assertEquals(Color.WHITE, v2.getColorParsed());

        // Longitud incorrecta (formato incompleto)
        Vehiculo v3 = crearVehiculoConColor("#FF0");
        assertEquals(Color.WHITE, v3.getColorParsed());

        // Formato antiguo 'RGB' para asegurar que el catch lo gestiona devolviendo Blanco
        Vehiculo v4 = crearVehiculoConColor("R30G30B35");
        assertEquals(Color.WHITE, v4.getColorParsed());
    }

    @Test
    public void testGetColorParsedBordesYNulos() {
        // Cadena vacía
        Vehiculo v1 = crearVehiculoConColor("");
        assertEquals(Color.WHITE, v1.getColorParsed());

        // Valor nulo (provoca NullPointerException capturado de forma segura por tu catch)
        Vehiculo v2 = crearVehiculoConColor(null);
        assertEquals(Color.WHITE, v2.getColorParsed());
    }
}
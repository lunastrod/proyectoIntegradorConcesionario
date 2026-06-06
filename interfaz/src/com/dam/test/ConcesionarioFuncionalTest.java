package com.dam.test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import com.dam.model.data.Trabajador;
import com.dam.model.data.Vehiculo;

public class ConcesionarioFuncionalTest {
    @Test
    public void testMatriculaValidaCorrectas() {
        assertTrue(Vehiculo.matriculaValida("4521 BKR"));
        assertTrue(Vehiculo.matriculaValida("0000 AAA"));
    }

    @Test
    public void testMatriculaInvalidaPorLongitud() {
        assertFalse(Vehiculo.matriculaValida("452 BKR"));
        assertFalse(Vehiculo.matriculaValida("4521 BK"));
    }

    @Test
    public void testMatriculaInvalidaPorFaltaDeEspacio() {
        assertFalse(Vehiculo.matriculaValida("4521BKR"));
    }

    @Test
    public void testMatriculaInvalidaPorMinusculas() {
        assertFalse(Vehiculo.matriculaValida("4521 bkr"));
    }

    @Test
    public void testMatriculaInvalidaPorCaracteresIncorrectos() {
        assertFalse(Vehiculo.matriculaValida("ABCD EFG"));
    }

    @Test
    public void testMatriculaInvalidaBordesYNulos() {
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

    @Test
    public void testcontrasenaValidaCorrectas() {
        assertTrue(Trabajador.contrasenaValida("Abcdefg1!"));
        assertTrue(Trabajador.contrasenaValida("P@ssw0rdDificil"));
        assertTrue(Trabajador.contrasenaValida("L3trasYSimbolo#"));
    }

    @Test
    public void testContrasenaInvalidaFaltaMayuscula() {
        assertFalse(Trabajador.contrasenaValida("sinmayusculas1!"));
    }

    @Test
    public void testContrasenaInvalidaFaltaMinuscula() {
        assertFalse(Trabajador.contrasenaValida("SINMINUSCULAS1!"));
    }

    @Test
    public void testContrasenaInvalidaFaltaSimbolo() {
        assertFalse(Trabajador.contrasenaValida("SinSimbolos1234"));
    }

    @Test
    public void testContrasenaInvalidaFaltaNumero() {
        assertFalse(Trabajador.contrasenaValida("FaltanNumeros$$"));
    }

    @Test
    public void testContrasenaInvalidaBordesYNulos() {
        assertFalse(Trabajador.contrasenaValida(""));
        assertFalse(Trabajador.contrasenaValida(null));
        
        assertFalse(Trabajador.contrasenaValida("aB!4567"));
        assertTrue(Trabajador.contrasenaValida("aB!45678"));
        
        String limiteMaximoValido = "aB!1" + "a".repeat(86);
        assertTrue(Trabajador.contrasenaValida(limiteMaximoValido));

        String limiteMaximoInvalido = "aB!1" + "a".repeat(87);
        assertFalse(Trabajador.contrasenaValida(limiteMaximoInvalido));
    }
}
package com.dam.test;

import static org.junit.Assert.*;
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
}
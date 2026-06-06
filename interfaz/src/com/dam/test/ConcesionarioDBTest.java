package com.dam.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dam.model.data.Cliente;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Trabajador;
import com.dam.model.data.Vehiculo;
import com.dam.model.data.Venta;
import com.dam.model.db.AccesoBDTest;
import com.dam.model.db.ClienteDAO;
import com.dam.model.db.ModeloVehiculoDAO;
import com.dam.model.db.SetupBD;
import com.dam.model.db.TrabajadorDAO;
import com.dam.model.db.VehiculoDAO;
import com.dam.model.db.VentaDAO;

public class ConcesionarioDBTest {

    private static AccesoBDTest bd;
    private static ClienteDAO clienteDAO;
    private static ModeloVehiculoDAO modeloDAO;
    private static TrabajadorDAO trabajadorDAO;
    private static VehiculoDAO vehiculoDAO;
    private static VentaDAO ventaDAO;
    private static SetupBD setup;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        bd = new AccesoBDTest();
        clienteDAO = new ClienteDAO(bd);
        modeloDAO = new ModeloVehiculoDAO(bd);
        trabajadorDAO = new TrabajadorDAO(bd);
        vehiculoDAO = new VehiculoDAO(bd);
        ventaDAO = new VentaDAO(bd);
        setup = new SetupBD(bd);
        setup.crearEsquema();
    }

    @Before
    public void setUp() throws Exception {
        setup.crearEsquema();
    }

    // ModeloVehiculoDAO

    @Test
    public void testModeloSelectTodos() {
        assertEquals(15, modeloDAO.selectTodos().size());
    }

    @Test
    public void testModeloSelectMarcas() {
        ArrayList<String> marcas = modeloDAO.selectMarcas();
        assertTrue(marcas.contains("Mercedes-Benz"));
        assertTrue(marcas.contains("Toyota"));
        assertTrue(marcas.contains("Volkswagen"));
    }

    @Test
    public void testModeloSelectPorMarca() {
        assertEquals(4, modeloDAO.selectModeloPorMarca("Mercedes-Benz").size());
    }

    @Test
    public void testModeloInsert() {
        ModeloVehiculo m = new ModeloVehiculo(-1, "Corolla", 5, 4, "Berlina",
                "Gasolina", "Delantera", "Toyota", "Manual");
        assertEquals(1, modeloDAO.insert(m));
        assertEquals(16, modeloDAO.selectTodos().size());
    }

    @Test
    public void testModeloUpdate() {
        ModeloVehiculo m = modeloDAO.selectModeloPorMarca("Volkswagen").get(0);
        ModeloVehiculo actualizado = new ModeloVehiculo(m.getIdModelo(), "Golf R", 5, 4,
                "Compacto", "Gasolina", "4x4", "Volkswagen", "Automático");
        assertEquals(1, modeloDAO.update(actualizado));
        assertEquals("Golf R", modeloDAO.selectModeloPorMarca("Volkswagen").get(0).getNombreModelo());
    }

    @Test
    public void testModeloDelete() {
        ModeloVehiculo m = new ModeloVehiculo(-1, "NuevoModelo", 5, 4, "Berlina",
                "Gasolina", "Delantera", "Toyota", "Manual");
        modeloDAO.insert(m);
        assertEquals(1, modeloDAO.delete("NuevoModelo"));
        assertEquals(15, modeloDAO.selectTodos().size());
    }

    @Test
    public void testModeloDeleteConVehiculoAsociado() {
        assertEquals(-1, modeloDAO.delete("Golf"));
    }

    // VehiculoDAO

    @Test
    public void testVehiculoSelectTodos() {
        assertEquals(15, vehiculoDAO.selectTodos().size());
    }

    @Test
    public void testVehiculoInsert() {
        ModeloVehiculo m = modeloDAO.selectModeloPorMarca("SEAT").get(0);
        Vehiculo v = new Vehiculo(-1, m, 13000, "0000 TST", "Azul", 2019, 50000, 95, 1498, 1075);
        assertEquals(1, vehiculoDAO.insert(v));
        assertEquals(16, vehiculoDAO.selectTodos().size());
    }

    @Test
    public void testVehiculoInsertMatriculaDuplicada() {
        ModeloVehiculo m = modeloDAO.selectModeloPorMarca("SEAT").get(0);
        Vehiculo v = new Vehiculo(-1, m, 13000, "9901 IBZ", "Azul", 2019, 50000, 95, 1498, 1075);
        assertEquals(-1, vehiculoDAO.insert(v));
    }

    @Test
    public void testVehiculoUpdate() {
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        Vehiculo actualizado = new Vehiculo(v.getIdVehiculo(), v.getModelo(), 99999,
                v.getMatricula(), v.getColor(), v.getYear(), 999,
                v.getPotenciaCV(), v.getCilindrada(), v.getPesoKG());
        assertEquals(1, vehiculoDAO.update(actualizado));
        Vehiculo leido = vehiculoDAO.selectTodos().get(0);
        assertEquals(99999, leido.getPrecio());
        assertEquals(999, leido.getKilometraje());
    }

    @Test
    public void testVehiculoDelete() {
        ModeloVehiculo m = modeloDAO.selectModeloPorMarca("SEAT").get(0);
        vehiculoDAO.insert(new Vehiculo(-1, m, 13000, "0000 TST", "Azul", 2019, 50000, 95, 1498, 1075));
        Vehiculo insertado = vehiculoDAO.selectTodos().stream()
                .filter(x -> x.getMatricula().equals("0000 TST"))
                .findFirst().get();
        assertEquals(1, vehiculoDAO.delete(insertado.getIdVehiculo()));
        assertEquals(15, vehiculoDAO.selectTodos().size());
    }

    @Test
    public void testVehiculoDeleteConVentaAsociada() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Carlos Gómez Pérez", "admin1234");
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        ventaDAO.insert(new Venta(-1, c, t, v, ""));
        assertEquals(-1, vehiculoDAO.delete(v.getIdVehiculo()));
    }

    // TrabajadorDAO

    @Test
    public void testTrabajadorSelectAll() {
        assertEquals(2, trabajadorDAO.selectAllTrabajadores().size());
    }

    @Test
    public void testTrabajadorGetPorCredencialesCorrectas() {
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Carlos Gómez Pérez", "admin1234");
        assertNotNull(t);
        assertEquals(1, t.getEsAdmin());
    }

    @Test
    public void testTrabajadorGetPorCredencialesIncorrectas() {
        assertNull(trabajadorDAO.getTrabajadorPorCredenciales("Carlos Gómez Pérez", "wrongpass"));
    }

    @Test
    public void testTrabajadorInsert() {
        assertEquals(1, trabajadorDAO.insert(new Trabajador("Nuevo Empleado", "pass999", 0)));
        assertEquals(3, trabajadorDAO.selectAllTrabajadores().size());
    }

    @Test
    public void testTrabajadorDelete() {
        assertEquals(1, trabajadorDAO.delete("Laura Martínez Soler"));
        assertEquals(1, trabajadorDAO.selectAllTrabajadores().size());
    }

    @Test
    public void testTrabajadorDeleteConVentaAsociada() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Carlos Gómez Pérez", "admin1234");
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        ventaDAO.insert(new Venta(-1, c, t, v, ""));
        assertEquals(-1, trabajadorDAO.delete("Carlos Gómez Pérez"));
    }

    // ClienteDAO

    @Test
    public void testClienteSelectPorNombreExistente() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        assertNotNull(c);
        assertEquals("Tarjeta de crédito", c.getMetodoPago());
        assertTrue(c.getIdCliente() > 0);
    }

    @Test
    public void testClienteSelectPorNombreInexistente() {
        assertNull(clienteDAO.selectPorNombre("Nadie"));
    }

    @Test
    public void testClienteInsert() {
        assertEquals(1, clienteDAO.insert(new Cliente(-1, "Nuevo Cliente", "efectivo")));
        assertNotNull(clienteDAO.selectPorNombre("Nuevo Cliente"));
    }

    // VentaDAO

    @Test
    public void testVentaInsert() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Carlos Gómez Pérez", "admin1234");
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        assertEquals(1, ventaDAO.insert(new Venta(-1, c, t, v, "")));
    }
}
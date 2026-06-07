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

    // -------------------------------------------------------------------------
    // ModeloVehiculoDAO
    // -------------------------------------------------------------------------

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
    public void testModeloSelectMarcasNoDuplicadas() {
        ArrayList<String> marcas = modeloDAO.selectMarcas();
        long distintas = marcas.stream().distinct().count();
        assertEquals(distintas, marcas.size());
    }

    @Test
    public void testModeloSelectPorMarca() {
        // SetupBD inserta 4 modelos de Mercedes-Benz
        assertEquals(4, modeloDAO.selectModeloPorMarca("Mercedes-Benz").size());
    }

    @Test
    public void testModeloSelectPorMarcaInexistente() {
        assertEquals(0, modeloDAO.selectModeloPorMarca("MarcaQueNoExiste").size());
    }

    @Test
    public void testModeloInsert() {
        ModeloVehiculo m = new ModeloVehiculo(-1, "Corolla", 5, 4, "Berlina",
                "Gasolina", "Delantera", "Toyota", "Manual");
        assertEquals(1, modeloDAO.insert(m));
        assertEquals(16, modeloDAO.selectTodos().size());
    }

    @Test
    public void testModeloInsertYRecuperar() {
        ModeloVehiculo m = new ModeloVehiculo(-1, "Panda", 5, 4, "Compacto",
                "Gasolina", "Delantera", "Fiat", "Manual");
        modeloDAO.insert(m);
        ArrayList<ModeloVehiculo> fiat = modeloDAO.selectModeloPorMarca("Fiat");
        assertEquals(1, fiat.size());
        assertEquals("Panda", fiat.get(0).getNombreModelo());
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
    public void testModeloUpdateMantieneConteo() {
        ModeloVehiculo m = modeloDAO.selectModeloPorMarca("Honda").get(0);
        ModeloVehiculo actualizado = new ModeloVehiculo(m.getIdModelo(), "Civic Type R", 5, 4,
                "Deportivo", "Gasolina", "Delantera", "Honda", "Manual");
        modeloDAO.update(actualizado);
        assertEquals(15, modeloDAO.selectTodos().size());
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
    public void testModeloDeleteInexistente() {
        assertEquals(0, modeloDAO.delete("ModeloQueNoExiste"));
    }

    @Test
    public void testModeloDeleteConVehiculoAsociado() {
        // "Golf" tiene un vehículo asociado en el setup, debe fallar por FK
        assertEquals(-1, modeloDAO.delete("Golf"));
    }

    // -------------------------------------------------------------------------
    // VehiculoDAO
    // -------------------------------------------------------------------------

    @Test
    public void testVehiculoSelectTodos() {
        assertEquals(15, vehiculoDAO.selectTodos().size());
    }

    @Test
    public void testVehiculoSelectTodosConModelo() {
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        assertNotNull(v.getModelo());
        assertNotNull(v.getModelo().getNombreModelo());
    }

    @Test
    public void testVehiculoSelectDisponibles() {
        // Sin ventas, todos los vehículos están disponibles
        assertEquals(15, vehiculoDAO.selectDisponibles().size());
    }

    @Test
    public void testVehiculoSelectDisponiblesTrasSale() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorNombre("Daniel");
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        ventaDAO.insert(new Venta(-1, c, t, v, ""));
        assertEquals(14, vehiculoDAO.selectDisponibles().size());
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
        // "9901 IBZ" ya existe en el setup
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
    public void testVehiculoUpdateMantieneConteo() {
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        Vehiculo actualizado = new Vehiculo(v.getIdVehiculo(), v.getModelo(), 1,
                v.getMatricula(), v.getColor(), v.getYear(), 1,
                v.getPotenciaCV(), v.getCilindrada(), v.getPesoKG());
        vehiculoDAO.update(actualizado);
        assertEquals(15, vehiculoDAO.selectTodos().size());
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
    public void testVehiculoDeleteInexistente() {
        assertEquals(0, vehiculoDAO.delete(Integer.MAX_VALUE));
    }

    @Test
    public void testVehiculoDeleteConVentaAsociada() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorNombre("Daniel");
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        ventaDAO.insert(new Venta(-1, c, t, v, ""));
        assertEquals(-1, vehiculoDAO.delete(v.getIdVehiculo()));
    }

    // -------------------------------------------------------------------------
    // TrabajadorDAO
    // -------------------------------------------------------------------------

    @Test
    public void testTrabajadorSelectAll() {
        // SetupBD inserta 3 trabajadores: Daniel, Luis, Empleado
        assertEquals(3, trabajadorDAO.selectAllTrabajadores().size());
    }

    @Test
    public void testTrabajadorGetPorNombre() {
        Trabajador t = trabajadorDAO.getTrabajadorPorNombre("Daniel");
        assertNotNull(t);
        assertEquals("Daniel", t.getNombreTrabajador());
    }

    @Test
    public void testTrabajadorGetPorNombreInexistente() {
        assertNull(trabajadorDAO.getTrabajadorPorNombre("NadieLlama"));
    }

    @Test
    public void testTrabajadorGetPorId() {
        Trabajador porNombre = trabajadorDAO.getTrabajadorPorNombre("Daniel");
        Trabajador porId = trabajadorDAO.getTrabajadorPorId(porNombre.getIdTrabajador());
        assertNotNull(porId);
        assertEquals(porNombre.getIdTrabajador(), porId.getIdTrabajador());
        assertEquals("Daniel", porId.getNombreTrabajador());
    }

    @Test
    public void testTrabajadorGetPorIdInexistente() {
        assertNull(trabajadorDAO.getTrabajadorPorId(Integer.MAX_VALUE));
    }

    @Test
    public void testTrabajadorGetPorCredencialesCorrectas() {
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Daniel", "admin");
        assertNotNull(t);
        assertEquals(1, t.getEsAdmin());
    }

    @Test
    public void testTrabajadorGetPorCredencialesEmpleado() {
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Empleado", "empleado");
        assertNotNull(t);
        assertEquals(0, t.getEsAdmin());
    }

    @Test
    public void testTrabajadorGetPorCredencialesIncorrectas() {
        assertNull(trabajadorDAO.getTrabajadorPorCredenciales("Daniel", "wrongpass"));
    }

    @Test
    public void testTrabajadorGetPorCredencialesNombreIncorrecto() {
        assertNull(trabajadorDAO.getTrabajadorPorCredenciales("Nadie", "admin"));
    }

    @Test
    public void testTrabajadorInsert() {
        assertEquals(1, trabajadorDAO.insert(new Trabajador("Nuevo Empleado", "pass999", 0)));
        assertEquals(4, trabajadorDAO.selectAllTrabajadores().size());
    }

    @Test
    public void testTrabajadorInsertYRecuperar() {
        trabajadorDAO.insert(new Trabajador("Maria Lopez", "pass123", 0));
        Trabajador t = trabajadorDAO.getTrabajadorPorNombre("Maria Lopez");
        assertNotNull(t);
        assertEquals(0, t.getEsAdmin());
    }

    @Test
    public void testTrabajadorDelete() {
        // "Empleado" no tiene ventas asociadas, se puede borrar
        Trabajador t = trabajadorDAO.getTrabajadorPorNombre("Empleado");
        assertEquals(1, trabajadorDAO.delete(t.getIdTrabajador()));
        assertEquals(2, trabajadorDAO.selectAllTrabajadores().size());
    }

    @Test
    public void testTrabajadorDeleteInexistente() {
        assertEquals(0, trabajadorDAO.delete(Integer.MAX_VALUE));
    }

    @Test
    public void testTrabajadorDeleteConVentaAsociada() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Daniel", "admin");
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        ventaDAO.insert(new Venta(-1, c, t, v, ""));
        assertEquals(-1, trabajadorDAO.delete(t.getIdTrabajador()));
    }

    // -------------------------------------------------------------------------
    // ClienteDAO
    // -------------------------------------------------------------------------

    @Test
    public void testClienteSelectAll() {
        // SetupBD inserta 2 clientes
        assertEquals(2, clienteDAO.selectAllClientes().size());
    }

    @Test
    public void testClienteSelectAllOrdenado() {
        ArrayList<Cliente> clientes = clienteDAO.selectAllClientes();
        assertTrue(clientes.get(0).getIdCliente() < clientes.get(1).getIdCliente());
    }

    @Test
    public void testClienteSelectPorNombreExistente() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        assertNotNull(c);
        assertEquals("Tarjeta de crédito", c.getMetodoPago());
        assertTrue(c.getIdCliente() > 0);
    }

    @Test
    public void testClienteSelectPorNombreSegundo() {
        Cliente c = clienteDAO.selectPorNombre("Pedro Infante Rivera");
        assertNotNull(c);
        assertEquals("Transferencia bancaria", c.getMetodoPago());
    }

    @Test
    public void testClienteSelectPorNombreInexistente() {
        assertNull(clienteDAO.selectPorNombre("Nadie"));
    }

    @Test
    public void testClienteInsert() {
        assertEquals(1, clienteDAO.insert(new Cliente(-1, "Nuevo Cliente", "Efectivo")));
        assertNotNull(clienteDAO.selectPorNombre("Nuevo Cliente"));
    }

    @Test
    public void testClienteInsertIncrementaConteo() {
        clienteDAO.insert(new Cliente(-1, "Otro Cliente", "Efectivo"));
        assertEquals(3, clienteDAO.selectAllClientes().size());
    }

    @Test
    public void testClienteInsertMetodoPago() {
        clienteDAO.insert(new Cliente(-1, "Cliente Bizum", "Bizum"));
        Cliente c = clienteDAO.selectPorNombre("Cliente Bizum");
        assertNotNull(c);
        assertEquals("Bizum", c.getMetodoPago());
    }

    // -------------------------------------------------------------------------
    // VentaDAO
    // -------------------------------------------------------------------------

    @Test
    public void testVentaInsert() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Daniel", "admin");
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        assertEquals(1, ventaDAO.insert(new Venta(-1, c, t, v, "")));
    }

    @Test
    public void testVentaInsertYSelectAll() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Daniel", "admin");
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        ventaDAO.insert(new Venta(-1, c, t, v, ""));
        assertEquals(1, ventaDAO.selectVentas().size());
    }

    @Test
    public void testVentaSelectVentasVacio() {
        assertEquals(0, ventaDAO.selectVentas().size());
    }

    @Test
    public void testVentaSelectMultiples() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Daniel", "admin");
        ArrayList<Vehiculo> todos = vehiculoDAO.selectTodos();
        ventaDAO.insert(new Venta(-1, c, t, todos.get(0), ""));
        ventaDAO.insert(new Venta(-1, c, t, todos.get(1), ""));
        assertEquals(2, ventaDAO.selectVentas().size());
    }

    @Test
    public void testVentaSelectContieneCliente() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Daniel", "admin");
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        ventaDAO.insert(new Venta(-1, c, t, v, ""));
        Venta venta = ventaDAO.selectVentas().get(0);
        assertNotNull(venta.getCliente());
        assertEquals("Ana Ruiz López", venta.getCliente().getNombreApellidos());
    }

    @Test
    public void testVentaSelectContieneTrabajador() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Daniel", "admin");
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        ventaDAO.insert(new Venta(-1, c, t, v, ""));
        Venta venta = ventaDAO.selectVentas().get(0);
        assertNotNull(venta.getTrabajador());
        assertEquals("Daniel", venta.getTrabajador().getNombreTrabajador());
    }

    @Test
    public void testVentaSelectContieneVehiculo() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Daniel", "admin");
        Vehiculo v = vehiculoDAO.selectTodos().get(0);
        ventaDAO.insert(new Venta(-1, c, t, v, ""));
        Venta venta = ventaDAO.selectVentas().get(0);
        assertNotNull(venta.getVehiculo());
        assertNotNull(venta.getVehiculo().getModelo());
    }

    @Test
    public void testVentaVehiculoNoDisponibleTrasVenta() {
        Cliente c = clienteDAO.selectPorNombre("Ana Ruiz López");
        Trabajador t = trabajadorDAO.getTrabajadorPorCredenciales("Daniel", "admin");
        Vehiculo v = vehiculoDAO.selectDisponibles().get(0);
        ventaDAO.insert(new Venta(-1, c, t, v, ""));
        boolean sigueDisponible = vehiculoDAO.selectDisponibles().stream()
                .anyMatch(x -> x.getIdVehiculo() == v.getIdVehiculo());
        assertFalse(sigueDisponible);
    }
}
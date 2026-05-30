package com.dam.control;

import com.dam.model.data.Cliente;
import com.dam.model.data.Login;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Trabajador;
import com.dam.model.data.Vehiculo;
import com.dam.model.data.Venta;
import com.dam.view.Avisos;
import com.dam.view.VPrincipal;
import com.dam.view.PNuevoVehiculo;
import com.dam.view.PVerCatalogo;
import com.dam.view.PNuevoModelo;
import com.dam.view.PInformacionVehiculo;
import com.dam.view.PLogin;
import com.dam.view.PModificarVehiculo;
import com.dam.view.PRegistrarTrabajador;
import com.dam.view.PVehiculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.dam.model.db.ClienteDAO;
import com.dam.model.db.ModeloVehiculoDAO;
import com.dam.model.db.TrabajadorDAO;
import com.dam.model.db.VehiculoDAO;
import com.dam.model.db.VentaDAO;

import javax.swing.JButton;
import javax.swing.JMenuItem;

public class ConcesionarioControlador implements ActionListener {
    private VPrincipal v;
    private PNuevoVehiculo pNuevoVehiculo;
    private PVerCatalogo pVerCatalogo;
    private PNuevoModelo pNuevoModelo;
    private PModificarVehiculo pModificarVehiculo;
    private PLogin pLogin;
    private PRegistrarTrabajador pRegistrarTrabajador;
    private PInformacionVehiculo pInformacionVehiculo;

    private ClienteDAO clienteDAO;
    private ModeloVehiculoDAO modeloVehiculoDAO;
    private TrabajadorDAO trabajadorDAO;
    private VehiculoDAO vehiculoDAO;
    private VentaDAO ventaDAO;

    private boolean modoClaro = true;
    private boolean sesionIniciada = true; // TODO: false en producción
    private boolean admin = true;          // TODO: false en producción

    public ConcesionarioControlador(
            VPrincipal v,
            PNuevoVehiculo pNuevoVehiculo,
            PVerCatalogo pVerCatalogo,
            PNuevoModelo pNuevoModelo,
            PModificarVehiculo pModificarVehiculo,
            PLogin pLogin,
            PRegistrarTrabajador pRegistrarTrabajador,
            PInformacionVehiculo pInformacionVehiculo,
            ClienteDAO clienteDAO,
            ModeloVehiculoDAO modeloVehiculoDAO,
            TrabajadorDAO trabajadorDAO,
            VehiculoDAO vehiculoDAO,
            VentaDAO ventaDAO) {
        this.v = v;
        this.pNuevoVehiculo = pNuevoVehiculo;
        this.pVerCatalogo = pVerCatalogo;
        this.pNuevoModelo = pNuevoModelo;
        this.pModificarVehiculo = pModificarVehiculo;
        this.pLogin = pLogin;
        this.pRegistrarTrabajador = pRegistrarTrabajador;
        this.pInformacionVehiculo = pInformacionVehiculo;
        this.clienteDAO = clienteDAO;
        this.modeloVehiculoDAO = modeloVehiculoDAO;
        this.trabajadorDAO = trabajadorDAO;
        this.vehiculoDAO = vehiculoDAO;
        this.ventaDAO = ventaDAO;
        actualizarModoClaroOscuro(modoClaro);
        v.actualizarMenu(sesionIniciada, admin);
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        if (e.getSource() instanceof JMenuItem) {
            controlMenus(e);
        } else if (e.getSource() instanceof JButton) {
            controlBotones(e);
        } else {
            System.out.println("Comando no reconocido: " + comando);
        }
    }

    private void controlMenus(ActionEvent e) {
        switch (e.getActionCommand()) {
            case VPrincipal.NUEVO_VEHICULO_MENU:
                pNuevoVehiculo.actualizarMarcas(modeloVehiculoDAO.selectMarcas());
                pNuevoVehiculo.actualizarVehiculos(vehiculoDAO.selectTodos());
                v.cargarPanel(pNuevoVehiculo);
                actualizarModoClaroOscuro(modoClaro);
                break;
            case VPrincipal.VER_CATALOGO_MENU:
                cargarPanelCatalogo();
                break;
            case VPrincipal.NUEVO_MODELO_MENU:
                v.cargarPanel(pNuevoModelo);
                actualizarModoClaroOscuro(modoClaro);
                break;
            case VPrincipal.REGISTRAR_TRABAJADOR_MENU:
                if (sesionIniciada && admin) {
                    v.cargarPanel(pRegistrarTrabajador);
                    actualizarModoClaroOscuro(modoClaro);
                }
                break;
            case VPrincipal.LOGIN_MENU:
                v.cargarPanel(pLogin);
                actualizarModoClaroOscuro(modoClaro);
                break;
            case VPrincipal.MODO_CLARO_OSCURO_MENU:
                modoClaro = !modoClaro;
                actualizarModoClaroOscuro(modoClaro);
                break;
            case VPrincipal.NUEVO_EMPLEADO:
                v.cargarPanel(pRegistrarTrabajador);
                actualizarModoClaroOscuro(modoClaro);
                consultarTrabajadores();
                break;
            default:
                System.out.println("Menu no reconocido: " + e.getActionCommand());
                break;
        }
    }

    private void controlBotones(ActionEvent e) {
        Vehiculo vehiculo;
        switch (e.getActionCommand()) {
            case PVehiculo.MAS_INFO_BTN:
                vehiculo = ((PVehiculo) ((JButton) e.getSource()).getParent()).getVehiculoActual();
                pInformacionVehiculo.actualizarTrabajadores(trabajadorDAO.selectAllTrabajadores());
                pInformacionVehiculo.mostrarInfoVehiculo(vehiculo);
                v.cargarPanel(pInformacionVehiculo);
                actualizarModoClaroOscuro(modoClaro);
                break;
            case PNuevoVehiculo.GUARDAR_VEHICULO_BTN:
                guardarVehiculo();
                break;
            case PNuevoModelo.GUARDAR_MODELO_BTN:
                guardarModelo();
                break;
            case PLogin.LOGIN_BTN:
                login();
                break;
            case PInformacionVehiculo.COMPRAR_BTN:
                // Muestra u oculta el formulario de compra sin procesar aún
                pInformacionVehiculo.togglePanelCompra();
                break;
            case PInformacionVehiculo.REALIZAR_COMPRA_BTN:
                comprarVehiculo();
                break;
            case PNuevoVehiculo.BUSCAR_MARCA_BTN:
                buscarMarca();
                break;
            case PNuevoVehiculo.VER_COLOR_BTN:
                pNuevoVehiculo.actualizarColor();
                break;
            case PNuevoVehiculo.ELIMINAR_VEHICULO_BTN:
                eliminarVehiculo();
                break;
            case PNuevoVehiculo.MODIFICAR_VEHICULO_BTN:
                cargarPanelModificarVehiculo();
                break;
            case PModificarVehiculo.GUARDAR_MODIFICACION_BTN:
                modificarVehiculo();
                break;
            case PModificarVehiculo.VER_COLOR_MODIFICAR_BTN:
                pModificarVehiculo.actualizarColor();
                break;
            case PModificarVehiculo.BUSCAR_MARCA_MODIFICAR_BTN:
                buscarMarcaModificar();
                break;
            default:
                System.out.println("Boton no reconocido: " + e.getActionCommand());
                break;
        }
    }

    /** Inserta el cliente si no existe, luego registra la venta. */
    public void comprarVehiculo() {
        Vehiculo vehiculo = pInformacionVehiculo.getVehiculoActual();
        if (vehiculo == null) {
            Avisos.error(v, "No hay vehículo seleccionado.");
            return;
        }

        Trabajador trabajador = pInformacionVehiculo.getTrabajadorSeleccionado();
        if (trabajador == null) {
            Avisos.error(v, "Selecciona el trabajador que atiende la venta.");
            return;
        }

        Cliente clienteFormulario = pInformacionVehiculo.getCliente();
        if (clienteFormulario.getNombreApellidos() == null || clienteFormulario.getNombreApellidos().isBlank()) {
            Avisos.error(v, "Indica el nombre del cliente.");
            return;
        }

        // Buscar cliente; si no existe, crearlo
        Cliente cliente = clienteDAO.selectPorNombre(clienteFormulario.getNombreApellidos());
        if (cliente == null) {
            clienteDAO.insert(clienteFormulario);
            cliente = clienteDAO.selectPorNombre(clienteFormulario.getNombreApellidos());
        }

        if (cliente == null) {
            Avisos.error(v, "No se pudo registrar al cliente.");
            return;
        }

        Venta venta = new Venta(-1, cliente, trabajador, vehiculo, "");
        int res = ventaDAO.insert(venta);
        if (res > 0) {
            Avisos.info(v, "Venta registrada correctamente.");
            System.out.println(venta);
        } else {
            Avisos.error(v, "Error al registrar la venta.");
        }
    }

    public void cargarPanelCatalogo() {
        pVerCatalogo.actualizarPanelesVehiculo(vehiculoDAO.selectTodos());
        v.cargarPanel(pVerCatalogo);
        actualizarModoClaroOscuro(modoClaro);
    }

    private void cargarPanelModificarVehiculo() {
        Vehiculo vehiculo = pNuevoVehiculo.getVehiculoSeleccionado();
        if (vehiculo == null) {
            Avisos.aviso(v, "Selecciona un vehículo de la lista.");
            return;
        }
        pModificarVehiculo.actualizarMarcas(modeloVehiculoDAO.selectMarcas());
        pModificarVehiculo.actualizarModelos(modeloVehiculoDAO.selectModeloPorMarca(vehiculo.getModelo().getMarca()));
        pModificarVehiculo.cargarVehiculo(vehiculo);
        v.cargarPanel(pModificarVehiculo);
        actualizarModoClaroOscuro(modoClaro);
    }

    private void modificarVehiculo() {
        Vehiculo vehiculo = pModificarVehiculo.getVehiculo();
        int res = vehiculoDAO.update(vehiculo);
        if (res > 0) {
            Avisos.info(v, "Vehículo modificado correctamente.");
        } else {
            Avisos.error(v, "Error al modificar el vehículo.");
        }
    }

    private void eliminarVehiculo() {
        Vehiculo vehiculo = pNuevoVehiculo.getVehiculoSeleccionado();
        if (vehiculo == null) {
            Avisos.aviso(v, "Selecciona un vehículo de la lista.");
            return;
        }
        if (!Avisos.confirmar(v, "¿Eliminar el vehículo " + vehiculo + "?")) return;
        int res = vehiculoDAO.delete(vehiculo.getIdVehiculo());
        if (res > 0) {
            pNuevoVehiculo.actualizarVehiculos(vehiculoDAO.selectTodos());
            Avisos.info(v, "Vehículo eliminado.");
        } else {
            Avisos.error(v, "Error al eliminar el vehículo.");
        }
    }

    private void actualizarModoClaroOscuro(boolean modoClaro) {
        try {
            if (modoClaro) {
                javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
            } else {
                javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
            }
            for (java.awt.Window window : java.awt.Window.getWindows()) {
                javax.swing.SwingUtilities.updateComponentTreeUI(window);
            }
        } catch (Exception e) {
            System.out.println("Fallo al inicializar FlatLaf");
        }
    }

    private void guardarVehiculo() {
        Vehiculo vehiculo = pNuevoVehiculo.getVehiculo();
        int res = vehiculoDAO.insert(vehiculo);
        if (res > 0) {
            pNuevoVehiculo.actualizarVehiculos(vehiculoDAO.selectTodos());
            Avisos.info(v, "Vehículo guardado correctamente.");
        } else {
            Avisos.error(v, "Error al guardar el vehículo.");
        }
    }

    private void guardarModelo() {
        ModeloVehiculo m = pNuevoModelo.getModeloVehiculo();
        int res = modeloVehiculoDAO.insert(m);
        if (res > 0) {
            Avisos.info(v, "Modelo guardado correctamente.");
        } else {
            Avisos.error(v, "Error al guardar el modelo.");
        }
        System.out.println(m);
    }

    private void login() {
        Login l = pLogin.getLogin();
        Trabajador trabajador = trabajadorDAO.getTrabajadorPorCredenciales(l.getUsuario(), l.getPasswd());
        if (trabajador != null) {
            sesionIniciada = true;
            admin = trabajador.getEsAdmin() == 1;
            Avisos.info(v, "Sesión iniciada como " + trabajador.getNombreApellidos() + ".");
        } else {
            sesionIniciada = false;
            admin = false;
            Avisos.error(v, "Usuario o contraseña incorrectos.");
        }
        v.actualizarMenu(sesionIniciada, admin);
    }

    private void buscarMarca() {
        String marca = pNuevoVehiculo.getMarca();
        ArrayList<ModeloVehiculo> modelos = modeloVehiculoDAO.selectModeloPorMarca(marca);
        pNuevoVehiculo.actualizarModelos(modelos);
    }

    private void buscarMarcaModificar() {
        String marca = pModificarVehiculo.getMarca();
        ArrayList<ModeloVehiculo> modelos = modeloVehiculoDAO.selectModeloPorMarca(marca);
        pModificarVehiculo.actualizarModelos(modelos);
    }

    private void consultarTrabajadores() {
        pRegistrarTrabajador.cargarTabla(trabajadorDAO.selectAllTrabajadores());
    }
}
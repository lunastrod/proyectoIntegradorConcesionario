package com.dam.control;

import com.dam.model.data.Cliente;
import com.dam.model.data.Login;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Trabajador;
import com.dam.model.data.Vehiculo;
import com.dam.model.data.Venta;
import com.dam.view.VPrincipal;
import com.dam.view.PNuevoVehiculo;
import com.dam.view.PRegistrarTrabajador;
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
import com.dam.model.db.LoginDAO;
import com.dam.model.db.ModeloVehiculoDAO;
import com.dam.model.db.TrabajadorDAO;
import com.dam.model.db.VehiculoDAO;
import com.dam.model.db.VentaDAO;

import javax.swing.JButton;
import javax.swing.JMenuItem;

public class ConcesionarioControlador implements ActionListener{
    private VPrincipal v;
    private PNuevoVehiculo pNuevoVehiculo;
    private PVerCatalogo pVerCatalogo;
    private PNuevoModelo pNuevoModelo;
    private PModificarVehiculo pModificarVehiculo;
    private PLogin pLogin;
    private PRegistrarTrabajador pRegistrarTrabajador;
    private LoginDAO loginDAO;
    private PInformacionVehiculo pInformacionVehiculo;
    private PRegistrarTrabajador pRegistrarTrabajador;
    private ModeloVehiculoDAO modeloVehiculoDAO;
    private TrabajadorDAO trabajadorDAO;
    private VehiculoDAO vehiculoDAO;
    private TrabajadorDAO trabajadorDAO;
    private VentaDAO ventaDAO;
    private boolean modoClaro=true;
    private boolean sesionIniciada=true;//TODO: temporal deberia estar a false en la version final
    private boolean admin=true;//TODO: temporal deberia estar a false en la version final

    public ConcesionarioControlador(VPrincipal v,PNuevoVehiculo pNuevoVehiculo,
    		PVerCatalogo pVerCatalogo,PNuevoModelo pNuevoModelo,PLogin pLogin,
    		PRegistrarTrabajador pRegistrarTrabajador,
    		PInformacionVehiculo pInformacionVehiculo,LoginDAO loginDAO,
    		ModeloVehiculoDAO modeloVehiculoDAO,VehiculoDAO vehiculoDAO,
    		TrabajadorDAO trabajadorDAO) {
        this.pNuevoVehiculo = pNuevoVehiculo;
        this.pVerCatalogo = pVerCatalogo;
        this.pNuevoModelo = pNuevoModelo;
        this.pModificarVehiculo = pModificarVehiculo;
        this.pLogin = pLogin;
        this.pRegistrarTrabajador = pRegistrarTrabajador;
        this.pInformacionVehiculo=pInformacionVehiculo;
        this.v = v;
        this.loginDAO=loginDAO;
        this.modeloVehiculoDAO=modeloVehiculoDAO;
        this.trabajadorDAO=trabajadorDAO;
        this.vehiculoDAO=vehiculoDAO;
        this.pRegistrarTrabajador = pRegistrarTrabajador;
        this.trabajadorDAO = trabajadorDAO;
        this.ventaDAO=ventaDAO;
        actualizarModoClaroOscuro(modoClaro);
        v.actualizarMenu(sesionIniciada,admin);
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        if(e.getSource() instanceof JMenuItem) {
            controlMenus(e);
        }
        else if(e.getSource() instanceof JButton) {
            controlBotones(e);
        }
        else {
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
                if(sesionIniciada && admin){
                    v.cargarPanel(pRegistrarTrabajador);
                    actualizarModoClaroOscuro(modoClaro);
                }
                break;
            case VPrincipal.LOGIN_MENU:
                v.cargarPanel(pLogin);
                actualizarModoClaroOscuro(modoClaro);
                break;
            case VPrincipal.MODO_CLARO_OSCURO_MENU:
                modoClaro=!modoClaro;
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
      private void consultarTrabajadores() {
		ArrayList<Trabajador> listaTrabajadores = new ArrayList<Trabajador>();
		
		listaTrabajadores = trabajadorDAO.selectAllTrabajadores();
		
		pRegistrarTrabajador.cargarTabla(listaTrabajadores);
		
		
	}

    private void controlBotones(ActionEvent e) {
        Vehiculo vehiculo;
        switch (e.getActionCommand()) {
            case PVehiculo.MAS_INFO_BTN:
                v.cargarPanel(pInformacionVehiculo);
                vehiculo = ((PVehiculo) ((JButton) e.getSource()).getParent()).getVehiculoActual();
                pInformacionVehiculo.mostrarInfoVehiculo(vehiculo);
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
                comprarVehiculo();
                break;
            case PNuevoVehiculo.BUSCAR_MARCA_BTN:
                buscarMarca();
                break;
            case PNuevoVehiculo.VER_COLOR_BTN:
                pNuevoVehiculo.actualizarColor();
                break;
            case PNuevoVehiculo.ELIMINAR_VEHICULO_BTN:
                //TODO: eliminar vehiculo
                break;
            case PNuevoVehiculo.MODIFICAR_VEHICULO_BTN:
                cargarPanelModificarVehiculo();
                break;
            default:
                System.out.println("Boton no reconocido: " + e.getActionCommand());
                break;
        }
    }

    public void comprarVehiculo() {
        Vehiculo v=pInformacionVehiculo.getVehiculoActual();
        String nombre= pInformacionVehiculo.getNombreTrabajador();
        Trabajador t=trabajadorDAO.getTrabajadorPorNombre(nombre);
        Cliente clienteFormulario = pInformacionVehiculo.getCliente();
        Cliente c=clienteDAO.getClientePorApellidos(clienteFormulario.getApellidos(),clienteFormulario.getNombre());
        Venta venta=new Venta(-1,cliente,t,v,"");
        ventaDAO.insert(venta);
        System.out.println(venta);
    }

    public void cargarPanelCatalogo() {
        pVerCatalogo.actualizarPanelesVehiculo(vehiculoDAO.selectTodos());
        v.cargarPanel(pVerCatalogo);
        actualizarModoClaroOscuro(modoClaro);
    }

    private void cargarPanelModificarVehiculo() {
        Vehiculo vehiculo=pNuevoVehiculo.getVehiculoSeleccionado();
        if(vehiculo==null){
            System.out.println("No hay vehiculo seleccionado");
            return;
        }

        pModificarVehiculo.actualizarMarcas(modeloVehiculoDAO.selectMarcas());
        pModificarVehiculo.actualizarModelos(modeloVehiculoDAO.selectModeloPorMarca(vehiculo.getModelo().getMarca()));
        pModificarVehiculo.cargarVehiculo(vehiculo);
        v.cargarPanel(pModificarVehiculo);
        actualizarModoClaroOscuro(modoClaro);
    }

    private void actualizarModoClaroOscuro(boolean modoClaro) {
        try{
            if (modoClaro) {
                javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
            } else {
                javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
            }
    
            for (java.awt.Window window : java.awt.Window.getWindows()) {
                javax.swing.SwingUtilities.updateComponentTreeUI(window);
            }
        }
        catch(Exception e){
            System.out.println("Fallo al inicializar FlatLaf");
        }
    }

    private void guardarVehiculo() {
        Vehiculo v=pNuevoVehiculo.getVehiculo();
        
        vehiculoDAO.insert(v);
        System.out.println(v);
    }

    private void guardarModelo() {
        ModeloVehiculo m=pNuevoModelo.getModeloVehiculo();
        System.out.println(modeloVehiculoDAO.insert(m));
        System.out.println(m);
    }

    private void login() {
        Login l=pLogin.getLogin();
        boolean loginCorrecto=loginDAO.iniciarSesion(l);
        if(loginCorrecto){
            System.out.println("Login correcto");
            sesionIniciada=true;
            v.actualizarMenu(sesionIniciada,admin);
        }else{
            System.out.println("Login incorrecto");
            sesionIniciada=false;
            v.actualizarMenu(sesionIniciada,admin);
        }
        System.out.println(l);
    }

    private void crearUsuario(Login l) {
        // TODO: Implementar lógica
        //Login l=pLogin.getLogin();
        loginDAO.crearUsuario(l);
        System.out.println(l);
    }

    private void buscarMarca() {
        String marca=pNuevoVehiculo.getMarca();
        ArrayList<ModeloVehiculo> modelos=modeloVehiculoDAO.selectModeloPorMarca(marca);
        pNuevoVehiculo.actualizarModelos(modelos);
    }
}

package com.dam.control;

import com.dam.model.data.Login;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Trabajador;
import com.dam.model.data.Vehiculo;
import com.dam.view.VPrincipal;
import com.dam.view.PNuevoVehiculo;
import com.dam.view.PRegistrarTrabajador;
import com.dam.view.PVerCatalogo;
import com.dam.view.PNuevoModelo;
import com.dam.view.PInformacionVehiculo;
import com.dam.view.PLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import com.dam.model.db.LoginDAO;
import com.dam.model.db.ModeloVehiculoDAO;
import com.dam.model.db.TrabajadorDAO;
import com.dam.model.db.VehiculoDAO;

import javax.swing.JButton;
import javax.swing.JMenuItem;

public class ConcesionarioControlador implements ActionListener{
    private VPrincipal v;
    private PNuevoVehiculo pNuevoVehiculo;
    private PVerCatalogo pVerCatalogo;
    private PNuevoModelo pNuevoModelo;
    private PLogin pLogin;
    private LoginDAO loginDAO;
    private PInformacionVehiculo pInformacionVehiculo;
    private PRegistrarTrabajador pRegistrarTrabajador;
    private ModeloVehiculoDAO modeloVehiculoDAO;
    private VehiculoDAO vehiculoDAO;
    private TrabajadorDAO trabajadorDAO;
    private boolean modoClaro=true;
    private boolean sesionIniciada=true;//TODO: temporal deberia estar a false en la version final

    public ConcesionarioControlador(VPrincipal v,PNuevoVehiculo pNuevoVehiculo,
    		PVerCatalogo pVerCatalogo,PNuevoModelo pNuevoModelo,PLogin pLogin,
    		PRegistrarTrabajador pRegistrarTrabajador,
    		PInformacionVehiculo pInformacionVehiculo,LoginDAO loginDAO,
    		ModeloVehiculoDAO modeloVehiculoDAO,VehiculoDAO vehiculoDAO,
    		TrabajadorDAO trabajadorDAO) {
        this.pNuevoVehiculo = pNuevoVehiculo;
        this.pVerCatalogo = pVerCatalogo;
        this.pNuevoModelo = pNuevoModelo;
        this.pLogin = pLogin;
        this.pInformacionVehiculo=pInformacionVehiculo;
        this.v = v;
        this.loginDAO=loginDAO;
        this.modeloVehiculoDAO=modeloVehiculoDAO;
        this.vehiculoDAO=vehiculoDAO;
        this.pRegistrarTrabajador = pRegistrarTrabajador;
        this.trabajadorDAO = trabajadorDAO;
        actualizarModoClaroOscuro(modoClaro);
        v.actualizarMenu(sesionIniciada);
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
        if(e.getActionCommand().startsWith("masinfo")){
            System.out.println("boton masinfo");
            v.cargarPanel(pInformacionVehiculo);
            int id=Integer.parseInt(e.getActionCommand().substring("masinfo".length()));
            System.out.println("id: "+id);
            Vehiculo vehiculo=vehiculoDAO.selectVehiculoPorId(id).get(0);
            pInformacionVehiculo.mostrarInfoVehiculo(vehiculo);
        }
        else{
            switch (e.getActionCommand()) {
                case PNuevoVehiculo.GUARDAR_VEHICULO_BTN:
                    guardarVehiculo();
                    break;
                case PNuevoModelo.GUARDAR_MODELO_BTN:
                    guardarModelo();
                    break;
                case PLogin.LOGIN_BTN:
                    login();
                    break;
                case PNuevoVehiculo.BUSCAR_MARCA_BTN:
                    buscarMarca();
                    break;
                case PNuevoVehiculo.VER_COLOR_BTN:
                    pNuevoVehiculo.actualizarColor();
                    break;
                default:
                    System.out.println("Boton no reconocido: " + e.getActionCommand());
                    break;
            }
        }
    }

    public void cargarPanelCatalogo() {
        pVerCatalogo.actualizarPanelesVehiculo(vehiculoDAO.selectTodos());
        v.cargarPanel(pVerCatalogo);
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
            v.actualizarMenu(sesionIniciada);
        }else{
            System.out.println("Login incorrecto");
            sesionIniciada=false;
            v.actualizarMenu(sesionIniciada);
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

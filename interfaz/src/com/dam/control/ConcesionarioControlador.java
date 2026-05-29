package com.dam.control;

import com.dam.model.data.Login;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Vehiculo;
import com.dam.view.VPrincipal;
import com.dam.view.PNuevoVehiculo;
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
    
    private ModeloVehiculoDAO modeloVehiculoDAO;
    private VehiculoDAO vehiculoDAO;
    private boolean modoClaro=true;

    public ConcesionarioControlador(VPrincipal v,PNuevoVehiculo pNuevoVehiculo,PVerCatalogo pVerCatalogo,PNuevoModelo pNuevoModelo,PLogin pLogin,PInformacionVehiculo pInformacionVehiculo,LoginDAO loginDAO,ModeloVehiculoDAO modeloVehiculoDAO,VehiculoDAO vehiculoDAO) {
        this.pNuevoVehiculo = pNuevoVehiculo;
        this.pVerCatalogo = pVerCatalogo;
        this.pNuevoModelo = pNuevoModelo;
        this.pLogin = pLogin;
        this.pInformacionVehiculo=pInformacionVehiculo;
        this.v = v;
        this.loginDAO=loginDAO;
        this.modeloVehiculoDAO=modeloVehiculoDAO;
        this.vehiculoDAO=vehiculoDAO;
        actualizarModoClaroOscuro(modoClaro);
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
        else{
            System.out.println("Comando no reconocido: " + comando);
        }
    }

    private void controlMenus(ActionEvent e) {
        switch (e.getActionCommand()) {
            case VPrincipal.NUEVO_VEHICULO_MENU:
                //TODO: temporal (implementar DAO)
                //String [] marcas={"Toyota","Honda","Ford"};
                //String [] modelos={"Corolla","RAV4","Yaris"};
                //pNuevoVehiculo.actualizarMarcas(marcas);
                //pNuevoVehiculo.actualizarModelos(modelos);
                v.cargarPanel(pNuevoVehiculo);
                actualizarModoClaroOscuro(modoClaro);
                break;
            case VPrincipal.VER_CATALOGO_MENU:
                v.cargarPanel(pVerCatalogo);
                actualizarModoClaroOscuro(modoClaro);
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
            default:
                System.out.println("Menu no reconocido: " + e.getActionCommand());
                break;
        }
    }

    private void controlBotones(ActionEvent e) {
        if(e.getActionCommand().startsWith("masinfo")){
            System.out.println("boton masinfo");
            v.cargarPanel(pInformacionVehiculo);
            //TODO temporal
            //ModeloVehiculo mPrueba=new ModeloVehiculo(1,"laferrari",2,3,"gasolina","trasera","Ferrari","manual");
            //Vehiculo vPrueba=new Vehiculo(1,mPrueba,100000,"matricula","rojo",2014,0,0,0,0);
            //pInformacionVehiculo.mostrarInfoVehiculo(vPrueba);
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

    private void actualizarModoClaroOscuro(boolean modoClaro) {
        System.out.println("Cambiando modo claro/oscuro");
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
        // TODO: Implementar lógica para guardar un nuevo modelo
        ModeloVehiculo m=pNuevoModelo.getModeloVehiculo();
        System.out.println(m);
    }

    private void login() {
        // TODO: Implementar lógica
        Login l=pLogin.getLogin();
        boolean loginCorrecto=loginDAO.iniciarSesion(l);
        if(loginCorrecto){
            System.out.println("Login correcto");
        }else{
            System.out.println("Login incorrecto");
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
        // TODO: Implementar lógica
        String marca=pNuevoVehiculo.getMarca();
        ArrayList<ModeloVehiculo> modelos=modeloVehiculoDAO.getModelos(marca);
        pNuevoVehiculo.actualizarModelos(modelos);
    }
}

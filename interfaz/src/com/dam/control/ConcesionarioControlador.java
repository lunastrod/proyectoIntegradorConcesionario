package com.dam.control;

import com.dam.model.*;
import com.dam.model.data.Login;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Vehiculo;
import com.dam.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenuItem;

public class ConcesionarioControlador implements ActionListener{
    private VPrincipal v;
    private PNuevoVehiculo pNuevoVehiculo;
    private PVerCatalogo pVerCatalogo;
    private PNuevoModelo pNuevoModelo;
    private PLogin pLogin;
    private final List<Vehiculo> vehiculos = new ArrayList<>();

    public ConcesionarioControlador(VPrincipal v, PNuevoVehiculo pNuevoVehiculo, PVerCatalogo pVerCatalogo, PNuevoModelo pNuevoModelo, PLogin pLogin) {
        this.pNuevoVehiculo = pNuevoVehiculo;
        this.pVerCatalogo = pVerCatalogo;
        this.pNuevoModelo = pNuevoModelo;
        this.pLogin = pLogin;
        this.v = v;
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
                String [] marcas={"Toyota","Honda","Ford"};
                String [] modelos={"Corolla","RAV4","Yaris"};
                pNuevoVehiculo.actualizarMarcas(marcas);
                pNuevoVehiculo.actualizarModelos(modelos);
                v.cargarPanel(pNuevoVehiculo);
                break;
            case VPrincipal.VER_CATALOGO_MENU:
                v.cargarPanel(pVerCatalogo);
                break;
            case VPrincipal.NUEVO_MODELO_MENU:
                v.cargarPanel(pNuevoModelo);
                break;
            case VPrincipal.LOGIN_MENU:
                v.cargarPanel(pLogin);
                break;
            default:
                System.out.println("Menu no reconocido: " + e.getActionCommand());
                break;
        }
    }

    private void controlBotones(ActionEvent e) {
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
            default:
                System.out.println("Boton no reconocido: " + e.getActionCommand());
                break;
        }
    }

    private void guardarVehiculo() {
        Vehiculo v=pNuevoVehiculo.getVehiculo();
        vehiculos.add(v);
        System.out.println("Vehiculo guardado: " + v);
    }

    private void guardarModelo() {
        // TODO: Implementar lógica para guardar un nuevo modelo
        ModeloVehiculo m=pNuevoModelo.getModeloVehiculo();
        System.out.println(m);
    }

    private void login() {
        // TODO: Implementar lógica para guardar un nuevo modelo
        Login l=pLogin.getLogin();
        System.out.println(l);
    }
}

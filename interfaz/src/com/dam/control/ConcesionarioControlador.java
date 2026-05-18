package com.dam.control;

import com.dam.model.*;
import com.dam.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConcesionarioControlador implements ActionListener{
    private VPrincipal v;
    private PNuevoVehiculo pNuevoVehiculo;
    private PVerCatalogo pVerCatalogo;
    private PNuevoModelo pNuevoModelo;
    private PLogin pLogin;

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
        if (comando.equals(VPrincipal.NUEVO_VEHICULO_MENU)) {
            String [] marcas={"Toyota","Honda","Ford"};
            String [] modelos={"Corolla","RAV4","Yaris"};
            pNuevoVehiculo.actualizarMarcas(marcas);
            pNuevoVehiculo.actualizarModelos(modelos);
            v.cargarPanel(pNuevoVehiculo);
        } 
        else if (comando.equals(VPrincipal.VER_CATALOGO_MENU)) {
            v.cargarPanel(pVerCatalogo);
        }
        else if (comando.equals(VPrincipal.NUEVO_MODELO_MENU)) {
            v.cargarPanel(pNuevoModelo);
        }
        else if (comando.equals(VPrincipal.LOGIN_MENU)) {
            v.cargarPanel(pLogin);
        }
        else if (comando.equals(PNuevoVehiculo.GUARDAR_VEHICULO_BTN)) {
            Vehiculo v=pNuevoVehiculo.getVehiculo();
            System.out.println(v);
            // TODO: Implementar lógica para guardar un nuevo vehiculo
        }
        else if (comando.equals(PNuevoModelo.GUARDAR_MODELO_BTN)) {
            ModeloVehiculo m=pNuevoModelo.getModeloVehiculo();
            System.out.println(m);
            // TODO: Implementar lógica para guardar un nuevo modelo
        }
        else if (comando.equals(PLogin.LOGIN_BTN)) {
            Login l=pLogin.getLogin();
            System.out.println(l);
        }
    }
}

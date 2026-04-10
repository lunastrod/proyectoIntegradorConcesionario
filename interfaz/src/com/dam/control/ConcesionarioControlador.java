package com.dam.control;

import com.dam.view.PNuevoModelo;
import com.dam.view.PNuevoVehiculo;
import com.dam.view.PVerCatalogo;
import com.dam.view.VPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConcesionarioControlador implements ActionListener{
    private VPrincipal v;
    private PNuevoVehiculo pNuevoVehiculo;
    private PVerCatalogo pVerCatalogo;
    private PNuevoModelo pNuevoModelo;

    public ConcesionarioControlador(VPrincipal v, PNuevoVehiculo pNuevoVehiculo, PVerCatalogo pVerCatalogo, PNuevoModelo pNuevoModelo){
        this.pNuevoVehiculo = pNuevoVehiculo;
        this.pVerCatalogo = pVerCatalogo;
        this.pNuevoModelo = pNuevoModelo;
        this.v = v;
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        if (comando.equals(VPrincipal.NUEVO_VEHICULO_MENU)) {
            v.cargarPanel(pNuevoVehiculo);
        } 
        else if (comando.equals(VPrincipal.VER_CATALOGO_MENU)) {
            v.cargarPanel(pVerCatalogo);
        }
        else if (comando.equals(VPrincipal.NUEVO_MODELO_MENU)) {
            v.cargarPanel(pNuevoModelo);
        }
    }
}

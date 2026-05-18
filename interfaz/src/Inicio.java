import java.awt.EventQueue;

import com.dam.control.*;
import com.dam.model.*;
import com.dam.view.*;

public class Inicio {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                pruebaModelo();
                VPrincipal ventanaPrincipal=new VPrincipal();
                PNuevoVehiculo pNuevoVehiculo=new PNuevoVehiculo();
                PVerCatalogo pVerCatalogo=new PVerCatalogo();
                PNuevoModelo pNuevoModelo=new PNuevoModelo();
                PLogin pLogin=new PLogin();
                ConcesionarioControlador controlador=new ConcesionarioControlador(ventanaPrincipal,pNuevoVehiculo,pVerCatalogo,pNuevoModelo,pLogin);
                ventanaPrincipal.setControlador(controlador);
                pNuevoVehiculo.setControlador(controlador);
                pVerCatalogo.setControlador(controlador);
                pNuevoModelo.setControlador(controlador);
                pLogin.setControlador(controlador);

                ventanaPrincipal.cargarPanel(pVerCatalogo);
                ventanaPrincipal.hacerVisible();
                ventanaPrincipal.actualizarMenu(true);
            }
        });
    }

    public static void pruebaModelo() {
        Cliente c=new Cliente(1,"Pepe");
        System.out.println(c);
        Trabajador t=new Trabajador(1,"Juan");
        System.out.println(t);
        ModeloVehiculo m=new ModeloVehiculo(1,"Corolla","Toyota");
        System.out.println(m);
        Vehiculo v=new Vehiculo(1,m,30000);
        System.out.println(v);
        System.out.println("-----------------");
        Venta venta=new Venta(1,c,t,v);
        System.out.println(venta);
    }
}
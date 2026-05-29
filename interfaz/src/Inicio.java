import java.awt.EventQueue;

import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Cliente;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Trabajador;
import com.dam.model.data.Vehiculo;
import com.dam.model.data.Venta;
import com.dam.model.db.AccesoBD;
import com.dam.model.db.LoginDAO;
import com.dam.model.db.ModeloVehiculoDAO;
import com.dam.model.db.VehiculoDAO;
import com.dam.view.PInformacionVehiculo;
import com.dam.view.PLogin;
import com.dam.view.PNuevoModelo;
import com.dam.view.PNuevoVehiculo;
import com.dam.view.PVerCatalogo;
import com.dam.view.VPrincipal;

public class Inicio {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                pruebaModelo();
                VPrincipal ventanaPrincipal=new VPrincipal();
                PVerCatalogo pVerCatalogo=new PVerCatalogo();
                ventanaPrincipal.cargarPanel(pVerCatalogo);
                PNuevoVehiculo pNuevoVehiculo=new PNuevoVehiculo();
                PNuevoModelo pNuevoModelo=new PNuevoModelo();
                PLogin pLogin=new PLogin();
                AccesoBD bd=new AccesoBD();
                LoginDAO loginDAO=new LoginDAO(bd);
                ModeloVehiculoDAO modeloVehiculoDAO=new ModeloVehiculoDAO(bd);
                VehiculoDAO vehiculoDAO=new VehiculoDAO(bd);
                PInformacionVehiculo pInformacionVehiculo=new PInformacionVehiculo();
                ConcesionarioControlador controlador=new ConcesionarioControlador(
                    ventanaPrincipal,
                    pNuevoVehiculo,
                    pVerCatalogo,
                    pNuevoModelo,
                    pLogin,
                    pInformacionVehiculo,
                    loginDAO,
                    modeloVehiculoDAO,
                    vehiculoDAO
                );
                ventanaPrincipal.setControlador(controlador);
                pNuevoVehiculo.setControlador(controlador);
                pVerCatalogo.setControlador(controlador);
                pNuevoModelo.setControlador(controlador);
                pLogin.setControlador(controlador);

                
                ventanaPrincipal.hacerVisible();
                ventanaPrincipal.actualizarMenu(true);
            }
        });
    }

    public static void pruebaModelo() {
    	/*
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
        */
    }
}

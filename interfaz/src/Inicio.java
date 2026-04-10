import java.awt.EventQueue;

import com.dam.control.ConcesionarioControlador;
import com.dam.view.PNuevoModelo;
import com.dam.view.PNuevoVehiculo;
import com.dam.view.PVerCatalogo;
import com.dam.view.VPrincipal;

public class Inicio {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                VPrincipal ventanaPrincipal=new VPrincipal();
                PNuevoVehiculo pNuevoVehiculo=new PNuevoVehiculo();
                PVerCatalogo pVerCatalogo=new PVerCatalogo();
                PNuevoModelo pNuevoModelo=new PNuevoModelo();
                ConcesionarioControlador controlador=new ConcesionarioControlador(ventanaPrincipal,pNuevoVehiculo,pVerCatalogo,pNuevoModelo);
                ventanaPrincipal.setControlador(controlador);
                ventanaPrincipal.hacerVisible();
            }
        });
    }
}
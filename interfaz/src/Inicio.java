import java.awt.EventQueue;

import com.dam.control.ConcesionarioControlador;
import com.dam.model.db.AccesoBD;
import com.dam.model.db.ClienteDAO;
import com.dam.model.db.ModeloVehiculoDAO;
import com.dam.model.db.TrabajadorDAO;
import com.dam.model.db.VehiculoDAO;
import com.dam.model.db.VentaDAO;
import com.dam.view.PInformacionVehiculo;
import com.dam.view.PLogin;
import com.dam.view.PModificarVehiculo;
import com.dam.view.PNuevoModelo;
import com.dam.view.PNuevoVehiculo;
import com.dam.view.PRegistrarTrabajador;
import com.dam.view.PVerCatalogo;
import com.dam.view.VPrincipal;

//TODO ver que pasa si se elimina un vehiculo
//TODO que el scrollpane quepa en la pantalla de catalogo

public class Inicio {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                VPrincipal ventanaPrincipal = new VPrincipal();
                PVerCatalogo pVerCatalogo = new PVerCatalogo();
                PNuevoVehiculo pNuevoVehiculo = new PNuevoVehiculo();
                PNuevoModelo pNuevoModelo = new PNuevoModelo();
                PModificarVehiculo pModificarVehiculo = new PModificarVehiculo();
                PLogin pLogin = new PLogin();
                PRegistrarTrabajador pRegistrarTrabajador = new PRegistrarTrabajador();
                PInformacionVehiculo pInformacionVehiculo = new PInformacionVehiculo();

                AccesoBD bd = new AccesoBD();
                ClienteDAO clienteDAO = new ClienteDAO(bd);
                ModeloVehiculoDAO modeloVehiculoDAO = new ModeloVehiculoDAO(bd);
                TrabajadorDAO trabajadorDAO = new TrabajadorDAO(bd);
                VehiculoDAO vehiculoDAO = new VehiculoDAO(bd);
                VentaDAO ventaDAO = new VentaDAO(bd);

                ConcesionarioControlador controlador = new ConcesionarioControlador(
                        ventanaPrincipal,
                        pNuevoVehiculo,
                        pVerCatalogo,
                        pNuevoModelo,
                        pModificarVehiculo,
                        pLogin,
                        pRegistrarTrabajador,
                        pInformacionVehiculo,
                        clienteDAO,
                        modeloVehiculoDAO,
                        trabajadorDAO,
                        vehiculoDAO,
                        ventaDAO);

                ventanaPrincipal.setControlador(controlador);
                pNuevoVehiculo.setControlador(controlador);
                pVerCatalogo.setControlador(controlador);
                pNuevoModelo.setControlador(controlador);
                pInformacionVehiculo.setControlador(controlador);
                pLogin.setControlador(controlador);
                pModificarVehiculo.setControlador(controlador);
                pRegistrarTrabajador.setControlador(controlador);

                controlador.cargarPanelCatalogo();
                ventanaPrincipal.hacerVisible();
            }
        });
    }
}
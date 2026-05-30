import java.awt.EventQueue;

import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Cliente;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Trabajador;
import com.dam.model.data.Vehiculo;
import com.dam.model.data.Venta;
import com.dam.model.db.AccesoBD;
import com.dam.model.db.ClienteDAO;
import com.dam.model.db.LoginDAO;
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

/*TODO que hacemos con LoginDAO, eliminar? reemplazada por TrabajadorDAO 
para eliminar solo deberian aparecer los modelos a la venta, asi que dao de vehiculos a la venta
panel de ver historial de ventas

mensajes de error y aviso en VPrincipal

quizas no hace falta cambiar el actioncommand de PVehiculo

*/

public class Inicio {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                pruebas();
                VPrincipal ventanaPrincipal=new VPrincipal();
                PVerCatalogo pVerCatalogo=new PVerCatalogo();
                
                PNuevoVehiculo pNuevoVehiculo=new PNuevoVehiculo();
                PNuevoModelo pNuevoModelo=new PNuevoModelo();
                PModificarVehiculo pModificarVehiculo=new PModificarVehiculo();
                PLogin pLogin=new PLogin();
                PRegistrarTrabajador pRegistrarTrabajador=new PRegistrarTrabajador();
                AccesoBD bd=new AccesoBD();
                ClienteDAO clienteDAO=new ClienteDAO(bd);
                LoginDAO loginDAO=new LoginDAO(bd);
                ModeloVehiculoDAO modeloVehiculoDAO=new ModeloVehiculoDAO(bd);
                TrabajadorDAO trabajadorDAO=new TrabajadorDAO(bd);
                VehiculoDAO vehiculoDAO=new VehiculoDAO(bd);
                VentaDAO ventaDAO=new VentaDAO(bd);
                PInformacionVehiculo pInformacionVehiculo=new PInformacionVehiculo();
                ConcesionarioControlador controlador=new ConcesionarioControlador(
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
                    ventaDAO,
                    loginDAO
                );
                
                ventanaPrincipal.setControlador(controlador);
                pNuevoVehiculo.setControlador(controlador);
                pVerCatalogo.setControlador(controlador);
                pNuevoModelo.setControlador(controlador);
                pInformacionVehiculo.setControlador(controlador);
                pLogin.setControlador(controlador);

                controlador.cargarPanelCatalogo();

                ventanaPrincipal.hacerVisible();
            }
        });
    }

    public static void pruebas() {
    	/*
        AccesoBD bd=new AccesoBD();
        LoginDAO loginDAO=new LoginDAO(bd);
        ModeloVehiculoDAO modeloVehiculoDAO=new ModeloVehiculoDAO(bd);
        VehiculoDAO vehiculoDAO=new VehiculoDAO(bd);
        TrabajadorDAO trabajadorDAO=new TrabajadorDAO(bd);
        VentaDAO ventaDAO=new VentaDAO(bd);
        System.out.println(modeloVehiculoDAO.selectMarcas());
        System.out.println(modeloVehiculoDAO.selectModeloPorMarca("Mercedes"));
        System.out.println(vehiculoDAO.selectTodos());
        System.out.println(trabajadorDAO.selectAllTrabajadores());
        System.out.println(ventaDAO.selectVentas());        
        */
    }
}

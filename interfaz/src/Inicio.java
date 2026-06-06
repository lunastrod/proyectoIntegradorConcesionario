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
import com.dam.view.PModificarModelo;
import com.dam.view.PModificarVehiculo;
import com.dam.view.PNuevoModelo;
import com.dam.view.PNuevoVehiculo;
import com.dam.view.PRegistrarTrabajador;
import com.dam.view.PVerCatalogo;
import com.dam.view.PVerVentasClientes;
import com.dam.view.VPrincipal;

/**
 * Clase de arranque de la aplicación de concesionario (Autogestión).
 * <p>
 * Contiene el método main que actúa como punto de entrada del programa.
 * Se encarga de instanciar todos los componentes de la arquitectura MVC (Modelo, Vista, Control):
 * las vistas, los objetos de acceso a datos y el controlador principal,
 * conectarlos entre sí y lanzar la interfaz gráfica.
 * <p>
 * La inicialización se ejecuta dentro del hilo de despacho de eventos
 * de Swing mediante {@link EventQueue#invokeLater(Runnable)}, garantizando
 * que todos los componentes gráficos se creen y manipulen desde el hilo
 * correcto.
 * @see ConcesionarioControlador
 * @see VPrincipal
 * @see AccesoBD
 */
public class Inicio {
    /**
     * Punto de entrada de la aplicación.
     * <p>
     * Programa la inicialización completa de la interfaz en el hilo de
     * despacho de eventos de Swing. El proceso sigue estos pasos:
     * - Crea la ventana principal y todos los paneles de la vista.
     * - Crea la conexión a la base de datos y todos los DAOs necesarios.
     * - Crea el controlador inyectando las vistas y los DAOs.
     * - Registra el controlador en cada vista mediante setControlador.
     * - Carga el panel del catálogo como vista inicial.
     * - Hace visible la ventana principal.
     * @param args argumentos de línea de comandos; no se utilizan en esta aplicación.
     */
//TODO: ver que pasa si se elimina un vehiculo o modelo cuando tienen fK en venta
//validaciones de los campos, como la matricula
//filtros en el catalogo
//constantes de tamaño duplicadas, podrian ir en la interface
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                VPrincipal ventanaPrincipal = new VPrincipal();
                PVerCatalogo pVerCatalogo = new PVerCatalogo();
                PNuevoVehiculo pNuevoVehiculo = new PNuevoVehiculo();
                PNuevoModelo pNuevoModelo = new PNuevoModelo();
                PModificarModelo pModificarModelo = new PModificarModelo();
                PModificarVehiculo pModificarVehiculo = new PModificarVehiculo();
                PLogin pLogin = new PLogin();
                PRegistrarTrabajador pRegistrarTrabajador = new PRegistrarTrabajador();
                PInformacionVehiculo pInformacionVehiculo = new PInformacionVehiculo();
                PVerVentasClientes pVerVentasClientes = new PVerVentasClientes();

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
                        pModificarModelo,
                        pModificarVehiculo,
                        pLogin,
                        pRegistrarTrabajador,
                        pInformacionVehiculo,
                        pVerVentasClientes,
                        clienteDAO,
                        modeloVehiculoDAO,
                        trabajadorDAO,
                        vehiculoDAO,
                        
                        ventaDAO);

                ventanaPrincipal.setControlador(controlador);
                pNuevoVehiculo.setControlador(controlador);
                pVerCatalogo.setControlador(controlador);
                pNuevoModelo.setControlador(controlador);
                pModificarModelo.setControlador(controlador);
                pInformacionVehiculo.setControlador(controlador);
                pLogin.setControlador(controlador);
                pModificarVehiculo.setControlador(controlador);
                pRegistrarTrabajador.setControlador(controlador);
                pVerVentasClientes.setControlador(controlador);
                
                controlador.cargarPanelCatalogo();
                ventanaPrincipal.hacerVisible();
            }
        });
    }
}
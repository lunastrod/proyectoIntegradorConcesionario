package com.dam.control;

import com.dam.model.data.Cliente;
import com.dam.model.data.Login;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Trabajador;
import com.dam.model.data.Vehiculo;
import com.dam.model.data.Venta;
import com.dam.view.Avisos;
import com.dam.view.VPrincipal;
import com.dam.view.PNuevoVehiculo;
import com.dam.view.PVerCatalogo;
import com.dam.view.PNuevoModelo;
import com.dam.view.PInformacionVehiculo;
import com.dam.view.PLogin;
import com.dam.view.PModificarModelo;
import com.dam.view.PModificarVehiculo;
import com.dam.view.PRegistrarTrabajador;
import com.dam.view.PVehiculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.dam.model.db.ClienteDAO;
import com.dam.model.db.ModeloVehiculoDAO;
import com.dam.model.db.TrabajadorDAO;
import com.dam.model.db.VehiculoDAO;
import com.dam.model.db.VentaDAO;

import javax.swing.JButton;
import javax.swing.JMenuItem;

/**
 * Controlador principal de la aplicación del concesionario.
 * <p>
 * Actúa como intermediario entre la capa de vista y la capa de acceso
 * a datos, siguiendo el patrón MVC. Implementa {@link ActionListener}
 * para centralizar el manejo de eventos de todos los botones y
 * elementos de menú de la interfaz.
 * <p>
 * Gestiona el estado de la sesión activa, el modo claro u oscuro
 * de la interfaz, y delega cada acción en el método privado correspondiente.
 * @see VPrincipal
 * @see com.dam.model.db.AccesoBD
 */
public class ConcesionarioControlador implements ActionListener {

    /** Ventana principal de la aplicación. */
    private VPrincipal v;

    /** Panel para añadir nuevos vehículos al catálogo. */
    private PNuevoVehiculo pNuevoVehiculo;

    /** Panel para visualizar el catálogo de vehículos disponibles. */
    private PVerCatalogo pVerCatalogo;

    /** Panel para añadir nuevos modelos de vehículo. */
    private PNuevoModelo pNuevoModelo;

    /** Panel para modificar un modelo de vehículo existente. */
    private PModificarModelo pModificarModelo;

    /** Panel para modificar un vehículo existente. */
    private PModificarVehiculo pModificarVehiculo;

    /** Panel de inicio de sesión para trabajadores. */
    private PLogin pLogin;

    /** Panel para registrar y gestionar trabajadores. */
    private PRegistrarTrabajador pRegistrarTrabajador;

    /** Panel con la información detallada de un vehículo y el formulario de compra. */
    private PInformacionVehiculo pInformacionVehiculo;

    /** DAO para operaciones sobre la tabla Cliente. */
    private ClienteDAO clienteDAO;

    /** DAO para operaciones sobre la tabla Modelo. */
    private ModeloVehiculoDAO modeloVehiculoDAO;

    /** DAO para operaciones sobre la tabla Trabajador. */
    private TrabajadorDAO trabajadorDAO;

    /** DAO para operaciones sobre la tabla Vehiculo. */
    private VehiculoDAO vehiculoDAO;

    /** DAO para operaciones sobre la tabla Venta. */
    private VentaDAO ventaDAO;

    /**
     * Indica si la interfaz está en modo claro (true) u oscuro (false).
     * Se alterna desde el menú principal.
     */
    private boolean modoClaro = true;

    /**
     * Indica si hay una sesión de trabajador activa.
     * Controla la visibilidad de las opciones del menú restringidas.
     * NOTA: En la versión final estará por defecto en false.
     */
    private boolean sesionIniciada = true;

    /**
     * Indica si el trabajador con sesión activa tiene permisos de administrador.
     * Controla la visibilidad de las opciones exclusivas de administrador.
     * NOTA: En la versión final estará por defecto en false.
     */
    private boolean admin = true;

    /**
     * Crea el controlador inyectando todas las vistas y DAOs necesarios.
     * <p>
     * Durante la construcción se aplica el modo claro u oscuro inicial
     * y se actualiza la visibilidad del menú según el estado de sesión.
     * @param v                    ventana principal de la aplicación.
     * @param pNuevoVehiculo       panel para añadir vehículos.
     * @param pVerCatalogo         panel del catálogo de vehículos.
     * @param pNuevoModelo         panel para añadir modelos.
     * @param pModificarModelo     panel para modificar modelos.
     * @param pModificarVehiculo   panel para modificar vehículos.
     * @param pLogin               panel de inicio de sesión.
     * @param pRegistrarTrabajador panel de gestión de trabajadores.
     * @param pInformacionVehiculo panel de información y compra de vehículos.
     * @param clienteDAO           DAO de clientes.
     * @param modeloVehiculoDAO    DAO de modelos de vehículo.
     * @param trabajadorDAO        DAO de trabajadores.
     * @param vehiculoDAO          DAO de vehículos.
     * @param ventaDAO             DAO de ventas.
     */
    public ConcesionarioControlador(
            VPrincipal v,
            PNuevoVehiculo pNuevoVehiculo,
            PVerCatalogo pVerCatalogo,
            PNuevoModelo pNuevoModelo,
            PModificarModelo pModificarModelo,
            PModificarVehiculo pModificarVehiculo,
            PLogin pLogin,
            PRegistrarTrabajador pRegistrarTrabajador,
            PInformacionVehiculo pInformacionVehiculo,
            ClienteDAO clienteDAO,
            ModeloVehiculoDAO modeloVehiculoDAO,
            TrabajadorDAO trabajadorDAO,
            VehiculoDAO vehiculoDAO,
            VentaDAO ventaDAO) {
        this.v = v;
        this.pNuevoVehiculo = pNuevoVehiculo;
        this.pVerCatalogo = pVerCatalogo;
        this.pNuevoModelo = pNuevoModelo;
        this.pModificarModelo = pModificarModelo;
        this.pModificarVehiculo = pModificarVehiculo;
        this.pLogin = pLogin;
        this.pRegistrarTrabajador = pRegistrarTrabajador;
        this.pInformacionVehiculo = pInformacionVehiculo;
        this.clienteDAO = clienteDAO;
        this.modeloVehiculoDAO = modeloVehiculoDAO;
        this.trabajadorDAO = trabajadorDAO;
        this.vehiculoDAO = vehiculoDAO;
        this.ventaDAO = ventaDAO;
        actualizarModoClaroOscuro(modoClaro);
        v.actualizarMenu(sesionIniciada, admin);
    }

    /**
     * Punto de entrada centralizado para todos los eventos de la interfaz.
     * <p>
     * Redirige el evento al método de control de menús si la fuente es
     * un {@link JMenuItem}, o al método de control de botones si la fuente
     * es un {@link JButton}. Si la fuente no es ninguno de los dos,
     * registra el comando no reconocido por consola.
     * @param e evento de acción generado por la interfaz
     */
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        if (e.getSource() instanceof JMenuItem) {
            controlMenus(e);
        } else if (e.getSource() instanceof JButton) {
            controlBotones(e);
        } else {
            System.out.println("Comando no reconocido: " + comando);
        }
    }

    /**
     * Gestiona los eventos generados por los elementos del menú principal.
     * <p>
     * Cada opción del menú carga el panel correspondiente en la ventana
     * principal y aplica el modo claro u oscuro activo. Las opciones
     * de gestión de trabajadores también refrescan la tabla de la vista.
     * @param e evento de acción generado por un elemento del menú
     */
    private void controlMenus(ActionEvent e) {
        switch (e.getActionCommand()) {
            case VPrincipal.NUEVO_VEHICULO_MENU:
                pNuevoVehiculo.actualizarMarcas(modeloVehiculoDAO.selectMarcas());
                pNuevoVehiculo.actualizarVehiculos(vehiculoDAO.selectTodos());
                v.cargarPanel(pNuevoVehiculo);
                actualizarModoClaroOscuro(modoClaro);
                break;
            case VPrincipal.VER_CATALOGO_MENU:
                cargarPanelCatalogo();
                break;
            case VPrincipal.NUEVO_MODELO_MENU:
                pNuevoModelo.actualizarListaModelos(modeloVehiculoDAO.selectTodos());
                v.cargarPanel(pNuevoModelo);
                actualizarModoClaroOscuro(modoClaro);
                break;
            case VPrincipal.LOGIN_MENU:
                v.cargarPanel(pLogin);
                actualizarModoClaroOscuro(modoClaro);
                break;
            case VPrincipal.MODO_CLARO_OSCURO_MENU:
                modoClaro = !modoClaro;
                actualizarModoClaroOscuro(modoClaro);
                break;
            case VPrincipal.NUEVO_EMPLEADO:
                v.cargarPanel(pRegistrarTrabajador);
                actualizarModoClaroOscuro(modoClaro);
                consultarTrabajadores();
                break;
            default:
                System.out.println("Menu no reconocido: " + e.getActionCommand());
                break;
        }
    }

    /**
     * Gestiona los eventos generados por los botones de todos los paneles.
     * <p>
     * Identifica el botón pulsado mediante su comando de acción y delega
     * la lógica en el método privado correspondiente.
     * @param e evento de acción generado por un botón de la interfaz.
     */
    private void controlBotones(ActionEvent e) {
        Vehiculo vehiculo;
        switch (e.getActionCommand()) {
            case PVehiculo.MAS_INFO_BTN:
                vehiculo = ((PVehiculo) ((JButton) e.getSource()).getParent()).getVehiculoActual();
                pInformacionVehiculo.actualizarTrabajadores(trabajadorDAO.selectAllTrabajadores());
                pInformacionVehiculo.mostrarInfoVehiculo(vehiculo);
                v.cargarPanel(pInformacionVehiculo);
                actualizarModoClaroOscuro(modoClaro);
                break;
            case PNuevoVehiculo.GUARDAR_VEHICULO_BTN:
                guardarVehiculo();
                break;
            case PNuevoVehiculo.LIMPIAR_VEHICULO_BTN:
                pNuevoVehiculo.limpiarDatos();
                break;
            case PNuevoModelo.GUARDAR_MODELO_BTN:
                guardarModelo();
                break;
            case PNuevoModelo.LIMPIAR_MODELO_BTN:
                pNuevoModelo.limpiarDatos();
                break;
            case PNuevoModelo.ELIMINAR_MODELO_BTN:
                eliminarModelo();
                break;
            case PNuevoModelo.MODIFICAR_MODELO_BTN:
                cargarPanelModificarModelo();
                break;
            case PModificarModelo.GUARDAR_MODIFICACION_MODELO_BTN:
                modificarModelo();
                break;
            case PModificarModelo.LIMPIAR_MOD_MODELO_BTN:
                pModificarModelo.limpiarDatos();
                break;
            case PLogin.LOGIN_BTN:
                login();
                break;
            case PInformacionVehiculo.COMPRAR_BTN:
                pInformacionVehiculo.togglePanelCompra();
                break;
            case PInformacionVehiculo.REALIZAR_COMPRA_BTN:
                comprarVehiculo();
                break;
            case PInformacionVehiculo.VOLVER_CATALOGO_BTN:
                cargarPanelCatalogo();
                break;
            case PNuevoVehiculo.BUSCAR_MARCA_BTN:
                buscarMarca();
                break;
            case PNuevoVehiculo.VER_COLOR_BTN:
                pNuevoVehiculo.actualizarColor();
                break;
            case PNuevoVehiculo.ELIMINAR_VEHICULO_BTN:
                eliminarVehiculo();
                break;
            case PNuevoVehiculo.MODIFICAR_VEHICULO_BTN:
                cargarPanelModificarVehiculo();
                break;
            case PModificarVehiculo.GUARDAR_MODIFICACION_BTN:
                modificarVehiculo();
                break;
            case PModificarVehiculo.LIMPIAR_MOD_VEHICULO_BTN:
                pModificarVehiculo.limpiarDatos();
                break;
            case PModificarVehiculo.VER_COLOR_MODIFICAR_BTN:
                pModificarVehiculo.actualizarColor();
                break;
            case PModificarVehiculo.BUSCAR_MARCA_MODIFICAR_BTN:
                buscarMarcaModificar();
                break;
            case PRegistrarTrabajador.REGISTRAR_TRABAJADOR_BTN:
                registrarTrabajador();
                break;
            case PRegistrarTrabajador.ELIMINAR_TRABAJADOR_BTN:
                eliminarTrabajador();
                break;
            case PRegistrarTrabajador.LIMPIAR_DATOS_BTN:
                pRegistrarTrabajador.limpiarDatos();
                break;
            default:
                System.out.println("Boton no reconocido: " + e.getActionCommand());
                break;
        }
    }

    /**
     * Registra la compra de un vehículo por parte de un cliente.
     * <p>
     * Obtiene el vehículo y el trabajador seleccionados en el panel de
     * información, valida los datos del formulario de compra y comprueba
     * si el cliente ya existe en la base de datos. Si no existe, lo inserta.
     * Finalmente, crea y persiste la venta.
     * <p>
     * Muestra mensajes de error si algún campo obligatorio está vacío
     * o si no se puede registrar la venta.
     */
    public void comprarVehiculo() {
        Vehiculo vehiculo = pInformacionVehiculo.getVehiculoActual();
        if (vehiculo == null) {
            Avisos.error(v, "No hay vehículo seleccionado.");
            return;
        }

        Trabajador trabajador = pInformacionVehiculo.getTrabajadorSeleccionado();
        if (trabajador == null) {
            Avisos.error(v, "Selecciona el trabajador que atiende la venta.");
            return;
        }

        Cliente clienteFormulario = pInformacionVehiculo.getCliente();
        if (clienteFormulario.getNombreApellidos() == null || clienteFormulario.getNombreApellidos().isBlank()) {
            Avisos.error(v, "Indica el nombre del cliente.");
            return;
        }

        Cliente cliente = clienteDAO.selectPorNombre(clienteFormulario.getNombreApellidos());
        if (cliente == null) {
            clienteDAO.insert(clienteFormulario);
            cliente = clienteDAO.selectPorNombre(clienteFormulario.getNombreApellidos());
        }

        if (cliente == null) {
            Avisos.error(v, "No se pudo registrar al cliente.");
            return;
        }

        Venta venta = new Venta(-1, cliente, trabajador, vehiculo, "");
        int res = ventaDAO.insert(venta);
        if (res > 0) {
            Avisos.info(v, "Venta registrada correctamente.");
            System.out.println(venta);
        } else {
            Avisos.error(v, "Error al registrar la venta.");
        }
    }

    /**
     * Carga el panel del catálogo de vehículos en la ventana principal.
     * <p>
     * Recupera todos los vehículos disponibles de la base de datos,
     * actualiza los paneles del catálogo y aplica el modo claro u oscuro activo.
     */
    public void cargarPanelCatalogo() {
        pVerCatalogo.actualizarPanelesVehiculo(vehiculoDAO.selectDisponibles());
        v.cargarPanel(pVerCatalogo);
        actualizarModoClaroOscuro(modoClaro);
    }

    /**
     * Carga el panel de modificación del vehículo seleccionado en la lista.
     * <p>
     * Recupera los modelos disponibles para la marca del vehículo seleccionado,
     * rellena el formulario con sus datos actuales y muestra el panel de modificación.
     * Si no hay ningún vehículo seleccionado, muestra un aviso al usuario.
     */
    private void cargarPanelModificarVehiculo() {
        Vehiculo vehiculo = pNuevoVehiculo.getVehiculoSeleccionado();
        if (vehiculo == null) {
            Avisos.aviso(v, "Selecciona un vehículo de la lista.");
            return;
        }
        String marca = vehiculo.getModelo().getMarca();
        ArrayList<ModeloVehiculo> modelos = modeloVehiculoDAO.selectModeloPorMarca(marca);
        if (modelos == null || modelos.isEmpty()) {
            modelos = new ArrayList<>();
            modelos.add(vehiculo.getModelo());
        }
        pModificarVehiculo.actualizarMarcas(modeloVehiculoDAO.selectMarcas());
        pModificarVehiculo.actualizarModelos(modelos);
        pModificarVehiculo.cargarVehiculo(vehiculo);
        v.cargarPanel(pModificarVehiculo);
        actualizarModoClaroOscuro(modoClaro);
    }

    /**
     * Persiste en la base de datos los cambios realizados sobre un vehículo
     * desde el panel de modificación.
     * <p>
     * Muestra un mensaje informativo si la operación se completó correctamente,
     * o un mensaje de error en caso contrario.
     */
    private void modificarVehiculo() {
        Vehiculo vehiculo = pModificarVehiculo.getVehiculo();
        if (!validarMatriculaVehiculo(vehiculo)) return;

        int res = vehiculoDAO.update(vehiculo);
        if (res > 0) {
            Avisos.info(v, "Vehículo modificado correctamente.");
        } else {
            Avisos.error(v, "Error al modificar el vehículo.");
        }
    }

    /**
     * Elimina el vehículo seleccionado en la lista tras solicitar confirmación.
     * <p>
     * Si el vehículo tiene ventas asociadas, la base de datos impedirá
     * la eliminación y se mostrará un mensaje de error. Si no hay ningún
     * vehículo seleccionado, muestra un aviso al usuario.
     */
    private void eliminarVehiculo() {
        Vehiculo vehiculo = pNuevoVehiculo.getVehiculoSeleccionado();
        if (vehiculo == null) {
            Avisos.aviso(v, "Selecciona un vehículo de la lista.");
            return;
        }
        if (!Avisos.confirmar(v, "¿Eliminar el vehículo " + vehiculo + "?\nSi tiene ventas asociadas no podrá eliminarse.")) return;
        int res = vehiculoDAO.delete(vehiculo.getIdVehiculo());
        if (res > 0) {
            pNuevoVehiculo.actualizarVehiculos(vehiculoDAO.selectTodos());
            Avisos.info(v, "Vehículo eliminado correctamente.");
        } else {
            Avisos.error(v, "No se pudo eliminar el vehículo.\nPuede que tenga ventas registradas asociadas.");
        }
    }

    /**
     * Aplica el tema visual claro u oscuro a todos los componentes
     * de las ventanas abiertas de la aplicación usando FlatLaf.
     * <p>
     * Si FlatLaf no está disponible en el classpath, registra el fallo
     * por consola sin interrumpir la ejecución.
     * @param modoClaro true para aplicar el tema claro, false para el oscuro.
     */
    private void actualizarModoClaroOscuro(boolean modoClaro) {
        try {
            if (modoClaro) {
                javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
            } else {
                javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
            }
            for (java.awt.Window window : java.awt.Window.getWindows()) {
                javax.swing.SwingUtilities.updateComponentTreeUI(window);
            }
        } catch (Exception e) {
            System.out.println("Fallo al inicializar FlatLaf");
        }
    }

    /**
     * Recoge los datos del formulario de nuevo vehículo y lo inserta
     * en la base de datos.
     * <p>
     * Si la inserción se completa correctamente, actualiza la lista de
     * vehículos del panel y muestra un mensaje informativo. En caso
     * contrario, muestra un mensaje de error.
     */
    private void guardarVehiculo() {
        Vehiculo vehiculo = pNuevoVehiculo.getVehiculo();
        if (!validarMatriculaVehiculo(vehiculo)) return;

        int res = vehiculoDAO.insert(vehiculo);
        if (res > 0) {
            pNuevoVehiculo.actualizarVehiculos(vehiculoDAO.selectTodos());
            Avisos.info(v, "Vehículo guardado correctamente.");
        } else {
            Avisos.error(v, "Error al guardar el vehículo.");
        }
    }

    /**
     * Recoge los datos del formulario de nuevo modelo y lo inserta
     * en la base de datos.
     * <p>
     * Si la inserción se completa correctamente, actualiza la lista de
     * modelos del panel y muestra un mensaje informativo. En caso
     * contrario, muestra un mensaje de error.
     */
    private void guardarModelo() {
        ModeloVehiculo m = pNuevoModelo.getModeloVehiculo();
        int res = modeloVehiculoDAO.insert(m);
        if (res > 0) {
            pNuevoModelo.actualizarListaModelos(modeloVehiculoDAO.selectTodos());
            Avisos.info(v, "Modelo guardado correctamente.");
        } else {
            Avisos.error(v, "Error al guardar el modelo.");
        }
        System.out.println(m);
    }

    private boolean validarMatriculaVehiculo(Vehiculo vehiculo) {
        if (!Vehiculo.matriculaValida(vehiculo.getMatricula())) {
            Avisos.error(v, "La matrícula debe tener el formato 0000 AAA.");
            return false;
        }
        return true;
    }

    /**
     * Carga el panel de modificación del modelo seleccionado en la lista.
     * <p>
     * Rellena el formulario con los datos actuales del modelo y muestra
     * el panel de modificación. Si no hay ningún modelo seleccionado,
     * muestra un aviso al usuario.
     */
    private void cargarPanelModificarModelo() {
        ModeloVehiculo modelo = pNuevoModelo.getModeloSeleccionado();
        if (modelo == null) {
            Avisos.aviso(v, "Selecciona un modelo de la lista.");
            return;
        }
        pModificarModelo.cargarModelo(modelo);
        v.cargarPanel(pModificarModelo);
        actualizarModoClaroOscuro(modoClaro);
    }

    /**
     * Persiste en la base de datos los cambios realizados sobre un modelo
     * desde el panel de modificación.
     * <p>
     * Si la operación se completa correctamente, vuelve al panel de modelos
     * y actualiza su lista. En caso contrario, muestra un mensaje de error.
     */
    private void modificarModelo() {
        ModeloVehiculo modelo = pModificarModelo.getModelo();
        int res = modeloVehiculoDAO.update(modelo);
        if (res > 0) {
            Avisos.info(v, "Modelo modificado correctamente.");
            v.cargarPanel(pNuevoModelo);
            pNuevoModelo.actualizarListaModelos(modeloVehiculoDAO.selectTodos());
            actualizarModoClaroOscuro(modoClaro);
        } else {
            Avisos.error(v, "Error al modificar el modelo.");
        }
    }

    /**
     * Elimina el modelo seleccionado en la lista tras solicitar confirmación.
     * <p>
     * Si el modelo tiene vehículos asociados, la base de datos impedirá
     * la eliminación y se mostrará un mensaje de error. Si no hay ningún
     * modelo seleccionado, muestra un aviso al usuario.
     */
    private void eliminarModelo() {
        ModeloVehiculo modelo = pNuevoModelo.getModeloSeleccionado();
        if (modelo == null) {
            Avisos.aviso(v, "Selecciona un modelo de la lista.");
            return;
        }
        if (!Avisos.confirmar(v, "¿Eliminar el modelo \"" + modelo.getNombreModelo()
                + "\"?\nSi tiene vehículos asociados no podrá eliminarse.")) return;
        int res = modeloVehiculoDAO.delete(modelo.getNombreModelo());
        if (res > 0) {
            pNuevoModelo.actualizarListaModelos(modeloVehiculoDAO.selectTodos());
            Avisos.info(v, "Modelo eliminado correctamente.");
        } else {
            Avisos.error(v, "No se pudo eliminar el modelo.\nPuede que tenga vehículos asociados.");
        }
    }

    /**
     * Valida las credenciales introducidas en el formulario de login
     * contra la base de datos y actualiza el estado de la sesión.
     * <p>
     * Si las credenciales son correctas, activa la sesión y determina
     * si el trabajador tiene permisos de administrador, actualizando
     * la visibilidad del menú en consecuencia. Si son incorrectas,
     * cierra la sesión y muestra un mensaje de error.
     */
    private void login() {
        Login l = pLogin.getLogin();
        Trabajador trabajador = trabajadorDAO.getTrabajadorPorCredenciales(l.getUsuario(), l.getPasswd());
        if (trabajador != null) {
            sesionIniciada = true;
            admin = trabajador.getEsAdmin() == 1;
            Avisos.info(v, "Sesión iniciada como " + trabajador.getNombreApellidos() + ".");
        } else {
            sesionIniciada = false;
            admin = false;
            Avisos.error(v, "Usuario o contraseña incorrectos.");
        }
        v.actualizarMenu(sesionIniciada, admin);
    }

    /**
     * Recupera los modelos de la marca seleccionada en el panel de nuevo
     * vehículo y actualiza su desplegable de modelos.
     */
    private void buscarMarca() {
        String marca = pNuevoVehiculo.getMarca();
        ArrayList<ModeloVehiculo> modelos = modeloVehiculoDAO.selectModeloPorMarca(marca);
        pNuevoVehiculo.actualizarModelos(modelos);
    }

    /**
     * Recupera los modelos de la marca seleccionada en el panel de
     * modificación de vehículo y actualiza su desplegable de modelos.
     */
    private void buscarMarcaModificar() {
        String marca = pModificarVehiculo.getMarca();
        ArrayList<ModeloVehiculo> modelos = modeloVehiculoDAO.selectModeloPorMarca(marca);
        pModificarVehiculo.actualizarModelos(modelos);
    }

    /**
     * Recupera todos los trabajadores de la base de datos y actualiza
     * la tabla del panel de registro de trabajadores.
     */
    private void consultarTrabajadores() {
        pRegistrarTrabajador.cargarTabla(trabajadorDAO.selectAllTrabajadores());
    }

    /**
     * Recoge los datos del formulario de registro de trabajador,
     * los valida e inserta el nuevo trabajador en la base de datos.
     * <p>
     * Si la inserción se completa correctamente, limpia el formulario
     * y refresca la tabla de trabajadores. En caso contrario, muestra
     * un mensaje de error indicando que el nombre puede estar repetido.
     */
    private void registrarTrabajador() {
        Trabajador t = pRegistrarTrabajador.obtenerDatos();
        if (t == null) return;

        int res = trabajadorDAO.insert(t);
        if (res > 0) {
            Avisos.info(v, "Trabajador registrado correctamente.");
            pRegistrarTrabajador.limpiarDatos();
            consultarTrabajadores();
        } else {
            Avisos.error(v, "Error al registrar el trabajador.\nComprueba que el nombre no esté repetido.");
        }
    }

    /**
     * Elimina el trabajador seleccionado en la tabla tras solicitar confirmación.
     * <p>
     * Busca el trabajador por su ID entre todos los registrados,
     * y lo elimina por nombre y apellidos. Si no hay ningún trabajador
     * seleccionado o no se encuentra en la base de datos, muestra un aviso
     * o un mensaje de error según corresponda.
     */
    private void eliminarTrabajador() {
        int id = pRegistrarTrabajador.getIdTrabajadorSeleccionado();
        if (id == -1) {
            Avisos.aviso(v, "Selecciona un trabajador de la tabla.");
            return;
        }
        if (!Avisos.confirmar(v, "¿Eliminar el trabajador seleccionado?")) return;

        ArrayList<Trabajador> todos = trabajadorDAO.selectAllTrabajadores();
        Trabajador seleccionado = null;
        for (Trabajador t : todos) {
            if (t.getIdTrabajador() == id) {
                seleccionado = t;
                break;
            }
        }
        if (seleccionado == null) {
            Avisos.error(v, "No se encontró el trabajador.");
            return;
        }

        int res = trabajadorDAO.delete(seleccionado.getNombreApellidos());
        if (res > 0) {
            Avisos.info(v, "Trabajador eliminado correctamente.");
            consultarTrabajadores();
        } else {
            Avisos.error(v, "No se pudo eliminar el trabajador.");
        }
    }
}

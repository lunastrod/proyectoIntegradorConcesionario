package com.dam.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import com.dam.control.ConcesionarioControlador;

/**
 * Ventana principal de la aplicación del concesionario.
 * <p>
 * Actúa como el contendor principal (que es el JFrame) de la interfaz gráfica y
 * proporciona una barra de menú superior (JMenuBar) para la navegación entre
 * las diferentes secciones de la aplicación, como ver el catálogo, añadir
 * vehículos, modelos, gestionar trabajadores, iniciar sesión y cambiar el tema.
 * <p>
 * Controla la visibilidad de las opciones del menú dinámicamente en función del
 * rol del usuario autenticado (empleado o administrador).
 * @see IVentana
 * @see ConcesionarioControlador
 */
public class VPrincipal extends JFrame implements IVentana {

    /** Título de la ventana principal. */
    private static final String TITULO = "CONCESIONARIO";

    /** Ancho predeterminado de la ventana en píxeles. */
    private static final int ANCHO = 1000;

    /** Alto predeterminado de la ventana en píxeles. */
    private static final int ALTO = 1000;

    /** Texto del ítem de menú para añadir un nuevo vehículo. */
    public static final String NUEVO_VEHICULO_MENU = "Nuevo Vehiculo";

    /** Texto del ítem de menú para visualizar el catálogo de vehículos. */
    public static final String VER_CATALOGO_MENU = "Ver Catalogo";

    /** Texto del ítem de menú para crear un nuevo modelo de vehículo. */
    public static final String NUEVO_MODELO_MENU = "Nuevo Modelo";

    /** Texto de comando para registrar un trabajador. */
    public static final String REGISTRAR_TRABAJADOR_MENU = "Registrar Trabajador";

    /** Texto del ítem de menú para acceder al formulario de inicio de sesión. */
    public static final String LOGIN_MENU = "Login";

    /** Texto del ítem de menú para alternar entre el modo claro y oscuro. */
    public static final String MODO_CLARO_OSCURO_MENU = "Modo claro-oscuro";

    /** Texto del ítem de menú para añadir un nuevo trabajador al sistema. */
    public static final String NUEVO_EMPLEADO = "Nuevo trabajador";
    
    /** Texto del ítem de menú para ver las tablas de clientes y ventas. */
    public static final String VER_TABLAS = "Ver ventas y clientes";

    /** Ítem de menú para la opción de añadir vehículos. */
    private JMenuItem itemNuevoVeh;

    /** Ítem de menú para la opción de registrar un nuevo trabajador. */
    private JMenuItem itemNuevoTra;

    /** Ítem de menú para la opción de ver el catálogo. */
    private JMenuItem itemVer;
    
    /** Ítem de menú para la opción de ver los clientes y ventas. */
    private JMenuItem itemVerTablas;

    /** Ítem de menú para la opción de añadir modelos de vehículos. */
    private JMenuItem itemNuevoMod;

    /** Ítem de menú para la opción de login. */
    private JMenuItem itemLogin;

    /** Ítem de menú para alternar el modo claro/oscuro. */
    private JMenuItem mntmNewMenuItem;

    /**
     * Constructor de la ventana principal.
     * <p>
     * Inicializa la configuración básica de la ventana y construye todos los
     * componentes visuales y menús de navegación iniciales.
     */
    public VPrincipal() {
        configurarVentana();
        crearComponentes();
    }

    /**
     * Configura las propiedades básicas de la ventana principal de Swing.
     * <p>
     * Establece el título del marco, las dimensiones, la posición centrada
     * en la pantalla, el comportamiento de cierre de la aplicación por defecto
     * y anula el gestor de diseño para permitir posicionamiento absoluto.
     */
    @Override
    public void configurarVentana() {
        setTitle(TITULO);
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
    }

    public void cargarPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    /**
     * Crea e inicializa todos los componentes de la barra de menús superior.
     * <p>
     * Construye los elementos interactivos del menú e ítems de navegación
     * y los organiza dentro de la barra de menús principal de la aplicación.
     */
    @Override
    public void crearComponentes() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuEncuestas = new JMenu("Menú");
        menuBar.add(menuEncuestas);

        itemVer = new JMenuItem(VER_CATALOGO_MENU);
        menuEncuestas.add(itemVer);

        itemNuevoVeh = new JMenuItem(NUEVO_VEHICULO_MENU);
        menuEncuestas.add(itemNuevoVeh);
        
        itemNuevoMod = new JMenuItem(NUEVO_MODELO_MENU);
        menuEncuestas.add(itemNuevoMod);
        
        itemNuevoTra = new JMenuItem(NUEVO_EMPLEADO);
        menuEncuestas.add(itemNuevoTra);
        
        itemVerTablas = new JMenuItem(VER_TABLAS);
        menuEncuestas.add(itemVerTablas);

        itemLogin = new JMenuItem(LOGIN_MENU);
        menuEncuestas.add(itemLogin);
        
        mntmNewMenuItem = new JMenuItem(MODO_CLARO_OSCURO_MENU);
        menuBar.add(mntmNewMenuItem);
    }

    /**
     * Actualiza la visibilidad de las opciones del menú según los permisos del usuario.
     * <p>
     * Permite restringir el acceso al añadido de vehículos, modelos, gestión de
     * trabajadores y ver ventas/clientes dependiendo de si el usuario ha iniciado sesión como empleado o
     * si cuenta con privilegios adicionales de administrador.
     * @param empleado true si el usuario actual es un empleado autenticado.
     * @param admin true si el usuario actual posee rol de administrador.
     */
    public void actualizarMenu(boolean empleado, boolean admin) {
        itemNuevoVeh.setVisible(empleado);
        itemNuevoMod.setVisible(empleado);
        itemVerTablas.setVisible(empleado);
        itemNuevoTra.setVisible(empleado && admin);
    }

    /**
     * Asigna el controlador principal como oyente de eventos para los ítems del menú.
     * @param c controlador principal que gestionará los clics de los ítems de menú.
     */
    @Override
    public void setControlador(ConcesionarioControlador c) {
        itemNuevoVeh.addActionListener(c);
        itemVer.addActionListener(c);
        itemNuevoMod.addActionListener(c);
        itemNuevoTra.addActionListener(c);
        itemVerTablas.addActionListener(c);
        itemLogin.addActionListener(c);
        mntmNewMenuItem.addActionListener(c);
    }
    public void hacerVisible() {
        this.setVisible(true);
    }
}
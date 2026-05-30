package com.dam.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.dam.control.ConcesionarioControlador;


public class VPrincipal extends JFrame implements IVentana{

    private static final String TITULO="CONCESIONARIO";
    private static final int ANCHO=1000;
    private static final int ALTO=1000;
    public static final String NUEVO_VEHICULO_MENU="Nuevo Vehiculo";
    public static final String VER_CATALOGO_MENU="Ver Catalogo";
    public static final String NUEVO_MODELO_MENU="Nuevo Modelo";
    public static final String REGISTRAR_TRABAJADOR_MENU="Registrar Trabajador";
    public static final String LOGIN_MENU="Login";
    public static final String MODO_CLARO_OSCURO_MENU="Modo claro-oscuro";


    private JMenuItem itemNuevoVeh;
    private JMenuItem itemVer;
    private JMenuItem itemNuevoMod;
    private JMenuItem itemRegistrarTrabajador;
    private JMenuItem itemLogin;
    private JMenuItem mntmNewMenuItem;
    

    public VPrincipal() {
        configurarVentana();
        crearComponentes();
        
    }

    public void cargarPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        getContentPane().revalidate();
        getContentPane().repaint();
    }


    public void configurarVentana() {
        setTitle(TITULO);
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
    }

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

        itemRegistrarTrabajador = new JMenuItem(REGISTRAR_TRABAJADOR_MENU);
        menuEncuestas.add(itemRegistrarTrabajador);

        itemLogin = new JMenuItem(LOGIN_MENU);
        menuEncuestas.add(itemLogin);
        
        mntmNewMenuItem = new JMenuItem(MODO_CLARO_OSCURO_MENU);
        menuBar.add(mntmNewMenuItem);
    }

    public void actualizarMenu(boolean empleado, boolean admin) {
        if(empleado){
            itemNuevoVeh.setVisible(true);
            itemNuevoMod.setVisible(true);
        }
        else{
            itemNuevoVeh.setVisible(false);
            itemNuevoMod.setVisible(false);
        }

        itemRegistrarTrabajador.setVisible(empleado && admin);
    }

    public void setControlador(ConcesionarioControlador c) {
        itemNuevoVeh.addActionListener(c);
        itemVer.addActionListener(c);
        itemNuevoMod.addActionListener(c);
        itemRegistrarTrabajador.addActionListener(c);
        itemLogin.addActionListener(c);
        mntmNewMenuItem.addActionListener(c);
    }

    public void hacerVisible() {
        this.setVisible(true);
    }
}

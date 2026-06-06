package com.dam.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Vehiculo;

/**
 * Panel que muestra el catálogo de vehículos disponibles en el concesionario.
 * <p>
 * Presenta una cuadrícula de paneles {@link PVehiculo}, uno por cada vehículo,
 * dentro de un área desplazable verticalmente. El catálogo se actualiza
 * dinámicamente llamando a {@link #actualizarPanelesVehiculo(ArrayList)}.
 * @see PVehiculo
 * @see IPanel
 * @see ConcesionarioControlador
 */
public class PVerCatalogo extends JPanel implements IPanel {

    /** Ancho del panel en píxeles. */
    private static final int ANCHO = 1000;

    /** Alto del panel en píxeles. */
    private static final int ALTO = 1000;

    /** Ancho de cada panel de vehículo individual en píxeles. */
    public static final int ANCHO_PANEL_VEHICULO = 160;

    /** Alto de cada panel de vehículo individual en píxeles. */
    public static final int ALTO_PANEL_VEHICULO = 160;

    /** Separación en píxeles entre paneles de vehículo en la cuadrícula. */
    private static final int GAP = 20;

    /** Panel interno que contiene la cuadrícula de paneles de vehículo. */
    private JPanel panelVehiculos;

    /** Área de desplazamiento que envuelve el panel de vehículos. */
    private JScrollPane scrollPane;

    /** Referencia al controlador, necesaria para asignarlo a cada PVehiculo creado. */
    private ConcesionarioControlador controlador;

    /**
     * Crea el panel del catálogo configurando su tamaño y creando sus componentes.
     */
    public PVerCatalogo() {
        setLayout(null);
        setSize(ANCHO, ALTO);
        crearComponentes();
    }

    /**
     * Crea el título del catálogo, el panel interno de vehículos con
     * FlowLayout y el JScrollPane que lo envuelve.
     */
    public void crearComponentes() {
        JLabel lblTitulo = new JLabel("catalogo");
        lblTitulo.setBounds(25, 20, 160, 20);
        add(lblTitulo);

        panelVehiculos = new JPanel(new FlowLayout(FlowLayout.LEFT, GAP, GAP));
        panelVehiculos.setOpaque(false);

        scrollPane = new JScrollPane(panelVehiculos,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 60, 985, 903);
        scrollPane.setBorder(null);
        add(scrollPane);
    }

    /**
     * Actualiza la cuadrícula del catálogo con la lista de vehículos indicada.
     * <p>
     * Elimina todos los paneles anteriores, crea un {@link PVehiculo} por cada
     * vehículo de la lista, le asigna el controlador y recalcula el tamaño
     * preferido del panel interno para que el scroll funcione correctamente.
     *
     * @param vehiculos lista de vehículos a mostrar en el catálogo
     */
    public void actualizarPanelesVehiculo(ArrayList<Vehiculo> vehiculos) {
        panelVehiculos.removeAll();
        for (Vehiculo v : vehiculos) {
            PVehiculo pv = new PVehiculo();
            pv.setControlador(controlador);
            pv.setVehiculo(v);
            panelVehiculos.add(pv);
        }
        int columnas = Math.max(1, ANCHO / (ANCHO_PANEL_VEHICULO + GAP));
        int filas = (int) Math.ceil((double) vehiculos.size() / columnas);
        int alto = filas * (ALTO_PANEL_VEHICULO + GAP) + GAP;
        panelVehiculos.setPreferredSize(new Dimension(ANCHO, alto));
        panelVehiculos.revalidate();
        panelVehiculos.repaint();
    }

    /**
     * Almacena la referencia al controlador para poder asignársela
     * a cada {@link PVehiculo} que se cree al actualizar el catálogo.
     *
     * @param c controlador principal de la aplicación
     */
    public void setControlador(ConcesionarioControlador c) {
        controlador = c;
    }
}
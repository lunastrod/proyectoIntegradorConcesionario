package com.dam.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Vehiculo;

public class PVerCatalogo extends JPanel implements IPanel {
    private static final int ANCHO = 1000;
    private static final int ALTO = 1000;
    public static final int ANCHO_PANEL_VEHICULO = 160;
    public static final int ALTO_PANEL_VEHICULO = 160;
    private static final int GAP = 20;
    private static final int MARGEN_Y = 60;

    private JPanel panelVehiculos;
    private JScrollPane scrollPane;
    ConcesionarioControlador controlador;

    public PVerCatalogo() {
        setLayout(null);
        setSize(ANCHO, ALTO);
        crearComponentes();
    }

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

    public void setControlador(ConcesionarioControlador c) {
        controlador = c;
    }
}
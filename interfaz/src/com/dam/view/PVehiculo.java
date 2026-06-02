package com.dam.view;

import javax.swing.JPanel;
import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Vehiculo;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * Panel compacto que representa un vehículo individual dentro del catálogo.
 * <p>
 * Muestra el modelo, la marca y el precio del vehículo, junto con un botón
 * para acceder a su información detallada. Se usa como tarjeta dentro de
 * la cuadrícula de {@link PVerCatalogo}.
 * <p>
 * El vehículo que representa se asigna mediante {@link #setVehiculo(Vehiculo)}
 * y puede consultarse con {@link #getVehiculoActual()}.
 *
 * @see Vehiculo
 * @see PVerCatalogo
 * @see IPanel
 */
public class PVehiculo extends JPanel implements IPanel {

    /** Comando de acción y texto del botón de más información. */
    public static final String MAS_INFO_BTN = "Más información";

    /** Botón que navega al panel de información detallada del vehículo. */
    JButton btnInfo;

    /** Etiqueta que muestra el precio del vehículo. */
    JLabel lblPrecio;

    /** Etiqueta que muestra la marca y el nombre del modelo del vehículo. */
    JLabel lblModelo;

    /** Vehículo representado actualmente por este panel. */
    Vehiculo vehiculoActual;

    /**
     * Crea el panel de vehículo con sus componentes y dimensiones fijas,
     * aplicando un fondo gris para diferenciarlo visualmente en la cuadrícula.
     */
    public PVehiculo() {
        crearComponentes();
        setSize(PVerCatalogo.ANCHO_PANEL_VEHICULO, PVerCatalogo.ALTO_PANEL_VEHICULO);
        setPreferredSize(new Dimension(PVerCatalogo.ANCHO_PANEL_VEHICULO, PVerCatalogo.ALTO_PANEL_VEHICULO));
        setBackground(new Color(128, 128, 128));
    }

    /**
     * Asigna el vehículo que representa este panel y actualiza
     * las etiquetas de modelo y precio con sus datos.
     *
     * @param vehiculo vehículo cuyos datos se mostrarán en el panel
     */
    public void setVehiculo(Vehiculo vehiculo) {
        vehiculoActual = vehiculo;
        lblPrecio.setText(vehiculo.getPrecio() + " €");
        lblModelo.setText(vehiculo.getModelo().getMarca() + " " + vehiculo.getModelo().getNombreModelo());
    }

    /**
     * Devuelve el vehículo asignado actualmente a este panel.
     * <p>
     * Es usado por el controlador para identificar el vehículo
     * cuyo botón de información ha sido pulsado.
     *
     * @return vehículo actual del panel
     */
    public Vehiculo getVehiculoActual() {
        return vehiculoActual;
    }

    /**
     * Crea e inicializa los componentes del panel: etiqueta de modelo,
     * etiqueta de precio y botón de más información.
     */
    public void crearComponentes() {
        setLayout(null);

        lblPrecio = new JLabel("Precio");
        lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrecio.setBounds(10, 36, 140, 14);
        add(lblPrecio);

        lblModelo = new JLabel("Modelo");
        lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
        lblModelo.setBounds(10, 11, 140, 14);
        add(lblModelo);

        btnInfo = new JButton(MAS_INFO_BTN);
        btnInfo.setBounds(10, 126, 140, 23);
        add(btnInfo);
    }

    /**
     * Registra el controlador como ActionListener del botón de más información.
     *
     * @param c controlador principal de la aplicación
     */
    public void setControlador(ConcesionarioControlador c) {
        btnInfo.addActionListener(c);
    }
}
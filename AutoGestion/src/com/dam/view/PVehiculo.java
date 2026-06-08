package com.dam.view;

import javax.swing.JPanel;
import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Vehiculo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
@SuppressWarnings("serial")
public class PVehiculo extends JPanel implements IPanel {

    /** Comando de acción y texto del botón de más información. */
    public static final String MAS_INFO_BTN = "Más información";
    public static final String RUTA_ICONO = "/img/vehiculo2.png";

    /** Botón que navega al panel de información detallada del vehículo. */
    JButton btnInfo;

    /** Etiqueta que muestra el precio del vehículo. */
    JLabel lblPrecio;

    /** Etiqueta que muestra la marca y el nombre del modelo del vehículo. */
    JLabel lblModelo;

    /** Vehículo representado actualmente por este panel. */
    Vehiculo vehiculoActual;

    /** Imagen del vehículo representado actualmente por este panel. */
    BufferedImage iconoVehiculo;

    /** Icono del vehículo representado actualmente por este panel. */
    JLabel lblIcono;

    /**
     * Crea el panel de vehículo con sus componentes y dimensiones fijas,
     * aplicando un fondo gris para diferenciarlo visualmente en la cuadrícula.
     */
    public PVehiculo() {
        crearComponentes();
        setSize(PVerCatalogo.ANCHO_PANEL_VEHICULO, PVerCatalogo.ALTO_PANEL_VEHICULO);
        setPreferredSize(new Dimension(PVerCatalogo.ANCHO_PANEL_VEHICULO, PVerCatalogo.ALTO_PANEL_VEHICULO));
        //setBackground(new Color(255, 255, 255));
    }

    /**
     * Asigna el vehículo que representa este panel y actualiza
     * las etiquetas de modelo y precio con sus datos.
     * @param vehiculo vehículo cuyos datos se mostrarán en el panel
     */
    public void setVehiculo(Vehiculo vehiculo) {
        vehiculoActual = vehiculo;
        lblPrecio.setText(vehiculo.getPrecio() + " €");
        lblModelo.setText(vehiculo.getModelo().getMarca() + " " + vehiculo.getModelo().getNombreModelo());
        setIconoVehiculo(vehiculo.getColorParsed());
    }

    /**
     * Devuelve el vehículo asignado actualmente a este panel.
     * <p>
     * Es usado por el controlador para identificar el vehículo
     * cuyo botón de información ha sido pulsado.
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

        lblIcono = new JLabel();
        
        URL imgURL = getClass().getResource(RUTA_ICONO);
        try{
            iconoVehiculo = ImageIO.read(imgURL);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcono.setBounds(10, 61, 140, 54);
        add(lblIcono);
    }

    public void setIconoVehiculo(Color color) {
        lblIcono.setIcon(teñirIcono(iconoVehiculo, color));
    }

    public static ImageIcon teñirIcono(BufferedImage original, Color color) {
        try {
            BufferedImage teñida = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < original.getWidth(); x++) {
                for (int y = 0; y < original.getHeight(); y++) {
                    int pixel = original.getRGB(x, y);
                    Color colorPixel = new Color(pixel, true);
                    int alpha = colorPixel.getAlpha();
                    int rojo = colorPixel.getRed();
                    int verde = colorPixel.getGreen();
                    int azul = colorPixel.getBlue();
                    if (rojo == 0 && verde == 0 && azul == 0) {
                        teñida.setRGB(x, y, (alpha << 24) | (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue());
                    } else {
                        teñida.setRGB(x, y, pixel);
                    }
                }
            }
            return new ImageIcon(teñida);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Registra el controlador como ActionListener del botón de más información.
     * @param c controlador principal de la aplicación
     */
    public void setControlador(ConcesionarioControlador c) {
        btnInfo.addActionListener(c);
    }
}
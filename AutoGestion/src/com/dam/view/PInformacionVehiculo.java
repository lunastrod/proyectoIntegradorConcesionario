package com.dam.view;

import javax.swing.JPanel;
import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Cliente;
import com.dam.model.data.Trabajador;
import com.dam.model.data.Vehiculo;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Panel que muestra la información detallada de un vehículo
 * y permite al usuario iniciar el proceso de compra del mismo.
 * <p>
 * Nos enseña el modelo, el precio y las especificaciones técnicas
 * del vehículo seleccionado del catálogo. Contiene un subpanel
 * de compra oculto por defecto que se muestra al pulsar el botón
 * de comprar, permitiendo introducir los datos del cliente,
 * el trabajador que nos atiende la venta y el método de pago.
 * @see Vehiculo
 * @see Cliente
 * @see Trabajador
 * @see IPanel
 * @see ConcesionarioControlador
 */
@SuppressWarnings("serial")
public class PInformacionVehiculo extends JPanel implements IPanel {
    /** Comando de acción del botón que muestra u oculta el panel de compra. */
    public static final String COMPRAR_BTN = "Comprar";

    /** Comando de acción del botón que confirma y registra la compra. */
    public static final String REALIZAR_COMPRA_BTN = "Realizar Compra";

    /** Comando de acción del botón que vuelve al catálogo. */
    public static final String VOLVER_CATALOGO_BTN = "Volver Catalogo";

    /** Etiqueta que muestra el nombre del modelo y la marca del vehículo. */
    JLabel lblPrecio;

    /** Etiqueta que muestra el precio del vehículo en euros. */
    JLabel lblModelo;

    /** Área de texto no editable con las especificaciones técnicas del vehículo. */
    JTextArea textAreaEspecificaciones;

    /** Botón que alterna la visibilidad del panel de compra. */
    private JButton btnComprar;

    /** Botón que vuelve al catálogo de vehículos. */
    private JButton btnVolver;

    /** Vehículo cuya información se está mostrando actualmente. */
    Vehiculo vehiculoActual;

    /** Campo de texto para introducir el nombre y apellidos del cliente. */
    private JTextField txtNombreCliente;

    /** Modelo del desplegable con la lista de trabajadores disponibles. */
    private DefaultComboBoxModel<Trabajador> modelTrabajadores;

    /** Desplegable para seleccionar el trabajador que atiende la venta. */
    private JComboBox<Trabajador> cbTrabajador;

    /** Botón que confirma y registra la compra del vehículo. */
    private JButton btnRealizarCompra;

    /** Campo de texto para introducir el método de pago del cliente. */
    private JTextField txtMetodoPago;

    /** Subpanel que agrupa los campos del formulario de compra. */
    private JPanel panelCompra;
    private BufferedImage iconoVehiculo;
    private JLabel lblIcono;

    /**
     * Crea el panel de información del vehículo e inicializa todos sus componentes.
     */
    public PInformacionVehiculo() {
        crearComponentes();
        setSize(VPrincipal.ANCHO, VPrincipal.ALTO);
    }

    /**
     * Crea e inicializa todos los componentes visuales del panel,
     * incluyendo las etiquetas de modelo y precio, el área de especificaciones
     * técnicas, el botón de comprar y el subpanel de compra con su formulario.
     */
    public void crearComponentes() {
        setLayout(null);

        lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(54, 58, 280, 14);
        add(lblModelo);

        lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(54, 83, 116, 14);
        add(lblPrecio);

        lblIcono = new JLabel();
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcono.setBounds(342, 58, 205, 45);
        add(lblIcono);

        URL imgURL = getClass().getResource(PVehiculo.RUTA_ICONO);
        try {
            iconoVehiculo = ImageIO.read(imgURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel lblEspecificaciones = new JLabel("Especificaciones Técnicas");
        lblEspecificaciones.setBounds(54, 158, 205, 14);
        add(lblEspecificaciones);

        textAreaEspecificaciones = new JTextArea();
        textAreaEspecificaciones.setEditable(false);
        textAreaEspecificaciones.setBounds(54, 183, 495, 556);
        add(textAreaEspecificaciones);

        btnComprar = new JButton(COMPRAR_BTN);
        btnComprar.setActionCommand(COMPRAR_BTN);
        btnComprar.setBounds(245, 58, 89, 23);
        add(btnComprar);

        btnVolver = new JButton("Volver al catálogo");
        btnVolver.setActionCommand(VOLVER_CATALOGO_BTN);
        btnVolver.setBounds(245, 88, 150, 23);
        add(btnVolver);

        panelCompra = new JPanel(null);
        panelCompra.setBounds(580, 40, 380, 200);
        panelCompra.setVisible(false);
        add(panelCompra);

        JLabel lblNombreCliente = new JLabel("Nombre y Apellidos");
        lblNombreCliente.setBounds(0, 18, 150, 20);
        panelCompra.add(lblNombreCliente);

        txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(155, 18, 150, 25);
        panelCompra.add(txtNombreCliente);

        JLabel lblNombreEmpleado = new JLabel("Atendido por");
        lblNombreEmpleado.setBounds(0, 54, 150, 20);
        panelCompra.add(lblNombreEmpleado);

        modelTrabajadores = new DefaultComboBoxModel<Trabajador>();
        cbTrabajador = new JComboBox<Trabajador>(modelTrabajadores);
        cbTrabajador.setBounds(155, 54, 150, 25);
        panelCompra.add(cbTrabajador);

        JLabel lblMetodoPago = new JLabel("Método de pago");
        lblMetodoPago.setBounds(0, 90, 150, 20);
        panelCompra.add(lblMetodoPago);

        txtMetodoPago = new JTextField();
        txtMetodoPago.setBounds(155, 90, 150, 25);
        panelCompra.add(txtMetodoPago);

        btnRealizarCompra = new JButton(REALIZAR_COMPRA_BTN);
        btnRealizarCompra.setActionCommand(REALIZAR_COMPRA_BTN);
        btnRealizarCompra.setBounds(155, 135, 150, 25);
        panelCompra.add(btnRealizarCompra);
    }

    /**
     * Actualiza el desplegable de trabajadores con la lista proporcionada.
     * <p>
     * Se llama antes de mostrar el panel para asegurarse de que
     * la lista de trabajadores está actualizada con los datos de la base de datos.
     * @param trabajadores lista de trabajadores a mostrar en el desplegable
     */
    public void actualizarTrabajadores(ArrayList<Trabajador> trabajadores) {
        modelTrabajadores.removeAllElements();
        for (Trabajador t : trabajadores) {
            modelTrabajadores.addElement(t);
        }
    }

    /**
     * Carga los datos del vehículo indicado en el panel y oculta
     * el formulario de compra.
     * <p>
     * Actualiza las etiquetas de modelo y precio, rellena el área de
     * especificaciones técnicas y limpia los campos del formulario de compra.
     * @param v vehículo cuya información se desea mostrar al usuario.
     */
    public void mostrarInfoVehiculo(Vehiculo v) {
        vehiculoActual = v;
        lblModelo.setText(v.getModelo().getMarca() + " " + v.getModelo().getNombreModelo());
        lblPrecio.setText(v.getPrecio() + " €");
        setIconoVehiculo(v);
        textAreaEspecificaciones.setText(v.getEspecificacionesTecnicas());
        panelCompra.setVisible(false);
        limpiarFormularioCompra();
    }

    private void setIconoVehiculo(Vehiculo vehiculo) {
        lblIcono.setIcon(PVehiculo.teñirIcono(iconoVehiculo, vehiculo.getColorParsed()));
    }

    /**
     * Vacia los campos del formulario de compra.
     * <p>
     * Se llama al cargar un nuevo vehículo para evitar que queden
     * datos de una compra anterior.
     */
    private void limpiarFormularioCompra() {
        txtNombreCliente.setText("");
        txtMetodoPago.setText("");
    }

    /**
     * Alterna la visibilidad del subpanel de compra.
     * <p>
     * Si el panel de compra estaba oculto, lo muestra; si estaba visible, lo oculta.
     * Se invoca al pulsar el botón de comprar.
     */
    public void togglePanelCompra() {
        panelCompra.setVisible(!panelCompra.isVisible());
    }

    /**
     * Devuelve el vehículo que se está mostrando actualmente en el panel.
     * @return vehículo actual
     */
    public Vehiculo getVehiculoActual() {
        return vehiculoActual;
    }

    /**
     * Construye y devuelve un objeto {@link Cliente} con los datos
     * introducidos en el formulario de compra.
     * <p>
     * El ID se establece a -1 ya que el cliente aún
     * no ha tenido persistencia en la base de datos.
     * @return cliente con los datos del formulario
     */
    public Cliente getCliente() {
        return new Cliente(-1, txtNombreCliente.getText(), txtMetodoPago.getText());
    }

    /**
     * Devuelve el trabajador seleccionado en el desplegable del formulario de compra.
     * @return trabajador seleccionado, o null si el desplegable está vacío
     */
    public Trabajador getTrabajadorSeleccionado() {
        return (Trabajador) modelTrabajadores.getSelectedItem();
    }

    /**
     * Registra el controlador como ActionListener de los botones
     * de comprar y de realizar compra.
     * @param c controlador principal de la aplicación
     */
    public void setControlador(ConcesionarioControlador c) {
        btnComprar.addActionListener(c);
        btnVolver.addActionListener(c);
        btnRealizarCompra.addActionListener(c);
    }
}

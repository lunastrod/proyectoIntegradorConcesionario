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

import java.util.ArrayList;

public class PInformacionVehiculo extends JPanel implements IPanel {
    private static final int ANCHO = 1000;
    private static final int ALTO = 1000;
    public static final String COMPRAR_BTN = "Comprar";
    public static final String REALIZAR_COMPRA_BTN = "Realizar Compra";

    JLabel lblPrecio;
    JLabel lblModelo;
    JTextArea textAreaEspecificaciones;
    private JButton btnComprar;
    Vehiculo vehiculoActual;
    private JTextField txtNombreCliente;
    private DefaultComboBoxModel<Trabajador> modelTrabajadores;
    private JComboBox<Trabajador> cbTrabajador;
    private JButton btnRealizarCompra;
    private JTextField txtMetodoPago;
    private JPanel panelCompra;

    public PInformacionVehiculo() {
        crearComponentes();
        setSize(ANCHO, ALTO);
    }

    public void crearComponentes() {
        setLayout(null);

        lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(54, 58, 280, 14);
        add(lblModelo);

        lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(54, 83, 116, 14);
        add(lblPrecio);

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

    public void actualizarTrabajadores(ArrayList<Trabajador> trabajadores) {
        modelTrabajadores.removeAllElements();
        for (Trabajador t : trabajadores) {
            modelTrabajadores.addElement(t);
        }
    }

    public void mostrarInfoVehiculo(Vehiculo v) {
        vehiculoActual = v;
        lblModelo.setText(v.getModelo().getMarca() + " " + v.getModelo().getNombreModelo());
        lblPrecio.setText(v.getPrecio() + " €");
        textAreaEspecificaciones.setText(v.getEspecificacionesTecnicas());
        panelCompra.setVisible(false);
        limpiarFormularioCompra();
    }

    private void limpiarFormularioCompra() {
        txtNombreCliente.setText("");
        txtMetodoPago.setText("");
    }

    public void togglePanelCompra() {
        panelCompra.setVisible(!panelCompra.isVisible());
    }

    public Vehiculo getVehiculoActual() {
        return vehiculoActual;
    }

    public Cliente getCliente() {
        return new Cliente(-1, txtNombreCliente.getText(), txtMetodoPago.getText());
    }

    /** Devuelve el trabajador seleccionado en el combo, o null si está vacío. */
    public Trabajador getTrabajadorSeleccionado() {
        return (Trabajador) modelTrabajadores.getSelectedItem();
    }

    public void setControlador(ConcesionarioControlador c) {
        btnComprar.addActionListener(c);
        btnRealizarCompra.addActionListener(c);
    }
}
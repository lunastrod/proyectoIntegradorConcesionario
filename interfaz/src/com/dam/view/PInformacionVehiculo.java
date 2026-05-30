package com.dam.view;

import javax.swing.JPanel;

import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Cliente;
import com.dam.model.data.Vehiculo;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PInformacionVehiculo extends JPanel implements IPanel{
    private static final int ANCHO=1000;
    private static final int ALTO=1000;
    public static final String COMPRAR_BTN="Comprar";
    public static final String REALIZAR_COMPRA_BTN="Realizar Compra";
    JLabel lblPrecio;
    JLabel lblModelo;
    JTextArea textAreaEspecificaciones;
    private JButton btnComprar;
    Vehiculo vehiculoActual;
    private JTextField txtNombreCliente;
    private JTextField txtNombreTrabajador;
    private JButton btnRealizarCompra;
    private JTextField txtMetodoPago;

    public PInformacionVehiculo() {
        crearComponentes();
        setSize(ANCHO,ALTO);
    }

    public void crearComponentes() {
        setLayout(null);

        btnComprar = new JButton(COMPRAR_BTN);
        btnComprar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnComprar.setActionCommand(COMPRAR_BTN);
        btnComprar.setBounds(245, 58, 89, 23);
        add(btnComprar);
        
        JLabel lblNombreCliente = new JLabel("Nombre y Apellidos");
        lblNombreCliente.setBounds(592, 58, 160, 20);
        add(lblNombreCliente);
        
        txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(697, 58, 150, 25);
        add(txtNombreCliente);
        
        txtNombreTrabajador = new JTextField();
        txtNombreTrabajador.setBounds(697, 104, 150, 25);
        add(txtNombreTrabajador);
        
        JLabel lblNombreEmpleado = new JLabel("Atendido por");
        lblNombreEmpleado.setBounds(592, 104, 160, 20);
        add(lblNombreEmpleado);
        
        JLabel lblMetodoPago = new JLabel("Método de pago");
        lblMetodoPago.setBounds(592, 142, 160, 20);
        add(lblMetodoPago);
        
        btnRealizarCompra = new JButton(REALIZAR_COMPRA_BTN);
        btnRealizarCompra.setBounds(697, 178, 150, 25);
        add(btnRealizarCompra);
        
        txtMetodoPago = new JTextField();
        txtMetodoPago.setBounds(697, 140, 150, 25);
        add(txtMetodoPago);

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
        textAreaEspecificaciones.setBounds(54, 183, 495, 556);
        add(textAreaEspecificaciones);
    }

    public void mostrarInfoVehiculo(Vehiculo v) {
        System.out.println("mostrarInfoVehiculo");
        vehiculoActual=v;
        lblModelo.setText(v.getModelo().getMarca()+" "+v.getModelo().getNombreModelo());
        lblPrecio.setText(v.getPrecio()+"€");
        textAreaEspecificaciones.setText(v.getEspecificacionesTecnicas());
    }

    public Vehiculo getVehiculoActual() {
        return vehiculoActual;
    }

    public Cliente getCliente() {
        return new Cliente(-1, txtNombreCliente.getText(), txtMetodoPago.getText());
    }

    public String getNombreTrabajador() {
        return txtNombreTrabajador.getText();
    }

    public void setControlador(ConcesionarioControlador c) {
        btnComprar.addActionListener(c);
    }
}

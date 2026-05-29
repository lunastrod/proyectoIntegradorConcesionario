package com.dam.view;

import javax.swing.JPanel;

import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Vehiculo;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;


public class PInformacionVehiculo extends JPanel implements IPanel{
    private static final int ANCHO=1000;
    private static final int ALTO=1000;
    JLabel lblPrecio;
    JLabel lblModelo;
    JTextArea textAreaEspecificaciones;

    public PInformacionVehiculo() {
        crearComponentes();
        setSize(ANCHO,ALTO);
    }

    public void crearComponentes() {
        setLayout(null);
        lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(54, 58, 116, 14);
        add(lblModelo);
        
        lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(54, 83, 116, 14);
        add(lblPrecio);
        
        JLabel lblEspecificaciones = new JLabel("Especificaciones Técnicas");
        lblEspecificaciones.setBounds(54, 158, 205, 14);
        add(lblEspecificaciones);
        
        textAreaEspecificaciones = new JTextArea();
        textAreaEspecificaciones.setBounds(54, 183, 729, 556);
        add(textAreaEspecificaciones);
    }

    public void mostrarInfoVehiculo(Vehiculo v) {
        lblModelo.setText(v.getModelo().getMarca()+" "+v.getModelo().getNombreModelo());
        lblPrecio.setText(v.getPrecio()+"€");
        textAreaEspecificaciones.setText(v.getEspecificacionesTecnicas());
    }

    public void setControlador(ConcesionarioControlador c) {
        // TODO Auto-generated method stub
        
    }


}
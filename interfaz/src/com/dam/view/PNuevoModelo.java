package com.dam.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dam.control.ConcesionarioControlador;

public class PNuevoModelo extends JPanel implements IPanel{
    private static final int ANCHO=600;
    private static final int ALTO=400;
    public static final String GUARDAR_MODELO_BTN="Guardar Modelo";
    JButton btnGuardar;

    public PNuevoModelo(){
        setLayout(null);
        setSize(ANCHO,ALTO);
        crearComponentes();
    }

    public void crearComponentes(){
        JLabel lblTitulo = new JLabel("Nuevo Vehículo");
        lblTitulo.setBounds(25, 20, 160, 20);
        add(lblTitulo);

        //escribir el nombre del modelo
        JLabel lblNombreModelo = new JLabel("Modelo:");
        lblNombreModelo.setBounds(25, 60, 160, 20);
        add(lblNombreModelo);
        JTextField tfNombreModelo = new JTextField();
        tfNombreModelo.setBounds(130, 60, 150, 25);
        add(tfNombreModelo);

        //escribir el nombre de la marca
        JLabel lblNombreMarca = new JLabel("Marca:");
        lblNombreMarca.setBounds(25, 100, 160, 20);
        add(lblNombreMarca);
        JTextField tfNombreMarca = new JTextField();
        tfNombreMarca.setBounds(130, 100, 150, 25);
        add(tfNombreMarca);

        //boton guardar
        btnGuardar=new JButton(GUARDAR_MODELO_BTN);
        btnGuardar.setActionCommand(GUARDAR_MODELO_BTN);
        btnGuardar.setBounds(130, 180, 150, 25);
        add(btnGuardar);
    }

    public void setControlador(ConcesionarioControlador c){
        btnGuardar.addActionListener(c);
    }
}

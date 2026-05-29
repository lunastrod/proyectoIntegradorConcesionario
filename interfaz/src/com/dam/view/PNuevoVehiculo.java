package com.dam.view;


import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Vehiculo;
import javax.swing.JTextField;

/*
CREATE TABLE Marca (
nombre_marca varchar(50) PRIMARY KEY
);

CREATE TABLE Modelo (
id_modelo INT PRIMARY KEY,
nombre_modelo varchar(80),
nombre_marca varchar(50),
foreign key (nombre_marca) references Marca(nombre_marca)
);

CREATE TABLE Vehiculo (
id_vehiculo INT PRIMARY KEY,
modelo int,
foreign key (modelo) references Modelo(id_modelo),
precio int
);
*/

public class PNuevoVehiculo extends JPanel implements IPanel{
    private static final int ANCHO=1000;
    private static final int ALTO=1000;
    public static final String GUARDAR_VEHICULO_BTN="Guardar Vehiculo";
    public static final String VER_COLOR_BTN="Ver color";

    JButton btnGuardar;
    DefaultComboBoxModel<String> modelMarcas;
    JComboBox<String> cbMarca;
    DefaultComboBoxModel<String> modelModelos;
    JComboBox<String> cbModelo;
    JSpinner spPrecio;
    private JTextField txtMatricula;
    private JSpinner spRojo;
    private JSpinner spVerde;
    private JSpinner spAzul;
    private JSpinner spYear;
    private JSpinner spKilometraje;
    private JSpinner spPotencia;
    private JSpinner spCilindrada;
    private JSpinner spPeso;
    private JButton btnVerColor;
    private JLabel lblModelo;
    private JLabel lblMarca;
    private JLabel lblPrecio;
    

    public PNuevoVehiculo(){
        setLayout(null);
        setSize(ANCHO,ALTO);
        crearComponentes();
    }

    public Vehiculo getVehiculo(){
        String marca=(String)cbMarca.getSelectedItem();
        String modelo=(String)cbModelo.getSelectedItem();
        int precio=(int)spPrecio.getValue();

        ModeloVehiculo m=new ModeloVehiculo(1,modelo,marca);

        return new Vehiculo(1,m,precio);
    }

    public void actualizarMarcas(String [] marcas){
        modelMarcas.removeAllElements();
        for(String marca:marcas){
            modelMarcas.addElement(marca);
        }
    }

    public void actualizarModelos(String [] modelos){
        modelModelos.removeAllElements();
        for(String modelo:modelos){
            modelModelos.addElement(modelo);
        }
    }

    public void crearComponentes(){
        JLabel lblTitulo = new JLabel("Nuevo Vehiculo");
        lblTitulo.setBounds(80, 26, 160, 20);
        add(lblTitulo);

        //seleccionar la marca con una lista desplegable
        modelMarcas=new DefaultComboBoxModel<>();
        cbMarca=new JComboBox<String>(modelMarcas);
        cbMarca.setBounds(185, 103, 150, 25);
        add(cbMarca);

        //seleccionar el modelo con una lista desplegable
        modelModelos=new DefaultComboBoxModel<>();
        cbModelo=new JComboBox<String>(modelModelos);
        cbModelo.setBounds(185, 143, 150, 25);
        add(cbModelo);

        //ingresar el precio con un spinner
        spPrecio=new JSpinner(new SpinnerNumberModel(20000, 0, 1000000, 1000));
        spPrecio.setBounds(185, 183, 150, 25);
        add(spPrecio);

        //boton guardar
        btnGuardar=new JButton(GUARDAR_VEHICULO_BTN);
        btnGuardar.setActionCommand(GUARDAR_VEHICULO_BTN);
        btnGuardar.setBounds(80, 436, 150, 25);
        add(btnGuardar);
        
        txtMatricula = new JTextField();
        txtMatricula.setBounds(185, 219, 86, 20);
        add(txtMatricula);
        txtMatricula.setColumns(10);
        
        spYear = new JSpinner();
        spYear.setBounds(185, 281, 86, 20);
        add(spYear);
        
        spKilometraje = new JSpinner();
        spKilometraje.setBounds(185, 312, 86, 20);
        add(spKilometraje);
        
        spPotencia = new JSpinner();
        spPotencia.setBounds(185, 343, 86, 20);
        add(spPotencia);
        
        spCilindrada = new JSpinner();
        spCilindrada.setBounds(185, 374, 86, 20);
        add(spCilindrada);
        
        spPeso = new JSpinner();
        spPeso.setBounds(185, 405, 86, 20);
        add(spPeso);
        
        JLabel lblMatricula = new JLabel("Matrícula");
        lblMatricula.setBounds(80, 222, 95, 14);
        add(lblMatricula);
        
        JLabel lblColor = new JLabel("Color");
        lblColor.setBounds(80, 253, 95, 14);
        add(lblColor);
        
        JLabel lblYear = new JLabel("Año");
        lblYear.setBounds(80, 284, 95, 14);
        add(lblYear);
        
        JLabel lblKilometraje = new JLabel("Kilometraje");
        lblKilometraje.setBounds(80, 315, 95, 14);
        add(lblKilometraje);
        
        JLabel lblPotencia = new JLabel("Potencia");
        lblPotencia.setBounds(80, 346, 95, 14);
        add(lblPotencia);
        
        JLabel lblCilindrada = new JLabel("Cilindrada");
        lblCilindrada.setBounds(80, 377, 95, 14);
        add(lblCilindrada);
        
        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(80, 408, 95, 14);
        add(lblPeso);
        
        spRojo = new JSpinner();
        spRojo.setModel(new SpinnerNumberModel(0, 0, 255, 15));
        spRojo.setBounds(185, 250, 55, 20);
        add(spRojo);
        
        spVerde = new JSpinner(new SpinnerNumberModel(0, 0, 255, 15));
        spVerde.setBounds(250, 250, 55, 20);
        add(spVerde);
        
        spAzul = new JSpinner(new SpinnerNumberModel(0, 0, 255, 15));
        spAzul.setBounds(315, 250, 55, 20);
        add(spAzul);
        
        btnVerColor = new JButton(VER_COLOR_BTN);
        btnVerColor.setBounds(380, 249, 89, 23);
        add(btnVerColor);
        
        lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(80, 148, 95, 14);
        add(lblModelo);
        
        lblMarca = new JLabel("Marca");
        lblMarca.setBounds(80, 108, 95, 14);
        add(lblMarca);
        
        lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(80, 188, 95, 14);
        add(lblPrecio);

        actualizarColor();
    }

    public void actualizarColor(){
        int rojo=(int) spRojo.getValue();
        int verde=(int) spVerde.getValue();
        int azul=(int) spAzul.getValue();
        btnVerColor.setBackground(new Color(rojo, verde, azul));
    }

    public void setControlador(ConcesionarioControlador c){
        btnGuardar.addActionListener(c);
        btnVerColor.addActionListener(c);
    }


}
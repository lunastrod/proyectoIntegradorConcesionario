package com.dam.view;


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
    private static final int ANCHO=600;
    private static final int ALTO=400;
    public static final String GUARDAR_VEHICULO_BTN="Guardar Vehiculo";

    JButton btnGuardar;
    DefaultComboBoxModel<String> modelMarcas;
    JComboBox<String> cbMarca;
    DefaultComboBoxModel<String> modelModelos;
    JComboBox<String> cbModelo;
    JSpinner spPrecio;
    

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
        lblTitulo.setBounds(25, 20, 160, 20);
        add(lblTitulo);

        //seleccionar la marca con una lista desplegable
        modelMarcas=new DefaultComboBoxModel<>();
        cbMarca=new JComboBox<String>(modelMarcas);
        cbMarca.setBounds(130, 60, 150, 25);
        add(cbMarca);

        //seleccionar el modelo con una lista desplegable
        modelModelos=new DefaultComboBoxModel<>();
        cbModelo=new JComboBox<String>(modelModelos);
        cbModelo.setBounds(130, 100, 150, 25);
        add(cbModelo);

        //ingresar el precio con un spinner
        spPrecio=new JSpinner(new SpinnerNumberModel(20000, 0, 1000000, 1000));
        spPrecio.setBounds(130, 140, 150, 25);
        add(spPrecio);

        //boton guardar
        btnGuardar=new JButton(GUARDAR_VEHICULO_BTN);
        btnGuardar.setActionCommand(GUARDAR_VEHICULO_BTN);
        btnGuardar.setBounds(130, 180, 150, 25);
        add(btnGuardar);
    }

    public void setControlador(ConcesionarioControlador c){
        btnGuardar.addActionListener(c);
    }

}
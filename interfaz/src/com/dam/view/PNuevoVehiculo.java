package com.dam.view;

import javax.swing.*;

import com.dam.control.ConcesionarioControlador;

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
    public static final String GUARDAR_VEHICULO_BTN="Guardar Vehículo";

    JButton btnGuardar;

    public PNuevoVehiculo(){
        setLayout(null);
        setSize(ANCHO,ALTO);
        crearComponentes();
    }

    public void crearComponentes(){
        JLabel lblTitulo = new JLabel("Nuevo Vehículo");
        lblTitulo.setBounds(25, 20, 160, 20);
        add(lblTitulo);

        //TODO:TEMPORAL MARCAS
        String[] marcas={"Toyota","Honda","Ford"};

        //seleccionar la marca con una lista desplegable
        JComboBox<String> cbMarca=new JComboBox<String>(marcas);
        cbMarca.setBounds(130, 60, 150, 25);
        add(cbMarca);

        //TODO:TEMPORAL MODELOS
        String[] modelos={"Corolla","Civic","Mustang"};

        //seleccionar el modelo con una lista desplegable
        JComboBox<String> cbModelo=new JComboBox<String>(modelos);
        cbModelo.setBounds(130, 100, 150, 25);
        add(cbModelo);

        //ingresar el precio con un spinner
        JSpinner spPrecio=new JSpinner(new SpinnerNumberModel(20000, 0, 1000000, 1000));
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
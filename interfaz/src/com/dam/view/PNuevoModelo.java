package com.dam.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.ModeloVehiculo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PNuevoModelo extends JPanel implements IPanel{
    private static final int ANCHO=1000;
    private static final int ALTO=1000;
    public static final String GUARDAR_MODELO_BTN="Guardar Modelo";
    JButton btnGuardar;
    JTextField tfNombreModelo;
    JTextField tfNombreMarca;
    private JLabel lblNombreMarca_1;
    private JLabel lblNombreMarca_2;
    private JLabel lblNombreMarca_3;

    public PNuevoModelo(){
        setLayout(null);
        setSize(ANCHO,ALTO);
        crearComponentes();
    }

    /*    public ModeloVehiculo(int idModelo, String nombreModelo, int numeroPlazas, int numeroPuertas, String tipoModelo, String tipoPropulsion,
			String traccion, String marca, String tipoTransmision) { */

    public ModeloVehiculo getModeloVehiculo(){
        String nombreModelo=tfNombreModelo.getText();
        String nombreMarca=tfNombreMarca.getText();
        int plazas=(int)spPlazas.getValue();
        int puertas=(int)spPuertas.getValue();
        String propulsion=cbPropulsion.getSelectedItem().toString();
        String traccion=cbTraccion.getSelectedItem().toString();
        //ModeloVehiculo mPrueba=new ModeloVehiculo(1,"laferrari",2,3,"gasolina","trasera","Ferrari","manual");
        ModeloVehiculo mPrueba=
        return mPrueba;
    }

    public void crearComponentes(){
        JLabel lblTitulo = new JLabel("Nuevo Vehiculo");
        lblTitulo.setBounds(25, 20, 160, 20);
        add(lblTitulo);

        //escribir el nombre del modelo
        JLabel lblNombreModelo = new JLabel("Modelo:");
        lblNombreModelo.setBounds(25, 60, 160, 20);
        add(lblNombreModelo);
        tfNombreModelo = new JTextField();
        tfNombreModelo.setBounds(195, 60, 150, 25);
        add(tfNombreModelo);

        //escribir el nombre de la marca
        JLabel lblNombreMarca = new JLabel("Marca:");
        lblNombreMarca.setBounds(25, 100, 160, 20);
        add(lblNombreMarca);
        tfNombreMarca = new JTextField();
        tfNombreMarca.setBounds(195, 100, 150, 25);
        add(tfNombreMarca);

        //boton guardar
        btnGuardar=new JButton(GUARDAR_MODELO_BTN);
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnGuardar.setActionCommand(GUARDAR_MODELO_BTN);
        btnGuardar.setBounds(25, 425, 150, 25);
        add(btnGuardar);
        
        lblNombreMarca_1 = new JLabel("Tipo de propulsión");
        lblNombreMarca_1.setBounds(25, 136, 160, 20);
        add(lblNombreMarca_1);
        
        lblNombreMarca_2 = new JLabel("Tracción");
        lblNombreMarca_2.setBounds(25, 172, 160, 20);
        add(lblNombreMarca_2);
        
        lblNombreMarca_3 = new JLabel("Tipo de transmisión");
        lblNombreMarca_3.setBounds(25, 208, 160, 20);
        add(lblNombreMarca_3);
        
        JComboBox<String> comboBoxPropulsion = new JComboBox<String>();
        DefaultComboBoxModel<String> modelPropulsion= new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_PROPULSION);
        comboBoxPropulsion.setModel(modelPropulsion);
        comboBoxPropulsion.setBounds(195, 135, 150, 22);
        add(comboBoxPropulsion);
        
        JComboBox<String> comboBoxTransmision = new JComboBox<String>();
        DefaultComboBoxModel<String> modelTransmision= new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_TRANSMISION);
        comboBoxTransmision.setModel(modelTransmision);
        comboBoxTransmision.setBounds(195, 207, 150, 22);
        add(comboBoxTransmision);
        
        JComboBox<String> comboBoxTraccion = new JComboBox<String>();
        DefaultComboBoxModel<String> modelTraccion= new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_TRACCION);
        comboBoxTraccion.setModel(modelTraccion);
        comboBoxTraccion.setBounds(195, 171, 150, 22);
        add(comboBoxTraccion);
        
        JLabel lblNombreMarca_3_1 = new JLabel("Tipo de vehículo");
        lblNombreMarca_3_1.setBounds(25, 239, 160, 20);
        add(lblNombreMarca_3_1);
        
        JComboBox<String> comboBoxVehiculo = new JComboBox<String>();
        DefaultComboBoxModel<String> modelVehiculo= new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_VEHICULOS);
        comboBoxVehiculo.setModel(modelVehiculo);
        comboBoxVehiculo.setBounds(195, 238, 150, 22);
        add(comboBoxVehiculo);
    }

    public void setControlador(ConcesionarioControlador c){
        btnGuardar.addActionListener(c);
    }



}

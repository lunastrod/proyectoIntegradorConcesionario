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
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JList;

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
    private JComboBox<String> comboBoxPropulsion;
    private JComboBox<String> comboBoxTraccion;
    private JComboBox<String> comboBoxTransmision;
    private JComboBox<String> comboBoxVehiculo;

    private DefaultComboBoxModel<String> modelPropulsion;
    private DefaultComboBoxModel<String> modelTraccion;
    private DefaultComboBoxModel<String> modelTransmision;
    private DefaultComboBoxModel<String> modelVehiculo;
    private JSpinner spinnerPlazas;
    private JSpinner spinnerPuertas;
    private JScrollPane scrollPane;
    private JButton btnEliminarModelo;
    private JButton btnModificarModelo;
    private JList listModelos;

    public PNuevoModelo(){
        setLayout(null);
        setSize(ANCHO,ALTO);
        crearComponentes();
    }

    /*    public ModeloVehiculo(int idModelo, String nombreModelo, int numeroPlazas, int numeroPuertas, String tipoModelo, String tipoPropulsion,
			String traccion, String marca, String tipoTransmision) { */

            /*

    private String nombreModelo;
    private int numeroPlazas;
    private int numeroPuertas;
    private String tipoModelo;
    private String tipoPropulsion;
    private String traccion;
    private String marca;
    private String tipoTransmision;

            */

    public ModeloVehiculo getModeloVehiculo(){
        String nombreModelo=tfNombreModelo.getText();
        String nombreMarca=tfNombreMarca.getText();

        String propulsion=comboBoxPropulsion.getSelectedItem().toString();
        String traccion=comboBoxTraccion.getSelectedItem().toString();
        String transmision=comboBoxTransmision.getSelectedItem().toString();
        String vehiculo=comboBoxVehiculo.getSelectedItem().toString();

        int plazas=(int)spinnerPlazas.getValue();
        int puertas=(int)spinnerPuertas.getValue();

        //ModeloVehiculo mPrueba=new ModeloVehiculo(1,"laferrari",2,3,"gasolina","trasera","Ferrari","manual");
        ModeloVehiculo m=new ModeloVehiculo(-1,nombreModelo,plazas,puertas,vehiculo,propulsion,traccion,nombreMarca,transmision);
        return m;
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
        
        comboBoxPropulsion = new JComboBox<String>();
        modelPropulsion= new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_PROPULSION);
        comboBoxPropulsion.setModel(modelPropulsion);
        comboBoxPropulsion.setBounds(195, 135, 150, 22);
        add(comboBoxPropulsion);
        
        comboBoxTransmision = new JComboBox<String>();
        modelTransmision= new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_TRANSMISION);
        comboBoxTransmision.setModel(modelTransmision);
        comboBoxTransmision.setBounds(195, 207, 150, 22);
        add(comboBoxTransmision);
        
        comboBoxTraccion = new JComboBox<String>();
        modelTraccion= new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_TRACCION);
        comboBoxTraccion.setModel(modelTraccion);
        comboBoxTraccion.setBounds(195, 171, 150, 22);
        add(comboBoxTraccion);
        
        JLabel lblNombreMarca_3_1 = new JLabel("Tipo de vehículo");
        lblNombreMarca_3_1.setBounds(25, 239, 160, 20);
        add(lblNombreMarca_3_1);
        
        comboBoxVehiculo = new JComboBox<String>();
        modelVehiculo= new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_VEHICULOS);
        comboBoxVehiculo.setModel(modelVehiculo);
        comboBoxVehiculo.setBounds(195, 238, 150, 22);
        add(comboBoxVehiculo);
        
        spinnerPlazas = new JSpinner();
        spinnerPlazas.setBounds(195, 282, 150, 20);
        add(spinnerPlazas);
        
        spinnerPuertas = new JSpinner();
        spinnerPuertas.setBounds(195, 320, 150, 20);
        add(spinnerPuertas);
        
        JLabel lblNombreMarca_3_1_1 = new JLabel("Número de plazas");
        lblNombreMarca_3_1_1.setBounds(25, 282, 160, 20);
        add(lblNombreMarca_3_1_1);
        
        JLabel lblNombreMarca_3_1_1_1 = new JLabel("Número de puertas");
        lblNombreMarca_3_1_1_1.setBounds(25, 320, 160, 20);
        add(lblNombreMarca_3_1_1_1);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(591, 85, 189, 194);
        add(scrollPane);
        
        listModelos = new JList();
        scrollPane.setViewportView(listModelos);
        
        JLabel lblModelosDisponibles = new JLabel("Modelos disponibles");
        lblModelosDisponibles.setBounds(591, 60, 142, 14);
        add(lblModelosDisponibles);
        
        btnEliminarModelo = new JButton("Eliminar Modelo");
        btnEliminarModelo.setActionCommand("Eliminar Vehiculo");
        btnEliminarModelo.setBounds(591, 305, 150, 25);
        add(btnEliminarModelo);
        
        btnModificarModelo = new JButton("Modificar Modelo");
        btnModificarModelo.setActionCommand("Modificar Vehiculo");
        btnModificarModelo.setBounds(591, 353, 150, 25);
        add(btnModificarModelo);
    }

    public void setControlador(ConcesionarioControlador c){
        btnGuardar.addActionListener(c);
    }
}

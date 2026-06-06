package com.dam.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.ModeloVehiculo;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class PNuevoModelo extends JPanel implements IPanel{
    private static final int ANCHO=1000;
    private static final int ALTO=1000;
    public static final String GUARDAR_MODELO_BTN="Guardar Modelo";
    public static final String ELIMINAR_MODELO_BTN = "Eliminar Modelo";
    public static final String MODIFICAR_MODELO_BTN = "Modificar Modelo";
    JButton btnGuardar;
    JTextField tfNombreModelo;
    JTextField tfNombreMarca;
    private JLabel lblPropulsion;
    private JLabel lblTraccion;
    private JLabel lblTipoTransmision;
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
    private DefaultListModel<ModeloVehiculo> modelModelosLista;
    private JList<ModeloVehiculo> listModelos;   // ← cambia el tipo existente

    public PNuevoModelo(){
        setLayout(null);
        setSize(ANCHO,ALTO);
        crearComponentes();
    }

    /*
    public ModeloVehiculo(int idModelo, String nombreModelo,
     int numeroPlazas, int numeroPuertas, String tipoModelo, String tipoPropulsion,
		String traccion, String marca, String tipoTransmision) { 
    */

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
        btnGuardar.setBounds(25, 425, 150, 25);
        add(btnGuardar);
        
        lblPropulsion = new JLabel("Tipo de propulsión");
        lblPropulsion.setBounds(25, 136, 160, 20);
        add(lblPropulsion);
        
        lblTraccion = new JLabel("Tracción");
        lblTraccion.setBounds(25, 172, 160, 20);
        add(lblTraccion);
        
        lblTipoTransmision = new JLabel("Tipo de transmisión");
        lblTipoTransmision.setBounds(25, 208, 160, 20);
        add(lblTipoTransmision);
        
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
        
        JLabel lblTipoVehiculo = new JLabel("Tipo de vehículo");
        lblTipoVehiculo.setBounds(25, 239, 160, 20);
        add(lblTipoVehiculo);
        
        comboBoxVehiculo = new JComboBox<String>();
        modelVehiculo= new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_VEHICULOS);
        comboBoxVehiculo.setModel(modelVehiculo);
        comboBoxVehiculo.setBounds(195, 238, 150, 22);
        add(comboBoxVehiculo);
        
        spinnerPlazas = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

        spinnerPlazas.setBounds(195, 282, 150, 20);
        add(spinnerPlazas);
        
        spinnerPuertas = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        spinnerPuertas.setBounds(195, 320, 150, 20);
        add(spinnerPuertas);
        
        JLabel lblNumeroPlazas = new JLabel("Número de plazas");
        lblNumeroPlazas.setBounds(25, 282, 160, 20);
        add(lblNumeroPlazas);
        
        JLabel lblNumeroPuertas = new JLabel("Número de puertas");
        lblNumeroPuertas.setBounds(25, 320, 160, 20);
        add(lblNumeroPuertas);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(591, 85, 189, 194);
        add(scrollPane);
        
        modelModelosLista = new DefaultListModel<>();
        listModelos = new JList<>(modelModelosLista);
        scrollPane.setViewportView(listModelos);
        
        JLabel lblModelosDisponibles = new JLabel("Modelos disponibles");
        lblModelosDisponibles.setBounds(591, 60, 142, 14);
        add(lblModelosDisponibles);
        
        btnEliminarModelo = new JButton(ELIMINAR_MODELO_BTN);
        btnEliminarModelo.setBounds(591, 305, 150, 25);
        add(btnEliminarModelo);
        
        btnModificarModelo = new JButton(MODIFICAR_MODELO_BTN);
        btnModificarModelo.setBounds(591, 353, 150, 25);
        add(btnModificarModelo);

        
    }

    public void setControlador(ConcesionarioControlador c) {
        btnGuardar.addActionListener(c);
        btnEliminarModelo.addActionListener(c);   // ya tenía actionCommand "Eliminar Vehiculo" — lo corregimos abajo
        btnModificarModelo.addActionListener(c);
    }

    public void actualizarListaModelos(ArrayList<ModeloVehiculo> modelos) {
        modelModelosLista.removeAllElements();
        for (ModeloVehiculo m : modelos) {
            modelModelosLista.addElement(m);
        }
    }

    public ModeloVehiculo getModeloSeleccionado() {
        return listModelos.getSelectedValue();
    }
}

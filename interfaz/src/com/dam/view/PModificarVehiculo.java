package com.dam.view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;

import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Vehiculo;

public class PModificarVehiculo extends JPanel implements IPanel {
    private static final int ANCHO = 1000;
    private static final int ALTO = 1000;

    public static final String GUARDAR_MODIFICACION_BTN = "Guardar Modificación";
    public static final String VER_COLOR_MODIFICAR_BTN = "Ver color modificar";
    public static final String BUSCAR_MARCA_MODIFICAR_BTN = "Buscar Marca Modificar";

    private JButton btnModificar;
    private DefaultComboBoxModel<String> modelMarcas;
    private JComboBox<String> cbMarca;
    private DefaultComboBoxModel<String> modelModelos;
    private JComboBox<String> cbModelo;
    private JSpinner spPrecio;
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
    private JButton btnBuscarMarca;
    private ArrayList<ModeloVehiculo> modelos;
    private Vehiculo vehiculoActual;

    public PModificarVehiculo() {
        setLayout(null);
        setSize(ANCHO, ALTO);
        crearComponentes();
    }

    public void actualizarMarcas(ArrayList<String> marcas) {
        modelMarcas.removeAllElements();
        for (String marca : marcas) {
            modelMarcas.addElement(marca);
        }
    }

    public void actualizarModelos(ArrayList<ModeloVehiculo> modelos) {
        this.modelos = modelos;
        modelModelos.removeAllElements();
        for (ModeloVehiculo modelo : modelos) {
            modelModelos.addElement(modelo.getNombreModelo());
        }
    }

    public String getMarca() {
        int indexMarca = cbMarca.getSelectedIndex();
        return modelMarcas.getElementAt(indexMarca);
    }

    public Vehiculo getVehiculo() {
        int idVehiculo = vehiculoActual != null ? vehiculoActual.getIdVehiculo() : -1;
        int precio = (int) spPrecio.getValue();

        int indexModelo = cbModelo.getSelectedIndex();
        ModeloVehiculo modelo = modelos.get(indexModelo);

        String matricula = txtMatricula.getText().trim();
        String color = "#" + Integer.toHexString(new java.awt.Color((int)spRojo.getValue(), (int)spVerde.getValue(), (int)spAzul.getValue()).getRGB()).substring(2).toUpperCase();
        int year = (int) spYear.getValue();
        int kilometraje = (int) spKilometraje.getValue();
        int potencia = (int) spPotencia.getValue();
        int cilindrada = (int) spCilindrada.getValue();
        int peso = (int) spPeso.getValue();

        return new Vehiculo(idVehiculo, modelo, precio, matricula, color, year, kilometraje, potencia, cilindrada, peso);
    }

    public void cargarVehiculo(Vehiculo vehiculo) {
        vehiculoActual = vehiculo;
        ModeloVehiculo modelo = vehiculo.getModelo();

        asegurarMarca(modelo.getMarca());
        asegurarModelo(modelo);

        seleccionarMarca(modelo.getMarca());
        seleccionarModelo(modelo);
        spPrecio.setValue(vehiculo.getPrecio());
        txtMatricula.setText(vehiculo.getMatricula());
        cargarColor(vehiculo.getColor());
        spYear.setValue(vehiculo.getYear());
        spKilometraje.setValue(vehiculo.getKilometraje());
        spPotencia.setValue(vehiculo.getPotenciaCV());
        spCilindrada.setValue(vehiculo.getCilindrada());
        spPeso.setValue(vehiculo.getPesoKG());

        actualizarColor();
    }

    public void crearComponentes() {
        JLabel lblTitulo = new JLabel("Modificar Vehiculo");
        lblTitulo.setBounds(80, 26, 160, 20);
        add(lblTitulo);

        modelMarcas = new DefaultComboBoxModel<String>();
        cbMarca = new JComboBox<String>(modelMarcas);
        cbMarca.setBounds(185, 103, 150, 25);
        add(cbMarca);

        modelModelos = new DefaultComboBoxModel<String>();
        cbModelo = new JComboBox<String>(modelModelos);
        cbModelo.setBounds(185, 143, 150, 25);
        add(cbModelo);

        spPrecio = new JSpinner(new SpinnerNumberModel(20000, 0, 1000000, 1000));
        spPrecio.setBounds(185, 183, 150, 25);
        add(spPrecio);

        btnModificar = new JButton("Guardar cambios");
        btnModificar.setActionCommand(GUARDAR_MODIFICACION_BTN);
        btnModificar.setBounds(80, 436, 150, 25);
        add(btnModificar);

        txtMatricula = new JTextField();
        txtMatricula.setBounds(185, 219, 86, 20);
        add(txtMatricula);
        txtMatricula.setColumns(10);

        spYear = new JSpinner(new SpinnerNumberModel(2000, 1800, 2200, 1));
        spYear.setBounds(185, 281, 86, 20);
        add(spYear);

        spKilometraje = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        spKilometraje.setBounds(185, 312, 86, 20);
        add(spKilometraje);

        spPotencia = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        spPotencia.setBounds(185, 343, 86, 20);
        add(spPotencia);

        spCilindrada = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        spCilindrada.setBounds(185, 374, 86, 20);
        add(spCilindrada);

        spPeso = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        spPeso.setBounds(185, 405, 86, 20);
        add(spPeso);

        JLabel lblMatricula = new JLabel("Matricula");
        lblMatricula.setBounds(80, 222, 95, 14);
        add(lblMatricula);

        JLabel lblColor = new JLabel("Color");
        lblColor.setBounds(80, 253, 95, 14);
        add(lblColor);

        JLabel lblYear = new JLabel("Anio");
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

        spRojo = new JSpinner(new SpinnerNumberModel(0, 0, 255, 15));
        spRojo.setBounds(185, 250, 55, 20);
        add(spRojo);

        spVerde = new JSpinner(new SpinnerNumberModel(0, 0, 255, 15));
        spVerde.setBounds(250, 250, 55, 20);
        add(spVerde);

        spAzul = new JSpinner(new SpinnerNumberModel(0, 0, 255, 15));
        spAzul.setBounds(315, 250, 55, 20);
        add(spAzul);

        btnVerColor = new JButton("Ver color");
        btnVerColor.setActionCommand(VER_COLOR_MODIFICAR_BTN);
        btnVerColor.setBounds(380, 249, 89, 23);
        add(btnVerColor);

        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(80, 148, 95, 14);
        add(lblModelo);

        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setBounds(80, 108, 95, 14);
        add(lblMarca);

        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(80, 188, 95, 14);
        add(lblPrecio);

        btnBuscarMarca = new JButton("Buscar Marca");
        btnBuscarMarca.setActionCommand(BUSCAR_MARCA_MODIFICAR_BTN);
        btnBuscarMarca.setBounds(345, 104, 150, 25);
        add(btnBuscarMarca);

        actualizarColor();
    }

    public void actualizarColor() {
        int rojo = (int) spRojo.getValue();
        int verde = (int) spVerde.getValue();
        int azul = (int) spAzul.getValue();
        btnVerColor.setBackground(new Color(rojo, verde, azul));
    }

    public void setControlador(ConcesionarioControlador c) {
        btnModificar.addActionListener(c);
        btnVerColor.addActionListener(c);
        btnBuscarMarca.addActionListener(c);
    }

    private void asegurarMarca(String marca) {
        if (buscarMarca(marca) == -1) {
            modelMarcas.addElement(marca);
        }
    }

    private void asegurarModelo(ModeloVehiculo modelo) {
        if (modelos == null) {
            modelos = new ArrayList<ModeloVehiculo>();
        }
        for (ModeloVehiculo modeloExistente : modelos) {
            if (modeloExistente.getIdModelo() == modelo.getIdModelo()) {
                return;
            }
        }
        modelos.add(modelo);
        modelModelos.addElement(modelo.getNombreModelo());
    }

    private void seleccionarMarca(String marca) {
        int index = buscarMarca(marca);
        if (index != -1) {
            cbMarca.setSelectedIndex(index);
        }
    }

    private void seleccionarModelo(ModeloVehiculo modelo) {
        if (modelos == null) return;
        for (int i = 0; i < modelos.size(); i++) {
            if (modelos.get(i).getIdModelo() == modelo.getIdModelo()) {
                cbModelo.setSelectedIndex(i);
                return;
            }
        }
    }

    private int buscarMarca(String marca) {
        for (int i = 0; i < modelMarcas.getSize(); i++) {
            if (modelMarcas.getElementAt(i).equals(marca)) {
                return i;
            }
        }
        return -1;
    }

    private void cargarColor(String color) {
        try {
            int indiceG = color.indexOf("G");
            int indiceB = color.indexOf("B");
            int rojo = Integer.parseInt(color.substring(1, indiceG));
            int verde = Integer.parseInt(color.substring(indiceG + 1, indiceB));
            int azul = Integer.parseInt(color.substring(indiceB + 1));
            spRojo.setValue(rojo);
            spVerde.setValue(verde);
            spAzul.setValue(azul);
        } catch (Exception e) {
            spRojo.setValue(0);
            spVerde.setValue(0);
            spAzul.setValue(0);
        }
    }
}

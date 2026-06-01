package com.dam.view;

import javax.swing.*;
import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.ModeloVehiculo;

public class PModificarModelo extends JPanel implements IPanel {
    private static final int ANCHO = 1000;
    private static final int ALTO = 1000;

    public static final String GUARDAR_MODIFICACION_MODELO_BTN = "Guardar Modificación Modelo";

    private JTextField tfNombreModelo;
    private JTextField tfNombreMarca;
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
    private JButton btnGuardar;

    private ModeloVehiculo modeloActual;

    public PModificarModelo() {
        setLayout(null);
        setSize(ANCHO, ALTO);
        crearComponentes();
    }

    @Override
    public void crearComponentes() {
        JLabel lblTitulo = new JLabel("Modificar Modelo");
        lblTitulo.setBounds(25, 20, 200, 20);
        add(lblTitulo);

        JLabel lblNombreModelo = new JLabel("Modelo:");
        lblNombreModelo.setBounds(25, 60, 160, 20);
        add(lblNombreModelo);
        tfNombreModelo = new JTextField();
        tfNombreModelo.setBounds(195, 60, 150, 25);
        add(tfNombreModelo);

        JLabel lblNombreMarca = new JLabel("Marca:");
        lblNombreMarca.setBounds(25, 100, 160, 20);
        add(lblNombreMarca);
        tfNombreMarca = new JTextField();
        tfNombreMarca.setBounds(195, 100, 150, 25);
        add(tfNombreMarca);

        JLabel lblPropulsion = new JLabel("Tipo de propulsión:");
        lblPropulsion.setBounds(25, 136, 160, 20);
        add(lblPropulsion);
        modelPropulsion = new DefaultComboBoxModel<>(ModeloVehiculo.TIPOS_PROPULSION);
        comboBoxPropulsion = new JComboBox<>(modelPropulsion);
        comboBoxPropulsion.setBounds(195, 135, 150, 22);
        add(comboBoxPropulsion);

        JLabel lblTraccion = new JLabel("Tracción:");
        lblTraccion.setBounds(25, 172, 160, 20);
        add(lblTraccion);
        modelTraccion = new DefaultComboBoxModel<>(ModeloVehiculo.TIPOS_TRACCION);
        comboBoxTraccion = new JComboBox<>(modelTraccion);
        comboBoxTraccion.setBounds(195, 171, 150, 22);
        add(comboBoxTraccion);

        JLabel lblTransmision = new JLabel("Tipo de transmisión:");
        lblTransmision.setBounds(25, 208, 160, 20);
        add(lblTransmision);
        modelTransmision = new DefaultComboBoxModel<>(ModeloVehiculo.TIPOS_TRANSMISION);
        comboBoxTransmision = new JComboBox<>(modelTransmision);
        comboBoxTransmision.setBounds(195, 207, 150, 22);
        add(comboBoxTransmision);

        JLabel lblVehiculo = new JLabel("Tipo de vehículo:");
        lblVehiculo.setBounds(25, 239, 160, 20);
        add(lblVehiculo);
        modelVehiculo = new DefaultComboBoxModel<>(ModeloVehiculo.TIPOS_VEHICULOS);
        comboBoxVehiculo = new JComboBox<>(modelVehiculo);
        comboBoxVehiculo.setBounds(195, 238, 150, 22);
        add(comboBoxVehiculo);

        JLabel lblPlazas = new JLabel("Número de plazas:");
        lblPlazas.setBounds(25, 278, 160, 20);
        add(lblPlazas);
        spinnerPlazas = new JSpinner(new SpinnerNumberModel(5, 1, 100, 1));
        spinnerPlazas.setBounds(195, 278, 150, 22);
        add(spinnerPlazas);

        JLabel lblPuertas = new JLabel("Número de puertas:");
        lblPuertas.setBounds(25, 316, 160, 20);
        add(lblPuertas);
        spinnerPuertas = new JSpinner(new SpinnerNumberModel(4, 1, 10, 1));
        spinnerPuertas.setBounds(195, 315, 150, 22);
        add(spinnerPuertas);

        btnGuardar = new JButton("Guardar cambios");
        btnGuardar.setActionCommand(GUARDAR_MODIFICACION_MODELO_BTN);
        btnGuardar.setBounds(25, 370, 150, 25);
        add(btnGuardar);
    }

    public void cargarModelo(ModeloVehiculo m) {
        modeloActual = m;
        tfNombreModelo.setText(m.getNombreModelo());
        tfNombreMarca.setText(m.getMarca());
        seleccionarEnCombo(comboBoxPropulsion, m.getTipoPropulsion());
        seleccionarEnCombo(comboBoxTraccion, m.getTraccion());
        seleccionarEnCombo(comboBoxTransmision, m.getTipoTransmision());
        seleccionarEnCombo(comboBoxVehiculo, m.getTipoModelo());
        spinnerPlazas.setValue(m.getNumeroPlazas());
        spinnerPuertas.setValue(m.getNumeroPuertas());
    }

    public ModeloVehiculo getModelo() {
        int id = modeloActual != null ? modeloActual.getIdModelo() : -1;
        return new ModeloVehiculo(
            id,
            tfNombreModelo.getText().trim(),
            (int) spinnerPlazas.getValue(),
            (int) spinnerPuertas.getValue(),
            comboBoxVehiculo.getSelectedItem().toString(),
            comboBoxPropulsion.getSelectedItem().toString(),
            comboBoxTraccion.getSelectedItem().toString(),
            tfNombreMarca.getText().trim(),
            comboBoxTransmision.getSelectedItem().toString()
        );
    }

    private void seleccionarEnCombo(JComboBox<String> combo, String valor) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).equals(valor)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    @Override
    public void setControlador(ConcesionarioControlador c) {
        btnGuardar.addActionListener(c);
    }
}
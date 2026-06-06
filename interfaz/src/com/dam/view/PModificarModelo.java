package com.dam.view;

import javax.swing.*;
import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.ModeloVehiculo;

/**
 * Panel para modificar los datos de un modelo de vehículo existente.
 * <p>
 * Presenta un formulario pre-rellenado con los datos actuales del modelo
 * seleccionado, permitiendo editar el nombre, la marca, el tipo de propulsión,
 * la tracción, la transmisión, el tipo de vehículo, el número de plazas
 * y el número de puertas. Los cambios tienen persistencia al pulsar el botón de guardar.
 * <p>
 * El modelo a editar se carga mediante {@link #cargarModelo(ModeloVehiculo)}
 * y los datos modificados se obtienen con {@link #getModelo()}.
 * @see ModeloVehiculo
 * @see IPanel
 * @see ConcesionarioControlador
 */
@SuppressWarnings("serial")
public class PModificarModelo extends JPanel implements IPanel {
    /** Comando de acción del botón que guarda los cambios del modelo. */
    public static final String GUARDAR_MODIFICACION_MODELO_BTN = "Guardar Modificación Modelo";

    /** Comando de acción del botón que limpia el formulario de modificación de modelo. */
    public static final String LIMPIAR_MOD_MODELO_BTN = "Limpiar Mod Modelo";

    /** Campo de texto para editar el nombre del modelo. */
    private JTextField tfNombreModelo;

    /** Campo de texto para editar el nombre de la marca. */
    private JTextField tfNombreMarca;

    /** Desplegable para seleccionar el tipo de propulsión del modelo. */
    private JComboBox<String> comboBoxPropulsion;

    /** Desplegable para seleccionar el tipo de tracción del modelo. */
    private JComboBox<String> comboBoxTraccion;

    /** Desplegable para seleccionar el tipo de transmisión del modelo. */
    private JComboBox<String> comboBoxTransmision;

    /** Desplegable para seleccionar la categoría del vehículo. */
    private JComboBox<String> comboBoxVehiculo;

    /** Modelo del desplegable de tipos de propulsión. */
    private DefaultComboBoxModel<String> modelPropulsion;

    /** Modelo del desplegable de tipos de tracción. */
    private DefaultComboBoxModel<String> modelTraccion;

    /** Modelo del desplegable de tipos de transmisión. */
    private DefaultComboBoxModel<String> modelTransmision;

    /** Modelo del desplegable de tipos de vehículo. */
    private DefaultComboBoxModel<String> modelVehiculo;

    /** Spinner para editar el número de plazas del modelo. */
    private JSpinner spinnerPlazas;

    /** Spinner para editar el número de puertas del modelo. */
    private JSpinner spinnerPuertas;

    /** Botón que guarda los cambios realizados sobre el modelo. */
    private JButton btnGuardar;

    /** Botón que limpia los datos del formulario. */
    private JButton btnLimpiar;

    /**
     * Modelo que se está editando actualmente.
     * Se usa para conservar el ID al construir el objeto modificado.
     */
    private ModeloVehiculo modeloActual;

    /**
     * Crea el panel de modificación de modelo e inicializa sus componentes.
     */
    public PModificarModelo() {
        setLayout(null);
        setSize(VPrincipal.ANCHO, VPrincipal.ALTO);
        crearComponentes();
    }

    /**
     * Crea e inicializa todos los componentes visuales del formulario:
     * etiquetas, campos de texto, desplegables, spinners y botón de guardar.
     */
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
        spinnerPlazas = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        spinnerPlazas.setBounds(195, 278, 150, 22);
        add(spinnerPlazas);

        JLabel lblPuertas = new JLabel("Número de puertas:");
        lblPuertas.setBounds(25, 316, 160, 20);
        add(lblPuertas);
        spinnerPuertas = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        spinnerPuertas.setBounds(195, 315, 150, 22);
        add(spinnerPuertas);

        btnGuardar = new JButton("Guardar cambios");
        btnGuardar.setActionCommand(GUARDAR_MODIFICACION_MODELO_BTN);
        btnGuardar.setBounds(25, 370, 150, 25);
        add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setActionCommand(LIMPIAR_MOD_MODELO_BTN);
        btnLimpiar.setBounds(195, 370, 150, 25);
        add(btnLimpiar);
    }

    /**
     * Carga los datos del modelo indicado en los campos del formulario.
     * <p>
     * Rellena los campos de texto, selecciona los valores correspondientes
     * en los desplegables y actualiza los spinners de plazas y puertas.
     * Guarda además la referencia al modelo para conservar su ID
     * al construir el objeto modificado con {@link #getModelo()}.
     * @param m modelo cuyos datos se cargarán en el formulario.
     */
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

    /**
     * Construye y devuelve un objeto {@link ModeloVehiculo} con los datos
     * introducidos actualmente en el formulario.
     * <p>
     * El ID del modelo se toma del modelo cargado con
     * {@link #cargarModelo(ModeloVehiculo)}, o -1 si no se ha cargado ninguno.
     * @return modelo con los datos modificados del formulario.
     */
    public ModeloVehiculo getModelo() {
        String nombreModelo = tfNombreModelo.getText().trim();
        String nombreMarca = tfNombreMarca.getText().trim();

        if (nombreModelo.isEmpty() || nombreMarca.isEmpty()) {
            Avisos.error(this, "La marca y el modelo no pueden estar vacíos.");
            return null;
        }

        String propulsion = comboBoxPropulsion.getSelectedItem().toString();
        String traccion = comboBoxTraccion.getSelectedItem().toString();
        String transmision = comboBoxTransmision.getSelectedItem().toString();
        String vehiculo = comboBoxVehiculo.getSelectedItem().toString();
        int plazas = (int) spinnerPlazas.getValue();
        int puertas = (int) spinnerPuertas.getValue();
        
        int id = (modeloActual != null) ? modeloActual.getIdModelo() : -1;
        
        return new ModeloVehiculo(id, nombreModelo, plazas, puertas, vehiculo, propulsion, traccion, nombreMarca, transmision);
    }

    /**
     * Selecciona en el desplegable indicado el elemento cuyo texto
     * coincide con el valor proporcionado.
     * <p>
     * Si no se encuentra ningún elemento con ese valor, no se modifica
     * la selección actual del desplegable.
     * @param combo desplegable en el que se realizará la selección.
     * @param valor texto del elemento a seleccionar.
     */
    private void seleccionarEnCombo(JComboBox<String> combo, String valor) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).equals(valor)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    /**
     * Registra el controlador como ActionListener del botón de guardar los cambios en la aplicación.
     * @param c controlador principal de la aplicación.
     */
    @Override
    public void setControlador(ConcesionarioControlador c) {
        btnGuardar.addActionListener(c);
        btnLimpiar.addActionListener(c);
    }

    public void limpiarDatos() {
        tfNombreModelo.setText("");
        tfNombreMarca.setText("");
        comboBoxPropulsion.setSelectedIndex(0);
        comboBoxTraccion.setSelectedIndex(0);
        comboBoxTransmision.setSelectedIndex(0);
        comboBoxVehiculo.setSelectedIndex(0);
        spinnerPlazas.setValue(1);
        spinnerPuertas.setValue(0);
    }
}

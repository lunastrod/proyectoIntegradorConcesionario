package com.dam.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

/**
 * Panel para crear nuevos modelos de vehículo y gestionar los existentes.
 * <p>
 * Presenta un formulario para introducir los datos de un nuevo modelo:
 * nombre, marca, tipo de propulsión, tracción, transmisión, categoría
 * de vehículo, número de plazas y número de puertas.
 * <p>
 * Además incluye una lista con todos los modelos registrados en la base
 * de datos, desde la que se puede seleccionar un modelo para eliminarlo
 * o cargarlo en el panel de modificación.
 * @see ModeloVehiculo
 * @see IPanel
 * @see ConcesionarioControlador
 */
@SuppressWarnings("serial")
public class PNuevoModelo extends JPanel implements IPanel {
    /** Comando de acción del botón que guarda el nuevo modelo. */
    public static final String GUARDAR_MODELO_BTN = "Guardar Modelo";

    /** Comando de acción del botón que elimina el modelo seleccionado. */
    public static final String ELIMINAR_MODELO_BTN = "Eliminar Modelo";

    /** Comando de acción del botón que carga el modelo seleccionado en el panel de modificación. */
    public static final String MODIFICAR_MODELO_BTN = "Modificar Modelo";

    /** Comando de acción del botón que limpia el formulario de modelo. */
    public static final String LIMPIAR_MODELO_BTN = "Limpiar Modelo";

    /** Botón que guarda el nuevo modelo en la base de datos. */
    private JButton btnGuardar;

    /** Botón que limpia los datos introducidos en el formulario. */
    private JButton btnLimpiar;

    /** Campo de texto para introducir el nombre del modelo. */
    private JTextField tfNombreModelo;

    /** Campo de texto para introducir el nombre de la marca. */
    private JTextField tfNombreMarca;

    /** Etiqueta del campo de tipo de propulsión. */
    private JLabel lblPropulsion;

    /** Etiqueta del campo de tracción. */
    private JLabel lblTraccion;

    /** Etiqueta del campo de tipo de transmisión. */
    private JLabel lblTipoTransmision;

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

    /** Modelo del desplegable de categorías de vehículo. */
    private DefaultComboBoxModel<String> modelVehiculo;

    /** Spinner para introducir el número de plazas del modelo. */
    private JSpinner spinnerPlazas;

    /** Spinner para introducir el número de puertas del modelo. */
    private JSpinner spinnerPuertas;

    /** Área de desplazamiento que envuelve la lista de modelos. */
    private JScrollPane scrollPane;

    /** Botón que elimina el modelo seleccionado en la lista. */
    private JButton btnEliminarModelo;

    /** Botón que carga el modelo seleccionado en el panel de modificación. */
    private JButton btnModificarModelo;

    /** Modelo de la lista que almacena los modelos de vehículo disponibles. */
    private DefaultListModel<ModeloVehiculo> modelModelosLista;

    /** Lista visual que muestra todos los modelos registrados en la base de datos. */
    private JList<ModeloVehiculo> listModelos;

    /**
     * Crea el panel de nuevo modelo e inicializa sus componentes.
     */
    public PNuevoModelo() {
        setLayout(null);
        setSize(VPrincipal.ANCHO, VPrincipal.ALTO);
        crearComponentes();
    }

    /**
     * Construye y devuelve un objeto {@link ModeloVehiculo} con los datos
     * introducidos actualmente en el formulario.
     * <p>
     * El ID se establece a -1 ya que el modelo aún no ha
     * sido persistido en la base de datos.
     * @return modelo con los datos del formulario listo para insertar
     */
    public ModeloVehiculo getModeloVehiculo() {
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
        
        return new ModeloVehiculo(-1, nombreModelo, plazas, puertas, vehiculo, propulsion, traccion, nombreMarca, transmision);
    }

    /**
     * Crea e inicializa todos los componentes visuales del formulario:
     * campos de texto de nombre y marca, desplegables de propulsión, tracción,
     * transmisión y categoría, spinners de plazas y puertas, lista de modelos
     * existentes y botones de guardar, eliminar y modificar.
     */
    public void crearComponentes() {
        JLabel lblTitulo = new JLabel("Nuevo Vehiculo");
        lblTitulo.setBounds(25, 20, 160, 20);
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

        btnGuardar = new JButton(GUARDAR_MODELO_BTN);
        btnGuardar.setBounds(25, 425, 150, 25);
        add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setActionCommand(LIMPIAR_MODELO_BTN);
        btnLimpiar.setBounds(195, 425, 150, 25);
        add(btnLimpiar);

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
        modelPropulsion = new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_PROPULSION);
        comboBoxPropulsion.setModel(modelPropulsion);
        comboBoxPropulsion.setBounds(195, 135, 150, 22);
        add(comboBoxPropulsion);

        comboBoxTransmision = new JComboBox<String>();
        modelTransmision = new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_TRANSMISION);
        comboBoxTransmision.setModel(modelTransmision);
        comboBoxTransmision.setBounds(195, 207, 150, 22);
        add(comboBoxTransmision);

        comboBoxTraccion = new JComboBox<String>();
        modelTraccion = new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_TRACCION);
        comboBoxTraccion.setModel(modelTraccion);
        comboBoxTraccion.setBounds(195, 171, 150, 22);
        add(comboBoxTraccion);

        JLabel lblTipoVehiculo = new JLabel("Tipo de vehículo");
        lblTipoVehiculo.setBounds(25, 239, 160, 20);
        add(lblTipoVehiculo);

        comboBoxVehiculo = new JComboBox<String>();
        modelVehiculo = new DefaultComboBoxModel<String>(ModeloVehiculo.TIPOS_VEHICULOS);
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

    /**
     * Registra el controlador como ActionListener de los botones
     * de guardar, eliminar y modificar modelo.
     * @param c controlador principal de la aplicación.
     */
    public void setControlador(ConcesionarioControlador c) {
        btnGuardar.addActionListener(c);
        btnLimpiar.addActionListener(c);
        btnEliminarModelo.addActionListener(c);
        btnModificarModelo.addActionListener(c);
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

    /**
     * Actualiza la lista de modelos con los elementos proporcionados.
     * <p>
     * Elimina todos los elementos anteriores e inserta los nuevos,
     * reflejando el estado actual de la base de datos.
     * @param modelos lista de modelos a mostrar en la lista.
     */
    public void actualizarListaModelos(ArrayList<ModeloVehiculo> modelos) {
        modelModelosLista.removeAllElements();
        for (ModeloVehiculo m : modelos) {
            modelModelosLista.addElement(m);
        }
    }

    /**
     * Devuelve el modelo actualmente seleccionado en la lista.
     * @return modelo seleccionado, o null si no hay ninguno seleccionado.
     */
    public ModeloVehiculo getModeloSeleccionado() {
        return listModelos.getSelectedValue();
    }
}

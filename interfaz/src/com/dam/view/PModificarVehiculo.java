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

/**
 * Panel para modificar los datos de un vehículo existente en el catálogo.
 * <p>
 * Presenta un formulario pre-rellenado con los datos actuales del vehículo
 * seleccionado, permitiendo editar la marca, el modelo, el precio, la matrícula,
 * el color, el año, el kilometraje, la potencia, la cilindrada y el peso.
 * Los cambios se persisten al pulsar el botón de guardar modificación.
 * <p>
 * El vehículo a editar se carga mediante {@link #cargarVehiculo(Vehiculo)}
 * y los datos modificados se obtienen con {@link #getVehiculo()}.
 * @see Vehiculo
 * @see ModeloVehiculo
 * @see IPanel
 * @see ConcesionarioControlador
 */
@SuppressWarnings("serial")
public class PModificarVehiculo extends JPanel implements IPanel {
    /** Comando de acción del botón que guarda los cambios del vehículo. */
    public static final String GUARDAR_MODIFICACION_BTN = "Guardar Modificación";

    /** Comando de acción del botón que actualiza el color de fondo del botón de vista previa. */
    public static final String VER_COLOR_MODIFICAR_BTN = "Ver color modificar";

    /** Comando de acción del botón que carga los modelos de la marca seleccionada. */
    public static final String BUSCAR_MARCA_MODIFICAR_BTN = "Buscar Marca Modificar";

    /** Comando de acción del botón que limpia el formulario de modificación de vehículo. */
    public static final String LIMPIAR_MOD_VEHICULO_BTN = "Limpiar Mod Vehiculo";

    /** Botón que guarda los cambios realizados sobre el vehículo. */
    private JButton btnModificar;

    /** Botón que limpia los datos del formulario. */
    private JButton btnLimpiar;

    /** Modelo del desplegable de marcas disponibles. */
    private DefaultComboBoxModel<String> modelMarcas;

    /** Desplegable para seleccionar la marca del vehículo. */
    private JComboBox<String> cbMarca;

    /** Modelo del desplegable de modelos disponibles para la marca seleccionada. */
    private DefaultComboBoxModel<String> modelModelos;

    /** Desplegable para seleccionar el modelo del vehículo. */
    private JComboBox<String> cbModelo;

    /** Spinner para editar el precio del vehículo en euros. */
    private JSpinner spPrecio;

    /** Campo de texto para editar la matrícula del vehículo. */
    private JTextField txtMatricula;

    /** Spinner para editar el componente rojo del color (0-255). */
    private JSpinner spRojo;

    /** Spinner para editar el componente verde del color (0-255). */
    private JSpinner spVerde;

    /** Spinner para editar el componente azul del color (0-255). */
    private JSpinner spAzul;

    /** Spinner para editar el año de fabricación del vehículo. */
    private JSpinner spYear;

    /** Spinner para editar el kilometraje del vehículo en kilómetros. */
    private JSpinner spKilometraje;

    /** Spinner para editar la potencia del motor en CV. */
    private JSpinner spPotencia;

    /** Spinner para editar la cilindrada del motor en cc. */
    private JSpinner spCilindrada;

    /** Spinner para editar el peso del vehículo en kg. */
    private JSpinner spPeso;

    /** Botón cuyo fondo muestra una vista previa del color RGB seleccionado. */
    private JButton btnVerColor;

    /** Botón que carga los modelos disponibles para la marca seleccionada. */
    private JButton btnBuscarMarca;

    /** Lista de modelos cargados para la marca actualmente seleccionada. */
    private ArrayList<ModeloVehiculo> modelos;

    /**
     * Vehículo que se está editando actualmente.
     * Se usa para conservar el ID al construir el objeto modificado.
     */
    private Vehiculo vehiculoActual;

    /**
     * Crea el panel de modificación de vehículo e inicializa sus componentes.
     */
    public PModificarVehiculo() {
        setLayout(null);
        setSize(VPrincipal.ANCHO, VPrincipal.ALTO);
        crearComponentes();
    }

    /**
     * Actualiza el desplegable de marcas con la lista proporcionada de marcas.
     * @param marcas lista de nombres de marcas a mostrar en el desplegable.
     */
    public void actualizarMarcas(ArrayList<String> marcas) {
        modelMarcas.removeAllElements();
        for (String marca : marcas) {
            modelMarcas.addElement(marca);
        }
    }

    /**
     * Actualiza el desplegable de modelos con la lista proporcionada.
     * <p>
     * También actualiza la lista interna de modelos usada para construir
     * el objeto {@link Vehiculo} al recuperar los datos del formulario.
     * @param modelos lista de modelos a mostrar en el desplegable.
     */
    public void actualizarModelos(ArrayList<ModeloVehiculo> modelos) {
        this.modelos = modelos;
        modelModelos.removeAllElements();
        for (ModeloVehiculo modelo : modelos) {
            modelModelos.addElement(modelo.getNombreModelo());
        }
    }

    /**
     * Devuelve el nombre de la marca actualmente seleccionada en el desplegable.
     * @return nombre de la marca seleccionada.
     */
    public String getMarca() {
        int indexMarca = cbMarca.getSelectedIndex();
        return modelMarcas.getElementAt(indexMarca);
    }

    /**
     * Construye y devuelve un objeto {@link Vehiculo} con los datos
     * introducidos actualmente en el formulario.
     * <p>
     * El ID del vehículo se toma del vehículo cargado con
     * {@link #cargarVehiculo(Vehiculo)}, o -1 si no se ha cargado ninguno.
     * El color se construye en formato RGB a partir de los spinners
     * de los componentes rojo, verde y azul.
     * @return vehículo con los datos modificados del formulario.
     */
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

    /**
     * Carga los datos del vehículo indicado en los campos del formulario.
     * <p>
     * Asegura que la marca y el modelo del vehículo estén presentes en
     * los desplegables aunque no hayan sido cargados previamente, los selecciona
     * y rellena el resto de campos con los valores actuales del vehículo.
     * Actualiza también la vista previa del color.
     * @param vehiculo vehículo cuyos datos se cargarán en el formulario.
     */
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

    /**
     * Crea e inicializa todos los componentes visuales del formulario:
     * desplegables de marca y modelo, spinners de precio, año, kilometraje,
     * potencia, cilindrada y peso, campo de matrícula, spinners RGB de color,
     * botón de vista previa de color, botón de buscar marca y botón de guardar.
     */
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

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setActionCommand(LIMPIAR_MOD_VEHICULO_BTN);
        btnLimpiar.setBounds(245, 436, 150, 25);
        add(btnLimpiar);

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
        setFormularioHabilitado(false);
    }

    /**
     * Actualiza el color de fondo del botón de vista previa con los valores
     * RGB actualmente seleccionados en los spinners de color.
     */
    public void actualizarColor() {
        int rojo = (int) spRojo.getValue();
        int verde = (int) spVerde.getValue();
        int azul = (int) spAzul.getValue();
        btnVerColor.setBackground(new Color(rojo, verde, azul));
    }

    public void limpiarDatos() {
        if (cbMarca.getItemCount() > 0) cbMarca.setSelectedIndex(0);
        if (cbModelo.getItemCount() > 0) cbModelo.setSelectedIndex(0);
        spPrecio.setValue(20000);
        txtMatricula.setText("");
        spRojo.setValue(0);
        spVerde.setValue(0);
        spAzul.setValue(0);
        spYear.setValue(2000);
        spKilometraje.setValue(0);
        spPotencia.setValue(0);
        spCilindrada.setValue(0);
        spPeso.setValue(0);
        actualizarColor();
        setFormularioHabilitado(false);
    }

    /**
     * Registra el controlador como ActionListener de los botones
     * de guardar modificación, ver color y buscar marca.
     * @param c controlador principal de la aplicación.
     */
    public void setControlador(ConcesionarioControlador c) {
        btnModificar.addActionListener(c);
        btnLimpiar.addActionListener(c);
        btnVerColor.addActionListener(c);
        btnBuscarMarca.addActionListener(c);
    }

    /**
     * Añade la marca al desplegable si no está ya presente.
     * <p>
     * Se usa al cargar un vehículo para garantizar que su marca
     * esté disponible para su selección aunque no haya sido cargada
     * previamente desde la base de datos.
     * @param marca nombre de la marca a asegurar en el desplegable
     */
    private void asegurarMarca(String marca) {
        if (buscarMarca(marca) == -1) {
            modelMarcas.addElement(marca);
        }
    }

    /**
     * Añade el modelo a la lista interna y al desplegable si no está ya presente.
     * <p>
     * Se usa al cargar un vehículo para garantizar que su modelo
     * esté disponible para su selección aunque no haya sido cargado previamente.
     * @param modelo modelo a asegurar en el desplegable
     */
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

    /**
     * Selecciona en el desplegable de marcas el elemento cuyo texto
     * coincide con la marca indicada.
     * @param marca nombre de la marca a seleccionar.
     */
    private void seleccionarMarca(String marca) {
        int index = buscarMarca(marca);
        if (index != -1) {
            cbMarca.setSelectedIndex(index);
        }
    }

    /**
     * Selecciona en el desplegable de modelos el elemento cuyo ID
     * coincide con el del modelo indicado.
     * @param modelo modelo a seleccionar en el desplegable.
     */
    private void seleccionarModelo(ModeloVehiculo modelo) {
        if (modelos == null) return;
        for (int i = 0; i < modelos.size(); i++) {
            if (modelos.get(i).getIdModelo() == modelo.getIdModelo()) {
                cbModelo.setSelectedIndex(i);
                return;
            }
        }
    }

    /**
     * Busca la posición de una marca en el modelo del desplegable de marcas.
     * @param marca nombre de la marca a buscar.
     * @return índice de la marca en el desplegable, o -1 si no se encuentra.
     */
    private int buscarMarca(String marca) {
        for (int i = 0; i < modelMarcas.getSize(); i++) {
            if (modelMarcas.getElementAt(i).equals(marca)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Descompone la cadena de color en formato RGB y carga
     * los valores resultantes en los spinners de los componentes RGB.
     * <p>
     * Si el formato no es válido o se produce un error al parsear,
     * los tres spinners se establecen a 0.
     * @param color cadena de color en formato RGB.
     */
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

    public void setFormularioHabilitado(boolean b) {
        cbModelo.setEnabled(b);
        spPrecio.setEnabled(b);
        txtMatricula.setEnabled(b);
        spRojo.setEnabled(b);
        spVerde.setEnabled(b);
        spAzul.setEnabled(b);
        spYear.setEnabled(b);
        spKilometraje.setEnabled(b);
        spPotencia.setEnabled(b);
        spCilindrada.setEnabled(b);
        spPeso.setEnabled(b);
        btnVerColor.setEnabled(b);
        btnModificar.setEnabled(b);
        btnLimpiar.setEnabled(b);
    }
}

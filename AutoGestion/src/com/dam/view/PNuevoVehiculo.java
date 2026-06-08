package com.dam.view;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.ModeloVehiculo;
import com.dam.model.data.Vehiculo;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

/**
 * Panel para añadir nuevos vehículos al catálogo y gestionar los existentes.
 * <p>
 * Presenta un formulario para introducir los datos de un nuevo vehículo:
 * marca, modelo, precio, matrícula, color RGB, año, kilometraje, potencia,
 * cilindrada y peso. La marca se selecciona de un desplegable y, al pulsar
 * el botón de buscar marca, se cargan los modelos disponibles para esa marca.
 * <p>
 * Además incluye una lista con todos los vehículos registrados en el catálogo,
 * desde la que se puede seleccionar un vehículo para eliminarlo o cargarlo
 * en el panel de modificación.
 * @see Vehiculo
 * @see ModeloVehiculo
 * @see IPanel
 * @see ConcesionarioControlador
 */
@SuppressWarnings("serial")
public class PNuevoVehiculo extends JPanel implements IPanel {
    /** Comando de acción del botón que guarda el nuevo vehículo. */
    public static final String GUARDAR_VEHICULO_BTN = "Guardar Vehiculo";

    /** Comando de acción del botón que actualiza la vista previa del color. */
    public static final String VER_COLOR_BTN = "Ver color";

    /** Comando de acción del botón que carga los modelos de la marca seleccionada. */
    public static final String BUSCAR_MARCA_BTN = "Buscar Marca";

    /** Comando de acción del botón que elimina el vehículo seleccionado. */
    public static final String ELIMINAR_VEHICULO_BTN = "Eliminar Vehiculo";

    /** Comando de acción del botón que carga el vehículo seleccionado en el panel de modificación. */
    public static final String MODIFICAR_VEHICULO_BTN = "Modificar Vehiculo";

    /** Comando de acción del botón que limpia el formulario de vehículo. */
    public static final String LIMPIAR_VEHICULO_BTN = "Limpiar Vehiculo";

    /** Botón que guarda el nuevo vehículo en la base de datos. */
    private JButton btnGuardar;

    /** Botón que limpia los datos introducidos en el formulario. */
    private JButton btnLimpiar;

    /** Modelo del desplegable de marcas disponibles. */
    private DefaultComboBoxModel<String> modelMarcas;

    /** Desplegable para seleccionar la marca del vehículo. */
    private JComboBox<String> cbMarca;

    /** Modelo del desplegable de modelos disponibles para la marca seleccionada. */
    private DefaultComboBoxModel<String> modelModelos;

    /** Desplegable para seleccionar el modelo del vehículo. */
    private JComboBox<String> cbModelo;

    /** Spinner para introducir el precio del vehículo en euros. */
    private JSpinner spPrecio;

    /** Campo de texto para introducir la matrícula del vehículo. */
    private JTextField txtMatricula;

    /** Spinner para introducir el componente rojo del color (0-255). */
    private JSpinner spRojo;

    /** Spinner para introducir el componente verde del color (0-255). */
    private JSpinner spVerde;

    /** Spinner para introducir el componente azul del color (0-255). */
    private JSpinner spAzul;

    /** Spinner para introducir el año de fabricación del vehículo. */
    private JSpinner spYear;

    /** Spinner para introducir el kilometraje del vehículo en kilómetros. */
    private JSpinner spKilometraje;

    /** Spinner para introducir la potencia del motor en CV. */
    private JSpinner spPotencia;

    /** Spinner para introducir la cilindrada del motor en cc. */
    private JSpinner spCilindrada;

    /** Spinner para introducir el peso del vehículo en kg. */
    private JSpinner spPeso;

    /** Botón cuyo fondo muestra una vista previa del color RGB seleccionado. */
    private JButton btnVerColor;

    /** Etiqueta del campo de modelo. */
    private JLabel lblModelo;

    /** Etiqueta del campo de marca. */
    private JLabel lblMarca;

    /** Etiqueta del campo de precio. */
    private JLabel lblPrecio;

    /** Botón que carga los modelos disponibles para la marca seleccionada. */
    private JButton btnBuscarMarca;

    /** Botón que elimina el vehículo seleccionado en la lista. */
    private JButton btnEliminarVehiculo;

    /** Botón que carga el vehículo seleccionado en el panel de modificación. */
    private JButton btnModificarVehiculo;

    /** Modelo de la lista que almacena los vehículos del catálogo. */
    private DefaultListModel<Vehiculo> modelVehiculos;

    /** Lista visual que muestra todos los vehículos registrados en el catálogo. */
    private JList<Vehiculo> listVehiculos;

    /** Lista de modelos cargados para la marca actualmente seleccionada. */
    private ArrayList<ModeloVehiculo> modelos;

    /** Lista de vehículos actualmente mostrados en la lista del panel. */
    private ArrayList<Vehiculo> vehiculos;

    /**
     * Crea el panel de nuevo vehículo e inicializa sus componentes.
     */
    public PNuevoVehiculo() {
        setLayout(null);
        setSize(VPrincipal.ANCHO, VPrincipal.ALTO);
        crearComponentes();
    }

    /**
     * Actualiza el desplegable de marcas con la lista proporcionada de marcas.
     * @param marcas lista de nombres de marcas a mostrar en el desplegable
     */
    public void actualizarMarcas(ArrayList<String> marcas) {
        modelMarcas.removeAllElements();
        for (String m : marcas) {
            modelMarcas.addElement(m);
        }
    }

    /**
     * Actualiza el desplegable de modelos con la lista proporcionada de modelos.
     * <p>
     * También actualiza la lista interna de modelos usada para construir
     * el objeto {@link Vehiculo} al recuperar los datos del formulario.
     * @param modelos lista de modelos a mostrar en el desplegable.
     */
    public void actualizarModelos(ArrayList<ModeloVehiculo> modelos) {
        this.modelos = modelos;
        modelModelos.removeAllElements();
        for (ModeloVehiculo m : modelos) {
            modelModelos.addElement(m.getNombreModelo());
        }
    }

    /**
     * Actualiza la lista de vehículos del catálogo con los elementos proporcionados.
     * <p>
     * Elimina todos los elementos anteriores e inserta los nuevos,
     * reflejando el estado actual de la base de datos.
     * @param vehiculos lista de vehículos a mostrar en la lista.
     */
    public void actualizarVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
        modelVehiculos.removeAllElements();
        for (Vehiculo v : vehiculos) {
            modelVehiculos.addElement(v);
        }
    }

    /**
     * Devuelve el vehículo actualmente seleccionado en la lista del catálogo.
     * @return vehículo seleccionado, o null si no hay ninguno seleccionado.
     */
    public Vehiculo getVehiculoSeleccionado() {
        int indexVehiculo = listVehiculos.getSelectedIndex();
        if (indexVehiculo == -1) {
            return null;
        }
        return vehiculos.get(indexVehiculo);
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
     * El ID se establece a -1 ya que el vehículo aún no ha
     * sido persistido en la base de datos. El color se construye en
     * formato RGB a partir de los spinners de los componentes RGB.
     * @return vehículo con los datos del formulario listo para insertar
     */
    public Vehiculo getVehiculo(){
        int precio=(int)spPrecio.getValue();

        int indexModelo=cbModelo.getSelectedIndex();
        ModeloVehiculo m=modelos.get(indexModelo);
        
        String matricula=txtMatricula.getText().trim();
        String color = "#" + Integer.toHexString(new java.awt.Color((int)spRojo.getValue(), (int)spVerde.getValue(), (int)spAzul.getValue()).getRGB()).substring(2).toUpperCase();
        int year=(int)spYear.getValue();
        int kilometraje=(int)spKilometraje.getValue();
        int potencia=(int)spPotencia.getValue();
        int cilindrada=(int)spCilindrada.getValue();
        int peso=(int)spPeso.getValue();

        return new Vehiculo(-1,m,precio,matricula,color,year,kilometraje,potencia,cilindrada,peso);
    }

    /**
     * Crea e inicializa todos los componentes visuales del formulario:
     * desplegables de marca y modelo, spinners de precio, año, kilometraje,
     * potencia, cilindrada y peso, campo de matrícula, spinners RGB de color,
     * botón de vista previa de color, botón de buscar marca, lista de vehículos
     * del catálogo y botones de guardar, eliminar y modificar.
     */
    public void crearComponentes() {
        JLabel lblTitulo = new JLabel("Nuevo Vehiculo");
        lblTitulo.setBounds(80, 26, 160, 20);
        add(lblTitulo);

        modelMarcas = new DefaultComboBoxModel<String>();
        cbMarca = new JComboBox<String>(modelMarcas);
        cbMarca.setBounds(185, 103, 150, 25);
        add(cbMarca);

        modelModelos = new DefaultComboBoxModel<>();
        cbModelo = new JComboBox<String>(modelModelos);
        cbModelo.setBounds(185, 143, 150, 25);
        add(cbModelo);

        spPrecio = new JSpinner(new SpinnerNumberModel(20000, 0, 1000000, 1000));
        spPrecio.setBounds(185, 183, 150, 25);
        add(spPrecio);

        btnGuardar = new JButton(GUARDAR_VEHICULO_BTN);
        btnGuardar.setActionCommand(GUARDAR_VEHICULO_BTN);
        btnGuardar.setBounds(80, 436, 150, 25);
        add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setActionCommand(LIMPIAR_VEHICULO_BTN);
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

        JLabel lblMatricula = new JLabel("Matrícula");
        lblMatricula.setBounds(80, 222, 95, 14);
        add(lblMatricula);

        JLabel lblColor = new JLabel("Color");
        lblColor.setBounds(80, 253, 95, 14);
        add(lblColor);

        JLabel lblYear = new JLabel("Año");
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

        spRojo = new JSpinner();
        spRojo.setModel(new SpinnerNumberModel(0, 0, 255, 15));
        spRojo.setBounds(185, 250, 55, 20);
        add(spRojo);

        spVerde = new JSpinner(new SpinnerNumberModel(0, 0, 255, 15));
        spVerde.setBounds(250, 250, 55, 20);
        add(spVerde);

        spAzul = new JSpinner(new SpinnerNumberModel(0, 0, 255, 15));
        spAzul.setBounds(315, 250, 55, 20);
        add(spAzul);

        btnVerColor = new JButton(VER_COLOR_BTN);
        btnVerColor.setBounds(380, 249, 89, 23);
        add(btnVerColor);

        lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(80, 148, 95, 14);
        add(lblModelo);

        lblMarca = new JLabel("Marca");
        lblMarca.setBounds(80, 108, 95, 14);
        add(lblMarca);

        lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(80, 188, 95, 14);
        add(lblPrecio);

        btnBuscarMarca = new JButton(BUSCAR_MARCA_BTN);
        btnBuscarMarca.setBounds(345, 104, 150, 25);
        add(btnBuscarMarca);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(636, 91, 189, 194);
        add(scrollPane);

        modelVehiculos = new DefaultListModel<Vehiculo>();
        listVehiculos = new JList<Vehiculo>(modelVehiculos);
        scrollPane.setViewportView(listVehiculos);

        JLabel lblVehculosALa = new JLabel("Vehículos a la venta");
        lblVehculosALa.setBounds(636, 66, 142, 14);
        add(lblVehculosALa);

        btnEliminarVehiculo = new JButton(ELIMINAR_VEHICULO_BTN);
        btnEliminarVehiculo.setActionCommand(ELIMINAR_VEHICULO_BTN);
        btnEliminarVehiculo.setBounds(636, 311, 150, 25);
        add(btnEliminarVehiculo);

        btnModificarVehiculo = new JButton(MODIFICAR_VEHICULO_BTN);
        btnModificarVehiculo.setActionCommand(MODIFICAR_VEHICULO_BTN);
        btnModificarVehiculo.setBounds(636, 359, 150, 25);
        add(btnModificarVehiculo);

        actualizarColor();
        setFormularioHabilitado(false);
    }

    public void setFormularioHabilitado(boolean habilitado) {
        cbModelo.setEnabled(habilitado);
        spPrecio.setEnabled(habilitado);
        txtMatricula.setEnabled(habilitado);
        spRojo.setEnabled(habilitado);
        spVerde.setEnabled(habilitado);
        spAzul.setEnabled(habilitado);
        spYear.setEnabled(habilitado);
        spKilometraje.setEnabled(habilitado);
        spPotencia.setEnabled(habilitado);
        spCilindrada.setEnabled(habilitado);
        spPeso.setEnabled(habilitado);
        btnVerColor.setEnabled(habilitado);
        btnGuardar.setEnabled(habilitado);
        btnLimpiar.setEnabled(habilitado);
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
     * de guardar, ver color, buscar marca, eliminar vehículo y modificar vehículo.
     * @param c controlador principal de la aplicación.
     */
    public void setControlador(ConcesionarioControlador c) {
        btnGuardar.addActionListener(c);
        btnLimpiar.addActionListener(c);
        btnVerColor.addActionListener(c);
        btnBuscarMarca.addActionListener(c);
        btnEliminarVehiculo.addActionListener(c);
        btnModificarVehiculo.addActionListener(c);
    }
}

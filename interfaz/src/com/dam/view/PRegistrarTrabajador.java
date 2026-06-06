package com.dam.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Trabajador;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Panel para la gestión y registro de trabajadores dentro del sistema del concesionario.
 * <p>
 * Ofrece un formulario para dar de alta a nuevos empleados con credenciales y niveles
 * de privilegio (administrador), además de una tabla para visualizar el listado existente
 * y permitir la eliminación de registros.
 * @see IPanel
 * @see ConcesionarioControlador
 * @see Trabajador
 */
public class PRegistrarTrabajador extends JPanel implements IPanel {
    /** Ancho estático predefinido para el panel en píxeles. */
    private static final int ANCHO = 1000;
    /** Alto estático predefinido para el panel en píxeles. */
    private static final int ALTO = 1000;

    /** Comando de acción asignado al botón para dar de alta un nuevo trabajador. */
    public static final String REGISTRAR_TRABAJADOR_BTN = "Registrar Trabajador";
    /** Comando de acción asignado al botón para dar de baja un trabajador. */
    public static final String ELIMINAR_TRABAJADOR_BTN  = "Eliminar Trabajador";
    /** Comando de acción asignado al botón para limpiar los campos del formulario. */
    public static final String LIMPIAR_DATOS_BTN        = "Limpiar Datos Trabajador";

    /** Campo de texto para rellenar el nombre completo del empleado. */
    private JTextField tfEmpleado;
    /** Campo reservado para escribir la contraseña de la cuenta. */
    private JPasswordField tfPasswd;
    /** Campo reservado para repetir la contraseña y verificar que coincida. */
    private JPasswordField tfConfirmPasswd;
    /** Botón para desencadenar el registro del nuevo trabajador. */
    private JButton btnRegistro;
    /** Tabla interactiva que despliega los trabajadores almacenados en la base de datos. */
    private JTable table;
    /** Botón para proceder con el borrado del trabajador seleccionado de la lista. */
    private JButton btnEliminarTrabajador;
    /** Modelo estructurado que maneja el flujo de filas y columnas de la tabla. */
    private DefaultTableModel dtmTrabajadores;

    /** Título de cabecera para la columna de ID único. */
    private static final String CLM_ID_TRABAJADOR   = "ID";
    /** Título de cabecera para la columna de nombre de usuario. */
    private static final String CLM_NOMBRE_APELLIDOS = "Nombre y apellidos";
    /** Título de cabecera para la columna que representa la clave de acceso. */
    private static final String CLM_PASSWORD         = "Contraseña";
    /** Título de cabecera para la columna que indica si el rol es administrativo o básico. */
    private static final String CLM_ES_ADMIN         = "Estado";

    /** Botón para vaciar los cuadros de texto y deseleccionar elementos de la vista. */
    private JButton btnLimpiarDatos;
    /** Casilla de selección múltiple para habilitar los permisos de administración. */
    private JCheckBox chckbxAdmin;

    /**
     * Constructor principal.
     * Invoca los métodos internos para configurar el layout base y desplegar los elementos visuales.
     */
    public PRegistrarTrabajador() {
        configurarPanel();
        crearComponentes();
    }

    /**
     * Establece una disposición absoluta en base a coordenadas nulas (null layout)
     * y redimensiona los límites del contenedor principal.
     */
    public void configurarPanel() {
        setLayout(null);
        setSize(ANCHO, ALTO);
    }

    /**
     * Instancia, dimensiona mediante coordenadas fijas y añade al panel todos los
     * componentes gráficos, incluyendo rótulos, entradas, la tabla y los botones.
     */
    public void crearComponentes() {
        JLabel lblTitulo = new JLabel("Registra a un nuevo trabajador");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTitulo.setBounds(25, 20, 212, 20);
        add(lblTitulo);

        JLabel lblNombreEmpleado = new JLabel("Nombre del empleado:");
        lblNombreEmpleado.setBounds(10, 62, 143, 20);
        add(lblNombreEmpleado);

        tfEmpleado = new JTextField();
        tfEmpleado.setBounds(190, 60, 150, 25);
        add(tfEmpleado);

        JLabel lblPasswdEmpleado = new JLabel("Contraseña:");
        lblPasswdEmpleado.setBounds(10, 102, 143, 20);
        add(lblPasswdEmpleado);

        tfPasswd = new JPasswordField();
        tfPasswd.setBounds(190, 100, 150, 25);
        add(tfPasswd);

        JLabel lblConfirmarContrasea = new JLabel("Confirmar contraseña:");
        lblConfirmarContrasea.setBounds(10, 146, 170, 25);
        add(lblConfirmarContrasea);

        tfConfirmPasswd = new JPasswordField();
        tfConfirmPasswd.setBounds(190, 152, 150, 25);
        add(tfConfirmPasswd);

        chckbxAdmin = new JCheckBox("Administrador");
        chckbxAdmin.setBounds(25, 202, 128, 20);
        add(chckbxAdmin);

        btnRegistro = new JButton("Registrar trabajador");
        btnRegistro.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnRegistro.setActionCommand(REGISTRAR_TRABAJADOR_BTN);
        btnRegistro.setBounds(10, 238, 165, 25);
        add(btnRegistro);

        btnLimpiarDatos = new JButton("Limpiar datos");
        btnLimpiarDatos.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnLimpiarDatos.setActionCommand(LIMPIAR_DATOS_BTN);
        btnLimpiarDatos.setBounds(190, 238, 150, 25);
        add(btnLimpiarDatos);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(374, 23, 519, 254);
        add(scrollPane);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);
        configurarTabla();

        btnEliminarTrabajador = new JButton("Eliminar trabajador");
        btnEliminarTrabajador.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnEliminarTrabajador.setActionCommand(ELIMINAR_TRABAJADOR_BTN);
        btnEliminarTrabajador.setBounds(743, 300, 150, 25);
        add(btnEliminarTrabajador);
    }

    /**
     * Configura el comportamiento de edición de celdas anulándolo para el usuario,
     * inicializa los nombres de las columnas y define la anchura preferida de cada una.
     */
    private void configurarTabla() {
        dtmTrabajadores = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(dtmTrabajadores);
        dtmTrabajadores.addColumn(CLM_ID_TRABAJADOR);
        dtmTrabajadores.addColumn(CLM_NOMBRE_APELLIDOS);
        dtmTrabajadores.addColumn(CLM_PASSWORD);
        dtmTrabajadores.addColumn(CLM_ES_ADMIN);
        table.getColumn(CLM_ID_TRABAJADOR).setPreferredWidth(50);
        table.getColumn(CLM_NOMBRE_APELLIDOS).setPreferredWidth(150);
        table.getColumn(CLM_PASSWORD).setPreferredWidth(100);
        table.getColumn(CLM_ES_ADMIN).setPreferredWidth(100);
    }

    /**
     * Vacia el listado visual actual e inserta una nueva tanda de filas en base
     * a los elementos recibidos en la colección.
     * @param listaTrabajadores colección dinámica con la información de los empleados a cargar
     */
    public void cargarTabla(ArrayList<Trabajador> listaTrabajadores) {
        table.clearSelection();
        dtmTrabajadores.getDataVector().clear();
        dtmTrabajadores.fireTableDataChanged();
        for (Trabajador trabajador : listaTrabajadores) {
            dtmTrabajadores.addRow(new Object[]{
                trabajador.getIdTrabajador(),
                trabajador.getNombreTrabajador(),
                trabajador.getPasswordTrabajador(),
                trabajador.traducirAdmin(trabajador.getEsAdmin())
            });
        }
    }

    /**
     * Identifica el valor de la celda de identificación correspondiente a la fila
     * que ha sido marcada por el usuario.
     * @return el número ID del trabajador seleccionado, o -1 si ninguna fila está activa
     */
    public int getIdTrabajadorSeleccionado() {
        int fila = table.getSelectedRow();
        if (fila == -1) return -1;
        return (int) dtmTrabajadores.getValueAt(fila, 0);
    }

    /**
     * Vuelca la información de un objeto Trabajador concreto sobre los cuadros de entrada
     * y la casilla de control del panel.
     * @param t el objeto que contiene las propiedades a reflejar en la interfaz
     */
    public void cargarTrabajador(Trabajador t) {
        tfEmpleado.setText(t.getNombreTrabajador());
        tfPasswd.setText(t.getPasswordTrabajador());
        tfConfirmPasswd.setText(t.getPasswordTrabajador());
        chckbxAdmin.setSelected(t.getEsAdmin() == 1);
    }

    /**
     * Limpia por completo las cajas de texto de contraseña y nombre, devuelve el
     * checkbox de administrador a su estado inicial desactivado y borra la selección de la tabla.
     */
    public void limpiarDatos() {
        tfEmpleado.setText("");
        tfPasswd.setText("");
        tfConfirmPasswd.setText("");
        chckbxAdmin.setSelected(false);
        table.clearSelection();
    }

    /**
     * Recopila los textos y la selección de la interfaz, realiza las validaciones lógicas
     * necesarias y devuelve una instancia estructurada del empleado.
     * <p>
     * Lanza advertencias emergentes si algún criterio obligatorio es omitido o es incorrecto.
     * @return un objeto de tipo Trabajador correctamente validado, o null si falla alguna comprobación
     */
    public Trabajador obtenerDatos() {
        String nombre = tfEmpleado.getText().trim();
        if (nombre.isEmpty()) {
            Avisos.error(this, "El nombre del empleado no puede estar vacío.");
            return null;
        }

        String passwd = new String(tfPasswd.getPassword());
        String confirm = new String(tfConfirmPasswd.getPassword());

        if (passwd.isEmpty()) {
            Avisos.error(this, "La contraseña no puede estar vacía.");
            return null;
        }
        if (!passwd.equals(confirm)) {
            Avisos.error(this, "Las contraseñas no coinciden.");
            return null;
        }

        int esAdmin = chckbxAdmin.isSelected() ? 1 : 0;

        return new Trabajador(nombre, passwd, esAdmin);
    }

    /**
     * Enlaza el controlador principal del concesionario con los botones interactivos
     * del panel para recibir los eventos de interacción periférica.
     * @param c el manejador controlador central de flujos de negocio y navegación
     */
    public void setControlador(ConcesionarioControlador c) {
        btnRegistro.addActionListener(c);
        btnEliminarTrabajador.addActionListener(c);
        btnLimpiarDatos.addActionListener(c);
    }

    /**
     * Proporciona acceso directo al botón encargado de disparar el registro de datos.
     * @return el objeto JButton de registro
     */
    public JButton getBtnRegistro() {
        return btnRegistro; 
    }
    
    /**
     * Proporciona acceso directo al botón encargado de disparar la baja de datos.
     * @return el objeto JButton de eliminación
     */
    public JButton getBtnEliminarTrabajador() {
        return btnEliminarTrabajador;
    }
}
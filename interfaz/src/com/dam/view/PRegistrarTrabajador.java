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

public class PRegistrarTrabajador extends JPanel implements IPanel {
    private static final int ANCHO = 1000;
    private static final int ALTO = 1000;

    public static final String REGISTRAR_TRABAJADOR_BTN = "Registrar Trabajador";
    public static final String ELIMINAR_TRABAJADOR_BTN  = "Eliminar Trabajador";
    public static final String LIMPIAR_DATOS_BTN        = "Limpiar Datos Trabajador";

    private JTextField tfEmpleado;
    private JPasswordField tfPasswd;
    private JPasswordField tfConfirmPasswd;
    private JButton btnRegistro;
    private JTable table;
    private JButton btnEliminarTrabajador;
    private DefaultTableModel dtmTrabajadores;

    private static final String CLM_ID_TRABAJADOR   = "ID";
    private static final String CLM_NOMBRE_APELLIDOS = "Nombre y apellidos";
    private static final String CLM_PASSWORD         = "Contraseña";
    private static final String CLM_ES_ADMIN         = "Estado";

    private JButton btnLimpiarDatos;
    private JCheckBox chckbxAdmin;

    public PRegistrarTrabajador() {
        configurarPanel();
        crearComponentes();
    }

    public void configurarPanel() {
        setLayout(null);
        setSize(ANCHO, ALTO);
    }

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


    public void cargarTabla(ArrayList<Trabajador> listaTrabajadores) {
        table.clearSelection();
        dtmTrabajadores.getDataVector().clear();
        dtmTrabajadores.fireTableDataChanged();
        for (Trabajador trabajador : listaTrabajadores) {
            dtmTrabajadores.addRow(new Object[]{
                trabajador.getIdTrabajador(),
                trabajador.getNombreApellidos(),
                trabajador.getPasswordTrabajador(),
                trabajador.traducirAdmin(trabajador.getEsAdmin())
            });
        }
    }

    public int getIdTrabajadorSeleccionado() {
        int fila = table.getSelectedRow();
        if (fila == -1) return -1;
        return (int) dtmTrabajadores.getValueAt(fila, 0);
    }


    public void cargarTrabajador(Trabajador t) {
        tfEmpleado.setText(t.getNombreApellidos());
        tfPasswd.setText(t.getPasswordTrabajador());
        tfConfirmPasswd.setText(t.getPasswordTrabajador());
        chckbxAdmin.setSelected(t.getEsAdmin() == 1);
    }


    public void limpiarDatos() {
        tfEmpleado.setText("");
        tfPasswd.setText("");
        tfConfirmPasswd.setText("");
        chckbxAdmin.setSelected(false);
        table.clearSelection();
    }


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


    public void setControlador(ConcesionarioControlador c) {
        btnRegistro.addActionListener(c);
        btnEliminarTrabajador.addActionListener(c);
        btnLimpiarDatos.addActionListener(c);
    }

    // Getters de botones (por si se necesitan externamente)
    public JButton getBtnRegistro()           { return btnRegistro; }
    public JButton getBtnEliminarTrabajador() { return btnEliminarTrabajador; }
}
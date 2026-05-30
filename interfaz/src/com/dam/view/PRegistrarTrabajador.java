package com.dam.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Login;
import com.dam.model.data.Trabajador;

import javax.swing.JCheckBox;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PRegistrarTrabajador extends JPanel implements IPanel{
    private static final int ANCHO=1000;
    private static final int ALTO=1000;

    public static final String LOGIN_BTN = "Acceder";

    private JTextField tfEmpleado;
    private JPasswordField tfPasswd;
    private JButton btnRegistro;
    private JPasswordField passwordField;
    private JTable table;
    private JButton btnModificarTrabajador;
    private JButton btnEliminarTrabajador;
    private DefaultTableModel dtmTrabajadores;
    
    private static final String CLM_ID_TRABAJADOR = "ID";
    private static final String CLM_NOMBRE_APELLIDOS = "Nombre y apellidos";
    private static final String CLM_PASSWORD = "Contraseña";
    private static final String CLM_ES_ADMIN = "Estado";
    
    private JButton btnLimpiarDatos;
    private JCheckBox chckbxNewCheckBox;

    public PRegistrarTrabajador() {
        configurarPanel();
        crearComponentes();
    }

    public void configurarPanel() {
        setLayout(null);
        setSize(ANCHO,ALTO);
    }

    public Login getLogin() {
        return new Login(tfEmpleado.getText(), tfPasswd.getText());
    }

    public void crearComponentes() {
        JLabel lblTitulo = new JLabel("Registra a un nuevo trabajador");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTitulo.setBounds(25, 20, 212, 20);
        add(lblTitulo);

        JLabel lblNombreEmpleado = new JLabel("Nombre del empleado: ");
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

        btnRegistro = new JButton("Registrar trabajador");
        btnRegistro.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnRegistro.setActionCommand(LOGIN_BTN);
        btnRegistro.setBounds(10, 238, 143, 25);
        add(btnRegistro);
        
        JLabel lblConfirmarContrasea = new JLabel("Confirmar contraseña: ");
        lblConfirmarContrasea.setBounds(10, 146, 170, 25);
        add(lblConfirmarContrasea);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(190, 152, 150, 25);
        add(passwordField);
        
        chckbxNewCheckBox = new JCheckBox("Administrador");
        chckbxNewCheckBox.setBounds(25, 202, 128, 20);
        add(chckbxNewCheckBox);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(374, 23, 519, 254);
        add(scrollPane);
        
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBounds(0, 0, 1, 1);
        scrollPane.setViewportView(table);
        configurarTabla();
        
        btnModificarTrabajador = new JButton("Modificar trabajador");
        btnModificarTrabajador.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnModificarTrabajador.setActionCommand("Acceder");
        btnModificarTrabajador.setBounds(371, 300, 150, 25);
        add(btnModificarTrabajador);
        
        btnEliminarTrabajador = new JButton("Eliminar trabajador");
        btnEliminarTrabajador.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnEliminarTrabajador.setActionCommand("Acceder");
        btnEliminarTrabajador.setBounds(743, 300, 150, 25);
        add(btnEliminarTrabajador);
        
        btnLimpiarDatos = new JButton("Limpiar datos");
        btnLimpiarDatos.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnLimpiarDatos.setActionCommand("Acceder");
        btnLimpiarDatos.setBounds(197, 240, 143, 25);
        add(btnLimpiarDatos);
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
		table.getColumn(CLM_NOMBRE_APELLIDOS).setPreferredWidth(100);
		table.getColumn(CLM_PASSWORD).setPreferredWidth(75);
		table.getColumn(CLM_ES_ADMIN).setPreferredWidth(50);
    }
    
    public void cargarTabla(ArrayList<Trabajador> listaTrabajadores) {
    	table.clearSelection();
    	dtmTrabajadores.getDataVector().clear();
    	
    	Object[] fila = new Object[4];
    	
    	for (Trabajador trabajador : listaTrabajadores) {
    		fila[0] = trabajador.getIdTrabajador();
    		fila[1] = trabajador.getNombreApellidos();
    		fila[2] = trabajador.getPasswordTrabajador();
    		fila[3] = trabajador.traducirAdmin(trabajador.getEsAdmin());
    		
    		dtmTrabajadores.addRow(fila);
    	}
    	
    }
    
    public void limpiarDatos() {
    	tfEmpleado.setText("");
    	passwordField.setText("");
    	tfPasswd.setText("");
    	chckbxNewCheckBox.setSelected(false);
    }
    
    public Trabajador obtenerDatos() {
    	Trabajador trabajador = null;
    	
    	String nombreApellidos = tfEmpleado.getText();
    	String passwordEmpleado = null;
    	if (passwordField.equals(tfPasswd)) {
    		passwordEmpleado = passwordField.getText();
    	}
    	
    	int esAdmin;
    	if (chckbxNewCheckBox.isSelected() == true) {
    		esAdmin = 1;
    	} else  {
    		esAdmin = 0;
    	}
    	
    	trabajador = new Trabajador(nombreApellidos, passwordEmpleado, esAdmin);
    	
    	return trabajador;
    	
    }
    
	public void setControlador(ConcesionarioControlador c) {
        btnRegistro.addActionListener(c);
        btnModificarTrabajador.addActionListener(c);
        btnEliminarTrabajador.addActionListener(c);
    }

	public JButton getBtnRegistro() {
		return btnRegistro;
	}

	public JButton getBtnModificarTrabajador() {
		return btnModificarTrabajador;
	}

	public JButton getBtnEliminarTrabajador() {
		return btnEliminarTrabajador;
	}
    
    
}

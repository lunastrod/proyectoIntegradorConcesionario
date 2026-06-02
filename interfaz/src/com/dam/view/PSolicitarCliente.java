package com.dam.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Login;

/**
 * Panel para solicitar los datos de un cliente dentro de la aplicación.
 * <p>
 * Contiene un formulario con campos para el nombre, método de pago y credenciales,
 * permitiendo capturar la información e interactuar con el controlador principal.
 * @see IPanel
 * @see ConcesionarioControlador
 */
public class PSolicitarCliente extends JPanel implements IPanel {
    
    /** Ancho del panel en píxeles. */
    private static final int ANCHO = 600;
    
    /** Alto del panel en píxeles. */
    private static final int ALTO = 400;

    /** Comando de acción y texto del botón de acceso. */
    public static final String LOGIN_BTN = "Acceder";

    /** Campo de texto para introducir el nombre del usuario. */
    JTextField tfEmpleado;
    
    /** Campo de contraseña para introducir la clave de acceso. */
    JPasswordField tfPasswd;
    
    /** Botón para procesar la solicitud de acceso o confirmación. */
    JButton btnLogin;
    
    /** Etiqueta secundaria para indicar el campo de método de pago. */
    private JLabel lblPasswdEmpleado_1;
    
    /** Campo de texto para introducir el método de pago del cliente. */
    private JTextField textField;

    /**
     * Constructor de la clase.
     * Inicializa la configuración geométrica del panel y construye sus componentes visuales.
     */
    public PSolicitarCliente() {
        configurarPanel();
        crearComponentes();
    }

    /**
     * Configura las propiedades básicas del contenedor del panel,
     * estableciendo un diseño nulo (diseño absoluto) y sus dimensiones.
     */
    public void configurarPanel() {
        setLayout(null);
        setSize(ANCHO, ALTO);
    }

    /**
     * Obtiene los datos de autenticación introducidos en el formulario.
     * @return un objeto Login que encapsula el texto del empleado y la contraseña
     */
    public Login getLogin() {
        return new Login(tfEmpleado.getText(), tfPasswd.getText());
    }

    /**
     * Crea, posiciona y añade todos los componentes gráficos del formulario al panel,
     * incluyendo etiquetas de texto, campos de entrada de datos y el botón de acción.
     */
    public void crearComponentes() {
        JLabel lblTitulo = new JLabel("login como empleado");
        lblTitulo.setBounds(25, 20, 160, 20);
        add(lblTitulo);

        JLabel lblNombreEmpleado = new JLabel("Nombre");
        lblNombreEmpleado.setBounds(25, 60, 160, 20);
        add(lblNombreEmpleado);

        tfEmpleado = new JTextField();
        tfEmpleado.setBounds(130, 60, 150, 25);
        add(tfEmpleado);

        JLabel lblPasswdEmpleado = new JLabel("Método de pago");
        lblPasswdEmpleado.setBounds(25, 144, 160, 20);
        add(lblPasswdEmpleado);

        tfPasswd = new JPasswordField();
        tfPasswd.setBounds(130, 144, 150, 25);
        add(tfPasswd);

        btnLogin = new JButton(LOGIN_BTN);
        btnLogin.setActionCommand(LOGIN_BTN);
        btnLogin.setBounds(130, 180, 150, 25);
        add(btnLogin);
        
        lblPasswdEmpleado_1 = new JLabel("Método de pago");
        lblPasswdEmpleado_1.setBounds(25, 106, 160, 20);
        add(lblPasswdEmpleado_1);
        
        textField = new JTextField();
        textField.setBounds(130, 106, 150, 25);
        add(textField);
    }

    /**
     * Registra el controlador de la aplicación como el escuchador de eventos
     * para el botón de acción principal de este panel.
     * @param c el controlador principal encargado de gestionar los eventos de la interfaz
     */
    public void setControlador(ConcesionarioControlador c) {
        btnLogin.addActionListener(c);
    }
}
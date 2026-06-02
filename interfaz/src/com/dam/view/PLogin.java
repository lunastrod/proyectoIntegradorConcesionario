package com.dam.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Login;

/**
 * Panel de inicio de sesión para los trabajadores del concesionario.
 * <p>
 * Presenta un formulario con los campos de nombre de usuario y contraseña,
 * y un botón para enviar las credenciales al controlador. Los datos
 * introducidos se encapsulan en un objeto {@link Login} mediante
 * el método {@link #getLogin()}.
 * @see Login
 * @see IPanel
 * @see ConcesionarioControlador
 */
public class PLogin extends JPanel implements IPanel {

    /** Ancho del panel en píxeles. */
    private static final int ANCHO = 600;

    /** Alto del panel en píxeles. */
    private static final int ALTO = 400;

    /** Comando de acción y texto del botón de acceso. */
    public static final String LOGIN_BTN = "Acceder";

    /** Campo de texto para introducir el nombre del trabajador. */
    JTextField tfEmpleado;

    /** Campo de contraseña para introducir la clave del trabajador. */
    JPasswordField tfPasswd;

    /** Botón que dispara el evento de inicio de sesión. */
    JButton btnLogin;

    /**
     * Crea el panel de login configurando su tamaño y creando sus componentes.
     */
    public PLogin() {
        configurarPanel();
        crearComponentes();
    }

    /**
     * Configura el layout y el tamaño del panel.
     */
    public void configurarPanel() {
        setLayout(null);
        setSize(ANCHO, ALTO);
    }

    /**
     * Construye y devuelve un objeto {@link Login} con los datos
     * introducidos en el formulario en el momento de la llamada.
     * @return objeto Login con el nombre de usuario y la contraseña introducidos
     */
    public Login getLogin() {
        return new Login(tfEmpleado.getText(), tfPasswd.getText());
    }

    /**
     * Crea e inicializa todos los componentes visuales del panel:
     * etiquetas, campo de nombre, campo de contraseña y botón de acceso.
     */
    public void crearComponentes() {
        JLabel lblTitulo = new JLabel("login como empleado");
        lblTitulo.setBounds(25, 20, 160, 20);
        add(lblTitulo);

        JLabel lblNombreEmpleado = new JLabel("Empleado:");
        lblNombreEmpleado.setBounds(25, 60, 160, 20);
        add(lblNombreEmpleado);

        tfEmpleado = new JTextField();
        tfEmpleado.setBounds(130, 60, 150, 25);
        add(tfEmpleado);

        JLabel lblPasswdEmpleado = new JLabel("Contraseña:");
        lblPasswdEmpleado.setBounds(25, 100, 160, 20);
        add(lblPasswdEmpleado);

        tfPasswd = new JPasswordField();
        tfPasswd.setBounds(130, 100, 150, 25);
        add(tfPasswd);

        btnLogin = new JButton(LOGIN_BTN);
        btnLogin.setActionCommand(LOGIN_BTN);
        btnLogin.setBounds(130, 180, 150, 25);
        add(btnLogin);
    }

    /**
     * Registra el controlador como ActionListener del botón de acceso.
     * @param c controlador principal de la aplicación
     */
    public void setControlador(ConcesionarioControlador c) {
        btnLogin.addActionListener(c);
    }
}
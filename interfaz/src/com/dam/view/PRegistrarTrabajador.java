package com.dam.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Login;
import javax.swing.JCheckBox;
import java.awt.Font;

public class PRegistrarTrabajador extends JPanel implements IPanel{
    private static final int ANCHO=600;
    private static final int ALTO=400;

    public static final String LOGIN_BTN="Acceder";

    JTextField tfEmpleado;
    JPasswordField tfPasswd;
    JButton btnLogin;
    private JPasswordField passwordField;

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
        lblNombreEmpleado.setBounds(25, 62, 160, 20);
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

        btnLogin = new JButton("Registrar trabajador");
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnLogin.setActionCommand(LOGIN_BTN);
        btnLogin.setBounds(37, 237, 150, 25);
        add(btnLogin);
        
        JLabel lblConfirmarContrasea = new JLabel("Confirmar contraseña: ");
        lblConfirmarContrasea.setBounds(25, 148, 160, 20);
        add(lblConfirmarContrasea);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(130, 146, 150, 25);
        add(passwordField);
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("Administrador");
        chckbxNewCheckBox.setBounds(25, 202, 128, 20);
        add(chckbxNewCheckBox);
    }

    public void setControlador(ConcesionarioControlador c) {
        btnLogin.addActionListener(c);
    }
}

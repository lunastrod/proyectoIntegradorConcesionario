package com.dam.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Login;

public class PSolicitarCliente extends JPanel implements IPanel{
    private static final int ANCHO=600;
    private static final int ALTO=400;

    public static final String LOGIN_BTN="Acceder";

    JTextField tfEmpleado;
    JPasswordField tfPasswd;
    JButton btnLogin;
    private JLabel lblPasswdEmpleado_1;
    private JTextField textField;

    public PSolicitarCliente() {
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

    public void setControlador(ConcesionarioControlador c) {
        btnLogin.addActionListener(c);
    }
}

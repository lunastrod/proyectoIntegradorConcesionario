package com.dam.view;

import javax.swing.JPanel;

import com.dam.control.ConcesionarioControlador;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class PVehiculo extends JPanel implements IPanel{
    JButton btnInfo;
	public PVehiculo(String actionCommand) {
		crearComponentes();
        btnInfo.setActionCommand(actionCommand);
        setSize(PVerCatalogo.ANCHO_PANEL_VEHICULO,PVerCatalogo.ALTO_PANEL_VEHICULO);
        //cambiar color de fondo
        setBackground(new Color(128,128,128));
	}
    public void crearComponentes(){
		setLayout(null);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setBounds(10, 36, 140, 14);
		add(lblPrecio);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblModelo.setBounds(10, 11, 140, 14);
		add(lblModelo);
		
        btnInfo = new JButton("Más información");
        btnInfo.setBounds(10, 126, 140, 23);
        add(btnInfo);
    }

	public void setControlador(ConcesionarioControlador c){
        btnInfo.addActionListener(c);
    }
}

package com.dam.view;

import javax.swing.JPanel;

import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Vehiculo;

import java.awt.Color;


import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class PVehiculo extends JPanel implements IPanel{
    JButton btnInfo;
	JLabel lblPrecio;
	JLabel lblModelo;


	public PVehiculo() {
		crearComponentes();
        
        setSize(PVerCatalogo.ANCHO_PANEL_VEHICULO,PVerCatalogo.ALTO_PANEL_VEHICULO);
        //cambiar color de fondo
        setBackground(new Color(128,128,128));
	}

	public void setVehiculo(Vehiculo vehiculo){
		lblPrecio.setText(vehiculo.getPrecio()+" €");
		lblModelo.setText(vehiculo.getModelo().getNombreModelo());
		btnInfo.setActionCommand("masinfo"+vehiculo.getIdVehiculo());
	}

    public void crearComponentes(){
		setLayout(null);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setBounds(10, 36, 140, 14);
		add(lblPrecio);

		lblModelo = new JLabel("Modelo");
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

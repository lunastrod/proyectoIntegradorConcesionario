package com.dam.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dam.control.ConcesionarioControlador;

public class PVerCatalogo extends JPanel implements IPanel{
    private static final int ANCHO=600;
    private static final int ALTO=400;

    public PVerCatalogo(){
        setLayout(null);
        setSize(ANCHO,ALTO);
        crearComponentes();
    }

    public void crearComponentes(){
        JLabel lblTitulo = new JLabel("catalogo");
        lblTitulo.setBounds(25, 20, 160, 20);
        add(lblTitulo);
    }

    public void setControlador(ConcesionarioControlador c){
        //btnVer.addActionListener(c);
    }
}

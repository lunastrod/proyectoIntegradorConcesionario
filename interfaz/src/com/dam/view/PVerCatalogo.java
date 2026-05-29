package com.dam.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;


import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Vehiculo;

//quizas podria ser un scrollpane

public class PVerCatalogo extends JPanel implements IPanel{
    private static final int ANCHO=1000;
    private static final int ALTO=1000;
    public static final int ANCHO_PANEL_VEHICULO=160;
    public static final int ALTO_PANEL_VEHICULO=160;
    private static final int DISTANCIA_PANEL_VEHICULO_X=20;
    private static final int DISTANCIA_PANEL_VEHICULO_Y=20;
    private static final int MARGEN_PANEL_VEHICULO_X=50;
    private static final int MARGEN_PANEL_VEHICULO_Y=150;
    private static final int NUM_PANEL_VEHICULO_X=5;
    private static final int NUM_PANEL_VEHICULO_Y=4;
    private PVehiculo[][] panelesVehiculo;

    public static ArrayList<String> MAS_INFO_BOTON_COMANDO;


    public PVerCatalogo(){
        setLayout(null);
        setSize(ANCHO,ALTO);
        crearComponentes();
    }

    public void crearComponentes(){
        JLabel lblTitulo = new JLabel("catalogo");
        lblTitulo.setBounds(25, 20, 160, 20);
        add(lblTitulo);

        panelesVehiculo = new PVehiculo[NUM_PANEL_VEHICULO_Y][NUM_PANEL_VEHICULO_X];
        System.out.println(panelesVehiculo.length+" "+panelesVehiculo[0].length);
        String comando;
        for (int i = 0; i < panelesVehiculo.length; i++) {
            for (int j = 0; j < panelesVehiculo[i].length; j++) {
                    comando="masinfo"+(i*panelesVehiculo[i].length+j);
                    panelesVehiculo[i][j] = new PVehiculo();
                    panelesVehiculo[i][j].setBounds(
                        MARGEN_PANEL_VEHICULO_X+(DISTANCIA_PANEL_VEHICULO_X+ANCHO_PANEL_VEHICULO)*j,
                        MARGEN_PANEL_VEHICULO_Y+(DISTANCIA_PANEL_VEHICULO_Y+ALTO_PANEL_VEHICULO)*i,
                        ANCHO_PANEL_VEHICULO,
                        ALTO_PANEL_VEHICULO
                    );
                    add(panelesVehiculo[i][j]);
            }  
        }
        //actualizarPanelesVehiculo();
    }

    public void actualizarPanelesVehiculo(ArrayList<Vehiculo> vehiculos){
        for (int i = 0; i < panelesVehiculo.length; i++) {
            for (int j = 0; j < panelesVehiculo[i].length; j++) {
                try{
                    //TODO: HAY QUE HACER ESTA LISTA EXTENSIBLE CON UN JSCROLLPANE
                    panelesVehiculo[i][j].setVehiculo(vehiculos.get(i*panelesVehiculo[i].length+j));
                }catch(Exception e){
                    System.out.println("ERROR NO HAY MAS ESPACIO DE VEHICULOS EN EL PANEL");
                }
            }  
        }
    }

    public void setControlador(ConcesionarioControlador c){
        //btnVer.addActionListener(c);
        for (int i = 0; i < panelesVehiculo.length; i++) {
            for (int j = 0; j < panelesVehiculo[i].length; j++) {
                panelesVehiculo[i][j].setControlador(c);
            }  
        }
    }


}

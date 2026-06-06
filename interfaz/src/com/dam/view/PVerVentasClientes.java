package com.dam.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import com.dam.control.ConcesionarioControlador;
import com.dam.model.data.Cliente;
import com.dam.model.data.Trabajador;
import com.dam.model.data.Venta;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Panel para poder ver los clientes registrados en la base de datos, aparte de las ventas realizadas.
 * <p>
 * @see IPanel
 * @see ConcesionarioControlador
 * @see Cliente
 * @see Venta
 */
public class PVerVentasClientes extends JPanel implements IPanel {
    /** Ancho estático predefinido para el panel en píxeles. */
    private static final int ANCHO = 1000;
    /** Alto estático predefinido para el panel en píxeles. */
    private static final int ALTO = 1000;

    /** 1ra Tabla interactiva que despliega los trabajadores almacenados en la base de datos. */
    private JTable table;
    
    /** 2da Tabla interactiva que despliega los trabajadores almacenados en la base de datos. */
    private JTable table1;
    
    /** Modelo estructurado que maneja el flujo de filas y columnas de la tabla, este para la tabla de clientes. */
    private DefaultTableModel dtmClientes;
    
    /** Modelo estructurado que maneja el flujo de filas y columnas de la tabla, este para la tabla de ventas. */
    private DefaultTableModel dtmVentas;

    /** Título de cabecera para la columna de ID del cliente. */
    private static final String CLM_ID_CLIENTE = "ID";
    /** Título de cabecera para la columna de nombre y apellidos del cliente. */
    private static final String CLM_NOMBRE_APELLIDOS = "Nombre y apellidos";
    /** Título de cabecera para la columna que representa el método de pago del cliente. */
    private static final String CLM_METODO_PAGO = "Método de pago";
    
    /** Título de cabecera para la columna del ID de venta. */
    private static final String CLM_ID_VENTA = "ID de venta";
    
    /** Título de cabecera para la columna de cliente en la tabla de ventas. */
    private static final String CLM_ID_CLIENTE_V = "Cliente";
    
    /** Título de cabecera para la columna del trabajador en la tabla de ventas. */
    private static final String CLM_ID_TRABAJADOR = "Trabajador";
    
    /** Título de cabecera para la columna del vcehiculo en la tabla de ventas. */
    private static final String CLM_ID_VEHICULO = "Vehículo";
    
    /** Título de cabecera para la columna de la fecha de venta. */
    private static final String CLM_FECHA = "Fecha de la venta";

    /**
     * Constructor principal.
     * Invoca los métodos internos para configurar el layout base y desplegar los elementos visuales.
     */
    public PVerVentasClientes() {
        configurarPanel();
        crearComponentes();
    }

    /**
     * Establece una disposición absoluta en base a coordenadas nulas (null layout)
     * y redimensiona los límites del contenedor principal.
     */
    public void configurarPanel() {
        setLayout(null);
        setSize(ANCHO, ALTO);
    }

    /**
     * Instancia, dimensiona mediante coordenadas fijas y añade al panel todos los
     * componentes gráficos, incluyendo rótulos, entradas, la tabla y los botones.
     */
    public void crearComponentes() {
        JLabel lblTitulo = new JLabel("Ventas y clientes registrados");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTitulo.setBounds(25, 20, 212, 20);
        add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(71, 88, 600, 254);
        add(scrollPane);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);
        configurarTabla();
        
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(71, 395, 800, 254);
        add(scrollPane1);
        
        table1 = new JTable();
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane1.setViewportView(table1);
        
        JLabel lblTablaDeVentas = new JLabel("Tabla de clientes");
        lblTablaDeVentas.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTablaDeVentas.setBounds(71, 58, 212, 20);
        add(lblTablaDeVentas);
        
        JLabel lblTablaDeVentas_1 = new JLabel("Tabla de ventas");
        lblTablaDeVentas_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTablaDeVentas_1.setBounds(71, 365, 212, 20);
        add(lblTablaDeVentas_1);
        configurarTabla1();
    }

    /**
     * Configura el comportamiento de edición de celdas anulándolo para el usuario,
     * inicializa los nombres de las columnas y define la anchura preferida de cada una.
     * Este es para la primera tabla, que es la de clientes.
     */
    private void configurarTabla() {
        dtmClientes = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(dtmClientes);
        dtmClientes.addColumn(CLM_ID_CLIENTE);
        dtmClientes.addColumn(CLM_NOMBRE_APELLIDOS);
        dtmClientes.addColumn(CLM_METODO_PAGO);
        table.getColumn(CLM_ID_CLIENTE).setPreferredWidth(50);
        table.getColumn(CLM_NOMBRE_APELLIDOS).setPreferredWidth(150);
        table.getColumn(CLM_METODO_PAGO).setPreferredWidth(100);
    }
    
    /**
     * Configura el comportamiento de edición de celdas anulándolo para el usuario,
     * inicializa los nombres de las columnas y define la anchura preferida de cada una.
     * Este es para la segunda tabla, que es de ventas.
     */
    private void configurarTabla1() {
        dtmVentas = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(dtmVentas);
        dtmVentas.addColumn(CLM_ID_VENTA);
        dtmVentas.addColumn(CLM_ID_CLIENTE_V);
        dtmVentas.addColumn(CLM_ID_TRABAJADOR);
        dtmVentas.addColumn(CLM_ID_VEHICULO);
        dtmVentas.addColumn(CLM_FECHA);
        table1.getColumn(CLM_ID_VENTA).setPreferredWidth(50);
        table1.getColumn(CLM_ID_CLIENTE_V).setPreferredWidth(75);
        table1.getColumn(CLM_ID_TRABAJADOR).setPreferredWidth(75);
        table1.getColumn(CLM_ID_VEHICULO).setPreferredWidth(75);
        table1.getColumn(CLM_FECHA).setPreferredWidth(100);
    }

    /**
     * Vacia el listado visual actual e inserta una nueva tanda de filas en base
     * a los elementos recibidos en la colección. Este es para la tabla de clientes.
     * @param listaClientes colección dinámica con la información de los clientes que se van a cargar.
     */
    public void cargarTabla(ArrayList<Cliente> listaClientes) {
        table.clearSelection();
        dtmClientes.getDataVector().clear();
        dtmClientes.fireTableDataChanged();
        for (Cliente cliente : listaClientes) {
            dtmClientes.addRow(new Object[]{
            	cliente.getIdCliente(),
            	cliente.getNombreApellidos(),
            	cliente.getMetodoPago()
            });
        }
    }
    
    /**
     * Vacia el listado visual actual e inserta una nueva tanda de filas en base
     * a los elementos recibidos en la colección. Este es para la tabla de ventas.
     * @param listaVentas colección dinámica con la información de las ventas que se van a cargar.
     */
    public void cargarTabla1(ArrayList<Venta> listaVentas) {
        table1.clearSelection();
        dtmVentas.getDataVector().clear();
        dtmVentas.fireTableDataChanged();
        for (Venta venta : listaVentas) {
            dtmVentas.addRow(new Object[]{
            	venta.getIdVenta(),
            	venta.getCliente(),
            	venta.getTrabajador(),
            	venta.getVehiculo(),
            	venta.getFecha()
            });
        }
    }
    
    /**
     * Enlaza el controlador principal del concesionario con los botones interactivos
     * del panel para recibir los eventos de interacción periférica.
     * @param c el manejador controlador central de flujos de negocio y navegación
     */
	@Override
	public void setControlador(ConcesionarioControlador c) {
	}
}
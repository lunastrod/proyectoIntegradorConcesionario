package com.dam.view;

import com.dam.control.ConcesionarioControlador;

/**
 * Interfaz que deben implementar todos los paneles de la aplicación.
 * <p>
 * Establece un contrato común para la creación de componentes visuales
 * y la asignación del controlador, permitiendo que el controlador
 * interactúe con cualquier panel de forma uniforme.
 * @see ConcesionarioControlador
 * @see IVentana
 */
public interface IPanel {

    /**
     * Crea e inicializa todos los componentes visuales del panel.
     * <p>
     * Se invoca durante la construcción del panel para añadir
     * y configurar los elementos de la interfaz como etiquetas,
     * campos de texto, botones y otros componentes Swing.
     */
    public void crearComponentes();

    /**
     * Registra el controlador en los componentes interactivos del panel.
     * <p>
     * Se llama una vez creados los componentes para añadir el controlador
     * como ActionListener de los botones y demás elementos que generan eventos.
     * @param c controlador principal de la aplicación
     */
    public void setControlador(ConcesionarioControlador c);
}
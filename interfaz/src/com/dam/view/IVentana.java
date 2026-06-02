package com.dam.view;

import com.dam.control.ConcesionarioControlador;

/**
 * Interfaz que deben implementar todas las ventanas de la aplicación.
 * <p>
 * Establece un contrato común para la configuración, creación de componentes,
 * asignación del controlador y visualización de cualquier ventana,
 * permitiendo que el controlador las gestione de forma uniforme.
 * @see ConcesionarioControlador
 * @see IPanel
 */
public interface IVentana {

    /**
     * Configura las propiedades básicas de la ventana.
     * <p>
     * Se ocupa de aspectos como el título, el tamaño, la posición
     * en pantalla, el comportamiento al cerrar y el layout principal.
     */
    public void configurarVentana();

    /**
     * Crea e inicializa todos los componentes visuales de la ventana.
     * <p>
     * Se invoca durante la construcción para añadir y configurar
     * los elementos de la interfaz como menús, paneles y otros
     * componentes Swing propios de la ventana.
     */
    public void crearComponentes();

    /**
     * Registra el controlador en los componentes interactivos de la ventana.
     * <p>
     * Se llama una vez creados los componentes para añadir el controlador
     * como el ActionListener de los elementos que generan eventos,
     * como los ítems de menú.
     * @param c controlador principal de la aplicación
     */
    public void setControlador(ConcesionarioControlador c);

    /**
     * Hace visible la ventana.
     * <p>
     * Se invoca una vez completada la inicialización de todos los
     * componentes y asignado el controlador, para mostrar la ventana al usuario.
     */
    public void hacerVisible();
}
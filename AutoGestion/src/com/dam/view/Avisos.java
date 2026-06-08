package com.dam.view;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Clase de utilidad para mostrar diálogos de aviso al usuario.
 * <p>
 * Agrupa los distintos tipos de mensajes emergentes usados en la aplicación
 * (información, advertencia, error y confirmación) en métodos estáticos,
 * evitando repetir la construcción de JOptionPane en cada panel o controlador.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     Avisos.info(this, "Operación completada correctamente.");
 *     if (Avisos.confirmar(this, "¿Deseas continuar?")) { ... }
 * </pre>
 */
public class Avisos {

    /**
     * Muestra un diálogo modal con un mensaje personalizable en tipo e icono.
     * <p>
     * Permite especificar manualmente el tipo de icono usando las constantes
     * de {@link JOptionPane}, como ERROR_MESSAGE, WARNING_MESSAGE o INFORMATION_MESSAGE.
     * @param c      componente padre sobre el que se centra el diálogo; puede ser null
     * @param msg    texto del mensaje a mostrar
     * @param titulo título de la ventana del diálogo
     * @param tipo   tipo de icono del diálogo según las constantes de JOptionPane
     */
    public static void mensaje(Component c, String msg, String titulo, int tipo) {
        JOptionPane.showMessageDialog(c, msg, titulo, tipo);
    }

    /**
     * Muestra un diálogo de error con el mensaje indicado.
     * <p>
     * El título del diálogo es siempre "Error" y se muestra el icono
     * de error de JOptionPane.
     * @param c   componente padre sobre el que se centra el diálogo; puede ser null
     * @param msg texto del mensaje de error a mostrar
     */
    public static void error(Component c, String msg) {
        JOptionPane.showMessageDialog(c, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un diálogo de advertencia con el mensaje indicado.
     * <p>
     * El título del diálogo es siempre "Aviso" y se muestra el icono
     * de advertencia de JOptionPane.
     * @param c   componente padre sobre el que se centra el diálogo; puede ser null
     * @param msg texto del mensaje de advertencia a mostrar
     */
    public static void aviso(Component c, String msg) {
        JOptionPane.showMessageDialog(c, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Muestra un diálogo informativo con el mensaje indicado.
     * <p>
     * El título del diálogo es siempre "Info" y se muestra el icono
     * informativo de JOptionPane.
     * @param c   componente padre sobre el que se centra el diálogo; puede ser null
     * @param msg texto del mensaje informativo a mostrar
     */
    public static void info(Component c, String msg) {
        JOptionPane.showMessageDialog(c, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un diálogo de confirmación con opciones Sí y No.
     * <p>
     * El título del diálogo es siempre "Confirmar".
     * @param c   componente padre sobre el que se centra el diálogo; puede ser null
     * @param msg texto de la pregunta a confirmar
     * @return true si el usuario pulsa Sí, false si pulsa No o cierra el diálogo
     */
    public static boolean confirmar(Component c, String msg) {
        return JOptionPane.showConfirmDialog(c, msg, "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
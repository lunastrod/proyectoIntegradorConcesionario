package com.dam.view;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Avisos {
    public static void mensaje(Component c, String msg, String titulo, int tipo) {
        JOptionPane.showMessageDialog(c, msg, titulo, tipo);
    }

    public static void error(Component c, String msg) {
        JOptionPane.showMessageDialog(c, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void aviso(Component c, String msg) {
        JOptionPane.showMessageDialog(c, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public static void info(Component c, String msg) {
        JOptionPane.showMessageDialog(c, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirmar(Component c, String msg) {
        return JOptionPane.showConfirmDialog(c, msg, "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    //ejemplo de uso: Avisos.info(this, "esto es un mensaje");
}

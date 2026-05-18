package com.dam.view;

import com.dam.control.ConcesionarioControlador;

public interface IVentana {
	public void configurarVentana();
	public void crearComponentes();
	public void setControlador(ConcesionarioControlador c);
	public void hacerVisible();
}

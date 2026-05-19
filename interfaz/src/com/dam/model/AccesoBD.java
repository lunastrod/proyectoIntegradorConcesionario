package com.dam.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AccesoBD {
	
	private String driver;
	private String url;
	
	public AccesoBD() {
		Properties prop = new Properties();
		
		InputStream is = null;
		
		try {
			is = new FileInputStream("DB/ConfiguracionDB.properties");
			prop.load(is);
			
			driver = prop.getProperty("DRIVER");
			url = prop.getProperty("URL");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("El fichero no ha sido encontrado");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No se ha podido leer el fichero");
		}
	}
	
	public Connection getConexion() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		
		Connection con = DriverManager.getConnection(url);
		
		return con;
	}
}

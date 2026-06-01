package com.dam.model.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Gestiona la conexión con la base de datos del concesionario.
 * <p>
 * Lee el driver y la URL de conexión desde el fichero de propiedades
 * DB/ConfiguracionDB.properties y proporciona conexiones JDBC
 * listas para usar al resto de clases DAO.
 *
 * @see ClienteDAO
 * @see TrabajadorDAO
 * @see VehiculoDAO
 * @see ModeloVehiculoDAO
 * @see VentaDAO
 */
public class AccesoBD {

    /** Nombre de la clase del driver JDBC leído desde el fichero de propiedades. */
    private String driver;

    /** URL de conexión a la base de datos leída desde el fichero de propiedades. */
    private String url;

    /**
     * Crea una instancia de AccesoBD cargando la configuración de conexión
     * desde el fichero DB/ConfiguracionDB.properties.
     * <p>
     * Si el fichero no se encuentra o no puede leerse, se imprime
     * la traza del error por consola y los campos quedan sin inicializar,
     * lo que provocará un fallo al intentar obtener una conexión.
     */
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

    /**
     * Crea y devuelve una nueva conexión a la base de datos.
     * <p>
     * Carga el driver JDBC registrado en el fichero de propiedades
     * y abre una conexión con la URL configurada.
     * Es responsabilidad del llamante cerrar la conexión cuando ya no
     * sea necesaria para liberar los recursos.
     *
     * @return conexión activa a la base de datos
     * @throws ClassNotFoundException si el driver JDBC no se encuentra en el classpath
     * @throws SQLException           si ocurre un error al establecer la conexión
     */
    public Connection getConexion() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url);
        return con;
    }
}
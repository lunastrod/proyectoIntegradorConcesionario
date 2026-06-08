package com.dam.model.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoBDTest extends AccesoBD {

    private static final String PATH = "DB/concesionarioPrueba.db";
    private static final String URL = "jdbc:sqlite:" + PATH;
    private static final String DRIVER = "org.sqlite.JDBC";

    public AccesoBDTest() {
        File f = new File(PATH);
        if (f.exists()) {
            f.delete();
        }
    }

    @Override
    public Connection getConexion() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(URL);
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("PRAGMA foreign_keys = ON");
        }
        return con;
    }
}
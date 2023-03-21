/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.utils;

import java.sql.SQLException;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 * Sin uso se borrara proximamente
 */


public class ConexionBaseDatos {
    private final static String url = "jdbc:mysql://localhost:33060/tickets";
    private final static String username = "root";
    private final static String password = "secret";
    private static BasicDataSource pool;

    public static BasicDataSource getConnection() throws SQLException {
        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(username);
            pool.setPassword(password);
            pool.setInitialSize(3);
            pool.setMaxIdle(10);
            pool.setMaxTotal(10);
        }
        return pool;
    }

}

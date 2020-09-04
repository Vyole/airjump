/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamers.flightsystem.classes;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.pool2.*;

/**
 *
 * @author Orbius
 */
public class Pool {

    // Connection Pool to make the DB more efficient.
    private final String DB = "airjump";
    private final String URL = "jdbc:mysql://localhost:3306/" + DB;
    private final String USER = "root";
    private final String PASS = "";

    private static BasicDataSource bds = null;
    private static Pool DataSource = null;

    private Pool() {
        bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUsername(USER);
        bds.setPassword(PASS);
        bds.setUrl(URL);

        bds.setMinIdle(5); // Min Inactive connections allowed.
        bds.setMaxIdle(10); // Max inactive connections allowed.
        bds.setMaxTotal(30); // Connections active and inactive are allowed in the pool.
        bds.setMaxWaitMillis(-1); // Waiting time if all connections are full.      
    }

    // Check that's only one instance of this class
    public static Pool getInstance() {

        if (DataSource == null) {
            DataSource = new Pool();

            return DataSource;
        } else {
            return DataSource;
        }

    }

    // Get connection from connection pool
    public Connection getConnection() throws SQLException {
        return this.bds.getConnection();
    }
    
    // Close Connection
    public void closeConnection(Connection connection) throws SQLException{
        connection.close();
    }

}

package com.management.project.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class implements  methods for creating connection to DB
 *
 * @author Вадим
 */
public class ConnectionMySql implements ConnectionDB {

    /**
     * User's name in MySQL server
     */
    private final static String USER = "root";

    /**
     * User's password in MySQL server
     */
    private final static String PASSWORD = "root";

    /**
     * Database's URL
     */
    private final static String DB_URL = "jdbc:mysql://localhost:3306/projectManagementDB?autoReconnect=true&useSSL=false";

    /**
     * reference of ConnectionMySql class
     */
    private static ConnectionMySql connectionMySql;

    /**
     * Private default constructor
     */
    private ConnectionMySql() {
    }

    /**
     * This method create instance of this class or return instance if it already exist
     *
     * @return instance of ConnectionMySql
     */
    public static ConnectionMySql getInstance() {
        if (connectionMySql == null) {
            connectionMySql = new ConnectionMySql();
        }
        return connectionMySql;
    }

    /**
     * This method create connection to DB
     *
     * @return connection to DB
     * @throws SQLException in case of connection problems
     */
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
